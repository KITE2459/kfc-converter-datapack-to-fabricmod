#!/usr/bin/env python3
"""
convert.py - 데이터팩 -> 네이티브 Fabric 모드 소스 오케스트레이터.

통합 파이프라인 (build = extract -> parse -> generate 일괄):
  [1] extract : 데이터팩(디렉터리 또는 .zip) -> 함수ID별 줄 수집 -> lines.json (맵)
  [2] parse   : 헤드리스 파서(HeadlessParser, MC 런타임을 게임 부팅 없이 초기화)로
                lines.json -> trees.json. 게임 부팅(runServer) 불필요 - JVM 한 번 호출.
  [3] generate: trees.json -> 함수별 자바 클래스 + 엔트리포인트 + 리소스 + 리포트.

사용:
  # 통합(권장): 한 번에 데이터팩 -> 자바 소스
  python convert.py build <datapack(.zip|dir)> <out_src_dir>
         [--group <name>] [--profile <name> | --profile-file <json>]
         [--parser-classes <dir|jar>] [--project <loom_dir>] [--parser-cp <paths...>]

  # 개별 단계(디버깅/수동 파싱용)
  python convert.py extract  <datapack(.zip|dir)> <out_lines.json>
  python convert.py generate <trees.json> <datapack(.zip|dir)> <out_src_dir> [--group <name>]

버전 변수(마인크래프트/Fabric)는 build_config.py 의 BuildProfile 로 격리한다.
현재 기본 프로파일: MC 1.21.5 / Fabric Loader 0.18.4. 새 버전 지원 시 프로파일만 추가.
"""
from __future__ import annotations
import json, re, sys, collections
from pathlib import Path

# Windows 콘솔(cp949 등)에서 한글-기호(- -> OK) 출력 시 UnicodeEncodeError 가 나는 것을 방지.
# stdout/stderr 를 UTF-8 로 재설정한다(Python 3.7+). 실패해도 무해하게 통과.
for _stream in (sys.stdout, sys.stderr):
    try:
        _stream.reconfigure(encoding="utf-8", errors="replace")
    except Exception:
        pass

import emit
from datapack_io import open_datapack
from assemble import function_to_class, JavaClass, audit_executeReturn


def sanitize_pkg(name: str) -> str:
    """데이터팩 이름 -> 유효한 자바 패키지 그룹명. 공백/특수문자 등 위반은 생략(제거)하고 소문자화."""
    s = re.sub(r'[^A-Za-z0-9]', '', name)   # 위반 문자 생략
    if not s:
        s = "datapack"
    if s[0].isdigit():
        s = "p" + s
    return s.lower()


# ───────────────────────── [1] extract ─────────────────────────
def extract(datapack_root: str, out_path: str):
    """data/<ns>/function/**/*.mcfunction -> {함수ID: [명령줄...]} 맵.
       datapack_root 는 디렉터리 또는 .zip (메모리에서 직접 읽음)."""
    src = open_datapack(datapack_root)
    fn_files = src.glob("data/*/function/**/*.mcfunction")
    if not fn_files:
        # 1.20 이하 호환: functions/ (복수형)
        fn_files = src.glob("data/*/functions/**/*.mcfunction")

    # [!] 대용량(100MB+ zip) 대응: 전체 fmap 을 메모리에 모은 뒤 json.dumps 로 한 번에
    #    문자열화하면 OOM 위험(파이썬 측). 함수별로 즉시 JSON 을 흘려 쓴다(스트리밍).
    #    출력 형식은 기존과 동일: { "ns:path": ["line", ...], ... }
    import json as _json
    n_fn = 0
    n_lines = 0
    with open(out_path, "w", encoding="utf-8") as fo:
        fo.write("{\n")
        first = True
        for rel_path in fn_files:
            parts = rel_path.split("/")
            di = parts.index("data")
            ns = parts[di + 1]
            # function 디렉토리 이후 상대경로
            try:
                fi = parts.index("function", di)
            except ValueError:
                fi = parts.index("functions", di)
            rel = "/".join(parts[fi + 1:]).removesuffix(".mcfunction")
            fid = f"{ns}:{rel}"

            lines = []
            _text = src.read_text(rel_path, encoding="utf-8", errors="replace").replace("\r", "")
            # 백슬래시 줄-연속(\) 합치기: 줄 끝 '\' 제거 후 다음 물리줄을 직접 이어붙임
            # (바닐라 mcfunction 1.20.2+ 규칙). 이걸 안 하면 lore=[[...]] 같은 조각이 별도 줄로 샌다.
            _physical = _text.split("\n")
            _joined = []
            _buf = None
            for _pl in _physical:
                _cur = _pl if _buf is None else _buf + _pl
                if _cur.endswith("\\") and not _cur.endswith("\\\\"):
                    _buf = _cur[:-1]
                    continue
                _joined.append(_cur)
                _buf = None
            if _buf is not None:
                _joined.append(_buf)
            for raw in _joined:
                s = raw.strip()
                if not s or s.startswith("#"):
                    continue
                lines.append(s)   # 매크로($) 줄도 그대로 (ParseDumper 가 macro 표시)

            if not first:
                fo.write(",\n")
            first = False
            fo.write(_json.dumps(fid, ensure_ascii=False))
            fo.write(": ")
            fo.write(_json.dumps(lines, ensure_ascii=False))
            n_fn += 1
            n_lines += len(lines)
        fo.write("\n}\n")

    print(f"[extract] {n_fn} functions, {n_lines} command lines -> {out_path}")
    # tick/load 태그도 같이 뽑아 옆에 저장 (generate 에서 엔트리포인트에 활용)
    tags = extract_tick_load(src)
    Path(out_path + ".tags.json").write_text(json.dumps(tags, ensure_ascii=False, indent=1))
    print(f"[extract] tick={len(tags.get('tick',[]))} load={len(tags.get('load',[]))} -> {out_path}.tags.json")


def extract_tick_load(src) -> dict:
    out = {"tick": [], "load": []}
    for kind in ("tick", "load"):
        for sub in ("function", "functions"):   # 1.21+ / 1.20 이하
            for rel in src.glob(f"data/minecraft/tags/{sub}/{kind}.json"):
                try:
                    data = json.loads(src.read_text(rel, encoding="utf-8"))
                    for v in data.get("values", []):
                        out[kind].append(v["id"] if isinstance(v, dict) else v)
                except Exception:
                    pass
    return out


# ───────────────────────── [2] generate ─────────────────────────
# ── 멀티프로세싱: 함수 변환을 워커 풀로 병렬화 ──
# function_to_class 는 함수별로 독립적이라 병렬화에 안전. 각 워커는 emit 의 전역 상태
# (block_tags/predicates/entity_tags + macro_fns + group)를 초기화 시 재현해야 한다.
# 큰 팩(188k 함수)에서 CPU 바운드인 변환을 코어 수만큼 가속한다.
_W_GROUP = None

def _mp_init(datapack_root, macro_fns, group, all_fids=(),
             force_bridge_prefixes=(), trace_prefixes=(),
             call_effects=None, known_fqcns=()):
    """각 워커 프로세스 1회 초기화: emit + assemble 전역 상태 복원.
       (spawn 워커는 메인의 set_force_bridge/set_trace 결과를 못 물려받으므로 여기서 재주입.)"""
    global _W_GROUP
    import emit as _emit
    _emit.load_entity_type_tags(datapack_root)
    _emit.load_block_tags(datapack_root)
    _emit.load_predicates(datapack_root)
    _emit.set_macro_fns(set(macro_fns))
    _emit.set_all_fids(set(all_fids))
    import assemble as _asm
    _asm.set_force_bridge(list(force_bridge_prefixes))      # --bridge <prefix> 강제 브릿지
    _asm.set_trace(list(trace_prefixes))                    # --trace <prefix>
    _asm.set_call_summaries(call_effects or {}, known_fqcns)  # 인터프로시저 태그 요약
    _W_GROUP = group

def _mp_convert(item):
    """워커: (fid, objs) -> (fid, package, cls, code, native, gated, bridge, fully).
    코드 문자열만 반환하고, 파일 쓰기는 메인이 한다(I/O 직렬화로 디스크 경합 방지)."""
    from assemble import function_to_class as _f2c
    fid, objs = item
    jc = _f2c(fid, objs, group=_W_GROUP)
    return (fid, jc.package, jc.cls, jc.code,
            jc.native_lines, jc.gated_lines, jc.bridge_lines, jc.fully_converted)


def _mp_convert_local(item, group):
    """단일 프로세스용: 워커 IPC 없이 직접 변환. (워커 _mp_convert 와 동일 반환)"""
    from assemble import function_to_class as _f2c
    fid, objs = item
    jc = _f2c(fid, objs, group=group)
    return (fid, jc.package, jc.cls, jc.code,
            jc.native_lines, jc.gated_lines, jc.bridge_lines, jc.fully_converted)


# ───────────────────────────── 대용량 가속(raw 워커) ─────────────────────────────
# 기존엔 메인 스레드가 trees(700MB+) 를 json.loads 로 2패스(pass-1 + pass-2 _gen_items)
# 했다 → 그 자체로 단일코어 ~수십초. 워커는 emit 만 병렬이고 파싱은 메인 직렬 병목.
# 개선: 메인은 raw 문자열만 함수 경계로 묶고(json.loads 안 함), 워커가 json.loads + emit 을
# 모두 수행 → 파싱까지 코어 수만큼 병렬화. (출력은 기존 경로와 바이트 동일.)

def write_if_changed(path, text: str, encoding="utf-8") -> bool:
    """내용이 기존 파일과 동일하면 기록하지 않는다(mtime 보존).
       emit 이 함수 단위 결정적 출력이므로, 데이터팩 일부만 바뀌는 반복 변환에서
       불변 버킷/리소스의 mtime 이 유지 → gradle 증분 컴파일이 그 컴파일을 스킵한다."""
    try:
        if path.exists() and path.read_text(encoding=encoding) == text:
            return False
    except OSError:
        pass
    path.write_text(text, encoding=encoding)
    return True


def _scan_fid(line: str):
    """JSONL 한 줄에서 function 값만 싸게 추출(json.loads 회피).
       형식: ...,"function":"ns:path"}  — fid 에는 따옴표/역슬래시가 없다."""
    i = line.rfind('"function":"')
    if i < 0:
        return None
    j = i + len('"function":"')
    k = line.find('"', j)
    return None if k < 0 else line[j:k]


def _scan_macro_and_fids(trees_path: str):
    """[pass-1 대체, JSONL 전용] json.loads 없이 raw 스캔으로 (macro_fns, all_fids) 수집.
       called(호출대상)는 pass-2 워커가 파싱하며 모은다(메인 단일스레드 절감)."""
    macro_fns, all_fids = set(), set()
    cur_fid, cur_macro = None, False
    with open(trees_path, encoding="utf-8") as f:
        for line in f:
            if not line.strip():
                continue
            fid = _scan_fid(line)
            if fid is None:
                continue
            all_fids.add(fid)
            if fid != cur_fid:
                if cur_fid is not None and cur_macro:
                    macro_fns.add(cur_fid)
                cur_fid, cur_macro = fid, False
            if not cur_macro and '"macro":true' in line:
                cur_macro = True
        if cur_fid is not None and cur_macro:
            macro_fns.add(cur_fid)
    return macro_fns, all_fids


def _gen_items_raw(trees_path: str):
    """[pass-2 입력, JSONL] (fid, [raw_json_line,...]) 스트리밍 — 메인은 json.loads 안 함."""
    cur_fid, cur_lines = None, []
    with open(trees_path, encoding="utf-8") as f:
        for line in f:
            s = line.strip()
            if not s:
                continue
            fid = _scan_fid(s)
            if fid is None:
                continue
            if fid != cur_fid:
                if cur_fid is not None:
                    yield (cur_fid, cur_lines)
                cur_fid, cur_lines = fid, []
            cur_lines.append(s)
        if cur_fid is not None:
            yield (cur_fid, cur_lines)


def _collect_called(objs):
    """파스트리에서 외부 호출 대상(ns:path) 집합 수집(pass-1 called 와 동일 기준)."""
    called = set()
    for obj in objs:
        for step in obj.get("chain", []):
            v = step.get("value", {})
            if isinstance(v, dict) and v.get("kind") == "" and ":" in str(v.get("raw", "")):
                called.add(v["raw"])
    return called


# ── [pass-1.5] 인터프로시저 태그 요약 ──────────────────────────────────────
# 각 함수가 (전이적으로) add/remove 할 수 있는 커맨드태그 집합과 엔티티 위치 변경 여부를
# '원본 mcfunction 소스'에서 계산한다. assemble 의 태그 스캔 CSE 가 함수 호출을 만났을 때
# 전량 무효화 대신 이 요약에 있는 태그만 무효화하게 한다(assemble.set_call_summaries).
# 소스 레벨이므로 native/bridge 어느 실행 경로에도 유효하다(emit 은 tag/kill/summon 명령
# 외에 태그·엔티티집합을 바꾸는 코드를 합성하지 않음). 판정은 fail-closed: 확신할 수 없는
# 줄은 그 함수를 ⊤(ALL=미지)로 만든다 — 매크로 $()(호출 시 텍스트 재조립이라 무엇이든 될
# 수 있음), 미지 callee(#함수태그/미존재), tag 명령 형태 인식 실패, 그리고 엔티티 생성·
# 제거·NBT 변형이 가능한 명령(summon/kill/damage/data ... entity/store ... entity 등.
# 즉발 피해 효과 instant_damage/instant_health 는 같은 틱에 죽음을 유발할 수 있어 포함.
# 시간 경과형 효과·물리는 함수 실행 중 틱이 진행되지 않으므로 제외).
_SUM_TOP_RE = re.compile(
    r'\b(?:summon|kill|damage|kick|ban(?:-ip)?|whitelist|transfer|place|reload|tick'
    r'|instant_damage|instant_health)\b'
    r'|\bdata\s+(?:merge|modify|remove)\s+entity\b'
    r'|\bstore\s+(?:result|success)\s+entity\b')
# 위치 변경 명령(엔티티 이동): origin-의존 스캔만 무효화하면 되는 효과
_SUM_MOVE_RE = re.compile(r'\b(?:tp|teleport|spreadplayers|ride|spectate)\b')
# tag <대상(셀렉터 내 공백/1단 중첩 [] 허용)> add|remove|list [<이름>]
_SUM_TAG_RE = re.compile(
    r'\btag\s+(@[a-z]\[(?:[^\[\]]|\[[^\]]*\])*\]|\S+)\s+(add|remove|list)\b(?:\s+(\S+))?')
_SUM_TAG_HEAD_RE = re.compile(r'\btag\s+\S')
_SUM_CALL_RE = re.compile(r'\bfunction\s+(\S+)')
_SUM_LINE_VAL_RE = re.compile(r'((?:[^"\\]|\\.)*)"')

# ── 점수판 쓰기 요약(인터프로시저) ──
# 함수별 '이 함수(전이 포함)가 쓸 수 있는 점수' 지시 집합을 계산해 assemble 의 score read CSE 가
# 함수 호출을 관통하게 한다. 지시: ('"h"','"o"') 리터럴쌍 | ("OBJ",'"o"') 동적홀더 | ("HLD",'"h"')
# reset 전체. "SALL" = 미지(전량 무효화). ⊤ 트리거는 head-앵커(run/줄머리)로 한정해
# objective 명에 흔한 kill/xp 같은 단어의 오탐을 막는다(선택자/텍스트 속 단어는 무해한
# 과보수화만 유발하나, head-앵커로 그마저 줄인다). advancement 는 보상 함수 실행 가능,
# kill/damage/xp 류는 통계·criteria 로 점수를 동기 변경할 수 있어 ⊤.
_SSUM_TOP_RE  = re.compile(r'(?:^|\brun\s+)(?:kill|damage|advancement|xp|experience|reload|tick)\b'
                           r'|instant_damage|instant_health')
_SSUM_OPER_RE = re.compile(r'\bscoreboard\s+players\s+operation\s+(\S+)\s+(\S+)\s+(\S+)\s+(\S+)\s+(\S+)')
_SSUM_PLY_RE  = re.compile(r'\bscoreboard\s+players\s+(set|add|remove|reset|enable)\s+(\S+)(?:\s+(\S+))?')
_SSUM_STORE_RE= re.compile(r'\bstore\s+(?:result|success)\s+score\s+(\S+)\s+(\S+)')
_SSUM_TRG_RE  = re.compile(r'\btrigger\s+(\S+)')
_SSUM_OBJRM_RE= re.compile(r'\bscoreboard\s+objectives\s+remove\b')

def _sum_score_token(tok):
    """홀더/obj 토큰 분류: ('lit', 자바문자열리터럴) | ('dyn',) | None(파싱불가=⊤)."""
    if not tok or '$(' in tok or '"' in tok or '\\' in tok:
        return None
    if tok.startswith('@'):
        if '[' in tok and not tok.endswith(']'):
            return None                 # 공백 낀 셀렉터 — 토큰 분해 실패 = fail-closed
        return ('dyn',)
    return ('lit', '"' + tok + '"')

def _sum_line_score(line):
    """한 명령의 점수 쓰기 요약: "SALL" | set(지시)."""
    if '$(' in line or _SSUM_TOP_RE.search(line) or _SSUM_OBJRM_RE.search(line):
        return "SALL"
    out = set()
    def _emit(th, to):
        if th is None or to is None or to[0] != 'lit':
            return False
        out.add((th[1], to[1]) if th[0] == 'lit' else ("OBJ", to[1]))
        return True
    for h, o, op, h2, o2 in _SSUM_OPER_RE.findall(line):
        pairs = [(h, o), (h2, o2)] if op == '><' else [(h, o)]
        for hh, oo in pairs:
            if not _emit(_sum_score_token(hh), _sum_score_token(oo)):
                return "SALL"
    for verb, h, o in _SSUM_PLY_RE.findall(line):
        th = _sum_score_token(h)
        if th is None:
            return "SALL"
        if verb == 'reset' and not o:
            if th[0] != 'lit':
                return "SALL"           # 동적 홀더 전체 리셋 = 미지
            out.add(("HLD", th[1]))
            continue
        if not _emit(th, _sum_score_token(o)):
            return "SALL"
    for h, o in _SSUM_STORE_RE.findall(line):
        if not _emit(_sum_score_token(h), _sum_score_token(o)):
            return "SALL"
    for o in _SSUM_TRG_RE.findall(line):
        to = _sum_score_token(o)
        if to is None or to[0] != 'lit':
            return "SALL"
        out.add(("OBJ", to[1]))         # trigger = @s(동적) 홀더 쓰기
    return out

def _sum_line_effect(line: str):
    """한 명령의 지역 효과: ("ALL" | tags:set, move:bool | None, calls:list).
       토큰은 줄 전체에서 찾는다(execute 체인·if function 등 중첩 위치 불문).
       과잉 매치(문자열 속 단어 등)는 과보수화만 유발할 뿐 불건전하지 않다."""
    calls = [m.group(1) for m in _SUM_CALL_RE.finditer(line)]
    if '$(' in line:
        return ("ALL", None, calls)
    if _SUM_TOP_RE.search(line):
        return ("ALL", None, calls)
    full = _SUM_TAG_RE.findall(line)
    if len(full) != len(_SUM_TAG_HEAD_RE.findall(line)):
        return ("ALL", None, calls)      # tag 명령 형태 인식 실패 = fail-closed
    tags = set()
    for _t, verb, name in full:
        if verb == 'list':
            continue
        if not name or '"' in name or '\\' in name:
            return ("ALL", None, calls)
        tags.add(name)
    return (tags, bool(_SUM_MOVE_RE.search(line)), calls)

def _sum_scan_line_field(rec: str):
    """JSONL 레코드에서 "line" 값을 이스케이프 원문 그대로 추출(json.loads 회피).
       토큰 검색엔 \\" 등 이스케이프가 남아 있어도 지장 없다. None = 추출 실패(⊤ 처리)."""
    i = rec.find('"line":"')
    if i < 0:
        return ''
    m = _SUM_LINE_VAL_RE.match(rec, i + 8)
    return m.group(1) if m else None

# ── pass-1.5 병렬 워커 ──
# 요약 수집은 '줄 단위 독립'(각 레코드에 fid 포함) → 라인 청크로 나눠 병렬 스캔 후
# 부분 결과를 단조 병합(ALL/SALL 흡수원, 집합 합집합)한다. 순서 무관이므로 결과 동일.
# (cap-48 은 '최종 합집합 크기' 기준으로 동치 — 부분합 어느 쪽에서 넘어도 SALL.)
_SUM_ALL_FIDS = None

def _sum_init(all_fids):
    global _SUM_ALL_FIDS
    _SUM_ALL_FIDS = all_fids

def _sum_feed_into(fid, line, local, slocal, edges, all_fids):
    """_scan_call_summaries._feed 와 동일 로직(전달 dict 에 기록)."""
    local.setdefault(fid, (set(), False))
    slocal.setdefault(fid, set())
    if line is None:
        local[fid] = "ALL"
        slocal[fid] = "SALL"
        return
    eff, move, calls = _sum_line_effect(line)
    for c in calls:
        if c in all_fids:
            edges.setdefault(fid, set()).add(c)
        else:
            local[fid] = "ALL"       # #함수태그/$()/미존재 callee = 미지
            slocal[fid] = "SALL"
    if eff == "ALL":
        local[fid] = "ALL"
    elif local[fid] != "ALL":
        cur = local[fid]
        local[fid] = (cur[0] | eff, cur[1] or move)
    if slocal[fid] != "SALL":
        se = _sum_line_score(line)
        if se == "SALL":
            slocal[fid] = "SALL"
        else:
            slocal[fid] |= se
            if len(slocal[fid]) > 48:
                slocal[fid] = "SALL"   # 폭주 방지 상한(사실상 전역 초기화 함수)

def _sum_chunk(lines):
    """워커: raw JSONL 라인 청크 → (local, slocal, edges) 부분 결과."""
    local, slocal, edges = {}, {}, {}
    for rec in lines:
        rec = rec.strip()
        if not rec:
            continue
        fid = _scan_fid(rec)
        if fid is None:
            continue
        _sum_feed_into(fid, _sum_scan_line_field(rec), local, slocal, edges, _SUM_ALL_FIDS)
    return local, slocal, edges


def _scan_call_summaries(trees_path: str, all_fids: set, group: str):
    """함수별 요약 → (effects_by_fqcn, known_fqcns).
       effects 는 효과 있는 함수만 수록: "ALL" | (frozenset[tags], move:bool).
       known 은 trees 의 전 함수 fqcn(미포함 fqcn 호출 = assemble 이 미지로 fail-closed)."""
    local: dict = {}                              # fid -> "ALL" | (set, bool)   [태그·이동]
    slocal: dict = {}                             # fid -> "SALL" | set(지시)     [점수 쓰기]
    edges = collections.defaultdict(set)          # fid -> callee fid 들
    def _feed(fid, line):
        local.setdefault(fid, (set(), False))
        slocal.setdefault(fid, set())
        if line is None:
            local[fid] = "ALL"
            slocal[fid] = "SALL"
            return
        eff, move, calls = _sum_line_effect(line)
        for c in calls:
            if c in all_fids:
                edges[fid].add(c)
            else:
                local[fid] = "ALL"       # #함수태그/$()/미존재 callee = 미지
                slocal[fid] = "SALL"
        if eff == "ALL":
            local[fid] = "ALL"
        elif local[fid] != "ALL":
            cur = local[fid]
            local[fid] = (cur[0] | eff, cur[1] or move)
        if slocal[fid] != "SALL":
            se = _sum_line_score(line)
            if se == "SALL":
                slocal[fid] = "SALL"
            else:
                slocal[fid] |= se
                if len(slocal[fid]) > 48:
                    slocal[fid] = "SALL"   # 폭주 방지 상한(사실상 전역 초기화 함수)
    if _trees_is_jsonl(trees_path):
        import os as _oss
        try:
            _sz = _oss.path.getsize(trees_path)
        except OSError:
            _sz = 0
        try:
            _jobs = int(_oss.environ.get("KFC_JOBS", "0")) or len(_oss.sched_getaffinity(0))
        except (AttributeError, OSError, ValueError):
            _jobs = _oss.cpu_count() or 1
        if _sz >= 50_000_000 and _jobs > 1:
            # 병렬: 라인 청크 → 부분 요약 → 단조 병합 (순서 무관 — 직렬과 동일 결과)
            import multiprocessing as _mpc
            def _chunks(f, n=8000):
                buf = []
                for rec in f:
                    buf.append(rec)
                    if len(buf) >= n:
                        yield buf; buf = []
                if buf:
                    yield buf
            with open(trees_path, encoding="utf-8") as f, \
                 _mpc.get_context("spawn").Pool(processes=_jobs,
                                                initializer=_sum_init,
                                                initargs=(all_fids,)) as pool:
                for plocal, pslocal, pedges in pool.imap_unordered(_sum_chunk, _chunks(f)):
                    for fid, v in plocal.items():
                        cur = local.get(fid)
                        if cur is None:
                            local[fid] = v
                        elif cur == "ALL" or v == "ALL":
                            local[fid] = "ALL"
                        else:
                            local[fid] = (cur[0] | v[0], cur[1] or v[1])
                    for fid, v in pslocal.items():
                        cur = slocal.get(fid)
                        if cur is None:
                            slocal[fid] = v
                        elif cur == "SALL" or v == "SALL":
                            slocal[fid] = "SALL"
                        else:
                            u = cur | v
                            slocal[fid] = "SALL" if len(u) > 48 else u
                    for fid, cs in pedges.items():
                        edges[fid] |= cs
        else:
            with open(trees_path, encoding="utf-8") as f:
                for rec in f:
                    rec = rec.strip()
                    if not rec:
                        continue
                    fid = _scan_fid(rec)
                    if fid is None:
                        continue
                    _feed(fid, _sum_scan_line_field(rec))
    else:
        for obj in _iter_trees(trees_path):
            fid = obj.get("function")
            if fid is not None:
                _feed(fid, obj.get("line", ""))
    # 호출 그래프 전이 폐포(worklist, 역방향 전파; 재귀/상호재귀는 단조 수렴)
    rev = collections.defaultdict(set)
    for f, cs in edges.items():
        for c in cs:
            rev[c].add(f)
    summary = local
    ssum = slocal
    work = collections.deque(summary.keys())
    inwork = set(work)
    while work:
        f = work.popleft(); inwork.discard(f)
        s = summary[f]
        sc = ssum[f]
        for caller in rev.get(f, ()):
            changed = False
            cs = summary.get(caller)
            if cs is not None and cs != "ALL":
                if s == "ALL":
                    summary[caller] = "ALL"; changed = True
                else:
                    nt = cs[0] | s[0]; nm = cs[1] or s[1]
                    if nt != cs[0] or nm != cs[1]:
                        summary[caller] = (nt, nm); changed = True
            ss = ssum.get(caller)
            if ss != "SALL":
                if sc == "SALL":
                    ssum[caller] = "SALL"; changed = True
                elif sc - ss:
                    ns = ss | sc
                    ssum[caller] = "SALL" if len(ns) > 48 else ns
                    changed = True
            if changed and caller not in inwork:
                work.append(caller); inwork.add(caller)
    # 인코딩: fqcn -> (태그효과, 점수효과). 둘 다 무효과인 함수는 미수록(사전 크기 절약).
    effects = {}
    for f, v in summary.items():
        te = "ALL" if v == "ALL" else (frozenset(v[0]), v[1])
        se = ssum[f]
        se = "SALL" if se == "SALL" else frozenset(se)
        if te == "ALL" or v[0] or v[1] or se == "SALL" or se:
            effects[fid_to_fqcn(f, group)] = (te, se)
    known = frozenset(fid_to_fqcn(f, group) for f in summary)
    return effects, known


def _mp_convert_raw(item):
    """워커(raw 입력): (fid, [raw_line,...]) -> (..., called). json.loads 를 워커에서 수행."""
    import json as _json
    from assemble import function_to_class as _f2c
    fid, raws = item
    objs = [_json.loads(r) for r in raws]
    jc = _f2c(fid, objs, group=_W_GROUP)
    from assemble import audit_executeReturn as _aud
    return (fid, jc.package, jc.cls, jc.code,
            jc.native_lines, jc.gated_lines, jc.bridge_lines, jc.fully_converted,
            _collect_called(objs), _aud(jc.code))


def _mp_convert_local_raw(item, group):
    """단일 프로세스 raw 변환(_mp_convert_raw 와 동일 반환)."""
    import json as _json
    from assemble import function_to_class as _f2c
    fid, raws = item
    objs = [_json.loads(r) for r in raws]
    jc = _f2c(fid, objs, group=group)
    from assemble import audit_executeReturn as _aud
    return (fid, jc.package, jc.cls, jc.code,
            jc.native_lines, jc.gated_lines, jc.bridge_lines, jc.fully_converted,
            _collect_called(objs), _aud(jc.code))


def _trees_is_jsonl(trees_path: str) -> bool:
    """trees 가 JSONL(줄당 객체)인지 판별. 첫 비공백 글자가 '[' 면 JSON 배열, 아니면 JSONL."""
    with open(trees_path, encoding="utf-8") as f:
        while True:
            ch = f.read(1)
            if not ch:
                return False
            if ch.strip():
                return ch != "["


def _iter_trees(trees_path: str):
    """trees 를 객체 단위로 yield. JSONL(줄당 객체) 우선, 아니면 JSON 배열로 폴백.

    대용량(700MB+) trees 를 통째로 메모리에 올리지 않기 위한 스트리밍 이터레이터.
    ParseDumper 는 JSONL 로 출력하므로 한 줄 = 한 객체. 첫 글자가 '[' 면 구버전
    JSON 배열로 보고 통째 로드(소형 전용 폴백)."""
    with open(trees_path, encoding="utf-8") as f:
        first = f.read(1)
        if not first:
            return
        if first == "[":
            # 구버전 JSON 배열 — 통째 로드 (소형 데이터팩 호환용)
            f.seek(0)
            for obj in json.load(f):
                yield obj
            return
        # JSONL: 첫 글자 포함해 줄 단위 스트리밍
        f.seek(0)
        for line in f:
            line = line.strip()
            if line:
                yield json.loads(line)


def generate(trees_path: str, datapack_root: str, out_dir: str, group: str = "kartriderpack", profile=None, clean: bool = True, merge: bool = True):
    # ── 패스별 벽시계 타이밍(항상 출력) — 병목 추적의 단일 근거 ──
    import time as _pt
    _pt0 = _pt.time(); _ptl = [_pt0]
    def _tlog(label):
        _n = _pt.time()
        print(f"[generate][t] {label}: +{_n - _ptl[0]:.1f}s (total {_n - _pt0:.1f}s)", flush=True)
        _ptl[0] = _n

    # 데이터팩 입력을 한 번 열어(디렉터리/zip 투명) 모든 로더-리소스 복사에서 재사용.
    dp_src = open_datapack(datapack_root) if datapack_root else None
    if dp_src is not None:
        emit.load_entity_type_tags(dp_src)
        emit.load_block_tags(dp_src)
        emit.load_predicates(dp_src)
    _tlog("setup (datapack tags/predicates)")

    out_root = Path(out_dir)
    # 이전 변환의 잔재 .java/리소스가 남아 빌드를 깨는 것을 막는다(예: 삭제/改名된 함수의 옛 클래스).
    # 단, 생성물인 out/src 만 비우고 빌드환경(build.gradle/gradle.properties/wrapper)·캐시
    # (.gradle/build)·개발월드(run)·사용자 수정은 보존한다. (--no-clean 으로 끌 수 있음)
    if clean:
        import shutil as _sh
        # [리소스 증분화] 종전엔 src/ 전체(=main/resources 포함)를 지워 write_resources 의
        # '불변 스킵'이 무효였다(매 변환 790MB/19만 파일 전량 재기록). java 트리만 지우고,
        # 리소스는 write_resources 가 기대 집합 대조로 스테일 파일을 삭제한다(최종 트리 동일).
        _src = out_root / "src" / "main" / "java"
        if _src.exists():
            try:
                _sh.rmtree(_src)
                print(f"[generate] cleaned previous java tree: {_src} (resources reconciled incrementally; build env/caches kept)")
            except OSError as _e:
                print(f"[generate][warn] couldn't fully clean {_src}: {_e} "
                      f"(파일 잠금? gradle 데몬/에디터 종료 후 재시도)")
    src_root = out_root / "src" / "main" / "java"
    src_root.mkdir(parents=True, exist_ok=True)

    # ── [pass-1] 스트리밍: macro_fns + called(호출 대상) 만 수집 (가벼움) ──
    # trees 전체를 메모리에 올리지 않고 한 객체씩 훑는다. 동시 메모리는 객체 1개분.
    # macro_fns 는 함수 변환 전에 알아야 하므로(emit 의 호출부 Map 인자 결정) 선행 패스로 분리.
    # ── [pass-1] macro_fns + all_fids 수집 ──
    # JSONL(신규 ParseDumper): json.loads 없이 raw 스캔(~12s/패스 → ~0.5s). called 는 pass-2
    # 워커가 파싱하며 모은다. 배열형(소형 구버전)만 기존 파싱 패스 사용.
    _jsonl = _trees_is_jsonl(trees_path)
    macro_fns = set()
    called = set()
    all_fids = set()
    if _jsonl:
        macro_fns, all_fids = _scan_macro_and_fids(trees_path)
        any_fn = any_obj = bool(all_fids)
    else:
        any_fn = any_obj = False
        cur_fid = None
        cur_has_macro = False
        for obj in _iter_trees(trees_path):
            any_obj = True
            fid = obj.get("function")
            if fid is not None:
                any_fn = True
                all_fids.add(fid)
            for step in obj.get("chain", []):
                v = step.get("value", {})
                if isinstance(v, dict) and v.get("kind") == "" and ":" in str(v.get("raw", "")):
                    called.add(v["raw"])
            if fid != cur_fid:
                if cur_fid is not None and cur_has_macro:
                    macro_fns.add(cur_fid)
                cur_fid = fid
                cur_has_macro = False
            if obj.get("macro"):
                cur_has_macro = True
        if cur_fid is not None and cur_has_macro:
            macro_fns.add(cur_fid)

    if any_obj and not any_fn:
        print("[!]  trees.json has no 'function' field. Looks like it was produced by an old ParseDumper.")
        print("    Update ParseDumper to the function-ID version and re-run extract->ParseDumper->generate.")
        return

    emit.set_macro_fns(macro_fns)
    emit.set_all_fids(all_fids)
    print(f"[generate] pass-1: identified {len(macro_fns)} macro functions")
    _tlog("pass-1 (macro/fid scan)")

    # ── [pass-1.5] 인터프로시저 태그 요약 계산 → assemble 주입 ──
    # (단일/병렬 공통. 병렬은 initargs 로 워커에도 전달. 실패해도 변환은 계속 —
    #  요약 미주입 시 assemble 은 함수 호출을 종전대로 전량 무효화로 처리한다.)
    import time as _sum_time
    import assemble as _asm_mod
    _sum_effects, _sum_known = {}, frozenset()
    try:
        _sum_t0 = _sum_time.time()
        _sum_effects, _sum_known = _scan_call_summaries(trees_path, all_fids, group)
        _asm_mod.set_call_summaries(_sum_effects, _sum_known)
        print(f"[generate] pass-1.5: interprocedural tag summaries — "
              f"{len(_sum_effects)} effectful / {len(_sum_known)} fns "
              f"({_sum_time.time() - _sum_t0:.1f}s)")
    except Exception as _sum_e:
        print(f"[generate][warn] pass-1.5 tag summary failed ({_sum_e}) — "
              f"falling back to conservative call invalidation")
    _tlog("pass-1.5 (call summaries)")

    # ── 리소스 복사(순수 I/O)를 pass-2(CPU·워커풀)와 병행 ──
    # write_resources 는 dp_src 읽기 + resources/ 쓰기뿐이라 pass-2 와 자원이 겹치지 않고,
    # 파일 I/O 는 GIL 을 놓으므로 스레드 병행이 유효하다. 예외는 join 시점에 재전파.
    tags = load_tags(trees_path)
    import threading as _thr
    _res_exc = []
    def _res_bg():
        try:
            write_resources(out_root, group, tags, dp_src)
        except BaseException as _e:
            _res_exc.append(_e)
    _res_thread = _thr.Thread(target=_res_bg, name="kfc-resources", daemon=True)
    _res_thread.start()

    # ── flatten/const-fold 준비(lines_map 로드 + 상수 분석)도 pass-2 와 병행 ──
    # 메인은 pass-2 동안 pool 대기(GIL 유휴)이므로 스레드에서 json 파싱/분석을 흡수.
    _prep_result = {}
    def _prep_bg():
        try:
            lm = None
            _lj = Path(trees_path).parent / "lines.json"
            if _lj.exists():
                try:
                    _try = json.loads(_lj.read_text(encoding="utf-8"))
                    if (all_fids and len(set(_try) & all_fids) >= len(all_fids) * 0.99
                            and len(_try) <= len(all_fids) * 1.05):   # 상위집합(다른 팩 잔재) 거부
                        lm = _try
                        _prep_result["src"] = f"extract lines.json ({len(lm)} fns)"
                except Exception:
                    lm = None
            if lm is None:
                import tree_flatten as _tfm
                lm = _tfm.collect_function_lines(dp_src)
                _prep_result["src"] = "datapack scan"
            _prep_result["lines"] = lm
            import const_fold as _cfm
            _prep_result["consts"] = _cfm.analyze(lm)
        except BaseException as _e:
            _prep_result["exc"] = _e
    _prep_thread = None
    if merge and dp_src is not None:
        _prep_thread = _thr.Thread(target=_prep_bg, name="kfc-linesmap", daemon=True)
        _prep_thread.start()

    # ── [pass-2] 함수별 변환 -> 파일 쓰기 (멀티프로세싱) ──
    # JSONL(신규 ParseDumper): 같은 함수가 연속 → 스트리밍으로 (fid,objs) 청크를 만들어
    # 워커 풀에 분배. 워커는 자바 코드 문자열만 반환, 파일 쓰기는 메인(디스크 경합 방지).
    # 동시 메모리 = 청크 in-flight 분량으로 제한되어 대용량 trees 도 메모리 일정.
    import os as _os
    stats = collections.Counter()
    fn_meta = []
    audit_violations = []   # (fid, [issue...]) — 흐름분석 사전 체크(개선사항 E)
    generated_fids = set()
    fn_count = 0

    # ── [A] 메모리 버킷화 경로 ──
    # merge(기본·버킷) 시 함수별 .java 를 디스크에 쓰지 않고 메모리(_records)에 모았다가
    # pass-3 에서 한 번에 버킷 클래스로 직접 생성한다. 기존엔 188K 개별 파일을 쓰고(pass-2)
    # → bucketize 가 전부 되읽고 → 버킷으로 합치고 → 188K 를 다시 삭제하는 왕복이 있었다.
    # 이 왕복(대형 팩 수십만 회 파일 연산)을 제거한다. 산출 버킷은 바이트 동일.
    # merge=False(--no-merge) 면 함수별 .java 자체가 산출물이므로 기존대로 디스크에 쓴다.
    import merge_pass as _mp_mod
    _records = [] if merge else None

    # 워커 수 결정: 기본은 '실제 사용 가능한 최대 코어'. KFC_JOBS 로 명시 시 그 값.
    def _usable_cpus() -> int:
        # 우선순위: 프로세스가 실제 스케줄 가능한 코어 수 -> cpu_count -> 1.
        # (컨테이너/cgroup 제한 시 cpu_count 보다 정확)
        try:
            n = len(_os.sched_getaffinity(0))      # Linux: 실제 가용 코어
            if n:
                return n
        except (AttributeError, OSError):
            pass
        try:
            n = _os.process_cpu_count()            # Python 3.13+ (affinity 반영)
            if n:
                return n
        except (AttributeError, TypeError):
            pass
        return _os.cpu_count() or 1
    try:
        _jobs_env = int(_os.environ.get("KFC_JOBS", "0"))
    except ValueError:
        _jobs_env = 0
    jobs = _jobs_env if _jobs_env > 0 else _usable_cpus()
    # 작은 팩은 spawn 오버헤드가 변환보다 커서 단일이 유리. 충분히 클 때만 병렬.
    try:
        _trees_size = _os.path.getsize(trees_path)
    except OSError:
        _trees_size = 0
    if _trees_size < 3_000_000:    # ~3MB 미만 trees ≈ 소형 팩 → 단일
        jobs = 1

    def _write_result(res):
        nonlocal fn_count
        fid, pkg, cls, code, nat, gat, br, full = res[:8]
        if len(res) > 8 and res[8]:
            called.update(res[8])          # raw 워커가 모은 호출대상 합치기
        if _records is not None:
            # [A] 디스크 미기록: 메모리 누적(Cls 가 code 텍스트에서 fid/pkg/cls/macro 파싱).
            _records.append(_mp_mod.Cls(None, code, fid=fid, package=pkg, cls_name=cls))
        else:
            pkg_dir = src_root / Path(*pkg.split("."))
            pkg_dir.mkdir(parents=True, exist_ok=True)
            write_if_changed(pkg_dir / f"{cls}.java", code)
        stats["native"] += nat
        stats["gated"] += gat
        stats["bridge"] += br
        fn_meta.append((fid, pkg, cls, nat, gat, br, full))
        if len(res) > 9:
            _au = res[9]            # 워커가 수행한 감사(메인 직렬 ~95s → 병렬부로 이동)
        else:
            _au = audit_executeReturn(code)
        if _au:
            audit_violations.append((fid, _au))
        generated_fids.add(fid)
        fn_count += 1

    def _gen_items():
        """trees 를 (fid, objs) 단위로 스트리밍 yield (메모리 일정)."""
        if _trees_is_jsonl(trees_path):
            cur_fid = None
            cur_objs = []
            for obj in _iter_trees(trees_path):
                fid = obj.get("function")
                if fid is None:
                    continue
                if fid != cur_fid:
                    if cur_fid is not None:
                        yield (cur_fid, cur_objs)
                    cur_fid = fid
                    cur_objs = []
                cur_objs.append(obj)
            if cur_fid is not None:
                yield (cur_fid, cur_objs)
        else:
            by_fn = collections.OrderedDict()
            for obj in _iter_trees(trees_path):
                fid = obj.get("function")
                if fid is not None:
                    by_fn.setdefault(fid, []).append(obj)
            for fid, objs in by_fn.items():
                yield (fid, objs)

    # 입력 생성기/워커 선택: JSONL → raw 문자열 그룹(워커가 json.loads, called 수집).
    #                        배열형(소형) → 기존 파싱 그룹(called 는 pass-1 에서 수집됨).
    if _jsonl:
        _items, _conv_local, _conv_worker = _gen_items_raw(trees_path), _mp_convert_local_raw, _mp_convert_raw
    else:
        _items, _conv_local, _conv_worker = _gen_items(), _mp_convert_local, _mp_convert

    if jobs <= 1:
        # 단일 프로세스 (작은 팩 / 디버깅): 워커 없이 직접.
        emit.set_macro_fns(macro_fns)
        emit.set_all_fids(all_fids)
        for item in _items:
            _write_result(_conv_local(item, group))
    else:
        # 멀티프로세싱: imap_unordered 로 스트리밍 분배(결과 도착 순 처리).
        import multiprocessing as _mp
        import assemble as _asm_state
        _force_prefixes = list(getattr(_asm_state, "_FORCE_BRIDGE", []))   # --bridge (CLI 가 메인에 설정)
        _trace_prefixes = list(getattr(_asm_state, "_TRACE", []))          # --trace
        ctx = _mp.get_context("spawn")   # Windows 호환(fork 없음)
        with ctx.Pool(processes=jobs,
                      initializer=_mp_init,
                      initargs=(datapack_root, macro_fns, group, all_fids,
                                _force_prefixes, _trace_prefixes,
                                _sum_effects, _sum_known)) as pool:
            # chunksize 로 IPC 오버헤드 감소. 함수가 많을수록 크게.
            for res in pool.imap_unordered(_conv_worker, _items, chunksize=64):
                _write_result(res)
        _src = "explicit KFC_JOBS" if _jobs_env > 0 else "auto (max usable cores)"
        print(f"[generate] pass-2: parallel convert with {jobs} workers [{_src}]")
    _tlog(f"pass-2 (convert {fn_count} fns, jobs={jobs})")

    # 호출되지만 클래스가 안 만들어진 함수(매크로 전용 등) -> 브릿지 stub 클래스 생성
    # called 는 pass-1 에서 스트리밍으로 수집됨.
    missing = called - generated_fids
    stub_count = 0
    for fid in missing:
        if ":" not in fid:
            continue
        jc = make_stub_class(fid, group)
        pkg_dir = src_root / Path(*jc.package.split("."))
        pkg_dir.mkdir(parents=True, exist_ok=True)
        f = pkg_dir / f"{jc.cls}.java"
        if not f.exists():
            write_if_changed(f, jc.code)
            stub_count += 1
    if stub_count:
        print(f"[generate] generated {stub_count} additional stub classes for call targets (macro/unparsed functions)")

    # KfcGen / kfcutil 안내 (실제 파일은 기존 것을 사용)
    # KfcGen.java 를 출력 트리에 자동 포함 (group 패키지로). convert.py 옆의 KfcGen.java 를 읽어 패키지 치환.
    kfcgen_src = Path(__file__).parent / "KfcGen.java"
    if kfcgen_src.exists():
        kg = kfcgen_src.read_text(encoding="utf-8")
        kg = re.sub(r'^package\s+[\w.]+;', f'package {group}.generated;', kg, count=1, flags=re.M)
        gen_dir = src_root / Path(*f"{group}.generated".split("."))
        gen_dir.mkdir(parents=True, exist_ok=True)
        write_if_changed(gen_dir / "KfcGen.java", kg)
        print(f"[generate] KfcGen.java -> {group}.generated")
    else:
        print("[!]  KfcGen.java is not next to convert.py - manual placement needed")

    # KfcPerfMixin.java 자동 포함 (20차 — 바닐라 초월 최적화: 대형 승객 리스트 O(n^2) -> O(n)).
    # KfcGen 과 동일 방식: convert.py 옆의 템플릿을 group 패키지로 치환해 배치.
    mixin_src = Path(__file__).parent / "KfcPerfMixin.java"
    if mixin_src.exists():
        mx = mixin_src.read_text(encoding="utf-8")
        mx = re.sub(r'^package\s+[\w.]+;', f'package {group}.mixin;', mx, count=1, flags=re.M)
        mixin_dir = src_root / Path(*f"{group}.mixin".split("."))
        mixin_dir.mkdir(parents=True, exist_ok=True)
        write_if_changed(mixin_dir / "KfcPerfMixin.java", mx)
        print(f"[generate] KfcPerfMixin.java -> {group}.mixin")
    else:
        print("[!]  KfcPerfMixin.java is not next to convert.py - perf mixin skipped")

    write_report(out_root, fn_meta, stats, group)
    _tlog("stubs+KfcGen+report")

    # ── 사전 흐름분석 감사(개선사항 E): unreachable/missing-return 0건 확인 ──
    if audit_violations:
        print(f"[generate][AUDIT] ⚠️ executeReturn 흐름분석 위반 {len(audit_violations)}건 "
              f"(컴파일 에러 가능성):")
        for fid, iss in audit_violations[:20]:
            print(f"    {fid}: {', '.join(iss)}")
        if len(audit_violations) > 20:
            print(f"    ... 외 {len(audit_violations)-20}건")
    else:
        print(f"[generate][AUDIT] ✅ 흐름분석 위반 0건 (unreachable/missing-return 없음)")

    # ── 모드 진입점 + resources 생성 (tick/load 태그 기반) ──
    write_entrypoint(src_root, group, tags, generated_fids)
    _res_thread.join()
    if _res_exc:
        raise _res_exc[0]
    _tlog("entrypoint+resources join (copy ran concurrently with pass-2)")

    # ── [pass-2.7] 스코어 디스패치 트리 평탄화 ──
    # 본문 전체가 `execute as @s[scores={obj=lo..hi}] run function child` 꼴인 순수
    # 디스패치 함수 클러스터(NBS 음악/키프레임 트리 — kartall 실측 전체 함수의 58%)를
    # 정적 구간 테이블 + 반복 워커로 컴파일한다. 플레이어·틱당 log2(N)단 메서드 프레임과
    # 단마다의 objective 해시 조회/박싱이 int 비교 루프로 줄고, 외부 미참조 내부 노드
    # 레코드는 제거되어 산출물/컴파일/클래스로딩 규모가 급감한다.
    # (무결성 논증·안전 게이트는 tree_flatten.py 헤더 — 실패는 fail-closed 로 원형 유지.)
    if merge and _records is not None and dp_src is not None:
        _lines_map = None
        _pre_consts = None
        try:
            import tree_flatten as _tf_mod
            # lines_map 은 pass-2 와 병행한 준비 스레드가 로드/분석 완료(위 _prep_bg).
            if _prep_thread is not None:
                _prep_thread.join()
            if "exc" in _prep_result:
                raise _prep_result["exc"]
            _lines_map = _prep_result.get("lines")
            _pre_consts = _prep_result.get("consts")
            if _lines_map is not None:
                print(f"[generate] pass-2.7: lines_map ready via {_prep_result.get('src')} (prepared concurrently with pass-2)")
            if _lines_map is None:
                _lines_map = _tf_mod.collect_function_lines(dp_src)
            tstats = _tf_mod.flatten_trees(_records, src_root, group, dp_src,
                                           tags, fid_to_fqcn, verbose=True,
                                           lines_map=_lines_map)
            print(f"[generate] pass-2.7 tree-flatten: {tstats}")
        except Exception as _te:
            import traceback; traceback.print_exc()
            print(f"[generate][warn] tree-flatten skipped due to error: {_te}")
        _tlog("pass-2.7 (tree-flatten)")

        # ── [pass-2.75] 엔티티 직접 홀더 재작성 — setScore(sb, nameOf(e), …) 류의
        #    문자열 왕복을 Entity 오버로드 호출로(opt_post.py 헤더 참조, fail-closed).
        try:
            import opt_post as _op_mod
            estats = _op_mod.rewrite_entity_holders(_records, verbose=True)
            print(f"[generate] pass-2.75 entity-holder: {estats}")
        except Exception as _ee:
            import traceback; traceback.print_exc()
            print(f"[generate][warn] entity-holder rewrite skipped due to error: {_ee}")
        _tlog("pass-2.75 (entity-holder)")

        # ── [pass-2.78] on passengers 소스 지연 생성 — 필터 선행, withEntity 는 통과분만.
        try:
            import opt_post as _op_mod78
            pstats = _op_mod78.defer_passenger_sources(_records, verbose=True)
            print(f"[generate] pass-2.78 onp-defer: {pstats}")
        except Exception as _pe:
            import traceback; traceback.print_exc()
            print(f"[generate][warn] onp-defer skipped due to error: {_pe}")
        _tlog("pass-2.78 (onp-defer)")

        # ── [pass-2.8] 상수 스코어 폴딩 — 전 팩 라인 분석으로 '항상 같은 리터럴'임이
        #    증명된 (#가짜플레이어, dummy objective) 읽기를 리터럴로 재작성.
        #    (증명 조건·시맨틱 논증은 const_fold.py 헤더. 실패는 fail-closed 로 원형 유지.)
        try:
            import const_fold as _cf_mod
            cstats = _cf_mod.fold_const_scores(_records, _lines_map, verbose=True,
                                               consts=_pre_consts)
            print(f"[generate] pass-2.8 const-fold: {cstats}")
        except Exception as _ce:
            import traceback; traceback.print_exc()
            print(f"[generate][warn] const-fold skipped due to error: {_ce}")
        _tlog("pass-2.8 (const-fold)")

        # ── [pass-2.9] 점수 체인 지역변수 강등 — 직선 구간의 (#홀더, 안전 objective)
        #    산술 연쇄를 자바 int 지역변수로('어셈블리 탈피' 1단계, opt_post.py 헤더 논증).
        try:
            import opt_post as _op_mod2
            dstats = _op_mod2.demote_scores(_records, _lines_map, verbose=True)
            print(f"[generate] pass-2.9 score-demote: {dstats}")
        except Exception as _de:
            import traceback; traceback.print_exc()
            print(f"[generate][warn] score-demote skipped due to error: {_de}")
        _tlog("pass-2.9 (score-demote)")

    # ── [pass-3] 후처리: 오버사이즈 브릿지 + 버킷화(여러 함수를 한 클래스로 묶어 클래스 수 감축) ──
    # ModEntry(tick) 가 생성된 뒤라 외부 FQCN 참조가 자동 핀된다. tick/load 는 명시 핀으로도 전달.
    # 기본 상시 버킷화. 끄려면 --no-merge (또는 --none-merge).
    if merge:
        try:
            import merge_pass
            if _records is not None:
                # [A] 메모리 경로: 디스크 왕복 없이 누적 레코드에서 바로 버킷 생성.
                #     (ModEntry/stub 등 on-disk 비생성 파일의 호출부도 여기서 재작성됨.)
                mstats = merge_pass.bucketize_records(_records, src_root, group, verbose=True)
            else:
                pins = set(tags.get("tick", [])) | set(tags.get("load", []))
                mstats = merge_pass.run_postpass(src_root, group, pins=pins,
                                                 strategy="bucket", verbose=True)
            print(f"[generate] pass-3 postprocess(bucket): {mstats}")
        except Exception as _me:
            import traceback; traceback.print_exc()
            print(f"[generate][warn] bucket pass skipped due to error: {_me}")
            # [A] 폴백: 메모리 경로 실패 시, 누적 레코드를 함수별 .java 로 흘려 써서
            #     최소한 (버킷 없는) 빌드 가능한 산출을 보장한다(= --no-merge 와 동급).
            if _records is not None:
                for c in _records:
                    pkg_dir = src_root / Path(*c.package.split("."))
                    pkg_dir.mkdir(parents=True, exist_ok=True)
                    p = pkg_dir / f"{c.cls}.java"
                    if not p.exists():
                        write_if_changed(p, c.text)
    _tlog("pass-3 (bucketize)")

    # ── [pass-4] 상수 배열 호이스팅: 방출된 `new String[]{...}` 등 상수 리터럴을
    #    클래스 static final 필드로 승격 — 실행당 할당 제거 + 메서드 바이트코드 축소.
    #    (merge 유무와 무관하게 최종 디스크 산출 전체에 적용. KfcGen.java 제외.)
    try:
        import merge_pass as _hp_mod
        _skip_hoist = ((src_root / Path(*f"{group}.buckets".split(".")))
                       if (merge and _records is not None) else None)
        hstats = _hp_mod.hoist_constants_tree(src_root, verbose=False, skip_dir=_skip_hoist)
        print(f"[generate] pass-4 hoist-constants: {hstats}")
    except Exception as _he:
        print(f"[generate][warn] hoist pass skipped due to error: {_he}")
    _tlog("pass-4 (hoist-constants)")

    print(f"[generate] {fn_count} classes -> {src_root}")
    tot = sum(stats.values())
    if tot:
        print(f"[generate] per-line: java(native {stats['native']} + gated {stats['gated']}) / "
              f"fallback {stats['bridge']} "
              f"({100*(stats['native']+stats['gated'])//tot}% pure java)")
    # 함수 단위 통계 - 문맥의 최소 단위는 mcfunction:
    #   완전변환 함수 = 네이티브로 실행 / 나머지 = instantExecuteFunction 폴백(원본 통째 실행)
    full = sum(1 for m in fn_meta if m[6])   # m=(fid,pkg,cls,native,gated,bridge,fully)
    print(f"[generate] per-function: full-native {full} / fallback {len(fn_meta)-full} "
          f"(total {len(fn_meta)}, {100*full//max(1,len(fn_meta))}% full-native)")

    # ── 빌드 환경 스캐폴딩: out 을 그대로 빌드 가능한 Fabric 모드 프로젝트로 ──
    if profile is None:
        from build_config import DEFAULT_PROFILE as profile
    scaffold_build_env(out_root, profile, group, _datapack_base_name(datapack_root))
    _tlog("scaffold (build env)")

    # ── [type-check] KfcGen 호출 타입 정합성 게이트 (javac 이전 안정망) ──
    #   생성 산출물의 모든 KfcGen.<m>(...) 호출을 KfcGen 시그니처에 대조해, String/매크로 값이
    #   수치·불리언 파라미터에 들어가는 확정적 타입 불일치(=컴파일 붕괴)를 즉시 전수 검출한다.
    #   javac 의 난해한 에러 대신, convert 시점에 파일:라인·메서드·인자로 정확히 지목한다.
    try:
        import validate_types as _vt
        _kfc = Path(__file__).resolve().parent / "KfcGen.java"
        _n = _vt.run(str(_kfc), str(src_root))
        if _n:
            print("[generate][!!] 위 타입 불일치는 컴파일이 깨지는 지점입니다 — emit 수정 필요.")
    except Exception as _ve:
        print(f"[generate][warn] type-check skipped: {_ve}")
    _tlog("type-check")



# ════════════════════════════════════════════════════════════════════
#  빌드 환경 스캐폴딩 — out 에 빌드 가능한 Fabric 모드 Gradle 프로젝트 생성
#  (build_config.py 의 BuildProfile 버전을 그대로 반영. 기존 파일은 보존)
# ════════════════════════════════════════════════════════════════════
_BUILD_GRADLE = """plugins {
    id 'fabric-loom' version '@LOOM@'
    id 'java'
}

// 데이터팩에서 자동 변환된 네이티브 Fabric 모드.
java {
    toolchain { languageVersion = JavaLanguageVersion.of(@JAVA@) }
    sourceCompatibility = JavaVersion.VERSION_@JAVA@
    targetCompatibility = JavaVersion.VERSION_@JAVA@
}

// 빌드 산출 jar 이름 = 원본 데이터팩 이름 (gradle.properties 의 mod_jar_name).
// version 을 비워 접미사 없이 정확히 "<데이터팩이름>.jar" 로 나오게 한다.
base {
    archivesName = (project.findProperty('mod_jar_name') ?: rootProject.name).toString()
}
version = ''

repositories {
    maven { url = "https://maven.fabricmc.net/" }
    mavenCentral()
}

sourceSets {
    main { java { srcDirs = ['src/main/java'] } }
}

// ── dev 실행(gradlew runServer/runClient) JVM 튜닝 ──
// 데이터팩을 네이티브 자바로 변환하면 일부 함수(충돌 물리 등 핫패스)가 8KB 를 넘는 대형
// 메서드가 된다. HotSpot 기본값(DontCompileHugeMethods=true, HugeMethodLimit=8000B)은
// 그런 메서드를 JIT 컴파일하지 않고 인터프리터로만 돌려 매 틱 성능을 크게 떨어뜨린다.
// 아래 플래그로 대형 메서드까지 전부 JIT 컴파일되게 한다(코드 변경 없이 성능 회복).
// (프로덕션 서버는 이 플래그를 서버 실행 스크립트에 직접 넣어야 한다 — gradle.properties 참고.)
loom {
    runs {
        configureEach {
            vmArgs '-XX:-DontCompileHugeMethods'   // 8KB 초과 대형 메서드도 JIT 컴파일 허용
            vmArgs '-XX:+UseG1GC'                  // 엔티티 스캔 등 대량 단명 객체에 유리
        }
    }
}

dependencies {
    minecraft "com.mojang:minecraft:${project.minecraft_version}"
    mappings "net.fabricmc:yarn:${project.yarn_mappings}:v2"
    modImplementation "net.fabricmc:fabric-loader:${project.loader_version}"
    modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"
    implementation "com.google.code.gson:gson:2.11.0"
}

// 대용량 생성 소스 컴파일: 컴파일러를 별도 JVM 으로 포크해 힙을 충분히 확보.
// (수만 클래스 규모 팩에서 데몬 힙만으론 OutOfMemoryError 가 날 수 있음)
tasks.withType(JavaCompile).configureEach {
    options.encoding = 'UTF-8'
    options.fork = true
    options.forkOptions.memoryMaximumSize = '6g'
    // 초대형(수만~십수만 클래스) 생성 트리 최적화:
    //  · 증분 컴파일 분석은 '재빌드' 시 변경분만 다시 컴파일하려는 용도라, 매번 새로
    //    생성되는 클린 트리에선 ABI 의존 그래프 구축에 시간·메모리만 쓰고 이득이 없다 → 끈다.
    //  · 이 생성 코드엔 애너테이션 프로세서가 없으므로 프로세싱 스캔도 생략(-proc:none).
    options.incremental = false
    options.compilerArgs += ['-proc:none']
}

tasks.withType(AbstractArchiveTask).configureEach {
    zip64 = true
}
"""

_SETTINGS = """pluginManagement {
    repositories {
        maven { url = "https://maven.fabricmc.net/" }
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "@MOD_ID@"
"""

_GRADLE_PROPS = """# ───────────────────────────────────────────────────────────────────
# 버전 (build_config.py BuildProfile 기준 자동 생성). 다른 MC/Fabric 으로
# 바꾸려면 이 4줄 또는 build_config.py 의 프로파일을 갈아끼우면 된다.
# ───────────────────────────────────────────────────────────────────
# 빌드 jar 이름(= 원본 데이터팩 폴더/zip 이름). 매 변환 시 현재 데이터팩에 맞게 갱신됨.
mod_jar_name=@JARNAME@

minecraft_version=@MC@
yarn_mappings=@YARN@
loader_version=@LOADER@
fabric_version=@FABRIC@

# Gradle/JVM (컴파일러는 build.gradle 에서 별도 포크되므로 데몬 힙은 작게)
org.gradle.jvmargs=-Xmx2G
org.gradle.parallel=true

# ───────────────────────────────────────────────────────────────────
# [프로덕션 서버 실행 권장 JVM 플래그]  ※ 빌드가 아니라 '서버 구동 스크립트'에 넣으세요
#   java -XX:-DontCompileHugeMethods -XX:+UseG1GC -jar fabric-server-launch.jar nogui
# 네이티브 변환으로 일부 함수(충돌 물리 등)가 8KB 를 넘는 대형 메서드가 됩니다.
# HotSpot 기본값은 그런 메서드를 JIT 컴파일하지 않고 인터프리터로만 돌려 틱 성능이
# 떨어집니다. 위 첫 플래그가 이를 해제해 전부 JIT 컴파일되게 합니다(무위험).
# (gradlew runServer 로 테스트할 때는 build.gradle 의 loom.runs 에 이미 적용돼 있습니다.)
# ───────────────────────────────────────────────────────────────────

# ───────────────────────────────────────────────────────────────────
# [중요] 마인크래프트 @MC@ 는 Java @JAVA@ 이 필요합니다.
# Java @JAVA@ 외(예: 25/26)로는 "Unsupported class file major version" 에러.
# JDK @JAVA@ 설치 후, 아래 줄의 주석(#)을 풀고 본인 설치 경로로 바꾸세요.
# (Windows 경로는 \\\\ 와 \\: 로 이스케이프)
#org.gradle.java.home=C\\:\\\\Program Files\\\\Java\\\\jdk-21.0.2
# (macOS/Linux 예: org.gradle.java.home=/usr/lib/jvm/temurin-21)
# ───────────────────────────────────────────────────────────────────
"""


def _datapack_base_name(datapack_root: str) -> str:
    """모드 jar 이름의 원천: 폴더명 또는 .zip 에서 확장자만 뗀 원본 이름(공백/점 보존).
       OS 무관하게 / 와 \\ 둘 다 경로 구분자로 처리."""
    raw = str(datapack_root).strip().rstrip("/\\")
    name = re.split(r"[\\/]", raw)[-1] if raw else ""
    if name.lower().endswith(".zip"):
        name = name[:-4]
    return name.strip() or "mod"


def _ensure_jar_name(props_path: Path, jar_name: str):
    """gradle.properties 의 mod_jar_name 만 현재 데이터팩에 맞게 갱신(나머지 사용자 수정 보존)."""
    txt = props_path.read_text(encoding="utf-8")
    line = f"mod_jar_name={jar_name}"
    if re.search(r'^\s*mod_jar_name\s*=.*$', txt, re.M):
        txt = re.sub(r'^\s*mod_jar_name\s*=.*$', line, txt, count=1, flags=re.M)
    else:
        txt = line + "\n" + txt
    props_path.write_text(txt, encoding="utf-8")


def scaffold_build_env(out_root: Path, profile, group: str, jar_name: str = "mod"):
    """out_root 에 빌드 가능한 Fabric 모드 Gradle 프로젝트를 깐다(없는 파일만 생성)."""
    import shutil, os, stat
    conv = Path(__file__).resolve().parent
    tmpl = conv / "gradle_project"
    mod_id = group.split(".")[-1].replace("_", "-")

    mc, yarn = profile.minecraft_version, profile.yarn_mappings
    loader, java = profile.fabric_loader_version, str(profile.java_release)
    fabric_api = (profile.fabric_api_version or "").strip()

    # loom 플러그인 버전: 변환기 템플릿과 동일하게 맞춘다(검증된 조합).
    loom = "1.10-SNAPSHOT"
    bg = tmpl / "build.gradle"
    if bg.exists():
        m = re.search(r"id 'fabric-loom' version '([^']+)'", bg.read_text(encoding="utf-8"))
        if m:
            loom = m.group(1)
    # fabric_api 미지정 시 변환기 gradle.properties 의 검증값을 폴백으로 사용.
    if not fabric_api:
        gp = tmpl / "gradle.properties"
        if gp.exists():
            m = re.search(r"^fabric_version=(.+)$", gp.read_text(encoding="utf-8"), re.M)
            fabric_api = m.group(1).strip() if m else ""
    # 변환기 자신이 쓰는 JDK(org.gradle.java.home)를 출력 프로젝트에 물려준다.
    # (변환기가 빌드된 이상 이 값은 유효한 JDK 21 경로 -> 출력도 바로 빌드 가능, Java 25 충돌 방지)
    java_home_line = None
    gpp = tmpl / "gradle.properties"
    if gpp.exists():
        m = re.search(r"^[ \t]*org\.gradle\.java\.home=(\S.*)$",
                      gpp.read_text(encoding="utf-8"), re.M)
        if m:
            java_home_line = "org.gradle.java.home=" + m.group(1).strip()

    created, kept = [], []

    def put(rel, text):
        p = out_root / rel
        if p.exists():
            kept.append(rel); return
        p.parent.mkdir(parents=True, exist_ok=True)
        p.write_text(text, encoding="utf-8")
        created.append(rel)

    def copy(rel, make_exec=False):
        src, dst = tmpl / rel, out_root / rel
        if dst.exists():
            kept.append(rel); return
        if not src.exists():
            return
        dst.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(src, dst)
        if make_exec:
            dst.chmod(dst.stat().st_mode | stat.S_IEXEC | stat.S_IXGRP | stat.S_IXOTH)
        created.append(rel)

    put("build.gradle", _BUILD_GRADLE.replace("@LOOM@", loom).replace("@JAVA@", java))
    put("settings.gradle", _SETTINGS.replace("@MOD_ID@", mod_id))
    props_text = (_GRADLE_PROPS.replace("@MC@", mc).replace("@YARN@", yarn)
                  .replace("@LOADER@", loader).replace("@FABRIC@", fabric_api)
                  .replace("@JAVA@", java).replace("@JARNAME@", jar_name))
    if java_home_line:
        # 주석 처리된 placeholder 줄을 변환기에서 읽은 활성 java.home 으로 교체
        # 주의: 치환문자열로 직접 넣으면 \\ 등 백슬래시가 해석되므로 람다로 원문 그대로 보존.
        props_text = re.sub(r"^#org\.gradle\.java\.home=.*$", lambda _m: java_home_line,
                            props_text, count=1, flags=re.M)
    put("gradle.properties", props_text)
    # gradle.properties 가 새로 쓰였든 기존이든, jar 이름은 항상 현재 데이터팩에 맞춘다.
    _ensure_jar_name(out_root / "gradle.properties", jar_name)
    if java_home_line:
        print(f"[generate] inherited JDK from converter: {java_home_line}")
    copy("gradlew", make_exec=True)
    copy("gradlew.bat")
    copy("gradle/wrapper/gradle-wrapper.jar")
    copy("gradle/wrapper/gradle-wrapper.properties")

    if created:
        print(f"[generate] build env scaffolded: MC {mc} / yarn {yarn} / loader {loader} / "
              f"loom {loom} / Java {java}  (+{len(created)} files)")
        if kept:
            print(f"[generate] (kept {len(kept)} existing build files)")
    else:
        print("[generate] build env already present - kept existing files")


def make_stub_class(fid: str, group: str) -> JavaClass:
    """호출되지만 본체가 없는 함수(매크로 전용 등)용 브릿지 stub.
       mcfunction 을 런타임 호출하는 안전한 폴백."""
    ns, path = fid.split(":", 1)
    segs = path.split("/")
    package = ".".join([group] + [emit.sanitize(ns)] + [emit.sanitize(s) for s in segs[:-1]])
    cls = emit.pascal(segs[-1])
    code = f"""package {package};

import {group}.generated.KfcGen;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

/** Auto-generated bridge stub for datapack function `{fid}`
 *  (매크로/미파싱 함수 - 원본 mcfunction 함수 단위 실행으로 폴백). */
public final class {cls} {{
    private {cls}() {{ throw new UnsupportedOperationException(); }}

    public static void execute(ServerCommandSource source) {{
        KfcGen.instantExecuteFunction(source, Identifier.of("{ns}", "{path}"));
    }}

    /** mcfunction return 값 전파 - if function / store result 호출용. */
    public static int executeReturn(ServerCommandSource source) {{
        return KfcGen.instantExecuteFunctionReturn(source, Identifier.of("{ns}", "{path}"));
    }}

    public static void execute(ServerCommandSource source, java.util.Map<String, String> macroArgs) {{
        KfcGen.instantExecuteFunction(source, Identifier.of("{ns}", "{path}"), macroArgs);
    }}

    /** 매크로 호출의 return 값 전파(bare 호출도 executeReturn 직접 호출로 통일 — 래퍼 프레임 제거). */
    public static int executeReturn(ServerCommandSource source, java.util.Map<String, String> macroArgs) {{
        return KfcGen.instantExecuteFunctionReturn(source, Identifier.of("{ns}", "{path}"), macroArgs);
    }}
}}
"""
    return JavaClass(fid, package, cls, code, 0, 0, 1)


def load_tags(trees_path: str) -> dict:
    """extract 가 만든 <lines>.tags.json 을 찾는다. trees 옆 / lines 옆 / 기본 경로 순.
       없으면 빈 태그(진입점은 비어도 생성)."""
    candidates = [
        Path(trees_path).with_suffix(".tags.json"),
        Path(trees_path).parent / "lines.json.tags.json",
        Path(trees_path).parent / "lines_by_fn.json.tags.json",
    ]
    for c in candidates:
        if c.exists():
            try:
                return json.loads(c.read_text(encoding="utf-8"))
            except Exception:
                pass
    print("[!]  tick/load tag file (.tags.json) not found - entrypoint will be empty.")
    print(f"    searched paths: {[str(c) for c in candidates]}")
    return {"tick": [], "load": []}


def fid_to_fqcn(fid: str, group: str) -> str:
    ns, path = fid.split(":", 1)
    segs = path.split("/")
    pkg = ".".join([group, emit.sanitize(ns)] + [emit.sanitize(s) for s in segs[:-1]])
    cls = emit.pascal(segs[-1])
    return f"{pkg}.{cls}" if segs[:-1] else f"{group}.{emit.sanitize(ns)}.{cls}"


def write_entrypoint(src_root: Path, group: str, tags: dict, generated_fids: set):
    """ModInitializer 진입점 생성. tick -> START_SERVER_TICK 매틱(틱 시작).
       load 함수는 데이터팩 load 태그(#minecraft:tags/function/load)가 바닐라 시맨틱으로 담당하므로
       진입점에서는 다루지 않는다(SERVER_STARTED 핸들러 불필요)."""
    tick_calls = []
    for fid in tags.get("tick", []):
        tick_calls.append(f"            {fid_to_fqcn(fid, group)}.executeReturn(src);")
    tick_body = "\n".join(tick_calls) if tick_calls else "            // (tick 함수 없음)"

    n_load = len(tags.get("load", []))
    load_note = (f" *  - load 태그 함수 {n_load}개 -> 데이터팩 load 태그가 담당(자바 진입점 미관여)\n"
                 if n_load else "")

    code = f"""package {group};

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.command.ServerCommandSource;

/**
 * 자동 생성 모드 진입점.
 *  - tick 태그 함수 -> 매 서버 틱 (#minecraft:tags/function/tick 대응)
{load_note} * 명령 컨텍스트는 서버 커맨드 소스(level 4, silent)를 사용한다.
 */
public final class ModEntry implements ModInitializer {{
    @Override
    public void onInitialize() {{
        ServerTickEvents.START_SERVER_TICK.register(server -> {{
            ServerCommandSource src = server.getCommandSource().withSilent();
{tick_body}
            {group}.generated.KfcGen.tickNativeSchedule(server);
        }});
    }}
}}
"""
    (src_root / Path(*group.split("."))).mkdir(parents=True, exist_ok=True)
    write_if_changed(src_root / Path(*group.split(".")) / "ModEntry.java", code)
    print(f"[generate] entrypoint ModEntry.java (load {len(tags.get('load',[]))} / tick {len(tags.get('tick',[]))})")


def write_resources(out_root: Path, group: str, tags: dict, datapack_root=None):
    """fabric.mod.json + 데이터팩 리소스 복사. mod id 는 group 의 마지막 세그먼트 기반."""
    res = out_root / "src" / "main" / "resources"
    res.mkdir(parents=True, exist_ok=True)
    mod_id = group.split(".")[-1].replace("_", "-")
    fabric_mod = {
        "schemaVersion": 1,
        "id": mod_id,
        "version": "${version}",
        "name": mod_id,
        "description": f"Auto-generated native mod from datapack (group {group})",
        "environment": "*",
        "entrypoints": {
            "main": [f"{group}.ModEntry"]
        },
        "mixins": [f"{mod_id}.mixins.json"],
        "depends": {
            "fabricloader": "*",
            "minecraft": "~1.21.5",
            "java": ">=21",
            "fabric-api": "*"
        }
    }
    (res / "fabric.mod.json").write_text(
        json.dumps(fabric_mod, indent=2, ensure_ascii=False), encoding="utf-8")
    print(f"[generate] resources/fabric.mod.json (id={mod_id}, entry={group}.ModEntry)")

    # 20차: 성능 믹스인 설정. required=false + defaultRequire=0 — 미래 MC 에서 주입이
    # 실패해도 크래시 없이 바닐라 경로 유지(fail-closed: 최적화만 소실).
    mixins_json = {
        "required": False,
        "package": f"{group}.mixin",
        "compatibilityLevel": "JAVA_21",
        "minVersion": "0.8",
        "mixins": ["KfcPerfMixin"],
        "injectors": {"defaultRequire": 0}
    }
    (res / f"{mod_id}.mixins.json").write_text(
        json.dumps(mixins_json, indent=2, ensure_ascii=False), encoding="utf-8")
    print(f"[generate] resources/{mod_id}.mixins.json (package={group}.mixin)")

    # ── 데이터팩 리소스 복사 ──
    # tags / predicate / loot_table / advancement 등 비-function 리소스는 그대로 데이터팩으로 남겨야
    # (a) 다른 데이터팩이 참조하는 #kartmobil:ignoreblock, kartmobil:ifride 등이 해소되고
    # (b) 브릿지 폴백(instantExecuteCommand)이 의존하는 정의가 살아있다.
    # function(.mcfunction)도 복사한다 - 브릿지 폴백이 'function X' 를 런타임 호출하기 때문.
    # 단, minecraft:tags/function/tick-load 는 제외한다(진입점이 자바로 대체하므로 중복 실행 방지).
    dp_src = (datapack_root if (datapack_root is not None and hasattr(datapack_root, "glob"))
              else (open_datapack(datapack_root) if datapack_root else None))
    if dp_src is not None and dp_src.exists():
        dst_data = res / "data"
        copied = skipped_ticks = unchanged = 0
        _seen_dirs = set()   # 생성한 부모 디렉터리 캐시 — 188k 파일에서 mkdir 중복 syscall 제거
        # data/ 이하 전 파일을 메모리에서 읽어 그대로 기록 (zip 도 디스크 전개 없이).
        # [가속 2건 — 산출 바이트 불변]
        #  (1) 불변 스킵: 기존 파일과 내용 동일하면 미기록(mtime 보존) — 반복 변환에서
        #      gradle processResources/jar 의 최신성 검사가 통과해 증분 빌드에도 기여.
        #  (2) 스레드 병렬 기록: 파일 I/O 는 GIL 을 놓으므로 스레드로 유효(대량 소파일 syscall
        #      레이턴시 은닉). 배치 단위 처리로 메모리 상주는 배치 분량으로 제한.
        from concurrent.futures import ThreadPoolExecutor
        # ── 매니페스트 증분 (dir 소스 전용) ──
        # {rel: [src_size, src_mtime_ns, dst_size, dst_mtime_ns]} — 원본과 산출 양쪽의
        # stat 이 직전 기록과 일치하면 read 없이 skip (make/gradle 식 최신성 판정).
        # 원본이 zip 이거나 KFC_RES_MANIFEST=0 이면 종전(바이트 비교) 경로.
        import os as _osr
        _use_manifest = (getattr(dp_src, "kind", "") == "dir"
                         and _osr.environ.get("KFC_RES_MANIFEST") != "0")
        _mf_path = res / ".kfc_res_manifest.json"
        _manifest = {}
        if _use_manifest and _mf_path.exists():
            try:
                _manifest = json.loads(_mf_path.read_text(encoding="utf-8"))
            except Exception:
                _manifest = {}
        _new_manifest = {}
        def _copy_one(job):
            # job=(dst, blob | None, abs_src, relposix, src_size, src_mtime_ns)
            dst, blob, absrc, relposix, ssz, smt = job
            if blob is None:
                blob = absrc.read_bytes()
            w = 1
            try:
                if dst.stat().st_size == len(blob) and dst.read_bytes() == blob:
                    w = 0                         # 불변 → 미기록(mtime 보존)
            except OSError:
                pass
            if w:
                dst.write_bytes(blob)
            try:
                st = dst.stat()
                _new_manifest[relposix] = [ssz, smt, st.st_size, st.st_mtime_ns]
            except OSError:
                pass
            return w
        _BATCH = 4096
        batch = []
        _expected = set()   # 이번 변환의 유효 리소스 상대경로 — 스테일 파일 삭제 대조용
        def _src_iter():
            """(relposix, blob|None, abs|None, size, mtime_ns) 로 정규화."""
            if _use_manifest:
                for rel, ap, sz, mt in dp_src.iter_stat(under="data"):
                    yield rel[len("data/"):], None, ap, sz, mt
            else:
                for rel, blob in dp_src.iter_files(under="data"):
                    yield rel[len("data/"):], blob, None, len(blob), None
        with ThreadPoolExecutor(max_workers=8) as _ex:
            def _flush():
                nonlocal copied, unchanged
                for w in _ex.map(_copy_one, batch):
                    copied += w
                    unchanged += (1 - w)
                batch.clear()
            for relposix, blob, absrc, ssz, smt in _src_iter():
                # tick 함수 태그만 제외 (자바 진입점이 매 틱 담당 -> 중복 구동 방지).
                # load 태그는 데이터팩에 보존 - 바닐라 함수 매니저가 월드 로드/리로드 시점에
                # 정확한 시맨틱(스폰청크 로드 후, /reload 포함)으로 실행한다.
                if relposix in ("minecraft/tags/function/tick.json",
                                "minecraft/tags/functions/tick.json"):
                    skipped_ticks += 1
                    continue
                dst = dst_data / relposix
                _expected.add(relposix)
                # 빠른 경로: 원본 stat + 산출 stat 이 매니페스트와 일치 → read 없이 skip
                if _use_manifest and smt is not None:
                    ent = _manifest.get(relposix)
                    if ent and ent[0] == ssz and ent[1] == smt:
                        try:
                            st = dst.stat()
                            if st.st_size == ent[2] and st.st_mtime_ns == ent[3]:
                                _new_manifest[relposix] = ent
                                unchanged += 1
                                continue
                        except OSError:
                            pass
                parent = dst.parent
                if parent not in _seen_dirs:        # 같은 디렉터리는 한 번만 mkdir
                    parent.mkdir(parents=True, exist_ok=True)
                    _seen_dirs.add(parent)
                batch.append((dst, blob, absrc, relposix, ssz, smt))
                if len(batch) >= _BATCH:
                    _flush()
            _flush()
        if _use_manifest:
            try:
                _mf_path.write_text(json.dumps(_new_manifest, separators=(",", ":")),
                                    encoding="utf-8")
            except OSError as _me:
                print(f"[generate][warn] resource manifest write failed: {_me}")
        # ── 스테일 리소스 삭제(종전 'src/ 전체 clean' 과 최종 트리 동일 보장) ──
        # 데이터팩에서 삭제/개명된 파일이 남지 않도록 기대 집합에 없는 파일을 지운다.
        stale = 0
        if dst_data.exists():
            for p in dst_data.rglob("*"):
                if p.is_file():
                    relp = p.relative_to(dst_data).as_posix()
                    if relp not in _expected:
                        try:
                            p.unlink(); stale += 1
                        except OSError:
                            pass
            if stale:
                # 비워진 디렉터리 정리(bottom-up)
                import os as _osw
                for dirpath, _dn, _fn in _osw.walk(dst_data, topdown=False):
                    try:
                        _osw.rmdir(dirpath)
                    except OSError:
                        pass
        # pack.mcmeta 도 복사 (있으면)
        pm = dp_src.pack_meta_bytes()
        if pm is not None:
            (res / "pack.mcmeta").write_bytes(pm)
        print(f"[generate] copied datapack resources: {copied} written, {unchanged} unchanged(skip), "
              f"{stale} stale removed (excluded {skipped_ticks} tick tags - replaced by entrypoint)")
    else:
        print("[!]  no datapack_root - datapack resources (tags/predicate etc.) not copied. "
              "other datapacks may fail to find #kartmobil:ignoreblock etc.")


def write_report(out_root: Path, fn_meta, stats, group, top_n: int = 500):
    # fn_meta: list of (fid, package, cls, native, gated, bridge, fully)
    lines = ["# Conversion Report\n"]
    tot = sum(stats.values())
    if tot:
        lines.append(f"- total {tot} lines | native {stats['native']} / gated {stats['gated']} / bridge {stats['bridge']}")
        lines.append(f"- native+gated: {100*(stats['native']+stats['gated'])//tot}%\n")
    full = sum(1 for m in fn_meta if m[6])
    lines.append(f"- functions: {len(fn_meta)} total | full-native {full} "
                 f"({100*full//max(1,len(fn_meta))}%)\n")
    # 함수 표는 bridge(폴백)가 많은 상위 N개만 — 대형 팩(수십만 함수)에서 리포트 비대화 방지.
    ranked = sorted(fn_meta, key=lambda m: -m[5])
    shown = ranked[:top_n]
    lines.append(f"## Functions by fallback lines (top {len(shown)} of {len(fn_meta)})\n")
    lines.append("| function | class | native | gated | bridge |")
    lines.append("|---|---|---|---|---|")
    for fid, pkg, cls, nat, gat, br, _full in shown:
        lines.append(f"| `{fid}` | `{pkg}.{cls}` | {nat} | {gat} | {br} |")
    (out_root / "CONVERSION_REPORT.md").write_text("\n".join(lines), encoding="utf-8")


# ───────────────────────── CLI ─────────────────────────
def _infer_group(datapack_root: str, argv: list) -> str:
    if "--group" in argv:
        return argv[argv.index("--group") + 1]
    _dp = Path(datapack_root.rstrip("/\\"))
    _nm = _dp.stem if _dp.suffix.lower() == ".zip" else _dp.name
    return sanitize_pkg(_nm)


def _flag_vals(argv: list, flag: str) -> list:
    return [argv[i + 1] for i, a in enumerate(argv) if a == flag and i + 1 < len(argv)]


def build(datapack_root: str, out_src_dir: str, argv: list):
    """통합: extract -> 헤드리스 parse -> generate 를 한 번에."""
    import tempfile
    from build_config import get_profile
    from parser_runner import run_headless_parse, compile_parser

    group = _infer_group(datapack_root, argv)
    prof_name = (_flag_vals(argv, "--profile") or [None])[0]
    prof_file = (_flag_vals(argv, "--profile-file") or [None])[0]
    profile = get_profile(prof_name, prof_file)

    parser_classes = (_flag_vals(argv, "--parser-classes") or [None])[0]
    project_dir = (_flag_vals(argv, "--project") or [None])[0]
    extra_cp = _flag_vals(argv, "--parser-cp")

    force = _flag_vals(argv, "--bridge")
    traced = _flag_vals(argv, "--trace")

    print(f"[build] profile: {profile.name} (MC {profile.minecraft_version} / "
          f"Fabric {profile.fabric_loader_version}), group: {group}")

    workdir = tempfile.mkdtemp(prefix="kfc_build_")
    lines_json = str(Path(workdir) / "lines.json")
    trees_json = str(Path(workdir) / "trees.json")

    # [1] extract
    extract(datapack_root, lines_json)

    # [2] 헤드리스 parse
    if not parser_classes:
        # 파서 클래스가 안 주어지면, HeadlessParser.java 를 즉석 컴파일 시도.
        hp_src = str(Path(__file__).parent / "HeadlessParser.java")
        if Path(hp_src).exists() and (extra_cp or profile.parser_classpath):
            cp_for_compile = list(profile.parser_classpath) + extra_cp
            parser_classes = compile_parser(hp_src, str(Path(workdir) / "parser_classes"),
                                             profile, cp_for_compile)
        else:
            raise RuntimeError(
                "--parser-classes <HeadlessParser.class dir|jar> is required. "
                "(or pass --parser-cp with MC/Fabric runtime to compile on the fly)")
    run_headless_parse(lines_json, trees_json, profile, parser_classes,
                       project_dir=project_dir, extra_classpath=extra_cp)

    # [3] generate
    import assemble as _asm
    _asm.set_force_bridge(force)
    _asm.set_trace(traced)
    generate(trees_json, datapack_root, out_src_dir, group, profile=profile,
             clean=("--no-clean" not in argv),
             merge=not any(f in argv for f in ("--no-merge", "--none-merge")))
    print(f"[build] done -> {out_src_dir}")


def main():
    if len(sys.argv) < 2:
        print(__doc__); sys.exit(1)
    cmd = sys.argv[1]
    if cmd == "extract":
        extract(sys.argv[2], sys.argv[3])
    elif cmd == "build":
        try:
            build(sys.argv[2], sys.argv[3], sys.argv)
        except (RuntimeError, KeyError) as e:
            print(f"\n[build aborted] {e}", file=sys.stderr)
            sys.exit(2)
    elif cmd == "generate":
        datapack_root = sys.argv[3]
        group = _infer_group(datapack_root, sys.argv)
        # --bridge <prefix> (반복 가능): 해당 prefix 함수들을 강제 브릿지(디버깅 이분 탐색용)
        force = _flag_vals(sys.argv, "--bridge")
        # --trace <prefix> (반복 가능): 해당 함수 진입 시 실행 컨텍스트 로그(서버 콘솔)
        traced = _flag_vals(sys.argv, "--trace")
        import assemble as _asm
        _asm.set_force_bridge(force)
        _asm.set_trace(traced)
        if force:
            print(f"[generate] forced bridge prefix: {force}")
        from build_config import get_profile
        prof_name = (_flag_vals(sys.argv, "--profile") or [None])[0]
        prof_file = (_flag_vals(sys.argv, "--profile-file") or [None])[0]
        profile = get_profile(prof_name, prof_file)
        print(f"[generate] package group: {group} | profile: {profile.name} "
              f"(MC {profile.minecraft_version} / Fabric {profile.fabric_loader_version})")
        generate(sys.argv[2], datapack_root, sys.argv[4], group, profile=profile,
                 clean=("--no-clean" not in sys.argv),
                 merge=not any(f in sys.argv for f in ("--no-merge", "--none-merge")))
    else:
        print(f"unknown command: {cmd}"); print(__doc__); sys.exit(1)


if __name__ == "__main__":
    main()