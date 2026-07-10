#!/usr/bin/env python3
"""
assemble.py - emit.py 의 줄 단위 결과를 모아 '함수 1개 = 자바 클래스 1개' 로 조립.

레퍼런스 src/ 의 클래스 골격을 따른다:

    package <pkg>;
    import kfcutil; import KfcGen; import <필요한 마크 클래스>;
    public final class <Cls> {
        private <Cls>() { throw new UnsupportedOperationException(); }
        public static void execute(ServerCommandSource source) {
            Entity executor = source.getEntity();
            if (executor == null) return;
            kfcutil.GameContext ctx = kfcutil.getOrCreateContext(source);
            ServerScoreboard sb = ctx.scoreboard;
            <emit 본문 줄들>
        }
    }

본문이 어떤 심볼(executor/sb/ctx/EntityType/...)을 쓰는지 스캔해서 필요한 선언과 import 만 넣는다.
"""
from __future__ import annotations
import re
from dataclasses import dataclass
from emit import emit_line, fqcn, sanitize, pascal, Emitted, set_group, reset_var_counter


# 본문에서 쓰는 심볼 -> (선언문, import들)
# 선언은 execute() 맨 위에 필요한 것만.
PRELUDE = {
    # 주의: 조기 return 금지 - 서버 소스(틱 진입점)는 executor 가 null 인 게 정상이고,
    # 원본 mcfunction 은 @s 의존 줄만 실패하고 나머지는 진행한다. executor 접근 식은
    # emit 이 전부 null-safe 로 생성한다.
    "executor": ("Entity executor = source.getEntity();",
                 ["net.minecraft.entity.Entity"]),
    "ctx":      ("KfcGen.GameContext ctx = KfcGen.getOrCreateContext(source);", []),
    "sb":       ("ServerScoreboard sb = ctx.scoreboard;",
                 ["net.minecraft.scoreboard.ServerScoreboard"]),
    "server":   ("net.minecraft.server.MinecraftServer server = source.getServer();", []),
}

# 본문 토큰 -> 추가 import
SYMBOL_IMPORTS = {
    "EntityType":           "net.minecraft.entity.EntityType",
    "Entity":               "net.minecraft.entity.Entity",
    "ServerPlayerEntity":   "net.minecraft.server.network.ServerPlayerEntity",
    "ServerCommandSource":  "net.minecraft.server.command.ServerCommandSource",
}


def base_imports(group: str) -> list[str]:
    return [
        f"{group}.generated.KfcGen",
        "net.minecraft.server.command.ServerCommandSource",
    ]


@dataclass
class JavaClass:
    fid: str          # 데이터팩 함수 id (kartmobil:main)
    package: str
    cls: str
    code: str
    native_lines: int
    gated_lines: int
    bridge_lines: int
    is_macro: bool = False
    macro_params: list = None
    fully_converted: bool = True


# ── 강제 브릿지(디버깅): 여기 등록된 prefix 로 시작하는 함수는 변환 가능 여부와 무관하게
#    원본 mcfunction 실행(instantExecuteFunction)으로 폴백한다. 브릿지는 데이터팩과 100% 동일
#    동작하므로, 의심 서브트리를 강제 브릿지하고 버그가 사라지는지 보며 이분 탐색하는 데 쓴다.
_FORCE_BRIDGE: list[str] = []
_TRACE: list[str] = []


def set_trace(prefixes):
    global _TRACE
    _TRACE = list(prefixes or [])


def _is_traced(fid: str) -> bool:
    for p in _TRACE:
        pn = p.rstrip("/")
        if fid == pn or fid.startswith(pn + "/"):
            return True
    return False


def set_force_bridge(prefixes):
    global _FORCE_BRIDGE
    _FORCE_BRIDGE = list(prefixes or [])


def _is_force_bridged(fid: str) -> bool:
    # 세그먼트 경계 인식: "ns:a/b" 는 "ns:a/b"(정확) 와 "ns:a/b/..."(하위)만 매칭하고
    # "ns:a/bc"(형제) 는 매칭하지 않는다. (예: "kartmobil:move/move" 가 move/movetp 를 안 잡음)
    for p in _FORCE_BRIDGE:
        pn = p.rstrip("/")
        if fid == pn or fid.startswith(pn + "/"):
            return True
    return False


# ══ emit/IR 레벨 셀렉터 CSE ══════════════════════════════════════════════════
# 함수 단위로, 동일한 base-source 단순형 nearest 스캔(비싼 전체 순회)을 1회로 합친다.
# 정확성은 '구조'로 보장한다:
#   · CSE 는 '확실히 read-only 인 명령'에서만 한다.
#   · 명령이 엔티티를 바꿀 가능성이 조금이라도 있으면 = '배리어' → 그 명령은 손대지 않고
#     스캔 캐시를 통째로 비운다. 따라서 스캔과 그 재사용 사이에는 변형이 존재할 수 없다.
#   · 배리어 판정은 '화이트리스트 외 모든 것'을 배리어로 본다(미지의 헬퍼 자동 배리어).
#
# SAFE = 엔티티의 위치/존재/커맨드태그를 바꾸지 않는다고 검증된 헬퍼만(단순형 스캔은
#        위치·거리·커맨드태그로만 필터하므로, 점수/팀/게임모드/인벤토리/회전 변경은 단순형에
#        영향 없음 → SAFE. 단 회전(rotateTo)·관전(spectate)은 보수적으로 배리어로 둔다).
_CSE_SAFE_HELPERS = frozenset({
    # 읽기/술어/컨텍스트
    "getOrCreateContext", "scoreMatches", "scoreMatchesEntity", "scoreCmp", "entityScoreMatches", "getScore",
    "getScoreOfEntity", "inRange", "itemsMatchSlots", "blockMatches", "nbtMatches",
    "posInRange", "posInBox", "posLoaded", "blockInTag", "entityInTypeTag", "entityHasPath",
    "storageHasPath", "hasEffect", "gamemodeIs", "testPredicate", "nbtGetStorage",
    "storageGetDouble", "nbtGetEntity", "entityGetDouble", "nbtGetBlock",
    # 스캔/읽기 셀렉터(변형 없음)
    "nearestEntityAnyType", "nearestEntity", "nearestPlayer", "nearestN", "nearestPlayerWhere",
    "firstEntity", "firstEntityAnyType", "anyEntityInTypeTag", "entityByUuid",
    "allEntitiesAnyType", "allEntities", "allEntitiesAny", "allEntitiesInBox",
    "anyPlayer", "anyPlayerWhere", "anyEntity", "anyEntityAnyType", "anyEntityInBox",
    "facingEntity", "localOffset", "anchorEyes",
    # 조회/박스/소스 생성(태그·위치 모두 불변 — 완전 안전). entitiesByTypeBox/anyEntityScored 는
    # 읽기, atEntity 는 소스 리바인드, rangeBox 는 박스 생성이라 엔티티 상태를 바꾸지 않는다.
    "entitiesByTypeBox", "atEntity", "rangeBox", "anyEntityScored",
    # 스코어보드(점수만)
    "setScore", "addScore", "resetScore", "opScore", "ensureObjective", "removeObjective",
    "setObjectiveDisplay", "enableTrigger",
    # 스토리지/매크로(엔티티 아님)
    "storagePutNumber", "nbtSetStorage", "storageRemovePath", "storagePutSnbt",
    "storageMacroArgs", "nbtAppendStorage", "macroArgs", "entityMacroArgs",
    # 표시/음향
    "titleText", "titleActionbar", "titleTimes", "tellraw", "playSound", "stopSound",
    "bossbarSetPlayers", "bossbarSetName", "bossbarSetValue", "bossbarSetMaxValue",
    "bossbarAdd", "bossbarSetColor",
    # 월드 블록/상태(엔티티 이동 아님)
    "setBlock", "fill", "clone", "forceloadAdd", "setWeather", "setTime",
    # 인벤토리/팀/게임모드/효과제거/속성/발전과제(단순형 미필터)
    "itemReplaceWith", "itemReplaceFrom", "clearItems", "teamModify", "teamJoin", "teamLeave",
    "teamAdd", "setGameMode", "effectClear", "attrModifierRemove", "attrModifierAdd", "advancement",
    # execute 컨텍스트 내비게이션(스스로 변형 안 함; 본문 변형은 같은 줄의 헬퍼로 따로 잡힘)
    "onVehicle", "onPassengers",
})
# 인라인(엔티티 메서드 직접 호출) 변형 — 존재만으로 배리어
_CSE_INLINE_BARRIER = (
    ".addCommandTag(", ".removeCommandTag(", ".stopRiding(", ".startRiding(",
    ".requestTeleport(", ".teleport(", ".remove(", ".refreshPositionAndAngles(",
    ".discard(", ".setVelocity(", ".setPosition(",
)
_CSE_HELPER_RE = re.compile(r'KfcGen\.([A-Za-z0-9_]+)\(')
# CSE 대상: base-source 단순형 단일-엔티티 스캔(괄호 1단 중첩까지)
_CSE_SCAN_RE = re.compile(
    r'KfcGen\.(?:nearestPlayer|nearestEntity|nearestEntityAnyType)\((?:[^()]|\([^()]*\))*\)')
# CSE 대상(전체-집합): List<Entity> 반환 쿼리. base-source 반복 시 1회 수집해 재사용.
_CSE_SET_RE = re.compile(
    r'KfcGen\.(?:allEntitiesAnyType|allEntitiesInBox|allEntities|nearestN)\((?:[^()]|\([^()]*\))*\)')
# CSE 대상(태그 스캔형): firstEntity/nearestEntity 의 태그 기반 단일 스캔. 데이터팩이 줄마다
# 같은 @e[tag=…,limit=1] 을 재탐색하는 걸(실측: 동일식 인접 반복쌍 509 중 508 이 함수호출
# 없이 무관한 tag추가/setScore 사이에 있음) 그 태그를 실제 add/remove·멤버 스폰/킬 하기 전까지
# 재사용한다. origin 은 _pos/source.getPosition()/es.getPosition() 등 무엇이든(캐시 키에 포함).
_CSE_TAG_RE = re.compile(
    r'KfcGen\.(?:firstEntity|nearestEntity)\((?:[^()]|\([^()]*\))*\)')
ENTITY_TAG_CSE = True   # 회귀 시 즉시 차단용 단일 토글

# ── 스코어 read CSE (mem2reg-lite) ──────────────────────────────────────────
# 같은 리터럴 (holder, objective) 점수를 한 함수 안에서 반복해 읽는 것(예: 프레임 디스패치의
# `if score #x obj matches N` 수백 줄)을, 그 점수를 쓰는 지점까지 Integer 지역변수 1회 읽기로
# 병합한다. scoreMatches 의 미설정(null)=false 시맨틱은 nullable 캐시 + 명시 범위검사로 복제.
# fail-closed 원칙: 확신 못 하는 것은 전부 전량 무효화 —
#   · 함수 호출(.execute/.executeReturn/instantExecute*): 호출부 점수 요약이 없으므로 배리어
#   · 미지 헬퍼(화이트리스트 외), 직접 sb.* 접근, 쓰기 인자 파싱 실패(복합식 홀더 등)
#   · advancement(보상 함수 실행 가능), summon/kill/damage 류(통계·criteria 유발 가능성)
# 쓰기의 표적 무효화: 리터럴 (h,o) 쓰기는 그 키만, 동적 홀더 쓰기는 그 objective 전체.
SCORE_READ_CSE = True   # 회귀 시 즉시 차단용 토글
_SCR_LIT = r'"(?:[^"\\]|\\.)*"'
_SCR_INT = r'-?\d+|Integer\.MIN_VALUE|Integer\.MAX_VALUE'
_SCR_ARG = rf'(?:{_SCR_LIT}|KfcGen\.nameOf\([^()]*\)|\([^()]*(?:\([^()]*\))?[^()]*\)|[^,()]+)'
_SCR_MATCH_RE = re.compile(rf'KfcGen\.scoreMatches\(sb, ({_SCR_LIT}), ({_SCR_LIT}), ({_SCR_INT}), ({_SCR_INT})\)')
_SCR_GET_RE   = re.compile(rf'KfcGen\.getScore\(sb, ({_SCR_LIT}), ({_SCR_LIT})\)')
_SCR_WRITE_RE = re.compile(rf'KfcGen\.(?:setScore|addScore|resetScore|enableTrigger)\(sb, ({_SCR_ARG}), ({_SCR_ARG})\s*[,)]')
_SCR_OP_RE    = re.compile(rf'KfcGen\.opScore\(sb, ({_SCR_ARG}), ({_SCR_ARG}),')
_SCR_WRITE_CNT_RE = re.compile(r'KfcGen\.(?:setScore|addScore|resetScore|enableTrigger)\(')
_SCR_OP_CNT_RE    = re.compile(r'KfcGen\.opScore\(')
_SCR_CLEARALL_RE  = re.compile(r'KfcGen\.(?:resetScoreWildcard|removeObjective|ensureObjective)\(')
# ── 엔티티-홀더(실행자) 점수 read CSE ──────────────────────────────────────
# 디스패치 트리 등에서 `{ Entity e = source.getEntity(); if (e!=null &&
#   scoreMatches(sb, nameOf(e), "obj", lo, hi)) {...} }` 블록이 같은 실행자·같은 objective 를
# 여러 번 읽는다(실측 ~26만 곳). 이 홀더는 source.getEntity()(메서드 param source 는 재대입
# 없음 → 메서드 내 안정)라, 그 objective 를 '쓰지 않는 구간'에선 값이 불변 → readScoreEnt 1회로 병합.
#   · 안전 게이트: 읽기 nameOf(X) 의 X 가 '이 em 에서 source.getEntity() 로만 바인딩된 별칭'일 때만
#     실행자 읽기로 인정(루프변수 e 등 오탐 방지). 키는 ("@EXEC", obj).
#   · 무효화: 실행자 점수 쓰기 setScore(sb, nameOf(e), obj,..)=동적홀더 → _score_effects 가
#     ("OBJ",obj) 반환 → ("@EXEC",obj) 도 함께 무효화(기존 규칙 재사용). 호출/배리어=전량.
#   · sb.getScore(e, ob) 는 e.getNameForScoreboard() 키 = nameOf(e)+holderOf 경로와 동일 조회 → 값 동치.
# 실행자 홀더 표기는 어셈블 시점 em 에서 `X.getNameForScoreboard()` 이고(이후 별도 패스가
# KfcGen.nameOf(X) 로 감쌈), 두 형태 모두 인정한다. var = group(1) 또는 group(2).
_ENT_H = r'(?:(\w+)\.getNameForScoreboard\(\)|KfcGen\.nameOf\((\w+)\))'
_SCR_MATCH_ENT_RE = re.compile(rf'KfcGen\.scoreMatches\(sb, {_ENT_H}, ({_SCR_LIT}), ({_SCR_INT}), ({_SCR_INT})\)')
_SCR_GET_ENT_RE   = re.compile(rf'KfcGen\.getScore\(sb, {_ENT_H}, ({_SCR_LIT})\)')
_EXEC_BIND_RE = re.compile(r'\b(\w+)\s*=\s*source\.getEntity\(\)')
_FOR_VAR_RE   = re.compile(r'for\s*\([^;:{}]*?\b(\w+)\s*:')
_ANY_ASSIGN_RE= re.compile(r'\b(\w+)\s*=\s*([^;]+);')

# source(메서드 param) 재대입 = 실행자 컨텍스트 변경(execute as/on/at). 이 지점 이후
# source.getEntity() 는 다른 엔티티가 될 수 있어 @EXEC 캐시의 배리어로 취급한다.
_SOURCE_REASSIGN_RE = re.compile(r'(?<![.\w])source\s*=\s*(?!source\s*;)')

def _reassigns_source(code: str) -> bool:
    return _SOURCE_REASSIGN_RE.search(code) is not None

def _exec_alias_vars(code: str) -> frozenset:
    """이 코드(em)에서 'source.getEntity() 로만' 바인딩된 실행자 별칭 변수 집합.
       루프변수이거나 다른 값으로도 대입되면 제외(오탐 방지 — fail-closed).
       source 를 재대입하는 em 은 실행자가 바뀌므로 전부 제외(fail-closed)."""
    if _reassigns_source(code):
        return frozenset()
    aliases = set(m.group(1) for m in _EXEC_BIND_RE.finditer(code))
    if not aliases:
        return frozenset()
    loopvars = set(m.group(1) for m in _FOR_VAR_RE.finditer(code))
    other = set()
    for m in _ANY_ASSIGN_RE.finditer(code):
        v, rhs = m.group(1), m.group(2)
        if v in aliases and 'source.getEntity()' not in rhs:
            other.add(v)                 # 같은 이름이 다른 값으로도 대입됨 → 불안정, 제외
    return frozenset(aliases - loopvars - other)
# 점수에 영향 없는 헬퍼(읽기/스캔/표시/월드/태그/이동 등). advancement 는 보상으로 함수를
# 실행할 수 있어 제외(배리어). summon/killEntity/lootSpawn/damage 는 미포함 = 배리어.
_SCORE_SAFE_HELPERS = frozenset({
    "getOrCreateContext", "scoreMatches", "scoreMatchesEntity", "scoreCmp", "entityScoreMatches", "getScore",
    "getScoreOfEntity", "readScore", "inRange", "itemsMatchSlots", "blockMatches", "nbtMatches",
    "posInRange", "posInBox", "posLoaded", "blockInTag", "entityInTypeTag", "entityHasPath",
    "storageHasPath", "hasEffect", "gamemodeIs", "testPredicate", "nbtGetStorage",
    "storageGetDouble", "nbtGetEntity", "entityGetDouble", "nbtGetBlock",
    "nearestEntityAnyType", "nearestEntity", "nearestPlayer", "nearestN", "nearestPlayerWhere",
    "firstEntity", "firstEntityAnyType", "anyEntityInTypeTag", "entityByUuid",
    "allEntitiesAnyType", "allEntities", "allEntitiesAny", "allEntitiesInBox",
    "anyPlayer", "anyPlayerWhere", "anyEntity", "anyEntityAnyType", "anyEntityInBox",
    "facingEntity", "localOffset", "anchorEyes",
    "entitiesByTypeBox", "atEntity", "rangeBox", "anyEntityScored", "typeBucketCopy",
    "storagePutNumber", "nbtSetStorage", "storageRemovePath", "storagePutSnbt",
    "storageMacroArgs", "nbtAppendStorage", "macroArgs", "entityMacroArgs",
    "titleText", "titleActionbar", "titleTimes", "tellraw", "playSound", "stopSound",
    "bossbarSetPlayers", "bossbarSetName", "bossbarSetValue", "bossbarSetMaxValue",
    "bossbarAdd", "bossbarSetColor",
    "setBlock", "fill", "clone", "forceloadAdd", "setWeather", "setTime",
    "itemReplaceWith", "itemReplaceFrom", "clearItems", "teamModify", "teamJoin", "teamLeave",
    "teamAdd", "setGameMode", "effectClear", "attrModifierRemove", "attrModifierAdd",
    "onVehicle", "onPassengers", "addTag", "removeTag", "teleportTo", "movePosition",
    "rotateTo", "nameOf", "hasPlayerPassenger", "trace",
})
_SCR_WRITE_HELPERS = frozenset({
    "setScore", "addScore", "resetScore", "enableTrigger", "opScore",
    "resetScoreWildcard", "removeObjective", "ensureObjective", "setObjectiveDisplay",
})


def _score_effects(em):
    """이 명령의 점수 캐시 영향. None=전량 무효화, 아니면 무효화 지시 집합:
       ("H","O") 리터럴 키 / ("OBJ","O") = 그 objective 의 모든 캐시."""
    if em.kind != "native":
        return None
    code = "\n".join(l for l in em.java if not l.lstrip().startswith("//"))
    inv0 = set()
    if "instantExecute" in code:
        return None                    # 브릿지 = 바닐라 임의 실행
    if ".execute(" in code or ".executeReturn(" in code:
        cs = _call_score(code)         # 인터프로시저 점수 요약(callee 가 쓰는 것만 무효화)
        if cs is None:
            return None
        inv0 |= cs
    if re.search(r'\bsb\.[A-Za-z]', code):
        return None                    # KfcGen 래퍼 밖 직접 스코어보드 접근
    for hm in _CSE_HELPER_RE.finditer(code):
        h = hm.group(1)
        if h not in _SCORE_SAFE_HELPERS and h not in _SCR_WRITE_HELPERS:
            return None                # 미지/criteria 유발 가능 헬퍼
    if _SCR_CLEARALL_RE.search(code):
        return None
    w = list(_SCR_WRITE_RE.finditer(code))
    ops = list(_SCR_OP_RE.finditer(code))
    # 인자 파싱 실패(복합식) fail-closed: 이름 기준 개수와 파싱 개수가 다르면 전량 무효화
    if len(w) != len(_SCR_WRITE_CNT_RE.findall(code)) or len(ops) != len(_SCR_OP_CNT_RE.findall(code)):
        return None
    inv = set(inv0)
    for m in w + ops:
        h, o = m.group(1), m.group(2)
        if not o.startswith('"'):
            return None                # objective 동적 = 전량
        if h.startswith('"'):
            inv.add((h, o))
        else:
            inv.add(("OBJ", o))        # 동적 홀더 = 그 objective 전체 무효화
    return inv


def _scr_apply_inv(cache: dict, inv) -> None:
    """무효화 지시(inv)를 캐시에 적용. cache 키는 (h,o)."""
    if inv:
        for k in list(cache):
            if k in inv or ("OBJ", k[1]) in inv or ("HLD", k[0]) in inv:
                cache.pop(k, None)


def _find_reused_score_reads(emitted) -> set:
    """리터럴 (h,o) 점수 읽기가 '그 키를 쓰는 지점/배리어 사이'에 2회+ 등장하면 병합 대상."""
    reused: set = set()
    seen: dict = {}
    for em in emitted:
        code = "\n".join(l for l in em.java if not l.lstrip().startswith("//"))
        for rx in (_SCR_MATCH_RE, _SCR_GET_RE):
            for m in rx.finditer(code):
                k = (m.group(1), m.group(2))
                seen[k] = seen.get(k, 0) + 1
                if seen[k] >= 2:
                    reused.add(k)
        _exec_al = _exec_alias_vars(code)         # 실행자-홀더 읽기(("@EXEC",obj)) 도 집계
        if _exec_al:
            for rx in (_SCR_MATCH_ENT_RE, _SCR_GET_ENT_RE):
                for m in rx.finditer(code):
                    var = m.group(1) or m.group(2)
                    if var not in _exec_al:
                        continue
                    k = ("@EXEC", m.group(3))
                    seen[k] = seen.get(k, 0) + 1
                    if seen[k] >= 2:
                        reused.add(k)
        eff = _score_effects(em)
        if eff is None:
            seen.clear()
        elif eff:
            for k in list(seen):
                if k in eff or ("OBJ", k[1]) in eff or ("HLD", k[0]) in eff:
                    seen.pop(k, None)
        if _reassigns_source(code):           # 실행자 변경 → @EXEC 바인딩 무효화(배리어)
            for k in list(seen):
                if k[0] == "@EXEC":
                    seen.pop(k, None)
    return reused


# ── 인터프로시저 태그 요약 (convert.py pass-1.5 가 주입) ────────────────────
# 종전엔 함수 호출(.execute/.executeReturn)을 '무슨 태그든 바꿀 수 있다'고 보고 캐시를
# 전량 무효화했다(실측: onkartcollision 의 carB 스캔 17회가 이 때문에 CSE 불가).
# convert.py 가 원본 mcfunction 소스에서 함수별 '변형 태그 + 위치변경' 요약을 호출 그래프
# 전이 폐포로 계산해 주입하면, 호출 시 그 함수가 실제로 건드리는 태그에 의존하는 캐시만
# 무효화한다. 요약은 소스 레벨이라 native/bridge 경로 모두에 유효하다(emit 은 tag/kill/
# summon 명령 외에 태그·엔티티집합을 바꾸는 코드를 합성하지 않음 — 실측 확인).
#   _CALL_EFFECTS: fqcn -> "ALL"(미지=전량) | (frozenset[변형태그], move:bool)
#                  (무효과(NONE) 함수는 미수록 — 사전 크기 절약)
#   _KNOWN_FQCNS:  요약이 계산된 전체 함수의 fqcn. 미포함 fqcn 호출 = fail-closed(미지).
# 둘 다 미주입이면 모든 함수 호출 = 미지 → 종전 동작과 동일(순수 가산적).
_CALL_EFFECTS: dict = {}
_KNOWN_FQCNS: frozenset = frozenset()

def set_call_summaries(effects: dict, known_fqcns):
    global _CALL_EFFECTS, _KNOWN_FQCNS
    _CALL_EFFECTS = dict(effects or {})
    _KNOWN_FQCNS = frozenset(known_fqcns or ())

_CALL_TARGET_RE = re.compile(r'([A-Za-z_][A-Za-z0-9_$.]*)\.(execute|executeReturn)\(')

def _call_effect(code: str):
    """code 내 모든 함수 호출의 합성 효과: (tags:set, move:bool) | None(미지).
       fail-closed: 요약 미주입, 미지 callee(스텁 등), 수신자를 식별 못 한
       `.execute(`/`.executeReturn(` 잔존 시 모두 None."""
    if not _KNOWN_FQCNS:
        return None
    tags: set = set()
    move = False
    n = 0
    for m in _CALL_TARGET_RE.finditer(code):
        n += 1
        eff = _CALL_EFFECTS.get(m.group(1))
        if eff is None:
            if m.group(1) not in _KNOWN_FQCNS:
                return None          # 미지 callee(스텁/비함수 수신자) = 전량 무효화
            continue                 # 알려진 함수 + 효과 미수록 = 무효과(NONE)
        te = eff[0] if isinstance(eff, tuple) and len(eff) == 2 and (eff[0] == "ALL" or isinstance(eff[0], tuple)) else eff
        if te == "ALL":
            return None
        tags |= te[0]
        move = move or te[1]
    # 정규식이 못 잡은 호출이 남아 있으면(비정형 수신자) fail-closed
    if n != code.count(".execute(") + code.count(".executeReturn("):
        return None
    return (tags, move)


def _call_score(code: str):
    """code 내 모든 함수 호출의 점수 쓰기 합성: 지시 set | None(미지).
       fail-closed 규칙은 _call_effect 와 동일(미지 callee/미주입/비정형 잔존)."""
    if not _KNOWN_FQCNS:
        return None
    inv = set()
    n = 0
    for m in _CALL_TARGET_RE.finditer(code):
        n += 1
        eff = _CALL_EFFECTS.get(m.group(1))
        if eff is None:
            if m.group(1) not in _KNOWN_FQCNS:
                return None
            continue                 # 효과 미수록 = 점수 무효과
        se = eff[1] if isinstance(eff, tuple) and len(eff) == 2 and (eff[0] == "ALL" or isinstance(eff[0], tuple)) else "SALL"
        if se == "SALL":
            return None
        inv |= se
    if n != code.count(".execute(") + code.count(".executeReturn("):
        return None
    return inv


def _cse_is_barrier(em) -> bool:
    """이 명령이 (단순형 nearest 캐시 관점에서) 엔티티 집합/위치/커맨드태그를 바꿀 수 있으면 True.
       확실히 read-only 가 아니면 보수적으로 True."""
    if em.kind != "native":
        return True  # 폴백/브릿지/디스패치 = 미지 → 배리어
    code = "\n".join(l for l in em.java if not l.lstrip().startswith("//"))
    if ".execute(" in code or ".executeReturn(" in code:
        # 인터프로시저 요약: '전이적으로 태그·위치·엔티티집합을 전혀 안 바꾸는' callee 만
        # 배리어 아님(나머지 검사는 계속). 태그 변형 callee 도 배리어 — nearest/집합 스캔은
        # 태그 필터를 포함할 수 있고 여기선 스캔별 태그 의존을 추적하지 않기 때문.
        ce = _call_effect(code)
        if ce is None or ce[0] or ce[1]:
            return True
    for p in _CSE_INLINE_BARRIER:
        if p in code:
            return True
    for m in _CSE_HELPER_RE.finditer(code):
        if m.group(1) not in _CSE_SAFE_HELPERS:
            return True  # 화이트리스트 외 헬퍼 = 배리어(미지 헬퍼 자동 포함)
    return False


def _cse_scan_eligible(expr: str) -> bool:
    """base-source 단순형만 CSE. rebound source(_onN 등)는 'source.getPosition()' 부재로 자동 제외."""
    if "source.getPosition()" not in expr:
        return False
    if "->" in expr:  # 술어 람다 형태(점수/게임모드 필터) 제외
        return False
    return True


_CSE_TAG_ARR_RE = re.compile(r'(new String\[\]\{[^}]*\}|KfcGen\.NO_TAGS)')

def _cse_tag_eligible(expr: str):
    """태그 스캔 CSE 적격이면 (양성태그 frozenset, 음성태그 frozenset) 반환, 아니면 None.
       - 술어 람다(->) 없음(점수/조건 필터는 상태 의존이라 제외).
       - 양성 태그 1개 이상(집합이 태그로 결정돼 무효화를 추적 가능).
       - origin 이 '메서드/세그먼트 전체 스코프'에서 유효한 것만: source.getPosition() 또는
         세그먼트 prelude 로컬 _pos. es/_on1/_asSrc 같은 블록-지역 origin 은 제외한다 —
         hoist 된 `Entity _tselN = ...` 선언이 그 블록 밖(본문 최상위)에 놓여 지역변수 origin 을
         못 찾는 컴파일 에러(cannot find symbol)를 유발하기 때문. (블록 지역 origin 반복은
         애초에 같은 블록 안에서만 반복되므로 hoist 이득도 작다.)
       캐시 키는 스캔식 문자열 전체(origin 표현·maxDist 포함)이므로 nearestEntity 최근접 정렬/
       거리필터도 origin 표현이 같은 한 결과가 같다."""
    if "->" in expr:
        return None
    # origin 인자(ctx 다음) 추출.
    m = re.match(r'KfcGen\.\w+\(\s*ctx\s*,\s*([^,]+?)\s*,', expr)
    origin = m.group(1).strip() if m else None
    # origin 무관 여부(firstEntity + min/max dist 둘 다 <0): inRange 가 origin 을 안 쓰므로
    # 결과가 origin 값과 무관하다. 이 경우 블록-지역 origin(_asSrc_N.getPosition() 등)이어도
    # 적격이며, hoist 시 origin 을 메서드-스코프 _pos 로 정규화해 여러 origin 의 동일 스캔을
    # 하나로 합친다(결과 동일 보장).
    origin_free = False
    mm2 = re.search(r',\s*(-?\d+)\s*,\s*(-?\d+)\s*\)\s*$', expr)
    if mm2 and expr.startswith("KfcGen.firstEntity(") \
            and int(mm2.group(1)) < 0 and int(mm2.group(2)) < 0:
        origin_free = True
    # origin-의존 스캔은 메서드-스코프 origin 만 hoist 안전(블록-지역은 스코프 밖 컴파일 에러).
    # origin-무관 스캔은 origin 을 _pos 로 정규화하므로 이 제한을 면제한다.
    if not origin_free and origin not in ("source.getPosition()", "_pos"):
        return None
    arrs = _CSE_TAG_ARR_RE.findall(expr)
    if len(arrs) < 2:
        return None
    def _tags(s):
        s = s.strip()
        if s == "KfcGen.NO_TAGS":
            return frozenset()
        inner = s[s.index("{") + 1:s.rindex("}")]
        return frozenset(t.strip().strip('"') for t in inner.split(",") if t.strip())
    pos = _tags(arrs[-2]); neg = _tags(arrs[-1])
    if not pos:
        return None
    return (pos, neg, origin_free)


_TAG_MUTATE_RE = re.compile(r'\.(?:addCommandTag|removeCommandTag)\("([^"]*)"\)')
# KfcGen.addTag/removeTag(<대상>, "이름") — 태그 버킷(런타임) 유지를 위해 emit 이 엔티티 메서드
# 직접 호출 대신 이 헬퍼를 경유한다. 태그 변형 분석은 두 형태 모두 인식(구형 생성물 호환).
_TAG_MUTATE2_RE = re.compile(r'KfcGen\.(?:addTag|removeTag)\(\s*[^,()]+,\s*"([^"]*)"\)')

def _cse_tags_mutated(em):
    """이 명령의 태그 캐시 영향. 반환:
         None             = 미지(전량 무효화: 미지 callee/미지 헬퍼/멤버 스폰·킬)
         (tags:set, move) = 변형 커맨드태그 집합 + 위치변경 여부.
       move=True 는 origin-'의존' 스캔만 무효화(origin-무관 스캔은 위치 변화와 결과 무관).
       tags 와 move 는 동시 표현 가능(종전 POS_REBIND 센티넬 대체) — 예: 태그 X 추가 +
       teleportTo 인 명령은 X-의존 스캔과 origin-의존 스캔을 함께 무효화하며, 그 외
       (X 미참조·origin-무관) 스캔은 유지해도 안전하다.
       함수 호출은 인터프로시저 요약(_call_effect)으로 그 함수가 실제 건드리는
       태그/위치만 반영한다(요약 미주입·미지 callee 는 종전대로 None)."""
    if em.kind != "native":
        return None
    code = "\n".join(l for l in em.java if not l.lstrip().startswith("//"))
    tags: set = set()
    move = False
    if ".execute(" in code or ".executeReturn(" in code:
        ce = _call_effect(code)
        if ce is None:
            return None                   # 미지 callee = 전량 무효화(종전 동작)
        tags |= ce[0]
        move = move or ce[1]
    # origin 로컬(_pos) 재대입: origin-의존 스캔만 무효화. (종전엔 여기서 조기 반환해
    # 같은 명령의 태그/스폰·킬 검사를 건너뛰었다 — 아래로 계속 진행해 그 구멍을 막는다.)
    if re.search(r'\b_pos\s*=', code) or re.search(r'\bVec3d\s+_pos\b', code):
        move = True
    tags |= set(_TAG_MUTATE_RE.findall(code))
    tags |= set(_TAG_MUTATE2_RE.findall(code))
    for p in (".remove(", ".discard(", ".kill(", "KfcGen.summon", "KfcGen.killEntity"):
        if p in code:
            return None                   # 멤버 스폰/킬 = 집합 크기 변동
    # 위치 변경 헬퍼(teleportTo/movePosition): 커맨드태그는 불변이나 엔티티 위치가 바뀌므로
    # origin-의존 스캔(거리 필터/최근접) 결과가 달라질 수 있다. origin-무관 스캔은 영향 없음.
    if "KfcGen.teleportTo(" in code or "KfcGen.movePosition(" in code:
        move = True
    for hm in _CSE_HELPER_RE.finditer(code):
        h = hm.group(1)
        if h in ("teleportTo", "movePosition"):
            continue                      # 위에서 move 로 정밀 처리됨
        if h in ("addTag", "removeTag"):
            continue                      # _TAG_MUTATE2_RE 로 태그 단위 정밀 처리됨
        if h not in _CSE_SAFE_HELPERS:
            return None                   # 미지 헬퍼 = 보수적 전량 무효화
    for p in _CSE_INLINE_BARRIER:
        if p in code and "CommandTag(" not in p:
            return None
    return (tags, move)


def _cse_tag_key(expr: str) -> str:
    """CSE 캐시 키. origin-무관 스캔(firstEntity·거리무제한)은 origin 인자만 달라도 결과가
       동일하므로, origin 을 source.getPosition() 으로 정규화해 서로 다른 origin 의 동일 스캔을
       하나로 합친다. source 는 모든 executeReturn(_seg) 의 파라미터라 어떤 스코프에서도 유효
       (_pos 는 세그먼트에만 있고 비세그먼트 함수엔 없어 스코프 위반을 유발 → source 사용).
       origin-의존 스캔은 origin 이 결과에 영향을 주므로 원식을 그대로 키로 쓴다."""
    el = _cse_tag_eligible(expr)
    if el and el[2]:   # origin_free
        return re.sub(r'(KfcGen\.firstEntity\(\s*ctx\s*,\s*)[^,]+?(\s*,)',
                      r'\1source.getPosition()\2', expr, count=1)
    return expr


def _find_reused_tag_exprs(emitted) -> set:
    """태그 스캔이 '그 태그를 변형·집합변동하는 배리어 사이'에 2회+ 등장하면 hoist 대상.
       단일 사용은 지역변수 churn 만 늘어 제외."""
    reused: set = set()
    seen: dict = {}       # 정규화 키 -> 등장 횟수
    key_variants: dict = {}  # 정규화 키 -> 원식 집합
    for em in emitted:
        code = "\n".join(l for l in em.java if not l.lstrip().startswith("//"))
        for m in _CSE_TAG_RE.finditer(code):
            e = m.group(0)
            if _cse_tag_eligible(e) is None:
                continue
            k = _cse_tag_key(e)
            seen[k] = seen.get(k, 0) + 1
            key_variants.setdefault(k, set()).add(e)
            if seen[k] >= 2:
                reused |= key_variants[k]   # 이 키의 모든 origin 변형을 hoist 대상으로
        mut = _cse_tags_mutated(em)
        if mut is None:
            seen.clear(); key_variants.clear()
        else:
            mtags, mmove = mut
            if mtags or mmove:
                # 변형 태그에 의존하거나, 위치가 바뀌었는데 origin-의존인 스캔만 제거
                for k in list(seen):
                    el = _cse_tag_eligible(k)
                    if el and ((el[0] & mtags) or (el[1] & mtags)
                               or (mmove and not el[2])):
                        seen.pop(k, None); key_variants.pop(k, None)
    return reused


def _find_reused_set_exprs(emitted) -> set:
    """배리어 없는 구간에서 동일한 full-set 쿼리식이 2회+ 등장하면 hoist 대상으로 표시.
       단일 사용 쿼리는 hoist 하지 않아(불필요한 지역변수 churn 방지) 실제 재사용만 1회 수집.
       배리어 판정/구간 경계는 기존 nearest CSE 와 동일(_cse_is_barrier)."""
    reused: set = set()
    seen: dict = {}
    for em in emitted:
        code = "\n".join(l for l in em.java if not l.lstrip().startswith("//"))
        for m in _CSE_SET_RE.finditer(code):
            e = m.group(0)
            if not _cse_scan_eligible(e):
                continue
            seen[e] = seen.get(e, 0) + 1
            if seen[e] >= 2:
                reused.add(e)
        if _cse_is_barrier(em):
            seen.clear()
    return reused




# ── Java 'normal completion'(JLS 14.21) 간이 흐름분석 ───────────────────────
# TCO 루프 본문이 '끝까지 fall-through(정상 완료)' 가능할 때만 trailing `return 0;` 이
# 도달 가능하다. 무조건 `continue`/`return` 으로 끝나는 본문(예: 마지막 obj 가
# `execute positioned ^ ^ ^X run function self` → `{ source=ARG; continue; }` 무조건 블록)
# 에선 그 return 이 unreachable 이라 Java 컴파일 에러가 된다. 문자열/괄호/중괄호 균형,
# if/else, 블록, for/while/do/switch 를 처리한다(주석은 줄 단위 // 만 제거).

def _idchar(c):
    return bool(c) and bool(re.match(r'\w', c))

def _fa_strip_comments(code):
    """// 라인주석(트레일링 포함)과 /* */ 블록주석을 제거한다(문자열/char 리터럴 내부 보호).
       기존 구현은 '줄 시작 //' 라인만 걸러서, `return 0;   // 설명` 의 트레일링 주석이
       _fa_top_statements 에서 별도 '문장'으로 살아남았다 — 그 주석 문장이 블록의 마지막
       문장이 되면 _stmt_completes 기본값(True)으로 흘러 완료성 판정이 오염된다
       (매크로 골격 catch 의 `return 0; // …` 가 정상완료로 오판 → audit MISSING 오탐)."""
    out = []; i = 0; n = len(code)
    while i < n:
        c = code[i]
        if c in '"\'':
            j = _fa_skip_string(code, i)
            out.append(code[i:j]); i = j; continue
        if c == '/' and i + 1 < n and code[i + 1] == '/':
            while i < n and code[i] != '\n':
                i += 1
            continue
        if c == '/' and i + 1 < n and code[i + 1] == '*':
            i += 2
            while i + 1 < n and not (code[i] == '*' and code[i + 1] == '/'):
                i += 1
            i = min(i + 2, n)
            continue
        out.append(c); i += 1
    return ''.join(out)

def _fa_skip_string(s, i):
    q = s[i]; i += 1; n = len(s)
    while i < n:
        if s[i] == '\\':
            i += 2; continue
        if s[i] == q:
            return i + 1
        i += 1
    return n

def _fa_balanced(s, i):
    """s[i] 가 '(' 또는 '{' 일 때 짝 맞는 닫힘 '다음' 인덱스(문자열 인식)."""
    open_c = s[i]; close_c = ')' if open_c == '(' else '}'
    depth = 0; n = len(s)
    while i < n:
        c = s[i]
        if c in '"\'':
            i = _fa_skip_string(s, i); continue
        if c == open_c:
            depth += 1
        elif c == close_c:
            depth -= 1
            if depth == 0:
                return i + 1
        i += 1
    return n

def _fa_stmt_end(code, i):
    n = len(code); rest = code[i:]
    m = re.match(r'(if|for|while|switch)\b', rest)
    if m:
        kw = m.group(1); j = i + len(kw)
        while j < n and code[j] in ' \t\r\n': j += 1
        if j < n and code[j] == '(':
            j = _fa_balanced(code, j)
        j = _fa_substmt_end(code, j)
        if kw == 'if':
            k = j
            while k < n and code[k] in ' \t\r\n': k += 1
            if code[k:k+4] == 'else' and not _idchar(code[k+4] if k+4 < n else ''):
                return _fa_substmt_end(code, k + 4)
        return j
    if re.match(r'do\b', rest):
        j = _fa_substmt_end(code, i + 2)
        while j < n and code[j] in ' \t\r\n': j += 1
        if re.match(r'while\b', code[j:]):
            j += 5
            while j < n and code[j] in ' \t\r\n': j += 1
            if j < n and code[j] == '(':
                j = _fa_balanced(code, j)
            while j < n and code[j] != ';': j += 1
            return j + 1 if j < n else n
        return j
    if re.match(r'try\b', rest):
        # try block (catch (..) block)* (finally block)? — 세미콜론 없이 끝나는 블록문이라
        # 마지막 catch/finally 블록까지를 한 문장으로 본다(뒤 문장과 병합 방지).
        j = _fa_substmt_end(code, i + 3)
        while True:
            k = j
            while k < n and code[k] in ' \t\r\n': k += 1
            if code[k:k+5] == 'catch' and not _idchar(code[k+5] if k+5 < n else ''):
                k += 5
                while k < n and code[k] in ' \t\r\n': k += 1
                if k < n and code[k] == '(':
                    k = _fa_balanced(code, k)
                j = _fa_substmt_end(code, k)
            elif code[k:k+7] == 'finally' and not _idchar(code[k+7] if k+7 < n else ''):
                j = _fa_substmt_end(code, k + 7)
                break
            else:
                break
        return j
    if code[i] == '{':
        return _fa_balanced(code, i)
    j = i
    while j < n:
        c = code[j]
        if c in '"\'':
            j = _fa_skip_string(code, j); continue
        if c == '{' or c == '(':
            j = _fa_balanced(code, j); continue
        if c == ';':
            return j + 1
        j += 1
    return n

def _fa_substmt_end(code, j):
    n = len(code)
    while j < n and code[j] in ' \t\r\n': j += 1
    if j >= n:
        return n
    return _fa_stmt_end(code, j)

def _fa_top_statements(code):
    code = _fa_strip_comments(code)
    stmts = []; i = 0; n = len(code)
    while i < n:
        while i < n and code[i] in ' \t\r\n;':
            i += 1
        if i >= n:
            break
        end = _fa_stmt_end(code, i)
        s = code[i:end].strip()
        if s:
            stmts.append(s)
        i = end if end > i else i + 1
    return stmts

def _stmt_completes(stmt):
    s = stmt.strip()
    if not s:
        return True
    if re.match(r'(return|continue|break|throw)\b', s):
        return False
    if re.match(r'if\b', s):
        i = s.index('(')
        e = _fa_balanced(s, i)
        then_stmt = s[e:_fa_substmt_end(s, e)].strip()
        rest = s[_fa_substmt_end(s, e):].strip()
        if rest.startswith('else') and not _idchar(rest[4] if len(rest) > 4 else ''):
            return _stmt_completes(then_stmt) or _stmt_completes(rest[4:].strip())
        return True  # else 없는 if 는 항상 정상 완료(조건 거짓 경로)
    if s.startswith('{'):
        inners = _fa_top_statements(s[1:_fa_balanced(s, 0) - 1])
        return _stmt_completes(inners[-1]) if inners else True
    if re.match(r'try\b', s):
        # JLS 14.21: try-catch(-finally) 문의 정상 완료 =
        #   finally 가 비정상 완료면 전체 비정상, 아니면 (try 블록 정상) ∨ (어떤 catch 블록 정상).
        # 이 규칙이 없으면 try 문이 항상 '정상 완료'(기본값 True)로 오판된다 — catch 가 빈
        # 블록이던 시절엔 우연히 참이었지만, 매크로 파싱 실패 래핑이 `catch { throw MACRO_FAIL; }`
        # 가 되면서 마지막 문장이 `try { ... return X; } catch { throw; }` 인 함수에 trailing
        # return 이 붙어 javac 'unreachable statement' 가 났다(Bucket 컴파일 실패의 근본 원인).
        i = s.index('{')
        e = _fa_balanced(s, i)
        try_ok = _stmt_completes(s[i:e])
        rest = s[e:].lstrip()
        any_catch_ok = False
        while rest.startswith('catch'):
            p = rest.index('(')
            pe = _fa_balanced(rest, p)
            b = rest.index('{', pe)
            be = _fa_balanced(rest, b)
            if _stmt_completes(rest[b:be]):
                any_catch_ok = True
            rest = rest[be:].lstrip()
        if rest.startswith('finally'):
            b = rest.index('{')
            be = _fa_balanced(rest, b)
            if not _stmt_completes(rest[b:be]):
                return False
        return try_ok or any_catch_ok
    if re.match(r'while\s*\(\s*true\s*\)', s):
        return 'break' in s
    if re.match(r'(for|while|switch|do)\b', s):
        return True  # 조건 있는 루프/스위치는 정상 완료 가능(코드젠은 무한루프 미생성)
    return True

def _completes_normally(code):
    """문장 시퀀스 code 가 정상 완료(끝까지 fall-through) 가능한지 — 마지막 top-level 문장으로 판정.
       (앞 문장이 무조건 종료하면 그건 별도 unreachable 에러이므로 코드젠이 만들지 않는다고 가정.)"""
    stmts = _fa_top_statements(code)
    return _stmt_completes(stmts[-1]) if stmts else True


def _tail_return_str(indented_body: str) -> str:
    """executeReturn 말미 trailing `return 0;` 문자열 — 본문이 정상 완료(fall-through)
       가능할 때만 붙인다(JLS 14.21 흐름분석 _completes_normally 단일 기준).
       매크로/비-TCO/TCO 경로가 모두 이 한 함수를 쓴다(분석 일원화)."""
    return "\n        return 0;" if _completes_normally(indented_body) else ""


def audit_executeReturn(code: str) -> list[str]:
    """생성된 클래스 코드의 executeReturn 본문을 흐름분석으로 재검증한다(코드젠 신뢰 안 함).
       반환: 위반 코드 리스트(빈 리스트=정상). 컴파일 전 회귀 감지용(개선사항 E).
       - MISSING_RETURN: 비-TCO 메서드 본문이 정상 완료(fall-through)인데 return 없음 → javac 'missing return'.
       - UNREACHABLE_RETURN: 마지막 문장이 return 인데 직전 문장이 정상 완료 안 함 → javac 'unreachable'."""
    issues = []
    for sig in ("int executeReturn(ServerCommandSource source, Map<String, String> macroArgs)",
                "int executeReturn(ServerCommandSource source)"):
        i = code.find(sig)
        if i < 0:
            continue
        b = code.find("{", i)
        if b < 0:
            continue
        body = code[b + 1:_fa_balanced(code, b) - 1]
        # TCO 메서드: int _tcoSteps=0; while(true){ <loop> } — 검사 대상은 루프 본문.
        m = re.search(r'while\s*\(\s*true\s*\)\s*\{', body)
        if m:
            lb = body.find("{", m.start())
            check = body[lb + 1:_fa_balanced(body, lb) - 1]
            # while(true) 는 break 없으면 메서드를 정상 완료시키지 않으므로 missing-return 아님.
        else:
            check = body
            if _completes_normally(body):
                issues.append(f"MISSING_RETURN[{sig.split('(')[0].split()[-1]}]")
        stmts = _fa_top_statements(check)
        if len(stmts) >= 2 and re.match(r'return\b.*;$', stmts[-1].strip()) \
                and not _stmt_completes(stmts[-2]):
            issues.append(f"UNREACHABLE_RETURN[{sig.split('(')[0].split()[-1]}]")
    return issues


# ── 꼬리재귀 → 루프 변환(TCO) ───────────────────────────────────────────────
# 자기재귀 함수는 기본적으로 함수 단위 브릿지(StackOverflow 방지)로 폴백한다.
# 그러나 재귀가 '마지막 줄의 단일 꼬리호출'이면 do/while 루프로 바꿔 스택을 쌓지
# 않고 네이티브화할 수 있다(표준 TCO). side-effect 시퀀스가 재귀와 완전히 같고,
# 꼬리 위치라 재귀의 반환값이 곧 다음 반복의 반환값이 되어 반환값까지 보존된다.
# 적격성은 극도로 보수적으로 판정한다(부적격 함수에 적용하면 의미가 깨지므로).
ENABLE_TCO = True

def _tco_selfcall_re(self_fqcn: str):
    import re as _re
    return _re.compile(_re.escape(self_fqcn) + r'\.(?:execute|executeReturn)\(')

def _tco_eligible(emitted, self_fqcn: str, is_macro: bool) -> bool:
    """TCO 적격: (1) 비매크로, (2) self-recursion 외 브릿지 사유 없음,
       (3) self-call 이 '마지막 실행 obj' 에서만 등장(꼬리), (4) 그 obj 의 self-call
       뒤에는 닫는 괄호/주석/공백만(뒤따르는 실행문 없음 = 진짜 tail)."""
    if not ENABLE_TCO or is_macro:
        return False
    if any(em.kind in ("bridge", "dispatch") for em in emitted):
        return False
    rx = _tco_selfcall_re(self_fqcn)
    # self-call 을 포함하는 obj 인덱스(주석 줄 제외한 실제 코드 줄 기준)
    self_idx = [i for i, em in enumerate(emitted)
                if any(rx.search(l) for l in em.java if not l.lstrip().startswith("//"))]
    if not self_idx:
        return False
    # 마지막 '실행' obj 인덱스(terminal/주석-only 가 아닌 마지막). emitted 는 parse 순서.
    last_exec = None
    for i, em in enumerate(emitted):
        if any(l.strip() and not l.lstrip().startswith("//") for l in em.java):
            last_exec = i
    if self_idx != [last_exec]:
        return False   # self-call 이 마지막 실행 obj 에서만 있어야(꼬리)
    # 그 obj 의 self-call 뒤에 실행문이 없고(진짜 tail), 닫는 '}' 가 있어야 한다.
    # 참고: 이 'closed' 검사는 조건/무조건 블록을 구분하지 않으므로, 가드 없는 무조건
    # tail self-call 도 적격이 된다. 이는 안전하다 — (1) executeReturn 루프 상단의
    # `_tcoSteps > 65536` 가드가 바닐라 maxCommandChainLength 컷오프를 모방해 무한루프를
    # 막고, (2) 무조건 self-call 은 본문이 `{ source=ARG; continue; }` 로 끝나 흐름분석
    # (_completes_normally)이 trailing `return 0;` 을 unreachable 로 보고 생략한다.
    em = emitted[last_exec]
    seen = False; closed = False
    for l in em.java:
        if l.lstrip().startswith("//"):
            continue
        if rx.search(l):
            seen = True
            continue
        if seen:
            s = l.strip()
            if not s:
                continue
            if all(c in "{}" for c in s):
                if "}" in s:
                    closed = True
                continue
            return False   # self-call 뒤 실행문 존재 → tail 아님
    return seen and closed

def _tco_rewrite(body: str, self_fqcn: str) -> str:
    """body 내 self-call `{fqcn}.execute(ARG);`/`.executeReturn(ARG);` 를
       `source = ARG; continue;` 로 치환(괄호 균형으로 ARG 정확 추출)."""
    import re as _re
    rx = _tco_selfcall_re(self_fqcn)
    out = []
    for line in body.split("\n"):
        if line.lstrip().startswith("//"):
            out.append(line); continue
        m = rx.search(line)
        if not m:
            out.append(line); continue
        start = m.end(); depth = 1; i = start
        while i < len(line) and depth:
            if line[i] == '(': depth += 1
            elif line[i] == ')': depth -= 1
            i += 1
        if depth != 0:          # ARG 가 한 줄로 안 닫힘(예상 밖) → 안전하게 미치환
            out.append(line); continue
        arg = line[start:i-1]
        indent = line[:len(line) - len(line.lstrip())]
        out.append(f"{indent}source = {arg};")
        out.append(f"{indent}continue;")
    return "\n".join(out)


# ═══ 거대 메서드 세그먼트 분할 (JIT HugeMethodLimit 대응) ═══
# HotSpot 은 기본 DontCompileHugeMethods=true, HugeMethodLimit=8000(바이트코드 바이트)로
# 8KB 초과 메서드를 '영원히 인터프리터로만' 실행한다. pushslowly-rectangle(충돌 물리)·
# boost-sound 등 핫패스 틱 함수가 이 한계를 넘으면 다른 모든 최적화가 무의미해진다.
# (기존 bridge_oversized 는 javac 64KB 한계만 처리 — 8KB~64KB 구간이 사각지대였다.)
# 해법: executeReturn 본문을 '라인 그룹'(mcfunction 한 줄 = 자체 스코프 블록) 경계에서
# Integer 반환 조각(private static executeReturn_seg<k>)으로 분할한다.
#   · return 시맨틱 무손실: 조각 반환형이 Integer 라 본문의 `return <int식>;` 이 자동박싱으로
#     무수정 컴파일되고, 정상 완료는 `return null;` — 본체가 null 아니면 즉시 전파.
#   · MacroParseFail(unchecked) 은 조각에서 던져도 본체 try 가 그대로 잡는다.
#   · CSE(_selN/_eset) 캐시는 조각 경계에서 clear 되어 조각-로컬로 격리(경계는 조립 전 확정).
#   · TCO/브릿지/거부/trace 함수는 분할 제외(기존 경로 그대로).
_SEG_TRIGGER = 9000   # 주석 제외 코드 문자수 — 초과 시에만 분할 발동
_SEG_TARGET  = 5500   # 조각 목표 코드 문자수(바이트코드 최악 ~1.2x 가정에도 8000B 안전권)
_SEG_MARK    = "// __KFC_SEG_BOUNDARY__"   # 조립-렌더 간 조각 경계 전달용(최종 출력서 제거)

def _seg_strip_str(l: str) -> str:
    """문자열 리터럴 '내부' 제거 — 문자열은 상수풀로 빠져 바이트코드에 미포함(ldc 참조뿐)."""
    out=[]; ins=False; esc=False
    for ch in l:
        if ins:
            if esc: esc=False
            elif ch=='\\': esc=True
            elif ch=='"':
                ins=False; out.append('"')
        else:
            out.append(ch)
            if ch=='"': ins=True
    return ''.join(out)

def _code_chars(lines) -> int:
    """주석/공백/문자열내용 제외 실질 코드 문자수(메서드 바이트코드 근사).
       거대 SNBT 문자열 한 줄짜리 모델 함수가 불필요하게 분할되지 않도록 문자열은 제외."""
    return sum(len(_seg_strip_str(l)) for l in lines
               if l.strip() and not l.lstrip().startswith("//"))

def _seg_bounds_for(emitted) -> set[int]:
    """emitted 그룹별 코드량 누적으로 분할 경계(그룹 시작 인덱스) 사전 계산."""
    bounds=set(); acc=0
    for i, em in enumerate(emitted):
        g=_code_chars(em.java)
        if acc>0 and acc+g>_SEG_TARGET:
            bounds.add(i); acc=0
        acc+=g
    return bounds

def _seg_params(seg_body: str) -> tuple[str, str]:
    """조각 본문이 사용하는 표준 로컬을 '파라미터 수취'로 전환하기 위한
       (시그니처 추가분, 호출 전달분) 생성.
       [GC 압박 제거] 기존엔 각 조각이 자체 prelude 로 executor/_exName/_pos/ctx/sb/server 를
       재계산했다 — 본체 + N 조각이 매 실행마다 getOrCreateContext / getPosition(새 Vec3d 할당) /
       getNameForScoreboard(비플레이어면 String 할당) 등을 조각 수만큼 중복 호출해 할당·GC 압력이
       분할 이전 대비 배가됐다. 본체 prelude 는 이미 '분할 전 전체 body 기준(합집합)'으로 이 로컬들을
       1회 선언하므로(used/cache_* 가 전체 body 로 계산됨), 조각은 재계산 대신 인자로 받는다.
       (_exName/_pos 치환은 전체 body 에서 이미 수행된 뒤라 여기선 '사용'만 존재.)"""
    scan = "\n".join(l for l in seg_body.split("\n") if not l.lstrip().startswith("//"))
    used = set(re.findall(r"\b[A-Za-z_]\w*\b", scan))
    # (파라미터명, 자바 타입) — 본체 prelude 선언 타입과 1:1. 순서는 결정적(같은 입력 = 같은 출력).
    order = [
        ("executor", "Entity"),
        ("_exName",  "String"),
        ("_pos",     "net.minecraft.util.math.Vec3d"),
        ("ctx",      "KfcGen.GameContext"),
        ("sb",       "ServerScoreboard"),
        ("server",   "net.minecraft.server.MinecraftServer"),
    ]
    sig, args = [], []
    for name, jtype in order:
        if name in used:
            sig.append(f"{jtype} {name}")
            args.append(name)
    if not sig:
        return "", ""
    return ", " + ", ".join(sig), ", " + ", ".join(args)


def function_to_class(fid: str, parse_trees: list[dict], group: str = "kartriderpack") -> JavaClass:
    """한 함수의 파스트리 줄들 -> 자바 클래스 코드."""
    reset_var_counter()   # 함수 단위 결정적 변수 번호(같은 입력 = 같은 출력 바이트)
    set_group(group)   # emit 의 fqcn 이 같은 group 을 쓰도록 (호출↔패키지 일치)
    ns, path = fid.split(":", 1)
    segs = path.split("/")
    package = ".".join([group] + [sanitize(ns)] + [sanitize(s) for s in segs[:-1]])
    cls = pascal(segs[-1])

    # 각 줄 emit
    body_lines: list[str] = []
    n_native = n_gated = n_bridge = 0
    rejected = False
    reject_reason = ""

    emitted = [emit_line(obj) for obj in parse_trees]
    # ── 자기재귀(self-recursion) 감지 → 함수 단위 브릿지 강제 ──
    # `run function X` 류는 직접 Java 호출(X.execute / X.executeReturn)로 컴파일된다.
    # 데이터 기반 꼬리재귀(예: trackselect:.../show-track-loop, show-theme-loop)는
    # 자기 자신을 호출해 JVM 콜스택을 깊이=반복횟수 만큼 쌓고 StackOverflowError 로
    # 서버를 죽인다. 바닐라는 함수 호출을 명령 큐로 '반복' 실행(maxCommandChainLength
    # 한도, 초과 시 조용히 중단)하므로 터지지 않는다. 브릿지(instantExecuteFunction)는
    # 바닐라 CommandExecutionContext 를 그대로 쓰므로, 자기 자신을 호출하는 함수는
    # 함수 단위로 브릿지에 맡겨 바닐라 시맨틱(+컷오프)과 100% 일치시키고 크래시를 막는다.
    _self_fqcn = f"{package}.{cls}"
    is_self_recursive = any(
        ((_self_fqcn + ".execute(") in _l) or ((_self_fqcn + ".executeReturn(") in _l)
        for _em in emitted for _l in _em.java
    )
    # 꼬리재귀면 브릿지 대신 루프(TCO)로 네이티브화한다(아래 조립부에서 분기).
    _has_macro_line = any(o.get("macro") for o in parse_trees)
    tco = _tco_eligible(emitted, _self_fqcn, _has_macro_line)
    # 함수 단위 파싱 거부: 한 줄이라도 무효 명령이면 mcfunction 은 함수 전체를 로드 거부.
    for obj, em in zip(parse_trees, emitted):
        if em.rejects_function:
            rejected = True
            reject_reason = em.reason
            break

    # ── 세그먼트 분할 사전 판정(JIT HugeMethodLimit 대응) ──
    # 폴백/거부/TCO/trace 함수는 기존 경로 그대로(분할 없음). 경계는 조립 '전'에 확정해
    # CSE(_selN/_eset) 재사용이 조각 경계를 넘지 않도록 루프에서 캐시를 끊는다.
    _will_fallback = (rejected or (is_self_recursive and not tco) or _is_force_bridged(fid)
                      or any(_em.kind in ("bridge", "dispatch") for _em in emitted))
    _total_code = sum(_code_chars(_em.java) for _em in emitted)
    cse_cache: dict[str, str] = {}   # scan_expr -> _selN  (현재 유효한 바인딩)
    cse_set_cache: dict[str, str] = {}  # full-set expr -> _esetN (재사용 구간 내 1회 수집)
    cse_tag_cache: dict[str, str] = {}  # 태그스캔 expr -> _tselN (태그/멤버 변동 전까지 배리어 관통)
    scr_cache: dict[tuple, str] = {}    # (holder,obj) -> _svN (그 키를 쓰기 전까지 재사용)
    cse_tag_tags: dict[str, tuple] = {}  # _tselN 이 의존하는 (pos, neg) 태그집합
    reused_sets = _find_reused_set_exprs(emitted)  # 배리어 없는 구간 2회+ 만 hoist
    reused_tags = _find_reused_tag_exprs(emitted) if ENTITY_TAG_CSE else set()
    reused_scores = _find_reused_score_reads(emitted) if SCORE_READ_CSE else set()
    # [수정] 세그먼트 분할이 CSE 주입 선언(_svN/_selN/_esetN/_tselN)을 예산에 넣지 않던 결함 교정.
    # 종전엔 do_split 을 CSE '이전' 크기(_total_code)로만 판정하고, 경계도 _seg_bounds_for(
    # emitted)(역시 CSE 이전)로 잡았다. 그 뒤 루프가 decls 를 본문에 주입하므로 그 바이트가
    # 예산 밖이 되어 조각이 _SEG_TARGET 을 넘고, 분할이 지키려던 JIT HugeMethodLimit(8KB) 근처로
    # 메서드가 부풀었다. 이제 (a) 트리거는 고유 hoist 대상 수만큼 크기를 보정하고, (b) 경계는
    # 아래 루프에서 decls 를 포함한 실제 누적 코드량(seg_acc)이 목표를 넘을 때 동적으로 넣는다.
    _cse_infl = (len(reused_scores) + len(reused_sets) + len(reused_tags)) * 48
    do_split = (not _will_fallback) and (not tco) and (not _is_traced(fid)) \
               and (_total_code + _cse_infl) > _SEG_TRIGGER
    seg_acc = 0   # 현재 조각의 실제 렌더 코드량(주석 제외, decls 포함) 누적
    scr_seq = [0]   # _sv 전용 카운터
    cse_seq = [0]
    cse_set_seq = [0]   # _eset 전용 카운터(기존 _sel 번호 불변 → 순수 가산적)
    cse_tag_seq = [0]   # _tsel 전용 카운터
    hit_terminal = False
    for _gi, (obj, em) in enumerate(zip(parse_trees, emitted)):
        if (do_split and not hit_terminal and seg_acc > 0
                and seg_acc + _code_chars(em.java) > _SEG_TARGET):
            body_lines.append(_SEG_MARK)        # 조각 경계 마커(렌더에서 분할 후 제거)
            cse_cache.clear()                   # CSE 를 조각-로컬로 격리
            cse_set_cache.clear()
            cse_tag_cache.clear(); cse_tag_tags.clear()  # _tsel 은 세그먼트 지역변수 → 경계 못 넘음
            scr_cache.clear()                            # _sv 도 세그먼트 지역변수
            seg_acc = 0
        # 무조건 top-level return 이후의 줄은 바닐라에서도 실행되지 않는다(함수 즉시 종료).
        # → 도달불가 코드를 생성하면 javac "unreachable statement" 컴파일 에러. 주석으로만 보존.
        if hit_terminal:
            body_lines.append(f'// [도달불가 - 앞선 return 으로 함수 종료] {obj["line"]}')
            continue

        if em.kind == "native":
            n_native += 1
        elif em.kind == "gated":
            n_gated += 1
        else:
            n_bridge += 1

        barrier = _cse_is_barrier(em)
        decls: list[str] = []
        if barrier:
            new_java = em.java
        else:
            new_java = []
            for line in em.java:
                if line.lstrip().startswith("//"):
                    new_java.append(line)
                    continue

                def _repl(m):
                    e = m.group(0)
                    if not _cse_scan_eligible(e):
                        return e
                    v = cse_cache.get(e)
                    if v is None:
                        v = f"_sel{cse_seq[0]}"
                        cse_seq[0] += 1
                        cse_cache[e] = v
                        decls.append(f"net.minecraft.entity.Entity {v} = {e};")
                    return v

                def _repl_set(m):
                    e = m.group(0)
                    if e not in reused_sets or not _cse_scan_eligible(e):
                        return e
                    v = cse_set_cache.get(e)
                    if v is None:
                        v = f"_eset{cse_set_seq[0]}"
                        cse_set_seq[0] += 1
                        cse_set_cache[e] = v
                        decls.append(
                            f"java.util.List<net.minecraft.entity.Entity> {v} = {e};")
                    return v

                line = _CSE_SCAN_RE.sub(_repl, line)     # 단일 엔티티 스캔(기존)
                line = _CSE_SET_RE.sub(_repl_set, line)  # 전체-집합 반복(신규, 재사용만)
                new_java.append(line)

        # 태그 스캔 CSE: barrier 여부와 무관하게 항상 적용(무관 tag추가/setScore 배리어 관통이 목적).
        # 무효화는 아래 _cse_tags_mutated 로 정밀 처리(해당 태그 변형·멤버변동·함수호출에서만).
        tag_mut = _cse_tags_mutated(em) if ENTITY_TAG_CSE else None
        if ENTITY_TAG_CSE and reused_tags:
            def _repl_tag(m):
                e = m.group(0)
                if e not in reused_tags:
                    return e
                el = _cse_tag_eligible(e)
                if el is None:
                    return e
                k = _cse_tag_key(e)          # origin-무관 스캔은 origin 을 _pos 로 정규화한 키
                v = cse_tag_cache.get(k)
                if v is None:
                    v = f"_tsel{cse_tag_seq[0]}"
                    cse_tag_seq[0] += 1
                    cse_tag_cache[k] = v
                    cse_tag_tags[k] = el
                    decls.append(f"net.minecraft.entity.Entity {v} = {k};")  # 정규화 식으로 1회 평가
                return v
            _nj2 = []
            for line in new_java:
                if line.lstrip().startswith("//"):
                    _nj2.append(line); continue
                _nj2.append(_CSE_TAG_RE.sub(_repl_tag, line))
            new_java = _nj2

        # 스코어 read CSE: 리터럴 (holder,obj) 반복 읽기를 _svN(Integer·nullable) 1회 읽기로.
        # 무효화는 아래 _score_effects 결과로 정밀 처리(그 키/objective 를 쓰는 지점에서만).
        scr_eff = _score_effects(em) if SCORE_READ_CSE else None
        if SCORE_READ_CSE and reused_scores:
            # [정확성] 같은 명령(em)이 그 키를 '쓰는' 경우 그 키의 읽기는 호이스트하지 않는다.
            # 이유: `execute store result ... run scoreboard players add/set/remove/operation`
            # 은 쓰기 '이후'(결과) 값을 저장한다 — 이때 읽기는 addScore/opScore/setScore 뒤에
            # 방출된다. 그런데 _svN 선언(decls)은 em 본문보다 '먼저' 놓이므로, 호이스트하면
            # 쓰기 이전 값으로 collapse 되어 결과가 (더한 양만큼) 틀어진다(실측: store-result-add).
            # → 이 em 이 건드리는 키(또는 미지 쓰기·objective 단위 쓰기)의 읽기는 원본 인라인
            #   (getScore/scoreMatches, 라이브 읽기)으로 남겨 방출 순서상 올바른 값을 읽게 한다.
            #   em 이 안 쓰는 키는 값이 em 전 구간 불변이므로 기존대로 안전하게 병합한다.
            def _scr_key_written(k):
                if scr_eff is None:
                    return True                          # 미지 쓰기 = 이 em 전부 라이브
                return (k in scr_eff) or (("OBJ", k[1]) in scr_eff) or (("HLD", k[0]) in scr_eff)
            def _scr_var(k):
                v = scr_cache.get(k)
                if v is None:
                    v = f"_sv{scr_seq[0]}"
                    scr_seq[0] += 1
                    scr_cache[k] = v
                    if k[0] == "@EXEC":
                        # 실행자 홀더: source.getEntity()(메서드 내 안정) 로 1회 nullable 읽기.
                        decls.append(f"Integer {v} = KfcGen.readScoreEnt(sb, source.getEntity(), {k[1]});")
                    else:
                        decls.append(f"Integer {v} = KfcGen.readScore(sb, {k[0]}, {k[1]});")
                return v
            def _repl_sm(m):
                k = (m.group(1), m.group(2))
                if k not in reused_scores or _scr_key_written(k):
                    return m.group(0)
                v = _scr_var(k)
                # scoreMatches 시맨틱 복제: 미설정(null)=false, 이후 [min,max] 검사(센티널 동일)
                return f"({v} != null && {v} >= {m.group(3)} && {v} <= {m.group(4)})"
            def _repl_sg(m):
                k = (m.group(1), m.group(2))
                if k not in reused_scores or _scr_key_written(k):
                    return m.group(0)
                v = _scr_var(k)
                return f"({v} == null ? 0 : {v})"   # getScore(readOrZero) 시맨틱 복제
            # 실행자-홀더 읽기 병합: nameOf(X) 의 X 가 이 em 의 source.getEntity() 별칭일 때만.
            _exec_aliases = _exec_alias_vars("\n".join(new_java))
            def _repl_sm_ent(m):
                var = m.group(1) or m.group(2)
                if var not in _exec_aliases:
                    return m.group(0)
                k = ("@EXEC", m.group(3))
                if k not in reused_scores or _scr_key_written(k):
                    return m.group(0)
                v = _scr_var(k)
                return f"({v} != null && {v} >= {m.group(4)} && {v} <= {m.group(5)})"
            def _repl_sg_ent(m):
                var = m.group(1) or m.group(2)
                if var not in _exec_aliases:
                    return m.group(0)
                k = ("@EXEC", m.group(3))
                if k not in reused_scores or _scr_key_written(k):
                    return m.group(0)
                v = _scr_var(k)
                return f"({v} == null ? 0 : {v})"
            _nj3 = []
            for line in new_java:
                if line.lstrip().startswith("//"):
                    _nj3.append(line); continue
                line = _SCR_MATCH_RE.sub(_repl_sm, line)
                line = _SCR_GET_RE.sub(_repl_sg, line)
                line = _SCR_MATCH_ENT_RE.sub(_repl_sm_ent, line)
                line = _SCR_GET_ENT_RE.sub(_repl_sg_ent, line)
                _nj3.append(line)
            new_java = _nj3

        body_lines.append(f'// {obj["line"]}')
        body_lines.extend(decls)      # 스캔 1회 평가(메서드 본문 레벨, 사용 직전)
        body_lines.extend(new_java)
        body_lines.append("")
        seg_acc += _code_chars(decls) + _code_chars(new_java)   # decls 포함 실제 누적

        if barrier:
            cse_cache.clear()         # 변형 후 base-source nearest 캐시 무효화
            cse_set_cache.clear()
        # 태그 캐시 정밀 무효화: 이 명령이 변형한 태그/위치(tag_mut)에 의존하는 바인딩만 제거.
        # None = 미지 callee/미지헬퍼/멤버변동 → 전량 무효화.
        # (tags, move): 변형 태그 의존 + (move 시) origin-의존 스캔 제거.
        # (_find_reused_tag_exprs 와 동일 판정 — 두 곳은 반드시 같은 규칙이어야 한다.)
        if ENTITY_TAG_CSE and cse_tag_cache:
            if tag_mut is None:
                cse_tag_cache.clear(); cse_tag_tags.clear()
            else:
                mtags, mmove = tag_mut
                if mtags or mmove:
                    for e in list(cse_tag_cache):
                        el = cse_tag_tags.get(e, (frozenset(), frozenset(), False))
                        if (el[0] & mtags) or (el[1] & mtags) or (mmove and not el[2]):
                            cse_tag_cache.pop(e, None); cse_tag_tags.pop(e, None)
        # 스코어 캐시 무효화(치환 뒤 적용 — 같은 줄의 읽기는 쓰기 '이전' 값이 맞다: 조건이 run 보다 먼저)
        if SCORE_READ_CSE and scr_cache:
            if scr_eff is None:
                scr_cache.clear()
            else:
                _scr_apply_inv(scr_cache, scr_eff)
                if _reassigns_source("\n".join(em.java)):   # 실행자 변경 → @EXEC 무효화(배리어)
                    for _k in [k for k in scr_cache if k[0] == "@EXEC"]:
                        scr_cache.pop(_k, None)

        if em.terminal:
            hit_terminal = True       # 이후 줄은 도달불가 → 주석 처리

    if rejected:
        # 원본 mcfunction 과 동일하게 함수 전체 비활성. 원래 본문은 주석으로 보존(디버깅).
        commented = "\n".join("        // " + l for l in "\n".join(body_lines).rstrip().split("\n"))
        body = (f'// [!] 이 함수는 무효 명령을 포함해 원본 데이터팩에서 로드 거부됩니다.\n'
                f'        //    ({reject_reason})\n'
                f'        //    원본과 동일하게 아무 동작도 하지 않습니다. 아래는 참고용 주석:\n'
                f'{commented}')
        n_native = n_gated = 0
        n_bridge = len(parse_trees)
        fully_converted = False
    elif (is_self_recursive and not tco) or _is_force_bridged(fid) or any(em.kind in ("bridge", "dispatch") for em in emitted):
        # ── 함수 단위 폴백 ──  (강제 브릿지 prefix 매칭 시에도 이 경로)
        # 정책: instantExecuteCommand(자바->바닐라 디스패처)는 최적화 이득이 없어 최종 산출에서
        # 금지. 그런 줄(dispatch)이 하나라도 있으면 함수 전체를 원본 mcfunction 실행으로 폴백한다.
        # (native + gated 는 둘 다 순수 자바 - 완전 변환으로 인정.)
        # (원본은 resources/data 에 복사돼 있어 함수 매니저가 로드한다.)
        has_macro_line = any(o.get("macro") for o in parse_trees)
        macro_arg_expr = ", macroArgs" if has_macro_line else ""
        ns_id, path_id = fid.split(":", 1)
        commented = "\n".join("        // " + l for l in "\n".join(body_lines).rstrip().split("\n"))
        n_br = sum(1 for em in emitted if em.kind in ("bridge", "dispatch"))
        tag = ("자기재귀 → 브릿지(스택오버플로 방지)" if is_self_recursive
               else "강제 브릿지(디버깅)" if _is_force_bridged(fid)
               else f"부분 변환(디스패처 의존 {n_br}/{len(parse_trees)}줄)")
        if has_macro_line:
            # 매크로 함수: void 시그니처 - 반환 전파 없이 실행만.
            body = (f'// {tag} - 함수 단위 폴백.\n'
                    f'        KfcGen.instantExecuteFunction(source, '
                    f'net.minecraft.util.Identifier.of("{ns_id}", "{path_id}"){macro_arg_expr});\n'
                    f'        // 아래는 줄 단위 변환 시도 결과(참고용 주석):\n'
                    f'{commented}')
        else:
            # 비매크로: 원본 mcfunction 의 return 값을 executeReturn 으로 전파해야
            # 네이티브 호출자의 `if function` / `store result ... run function` 이 올바로 동작한다.
            body = (f'// {tag} - 함수 단위 폴백 (반환값 전파).\n'
                    f'        int kfcBridgeRet = KfcGen.instantExecuteFunctionReturn(source, '
                    f'net.minecraft.util.Identifier.of("{ns_id}", "{path_id}"));\n'
                    f'        // 아래는 줄 단위 변환 시도 결과(참고용 주석):\n'
                    f'{commented}\n'
                    f'        return kfcBridgeRet;')
        n_native = n_gated = 0
        n_bridge = len(parse_trees)
        fully_converted = False
    else:
        body = "\n".join(body_lines).rstrip()
        if tco:
            # 꼬리 self-call 을 `source = ARG; continue;` 로 치환(루프 본문).
            body = _tco_rewrite(body, _self_fqcn)
        fully_converted = True

    if _is_traced(fid):
        body = f'KfcGen.trace("{fid}", source);\n' + body

    # ── peephole 최적화: 틱 내 불변값 캐싱 ──
    # executor(=source.getEntity(), 한 번 대입·불변)와 source(메서드 파라미터·불변, ServerCommandSource
    # 는 immutable)에서 반복 추출하는 값을 1회만 계산해 재사용한다. 핫패스(per-entity per-tick)에서
    # executor.getNameForScoreboard() 는 비-플레이어의 경우 매번 UUID.toString() 을 할당하므로
    # 함수당 수십 회 → 1 회로 줄이면 할당/CPU 가 크게 준다. 루프-로컬 엔티티(_onEntN/_pp 등)는
    # 'executor.' 가 아니므로 치환되지 않아 고증 안전(반복마다 바뀌는 값은 캐시하지 않음).
    cache_exname = body.count("executor.getNameForScoreboard()") >= 2
    if cache_exname:
        body = body.replace("executor.getNameForScoreboard()", "_exName")
    cache_pos = body.count("source.getPosition()") >= 2
    if cache_pos:
        body = body.replace("source.getPosition()", "_pos")
    # 루프-로컬 엔티티 등 잔여 getNameForScoreboard(): 비-플레이어는 호출마다 UUID 문자열을
    # 새로 만든다(핫패스 할당 — 핫코어 실측 3,574 호출부). 이름은 엔티티 수명 동안 불변이므로
    # KfcGen.nameOf(identity 캐시) 경유로 바꾼다. 수신자가 단순 변수인 경우만 치환(체인식은
    # 그대로), _exName 치환 뒤 잔여가 대상이라 executor 캐시 로직과 충돌하지 않는다.
    body = re.sub(r'\b([A-Za-z_]\w*)\.getNameForScoreboard\(\)', r'KfcGen.nameOf(\1)', body)

    # ── 엔티티 홀더 점수 매칭의 이름 경유 제거 ──
    # scoreMatches(sb, nameOf(e), o, lo, hi) 는 nameOf(WeakHashMap 조회/최초 UUID 문자열화)
    # + holderOf(HashMap 조회) 를 매 호출 태운다. sb.getScore(e, ob) 는 내부적으로
    # e.getNameForScoreboard() 를 키로 쓰므로 scoreMatchesEntity(sb, e, o, lo, hi) 와 값 동치
    # (KfcGen 두 구현 모두 미설정=false, null 엔티티=false). primitive(int,int) 인자 확정
    # 호출만 치환(Integer/null 오버로드 오매치 방지 — 경계는 정수 리터럴/MIN/MAX 센티넬).
    # 반드시 read-CSE 이후(여기)에 적용: CSE 의 _SCR_MATCH_ENT_RE 는 scoreMatches 형태를 본다.
    body = re.sub(
        r'KfcGen\.scoreMatches\(sb, KfcGen\.nameOf\((\w+)\), ("(?:[^"\\]|\\.)*"), '
        r'(-?\d+|Integer\.MIN_VALUE|Integer\.MAX_VALUE), '
        r'(-?\d+|Integer\.MIN_VALUE|Integer\.MAX_VALUE)\)',
        r'KfcGen.scoreMatchesEntity(sb, \1, \2, \3, \4)', body)

    # 필요한 prelude/import 결정 (본문 토큰 스캔; 주석 줄은 제외)
    scan_src = "\n".join(l for l in body.split("\n") if not l.lstrip().startswith("//"))
    used = set(re.findall(r'\b[A-Za-z_]\w*\b', scan_src))
    imports = set(base_imports(group))

    prelude_stmts = []
    # executor 는 거의 항상 필요(태그/스코어 @s/data). ctx/sb 는 쓰일 때만.
    need_executor = "executor" in used or cache_exname  # _exName 선언이 executor 를 참조
    need_ctx = "ctx" in used
    need_sb = "sb" in used
    need_server = "server" in used
    if need_sb:
        need_ctx = True
    if need_executor:
        d, imps = PRELUDE["executor"]; prelude_stmts.append(d); imports.update(imps)
    if cache_exname:
        prelude_stmts.append("String _exName = executor == null ? null : executor.getNameForScoreboard();")
    if cache_pos:
        prelude_stmts.append("net.minecraft.util.math.Vec3d _pos = source.getPosition();")
    if need_ctx:
        d, imps = PRELUDE["ctx"]; prelude_stmts.append(d); imports.update(imps)
    if need_sb:
        d, imps = PRELUDE["sb"]; prelude_stmts.append(d); imports.update(imps)
    if need_server:
        d, imps = PRELUDE["server"]; prelude_stmts.append(d); imports.update(imps)

    for sym, imp in SYMBOL_IMPORTS.items():
        if re.search(rf'\b{sym}\b', scan_src):
            imports.add(imp)

    # 매크로 변수 수집 (함수 시그니처 결정)
    macro_params = []
    for em in emitted:
        for p in em.macro_params:
            if p not in macro_params:
                macro_params.append(p)
    # 매크로 줄을 하나라도 포함하면 매크로 함수(호출부가 Map 인자를 넘기므로 시그니처 일치 필수).
    # 변수가 출력에 안 쓰여 macro_params 가 비어도 시그니처에는 macroArgs 파라미터를 둔다.
    has_macro_line = any(o.get("macro") for o in parse_trees)
    is_macro_fn = bool(macro_params) or has_macro_line

    # import 정렬: 프로젝트 것 먼저, 마크 것 다음
    proj = sorted(i for i in imports if i.startswith(group))
    mc = sorted(i for i in imports if not i.startswith(group))
    if is_macro_fn:
        mc = sorted(set(mc) | {"java.util.Map"})
    import_block = "\n".join(f"import {i};" for i in proj + mc)

    # ── 세그먼트 렌더(분할 발동 시): 마커로 조각화 → 앞 조각들은 Integer 반환 메서드,
    #    마지막 조각은 본체 인라인(기존 tail_return 흐름분석 그대로 적용). ──
    seg_methods = ""
    seg_calls = ""
    if do_split and fully_converted and _SEG_MARK in body:
        parts = [p.strip("\n") for p in body.split(_SEG_MARK)]
        macro_sig = ", Map<String, String> macroArgs" if is_macro_fn else ""
        macro_pass = ", macroArgs" if is_macro_fn else ""
        rendered = []
        calls = []
        for k, part in enumerate(parts[:-1]):
            sig_k, pass_k = _seg_params(part)
            ind = "\n".join("        " + l if l else "" for l in part.split("\n"))
            rendered.append(
                f"    private static Integer executeReturn_seg{k}(ServerCommandSource source{macro_sig}{sig_k}) {{\n"
                f"{ind}\n"
                f"        return null;   // 정상 완료(반환 없음) — 본체가 다음 조각으로 진행\n"
                f"    }}")
            calls.append(f"{{ Integer _sr = executeReturn_seg{k}(source{macro_pass}{pass_k}); "
                         f"if (_sr != null) return _sr; }}")
        seg_methods = "\n\n" + "\n\n".join(rendered)
        seg_calls = "\n        ".join(calls) + "\n        "
        body = parts[-1]              # 본체 인라인부(마지막 조각)만 남긴다
    elif _SEG_MARK in body:
        body = body.replace(_SEG_MARK + "\n", "").replace(_SEG_MARK, "")

    prelude = "\n        ".join(prelude_stmts)
    indented_body = "\n".join("        " + l if l else "" for l in body.split("\n"))
    tail_return = _tail_return_str(indented_body)   # 흐름분석 일원화: 매크로/비-TCO/TCO 공통
    indented_body_stripped = indented_body.lstrip()   # seg_calls 연결 시 첫 줄 이중 들여쓰기 방지

    macro_note = f"\n *  매크로 함수 - 변수: {', '.join(macro_params)}." if is_macro_fn else ""

    if is_macro_fn:
        # int executeReturn(source, macroArgs) 가 본문(return 값 전파), void execute 는 래퍼.
        # → `store result score X run function <macro> with ...` 가 반환값을 캡처 가능.
        # 본문 전체 try: 바닐라 Macro$VariableLine.instantiate 는 치환 라인 파싱 실패 시
        # MacroException 으로 함수 호출 '전체'를 실패시킨다(어떤 줄도 미실행 — jar 확인).
        # 런타임 매크로 파싱 지점(coord/clearItemsPred/tpMacroDest/수치 래퍼)이 던지는
        # KfcGen.MACRO_FAIL 을 여기서 잡아 즉시 return 0 으로 재현한다.
        # (한계: 실패 줄 이전의 부수효과는 이미 실행 — 이 팩의 실제 실패 함수는
        #  detect-exist-item 뿐이고 실패 줄이 첫 줄이라 바닐라와 완전 동등.)
        methods = f"""    public static void execute(ServerCommandSource source, Map<String, String> macroArgs) {{
        executeReturn(source, macroArgs);
    }}

    public static int executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {{
        try {{
        {prelude}
        {seg_calls}{indented_body_stripped}{tail_return}
        }} catch (KfcGen.MacroParseFail _mf) {{
            return 0;   // 바닐라: 매크로 인스턴스화 실패 = 함수 전체 미실행
        }}
    }}"""
    else:
        # 일반 함수: int executeReturn(source) 가 실제 본문(return 값 전파),
        #            void execute(source) 는 그 결과를 버리는 래퍼.
        if tco:
            # 꼬리재귀 → 루프. prelude(executor/_exName/_pos 등)를 루프 내부에 두어
            # source 가 재바인드될 때마다 재계산한다. 꼬리 self-call 은 본문에서 이미
            # `source = ARG; continue;` 로 치환됨.
            # trailing `return 0;` 은 본문이 정상 완료(fall-through) 가능할 때만 붙인다.
            # 마지막 obj 가 무조건 블록(`execute positioned ^ ^ ^X run function self`
            # → `{ source=ARG; continue; }`)이면 그 뒤 return 은 unreachable(컴파일 에러)
            # 이므로 흐름분석(_completes_normally)으로 생략한다. while(true) 는 break 없이
            # 종료하지 않으므로 return 생략해도 'missing return' 이 아니다.
            methods = f"""    public static void execute(ServerCommandSource source) {{
        executeReturn(source);
    }}

    public static int executeReturn(ServerCommandSource source) {{
        int _tcoSteps = 0;
        while (true) {{
        // 바닐라 maxCommandChainLength(기본 65536) 컷오프 모방 — 종료 조건이 어긋난
        // 비정상 재귀에서 서버가 무한루프로 멈추는 것을 막는다(정상 재귀는 본문에서
        // 종료 점수가 감소해 그 전에 자연 종료하므로 이 가드에 도달하지 않는다).
        if (++_tcoSteps > 65536) return 0;
        {prelude}
{indented_body}{tail_return}
        }}
    }}"""
        else:
            methods = f"""    public static void execute(ServerCommandSource source) {{
        executeReturn(source);
    }}

    public static int executeReturn(ServerCommandSource source) {{
        {prelude}
        {seg_calls}{indented_body_stripped}{tail_return}
    }}"""

    code = f"""package {package};

{import_block}

/** Auto-generated from datapack function `{fid}`.
 *  native {n_native} / gated {n_gated} / bridge {n_bridge}.{macro_note} */
public final class {cls} {{
    private {cls}() {{ throw new UnsupportedOperationException(); }}

{methods}{seg_methods}
}}
"""
    jc = JavaClass(fid, package, cls, code, n_native, n_gated, n_bridge)
    jc.is_macro = is_macro_fn
    jc.macro_params = macro_params
    jc.fully_converted = fully_converted
    return jc


if __name__ == "__main__":
    import json, sys
    from pathlib import Path
    # 데모: 픽스처 전체를 한 가상 함수 'demo:sample' 로 묶어 클래스 생성
    import emit
    dp = "/home/claude/project/kartriderpack"
    if Path(dp).exists():
        emit.load_entity_type_tags(dp)
    trees = json.loads(Path(sys.argv[1] if len(sys.argv) > 1
                       else Path(__file__).parent / "fixtures" / "trees_sample.json").read_text())
    jc = function_to_class("kartmobil:demo/sample", trees)
    print(jc.code)
    print(f"\n// === native {jc.native_lines} / gated {jc.gated_lines} / bridge {jc.bridge_lines} ===")