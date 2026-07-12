#!/usr/bin/env python3
"""
opt_post.py — 생성 자바 레코드 후처리 최적화 2종 (버킷화/pass-4 이전, records 텍스트 단계).

[pass-2.75] 엔티티 직접 홀더 재작성 (optimize-offer #2)
    KfcGen.setScore(sb, KfcGen.nameOf(<expr>), ...) 류의 '엔티티→이름 문자열→ScoreHolder'
    왕복을 엔티티 직접 오버로드 호출로 재작성한다. KfcGen 에 동일 이름·동일 인자수의
    Entity 오버로드가 있어 pass-4 의 objective(ObjRef) 승격 패턴과 그대로 호환된다.
    시맨틱 노트: nameOf(null)=null 홀더는 종전엔 'null 이름 홀더'로 기록되는 정의되지 않은
    동작이었다(바닐라 mcfunction 은 셀렉터 미매치 시 명령 자체를 건너뜀). Entity 판은
    null 이면 no-op — 바닐라 시맨틱과 일치하는 방향의 정정이며 report 에 기록된다.

[pass-2.9] 상수-홀더 점수 체인 지역변수 강등 (optimize-offer #3③ / '어셈블리 탈피' 1단계)
    한 메서드의 '직선 구간'(분기/루프경계/비-점수 호출이 없는 연속 순수 점수 문장 run)
    안에서 (#가짜플레이어, 안전 objective) 키에 대한 set/add/operation 연쇄를 자바 int
    지역변수로 계산하고, 구간이 끝나는 지점에서 최종값 1회만 setScore 로 기록(flush)한다.

    안전 논증(fail-closed — 전부 만족해야 강등):
      · 홀더가 '#' 가짜플레이어 (플레이어/엔티티 이름 충돌 불가 관례).
      · objective 는 팩 전체에서 `objectives add <o> dummy` 로만 생성되고 remove/setdisplay/
        display name·numberformat/objectives modify 대상이 아니며, 텍스트 컴포넌트 score
        참조·bossbar·players get 등 스코어 관측 명령이 있는 줄에 이름이 등장하지 않음
        → load 이후 항상 존재 + 중간 상태가 패킷/화면/관측 명령으로 노출될 경로 없음.
      · 직선 구간 내에는 다른 코드가 전혀 실행되지 않고(서버 메인 스레드 순차 실행) 구간
        경계(배리어) 직전에 항상 flush → 구간 밖에서 보이는 스코어보드 상태는 원본과 동일.
      · opScore 는 =, +=, -=, *=, <, > 만 강등(/ % 의 0-가드·><(swap) 은 원형 유지).
        소스가 강등-안전 키가 아니면(엔티티/비안전 objective) 그 줄은 원형 유지 + 해당
        소스 키가 추적 중이면 먼저 flush(스테일 읽기 방지).
      · 알 수 없는 줄/부분 매칭 실패/여러 줄로 감싼 문장은 전부 배리어(원형 유지).

    관측 편차(문서화): 강등 구간의 중간 쓰기가 개별 스코어보드 dirty/patket 갱신을 만들지
    않으나, 비표시 objective 는 클라이언트로 브로드캐스트되지 않으므로 실제 관측 불가.
    opScore 소스 특수화 시 '소스 엔트리 getOrCreateScore 존재화'가 생략될 수 있는데, 이는
    안전-objective(#홀더) 키 한정이며 존재 여부는 표시·관측 경로가 없어 게임 내 노출이
    없다(운영자 /scoreboard players list 수동 조회만 — const_fold 와 동급 노출).
"""
from __future__ import annotations
import re

REWRITE_ENTITY_HOLDERS = True
DEMOTE_SCORES = True

# ───────────────────────── 공용: 호출 인자 스캐너 ─────────────────────────

_STR_RE = re.compile(r'"(?:[^"\\]|\\.)*"')

def _strip_strings_line(s: str) -> str:
    return _STR_RE.sub('""', s)


def _scan_calls(text: str, needle: str):
    """text 안 모든 `needle`(= 'KfcGen.foo(') 호출의 (start, close_idx, [(a0s,a0e),...]).
       문자열/괄호 상태 추적 — SNBT/텍스트 리터럴 내부 쉼표·괄호에 속지 않는다."""
    out = []
    i = 0
    L = len(text)
    while True:
        j = text.find(needle, i)
        if j < 0:
            break
        i = j + len(needle)
        ls = text.rfind('\n', 0, j) + 1
        seg = text[ls:j]
        if seg.count('"') % 2 == 1 or '//' in _strip_strings_line(seg):
            continue    # 문자열/주석 내부
        k = j + len(needle)
        depth = 1
        in_str = False
        esc = False
        seg_start = k
        args = []
        close_idx = -1
        while k < L:
            c = text[k]
            if in_str:
                if esc:
                    esc = False
                elif c == '\\':
                    esc = True
                elif c == '"':
                    in_str = False
            else:
                if c == '"':
                    in_str = True
                elif c == '(':
                    depth += 1
                elif c == ')':
                    depth -= 1
                    if depth == 0:
                        args.append((seg_start, k))
                        close_idx = k
                        break
                elif c == ',' and depth == 1:
                    args.append((seg_start, k))
                    seg_start = k + 1
            k += 1
        if close_idx >= 0:
            out.append((j, close_idx, args))
    return out


# ═══════════════ [pass-2.75] 엔티티 직접 홀더 재작성 ═══════════════

# (호출명, 인자수, 홀더 인자 인덱스들)
_ENT_SPECS = [
    ("KfcGen.setScore(", 4, (1,)),
    ("KfcGen.addScore(", 4, (1,)),
    ("KfcGen.getScore(", 3, (1,)),
    ("KfcGen.readScore(", 3, (1,)),
    ("KfcGen.resetScore(", 3, (1,)),
    ("KfcGen.enableTrigger(", 3, (1,)),
    ("KfcGen.opScore(", 6, (1, 4)),
]
_NAMEOF = "KfcGen.nameOf("


def _balanced(inner: str) -> bool:
    depth = 0
    in_str = False
    esc = False
    for ch in inner:
        if in_str:
            if esc:
                esc = False
            elif ch == '\\':
                esc = True
            elif ch == '"':
                in_str = False
        elif ch == '"':
            in_str = True
        elif ch == '(':
            depth += 1
        elif ch == ')':
            depth -= 1
            if depth < 0:
                return False
    return depth == 0 and not in_str


def _rewrite_entity_text(text: str) -> tuple[str, int]:
    n = 0
    for needle, argc, hidxs in _ENT_SPECS:
        if needle not in text:
            continue
        calls = _scan_calls(text, needle)
        repls = []
        for start, close, args in calls:
            if len(args) != argc:
                continue
            for hi in hidxs:
                a0, a1 = args[hi]
                arg = text[a0:a1].strip()
                if arg.startswith(_NAMEOF) and arg.endswith(")"):
                    inner = arg[len(_NAMEOF):-1]
                    if _balanced(inner):
                        repls.append((a0, a1, " " + inner))
        for a0, a1, rep in sorted(repls, reverse=True):
            text = text[:a0] + rep + text[a1:]
            n += 1
    return text, n


def rewrite_entity_holders(records: list, verbose: bool = True) -> dict:
    if not REWRITE_ENTITY_HOLDERS:
        return {"rewritten": 0}
    total = 0
    for c in records:
        if _NAMEOF not in c.text:
            continue
        t, n = _rewrite_entity_text(c.text)
        if n:
            c.text = t
            c.size = len(t.encode("utf-8"))
            total += n
    if verbose:
        print(f"  [entity-holder] {total} sites rewritten (nameOf 왕복 제거)")
    return {"rewritten": total}


# ═══════════════ [pass-2.9] 점수 체인 지역변수 강등 ═══════════════

_H = r'#[\w.+-]+'
_O = r'[\w.+-]+'
_OBJ_ADD = re.compile(rf'scoreboard objectives add ({_O}) (\S+)')
_OBJ_RM = re.compile(rf'scoreboard objectives remove ({_O})')
_SETDISPLAY = re.compile(rf'scoreboard objectives setdisplay \S+ ({_O})')
_DISPLAY_PLAYER = re.compile(rf'scoreboard players display \S+ \S+ ({_O})')
_OBJ_MODIFY = re.compile(rf'scoreboard objectives modify ({_O}) ')


def demotable_objectives(lines_map: dict) -> set:
    """전 팩 라인 분석: '표시/관측 경로에 절대 등장하지 않는 dummy objective' 집합.
       조건 미충족·의심스러운 사용처는 전부 실격(fail-closed)."""
    crit = {}
    disq = set()
    for fid, lines in lines_map.items():
        for raw in lines:
            l = raw[1:].strip() if raw.startswith("$") else raw
            m = _OBJ_ADD.search(l)
            if m:
                crit.setdefault(m.group(1), set()).add(m.group(2))
                if "$(" in m.group(1):
                    return set()          # 매크로 objective 생성 — 전역 보수화
            m = _OBJ_RM.search(l)
            if m:
                disq.add(m.group(1))
            m = _SETDISPLAY.search(l)
            if m:
                disq.add(m.group(1))
            m = _DISPLAY_PLAYER.search(l)
            if m:
                disq.add(m.group(1))
            m = _OBJ_MODIFY.search(l)
            if m:
                disq.add(m.group(1))      # displayname/numberformat/rendertype 등 표시 계열
            # 매크로($())가 표시/제거 명령의 objective 위치에 올 수 있으면 어떤 objective 든
            # 런타임에 표시될 수 있다 → 전역 보수화(전체 강등 비활성).
            if "$(" in raw and ("objectives setdisplay" in l or "objectives remove" in l
                                or "objectives modify" in l or "players display" in l):
                return set()
    safe = set()
    for o, cs in crit.items():
        if o in disq or cs != {"dummy"}:
            continue
        safe.add(o)
    if not safe:
        return safe
    # 주의: 텍스트 컴포넌트 score 참조(tellraw 등)·players get·if score·store 캡처는 전부
    # '명령 실행 시점의 동기 읽기'다 — 강등 구간은 그런 줄을 배리어로 취급해 직전에 flush
    # 하므로 읽기가 보는 스코어보드 값은 원본과 항상 동일하다(실격 사유 아님).
    # 비동기 노출은 표시 바인딩(setdisplay/display name·numberformat/criteria)뿐이며 위에서
    # 전부 실격 처리했다. 표시 objective 는 클라이언트 패킷으로 중간 상태가 보일 수 있으나
    # 비표시 objective 는 브로드캐스트 자체가 없다(ServerScoreboard 는 display 슬롯 objective
    # 만 추적 전송).
    return safe


# ── 순수 점수 문장 문법(정확 매칭 실패 = 배리어) ──
_L_SET = re.compile(r'^(\s*)KfcGen\.setScore\(sb, "(' + _H + r')", "(' + _O + r')", (.+)\);\s*$')
_L_ADD = re.compile(r'^(\s*)KfcGen\.addScore\(sb, "(' + _H + r')", "(' + _O + r')", (.+)\);\s*$')
_L_OP = re.compile(r'^(\s*)KfcGen\.opScore\(sb, "(' + _H + r')", "(' + _O + r')", "(=|\+=|-=|\*=|/=|%=|<|>)", '
                   r'"(' + _H + r')", "(' + _O + r')"\);\s*$')
_L_OPN = re.compile(r'^(\s*)KfcGen\.opScoreN\(sb, "(' + _H + r')", "(' + _O + r')", "(=|\+=|-=|\*=|/=|%=|<|>)", '
                    r'(-?\d+)\);\s*$')
_PURE_PREFIX = re.compile(
    r'^\s*(?:KfcGen\.(?:setScore|addScore|opScoreN?|resetScore)\(sb, |'
    r'(?:int|Integer|boolean) _\w+ = )')

_INT = r'-?\d+|Integer\.MIN_VALUE|Integer\.MAX_VALUE'


def _read_pats(h: str, o: str):
    hq, oq = re.escape(f'"{h}"'), re.escape(f'"{o}"')
    return [
        (re.compile(rf'KfcGen\.getScore\(sb, {hq}, {oq}\)'), "VAR"),
        (re.compile(rf'KfcGen\.readScore\(sb, {hq}, {oq}\)'), "Integer.valueOf(VAR)"),
        (re.compile(rf'KfcGen\.scoreMatches\(sb, {hq}, {oq}, ({_INT}), ({_INT})\)'),
         r"(VAR >= \1 && VAR <= \2)"),
    ]


def _line_kind(line: str, safe_objs: set):
    """(kind, groups) — kind ∈ {set, add, op, None}. 강등-안전 키에 대한 문장만 인식."""
    m = _L_SET.match(line)
    if m and m.group(3) in safe_objs:
        return "set", m
    m = _L_ADD.match(line)
    if m and m.group(3) in safe_objs:
        return "add", m
    m = _L_OP.match(line)
    if m and m.group(3) in safe_objs and m.group(6) in safe_objs:
        return "op", m
    m = _L_OPN.match(line)
    if m and m.group(3) in safe_objs:
        return "opn", m
    return None, None


def _demote_segment(seg: list[str], safe_objs: set, var_seq: list) -> tuple[list[str], int]:
    """직선 구간(순수 점수 문장 run) 하나를 강등 재작성. (새 줄들, 강등 op 수)."""
    # 1차: 키별 쓰기 op 수 집계 — 2회 이상 등장하는 키만 강등(이득 조건)
    write_count = {}
    for ln in seg:
        if _is_neutral_line(ln):
            continue
        kind, m = _line_kind(ln, safe_objs)
        if kind in ("set", "add"):
            write_count[(m.group(2), m.group(3))] = write_count.get((m.group(2), m.group(3)), 0) + 1
        elif kind in ("op", "opn"):
            write_count[(m.group(2), m.group(3))] = write_count.get((m.group(2), m.group(3)), 0) + 1
    hot = {k for k, n in write_count.items() if n >= 2}
    if not hot:
        return seg, 0

    out = []
    active = {}    # key -> (var, declared)  — 항상 dirty(쓰기에서만 시작)
    demoted = 0

    def read_expr(key):
        h, o = key
        if key in active:
            return active[key][0]
        return f'KfcGen.getScore(sb, "{h}", "{o}")'

    def subst_reads(line, skip_key=None):
        """추적 중 키의 읽기 호출을 지역변수로 치환."""
        for key, (var, _) in active.items():
            if key == skip_key:
                pass    # 자기 자신 읽기도 치환 대상(값 동일)
            h, o = key
            for pat, tmpl in _read_pats(h, o):
                line = pat.sub(tmpl.replace("VAR", var), line)
        return line

    def flush_key(key, indent="        "):
        var, _ = active.pop(key)
        h, o = key
        out.append(f'{indent}KfcGen.setScore(sb, "{h}", "{o}", {var});')

    def leftover_guard(line, cur_key):
        """치환 후에도 추적 중 '다른 키'의 홀더 리터럴이 남은 줄 → 그 키 flush.
           (미인식 읽기/쓰기 문맥 방지 — 홀더 단독 등장까지 과잉 flush, 안전 우선)"""
        for key in list(active.keys()):
            if key == cur_key:
                continue
            h, o = key
            if f'"{h}"' in line:
                flush_key(key, line[:len(line) - len(line.lstrip())])

    for ln in seg:
        if _is_neutral_line(ln):
            out.append(ln)
            continue
        kind, m = _line_kind(ln, safe_objs)
        if kind == "set":
            ind, h, o, expr = m.group(1), m.group(2), m.group(3), m.group(4)
            key = (h, o)
            expr = subst_reads(expr)
            if key in hot:
                leftover_guard(ind + expr, key)
                if key in active:
                    out.append(f'{ind}{active[key][0]} = {expr};')
                else:
                    var_seq[0] += 1
                    var = f'_kd{var_seq[0]}'
                    active[key] = (var, True)
                    out.append(f'{ind}int {var} = {expr};')
                demoted += 1
            else:
                ln2 = f'{ind}KfcGen.setScore(sb, "{h}", "{o}", {expr});'
                leftover_guard(ln2, None)
                out.append(ln2)
            continue
        if kind == "add":
            ind, h, o, expr = m.group(1), m.group(2), m.group(3), m.group(4)
            key = (h, o)
            expr = subst_reads(expr)
            if key in hot:
                leftover_guard(ind + expr, key)
                if key in active:
                    out.append(f'{ind}{active[key][0]} += {expr};')
                else:
                    var_seq[0] += 1
                    var = f'_kd{var_seq[0]}'
                    active[key] = (var, True)
                    out.append(f'{ind}int {var} = KfcGen.getScore(sb, "{h}", "{o}") + ({expr});')
                demoted += 1
            else:
                ln2 = f'{ind}KfcGen.addScore(sb, "{h}", "{o}", {expr});'
                leftover_guard(ln2, None)
                out.append(ln2)
            continue
        if kind == "op":
            ind, h, o, op, sh, so = (m.group(1), m.group(2), m.group(3),
                                     m.group(4), m.group(5), m.group(6))
            key = (h, o)
            skey = (sh, so)
            src = read_expr(skey)
            if key in hot:
                if key in active:
                    var = active[key][0]
                    if op == "=":
                        out.append(f'{ind}{var} = {src};')
                    elif op in ("+=", "-=", "*="):
                        out.append(f'{ind}{var} {op} {src};')
                    elif op == "/=":
                        out.append(f'{ind}{var} = KfcGen.sdiv({var}, {src});')
                    elif op == "%=":
                        out.append(f'{ind}{var} = KfcGen.smod({var}, {src});')
                    elif op == "<":
                        out.append(f'{ind}{var} = Math.min({var}, {src});')
                    else:
                        out.append(f'{ind}{var} = Math.max({var}, {src});')
                else:
                    var_seq[0] += 1
                    var = f'_kd{var_seq[0]}'
                    active[key] = (var, True)
                    base = f'KfcGen.getScore(sb, "{h}", "{o}")'
                    if op == "=":
                        out.append(f'{ind}int {var} = {src};')
                    elif op in ("+=", "-=", "*="):
                        out.append(f'{ind}int {var} = {base} {op[0]} ({src});')
                    elif op == "/=":
                        out.append(f'{ind}int {var} = KfcGen.sdiv({base}, {src});')
                    elif op == "%=":
                        out.append(f'{ind}int {var} = KfcGen.smod({base}, {src});')
                    elif op == "<":
                        out.append(f'{ind}int {var} = Math.min({base}, {src});')
                    else:
                        out.append(f'{ind}int {var} = Math.max({base}, {src});')
                demoted += 1
            else:
                # 대상은 비강등 — 소스가 추적 중이면 setScore/addScore 특수화로 스테일 방지
                if skey in active:
                    var = active[skey][0]
                    if op == "=":
                        out.append(f'{ind}KfcGen.setScore(sb, "{h}", "{o}", {var});')
                    elif op == "+=":
                        out.append(f'{ind}KfcGen.addScore(sb, "{h}", "{o}", {var});')
                    elif op == "-=":
                        out.append(f'{ind}KfcGen.addScore(sb, "{h}", "{o}", -({var}));')
                    else:
                        # 원형 유지가 안전 — 소스 flush 후 원줄
                        flush_key(skey, ind)
                        out.append(ln)
                        continue
                else:
                    out.append(ln)
            continue
        if kind == "opn":
            ind, h, o, op, nlit = (m.group(1), m.group(2), m.group(3), m.group(4), m.group(5))
            key = (h, o)
            if key in hot:
                if key in active:
                    var = active[key][0]
                    if op == "=":
                        out.append(f'{ind}{var} = {nlit};')
                    elif op in ("+=", "-=", "*="):
                        out.append(f'{ind}{var} {op} {nlit};')
                    elif op == "/=":
                        out.append(f'{ind}{var} = KfcGen.sdiv({var}, {nlit});')
                    elif op == "%=":
                        out.append(f'{ind}{var} = KfcGen.smod({var}, {nlit});')
                    elif op == "<":
                        out.append(f'{ind}{var} = Math.min({var}, {nlit});')
                    else:
                        out.append(f'{ind}{var} = Math.max({var}, {nlit});')
                else:
                    var_seq[0] += 1
                    var = f'_kd{var_seq[0]}'
                    active[key] = (var, True)
                    base = f'KfcGen.getScore(sb, "{h}", "{o}")'
                    if op == "=":
                        out.append(f'{ind}int {var} = {nlit};')
                    elif op in ("+=", "-=", "*="):
                        out.append(f'{ind}int {var} = {base} {op[0]} ({nlit});')
                    elif op == "/=":
                        out.append(f'{ind}int {var} = KfcGen.sdiv({base}, {nlit});')
                    elif op == "%=":
                        out.append(f'{ind}int {var} = KfcGen.smod({base}, {nlit});')
                    elif op == "<":
                        out.append(f'{ind}int {var} = Math.min({base}, {nlit});')
                    else:
                        out.append(f'{ind}int {var} = Math.max({base}, {nlit});')
                demoted += 1
            else:
                out.append(ln)
            continue
        # 순수 run 안의 기타 줄(다른 홀더 resetScore/opScore, CSE 선언 등):
        # 추적 키 읽기 치환 + 잔여 리터럴 가드 후 통과
        ln2 = subst_reads(ln)
        if "resetScore" in ln2:
            # reset 은 홀더 전체("obj" 생략)/와일드카드("*") 형태가 추적 키를 지울 수 있어
            # 리터럴 매칭으로는 부족 — 전체 flush 후 원줄(fail-closed).
            ind = ln2[:len(ln2) - len(ln2.lstrip())]
            for key in list(active.keys()):
                flush_key(key, ind)
            out.append(ln2)
            continue
        leftover_guard(ln2, None)
        out.append(ln2)

    # 구간 종료 — 전체 flush
    for key in list(active.keys()):
        flush_key(key)
    return out, demoted


def _is_neutral_line(line: str) -> bool:
    """빈 줄/주석 전용 줄 — 실행 코드가 없어 체인을 끊지 않고 그대로 통과시킨다.
       (emit 은 문장마다 원본 mcfunction 주석 + 빈 줄을 끼워 넣는다.)"""
    s = line.strip()
    return (not s) or s.startswith("//")


def _is_pure_line(line: str, safe_objs: set) -> bool:
    s = line.strip()
    if not s or s.startswith("//"):
        return False        # 중립 줄은 별도 처리(_is_neutral_line)
    if not _PURE_PREFIX.match(line):
        return False
    kind, _ = _line_kind(line, safe_objs)
    if kind is not None:
        return True
    # 강등 키 외의 점수 문장/단순 CSE 선언 — 다른 KfcGen 호출이 섞이지 않은 한 줄이어야 함
    stripped = _strip_strings_line(line)
    if stripped.count("(") != stripped.count(")"):
        return False        # 여러 줄로 감싼 문장 — 배리어
    calls = re.findall(r'KfcGen\.(\w+)\(', stripped)
    return all(c in ("setScore", "addScore", "opScore", "opScoreN", "resetScore",
                     "getScore", "readScore", "scoreMatches", "sdiv", "smod")
               for c in calls) and bool(calls)


def demote_scores(records: list, lines_map: dict, verbose: bool = True) -> dict:
    if not DEMOTE_SCORES or not lines_map:
        return {"objs": 0, "demoted": 0}
    safe_objs = demotable_objectives(lines_map)
    if not safe_objs:
        return {"objs": 0, "demoted": 0}
    var_seq = [0]
    total = 0
    changed_records = 0
    for c in records:
        if '"#' not in c.text:
            continue
        if not any(f'"{o}"' in c.text for o in safe_objs):
            continue
        lines = c.text.split("\n")
        out = []
        seg = []
        rec_n = 0
        for ln in lines:
            if _is_pure_line(ln, safe_objs) or (seg and _is_neutral_line(ln)):
                seg.append(ln)
                continue
            if seg:
                new_seg, n = _demote_segment(seg, safe_objs, var_seq)
                out.extend(new_seg)
                rec_n += n
                seg = []
            out.append(ln)
        if seg:
            new_seg, n = _demote_segment(seg, safe_objs, var_seq)
            out.extend(new_seg)
            rec_n += n
        if rec_n:
            c.text = "\n".join(out)
            c.size = len(c.text.encode("utf-8"))
            total += rec_n
            changed_records += 1
    if verbose:
        print(f"  [score-demote] safe objectives {len(safe_objs)}, "
              f"{total} ops demoted in {changed_records} records")
    return {"objs": len(safe_objs), "demoted": total}
