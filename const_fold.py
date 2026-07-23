#!/usr/bin/env python3
"""
const_fold.py — [pass-2.8] 상수 스코어 폴딩.

데이터팩은 상수(#kart360 kartmain = 360 등)를 가짜플레이어 점수로 두고 매 틱
`scoreboard players operation … += #kart360 kartmain` 처럼 읽는다(레지스터가 그것뿐이라).
수제 포팅은 이를 자바 int 상수로 박제했지만 원본이 바뀌면 수동 동기화가 필요했다 —
이 패스는 그 박제를 '전 팩 라인 분석으로 증명된 키'에 한해 자동화한다.

폴딩 조건 (전부 만족해야 함 — fail-closed):
  · 홀더가 '#' 가짜플레이어 (플레이어/엔티티가 이름 충돌로 만질 수 없는 내부 상수 관례).
  · 그 (홀더, objective) 키에 대한 팩 전체의 쓰기가 `scoreboard players set <h> <o> <리터럴>`
    뿐이고, 그 리터럴이 전부 동일한 값 N (조건부든 아니든 — 어차피 항상 N 이 쓰임).
  · add/remove/reset/enable/operation-대상/store-result·success 대상 어디에도 등장 안 함.
  · objective 는 `scoreboard objectives add <o> dummy` 로만 생성되고(게임 이벤트로 변하는
    criteria 금지) `objectives remove <o>` 가 팩 어디에도 없음(언인스톨 등 제거 시나리오 배제).
  · 홀더 전체 reset(`players reset <h>` objective 생략형)·와일드카드(*) 없음.
  · 매크로($) 줄도 동일 규칙으로 스캔. 점수 쓰기 위치(홀더/objective)에 $() 토큰이
    있으면 그 템플릿($() → 임의 문자열)에 매칭될 수 있는 '모든' 키를 패턴 실격 처리
    (예: `set #track-anime-$(track) track-anime 1` → #track-anime-* 전부 실격).

시맨틱 논증: 위 조건이면 그 키는 "최초 set 이후 영원히 N, set 이전엔 미설정".
load 태그가 첫 틱 이전에 실행되므로(바닐라 보장) 게임플레이 중 읽기는 항상 N 을 본다.
운영자가 수동 /scoreboard 로 #홀더를 바꾸는 경우만 원본과 달라지는데, 이는 수제 포팅
(KartConst)과 동일한 노출이며 데이터팩 내부 상수 관례상 실사용 간섭이 없다.
(우려 시 FOLD_CONST_SCORES=False 로 즉시 비활성.)

재작성 (records 텍스트, 버킷화 이전):
  · scoreMatches(sb,"H","O",lo,hi)  → (N >= lo && N <= hi)   — set 이후 '반드시 설정' → 순수 비교
  · getScore(sb,"H","O")            → N
  · readScore(sb,"H","O")           → Integer.valueOf(N)      — CSE _sv 선언 호환
  · opScore(sb,D,DO,"=","H","O")    → setScore(sb,D,DO,N)
  · opScore(sb,D,DO,"+=","H","O")   → addScore(sb,D,DO,N)
  · opScore(sb,D,DO,"-=","H","O")   → addScore(sb,D,DO,-N)
    (opScore 의 소스 getOrCreateScore 부수효과는 '이미 존재+무변경'이라 관측 불변)
  그 외 연산(*,/,%,<,>,><)과 scoreCmp 는 원형 유지(여전히 ObjRef 캐시 경유).
"""
from __future__ import annotations
import re

FOLD_CONST_SCORES = True    # 회귀 시 즉시 차단용 토글

_H = r'#[\w.+-]+'
_O = r'[\w.+-]+'
_SET_LIT  = re.compile(rf'(?:^|run )scoreboard players set ({_H}) ({_O}) (-?\d+)$')
_SET_ANY  = re.compile(rf'(?:^|run )scoreboard players set (\S+) (\S+) (\S+)$')
_WRITE    = re.compile(r'(?:^|run )scoreboard players (add|remove|enable) (\S+) (\S+)')
_RESET    = re.compile(r'(?:^|run )scoreboard players reset (\S+)(?: (\S+))?\s*$')
_OP_DEST  = re.compile(r'(?:^|run )scoreboard players operation (\S+) (\S+) ')
_STORE    = re.compile(r'store (?:result|success) score (\S+) (\S+)')
_OBJ_ADD  = re.compile(rf'scoreboard objectives add ({_O}) (\S+)')
_OBJ_RM   = re.compile(rf'scoreboard objectives remove ({_O})')


def analyze(lines_map: dict) -> dict:
    """{fid: [lines]} → {(holder, obj): int} 폴딩 가능 상수 집합."""
    set_vals = {}          # key -> set(리터럴)
    disq_key = set()       # 실격 키
    disq_obj = set()       # 실격 objective
    disq_holder = set()    # 실격 홀더(전체 reset)
    obj_crit = {}          # obj -> set(criteria)
    wild = []              # 매크로 쓰기 템플릿 → (홀더 regex, obj regex) 패턴 실격
    global_off = False

    def _tmpl_rx(tok: str):
        """$() 토큰 포함 템플릿 → 정규식. `#a-$(x)` → `#a\-.*`. 실패 시 None."""
        parts = re.split(r"\$\([^)]*\)", tok)
        return re.compile("^" + ".*".join(re.escape(s) for s in parts) + "$")

    def _wild_write(holder: str, obj: str):
        nonlocal global_off
        try:
            wild.append((_tmpl_rx(holder), _tmpl_rx(obj)))
        except re.error:
            global_off = True

    for fid, lines in lines_map.items():
        for raw in lines:
            l = raw[1:].strip() if raw.startswith("$") else raw
            is_macro = raw.startswith("$")
            m = _OBJ_ADD.search(l)
            if m:
                obj_crit.setdefault(m.group(1), set()).add(m.group(2))
            m = _OBJ_RM.search(l)
            if m:
                disq_obj.add(m.group(1))
            m = _STORE.search(l)
            if m:
                if "$(" in m.group(1) + m.group(2):
                    _wild_write(m.group(1), m.group(2))
                disq_key.add((m.group(1), m.group(2)))
            m = _OP_DEST.search(l)
            if m:
                if "$(" in m.group(1) + m.group(2):
                    _wild_write(m.group(1), m.group(2))
                disq_key.add((m.group(1), m.group(2)))
            m = _WRITE.search(l)
            if m:
                if "$(" in m.group(2) + m.group(3):
                    _wild_write(m.group(2), m.group(3))
                disq_key.add((m.group(2), m.group(3)))
            m = _RESET.search(l)
            if m:
                if "$(" in m.group(1) + (m.group(2) or ""):
                    _wild_write(m.group(1), m.group(2) or "$()")
                if m.group(2) is None or m.group(2).startswith("*"):
                    disq_holder.add(m.group(1))     # 홀더 전체 reset
                if (m.group(1) or "").startswith("*"):
                    disq_obj.add(m.group(2) or "?")
                disq_key.add((m.group(1), m.group(2)))
            m = _SET_ANY.search(l)
            if m:
                lit = _SET_LIT.search(l)
                if lit:
                    set_vals.setdefault((lit.group(1), lit.group(2)), set()).add(lit.group(3))
                else:
                    # 비리터럴 값 set (매크로 값 등) — 그 키 실격, 위치 토큰에 $() 면 패턴 실격
                    if "$(" in m.group(1) + m.group(2):
                        _wild_write(m.group(1), m.group(2))
                    disq_key.add((m.group(1), m.group(2)))

    if global_off:
        return {}
    out = {}
    for k, vs in set_vals.items():
        h, o = k
        if len(vs) != 1:
            continue
        if k in disq_key or o in disq_obj or h in disq_holder:
            continue
        if obj_crit.get(o) != {"dummy"}:
            continue                     # dummy 이외 criteria(게임 이벤트 갱신) 금지
        if any(hr.match(h) and orx.match(o) for hr, orx in wild):
            continue                     # 매크로 쓰기 템플릿에 걸릴 수 있는 키
        out[k] = int(next(iter(vs)))
    return out


_INT = r'-?\d+|Integer\.MIN_VALUE|Integer\.MAX_VALUE'


def _compile_key(h: str, o: str, v: int) -> list:
    """키 하나의 (패턴, 치환) 6종 사전 컴파일 — 레코드 루프 밖에서 1회."""
    hq, oq = re.escape(f'"{h}"'), re.escape(f'"{o}"')

    def _lit(s: str) -> int:
        # _INT 토큰(정수 리터럴 / Integer.MIN_VALUE / MAX_VALUE)을 정수로.
        if s == "Integer.MIN_VALUE":
            return -2147483648
        if s == "Integer.MAX_VALUE":
            return 2147483647
        return int(s)

    def _sm(m, _v=v):
        # scoreMatches(sb,"H","O",lo,hi) — H·O 상수화 후 lo·hi 도 리터럴이면 값이 상수라
        # (lo <= v <= hi) 를 컴파일타임 평가해 true/false 리터럴로 접는다(scoreCmpL _both 와 동형).
        # 접힌 조건은 if (true)/(false) → JIT 이 죽은 분기를 제거(런타임 비교 0). 관측 동등.
        lo, hi = _lit(m.group(1)), _lit(m.group(2))
        return "true" if (lo <= _v <= hi) else "false"

    out = [
        # scoreMatches → 순수 범위비교(양측 상수면 true/false 리터럴로 폴딩)
        (re.compile(rf'KfcGen\.scoreMatches\(sb, {hq}, {oq}, ({_INT}), ({_INT})\)'),
         _sm),
        # getScore → 리터럴
        (re.compile(rf'KfcGen\.getScore\(sb, {hq}, {oq}\)'), f'{v}'),
        # readScore(CSE 선언) → 박싱 리터럴
        (re.compile(rf'KfcGen\.readScore\(sb, {hq}, {oq}\)'), f'Integer.valueOf({v})'),
    ]
    # opScore 소스 특수화: =, +=, -=  (대상 인자는 임의 식 — 중첩 괄호 없는 흔한 꼴만,
    # 매칭 실패 시 원형 유지 = fail-closed)
    for op, repl in (("=", f'KfcGen.setScore(sb, \\1, \\2, {v});'),
                     ("+=", f'KfcGen.addScore(sb, \\1, \\2, {v});'),
                     ("-=", f'KfcGen.addScore(sb, \\1, \\2, -({v}));')):
        out.append((re.compile(
            r'KfcGen\.opScore\(sb, ((?:[^,()]|\([^()]*\))+), ((?:[^,()]|\([^()]*\))+), '
            + re.escape(f'"{op}"') + rf', {hq}, {oq}\);'), repl))
    # 잔여 연산(*, /, %, <, >)의 상수 소스 → opScoreN(대상만 접근, 상수 직접 적용).
    # 소스 getOrCreateScore 부수효과는 '이미 존재+무변경' — 위 3종과 동일 논증.
    for op in ("*=", "/=", "%=", "<", ">"):
        out.append((re.compile(
            r'KfcGen\.opScore\(sb, ((?:[^,()]|\([^()]*\))+), ((?:[^,()]|\([^()]*\))+), '
            + re.escape(f'"{op}"') + rf', {hq}, {oq}\);'),
            rf'KfcGen.opScoreN(sb, \1, \2, "{op}", {v});'))
    # ── scoreCmp 확장(#3①): 폴딩 상수와의 비교를 직접 int 비교로 ──
    _OPS = r'(<=|>=|<|>|=)'
    # (a) 좌측 상수: scoreCmp(sb,"H","O","op", <나머지>) → scoreCmpL(sb, N, "op", <나머지>)
    #     나머지(문자열/엔티티/neg 조합)는 그대로 두면 scoreCmpL 오버로드가 해소한다.
    out.append((re.compile(rf'KfcGen\.scoreCmp\(sb, {hq}, {oq}, "{_OPS}", '),
                rf'KfcGen.scoreCmpL(sb, {v}, "\1", '))
    # (b) 우측 상수: scoreCmp(sb, A, B, "op", "H", "O"[, neg]) → scoreCmpR(sb, A, B, "op", N[, neg])
    _ARG = r'((?:[^,()]|\([^()]*\))+)'
    out.append((re.compile(
        rf'KfcGen\.scoreCmp\(sb, {_ARG}, {_ARG}, "{_OPS}", {hq}, {oq}\)'),
        rf'KfcGen.scoreCmpR(sb, \1, \2, "\3", {v})'))
    out.append((re.compile(
        rf'KfcGen\.scoreCmp\(sb, {_ARG}, {_ARG}, "{_OPS}", {hq}, {oq}, (true|false)\)'),
        rf'KfcGen.scoreCmpR(sb, \1, \2, "\3", {v}, \4)'))
    # (c) 양측 상수: (a) 적용 후 남은 scoreCmpL(sb, M, "op", "H", "O") → 순수 불리언 리터럴
    def _both(m, _v=v):
        a = int(m.group(1)); op2 = m.group(2)
        r = {"<": a < _v, "<=": a <= _v, "=": a == _v, ">=": a >= _v, ">": a > _v}[op2]
        return "true" if r else "false"
    out.append((re.compile(
        rf'KfcGen\.scoreCmpL\(sb, (-?\d+), "{_OPS}", {hq}, {oq}\)'), _both))
    return out


def _fold_text(text: str, consts: dict) -> tuple[str, int]:
    """단일 클래스 소스에 폴딩 적용. (새 텍스트, 치환수). (하위호환 API — 느린 경로)"""
    n = 0
    for (h, o), v in consts.items():
        for pat, repl in _compile_key(h, o, v):
            text, k = pat.subn(repl, text)
            n += k
    return text, n


def fold_const_scores(records: list, lines_map: dict, verbose: bool = True,
                      consts: dict | None = None) -> dict:
    """records 텍스트 제자리 폴딩. 반환 {"keys": n, "folded": n}.

    [성능] 종전엔 레코드마다 '키 48개 × 패턴 6개' 정규식이 전체 텍스트를 스캔했다
    (272MB 코드 × 288패스 ≈ 78GB 스캔 — kartall 실측 96.6s, 실제 폴딩은 39곳).
    폴딩 대상 호출은 반드시 `"#홀더"` 자바 문자열 리터럴을 포함하므로:
      1) `'"#'` 미포함 레코드는 즉시 스킵 (C 레벨 substring, 대부분 여기서 탈락)
      2) 남은 레코드도 해당 키의 `"홀더"` 리터럴이 있을 때만 그 키의 패턴 6종 적용
    적용 순서는 종전과 동일(consts 삽입 순서)이므로 산출물 바이트 동일."""
    if not FOLD_CONST_SCORES or not lines_map:
        return {"keys": 0, "folded": 0}
    if consts is None:
        consts = analyze(lines_map)   # (백그라운드 사전 계산분이 있으면 재사용)
    if not consts:
        return {"keys": 0, "folded": 0}
    compiled = [(f'"{h}"', _compile_key(h, o, v)) for (h, o), v in consts.items()]
    total = 0
    for c in records:
        t = c.text
        if '"#' not in t:               # 1) 홀더 리터럴 자체가 없음 → 폴딩 불가
            continue
        n = 0
        for hlit, pats in compiled:     # 2) 키별: 홀더 리터럴 있을 때만 정규식
            if hlit not in t:
                continue
            for pat, repl in pats:
                t, k = pat.subn(repl, t)
                n += k
        if n:
            c.text = t
            c.size = len(t.encode("utf-8"))
            total += n
    if verbose and total:
        top = sorted(consts.items())[:100]
        print(f"  [const-fold] {len(consts)} keys, {total} sites — "
              + ", ".join(f"{h} {o}={v}" for (h, o), v in top[:8])
              + (" …" if len(consts) > 8 else ""))
    return {"keys": len(consts), "folded": total}