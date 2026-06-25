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
from emit import emit_line, fqcn, sanitize, pascal, Emitted, set_group


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
    "getOrCreateContext", "scoreMatches", "scoreCmp", "entityScoreMatches", "getScore",
    "getScoreOfEntity", "inRange", "itemsMatchSlots", "blockMatches", "nbtMatches",
    "posInRange", "posInBox", "posLoaded", "blockInTag", "entityInTypeTag", "entityHasPath",
    "storageHasPath", "hasEffect", "gamemodeIs", "testPredicate", "nbtGetStorage",
    "storageGetDouble", "nbtGetEntity", "entityGetDouble", "nbtGetBlock",
    # 스캔/읽기 셀렉터(변형 없음)
    "nearestEntityAnyType", "nearestEntity", "nearestPlayer", "nearestN", "nearestPlayerWhere",
    "allEntitiesAnyType", "allEntities", "allEntitiesAny", "allEntitiesInBox",
    "anyPlayer", "anyPlayerWhere", "anyEntity", "anyEntityAnyType", "anyEntityInBox",
    "facingEntity", "localOffset", "anchorEyes",
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


def _cse_is_barrier(em) -> bool:
    """이 명령이 (단순형 nearest 캐시 관점에서) 엔티티 집합/위치/커맨드태그를 바꿀 수 있으면 True.
       확실히 read-only 가 아니면 보수적으로 True."""
    if em.kind != "native":
        return True  # 폴백/브릿지/디스패치 = 미지 → 배리어
    code = "\n".join(l for l in em.java if not l.lstrip().startswith("//"))
    if ".execute(" in code or ".executeReturn(" in code:
        return True  # 다른 함수 호출(execute / executeReturn) = 미지의 부작용
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


def _compute_tail_return(indented_body: str) -> str:
    """executeReturn 끝의 `return 0;` 이 unreachable(컴파일 에러) 이 되지 않도록,
       본문 마지막 비공백 줄이 무조건 return 또는 'return 으로 끝나는 무조건 블록 닫기'면
       trailing return 을 생략한다. 일반/매크로 함수 공통."""
    body_lines_stripped = [l for l in indented_body.split("\n")
                           if l.strip() and not l.lstrip().startswith("//")]
    last = body_lines_stripped[-1].strip() if body_lines_stripped else ""
    omit = bool(re.match(r'^return\b.*;$', last))
    if not omit and last == "}":
        raw = indented_body.split("\n")
        li = max(i for i, l in enumerate(raw) if l.strip())
        if re.match(r'^\s{8}\}$', raw[li]):
            depth = 0
            for j in range(li, -1, -1):
                s = raw[j].rstrip()
                depth += s.count("}") - s.count("{")
                if depth == 0:
                    if raw[j].strip() == "{":
                        inner_last = body_lines_stripped[-2].strip() if len(body_lines_stripped) >= 2 else ""
                        if re.match(r'^return\b.*;$', inner_last):
                            omit = True
                    break
    return "" if omit else "\n        return 0;"


def function_to_class(fid: str, parse_trees: list[dict], group: str = "kartriderpack") -> JavaClass:
    """한 함수의 파스트리 줄들 -> 자바 클래스 코드."""
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
    # 함수 단위 파싱 거부: 한 줄이라도 무효 명령이면 mcfunction 은 함수 전체를 로드 거부.
    for obj, em in zip(parse_trees, emitted):
        if em.rejects_function:
            rejected = True
            reject_reason = em.reason
            break

    cse_cache: dict[str, str] = {}   # scan_expr -> _selN  (현재 유효한 바인딩)
    cse_set_cache: dict[str, str] = {}  # full-set expr -> _esetN (재사용 구간 내 1회 수집)
    reused_sets = _find_reused_set_exprs(emitted)  # 배리어 없는 구간 2회+ 만 hoist
    cse_seq = [0]
    cse_set_seq = [0]   # _eset 전용 카운터(기존 _sel 번호 불변 → 순수 가산적)
    hit_terminal = False
    for obj, em in zip(parse_trees, emitted):
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

        body_lines.append(f'// {obj["line"]}')
        body_lines.extend(decls)      # 스캔 1회 평가(메서드 본문 레벨, 사용 직전)
        body_lines.extend(new_java)
        body_lines.append("")

        if barrier:
            cse_cache.clear()         # 변형 후 모든 캐시 무효화
            cse_set_cache.clear()

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
    elif is_self_recursive or _is_force_bridged(fid) or any(em.kind in ("bridge", "dispatch") for em in emitted):
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

    prelude = "\n        ".join(prelude_stmts)
    indented_body = "\n".join("        " + l if l else "" for l in body.split("\n"))

    macro_note = f"\n *  매크로 함수 - 변수: {', '.join(macro_params)}." if is_macro_fn else ""

    if is_macro_fn:
        # int executeReturn(source, macroArgs) 가 본문(return 값 전파), void execute 는 래퍼.
        # → `store result score X run function <macro> with ...` 가 반환값을 캡처 가능.
        tail_return = _compute_tail_return(indented_body)
        methods = f"""    public static void execute(ServerCommandSource source, Map<String, String> macroArgs) {{
        executeReturn(source, macroArgs);
    }}

    public static int executeReturn(ServerCommandSource source, Map<String, String> macroArgs) {{
        {prelude}
{indented_body}{tail_return}
    }}"""
    else:
        # 일반 함수: int executeReturn(source) 가 실제 본문(return 값 전파),
        #            void execute(source) 는 그 결과를 버리는 래퍼.
        tail_return = _compute_tail_return(indented_body)
        methods = f"""    public static void execute(ServerCommandSource source) {{
        executeReturn(source);
    }}

    public static int executeReturn(ServerCommandSource source) {{
        {prelude}
{indented_body}{tail_return}
    }}"""

    code = f"""package {package};

{import_block}

/** Auto-generated from datapack function `{fid}`.
 *  native {n_native} / gated {n_gated} / bridge {n_bridge}.{macro_note} */
public final class {cls} {{
    private {cls}() {{ throw new UnsupportedOperationException(); }}

{methods}
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