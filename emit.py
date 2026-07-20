#!/usr/bin/env python3
"""
emit.py - brigadier 파스트리(JSON) -> 네이티브 자바 문장.

입력: ParseDumper 가 만든 trees.json (한 줄 = 한 객체).
출력: 각 줄에 대한 자바 코드 문자열 + 분류(native/bridge) + 사유.

설계 핵심
---------
brigadier 의 chain 은 [노드들 + 인자들] 이 평탄하게 섞여 있다. emit 을 위해
이를 의미 단위로 재구성한다:

  execute 커맨드  -> [수정자(as/at/if/on/positioned/store/...)] + run <타겟 커맨드>
  비-execute 커맨드 -> 그 자체가 타겟 커맨드 (scoreboard/data/tag/tp/...)

타겟 커맨드는 `emit_target()` 가, execute 수정자는 `emit_execute()` 가 처리한다.
네이티브화 불가(의미 이해 필요/미해소)면 BRIDGE 로 분류하고 사유를 단다.

이 파일은 마인크래프트가 필요 없다 - 순수 파이썬. fixtures/trees_sample.json 으로 단위 테스트.
"""
from __future__ import annotations
import json, re, sys
from datapack_io import open_datapack
from dataclasses import dataclass, field
from pathlib import Path

# 출력 패키지 루트 (convert.py 가 set_group 으로 주입). fqcn/패키지 일관성의 단일 소스.
GROUP = "kartriderpack"

def set_group(g):
    global GROUP
    GROUP = g


# 매크로 함수 집합 (호출부에서 Map<String,String> 인자를 넘겨야 하는 피호출 함수들).
# convert.py 의 [pass-1] 에서 set_macro_fns 로 주입. 빈 집합이면 모든 호출을 무인자로 본다.
MACRO_FNS: set = set()

_VAR_COUNTER = [0]

def reset_var_counter():
    """함수 단위 변수 카운터 리셋 — 같은 입력이면 같은 출력(결정적 산출물).
       변수 유일성은 '함수 내(자바 스코프)'만 필요하므로 함수 시작마다 0 으로 되돌려도 안전.
       결정적 출력이어야 '내용 불변 파일 미기록'이 성립해 gradle 증분 컴파일이 동작한다
       (반복 개발에서 빌드가 변경 버킷만 재컴파일 → 수 초)."""
    _VAR_COUNTER[0] = 0

def _fresh_var(prefix: str) -> str:
    """함수 내 임시변수 충돌 방지용 전역 유니크 이름."""
    _VAR_COUNTER[0] += 1
    return f"{prefix}_{_VAR_COUNTER[0]}"

def _uid() -> int:
    """전역 단조 증가 정수. kfcSrc<N>/_faceE<N> 등 '숫자 접미사' 형태를 유지하면서
       체인 간 번호 재사용으로 인한 동일-스코프 변수 중복 선언을 원천 차단한다.
       (kfcSrcN 형태를 유지하므로 하위 정규식 매칭/마지막-소스 추적은 그대로 동작.)"""
    _VAR_COUNTER[0] += 1
    return _VAR_COUNTER[0]


def _nonnull_simplify(s: str, var: str) -> str:
    """`var` 가 non-null 임이 구조적으로 보장된 문맥(enhanced-for 루프 변수, instanceof 패턴
    바인딩, `if (var != null)` 가드 내부)에서 방출 자바의 죽은 널체크를 제거한다.
    순수 단순화 — 관측 시맨틱 불변, 목적은 메서드 바이트코드 축소(JIT 인라이닝 예산)와 분기 제거:
      · `(var != null ? A : B)` / `((var != null) ? A : B)`  →  `A`
      · `var != null && X`                                    →  `X`
      · `if (var != null) STMT`                               →  `STMT`
    따옴표 상태를 추적해 문자열 리터럴 내부는 절대 건드리지 않는다."""
    v = re.escape(var)
    # 1) 삼항 제거 — 괄호/따옴표 인지 스캐너로 A 부분만 추출
    tern = re.compile(r'\(\(?' + v + r' != null\)? \? ')
    while True:
        m = tern.search(s)
        if m is None:
            break
        # m.start() 의 '(' 부터 균형 스캔: depth1 에서 만나는 '?' 다음 ':' 위치와 닫는 ')' 위치
        i = m.end()          # A 시작
        depth = 1            # 여는 '(' 하나 소비된 상태
        in_str = False; esc = False
        qdepth = 0           # depth1 에서의 중첩 삼항 카운터(외곽 '?' 는 정규식이 이미 소비)
        colon = -1; close = -1
        j = i
        while j < len(s):
            c = s[j]
            if in_str:
                if esc: esc = False
                elif c == '\\': esc = True
                elif c == '"': in_str = False
            else:
                if c == '"': in_str = True
                elif c == '(': depth += 1
                elif c == ')':
                    depth -= 1
                    if depth == 0:
                        close = j
                        break
                elif c == '?' and depth == 1:
                    qdepth += 1
                elif c == ':' and depth == 1:
                    if qdepth > 0:
                        qdepth -= 1
                    elif colon < 0:
                        colon = j
            j += 1
        if colon < 0 or close < 0:
            break   # 형태 불일치 — 건드리지 않음(보수)
        a_part = s[i:colon].strip()
        s = s[:m.start()] + a_part + s[close + 1:]
    # 2) `var != null && ` 접두 제거 (문자열 밖에서만 — 방출 코드의 이 패턴은 코드 위치에만 등장)
    s = re.sub(r'\(' + v + r' != null && ', '(', s)
    s = re.sub(v + r' != null && ', '', s)
    # 3) `if (var != null) ` 문장 가드 제거
    s = re.sub(r'if \(' + v + r' != null\) ', '', s)
    return s


def _nonnull_simplify_conds(conds: list, var: str) -> list:
    """조건식 리스트 버전 — 각 항목 단순화 후, `var != null` 자체였던 죽은 항목은 제거."""
    out = []
    for c in conds:
        c2 = _nonnull_simplify(c, var)
        cc = c2.strip()
        if cc in (f'{var} != null', f'({var} != null)'):
            continue   # 루프 변수 non-null — 항상 참인 조건은 통째 제거
        out.append(c2)
    return out


ALL_FIDS = set()
def set_all_fids(s):
    """변환되는 모든 함수 id 집합 주입(동적 function 디스패치 후보 열거용)."""
    global ALL_FIDS
    ALL_FIDS = set(s)

def set_macro_fns(s):
    global MACRO_FNS
    MACRO_FNS = set(s)


# ───────────────────────── 결과 구조 ─────────────────────────
@dataclass
class Emitted:
    line: str
    java: list[str] = field(default_factory=list)   # 생성된 자바 문장(들)
    kind: str = "native"                              # native | gated | bridge
    reason: str = ""                                  # bridge/gated 사유
    rejects_function: bool = False                    # True면 이 줄이 함수 전체를 무효화(mcfunction 파싱거부 재현)
    macro_params: list = field(default_factory=list)  # 이 줄이 쓰는 매크로 변수명(시그니처 결정용)
    side_effects: list = field(default_factory=list)  # 값 식 평가 전에 먼저 실행할 부수효과 문장
    macro_guard: list = field(default_factory=list)   # store/return 값이 매크로함수 호출일 때, 빈 매크로변수면 줄 스킵용 가드식(들)
    terminal: bool = False                            # True면 이 줄이 무조건 함수를 종료(top-level return). 이후 줄은 도달불가(바닐라 동치) → 드롭.

    def bridge(self, reason: str) -> "Emitted":
        # 자바->바닐라 디스패처 호출은 최적화 이득이 없어 최종 산출에서 쓰지 않는다.
        # 이 줄을 포함한 함수는 통째로 instantExecuteFunction(원본 mcfunction) 폴백으로 간다.
        # 여기선 표식(dispatch)만 남긴다 - 본문 자바는 폴백 시 참고용 주석으로만 쓰임.
        self.reason = reason
        self.java = [f'// ⛔ 자바 변환 불가(함수 폴백): {reason}']
        self.kind = "dispatch"
        return self

    def reject(self, reason: str) -> "Emitted":
        """무효 명령 - mcfunction 에서는 이 함수 전체가 로드 거부됨. 함수 전체 비활성 신호."""
        self.kind = "bridge"
        self.reason = reason
        self.rejects_function = True
        self.java = [f'// [INVALID COMMAND - 원본에서 함수 전체 거부됨] {self.line}']
        return self

    def line_for_bridge(self) -> str:
        # 매크로($) 줄은 $ 포함 원문 그대로 (런타임 매크로 치환은 mcfunction 호출로 처리해야 하므로
        #  사실 줄 단위가 아닌 함수 단위 브릿지가 옳다 - 여기선 표식만)
        return self.line


# ───────────────────────── 헬퍼 ─────────────────────────
def _scbound(v, sentinel):
    """점수 매칭 bound → 항상 primitive int 식으로.
       None=개구간 센티넬(Integer.MIN/MAX_VALUE), 숫자 리터럴=그대로,
       그 외(매크로 런타임 식, 예: macroArgs.get("x"))=Integer.parseInt(...) 로 파싱.
       → scoreMatches(...,int,int) 단일 오버로드로 박싱 없이 매칭."""
    if v is None:
        return sentinel
    sv = str(v)
    return sv if sv.lstrip("-").isdigit() else f"KfcGen.macroI({sv})"


_JSTR_LIMIT = 55000   # 클래스파일 CONSTANT_Utf8 한계(65535B) 대비 안전 여유
_JSTR_CHUNK = 24000   # 분할 청크(원문 기준 — 이스케이프/UTF-8 팽창을 감안해도 한계 미만)

def _jesc(s: str) -> str:
    return s.replace("\\", "\\\\").replace('"', '\\"')

def jstr(s: str) -> str:
    """자바 문자열 리터럴. 거대 문자열(모델링 summon SNBT 등)은 클래스파일 상수 한계
       (CONSTANT_Utf8 = 65535 바이트)를 넘어 javac 가 실패하므로, 원문을 청크로 나눠
       KfcGen.cat("p1","p2",…) 런타임 연결식으로 방출한다. ("a"+"b" 는 컴파일타임
       상수 폴딩으로 다시 한 상수가 되어 소용없음 — 메서드 연결이 필요.)
       청크 경계는 MACROVAR_n 토큰을 자르지 않도록 보정(매크로 후처리 치환 보호).
       짧은 문자열(사실상 전부)은 기존과 동일한 단일 리터럴 — case 라벨 등 상수 문맥 안전."""
    esc = _jesc(s)
    if len(esc.encode("utf-8")) <= _JSTR_LIMIT:
        return '"' + esc + '"'
    parts = []
    i, n = 0, len(s)
    while i < n:
        j = min(n, i + _JSTR_CHUNK)
        if j < n:
            k = s.rfind("MACROVAR_", max(i, j - 16), j)
            if k != -1:
                mm = re.match(r'MACROVAR_\d+', s[k:])
                if mm and k + len(mm.group(0)) > j:
                    j = k              # 토큰 시작 앞에서 절단
        parts.append(s[i:j]); i = j
    return "KfcGen.cat(" + ", ".join('"' + _jesc(p) + '"' for p in parts) + ")"


def _jnum(tok, parse_fn, suffix=""):
    """수치 토큰 -> 자바 수치식. MACROVAR_n 포함 시 런타임 파싱식 생성.
       '0.5' -> '0.5f' / 'MACROVAR_0' -> 'Float.parseFloat(MACROVAR_0)'
       '-MACROVAR_0' -> '(-Float.parseFloat(MACROVAR_0))'
       'aMACROVAR_0b' -> 'Float.parseFloat("a" + MACROVAR_0 + "b")' (문자열 연결)"""
    t = str(tok)
    if "MACROVAR_" not in t:
        return None  # 호출측이 기존 경로(float()/int())로
    mm = re.fullmatch(r'(-?)(MACROVAR_\d+)', t)
    if mm:
        core = f'{parse_fn}({mm.group(2)})'
        return f'(-{core})' if mm.group(1) else core
    parts = [p for p in re.split(r'(MACROVAR_\d+)', t) if p]
    expr = " + ".join(p if p.startswith("MACROVAR_") else jstr(p) for p in parts)
    return f'{parse_fn}({expr})'


def jfloat(tok):
    e = _jnum(tok, "KfcGen.macroF")
    return e if e is not None else f'{float(tok)}f'


def jdouble(tok):
    e = _jnum(tok, "KfcGen.macroD")
    return e if e is not None else repr(float(tok))


def jdouble_g(tok):
    """셀렉터 수치필드(박스 dx/dy/dz, 원점 x/y/z)용 '가드된' double 식.
       이 값들은 조건식(posInBox/anyEntityInBox) 안에서 평가되므로, 매크로 변수를
       unguarded Double.parseDouble 로 두면 (a) raw String 이 그대로 새어 컴파일 에러이거나
       (b) 비수치 매크로에서 NumberFormatException 이 함수 밖으로 튄다. KfcGen.coord(s,false)
       는 비수치/null 이면 MACRO_FAIL 을 던져(=바닐라: 매크로 인스턴스화 실패 → 줄 스킵),
       함수의 catch(MacroParseFail) 로 안전하게 잡힌다. 리터럴은 숫자 그대로."""
    e = _jnum(str(tok), "KfcGen.macroD")
    return e if e is not None else repr(float(tok))


def jint(tok):
    e = _jnum(tok, "KfcGen.macroI")
    return e if e is not None else str(int(tok))



def parse_range(raw: str) -> tuple[str | None, str | None]:
    """'8000..' -> ('8000', None), '..5' -> (None,'5'), '3' -> ('3','3'), '1..9'->('1','9')"""
    if ".." in raw:
        lo, hi = raw.split("..", 1)
        return (lo or None, hi or None)
    return (raw, raw)


def _dist_arg(v) -> str:
    """distance min/max 토큰 -> 자바 double 식.
       None -> '-1'(무제한), MACROVAR 포함 -> Double.parseDouble(...) 런타임 파싱, 숫자 -> 그대로.
       (매크로 함수에서 distance=..$(range) 의 $(range) 가 macroArgs.get(..)=String 으로 들어와도
        숫자 파라미터에 맞게 파싱되도록 보장.)"""
    if v is None:
        return "-1"
    e = _jnum(str(v), "KfcGen.macroD")
    return e if e is not None else str(v)


def _int_arg(v, default: str) -> str:
    """정수 셀렉터 필드(level/limit 등) 토큰 -> 자바 int 식.
       None -> default, MACROVAR 포함 -> Integer.parseInt(...) 런타임 파싱, 숫자 -> 그대로."""
    if v is None:
        return default
    e = _jnum(str(v), "KfcGen.macroI")
    return e if e is not None else str(v)


def holder_expr(raw: str) -> str:
    """스코어 홀더 -> 자바 holder name 식.
       '@s' -> 실행자 이름 ; '@...' -> None(셀렉터) ; '*' -> None(전체) ;
       그 외('#foo'/'lowdetail'/플레이어명) -> 리터럴 홀더 이름."""
    if raw == "@s":
        # 서버 소스(executor 없음)에서 @s 줄은 원본에서 '명령 실패=무동작'.
        # 식 컨텍스트에선 매치 불가능한 홀더명으로 안전하게 무력화.
        return '(executor == null ? "<no-executor>" : executor.getNameForScoreboard())'
    if raw.startswith("@") or raw == "*":
        return None  # 셀렉터/전체 홀더 - 호출부에서 처리
    return jstr(raw)  # #foo / 평문 가짜플레이어(lowdetail 등) / 플레이어명 -> 리터럴 홀더


def self_holder_guard(raw: str):
    """홀더 raw -> (자바 holder식, guard조건|None). @s / @s[...] / #foo 만 처리.
       그 외 셀렉터(@e/@n/...)는 None 반환(루프 필요)."""
    if raw == "@s":
        return ("executor.getNameForScoreboard()", "executor != null")
    if raw.startswith("#"):
        return (jstr(raw), None)
    if raw.startswith("@s[") or raw.startswith("@s "):
        sel = parse_selector(raw)
        if sel is None:
            return None
        cond = selector_cond(sel)        # base 's' -> 실행자 검사식
        if cond is None:
            return None
        guard = "executor != null" if cond == "true" else f"executor != null && ({cond})"
        return ("executor.getNameForScoreboard()", guard)
    # @ 로 시작하지 않는 토큰은 가짜 플레이어(literal score holder). 바닐라 ScoreHolderArgument 는
    # 셀렉터(@..)가 아니면 임의 이름을 그대로 점수 홀더로 허용한다(#foo 와 동일 취급).
    if not raw.startswith("@"):
        return (jstr(raw), None)
    return None


# ───────────────────────── 셀렉터 raw 파싱 ─────────────────────────
# brigadier 가 selector 를 raw 로만 줘서, 여기서 분해한다. (ParseDumper 보강 전 임시지만
#  selector 문법은 단순/안정적이라 raw 파싱으로 충분히 정확하다.)
@dataclass
class Selector:
    base: str                      # a/e/p/r/s/n
    tags_pos: list = field(default_factory=list)   # 요구 태그
    tags_neg: list = field(default_factory=list)   # 배제 태그
    type_id: str | None = None     # type= (단일 또는 #tag), '!' 접두 제거
    type_neg: bool = False
    type_is_tag: bool = False      # #로 시작했나
    limit: int | None = None
    distance: tuple | None = None  # (min,max)
    volume: tuple | None = None    # (dx,dy,dz) 박스 셀렉터
    origin: tuple | None = None    # (x,y,z) 박스 절대 원점
    sort: str | None = None
    scores: dict = field(default_factory=dict)
    predicates: list = field(default_factory=list)
    gamemode: str | None = None    # gamemode= (플레이어 셀렉터)
    gamemode_neg: bool = False
    team: tuple | None = None      # (이름, 부정) team=red / team=!red / team= (무소속)
    name: tuple | None = None      # (이름, 부정) name=Steve / name=!Steve
    level: tuple | None = None     # (min,max) 경험치 레벨 (플레이어)
    advancements: list = field(default_factory=list)  # (advId, bool | {crit:bool})
    nbt: list = field(default_factory=list)            # (nbt문자열, 부정)
    x_rotation: tuple | None = None  # (min,max) 피치 범위
    y_rotation: tuple | None = None  # (min,max) yaw 범위 (래핑 없는 min<=max 만 지원)
    raw: str = ""


def box_origin_expr(sel, src_var: str) -> str:
    """박스 셀렉터의 원점 Vec3d 식. 절대좌표(x/y/z) 있으면 그걸, 없으면 소스 위치."""
    if sel.origin is not None:
        x, y, z = sel.origin
        return f"new net.minecraft.util.math.Vec3d({x}, {y}, {z})"
    return f"{src_var}.getPosition()"


def _volume_cond(sel, var: str, src_var: str = "source") -> str | None:
    """볼륨 셀렉터(@x[x,y,z,dx,dy,dz])의 posInBox 조건식. 볼륨 없으면 None.
       모든 셀렉터 소비 경로(루프 가드/존재검사/명령 대상)에서 박스 필터가 누락되지
       않도록 공통 사용한다. 누락 시 박스 밖 대상까지 명령이 적용돼 바닐라와 어긋난다."""
    if sel.volume is None:
        return None
    dx, dy, dz = sel.volume
    return f'KfcGen.posInBox({box_origin_expr(sel, src_var)}, {dx}, {dy}, {dz}, {var})'


def _split_commas_depth0(s: str) -> list:
    out, depth, buf = [], 0, ""
    for ch in s:
        if ch in "[{": depth += 1; buf += ch
        elif ch in "]}": depth -= 1; buf += ch
        elif ch == "," and depth == 0:
            if buf.strip(): out.append(buf.strip())
            buf = ""
        else: buf += ch
    if buf.strip(): out.append(buf.strip())
    return out


def parse_advancements(v: str) -> list:
    """advancements={ns:path=true, ns:p2={crit=true}} -> [(advId, bool|{crit:bool}), ...]"""
    v = v.strip()
    if v.startswith("{") and v.endswith("}"):
        v = v[1:-1]
    out = []
    for item in _split_commas_depth0(v):
        if "=" not in item:
            continue
        key, val = item.split("=", 1)
        key, val = key.strip(), val.strip()
        if val.startswith("{") and val.endswith("}"):
            crits = {}
            for c in _split_commas_depth0(val[1:-1]):
                if "=" in c:
                    ck, cv = c.split("=", 1)
                    crits[ck.strip()] = (cv.strip() == "true")
            out.append((key, crits))
        else:
            out.append((key, val == "true"))
    return out


def parse_selector(raw: str) -> Selector | None:
    m = re.match(r'^@([aeprsn])(?:\[(.*)\])?$', raw.strip())
    if not m:
        return None
    sel = Selector(base=m.group(1), raw=raw)
    body = m.group(2)
    if not body:
        return sel
    for k, v in split_selector_args(body):
        if k == "tag":
            (sel.tags_neg if v.startswith("!") else sel.tags_pos).append(v.lstrip("!"))
        elif k == "type":
            neg = v.startswith("!")
            v2 = v.lstrip("!")
            sel.type_neg = neg
            sel.type_is_tag = v2.startswith("#")
            sel.type_id = v2.lstrip("#")
        elif k == "limit":
            sel.limit = int(v)
        elif k == "distance":
            sel.distance = parse_range(v)
        elif k in ("dx", "dy", "dz"):
            if sel.volume is None:
                sel.volume = {"dx": "0", "dy": "0", "dz": "0"}
            sel.volume[k] = v
        elif k in ("x", "y", "z"):
            if not isinstance(sel.origin, dict):
                sel.origin = {}
            sel.origin[k] = v
        elif k == "sort":
            sel.sort = v
        elif k == "scores":
            sel.scores = parse_scores(v)
        elif k == "predicate":
            sel.predicates.append(v)
        elif k == "gamemode":
            sel.gamemode_neg = v.startswith("!")
            sel.gamemode = v.lstrip("!")
        elif k == "x_rotation":
            sel.x_rotation = parse_range(v)
        elif k == "y_rotation":
            sel.y_rotation = parse_range(v)
        elif k == "team":
            sel.team = (_unquote_sel(v.lstrip("!")), v.startswith("!"))
        elif k == "name":
            sel.name = (_unquote_sel(v.lstrip("!")), v.startswith("!"))
        elif k == "level":
            sel.level = parse_range(v)
        elif k == "advancements":
            sel.advancements = parse_advancements(v)
        elif k == "nbt":
            sel.nbt.append((v.lstrip("!"), v.startswith("!")))
        else:
            # 미인식 필터 키: 조용히 무시하면 셀렉터 의미가 넓어진 채 native 인정되는
            # 의미 왜곡이 생긴다(예: gamemode 누락으로 관전자 전용 태그가 전원에게).
            # 파싱 실패로 처리해 해당 줄을 거부/폴백시킨다 - 정확성 우선.
            return None
    if isinstance(sel.volume, dict):
        sel.volume = (jdouble_g(sel.volume["dx"]), jdouble_g(sel.volume["dy"]), jdouble_g(sel.volume["dz"]))
    if isinstance(sel.origin, dict):
        if not all(a in sel.origin for a in ("x", "y", "z")):
            return None  # 부분 좌표 원점 1차 미지원
        sel.origin = (jdouble_g(sel.origin["x"]), jdouble_g(sel.origin["y"]), jdouble_g(sel.origin["z"]))
    return sel


def split_selector_args(body: str):
    """'tag=a,tag=!b,type=#x,scores={o=1..}' -> [(k,v),...]. 중괄호/대괄호 균형 고려."""
    out, depth, buf = [], 0, ""
    for ch in body:
        if ch in "[{":
            depth += 1; buf += ch
        elif ch in "]}":
            depth -= 1; buf += ch
        elif ch == "," and depth == 0:
            if buf.strip():
                out.append(buf)
            buf = ""
        else:
            buf += ch
    if buf.strip():
        out.append(buf)
    res = []
    for item in out:
        if "=" in item:
            k, v = item.split("=", 1)
            res.append((k.strip(), v.strip()))
    return res


def parse_scores(v: str) -> dict:
    # '{obj=1..,o2=..5}' -> {'obj':(1,None),'o2':(None,5)}
    v = v.strip().lstrip("{").rstrip("}")
    out = {}
    for k, val in split_selector_args(v):
        out[k] = parse_range(val)
    return out


# 엔티티 type id -> 자바 EntityType 상수 (1차: 흔한 것 + #tag 는 KARTMODELS 류로 근사)
ENTITY_TYPE_JAVA = {
    "minecraft:text_display": "EntityType.TEXT_DISPLAY",
    "minecraft:item_display": "EntityType.ITEM_DISPLAY",
    "minecraft:block_display": "EntityType.BLOCK_DISPLAY",
    "minecraft:area_effect_cloud": "EntityType.AREA_EFFECT_CLOUD",
    "item_display": "EntityType.ITEM_DISPLAY",
    "text_display": "EntityType.TEXT_DISPLAY",
    "block_display": "EntityType.BLOCK_DISPLAY",
    "area_effect_cloud": "EntityType.AREA_EFFECT_CLOUD",
}


# 바닐라 1.21.5 EntityType 정적 상수명(JAR 에서 추출). 임의 minecraft:<path> -> EntityType.<UPPER>
# 매핑이 안전한지(상수가 실제 존재하는지) 판정하는 데 쓴다.
VANILLA_ENTITY_TYPES = {
    'ACACIA_BOAT', 'ACACIA_CHEST_BOAT', 'ALLAY', 'AREA_EFFECT_CLOUD', 'ARMADILLO',
    'ARMOR_STAND', 'ARROW', 'AXOLOTL', 'BAMBOO_CHEST_RAFT', 'BAMBOO_RAFT', 'BAT', 'BEE',
    'BIRCH_BOAT', 'BIRCH_CHEST_BOAT', 'BLAZE', 'BLOCK_DISPLAY', 'BOGGED', 'BREEZE',
    'BREEZE_WIND_CHARGE', 'CAMEL', 'CAT', 'CAVE_SPIDER', 'CHERRY_BOAT', 'CHERRY_CHEST_BOAT',
    'CHEST_MINECART', 'CHICKEN', 'COD', 'COMMAND_BLOCK_MINECART', 'COW', 'CREAKING', 'CREEPER',
    'DARK_OAK_BOAT', 'DARK_OAK_CHEST_BOAT', 'DOLPHIN', 'DONKEY', 'DRAGON_FIREBALL', 'DROWNED',
    'EGG', 'ELDER_GUARDIAN', 'ENDERMAN', 'ENDERMITE', 'ENDER_DRAGON', 'ENDER_PEARL',
    'END_CRYSTAL', 'EVOKER', 'EVOKER_FANGS', 'EXPERIENCE_BOTTLE', 'EXPERIENCE_ORB',
    'EYE_OF_ENDER', 'FALLING_BLOCK', 'FIREBALL', 'FIREWORK_ROCKET', 'FISHING_BOBBER', 'FOX',
    'FROG', 'FURNACE_MINECART', 'GHAST', 'GIANT', 'GLOW_ITEM_FRAME', 'GLOW_SQUID', 'GOAT',
    'GUARDIAN', 'HOGLIN', 'HOPPER_MINECART', 'HORSE', 'HUSK', 'ILLUSIONER', 'INTERACTION',
    'IRON_GOLEM', 'ITEM', 'ITEM_DISPLAY', 'ITEM_FRAME', 'JUNGLE_BOAT', 'JUNGLE_CHEST_BOAT',
    'LEASH_KNOT', 'LIGHTNING_BOLT', 'LINGERING_POTION', 'LLAMA', 'LLAMA_SPIT', 'MAGMA_CUBE',
    'MANGROVE_BOAT', 'MANGROVE_CHEST_BOAT', 'MARKER', 'MINECART', 'MOOSHROOM', 'MULE',
    'OAK_BOAT', 'OAK_CHEST_BOAT', 'OCELOT', 'OMINOUS_ITEM_SPAWNER', 'PAINTING', 'PALE_OAK_BOAT',
    'PALE_OAK_CHEST_BOAT', 'PANDA', 'PARROT', 'PHANTOM', 'PIG', 'PIGLIN', 'PIGLIN_BRUTE',
    'PILLAGER', 'PLAYER', 'POLAR_BEAR', 'PUFFERFISH', 'RABBIT', 'RAVAGER', 'SALMON', 'SHEEP',
    'SHULKER', 'SHULKER_BULLET', 'SILVERFISH', 'SKELETON', 'SKELETON_HORSE', 'SLIME',
    'SMALL_FIREBALL', 'SNIFFER', 'SNOWBALL', 'SNOW_GOLEM', 'SPAWNER_MINECART', 'SPECTRAL_ARROW',
    'SPIDER', 'SPLASH_POTION', 'SPRUCE_BOAT', 'SPRUCE_CHEST_BOAT', 'SQUID', 'STRAY', 'STRIDER',
    'TADPOLE', 'TEXT_DISPLAY', 'TNT', 'TNT_MINECART', 'TRADER_LLAMA', 'TRIDENT',
    'TROPICAL_FISH', 'TURTLE', 'VEX', 'VILLAGER', 'VINDICATOR', 'WANDERING_TRADER', 'WARDEN',
    'WIND_CHARGE', 'WITCH', 'WITHER', 'WITHER_SKELETON', 'WITHER_SKULL', 'WOLF', 'ZOGLIN',
    'ZOMBIE', 'ZOMBIE_HORSE', 'ZOMBIE_VILLAGER', 'ZOMBIFIED_PIGLIN'
}

def entity_type_java(type_id: str) -> str | None:
    """엔티티 타입 id -> 자바 EntityType 상수식. minecraft 네임스페이스의 모든 바닐라 타입 지원.
       명시 매핑(ENTITY_TYPE_JAVA) 우선, 그 외엔 path 를 대문자화해 EntityType.<UPPER>.
       존재하지 않는 상수는 생성하지 않는다(모드/커스텀 타입 -> None -> 브릿지)."""
    if type_id in ENTITY_TYPE_JAVA:
        return ENTITY_TYPE_JAVA[type_id]
    ns, _, path = type_id.partition(":")
    if path == "":
        ns, path = "minecraft", ns
    if ns != "minecraft":
        return None
    const = path.upper()
    if const in VANILLA_ENTITY_TYPES:
        return f"EntityType.{const}"
    return None

# 엔티티 타입 태그(#ns:path) -> 실제 타입 id 리스트. 데이터팩 tags/entity_type/*.json 에서 로드.
# 도메인 가정 없이, 입력 데이터팩의 정의를 그대로 읽는다(범용).
ENTITY_TYPE_TAGS: dict[str, list[str]] = {}

PREDICATES: dict[str, str] = {}   # "kartmobil:ifride" -> 자바 boolean 식 템플릿({E}=대상 엔티티)
# 대상이 이미 ServerPlayerEntity 로 확정된 컨텍스트(플레이어 루프 람다 등)용 변형:
# instanceof 패턴 바인딩 없이 {P} 를 직접 사용 (같은 줄 다중 바인딩 충돌 방지).
PREDICATES_PLAYER: dict[str, str] = {}


def compile_predicate_json(j):
    """predicate JSON -> (일반식, 플레이어컨텍스트식). 둘 다 {E}/{P} 템플릿.
       플레이어컨텍스트식은 대상이 이미 ServerPlayerEntity 인 람다 안에서 쓰며
       instanceof 바인딩이 없어 같은 줄 다중 사용 시 충돌하지 않는다. 미지원이면 None."""
    cond = j.get("condition")
    if cond == "minecraft:random_chance":
        e = f'(ctx.world.random.nextFloat() < {j.get("chance", 0)}f)'
        return (e, e)
    if cond == "minecraft:entity_properties" and j.get("entity") == "this":
        p = j.get("predicate", {})
        if p == {"vehicle": {"passenger": {}}}:
            e = '({E} != null && {E}.hasVehicle())'
            return (e, e.replace("{E}", "{P}"))
        if set(p.keys()) == {"passenger"} and p["passenger"].get("type") == "minecraft:player":
            e = 'KfcGen.hasPlayerPassenger({E})'
            return (e, e.replace("{E}", "{P}"))
        inp = (p.get("type_specific") or {}).get("input")
        if p.get("type") == "minecraft:player" and inp and len(inp) == 1:
            key, val = next(iter(inp.items()))
            if val is True and key in ("forward", "backward", "left", "right", "jump", "sneak", "sprint"):
                gen = ('({E} instanceof net.minecraft.server.network.ServerPlayerEntity _kp'
                       f' && _kp.getPlayerInput().{key}())')
                ply = f'{{P}}.getPlayerInput().{key}()'
                return (gen, ply)
    return None


def load_predicates(datapack_root):
    """data/<ns>/predicate/**/*.json -> PREDICATES / PREDICATES_PLAYER (컴파일 가능한 것만).
       datapack_root 는 디렉터리/zip 경로 또는 이미 열린 소스."""
    src = datapack_root if hasattr(datapack_root, "glob") else open_datapack(datapack_root)
    for rel in src.glob("data/*/predicate/**/*.json"):
        parts = rel.split("/")
        ns = parts[parts.index("data") + 1]
        pi = parts.index("predicate")
        sub = "/".join(parts[pi + 1:]).removesuffix(".json")
        try:
            j = json.loads(src.read_text(rel, encoding="utf-8-sig").replace("\r", ""))
        except Exception:
            continue
        res = compile_predicate_json(j)
        if res is not None:
            gen, ply = res
            PREDICATES[f"{ns}:{sub}"] = gen
            if ply is not None:
                PREDICATES_PLAYER[f"{ns}:{sub}"] = ply


BLOCK_TAGS: dict[str, list] = {}   # "kartmobil:ignoreblock" -> ["minecraft:stone", ...]

def load_block_tags(datapack_root):
    """data/<ns>/tags/block/<name>.json -> BLOCK_TAGS. 중첩(#) 재귀 해소."""
    src = datapack_root if hasattr(datapack_root, "glob") else open_datapack(datapack_root)
    raw = {}
    for rel in src.glob("data/*/tags/block/**/*.json"):
        parts = rel.split("/")
        ns = parts[parts.index("data") + 1]
        bi = parts.index("block")
        sub = "/".join(parts[bi + 1:]).removesuffix(".json")
        try:
            raw[f"{ns}:{sub}"] = json.loads(src.read_text(rel, encoding="utf-8")).get("values", [])
        except Exception:
            continue
    def resolve(tid, seen=None):
        seen = seen or set()
        if tid in seen: return []
        seen.add(tid)
        out = []
        for v in raw.get(tid, []):
            vid = v if isinstance(v, str) else v.get("id", "")
            if vid.startswith("#"):
                out += resolve(vid[1:], seen)
            else:
                out.append(vid if ":" in vid else "minecraft:" + vid)
        return out
    for tid in raw:
        BLOCK_TAGS[tid] = sorted(set(resolve(tid)))


def load_entity_type_tags(datapack_root):
    """data/<ns>/tags/entity_type/<name>.json 들을 읽어 ENTITY_TYPE_TAGS 채움.
       #으로 시작하는 중첩 태그 참조도 재귀 해소."""
    src = datapack_root if hasattr(datapack_root, "glob") else open_datapack(datapack_root)
    raw = {}
    for rel in src.glob("data/*/tags/entity_type/**/*.json"):
        parts = rel.split("/")
        ns = parts[parts.index("data") + 1]
        ei = parts.index("entity_type")
        sub = "/".join(parts[ei + 1:]).removesuffix(".json")
        tag_id = f"{ns}:{sub}"
        try:
            data = json.loads(src.read_text(rel, encoding="utf-8"))
        except Exception:
            continue
        raw[tag_id] = data.get("values", [])

    def resolve(tag_id, seen=None):
        seen = seen or set()
        if tag_id in seen:
            return []
        seen.add(tag_id)
        out = []
        for v in raw.get(tag_id, []):
            entry = v["id"] if isinstance(v, dict) else v
            if entry.startswith("#"):
                out += resolve(entry[1:], seen)
            else:
                out.append(entry)
        return out

    for tag_id in raw:
        ENTITY_TYPE_TAGS[tag_id] = resolve(tag_id)


def resolve_entity_types(sel: "Selector") -> list[str] | None:
    """셀렉터 type 을 자바 EntityType 상수 리스트로. 해소 실패 시 None."""
    if not sel.type_id:
        return None
    if sel.type_is_tag:
        ids = ENTITY_TYPE_TAGS.get(sel.type_id)
        if not ids:
            return None
        out = []
        for i in ids:
            j = entity_type_java(i)
            if not j:
                return None
            out.append(j)
        return out
    j = entity_type_java(sel.type_id)
    return [j] if j else None


def java_str_array(items: list[str]) -> str:
    if not items:
        return "KfcGen.NO_TAGS"   # 공유 빈 배열 — 호출당 new String[0] 할당 제거
    return "new String[]{" + ", ".join(jstr(t) for t in items) + "}"


def at_effect_cond(raw: str) -> str | None:
    """@a/@p/@s[...,nbt={active_effects:[{id:"ns:eff"}]}] -> '해당 효과 가진 매칭 대상 존재' boolean.
       active_effects 외 nbt 가 섞이면 None(정확성)."""
    m = re.match(r'@([apers])\[(.*)\]$', raw.strip())
    if not m:
        return None
    base, inner = m.group(1), m.group(2)
    em_ = re.search(r'active_effects:\[\{id:"([^"]+)"\}', inner)
    if not em_:
        return None
    effect = em_.group(1)
    # active_effects nbt 제거 후 남은 필터(tags/scores)만 허용
    rest = re.sub(r'nbt=\{active_effects:\[[^\]]*\]\}', '', inner)
    sel = parse_selector('@' + base + '[' + rest.strip(' ,') + ']' if rest.strip(' ,') else '@' + base)
    if sel is None or sel.predicates or sel.type_id or sel.distance is not None or _sel_has_extra(sel):
        return None
    guards = [f'KfcGen.hasEffect(_pp, {jstr(effect)})']
    guards += _tag_conds(sel, '_pp')
    guards += _score_conds(sel, '_pp')
    if base == "s":
        return ('(executor instanceof net.minecraft.server.network.ServerPlayerEntity _pp && '
                + " && ".join(guards) + ')')
    return f'KfcGen.anyPlayerWhere(ctx, _pp -> ({" && ".join(guards)}))'


def at_s_selecteditem_cond(raw: str) -> str | None:
    """@s[nbt={SelectedItemSlot:N}] (+scores={...}) -> 자바 boolean. 그 외 필터 섞이면 None.
       SelectedItemSlot = 선택된 핫바 슬롯(0-8). getSelectedSlot() 와 1:1."""
    m = re.match(r'@s\[(.*)\]$', raw.strip())
    if not m:
        return None
    inner = m.group(1)
    sm = re.search(r'SelectedItemSlot:\s*(\d+)', inner)
    if not sm:
        return None
    slot = int(sm.group(1))
    parts = ['executor instanceof net.minecraft.entity.player.PlayerEntity',
             f'((net.minecraft.entity.player.PlayerEntity)executor).getInventory().getSelectedSlot() == {slot}']
    scm = re.search(r'scores=\{([^}]*)\}', inner)
    if scm:
        for pair in scm.group(1).split(','):
            pair = pair.strip()
            if not pair:
                continue
            mm = re.match(r'([\w.\-+]+)=(.+)', pair)
            if not mm:
                return None
            obj, rng = mm.group(1), mm.group(2)
            lo, hi = parse_range(rng)
            lo_j = _scbound(lo, "Integer.MIN_VALUE")
            hi_j = _scbound(hi, "Integer.MAX_VALUE")
            parts.append(f'KfcGen.scoreMatches(sb, executor.getNameForScoreboard(), {jstr(obj)}, {lo_j}, {hi_j})')
    # SelectedItemSlot nbt + scores 외 다른 필터가 있으면 처리 불가(정확성 우선)
    leftover = re.sub(r'nbt=\{[^}]*\}', '', inner)
    leftover = re.sub(r'scores=\{[^}]*\}', '', leftover)
    if leftover.strip(' ,'):
        return None
    return '(executor != null && ' + ' && '.join(parts) + ')'


def _guard_cost(c: str) -> int:
    """가드 식의 평가 비용 추정(단락 평가에서 싼 가드를 앞으로). 전부 순수(부작용 없음)
       AND 결합이라 재배열해도 결과 boolean 동일 — 빠른 early-exit 만 얻는다."""
    if ('entityMatchesNbt' in c or 'nbtMatches' in c
            or 'Advancement' in c or 'hasAdvancement' in c or 'advancementDone' in c):
        return 5   # writeNbt 전체 직렬화 / advancement 조회 — 가장 비쌈
    if 'predicateMatches' in c or 'testPredicate' in c or 'KfcGen.predicate' in c:
        return 4   # loot predicate 평가
    if 'scoreMatches' in c:
        return 3   # 스코어보드 조회
    if 'posInRange' in c or 'posInBox' in c or 'squaredDistanceTo' in c:
        return 2   # 기하 계산
    if 'gamemodeIs' in c:
        return 1
    return 0       # getType()== / getCommandTags().contains / 단순 비교 — 가장 쌈


def _order_guards(conds: list) -> list:
    """가드 리스트를 비용 오름차순으로 stable 정렬(원래 상대순서 보존).
       싼 가드(type/tag) 앞, 비싼 가드(predicate/nbt) 뒤 → early-exit 빈도↑.
       모든 가드가 순수 boolean 이라 동작 동치."""
    return sorted(conds, key=_guard_cost)


def _selector_type_cond(sel, evar: str):
    """셀렉터 type 필터를 단일 후보 evar 의 boolean 조건으로. (조건문자열 | None).
       반환값 None 은 '타입 필터 없음'(조건 불필요). 해소 불가면 ('__FAIL__',) 튜플.
       - 바닐라 타입(또는 데이터팩 로드된 타입태그) -> 컴파일타임 == 비교
       - 미로딩 커스텀 타입태그 -> 런타임 entityInTypeTag
       - 커스텀 단일 타입(모드 엔티티) -> 런타임 entityTypeIs (레지스트리 조회)
    """
    if not sel.type_id and not sel.type_is_tag:
        return None
    types = resolve_entity_types(sel)
    if types and None not in types:
        inner = " || ".join(f"{evar}.getType() == {t}" for t in types)
        cond = f"({inner})" if len(types) > 1 else inner
        return f"!({cond})" if sel.type_neg else cond
    # 컴파일타임 미해소
    if sel.type_is_tag:
        e = f"KfcGen.entityInTypeTag({evar}, {jstr(sel.type_id)})"
        return f"!({e})" if sel.type_neg else e
    # 커스텀 단일 타입 id(모드 엔티티) -> 런타임 레지스트리 비교
    if sel.type_id:
        tid = sel.type_id if ":" in sel.type_id else "minecraft:" + sel.type_id
        e = f"KfcGen.entityTypeIs({evar}, {jstr(tid)})"
        return f"!({e})" if sel.type_neg else e
    return ("__FAIL__",)


def _selector_entity_guards(sel, evar: str, src_var: str = "source", player: bool = False):
    """단일 후보 엔티티 evar 에 대한 모든 셀렉터 필터를 bare boolean 조건 리스트로.
       존재검사(if entity)·루프 가드 양쪽에서 공유하는 범용 빌더.
       해소 불가능한 제약이 하나라도 있으면 None(->폴백).
    """
    conds = []
    # 타입
    tc = _selector_type_cond(sel, evar)
    if tc is not None:
        if isinstance(tc, tuple):
            return None
        conds.append(tc)
    # 태그
    conds += _tag_conds(sel, evar)
    # 박스(dx/dy/dz)
    if sel.volume is not None:
        dx, dy, dz = sel.volume
        conds.append(f'KfcGen.posInBox({box_origin_expr(sel, src_var)}, {dx}, {dy}, {dz}, {evar})')
    # 거리
    if sel.distance is not None:
        lo, hi = sel.distance
        conds.append(f'KfcGen.posInRange({src_var}.getPosition(), {evar}.getPos(), '
                     f'{_dist_arg(lo)}, {_dist_arg(hi)})')
    # 회전(x_rotation/y_rotation)은 _selector_extra_conds 에서 일괄 추가(중복 방지).
    # 게임모드
    if sel.gamemode is not None:
        ge = f'KfcGen.gamemodeIs({evar}, {jstr(sel.gamemode)})'
        conds.append(f'!({ge})' if sel.gamemode_neg else ge)
    # 점수
    conds += _score_conds(sel, evar)
    # predicate (런타임 testPredicate 폴백 포함)
    if sel.predicates:
        pg = predicate_guards(sel.predicates, evar, player=player, src_var=src_var)
        if pg is None:
            return None
        conds += pg
    # team/name/level/nbt/advancements
    conds += _selector_extra_conds(sel, evar)
    return _order_guards(conds)


def _selector_cond_general(sel, src_var: str = "source") -> str | None:
    """selector_cond 의 범용 폴백: 특화 fast-path 가 못 푼 셀렉터를
       단일후보 가드 람다로 일반화해 존재검사 boolean 식을 만든다.
       @s 는 executor 직접검사, @a/@p/@r 는 anyPlayerWhere, @e/@n 는 anyEntityWhere.
    """
    is_player = sel.base in ("a", "p", "r")
    if sel.base == "s":
        guards = _selector_entity_guards(sel, "executor", src_var, player=False)
        if guards is None:
            return None
        if not guards:
            return "(executor != null)"
        return "(executor != null && " + " && ".join(guards) + ")"
    evar = "_se"
    guards = _selector_entity_guards(sel, evar, src_var, player=is_player)
    if guards is None:
        return None
    body = " && ".join(guards) if guards else "true"
    if is_player:
        return f'KfcGen.anyPlayerWhere(ctx, {evar} -> ({body}))'
    return f'KfcGen.anyEntityWhere(ctx, {evar} -> ({body}))'


def selector_cond(sel: "Selector", src_var: str = "source") -> str | None:
    """`if entity <selector>` 를 자바 boolean 식으로. 불가하면 None(->브릿지).

    @s          -> 실행자 자신이 태그/타입 조건을 만족하는지 직접 검사.
    @e/@n       -> 타입별 KfcGen.anyEntity 존재 검사(OR).
    @a/@p/@r    -> KfcGen.anyPlayer 존재 검사.
    src_var: distance= 필터의 기준 위치 소스 변수 (positioned 등 리바인드 뒤에서는
             리바인드된 소스를 넘겨야 함 - 수정자 체인 순서 보존).

    특화 fast-path 가 못 푸는 조합(predicate+type, 커스텀 타입태그, nbt/team/level 등)은
    _selector_cond_general 로 일반화한다(런타임 헬퍼 사용 - 정확성 유지).

    회전(x_rotation/y_rotation)·부정타입(type=!X)은 단순 fast-path 가 조건을 빠뜨리거나
    (회전 누락) 양성 타입으로 오역(type_neg)하므로, 무조건 범용 경로로 보낸다.
    범용 경로의 _selector_entity_guards/_selector_type_cond 가 둘 다 정확히 처리한다.
    """
    if (_sel_has_extra(sel) or sel.x_rotation is not None or sel.y_rotation is not None
            or sel.type_neg):
        return _selector_cond_general(sel, src_var)
    if (sel.volume is not None and sel.base in ("e", "n")
            and not sel.scores and not sel.predicates and not sel.type_neg):
        dx, dy, dz = sel.volume
        if sel.type_id or sel.type_is_tag:
            types = resolve_entity_types(sel) if sel.type_is_tag else [entity_type_java(sel.type_id)]
            if not types or None in types:
                return None
            arr = "new net.minecraft.entity.EntityType<?>[]{" + ", ".join(types) + "}"
        else:
            arr = "null"
        return (f'KfcGen.anyEntityInBox(ctx, {box_origin_expr(sel, src_var)}, {arr}, '
                f'{jarr_tags(sel.tags_pos)}, {jarr_tags(sel.tags_neg)}, {dx}, {dy}, {dz})')
    if (sel.volume is not None and sel.base in ("a", "p", "r")
            and not sel.scores and not sel.predicates):
        dx, dy, dz = sel.volume
        pc = _tag_conds(sel, '_pe')
        pc.append(f'KfcGen.posInBox({box_origin_expr(sel, src_var)}, {dx}, {dy}, {dz}, _pe)')
        if sel.gamemode is not None:
            ge = f'KfcGen.gamemodeIs(_pe, {jstr(sel.gamemode)})'
            pc.append(f'!({ge})' if sel.gamemode_neg else ge)
        return f'KfcGen.anyPlayerWhere(ctx, _pe -> ({" && ".join(pc)}))' 
    if sel.scores and sel.base != "s":
        # @e/@n + scores 1개: anyEntityScored 로 지원 (rectangle-hitbox/calc 의 #crashed 판정 등).
        if sel.base in ("e", "n") and len(sel.scores) == 1 and not sel.predicates and sel.volume is None:
            jt = resolve_entity_types(sel)
            if jt is None:
                return _selector_cond_general(sel, src_var)
            lo4, hi4 = sel.distance if sel.distance else (None, None)
            d4lo = "-1" if lo4 is None else str(lo4)
            d4hi = "-1" if hi4 is None else str(hi4)
            (o4, (slo4, shi4)), = sel.scores.items()
            slo_j = "null" if slo4 is None else f"Integer.valueOf({slo4})"
            shi_j = "null" if shi4 is None else f"Integer.valueOf({shi4})"
            arr4 = "new net.minecraft.entity.EntityType<?>[]{" + ", ".join(jt) + "}"
            return (f'KfcGen.anyEntityScored(ctx, {src_var}.getPosition(), {arr4}, '
                    f'{jarr_tags(sel.tags_pos)}, {jarr_tags(sel.tags_neg)}, {d4lo}, {d4hi}, '
                    f'sb, {jstr(o4)}, {slo_j}, {shi_j})')
        # @a/@p/@r + scores(+tag/distance): 아래 플레이어 존재검사에서 점수까지 처리.
        if sel.base in ("a", "p", "r") and not sel.predicates:
            pass
        else:
            return _selector_cond_general(sel, src_var)  # @e/@n 다중 scores / predicate 동반
    if sel.base == "s":
        parts = []
        for pid in sel.predicates:
            neg_p = pid.startswith("!")
            key = pid[1:] if neg_p else pid
            expr = PREDICATES.get(key)
            if expr is None:
                # 컴파일타임 JSON 부재 -> 런타임 LootCondition 평가.
                # 위치·문맥 의존 술어를 위해 재바인딩 소스(src_var)로 평가(다른 분기의
                # src_var.getPosition() 사용과 일관; bare source 면 positioned/at 뒤 어긋남).
                pid_norm = key if ":" in key else "minecraft:" + key
                e = f'KfcGen.testPredicate({src_var}, executor, {jstr(pid_norm)})'
            else:
                e = expr.replace("{E}", "executor")
            parts.append(f'!({e})' if neg_p else e)
        if sel.type_id:
            jt = resolve_entity_types(sel)
            if jt is None:
                return _selector_cond_general(sel, src_var)
            inner = " || ".join(f"executor.getType() == {t}" for t in jt)
            if sel.type_neg:
                parts.append(f"!({inner})")
            else:
                parts.append(f"({inner})" if len(jt) > 1 else inner)
        for t in sel.tags_pos:
            parts.append(f'executor.getCommandTags().contains({jstr(t)})')
        for t in sel.tags_neg:
            parts.append(f'!executor.getCommandTags().contains({jstr(t)})')
        parts += _score_conds(sel, 'executor')
        rc = rotation_conds(sel, "executor")
        if rc is None:
            return _selector_cond_general(sel, src_var)
        parts.extend(rc)
        if sel.distance is not None:
            lo, hi = sel.distance
            parts.append(f'KfcGen.posInRange({src_var}.getPosition(), executor.getPos(), '
                         f'{_dist_arg(lo)}, {_dist_arg(hi)})')
        _vc = _volume_cond(sel, "executor", src_var)
        if _vc: parts.append(_vc)
        if not parts:
            return "(executor != null)"
        return "(executor != null && " + " && ".join(parts) + ")"

    # 존재 검사 (@e/@n/@a/@p/@r)
    if sel.predicates:
        # predicate 동반 플레이어 존재검사 -> 인라인 루프(predicate 를 조건식으로)
        if sel.base in ("a", "p", "r") and not sel.type_id:
            pexprs = []
            ok_precompiled = True
            for _pi, pid in enumerate(sel.predicates):
                neg = pid.startswith("!")
                key = pid[1:] if neg else pid
                # 일반식(instanceof 가드 포함)만 사용 — _pe 가 ServerPlayerEntity 임이 보장되어도
                # 가드 없는 player 전용 호출을 코드베이스에서 완전히 배제(잠재 위험 원천 차단).
                ex = PREDICATES.get(key)
                if ex is None:
                    ok_precompiled = False
                    break
                e2 = ex.replace("{P}", "_pe").replace("{E}", "_pe").replace("_kp", f"_kp{_pi}")
                pexprs.append(f"!({e2})" if neg else e2)
            if not ok_precompiled:
                # 컴파일타임 predicate JSON 부재 -> 범용 런타임 폴백(testPredicate)
                return _selector_cond_general(sel, src_var)
            tagconds = _tag_conds(sel, '_pe')
            _vc = _volume_cond(sel, "_pe", src_var)
            if _vc: tagconds.append(_vc)
            lo3, hi3 = sel.distance if sel.distance else (None, None)
            if lo3 is not None or hi3 is not None:
                tagconds.append(f'KfcGen.posInRange({src_var}.getPosition(), _pe.getPos(), '
                                f'{_dist_arg(lo3)}, {_dist_arg(hi3)})')
            if sel.gamemode is not None:
                ge = f'KfcGen.gamemodeIs(_pe, {jstr(sel.gamemode)})'
                tagconds.append(f'!({ge})' if sel.gamemode_neg else ge)
            allc = " && ".join(tagconds + pexprs) if (tagconds or pexprs) else "true"
            return f'KfcGen.anyPlayerWhere(ctx, _pe -> ({allc}))'
        return _selector_cond_general(sel, src_var)  # @e/@n+predicate 등 -> 범용 런타임
    if sel.base in ("e", "n") and not sel.type_id:
        # 타입 미지정 - 전 엔티티 순회 존재검사
        lo2, hi2 = sel.distance if sel.distance else (None, None)
        tp = jarr_tags(sel.tags_pos); tn = jarr_tags(sel.tags_neg)
        return (f'KfcGen.anyEntityAnyType(ctx, {src_var}.getPosition(), {tp}, {tn}, '
                f'{_dist_arg(lo2)}, {_dist_arg(hi2)})')
    lo = hi = "-1"
    if sel.distance is not None:
        dlo, dhi = sel.distance
        lo = _dist_arg(dlo)
        hi = _dist_arg(dhi)
    tp = java_str_array(sel.tags_pos)
    tn = java_str_array(sel.tags_neg)
    if sel.base in ("a", "p", "r"):
        # 태그/거리만이면 단순 anyPlayer, 점수/게임모드가 섞이면 anyPlayerWhere 람다로 일반화.
        if sel.gamemode is not None or sel.scores:
            pc = _tag_conds(sel, '_pe')
            if sel.distance is not None:
                dl, dh = sel.distance
                pc.append(f'KfcGen.posInRange({src_var}.getPosition(), _pe.getPos(), '
                          f'{_dist_arg(dl)}, {_dist_arg(dh)})')
            _vc = _volume_cond(sel, "_pe", src_var)
            if _vc: pc.append(_vc)
            if sel.gamemode is not None:
                ge = f'KfcGen.gamemodeIs(_pe, {jstr(sel.gamemode)})'
                pc.append(f'!({ge})' if sel.gamemode_neg else ge)
            pc += _score_conds(sel, '_pe')
            return f'KfcGen.anyPlayerWhere(ctx, _pe -> ({" && ".join(pc) if pc else "true"}))'
        return f'KfcGen.anyPlayer(ctx, {src_var}.getPosition(), {tp}, {tn}, {lo}, {hi})'
    jt = resolve_entity_types(sel)
    if jt is None:
        # 컴파일타임 타입태그 JSON 부재 -> 런타임 EntityType.isIn(#tag) 존재검사(양성 단일 타입태그).
        if sel.type_is_tag and not sel.type_neg and not sel.scores:
            return (f'KfcGen.anyEntityInTypeTag(ctx, {src_var}.getPosition(), {jstr(sel.type_id)}, '
                    f'{tp}, {tn}, {lo}, {hi})')
        return _selector_cond_general(sel, src_var)
    calls = [f'KfcGen.anyEntity(ctx, {src_var}.getPosition(), {t}, {tp}, {tn}, {lo}, {hi})' for t in jt]
    return "(" + " || ".join(calls) + ")" if len(calls) > 1 else calls[0]


# ───────────────────────── 체인 재구성 ─────────────────────────
def split_chain(chain: list[dict]):
    """평탄 체인을 노드 시퀀스와 인자맵으로 분리.
       반환: (nodes:[name...], args:{name:[value,...]})  - 같은 인자명이 여러 번이면 순서 보존 리스트."""
    nodes, args = [], {}
    for step in chain:
        if "node" in step:
            nodes.append(step)
        else:  # arg step
            args.setdefault(step["arg"], []).append(step["value"])
    return nodes, args


# ───────────────────────── 타겟 커맨드 emit ─────────────────────────
def _dynamic_dispatch(fid: str, em: "Emitted") -> "list[str] | None":
    """`function <대상>` 의 이름에 매크로 변수(MACROVAR_i)가 들어간 동적 호출을,
       후보 함수들에 대한 switch 디스패치로 네이티브 생성(폴백 아님).
       대상의 정확히 한 세그먼트(네임스페이스 또는 path 구성요소)가 통째로
       MACROVAR_i 인 경우를 지원한다(예: `$(bgm):play`). 그 외(부분 세그먼트/
       다중 변수)는 None 을 돌려준다(상위에서 별도 처리)."""
    if "$(" in fid:
        em.reason = "동적 function: 매크로 토큰 미정규화"
        return None
    if ":" not in fid or "MACROVAR_" not in fid:
        em.reason = "동적 function: 네임스페이스(:) 없음" if ":" not in fid else "동적 function: 매크로 없음"
        return None
    # [일반화] 대상 이름의 임의 개수/부분 세그먼트 매크로를 지원한다.
    #   패턴을 정규식(매크로=한 경로컴포넌트 [^/:]+)으로 만들어 후보 함수를 열거하고,
    #   런타임에 전체 경로 문자열을 매크로로 조립(_dp)한 뒤 그 값으로 switch → 네이티브 callee.
    #   예: tower:spawn/model/$(model)/lv$(level)
    #       _dp = "tower:spawn/model/" + macroArgs.get("model") + "/lv" + macroArgs.get("level")
    #   null 매크로면 "...null..."/빈문자로 조립돼 어느 case 와도 불일치 → default no-op
    #   (바닐라: 인자 미제공/무효 경로 = 그 줄 무효). switch(_dp) 는 _dp 가 항상 non-null 이라 안전.
    parts = [p for p in re.split(r'(MACROVAR_\d+)', fid) if p != ""]
    rx = re.compile("^" + "".join(
        ("[^/:]+" if p.startswith("MACROVAR_") else re.escape(p)) for p in parts) + "$")
    cands = [cf for cf in ALL_FIDS
             if ":" in cf and rx.match(cf) and cf not in MACRO_FNS]
    if not cands:
        em.reason = "동적 function: 호출가능 후보 없음(레지스트리 미주입/매칭 0)"
        return None
    cands = sorted(set(cands))
    path_expr = " + ".join(p if p.startswith("MACROVAR_") else jstr(p) for p in parts)
    lines = [f"{{ String _dp = {path_expr};", "  switch (_dp) {"]
    for cf in cands:
        lines.append(f"    case {jstr(cf)}: {fqcn(cf)}.executeReturn(source); break;")
    lines.append("    default: break;")   # 매칭 함수 없음(미존재/무효 경로) = no-op
    lines.append("  } }")
    em.kind = "native"
    em.reason = f"동적 function 디스패치({len(cands)}개 후보, {sum(1 for p in parts if p.startswith('MACROVAR_'))}변수)"
    return lines


def _external_fn_call(fid: str, args: dict, nn: list[str], em: Emitted) -> list[str]:
    """입력 데이터팩에 본문이 없는(외부 namespace) 함수 호출 → instantExecuteFunction 으로
       원본 mcfunction 을 직접 실행한다. with <엔티티/스토리지/블록> 매크로 인자도 런타임에 전달."""
    ns, path = fid.split(":", 1) if ":" in fid else ("minecraft", fid)
    idexpr = f'net.minecraft.util.Identifier.of({jstr(ns)}, {jstr(path)})'
    if "with" in nn:
        wi = nn.index("with")
        kind = nn[wi + 1] if wi + 1 < len(nn) else None
        if kind == "storage":
            sid = first_arg(args, "source")
            mpath = first_arg(args, "path")
            margs = (f'KfcGen.storageMacroArgs(server, {jstr(sid)}, {jstr(mpath)})' if mpath
                     else f'KfcGen.storageMacroArgs(server, {jstr(sid)})')
            return [f'KfcGen.instantExecuteFunction(source, {idexpr}, {margs});']
        if kind == "entity":
            sel_raw = first_arg(args, "source")
            mpath = first_arg(args, "path") or ""
            if sel_raw == "@s":
                ent = "executor"
            else:
                ent = nearest_entity_java(parse_selector(sel_raw))
            if ent is not None:
                return [f'KfcGen.instantExecuteFunction(source, {idexpr}, '
                        f'KfcGen.entityMacroArgs({ent}, {jstr(mpath)}));']
            # 셀렉터 해소 실패 → 인자 없이라도 원본 실행(런타임이 @n 등을 재해석하진 못하나 최소 호출)
            return [f'KfcGen.instantExecuteFunction(source, {idexpr});']
        if kind == "block":
            pos = (first_arg(args, "pos") or first_arg(args, "position")
                   or first_arg(args, "targetPos"))
            mpath = first_arg(args, "path") or ""
            bp = block_pos_java(pos) if pos else None
            if bp is not None:
                return [f'KfcGen.instantExecuteFunction(source, {idexpr}, '
                        f'KfcGen.blockMacroArgs(ctx.world, {bp}, {jstr(mpath)}));']
            return [f'KfcGen.instantExecuteFunction(source, {idexpr});']
        # with {compound} 리터럴 등은 런타임 측에서 처리 불가 → 인자 없이 호출
        return [f'KfcGen.instantExecuteFunction(source, {idexpr});']
    # 인자 없는 단순 호출
    return [f'KfcGen.instantExecuteFunction(source, {idexpr});']


def _macro_args_expr(args: dict, nn: list[str]) -> tuple[str, list[str]] | None:
    """`function ... with <source>` / 인라인 `{compound}` 의 macroArgs(Map) 자바 식.
       반환 (margs_식, guard_vars). guard_vars 는 매크로변수(MACROVAR) 유래 값들 —
       빈 문자열이면 바닐라가 그 매크로 줄을 스킵하므로 호출측이 !macroEmpty 가드해야 한다.
       해소 불가(비-@s 엔티티/블록 형식 등) 면 None. (기존엔 인라인 compound 를 'null' 로
       반환해 매크로 함수에 null 을 넘겼고 → 본문 macroArgs.get NPE. store/return 경로 크래시.)"""
    if "with" in nn:
        wi = nn.index("with")
        kind = nn[wi + 1] if wi + 1 < len(nn) else None
        if kind == "storage":
            sid = first_arg(args, "source"); path = first_arg(args, "path")
            e = (f'KfcGen.storageMacroArgs(server, {jstr(sid)}, {jstr(path)})' if path
                 else f'KfcGen.storageMacroArgs(server, {jstr(sid)})')
            return (e, [])
        if kind == "entity":
            sel_raw = first_arg(args, "source"); path = first_arg(args, "path") or ""
            ent = "executor" if sel_raw == "@s" else nearest_entity_java(parse_selector(sel_raw))
            if ent is None:
                return None
            return (f'KfcGen.entityMacroArgs({ent}, {jstr(path)})', [])
        if kind == "block":
            pos = (first_arg(args, "pos") or first_arg(args, "position")
                   or first_arg(args, "targetPos"))
            path = first_arg(args, "path") or ""
            bp = block_pos_java(pos) if pos else None
            if bp is None:
                return None
            return (f'KfcGen.blockMacroArgs(ctx.world, {bp}, {jstr(path)})', [])
        return None
    # 인라인 {compound} — function_call_java 와 동일 규칙으로 macroArgs 를 구성한다.
    comp = first_arg(args, "arguments")
    if comp is not None:
        pairs = parse_compound_args(comp)
        kv = []; guards = []
        for k, v in pairs:
            kv.append(jstr(k))
            mv = macro_value_expr(v)
            kv.append(mv)
            if re.fullmatch(r'MACROVAR_\d+', v.strip()):
                guards.append(mv)
        return (f'KfcGen.macroArgs({", ".join(kv)})', guards)
    # 인자 없음 -> 빈 맵(무인자 매크로 호출; null 대신 빈 맵이라 NPE 없음).
    return ('KfcGen.macroArgs()', [])


def function_call_java(fid: str, args: dict, nn: list[str], em: Emitted) -> list[str] | None:
    """`function <fid> [with ...|{compound}]` -> 자바 호출문 리스트.
       피호출이 매크로 함수면 Map<String,String> 인자를 만들어 넘긴다.
       네이티브화 불가한 인자 소스(비-@s 엔티티/블록 NBT)면 None(->브릿지)."""
    if ("MACROVAR_" in fid) or ("$(" in fid):
        # 대상 이름에 매크로 변수 -> 런타임 동적 디스패치. 폴백이 아니라
        # 후보 함수들에 대한 switch 를 생성해 원본 흐름 그대로 네이티브화한다.
        return _dynamic_dispatch(fid, em)
    # 입력 데이터팩에 본문이 없는 함수(외부 namespace 의존 등)는 자바 클래스 호출 대신
    # 원본 mcfunction 을 instantExecuteFunction 으로 직접 실행한다. ALL_FIDS 가 비어 있으면
    # (단독 테스트 등) 이 판정을 건너뛴다(전수 변환 가정).
    if ALL_FIDS and fid not in ALL_FIDS:
        return _external_fn_call(fid, args, nn, em)
    call_cls = fqcn(fid)
    if fid not in MACRO_FNS:
        # 일반 함수: 인자가 붙어 있어도 mcfunction 은 무시 -> 무인자 호출.
        return [f'{call_cls}.executeReturn(source);']

    # 매크로 함수 -> 인자 소스 결정
    if "with" in nn:
        wi = nn.index("with")
        kind = nn[wi + 1] if wi + 1 < len(nn) else None
        if kind == "storage":
            sid = first_arg(args, "source")
            path = first_arg(args, "path")
            if path:
                return [f'{call_cls}.executeReturn(source, '
                        f'KfcGen.storageMacroArgs(server, {jstr(sid)}, {jstr(path)}));']
            return [f'{call_cls}.executeReturn(source, KfcGen.storageMacroArgs(server, {jstr(sid)}));']
        if kind == "entity":
            sel_raw = first_arg(args, "source")
            path = first_arg(args, "path") or ""
            if sel_raw == "@s":
                return [f'{call_cls}.executeReturn(source, '
                        f'KfcGen.entityMacroArgs(executor, {jstr(path)}));']
            ent = nearest_entity_java(parse_selector(sel_raw))
            if ent is None:
                em.reason = f"function with entity 셀렉터({sel_raw}) - 미지원"
                return None
            return [f'{call_cls}.executeReturn(source, '
                    f'KfcGen.entityMacroArgs({ent}, {jstr(path)}));']
        if kind == "block":
            pos = (first_arg(args, "pos") or first_arg(args, "position")
                   or first_arg(args, "targetPos"))
            path = first_arg(args, "path") or ""
            bp = block_pos_java(pos)
            if bp is None:
                em.reason = f"function with block 좌표({pos}) - 로컬(^)/형식 미지원"
                return None
            return [f'{call_cls}.executeReturn(source, '
                    f'KfcGen.blockMacroArgs(ctx.world, {bp}, {jstr(path)}));']
        em.reason = f"function with {kind} 소스 - 1차 미지원"
        return None

    # 인라인 {compound}
    comp = first_arg(args, "arguments")
    if comp is not None:
        pairs = parse_compound_args(comp)
        kv = []
        guard_vars = []  # 매크로 변수에서 온 값 → 빈 문자열이면 NBT 파싱 실패(호출 스킵)
        for k, v in pairs:
            kv.append(jstr(k))
            mv = macro_value_expr(v)
            kv.append(mv)
            # 값이 매크로 변수(MACROVAR_i)면, 치환 후 빈 문자열일 때 바닐라는
            # {key:} 가 되어 NBT 파싱 실패 → 매크로 줄 전체가 스킵된다(함수 미호출).
            # 변환도 동일하게: 해당 인자가 비어있으면 호출하지 않도록 가드.
            if re.fullmatch(r'MACROVAR_\d+', v.strip()):
                guard_vars.append(mv)
        call = f'{call_cls}.executeReturn(source, KfcGen.macroArgs({", ".join(kv)}));'
        if guard_vars:
            # null(인자 부재) 또는 빈 문자열이면 NBT 파싱 실패와 동일 → 호출 스킵.
            cond = " && ".join(f'!KfcGen.macroEmpty({g})' for g in guard_vars)
            return [f'if ({cond}) {call}']
        return [call]

    # 매크로 함수인데 인자 없음 -> 빈 맵 (mcfunction 에선 매크로 줄 도달 시 에러지만, 안전하게 빈 맵)
    return [f'{call_cls}.executeReturn(source, KfcGen.macroArgs());']


def parse_compound_args(raw: str) -> list[tuple[str, str]]:
    """SNBT compound '{k:v,k2:{...}}' -> [(k, v_text), ...]. 중괄호/대괄호/문자열 균형 고려."""
    raw = raw.strip()
    if raw.startswith("{"):
        raw = raw[1:]
    if raw.endswith("}"):
        raw = raw[:-1]
    out, depth, buf, instr = [], 0, "", False
    for ch in raw:
        if ch == '"':
            instr = not instr; buf += ch
        elif not instr and ch in "[{":
            depth += 1; buf += ch
        elif not instr and ch in "]}":
            depth -= 1; buf += ch
        elif not instr and ch == "," and depth == 0:
            if buf.strip():
                out.append(buf)
            buf = ""
        else:
            buf += ch
    if buf.strip():
        out.append(buf)
    pairs = []
    for item in out:
        if ":" in item:
            k, v = item.split(":", 1)
            pairs.append((k.strip().strip('"'), v.strip()))
    return pairs


def macro_value_expr(v: str) -> str:
    """인라인 compound 의 값 -> 자바 String 식.
       MACROVAR_<i> 더미는 그대로 둬서 emit_macro 가 macroArgs.get 으로 환원하게 한다.
       그 외 리터럴은 따옴표 벗겨 String 으로(매크로 인자는 항상 문자열)."""
    v = v.strip()
    if re.fullmatch(r'MACROVAR_\d+', v):
        return v
    if len(v) >= 2 and v[0] == '"' and v[-1] == '"':
        v = v[1:-1]
    return jstr(v)


def _want_nearest(sel: "Selector") -> bool:
    """거리순 정렬이 의미상 필요한 셀렉터인지. 바닐라 sort 기본값 규칙:
       · @n / @p 는 sort 미지정이라도 암묵적으로 nearest
       · @e / @a 는 sort 미지정 시 arbitrary(순서 무관)
       · 명시 sort=nearest 는 종류 무관하게 nearest
    이 경우에만 nearestN/nearestNAnyType 이 정렬을 수행해야 한다(LIMIT_SORT_NEAREST 토글과 무관히
    보호됨). 그 외 arbitrary 는 정렬을 생략해도 관측 동등이다."""
    return sel.sort == "nearest" or sel.base in ("n", "p")


def nearest_entity_java(sel: "Selector") -> str | None:
    """비-@s 엔티티 소스 셀렉터 -> '단일 엔티티' 자바 식(없으면 null 반환식). 불가 시 None.
       바닐라 sort 규칙(_want_nearest)에 따라: @n/@p/sort=nearest 는 최근접,
       @e/@a[limit=1](sort 미지정 = ARBITRARY)는 first-match(월드 순회 순서 첫 매치 —
       틱 간 안정적으로 같은 엔티티)로 해소한다. nearest 로 잘못 해소하면 소스 위치에 따라
       대상이 스위칭돼(logmain 모델 2대 번갈아 전진 등) 관측이 어긋난다."""
    if sel is None or sel.scores or getattr(sel, "predicates", None) or _sel_has_extra(sel):
        return None
    lo = hi = "-1"
    if sel.distance is not None:
        dlo, dhi = sel.distance
        lo = _dist_arg(dlo)
        hi = _dist_arg(dhi)
    tp = java_str_array(sel.tags_pos)
    tn = java_str_array(sel.tags_neg)
    near = _want_nearest(sel)
    if sel.base in ("a", "p", "r"):
        if sel.base == "r":
            # @r = 균등 랜덤(바닐라). 과거엔 nearest 로 해소해 '항상 최근접'이 되던 divergence.
            return f'KfcGen.randomPlayer(ctx, source.getPosition(), {tp}, {tn}, {lo}, {hi})'
        fnp = "nearestPlayer" if near else "firstPlayer"
        return f'KfcGen.{fnp}(ctx, source.getPosition(), {tp}, {tn}, {lo}, {hi})'
    jt = resolve_entity_types(sel)
    if jt is None:
        # 타입 미지정(@n[tag=...]/@e[tag=...]) -> 전 엔티티 대상
        if not getattr(sel, "type_id", None) and not getattr(sel, "type_is_tag", False):
            fne = "nearestEntityAnyType" if near else "firstEntityAnyType"
            return f'KfcGen.{fne}(ctx, source.getPosition(), {tp}, {tn}, {lo}, {hi})'
        return None
    arr = "new net.minecraft.entity.EntityType[]{" + ", ".join(jt) + "}"
    fnt = "nearestEntity" if near else "firstEntity"
    return f'KfcGen.{fnt}(ctx, source.getPosition(), {arr}, {tp}, {tn}, {lo}, {hi})'


def block_pos_java(raw: str | None) -> str | None:
    """블록 좌표 raw('~ ~ ~' / '0 64 0' / '~ ~-1 ~') -> BlockPos.ofFloored(...) 식.
       로컬(^) 좌표나 3축이 아니면 None(->브릿지)."""
    if not raw:
        return None
    parts = raw.split()
    if len(parts) != 3 or any(p.startswith("^") for p in parts):
        return None
    # `~` 상대좌표는 바닐라에서 '소스(실행) 위치' 기준이다. executor 기준으로 계산하면
    # (1) positioned/at <타엔티티> 로 소스≠executor 가 되면 위치가 어긋나고
    # (2) executor 가 없는 소스(명령블록/함수 루트)에서 NPE 가 난다.
    # 소스 위치로 산출하며, 리프 본문 치환(source→cur_src)이 재바인딩까지 반영한다.
    bases = ["source.getPosition().x", "source.getPosition().y", "source.getPosition().z"]
    axes = []
    for i, p in enumerate(parts):
        if p.startswith("~"):
            off = p[1:]
            if off == "":
                axes.append(bases[i])
            else:
                try:
                    axes.append(f"{bases[i]} + {float(off)}")
                except ValueError:
                    return None
        else:
            try:
                axes.append(repr(float(p)))
            except ValueError:
                return None
    return f'net.minecraft.util.math.BlockPos.ofFloored({axes[0]}, {axes[1]}, {axes[2]})'


# ───────────────────────── 선언적 매핑 테이블 ─────────────────────────
# trees.json 의 노드 시퀀스(명령 구문 경로)를 키로, 자바 템플릿을 값으로 갖는 카탈로그.
# 새 명령 추가 = 여기 한 항목. execute 계열/로직 필요한 명령(scoreboard/tag/data/...)은
# 기존 전용 함수가 담당하고, 이 테이블은 '단순 타겟 명령'만 다룬다.
#
# 키: 노드 이름 전체 시퀀스 튜플 (command 포함). trees.json 의 chain 에서 그대로 나옴.
# 값: SimpleRule
#   args   : {인자명: 변환모드}
#            raw    - 원문 그대로 (숫자 등)
#            jstr   - 자바 문자열 리터럴로
#            self   - 반드시 @s (아니면 규칙 부적용 -> 기존 경로 폴백)
#            single - 단일 셀렉터(@n/@e[limit=1]/@p/@s[..]) -> 엔티티 식 (_t 에 대입됨)
#   java   : 템플릿. {인자명} 치환. single 인자는 {인자명} 자리에 변수 _t 가 들어가고
#            null 가드 블록으로 감싸진다.
#   kind   : 결과 분류 (native/gated)
#   prelude: 필요한 prelude 심볼 힌트 (executor/ctx/sb/server 는 assemble 이 자동 감지하므로
#            템플릿에 그 식별자가 등장하면 별도 지정 불필요)

@dataclass
class SimpleRule:
    args: dict
    java: str
    kind: str = "native"

SIMPLE_RULES: dict[tuple, SimpleRule] = {
    # ride <@s> mount <단일셀렉터> - 카트 탑승. 바닐라는 항상 탑승(다른 탈것이면 내린 뒤 탑승).
    # rideMount 가 자기탑승/순환을 차단(없으면 addPassengersDeep 무한재귀 → StackOverflow).
    ("ride", "target", "mount", "vehicle"): SimpleRule(
        args={"target": "self", "vehicle": "single"},
        java="KfcGen.rideMount(executor, {vehicle});",
        kind="native",
    ),
    # ride <@s> dismount
    ("ride", "target", "dismount"): SimpleRule(
        args={"target": "self"},
        java="KfcGen.dismount(executor);",   # stopRiding + 탑승 정렬 캐시(RIDE_MUT) 무효화
        kind="native",
    ),
    # xp set @s <n> levels / points
    ("xp", "set", "target", "amount", "levels"): SimpleRule(
        args={"target": "self", "amount": "raw"},
        java="if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _p) _p.setExperienceLevel({amount});",
        kind="native",
    ),
    ("xp", "set", "target", "amount", "points"): SimpleRule(
        args={"target": "self", "amount": "raw"},
        java="if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _p) _p.setExperiencePoints({amount});",
        kind="native",
    ),
    # me <action> — 바닐라 chat.type.emote("* %s %s") 브로드캐스트
    ("me", "action"): SimpleRule(
        args={"action": "jstr"},
        java='source.getServer().getPlayerManager().broadcast('
             'net.minecraft.text.Text.translatable("chat.type.emote", '
             'source.getDisplayName(), net.minecraft.text.Text.literal({action})), false);',
        kind="native",
    ),
    # say <message> - 전체 브로드캐스트 (서버 소스 기준 mcfunction say 와 동등)
    ("say", "message"): SimpleRule(
        args={"message": "jstr"},
        java='source.getServer().getPlayerManager().broadcast('
             'net.minecraft.text.Text.literal("[server] " + {message}), false);',
        kind="native",
    ),
}


def try_simple_rule(command: str, nn: list, args: dict, em: Emitted):
    """선언적 테이블 매치. 반환: True(변환됨) / False(규칙상 브릿지) / None(테이블 미적용->기존 경로)."""
    key = tuple([command] + list(nn[1:])) if nn and nn[0] == command else tuple([command] + list(nn))
    # nn 은 보통 [command, sub, ...] 형태 - command 중복 방지
    rule = SIMPLE_RULES.get(tuple(nn)) or SIMPLE_RULES.get(key)
    if rule is None:
        return None
    vals = {}
    single_var = None
    for name, mode in rule.args.items():
        raw = args.get(name, [{}])[0].get("raw")
        if raw is None:
            return None
        if mode == "raw":
            vals[name] = raw
        elif mode == "jstr":
            vals[name] = jstr(raw)
        elif mode == "self":
            if raw != "@s":
                return None  # @s 전제 불충족 -> 기존 경로/브릿지
            vals[name] = "executor"
        elif mode == "single":
            ent = single_entity_expr(raw)
            if ent is None:
                return None
            single_var = (name, ent)
            vals[name] = "_t"
        else:
            return None
    stmt = rule.java.format(**vals)
    if "self" in rule.args.values():
        stmt = f'if (executor != null) {{ {stmt} }}'
    if single_var:
        _, ent = single_var
        em.java.append(f'{{ net.minecraft.entity.Entity _t = {ent};')
        em.java.append(f'  if (_t != null) {stmt} }}')
    else:
        em.java.append(stmt)
    em.kind = rule.kind
    return True


def _loot_fanout(sel_raw, var, body, em, reason_label, loot_decl):
    """loot give / replace entity 공통: { <loot_decl> <단일|루프 body> }.
       single_entity_expr 우선(@s/@p/@n/limit=1 단일), 실패 시 루프.
       body(entvar) 는 자바 문장(가드 포함 여부는 body 가 결정).
       loot_decl 은 '_loot' 리스트 선언문(블록 최상단 1회)."""
    em.java.append(f"{{ {loot_decl}")
    ent = single_entity_expr(sel_raw) if sel_raw else None
    if ent is not None:
        em.java.append(f"  net.minecraft.entity.Entity {var} = {ent};")
        em.java.append(f"  {body(var)}")
    else:
        sel = parse_selector(sel_raw) if sel_raw else None
        lo = entity_loop_open(sel, var) if sel else None
        if lo is None:
            em.reason = f"{reason_label} 대상({str(sel_raw)[:20]}) 미지원"
            return False
        em.java.extend(lo)
        em.java.append(f"    {body(var)}")
        em.java.append("}")
    em.java.append("}")
    em.kind = "native"
    return True


def _fanout_target(tgt, var, body, em, reason_label):
    """대상 셀렉터 tgt 를 @s / 단일 엔티티 / 루프 세 경로로 전개하는 공통 헬퍼.
       body(entvar) 는 자바 '문장 하나'(끝에 ; 포함)를 만드는 콜백.
       - @s        : if (executor != null) <body(executor)>
       - 단일       : { Entity <var> = <expr>; if (<var> != null) <body(var)> }
       - 루프       : for(...) { <body(var)> }
       성공 시 em.kind='native' 세팅 후 True, 미해소 시 em.reason 세팅 후 False.
       (advancement/recipe/clear/item replace 등 동일 골격의 중복을 제거)"""
    if tgt == "@s":
        em.java.append(f'if (executor != null) {body("executor")}')
        em.kind = "native"; return True
    sel = parse_selector(tgt)
    # [버그수정] @n/@p/@r/limit=1 은 '단일'(최근접/첫) 셀렉터다 — 전체 루프로 전개하면
    #   같은 셀렉터가 소스(single_entity_expr=단일)와 타깃(loop=전체)에서 다르게 해소돼
    #   (예: item replace 3-way 스왑) 멀티플레이 시 아이템이 EMPTY(air)가 된다.
    #   단일 셀렉터는 반드시 single_entity_expr(단일)로 해소해 소스/타깃 해소를 일치시킨다.
    single = sel is not None and (sel.base in ("s", "n", "p", "r") or sel.limit == 1)
    if single:
        ent = single_entity_expr(tgt)
        if ent is not None:
            em.java.append(f'{{ net.minecraft.entity.Entity {var} = {ent};'
                           f' if ({var} != null) {body(var)} }}')
            em.kind = "native"; return True
        # 단일인데 식 해소 실패 → 아래 loop/bridge 로 폴백
    lo = entity_loop_open(sel, var) if sel else None
    if lo is None:
        ent = single_entity_expr(tgt)
        if ent is None:
            em.reason = f"{reason_label} 대상({str(tgt)[:20]}) 미지원"; return False
        em.java.append(f'{{ net.minecraft.entity.Entity {var} = {ent};'
                       f' if ({var} != null) {body(var)} }}')
        em.kind = "native"; return True
    em.java.extend(lo)
    em.java.append(f'    {body(var)}')
    em.java.append("}")
    em.kind = "native"; return True


def emit_target(line: str, command: str, chain: list[dict], em: Emitted) -> bool:
    """비-execute(또는 run 뒤) 타겟 커맨드를 자바로. 성공 시 True, 네이티브 불가면 False."""
    # 중첩 execute(run execute ...) - 서브체인을 그대로 execute 폴드로 재귀.
    if command == "execute":
        return emit_execute(line, chain, em)

    nodes, args = split_chain(chain)
    nn = [n["node"] for n in nodes]

    # ---- 침묵 조회형(standalone) → 네이티브 no-op ----
    # [근거] 함수는 침묵 소스(CommandFunctionManager withSilent — 바이트코드 확인)로 실행되어
    # 조회형 명령의 유일한 효과(채팅 피드백)가 억제된다. 반환값이 필요한 자리는
    # store/if/return 경로가 별도 처리하므로(여기 도달 = standalone) 관측효과가 없다.
    _sq = None
    if command in ("seed", "list"):
        _sq = command
    elif command == "locate":
        _sq = "locate"                      # 탐색만 하고 상태 불변(피드백 억제) — no-op 가 더 빠름
    elif command == "xp" and len(nn) > 1 and nn[1] == "query":
        _sq = "xp query"
    elif command == "time" and len(nn) > 1 and nn[1] == "query":
        _sq = "time query"
    elif command == "worldborder" and len(nn) > 1 and nn[1] == "get":
        _sq = "worldborder get"
    elif command == "scoreboard" and nn[1:3] == ["players", "get"]:
        _sq = "scoreboard players get"
    elif command == "tag" and len(nn) > 2 and nn[2] == "list":
        _sq = "tag list"
    elif command == "forceload" and len(nn) > 1 and nn[1] == "query":
        _sq = "forceload query"
    elif command == "data" and len(nn) > 1 and nn[1] == "get":
        _sq = "data get"                    # 부수효과 없음(기존 브릿지 사유에도 명시)
    elif command == "attribute" and nn and nn[-1] == "get" and not ({"set", "add", "remove"} & set(nn)):
        _sq = "attribute get"
    if _sq is not None:
        em.java.append(f"// (침묵 조회) {_sq} — 함수 소스는 피드백 억제, standalone 은 관측효과 없음")
        em.kind = "native"
        return True

    # ---- 0) 선언적 규칙 테이블 (노드 경로 -> 템플릿) ----
    r = try_simple_rule(command, nn, args, em)
    if r is not None:
        return r

    _h = _DISPATCH_SIMPLE.get(command)
    if _h is not None:
        return _h(nn, args, em)

    # ---- scoreboard players add/remove/set/operation ----
    if command == "scoreboard" and "players" in nn:
        sub = nn[nn.index("players") + 1] if "players" in nn else None
        if sub == "display" and "numberformat" in nn:
            return emit_scoreboard_numberformat(nn, args, em)
        if sub == "display" and "name" in nn:
            return emit_scoreboard_displayname(nn, args, em)
        return emit_scoreboard(sub, args, em)

    if command == "scoreboard" and "objectives" in nn:
        return emit_scoreboard_objectives(nn, args, em)
















    # ---- tag @s add/remove ----
    if command == "time":
        verb = nn[1] if len(nn) > 1 else None
        if verb in ("set", "add"):
            LIT = {"day": 1000, "night": 13000, "noon": 6000, "midnight": 18000}
            val = None
            if len(nn) > 2 and nn[2] in LIT:
                val = LIT[nn[2]]
            else:
                t = first_arg(args, "time")
                if t is not None:
                    m = re.fullmatch(r'(-?\d+)([tsd]?)', str(t).strip())
                    if m:
                        n = int(m.group(1)); u = m.group(2)
                        val = n * (20 if u == "s" else 24000 if u == "d" else 1)
            if val is not None:
                em.java.append(f"KfcGen.{'setTime' if verb=='set' else 'addTime'}(ctx, {val}L);")
                em.kind = "native"; return True
        em.reason = f"time {verb} (query/형식 미지원)"
        return False

    if command == "tick":
        # tick rate <float> | tick freeze | tick unfreeze  (1.20.3+)
        # [검증] yarn 1.21.5 바이트코드: TickCommand.executeRate → ServerTickManager.setTickRate(F)V,
        # freeze/unfreeze → setFrozen(Z). server.getTickManager() 가 ServerTickManager 를 반환하므로
        # 가상 디스패치로 바닐라와 동일 경로(클라이언트 동기화 포함). rate 인자 범위(1..10000)는
        # 파서가 이미 강제(범위 밖은 파스 실패=trees 에 안 옴). step/sprint 은 1차 폴백.
        sub = nn[1] if len(nn) > 1 else None
        if sub == "rate":
            r = first_arg(args, "rate")
            if r is not None:
                em.java.append(f"ctx.server.getTickManager().setTickRate({jfloat(r)});")
                em.kind = "native"
                return True
        elif sub in ("freeze", "unfreeze"):
            em.java.append(f"ctx.server.getTickManager().setFrozen({'true' if sub == 'freeze' else 'false'});")
            em.kind = "native"
            return True
        elif sub == "step":
            # tick step <time> | tick step stop  (yarn: ServerTickManager.step(I)Z / stopStepping)
            tm = first_arg(args, "time")
            if tm is not None:
                em.java.append(f"ctx.server.getTickManager().step({jint(tm)});")
                em.kind = "native"
                return True
        elif sub == "sprint":
            tm = first_arg(args, "time")
            if tm is not None:
                em.java.append(f"ctx.server.getTickManager().startSprint({jint(tm)});")
                em.kind = "native"
                return True
        em.reason = f"tick {sub} 미지원"
        return False

    if command == "weather":
        wk = nn[1] if len(nn) > 1 else None
        if wk in ("clear", "rain", "thunder"):
            d = first_arg(args, "duration")
            ticks = None
            if d is not None:
                m = re.fullmatch(r'(\d+)([tsd]?)', str(d).strip())
                if m:
                    n = int(m.group(1)); u = m.group(2)
                    ticks = n * (1 if u == "t" else 24000 if u == "d" else 20)
            t = ticks if ticks is not None else 6000
            if wk == "clear":
                em.java.append(f"KfcGen.setWeather(ctx, {t}, 0, false, false);")
            elif wk == "rain":
                em.java.append(f"KfcGen.setWeather(ctx, 0, {t}, true, false);")
            else:
                em.java.append(f"KfcGen.setWeather(ctx, 0, {t}, true, true);")
            em.kind = "native"; return True
        em.reason = f"weather {wk} 미지원"
        return False

    if command == "difficulty":
        lvl = first_arg(args, "difficulty")
        if lvl is None and len(nn) > 1 and nn[1] in ("peaceful", "easy", "normal", "hard"):
            lvl = nn[1]                      # 리터럴 노드 형태(brigadier literal)
        if lvl is None:
            em.reason = "difficulty query(인자 없음) 미지원"; return False
        em.java.append(f"KfcGen.setDifficulty(server, {jstr(lvl)});")
        em.kind = "native"; return True

    if command == "defaultgamemode":
        mode = first_arg(args, "gamemode")
        if mode is None:
            em.reason = "defaultgamemode 인자 없음"; return False
        em.java.append(f"KfcGen.setDefaultGameMode(server, {jstr(mode)});")
        em.kind = "native"; return True

    if command == "random":
        sub = nn[1] if len(nn) > 1 else None
        if sub not in ("value", "roll"):
            em.reason = f"random {sub} (reset/sequence 1차 미지원)"; return False
        rng = first_arg(args, "range")
        lo, hi = parse_range(rng) if rng else (None, None)
        if lo is None or hi is None:
            em.reason = f"random {sub} 범위({rng}) 미지원"; return False
        try:
            int(lo); int(hi)
        except (ValueError, TypeError):
            em.reason = f"random {sub} 정수범위 아님({rng})"; return False
        if sub == "value":
            # 단독 random value: 결과를 store 없이 버림(바닐라도 체이닝 없으면 관측효과 없음).
            # RNG 소비만 일으키도록 호출(부수효과). store 경로는 별도로 값을 받는다.
            em.java.append(f"ctx.world.getRandom().nextBetween({lo}, {hi});")
            em.kind = "native"; return True
        # random roll: 굴린 값을 op/플레이어에게 브로드캐스트(바닐라 동작).
        em.java.append(f"KfcGen.randomRoll(ctx, source, {lo}, {hi});")
        em.kind = "native"; return True

    if command == "gamemode":
        mode = first_arg(args, "gamemode")
        tgt = first_arg(args, "target") or "@s"
        if mode is not None:
            if tgt == "@s":
                em.java.append(f'if (executor != null) KfcGen.setGameMode(executor, {jstr(mode)});')
                em.kind = "native"; return True
            if tgt.startswith("@s[") or tgt.startswith("@s "):
                psel = parse_selector(tgt)
                cond = selector_cond(psel) if psel else None
                if cond is None:
                    em.reason = "gamemode @s[...] 필터 미해소"; return False
                stmt = f'KfcGen.setGameMode(executor, {jstr(mode)});'
                em.java.append(stmt if cond == "true" else f'if ({cond}) {stmt}')
                em.kind = "native"; return True
            # 그 외 대상(@a/@p/@e[...] 등): 공통 3분기 전개. setGameMode 는 내부에서
            # 플레이어 여부를 확인하므로(기존 @s 경로가 Entity 로 호출) 그대로 위임.
            return _fanout_target(
                tgt, "_gmE",
                lambda e: f'KfcGen.setGameMode({e}, {jstr(mode)});',
                em, "gamemode")

    if command == "tag":
        verb = "add" if "add" in nn else ("remove" if "remove" in nn else None)
        sel = args.get("targets", [{}])[0].get("raw")
        name = args.get("name", [{}])[0].get("raw")
        if verb and name is not None:
            if sel == "@s":
                fn = "addTag" if verb == "add" else "removeTag"
                em.java.append(f'if (executor != null) KfcGen.{fn}(executor, {jstr(name)});')
                return True
            if sel and (sel.startswith("@s[") or sel.startswith("@s ")):
                psel = parse_selector(sel)
                cond = selector_cond(psel) if psel else None
                if cond is None:
                    return False
                fn = "addTag" if verb == "add" else "removeTag"
                stmt = f'KfcGen.{fn}(executor, {jstr(name)});'
                em.java.append(f'if ({cond}) {stmt}' if cond != "true" else stmt)
                return True
            return emit_tag_selector(verb, sel, name, em)  # @e/@n/@p 셀렉터 -> 루프/단일
        return False


    # ---- function ns:path [with ...|{compound}] ----
    if command == "function":
        fid = args.get("name", [{}])[0].get("raw")
        if not fid:
            return False
        call = function_call_java(fid, args, nn, em)
        if call is None:
            return False  # 매크로 인자 소스 미지원 -> 브릿지
        em.java.append(f'// -> {fid}')
        em.java.extend(call)
        return True

    # ---- return [value] | return run function ns:path | return run <cmd> ----
    # 함수 본문은 int executeReturn(source) 로 생성되며, mcfunction 의 return 은
    # 실제 자바 return <값>; 으로 매핑된다(if function / store success 에서 값 사용).
    if command == "return":
        if "run" in nn:
            ri = nn.index("run")
            sub = nn[ri + 1:]
            if sub and sub[0] == "function":
                fid = first_arg(args, "name")
                if not fid:
                    em.reason = "return run function 이름 없음"
                    return False
                # 매크로 함수: with 인자 소스를 해소해 executeReturn(source, macroArgs) 값 전파.
                if fid in MACRO_FNS:
                    _mres = _macro_args_expr(args, nn)
                    if _mres is None:
                        em.reason = "return run function(매크로) 인자 소스 미지원"
                        return False
                    margs, _mguards = _mres
                    _call = f"{fqcn(fid)}.executeReturn(source, {margs})"
                    if _mguards:
                        # 매크로변수가 비면 바닐라는 이 $줄을 스킵 → return 안 하고 다음 줄로 진행.
                        _cond = " && ".join(f"!KfcGen.macroEmpty({g})" for g in _mguards)
                        em.java.append(f"if ({_cond}) return {_call};")
                        em.terminal = False
                    else:
                        em.java.append(f"return {_call};")
                        em.terminal = True
                    return True
                if ALL_FIDS and fid not in ALL_FIDS:
                    _ns, _p = fid.split(":", 1) if ":" in fid else ("minecraft", fid)
                    em.java.append(f"return KfcGen.instantExecuteFunctionReturn(source, "
                                   f"net.minecraft.util.Identifier.of({jstr(_ns)}, {jstr(_p)}));")
                    em.terminal = True
                    return True
                em.java.append(f"return {fqcn(fid)}.executeReturn(source);")
                em.terminal = True
                return True
            # return run <비함수 커맨드> -> 내부 커맨드를 emit 후 결과(성공=1) 반환
            inner_chain = []
            seen_run = False
            for s in chain:
                if not seen_run:
                    if s.get("node") == "run":
                        seen_run = True
                    continue
                inner_chain.append(s)
            sub_cmd = sub[0] if sub else None
            inner = Emitted(line=line)
            if sub_cmd and emit_target(line, sub_cmd, inner_chain, inner):
                em.java.extend(inner.java)
                em.java.append("return 1;")
                em.kind = inner.kind
                em.terminal = True
                return True
            em.reason = inner.reason or f"return run {sub_cmd} 네이티브화 불가"
            return False
        # return <value> -> 그 값을 반환. (return fail 은 0)
        val = first_arg(args, "value")
        rv = "0"
        if val is not None:
            # 매크로 변수 반환($return $(lap) 등): 런타임에 정수 파싱.
            #   기존엔 int(val) 실패 → "1" 상수로 뭉개져 '최대 랩 1 고정' 류 버그를 유발했다.
            _mi = _jnum(val, "Double.parseDouble") if "MACROVAR_" in str(val) else None
            if _mi is not None:
                rv = f"((int) {_mi})"        # 후처리에서 MACROVAR_i -> macroArgs.get(var) 환원
            else:
                try:
                    rv = str(int(val))
                except ValueError:
                    rv = "1"  # 비정수·비매크로 값은 보수적으로 1(참)
        em.java.append(f"return {rv};")
        em.terminal = True
        return True

    # ---- data (modify/get/...) : 질문1=A, 최대 네이티브 시도 ----

    if command == "kill":
        holder = first_arg(args, "targets") or "@s"
        sel = parse_selector(holder)
        if sel is None:
            em.reason = f"kill 셀렉터({holder[:25]}) 미지원"
            return False

        def _kill_pred_guards(entvar):
            gs = []
            for pid in sel.predicates:
                neg = pid.startswith("!")
                key = pid[1:] if neg else pid
                pid_norm = key if ":" in key else "minecraft:" + key
                e = f'KfcGen.testPredicate(source, {entvar}, {jstr(pid_norm)})'
                gs.append(f'!({e})' if neg else e)
            return gs

        if sel.base == "s":
            # @s / @s[tag/score/predicate]: 실행자 자신을 (필터 만족 시) 제거.
            conds = _tag_conds(sel, 'executor')
            _vc = _volume_cond(sel, "executor")
            if _vc: conds.append(_vc)
            conds += _score_conds(sel, 'executor')
            conds += _kill_pred_guards("executor")
            conds += _selector_extra_conds(sel, "executor")
            guard = " && ".join(["executor != null"] + conds)
            em.java.append(f"if ({guard}) KfcGen.killEntity(executor);")
            em.kind = "native"
            return True
        # 루프 대상 (@e/@a/@n): tag/type/distance 는 루프 헬퍼, scores/predicate 는 본문 가드.
        loop = entity_loop_open(sel, "_k")
        if loop is None:
            em.reason = f"kill 셀렉터({holder[:25]}) 루프 미해소"
            return False
        em.java.extend(loop)
        for _c in _score_conds(sel, '_k'):
            em.java.append(f'    if (!{_c}) continue;')
        for g in _kill_pred_guards("_k"):
            em.java.append(f'    if (!({g})) continue;')
        em.java.append("    KfcGen.killEntity(_k);")
        em.java.append("}")
        em.kind = "native"
        return True


    if command == "attribute":
        holder = first_arg(args, "target")
        attr = first_arg(args, "attribute")
        if not holder or not attr:
            em.reason = "attribute 인자 부족"
            return False
        sub = nn[3] if len(nn) > 3 else None   # modifier | base | get
        if sub == "modifier":
            verb = nn[4] if len(nn) > 4 else None
            if verb == "add":
                mid = first_arg(args, "id")
                val = first_arg(args, "value")
                op = nn[-1] if nn[-1] in ("add_value", "add_multiplied_base",
                                          "add_multiplied_total", "multiply_base", "multiply") else "add_value"
                mk = lambda ent, _v=val: f'KfcGen.attrModifierAdd({ent}, {jstr(attr)}, {jstr(mid)}, {jdouble(_v) if "MACROVAR_" in str(_v) else _v}, {jstr(op)});'
            elif verb == "remove":
                mid = first_arg(args, "id")
                mk = lambda ent: f'KfcGen.attrModifierRemove({ent}, {jstr(attr)}, {jstr(mid)});'
            else:
                em.reason = f"attribute modifier {verb} (1차 미지원)"
                return False
        elif sub == "base" and "set" in nn:
            val = first_arg(args, "value")
            mk = lambda ent: f'KfcGen.attrBaseSet({ent}, {jstr(attr)}, {val});'
        else:
            em.reason = f"attribute {sub} (1차 미지원)"
            return False
        if holder == "@s":
            em.java.append(f'if (executor != null) {mk("executor")}')
        else:
            esel = single_entity_expr(holder)
            if esel is None:
                em.reason = f"attribute 셀렉터({holder[:20]}) 미지원"
                return False
            em.java.append(f'{{ net.minecraft.entity.Entity _attrE = {esel};'
                           f' if (_attrE != null) {mk("_attrE")} }}')
        em.kind = "native"
        return True

    if command == "particle":
        name = first_arg(args, "name")
        pos = first_arg(args, "pos")
        if name and pos is None:
            pos = "~ ~ ~"                    # 바닐라: pos 생략 = 소스 위치
        if not name or not pos:
            em.reason = "particle 인자 부족"
            return False
        pe = cond_pos_expr(pos)
        if pe is None:
            em.reason = f"particle 좌표({pos}) 미지원"
            return False
        delta = first_arg(args, "delta") or "0 0 0"
        dparts = delta.split()
        if len(dparts) != 3:
            dparts = ["0", "0", "0"]
        speed = first_arg(args, "speed") or "0"
        count = first_arg(args, "count") or "1"
        # force 모드(클라 파티클 설정 무시·강제 표시)와 viewers(대상 플레이어 제한).
        # 이 둘을 빠뜨리면 파티클이 클라 설정에 따라 샘플링돼 양이 줄고 불규칙해진다.
        force_j = "true" if "force" in nn else "false"
        viewers_raw = first_arg(args, "viewers")
        viewers_j = "null"
        if viewers_raw:
            vexpr = _particle_viewers_expr(viewers_raw)
            if vexpr is not None:
                viewers_j = vexpr
        # dust{color:[r,g,b],scale:s} 특수 처리
        dm = re.match(r'^(?:minecraft:)?dust\{', name)
        if dm:
            cm = re.search(r'color:\s*\[\s*([\d.]+)f?\s*,\s*([\d.]+)f?\s*,\s*([\d.]+)f?\s*\]', name)
            sm = re.search(r'scale:\s*([\d.]+)f?', name)
            if not cm:
                em.reason = f"particle dust 색상 파싱 실패: {name[:30]}"
                return False
            r, g, b = cm.group(1), cm.group(2), cm.group(3)
            scale = sm.group(1) if sm else "1"
            em.java.append(
                f'{{ net.minecraft.util.math.Vec3d _pp = {pe};'
                f' KfcGen.spawnDust(ctx.world, _pp.x, _pp.y, _pp.z, '
                f'{dparts[0]}, {dparts[1]}, {dparts[2]}, {speed}, (int)({count}), '
                f'{r}f, {g}f, {b}f, {scale}f, {force_j}, {viewers_j}); }}')
            em.kind = "native"
            return True
        # 파라미터 동반 파티클(block{block_state:..}/item{..}/dust_color_transition 등):
        # ParticleEffectArgumentType.readParameters 로 런타임 파싱해 스폰(모든 vanilla 파티클 지원).
        if "[" in name or "{" in name or " " in name:
            em.java.append(
                f'{{ net.minecraft.util.math.Vec3d _pp = {pe};'
                f' KfcGen.spawnParticleParsed(ctx.world, {jstr(name)}, _pp.x, _pp.y, _pp.z, '
                f'{dparts[0]}, {dparts[1]}, {dparts[2]}, {speed}, (int)({count}), {force_j}, {viewers_j}); }}')
            em.kind = "native"
            return True
        em.java.append(
            f'{{ net.minecraft.util.math.Vec3d _pp = {pe};'
            f' KfcGen.spawnParticle(ctx.world, {jstr(name)}, _pp.x, _pp.y, _pp.z, '
            f'{dparts[0]}, {dparts[1]}, {dparts[2]}, {speed}, (int)({count}), {force_j}, {viewers_j}); }}')
        em.kind = "native"
        return True

    if command == "tellraw":
        return emit_tellraw_title(nn, args, em, "tellraw")
    if command == "title":
        return emit_tellraw_title(nn, args, em, "title")

    if command == "summon":
        etype = first_arg(args, "entity")
        pos = first_arg(args, "pos") or "~ ~ ~"
        nbt = first_arg(args, "nbt")
        if not etype:
            em.reason = "summon 엔티티 타입 없음"
            return False
        pe = cond_pos_expr(pos)
        if pe is None:
            em.reason = f"summon 좌표({pos}) 미지원"
            return False
        nbt_j = "null" if nbt is None else jstr(nbt)
        em.java.append(
            f'{{ net.minecraft.util.math.Vec3d _sp = {pe};'
            f' KfcGen.summon(ctx.world, {jstr(etype)}, _sp.x, _sp.y, _sp.z, {nbt_j}); }}')
        em.kind = "native"
        return True

    if command == "rotate":
        tgt = first_arg(args, "target") or "@s"
        rot = first_arg(args, "rotation")
        if rot is None:
            # rotate <target> facing entity <e> [eyes|feet]  /  rotate <target> facing <pos>
            if "facing" in nn:
                tgt_expr = "executor" if tgt == "@s" else single_entity_expr(tgt)
                if tgt_expr is None:
                    em.reason = f"rotate 대상({tgt[:20]}) 미지원"
                    return False
                if "entity" in nn:
                    fe = first_arg(args, "facingEntity")
                    anchor = first_arg(args, "facingAnchor") or "feet"
                    eyes = "true" if anchor == "eyes" else "false"
                    fe_expr = "executor" if fe == "@s" else (single_entity_expr(fe) if fe else None)
                    if fe_expr is None:
                        em.reason = f"rotate facing entity 대상({(fe or '?')[:20]}) 미해소"
                        return False
                    em.java.append(f'{{ net.minecraft.entity.Entity _rt = {tgt_expr}, _fe = {fe_expr};'
                                   f' if (_rt != null && _fe != null) KfcGen.rotateToFaceEntity(_rt, _fe, {eyes}); }}')
                    em.kind = "native"
                    return True
                pos = first_arg(args, "facingLocation") or first_arg(args, "pos")
                pe = cond_pos_expr(pos) if pos else None
                if pe is None:
                    em.reason = f"rotate facing 좌표({pos}) 미지원"
                    return False
                em.java.append(f'{{ net.minecraft.entity.Entity _rt = {tgt_expr};'
                               f' if (_rt != null) {{ net.minecraft.util.math.Vec3d _fp = {pe};'
                               f' KfcGen.rotateToFacePos(_rt, _fp.x, _fp.y, _fp.z); }} }}')
                em.kind = "native"
                return True
            em.reason = "rotate 회전값 없음"
            return False
        parts = rot.split()
        if len(parts) != 2:
            em.reason = f"rotate 회전 형식({rot}) 미지원"
            return False
        # rotate 의 ~ 는 '소스(실행 컨텍스트)의 회전' 기준(타깃 자기 회전이 아님). Vec2f: x=pitch, y=yaw.
        def rot_comp(v, src_comp):
            if v == "~":
                return src_comp
            if v.startswith("~"):
                return f"({src_comp} + {jfloat(v[1:])})"
            return jfloat(v)
        yaw = rot_comp(parts[0], "source.getRotation().y")
        pitch = rot_comp(parts[1], "source.getRotation().x")
        if tgt == "@s":
            em.java.append(f'if (executor != null) KfcGen.rotateTo(executor, {yaw}, {pitch});')
            em.kind = "native"
            return True
        ent = single_entity_expr(tgt)
        if ent is None:
            em.reason = f"rotate 대상({tgt[:20]}) 미지원"
            return False
        em.java.append(f'{{ net.minecraft.entity.Entity _rotE = {ent};'
                       f' if (_rotE != null) KfcGen.rotateTo(_rotE, {yaw}, {pitch}); }}')
        em.kind = "native"
        return True

    if command == "setblock":
        pos = first_arg(args, "pos")
        block = first_arg(args, "block")
        if pos is None or block is None:
            em.reason = "setblock 인자 누락"
            return False
        pe = cond_pos_expr(pos)            # 절대/~/^ -> Vec3d (source 기준; run-target 치환과 합성)
        if pe is None:
            em.reason = f"setblock 좌표({pos}) 미지원"
            return False
        mode = next((m for m in ("replace", "destroy", "keep") if m in nn), "replace")
        em.java.append(
            f'KfcGen.setBlock(source.getWorld(), '
            f'net.minecraft.util.math.BlockPos.ofFloored({pe}), {jstr(block)}, {jstr(mode)});')
        em.kind = "native"
        return True

    if command == "team":
        verb = nn[1] if len(nn) > 1 else None
        if verb == "add":
            name = first_arg(args, "team")
            disp = first_arg(args, "displayName")
            if not name:
                em.reason = "team add 이름 없음"; return False
            dj = "null" if disp is None else jstr(disp)
            em.java.append(f'KfcGen.teamAdd(ctx, {jstr(name)}, {dj});')
            em.kind = "native"; return True
        if verb == "modify":
            name = first_arg(args, "team")
            # 옵션은 'team' 다음 노드, 값은 arg(allowed/value/displayName/...) 또는 다음 리터럴 노드.
            opt = nn[3] if len(nn) > 3 else None
            if not name or not opt:
                em.reason = "team modify 형식 미지원"; return False
            val = (first_arg(args, "allowed") or first_arg(args, "value")
                   or first_arg(args, "displayName") or first_arg(args, "prefix")
                   or first_arg(args, "suffix"))
            if val is None and len(nn) > 4:
                val = nn[4]   # nametagVisibility/collisionRule/deathMessageVisibility: 값이 리터럴 노드
            if val is None:
                em.reason = f"team modify {opt} 값 미해소"; return False
            em.java.append(f'KfcGen.teamModify(ctx, {jstr(name)}, {jstr(opt)}, {jstr(val)});')
            em.kind = "native"; return True
        if verb in ("leave", "join", "empty", "remove"):
            if verb == "join":
                tname = first_arg(args, "team")
                mem = first_arg(args, "members")
                if not tname:
                    em.reason = "team join 팀명 없음"; return False
                return _fanout_target(
                    mem or "@s", "_tjE",
                    lambda e: f'KfcGen.teamJoin(sb, {jstr(tname)}, {e}.getNameForScoreboard());',
                    em, "team join")
            if verb == "leave":
                mem = first_arg(args, "members")
                return _fanout_target(
                    mem or "@s", "_tmE",
                    lambda e: f'KfcGen.teamLeave(sb, {e}.getNameForScoreboard());',
                    em, "team leave")
            if verb == "empty":
                tname = first_arg(args, "team")
                if not tname:
                    em.reason = "team empty 팀명 없음"; return False
                em.java.append(f'KfcGen.teamEmpty(ctx, {jstr(tname)});')
                em.kind = "native"; return True
            if verb == "remove":
                tname = first_arg(args, "team")
                if not tname:
                    em.reason = "team remove 팀명 없음"; return False
                em.java.append(f'KfcGen.teamRemove(ctx, {jstr(tname)});')
                em.kind = "native"; return True
            em.reason = f"team {verb} (1차 미지원)"; return False
        em.reason = f"team {verb} 미지원"; return False

    if command == "fill":
        frm = first_arg(args, "from"); to = first_arg(args, "to")
        block = first_arg(args, "block")
        if frm is None or to is None or block is None:
            em.reason = "fill 인자 누락"; return False
        fe = cond_pos_expr(frm); te = cond_pos_expr(to)
        if fe is None or te is None:
            em.reason = f"fill 좌표({frm} / {to}) 미지원"; return False
        mode = next((m for m in ("replace", "destroy", "keep", "outline", "hollow") if m in nn), "replace")
        filt = first_arg(args, "filter")
        fj = "null" if filt is None else jstr(filt)
        em.java.append(
            f'{{ net.minecraft.util.math.BlockPos _ff = net.minecraft.util.math.BlockPos.ofFloored({fe});'
            f' net.minecraft.util.math.BlockPos _ft = net.minecraft.util.math.BlockPos.ofFloored({te});'
            f' KfcGen.fill(source.getWorld(), _ff.getX(), _ff.getY(), _ff.getZ(), '
            f'_ft.getX(), _ft.getY(), _ft.getZ(), {jstr(block)}, {jstr(mode)}, {fj}); }}')
        em.kind = "native"; return True


    if command == "spectate":
        tgt = first_arg(args, "target")
        ply = first_arg(args, "player")   # 생략 시 실행자
        # 관전자(player) 식
        if ply is None or ply == "@s":
            ply_expr = "executor"
        else:
            ply_expr = single_entity_expr(ply)
            if ply_expr is None:
                em.reason = f"spectate 관전자({ply[:20]}) 미해소"; return False
        if tgt is None:
            # spectate (인자 없음) - 본인 시점 복귀
            em.java.append(f'KfcGen.spectate({ply_expr}, null);')
            em.kind = "native"; return True
        if tgt == "@s":
            tgt_expr = "executor"
        else:
            tgt_expr = single_entity_expr(tgt)
            if tgt_expr is None:
                em.reason = f"spectate 대상({tgt[:20]}) 미해소"; return False
        em.java.append(f'KfcGen.spectate({ply_expr}, {tgt_expr});')
        em.kind = "native"; return True

    if command == "ride":
        tgt = first_arg(args, "target")
        if tgt is None:
            em.reason = "ride 대상 없음"; return False
        tgt_expr = "executor" if tgt == "@s" else single_entity_expr(tgt)
        if tgt_expr is None:
            em.reason = f"ride 대상({tgt[:20]}) 미해소"; return False
        if "dismount" in nn:
            em.java.append(f'{{ net.minecraft.entity.Entity _re = {tgt_expr};'
                           f' if (_re != null) KfcGen.rideDismount(_re); }}')
            em.kind = "native"; return True
        if "mount" in nn:
            veh = first_arg(args, "vehicle")
            veh_expr = "executor" if veh == "@s" else (single_entity_expr(veh) if veh else None)
            if veh_expr is None:
                em.reason = f"ride 탈것({str(veh)[:20]}) 미해소"; return False
            em.java.append(f'{{ net.minecraft.entity.Entity _re = {tgt_expr}; net.minecraft.entity.Entity _rv = {veh_expr};'
                           f' if (_re != null && _rv != null) KfcGen.rideMount(_re, _rv); }}')
            em.kind = "native"; return True
        em.reason = "ride 형식 미지원"; return False

    if command in ("msg", "tell", "w"):
        m = first_arg(args, "message")
        tgt = first_arg(args, "targets") or "@s"
        if m is None:
            em.reason = "msg 텍스트 없음"; return False
        if "@" in m or "$(" in m:
            em.reason = "msg 텍스트 내 셀렉터/매크로 미지원"; return False
        return _fanout_target(
            tgt, "_mgE",
            lambda e: (f'if ({e} instanceof net.minecraft.server.network.ServerPlayerEntity _mp)'
                       f' KfcGen.msgTo(source, _mp, {jstr(m)});'),
            em, "msg")

    if command == "teammsg":
        m = first_arg(args, "message")
        if m is None:
            em.reason = "teammsg 텍스트 없음"; return False
        if "@" in m or "$(" in m:
            em.reason = "teammsg 텍스트 내 셀렉터/매크로 미지원"; return False
        em.java.append(f'KfcGen.teamMsg(ctx, source, {jstr(m)});')
        em.kind = "native"; return True

    if command == "kick":
        tgt = first_arg(args, "targets")
        if not tgt:
            em.reason = "kick 대상 없음"; return False
        reason = first_arg(args, "reason")
        rj = "null" if reason is None else jstr(reason)
        return _fanout_target(
            tgt, "_kkE",
            lambda e: (f'if ({e} instanceof net.minecraft.server.network.ServerPlayerEntity _kp)'
                       f' KfcGen.kickPlayer(_kp, {rj});'),
            em, "kick")

    if command == "advancement":
        verb = nn[1] if len(nn) > 1 else None
        if verb not in ("grant", "revoke"):
            em.reason = f"advancement {verb} 미지원"; return False
        # only <id> / everything 지원. from/through/until(트리 범위)는 1차 미지원.
        if "everything" in nn:
            tgt = first_arg(args, "targets") or "@s"
            grant = "true" if verb == "grant" else "false"
            return _fanout_target(
                tgt, "_aae",
                lambda e: f'KfcGen.advancementAll(server, {e}, {grant});',
                em, "advancement everything")
        if "only" not in nn:
            scope = next((s for s in ("from", "through", "until") if s in nn), None)
            if scope is not None:
                advid = first_arg(args, "advancement")
                if not advid:
                    em.reason = f"advancement {scope} id 없음"; return False
                tgt = first_arg(args, "targets") or "@s"
                grant = "true" if verb == "grant" else "false"
                return _fanout_target(
                    tgt, "_aas",
                    lambda e: (f'KfcGen.advancementScope(server, {e}, '
                               f'{jstr(advid)}, {jstr(scope)}, {grant});'),
                    em, f"advancement {scope}")
            em.reason = f"advancement {verb} (범위 미지원)"; return False
        advid = first_arg(args, "advancement")
        tgt = first_arg(args, "targets") or "@s"
        grant = "true" if verb == "grant" else "false"
        if not advid:
            em.reason = "advancement id 없음"; return False
        return _fanout_target(
            tgt, "_ae",
            lambda e: f'KfcGen.advancement(server, {e}, {jstr(advid)}, {grant});',
            em, "advancement")

    if command == "recipe":
        verb = nn[1] if len(nn) > 1 else None
        if verb not in ("give", "take"):
            em.reason = f"recipe {verb} 미지원"; return False
        rec = first_arg(args, "recipe")        # 레시피 id 또는 '*'(전체)
        if rec is None and "*" in nn:
            rec = "*"                          # '*' 는 리터럴 노드로 옴
        tgt = first_arg(args, "targets") or "@s"
        if rec is None:
            em.reason = "recipe id 없음"; return False
        unlock = "true" if verb == "give" else "false"
        rj = "null" if rec == "*" else jstr(rec)
        return _fanout_target(
            tgt, "_re",
            lambda e: (f'if ({e} instanceof net.minecraft.server.network.ServerPlayerEntity _rp)'
                       f' KfcGen.recipe(server, _rp, {rj}, {unlock});'),
            em, "recipe")

    if command == "clear":
        tgt = first_arg(args, "targets") or "@s"
        item = first_arg(args, "item")
        maxc = first_arg(args, "maxCount")
        parsed = parse_clear_item(item) if item is not None else ("*", None)
        if parsed is None:
            em.reason = f"clear 아이템 술어({str(item)[:25]}) 미지원"; return False
        iid, cdata = parsed
        mc = "-1" if maxc is None else jint(maxc)
        if cdata == "__RUNTIME_PRED__":
            iid_j = jstr(iid)
            return _fanout_target(
                tgt, "_clE",
                lambda e: f'KfcGen.clearItemsPred(source, {e}, {iid_j}, {mc});',
                em, "clear")
        cd = "null" if cdata is None else jstr(cdata)
        iid_j = "null" if iid == "*" else jstr(iid)
        return _fanout_target(
            tgt, "_clE",
            lambda e: f'KfcGen.clearItems({e}, {iid_j}, {cd}, {mc});',
            em, "clear")

    if command == "item":
        verb = nn[1] if len(nn) > 1 else None
        if verb != "replace":
            em.reason = f"item {verb} (1차 미지원)"; return False
        holder_kind = nn[2] if len(nn) > 2 else None
        if holder_kind == "block":
            # item replace block <pos> <slot> with <item> [count] | from entity/block <src> <srcSlot>
            pos = first_arg(args, "pos")
            slot = first_arg(args, "slot")
            if not pos or not slot:
                em.reason = "item replace block 좌표/슬롯 없음"; return False
            pe = cond_pos_expr(pos)
            if pe is None:
                em.reason = f"item replace block 좌표({pos}) 미지원"; return False
            bp = f'net.minecraft.util.math.BlockPos.ofFloored({pe})'
            if "with" in nn:
                item = first_arg(args, "item")
                cnt = first_arg(args, "count")
                cj = "1" if cnt is None else jint(cnt)
                ij = "null" if item is None else jstr(item)
                em.java.append(
                    f'KfcGen.itemReplaceBlockWith(server, source.getWorld(), '
                    f'{bp}, {jstr(slot)}, {ij}, {cj});')
                em.kind = "native"; return True
            if "from" in nn:
                fi = nn.index("from")
                src_kind = nn[fi + 1] if fi + 1 < len(nn) else None
                srcslot = first_arg(args, "sourceSlot")
                if not srcslot:
                    em.reason = "item replace block from 슬롯 없음"; return False
                if src_kind == "entity":
                    src = first_arg(args, "source")
                    src_expr = "executor" if src == "@s" else (single_entity_expr(src) if src else None)
                    if src_expr is None:
                        em.reason = f"item replace block from entity({(src or '?')[:20]}) 미해소"; return False
                    em.java.append(f'{{ net.minecraft.entity.Entity _isrc = {src_expr};'
                                   f' if (_isrc != null) KfcGen.itemReplaceBlockFromEntity(source.getWorld(), '
                                   f'{bp}, {jstr(slot)}, _isrc, {jstr(srcslot)}); }}')
                    em.kind = "native"; return True
                if src_kind == "block":
                    spos = first_arg(args, "source")
                    spe = cond_pos_expr(spos) if spos else None
                    if spe is None:
                        em.reason = f"item replace block from block 좌표({spos}) 미지원"; return False
                    em.java.append(f'KfcGen.itemReplaceBlockFromBlock(source.getWorld(), {bp}, {jstr(slot)}, '
                                   f'net.minecraft.util.math.BlockPos.ofFloored({spe}), {jstr(srcslot)});')
                    em.kind = "native"; return True
                em.reason = f"item replace block from {src_kind} 미지원"; return False
            em.reason = "item replace block 형식 미지원"; return False
        if holder_kind != "entity":
            em.reason = f"item replace {holder_kind} 미지원"; return False
        tgt = first_arg(args, "targets")
        slot = first_arg(args, "slot")
        if not tgt or not slot:
            em.reason = "item replace 대상/슬롯 없음"; return False
        # 본문 빌더: 타겟 엔티티 식 _it 에 대해 콜을 생성
        if "with" in nn:
            item = first_arg(args, "item")
            cnt = first_arg(args, "count")
            cj = "1" if cnt is None else jint(cnt)
            ij = "null" if item is None else jstr(item)
            body = lambda e: f'KfcGen.itemReplaceWith(server, {e}, {jstr(slot)}, {ij}, {cj});'
        elif "from" in nn:
            fi = nn.index("from")
            src_kind = nn[fi + 1] if fi + 1 < len(nn) else None
            srcslot = first_arg(args, "sourceSlot")
            if not srcslot:
                em.reason = "item replace from 슬롯 없음"; return False
            if src_kind == "block":
                # item replace entity <e> <slot> from block <pos> <srcSlot>
                spos = first_arg(args, "source")
                spe = cond_pos_expr(spos) if spos else None
                if spe is None:
                    em.reason = f"item replace from block 좌표({spos}) 미지원"; return False
                body = lambda e: (f'KfcGen.itemReplaceFromBlock({e}, {jstr(slot)}, source.getWorld(), '
                                  f'net.minecraft.util.math.BlockPos.ofFloored({spe}), {jstr(srcslot)});')
            else:
                src = first_arg(args, "source")
                if not src:
                    em.reason = "item replace from 소스 없음"; return False
                src_expr = "executor" if src == "@s" else single_entity_expr(src)
                if src_expr is None:
                    em.reason = f"item replace from 소스({src[:20]}) 미해소"; return False
                body = lambda e: (f'{{ net.minecraft.entity.Entity _isrc = {src_expr};'
                                  f' if (_isrc != null) KfcGen.itemReplaceFrom({e}, {jstr(slot)}, _isrc, {jstr(srcslot)}); }}')
        else:
            em.reason = "item replace 형식 미지원"; return False
        # 타겟 해소: @s / 단일 / 루프 (공통 헬퍼)
        return _fanout_target(tgt, "_it", body, em, "item replace")

    if command == "clone":
        b = first_arg(args, "begin"); e = first_arg(args, "end"); d = first_arg(args, "destination")
        if not b or not e or not d:
            em.reason = "clone 좌표 누락"; return False
        be = cond_pos_expr(b); ee = cond_pos_expr(e); de = cond_pos_expr(d)
        if be is None or ee is None or de is None:
            em.reason = "clone 좌표 미지원"; return False
        mask_mode = next((m for m in ("masked", "filtered", "replace") if m in nn), "replace")
        move_mode = next((m for m in ("force", "move", "normal") if m in nn), "normal")
        # 모드 없는 단순 clone -> 기존 경량 경로 유지.
        if mask_mode == "replace" and move_mode == "normal":
            em.java.append(
                f'{{ net.minecraft.util.math.BlockPos _cb = net.minecraft.util.math.BlockPos.ofFloored({be});'
                f' net.minecraft.util.math.BlockPos _ce2 = net.minecraft.util.math.BlockPos.ofFloored({ee});'
                f' net.minecraft.util.math.BlockPos _cd = net.minecraft.util.math.BlockPos.ofFloored({de});'
                f' KfcGen.clone(source.getWorld(), _cb.getX(), _cb.getY(), _cb.getZ(), '
                f'_ce2.getX(), _ce2.getY(), _ce2.getZ(), _cd.getX(), _cd.getY(), _cd.getZ()); }}')
            em.kind = "native"; return True
        flt = first_arg(args, "filter")
        fj = "null" if flt is None else jstr(flt)
        em.java.append(
            f'{{ net.minecraft.util.math.BlockPos _cb = net.minecraft.util.math.BlockPos.ofFloored({be});'
            f' net.minecraft.util.math.BlockPos _ce2 = net.minecraft.util.math.BlockPos.ofFloored({ee});'
            f' net.minecraft.util.math.BlockPos _cd = net.minecraft.util.math.BlockPos.ofFloored({de});'
            f' KfcGen.cloneEx(source.getWorld(), _cb.getX(), _cb.getY(), _cb.getZ(), '
            f'_ce2.getX(), _ce2.getY(), _ce2.getZ(), _cd.getX(), _cd.getY(), _cd.getZ(), '
            f'{jstr(mask_mode)}, {fj}, {jstr(move_mode)}); }}')
        em.kind = "native"; return True

    if command == "forceload":
        sub = nn[1] if len(nn) > 1 else None
        if sub not in ("add", "remove"):
            em.reason = f"forceload {sub or '?'} 미지원"; return False
        frm = first_arg(args, "from"); to = first_arg(args, "to")
        if sub == "remove" and nn[2:3] == ["all"]:
            em.java.append('KfcGen.forceloadRemoveAll(source.getWorld());')
            em.kind = "native"; return True
        if not frm:
            em.reason = f"forceload {sub} 좌표 없음"; return False
        # from/to 는 2D 컬럼좌표(x z). cond_pos_expr 은 3D용이라 직접 파싱.
        def _col(raw):
            ps = raw.split()
            if len(ps) != 2:
                return None
            comps = []
            for idx, tok in enumerate(ps):
                axis = "x" if idx == 0 else "z"
                if tok == "~":
                    comps.append(f"((int)Math.floor(source.getPosition().{axis}))")
                elif tok.startswith("~"):
                    try:
                        off = int(tok[1:])
                    except ValueError:
                        return None
                    comps.append(f"((int)Math.floor(source.getPosition().{axis}) + {off})")
                elif tok.startswith("^"):
                    return None  # 로컬좌표(^) 컬럼 미지원
                else:
                    try:
                        comps.append(str(int(tok)))
                    except ValueError:
                        return None
            return (comps[0], comps[1])
        fc = _col(frm); tc = _col(to) if to else fc
        if fc is None or tc is None:
            em.reason = f"forceload {sub} 좌표({frm}) 미지원"; return False
        _helper = "forceloadAdd" if sub == "add" else "forceloadRemove"
        em.java.append(f'KfcGen.{_helper}(source.getWorld(), {fc[0]}, {fc[1]}, {tc[0]}, {tc[1]});')
        em.kind = "native"; return True

    # ---- loot (바닐라 LootCommand: source -> List<ItemStack> -> target) ----
    if command == "loot":
        SRC_KW = {"loot", "mine", "kill", "fish"}
        nodes_only = [s["node"] for s in chain if "node" in s]
        # nodes_only[0] == 'loot' 커맨드. 그 뒤 첫 SRC_KW 가 소스 키워드.
        src_idx = None
        for i in range(1, len(nodes_only)):
            if nodes_only[i] in SRC_KW:
                src_idx = i
                break
        if src_idx is None:
            em.reason = "loot source 키워드 없음"
            return False
        target_nodes = nodes_only[1:src_idx]
        source_nodes = nodes_only[src_idx + 1:]
        src_kw = nodes_only[src_idx]
        # 인자(이름별 파싱순 리스트)를 target/source 구간으로 개수 분할 (pos 충돌 대응).
        _, all_args = split_chain(chain)

        def sect(node_list, name, idx=0):
            vals = all_args.get(name, [])
            tcount = nodes_only[1:src_idx].count(name)  # target 구간 내 해당 인자 개수
            seg = vals[:tcount] if node_list is target_nodes else vals[tcount:]
            return seg[idx].get("raw") if idx < len(seg) else None

        # ---- source: List<ItemStack> 식 ----
        if src_kw == "loot":
            table = sect(source_nodes, "loot_table")
            if not table:
                em.reason = "loot source table 없음"
                return False
            src_expr = f"KfcGen.lootFromTable(source, {jstr(table)})"
        elif src_kw == "mine":
            mpos = sect(source_nodes, "pos")
            mpe = cond_pos_expr(mpos) if mpos else None
            if mpe is None:
                em.reason = f"loot mine 좌표({mpos}) 미지원"
                return False
            tool = sect(source_nodes, "tool")
            tool_expr = f"KfcGen.lootTool(server, source, {jstr(tool) if tool is not None else 'null'})"
            src_expr = (f"KfcGen.lootFromMine(source, "
                        f"net.minecraft.util.math.BlockPos.ofFloored({mpe}), {tool_expr})")
        else:
            em.reason = f"loot source '{src_kw}' 미지원(데미지소스/희소)"
            return False

        loot_decl = f"java.util.List<net.minecraft.item.ItemStack> _loot = {src_expr};"

        # ---- target ----
        tk = target_nodes[0] if target_nodes else None
        # 목적지 좌표 arg 는 targetPos (mine/fish 소스의 pos 와 구분). 이전엔 "pos" 로 찾아
        # None 이 돼 loot spawn/insert/replace block 이 전부 브릿지됐다.
        def _dpos():
            return sect(target_nodes, "targetPos") or sect(target_nodes, "pos")
        if tk == "spawn":
            tpos = _dpos()
            tpe = cond_pos_expr(tpos) if tpos else None
            if tpe is None:
                em.reason = f"loot spawn 좌표({tpos}) 미지원"
                return False
            em.java.append(f"{{ {loot_decl} KfcGen.lootSpawn(source.getWorld(), {tpe}, _loot); }}")
            em.kind = "native"
            return True
        if tk == "insert":
            tpos = _dpos()
            tpe = cond_pos_expr(tpos) if tpos else None
            if tpe is None:
                em.reason = f"loot insert 좌표({tpos}) 미지원"
                return False
            em.java.append(f"{{ {loot_decl} KfcGen.lootInsert(source, "
                           f"net.minecraft.util.math.BlockPos.ofFloored({tpe}), _loot); }}")
            em.kind = "native"
            return True

        if tk == "give":
            players = sect(target_nodes, "players") or sect(target_nodes, "targets")
            if not players:
                em.reason = "loot give players 없음"
                return False
            return _loot_fanout(
                players, "_ge",
                lambda e: (f"if ({e} instanceof net.minecraft.server.network.ServerPlayerEntity _gp)"
                           f" KfcGen.lootGive(_loot, java.util.List.of(_gp));"),
                em, "loot give", loot_decl)

        if tk == "replace":
            sub = target_nodes[1] if len(target_nodes) > 1 else None
            slot = sect(target_nodes, "slot")
            if not slot:
                em.reason = "loot replace slot 없음"
                return False
            cnt = sect(target_nodes, "count")
            cnt_arg = cnt if cnt is not None else "-1"
            if sub == "entity":
                ents = sect(target_nodes, "entities")
                return _loot_fanout(
                    ents, "_le",
                    lambda e: f"if ({e} != null) KfcGen.lootReplaceEntity({e}, {jstr(slot)}, {cnt_arg}, _loot);",
                    em, "loot replace entity", loot_decl)
            if sub == "block":
                bpos = _dpos()
                bpe = cond_pos_expr(bpos) if bpos else None
                if bpe is None:
                    em.reason = f"loot replace block 좌표({bpos}) 미지원"
                    return False
                em.java.append(f"{{ {loot_decl} KfcGen.lootReplaceBlock(source, "
                               f"net.minecraft.util.math.BlockPos.ofFloored({bpe}), {jstr(slot)}, {cnt_arg}, _loot); }}")
                em.kind = "native"
                return True
            em.reason = f"loot replace '{sub}' 미지원"
            return False

        em.reason = f"loot target '{tk}' 미지원(insert 등)"
        return False

    # ---- 이벤트성/복잡: 폴백(instantExecuteFunction) 대상 ----
    return False


def emit_tellraw_title(nn, args, em, cmd):
    holder = first_arg(args, "targets")
    if not holder:
        em.reason = f"{cmd} 대상 없음"
        return False
    ss_cond = at_s_selecteditem_cond(holder)   # @s[nbt={SelectedItemSlot:N}] 특수 처리(핫바 슬롯)
    sel = parse_selector(holder)
    if ss_cond is None and (sel is None or sel.base not in ("a", "p", "r", "s")):
        em.reason = f"{cmd} 셀렉터({holder[:20]}) 미지원(플레이어 한정)"
        return False

    def _player_guards(var):
        gs = []
        gs += _score_conds(sel, var)
        for pid in (sel.predicates if sel else []):
            neg_p = pid.startswith("!"); key = pid[1:] if neg_p else pid
            pn = key if ":" in key else "minecraft:" + key
            g = f'KfcGen.testPredicate(source, {var}, {jstr(pn)})'
            gs.append(f'!({g})' if neg_p else g)
        return gs

    if cmd == "tellraw":
        msg = first_arg(args, "message")
        if msg is None:
            em.reason = "tellraw 메시지 없음"
            return False
        call = f'KfcGen.tellraw(_tp, source, {jstr(msg)});'
    else:  # title
        # 주의: nn[0] 은 명령어 노드 'title' 자체 -> mode 탐색에서 제외해야 actionbar 가
        #       명령어 'title' 에 가려지지 않는다. 모드 리터럴은 targets 뒤에 온다.
        mode = next((n for n in nn[1:] if n in ("actionbar", "title", "subtitle", "times", "clear", "reset")), None)
        if mode == "actionbar":
            msg = first_arg(args, "title") or first_arg(args, "message")
            call = f'KfcGen.titleActionbar(_tp, source, {jstr(msg)});'
        elif mode in ("title", "subtitle"):
            msg = first_arg(args, "title")
            if msg is None:
                em.reason = "title 텍스트 없음"
                return False
            call = f'KfcGen.titleText(_tp, source, {jstr(msg)}, {"true" if mode=="subtitle" else "false"});'
        elif mode == "times":
            fi = first_arg(args, "fadeIn"); st = first_arg(args, "stay"); fo = first_arg(args, "fadeOut")
            if None in (fi, st, fo):
                em.reason = "title times 인자 부족"
                return False
            call = f'KfcGen.titleTimes(_tp, {jint(fi)}, {jint(st)}, {jint(fo)});'
        elif mode in ("clear", "reset"):
            call = f'KfcGen.titleClear(_tp, {"true" if mode == "reset" else "false"});'
        else:
            em.reason = f"title {mode} (1차 미지원)"
            return False
    if ss_cond is not None:
        em.java.append(f'if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp && {ss_cond}) {call}')
        em.kind = "native"
        return True
    if sel.base == "s":
        pg = _player_guards("_tp")
        extra = (" && " + " && ".join(pg)) if pg else ""
        em.java.append(f'if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _tp{extra}) {call}')
        em.kind = "native"
        return True
    em.java.append("for (net.minecraft.server.network.ServerPlayerEntity _tp : ctx.allPlayers) {")
    conds = _tag_conds(sel, '_tp')
    _vc = _volume_cond(sel, "_tp")
    if _vc: conds.append(_vc)
    conds += _player_guards("_tp")
    conds += _selector_extra_conds(sel, "_tp")
    if sel.distance:
        lo, hi = sel.distance
        if hi is not None:
            conds.append(f'_tp.squaredDistanceTo(source.getPosition()) <= {float(hi) ** 2}')
        if lo is not None:
            conds.append(f'_tp.squaredDistanceTo(source.getPosition()) >= {float(lo) ** 2}')
    if conds:
        em.java.append(f'    if (!({" && ".join(conds)})) continue;')
    em.java.append("    " + call)
    em.java.append("}")
    em.kind = "native"
    return True


def _unquote_sel(v: str) -> str:
    v = v.strip()
    if len(v) >= 2 and v[0] == v[-1] and v[0] in ('"', "'"):
        return v[1:-1].replace('\\"', '"').replace("\\'", "'").replace("\\\\", "\\")
    return v


def _tag_conds(sel, var: str) -> list:
    """sel.tags_pos/neg -> [var.getCommandTags().contains(t), !var...contains(t)] 조건 리스트."""
    out = [f'{var}.getCommandTags().contains({jstr(t)})' for t in sel.tags_pos]
    out += [f'!{var}.getCommandTags().contains({jstr(t)})' for t in sel.tags_neg]
    return out


def _score_conds(sel, var: str) -> list:
    """sel.scores -> [scoreMatches(sb, var.getNameForScoreboard(), obj, lo, hi)] 조건 리스트."""
    if not sel:
        return []
    out = []
    for obj_name, (slo, shi) in (sel.scores or {}).items():
        lo_j = _scbound(slo, "Integer.MIN_VALUE")
        hi_j = _scbound(shi, "Integer.MAX_VALUE")
        out.append(f'KfcGen.scoreMatches(sb, {var}.getNameForScoreboard(), '
                   f'{jstr(obj_name)}, {lo_j}, {hi_j})')
    return out


def rotation_conds(sel, evar: str):
    """x_rotation/y_rotation -> 자바 조건식 리스트(축당 1개). 바닐라
       EntitySelectorReader.getRotationPredicate 를 그대로 옮긴 KfcGen.rotInRange 로 위임한다:
       엔티티 각도와 경계 both wrapDegrees([-180,180)) 정규화 후 비교하고, 무한계 쪽은 바닐라
       기본값(min=0/max=359)을 채우며 min>max(래핑)은 wrap 후 d>e 로 OR 판정한다.
       과거처럼 getYaw() 원값을 직접 비교하면 누적 yaw(>180°)·단측 범위(예: y_rotation=45..)에서
       결과 집합이 어긋난다. rotInRange 가 이 전부를 런타임에 정확히 재현한다."""
    out = []
    for field, getter in (("x_rotation", "getPitch"), ("y_rotation", "getYaw")):
        rng = getattr(sel, field)
        if rng is None:
            continue
        lo, hi = rng
        haslo = "true" if lo is not None else "false"
        hashi = "true" if hi is not None else "false"
        loj = _dist_arg(lo) if lo is not None else "0.0"
        hij = _dist_arg(hi) if hi is not None else "0.0"
        out.append(f'KfcGen.rotInRange({evar}.{getter}(), {haslo}, {loj}, {hashi}, {hij})')
    return out


def _selector_extra_conds(sel, var: str) -> list:
    """team/level/name/nbt/advancements 를 'bare boolean 조건식'으로 반환(AND 빌더용).
       parse 는 되지만 이 필드들을 빠뜨리는 소비경로가 있으면 셀렉터가 느슨해져
       거짓양성(예: nbt 조건 누락)이 난다. 그 누락을 막기 위한 공통 조건 생성기."""
    c = []
    if sel.team is not None:
        val, inv = sel.team
        c.append(f'KfcGen.teamIs({var}, {jstr(val)}, {str(inv).lower()})')
    if sel.name is not None:
        val, inv = sel.name
        c.append(f'KfcGen.nameIs({var}, {jstr(val)}, {str(inv).lower()})')
    if sel.level is not None:
        lo, hi = sel.level
        loj = _int_arg(lo, "Integer.MIN_VALUE")
        hij = _int_arg(hi, "Integer.MAX_VALUE")
        c.append(f'KfcGen.levelInRange({var}, {loj}, {hij})')
    for adv_id, spec in sel.advancements:
        if isinstance(spec, dict):
            for crit, exp in spec.items():
                c.append(f'KfcGen.advancementCriterion(source, {var}, {jstr(adv_id)}, '
                         f'{jstr(crit)}, {str(exp).lower()})')
        else:
            c.append(f'KfcGen.advancementDone(source, {var}, {jstr(adv_id)}, {str(spec).lower()})')
    for nbt_str, inv in sel.nbt:
        c.append(f'KfcGen.nbtMatches({var}, {jstr(nbt_str)}, {str(inv).lower()})')
    # 회전(x_rotation/y_rotation): 루프 가드/존재 가드 공통 경로에서 누락되지 않도록 포함.
    c += rotation_conds(sel, var)
    return c


def _sel_tags_only(sel) -> bool:
    """셀렉터가 base + 태그(tags_pos/neg)만 갖는지 — 그 외 필터가 전혀 없으면 True.
       멀티 타겟 존재검사를 태그 기준으로 안전하게 네이티브화할 수 있는 조건."""
    return (not sel.type_id and sel.limit is None and sel.distance is None
            and sel.volume is None and sel.origin is None and not sel.scores
            and not sel.predicates and sel.gamemode is None and sel.team is None
            and sel.name is None and sel.level is None and not sel.advancements
            and not sel.nbt and sel.x_rotation is None and sel.y_rotation is None)


def _sel_has_extra(sel) -> bool:
    """단순 빌더가 처리 못하는 추가 제약(team/level/name/nbt/advancements) 보유 여부."""
    return bool(sel.team is not None or sel.name is not None or sel.level is not None
                or sel.advancements or sel.nbt)


def _selector_extra_guards(sel, var: str) -> list:
    """루프 본문 첫 줄 continue-가드(_selector_extra_conds 재사용)."""
    return [f'    if (!({c})) continue;' for c in _selector_extra_conds(sel, var)]


def _loop_score_pred_conds(sel, var: str):
    """루프 변수 var 에 대한 scores + predicates 의 bare boolean 조건 리스트.
       (단순 루프 빌더가 빠뜨리던 제약 - 누락 시 거짓양성). predicate 미해소면 None."""
    conds = []
    conds += _score_conds(sel, var)
    if sel.predicates:
        pg = predicate_guards(sel.predicates, var, player=(sel.base in ("a", "p", "r")))
        if pg is None:
            return None
        conds += pg
    return conds


_LIMIT_LAMBDA_CONSUMED_EXTRA = False


def _limit_extra_lambda(sel):
    """limit 셀렉터의 모든 비-type/tag/distance 술어(scores/predicate + team/level/name/
       nbt/advancements/rotation)를 nearestN/nearestNAnyType 의 extra 람다 문자열로 묶는다.
       바닐라는 모든 술어를 선택 시점(collect)에서 limit·sort 전에 평가하므로, 이 술어를
       루프 안 continue(=limit 후, 게다가 루프 본문 상태변화에 재노출)가 아니라 후보 수집
       직후(limit 전)에 한 번 평가해야 정확하다.
       반환: None(predicate 미해소→폴백) / ""(추가 술어 없음) / ", _pe -> (...)"(람다).
       람다를 반환할 때 _LIMIT_LAMBDA_CONSUMED_EXTRA 를 True 로 세워, entity_loop_open 이
       _selector_extra_guards 를 limit 후에 중복 부착하지 않도록 한다."""
    global _LIMIT_LAMBDA_CONSUMED_EXTRA
    g = _loop_score_pred_conds(sel, "_pe")
    if g is None:
        return None
    g = list(g) + _selector_extra_conds(sel, "_pe")
    if not g:
        return ""
    _LIMIT_LAMBDA_CONSUMED_EXTRA = True
    return f', _pe -> ({" && ".join(_order_guards(g))})'


def _loop_with_guards(open_lines, sel, var: str):
    """@e 루프 오프너 뒤에 scores/predicates 가드(`if (!c) continue;`)를 덧붙인다.
       predicate 미해소면 None(폴백)."""
    if open_lines is None:
        return None
    g = _loop_score_pred_conds(sel, var)
    if g is None:
        return None
    return list(open_lines) + [f'    if (!({c})) continue;' for c in _order_guards(g)]


def entity_loop_open(sel, var):
    """엔티티 루프 헬퍼(공개 진입점): 코어 + team/level/name 가드."""
    global _LIMIT_LAMBDA_CONSUMED_EXTRA
    _LIMIT_LAMBDA_CONSUMED_EXTRA = False
    out = _entity_loop_open_core(sel, var)
    if out is None:
        return None
    if _LIMIT_LAMBDA_CONSUMED_EXTRA:
        # limit 셀렉터의 extra 술어(team/level/name/nbt/advancements/rotation)가 이미
        # nearestN/nearestNAnyType 의 extra 람다(수집 직후·limit 전)에서 평가됐다. 여기서
        # _selector_extra_guards 를 limit 후 continue 로 또 붙이면 (1) 중복이고 (2) 루프 본문이
        # 해당 속성을 바꾸면 재평가가 어긋난다(바닐라는 선택 시점 스냅샷 평가). → 중복 부착 금지.
        return list(out)
    return list(out) + _selector_extra_guards(sel, var)


def _entity_loop_open_core(sel, var: str):
    """셀렉터 -> 'for (Entity var : ...) {' 여는 줄들. @a/@e 지원. 못하면 None."""
    tp = jarr_tags(sel.tags_pos); tn = jarr_tags(sel.tags_neg)
    lo, hi = sel.distance if sel.distance else (None, None)
    dmin = _dist_arg(lo); dmax = _dist_arg(hi)
    if sel.base == "a":
        if sel.sort in ("nearest", "furthest"):
            # sort=nearest/furthest — origin(현재 source 위치, at 으로 rebind 됨) 기준 정렬 순회.
            # 순위 부여(거리순 max 증가) 알고리즘에서 정렬 순서가 결과를 좌우한다.
            _furth = "true" if sel.sort == "furthest" else "false"
            out = [f"for (net.minecraft.entity.Entity {var} : "
                   f"KfcGen.sortedPlayersByDist(ctx, source.getPosition(), {_furth})) {{"]
        else:
            out = [f"for (net.minecraft.entity.Entity {var} : ctx.allPlayers) {{"]
        conds = _tag_conds(sel, var)
        if sel.volume is not None:
            dx, dy, dz = sel.volume
            conds.append(f'KfcGen.posInBox({box_origin_expr(sel, "source")}, '
                         f'{dx}, {dy}, {dz}, {var})')
        if sel.gamemode is not None:
            ge = f'KfcGen.gamemodeIs({var}, {jstr(sel.gamemode)})'
            conds.append(f'!({ge})' if sel.gamemode_neg else ge)
        if sel.distance:
            _dlo, _dhi = sel.distance
            _dmin = _dist_arg(_dlo)
            _dmax = _dist_arg(_dhi)
            conds.append(f'KfcGen.inRange({box_origin_expr(sel, "source")}, {var}, {_dmin}, {_dmax})')
        _sp = _loop_score_pred_conds(sel, var)
        if _sp is None:
            return None
        conds += _sp
        if conds:
            out.append(f'    if (!({" && ".join(_order_guards(conds))})) continue;')
        return out
    if sel.base in ("e", "n"):
        # type=!X : 양성 타입으로 순회하면 의미가 정반대가 된다.
        # -> 전 엔티티 순회 후 해소된 타입(들)을 제외 가드로 거른다(범용·정확).
        if sel.type_neg and (sel.type_id or sel.type_is_tag):
            ntypes = (resolve_entity_types(sel) if sel.type_is_tag
                      else [entity_type_java(sel.type_id)])
            if ntypes and None not in ntypes:
                excl = " || ".join(f"{var}.getType() == {t}" for t in ntypes)
                return _loop_with_guards(
                    [f'for (net.minecraft.entity.Entity {var} : '
                     f'KfcGen.allEntitiesAnyType(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax})) {{',
                     f'    if ({excl}) continue;'], sel, var)
            if sel.type_is_tag:
                # 미해소 타입태그 -> 런타임 EntityType.isIn(#tag) 으로 제외.
                return _loop_with_guards(
                    [f'for (net.minecraft.entity.Entity {var} : '
                     f'KfcGen.allEntitiesAnyType(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax})) {{',
                     f'    if (KfcGen.entityInTypeTag({var}, {jstr(sel.type_id)})) continue;'], sel, var)
            return None  # 모드 커스텀 단일 타입 부정 - 1차 미지원(폴백)
        if sel.volume is not None and not sel.type_neg:
            dx, dy, dz = sel.volume
            if sel.type_id or sel.type_is_tag:
                types = resolve_entity_types(sel) if sel.type_is_tag else [entity_type_java(sel.type_id)]
                if not types or None in types:
                    return None
                arr = "new net.minecraft.entity.EntityType<?>[]{" + ", ".join(types) + "}"
            else:
                arr = "null"
            return _loop_with_guards([f'for (net.minecraft.entity.Entity {var} : '
                    f'KfcGen.allEntitiesInBox(ctx, {box_origin_expr(sel, "source")}, {arr}, '
                    f'{tp}, {tn}, {dx}, {dy}, {dz})) {{'], sel, var)
        # 타입 미지정 @e/@n -> 전 엔티티 순회(태그/거리 필터). kill/tag/as 등 모든 호출부 공통 적용.
        # limit=N 이면 가까운 N개만(바닐라 sort 미지정=nearest).
        if not sel.type_id and not sel.type_is_tag:
            if (sel.limit or 0) >= 1:
                _ex = _limit_extra_lambda(sel)
                if _ex is None:
                    return None
                _wn = "true" if _want_nearest(sel) else "false"
                if _ex:
                    return [f'for (net.minecraft.entity.Entity {var} : '
                            f'KfcGen.nearestNAnyType(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax}, {sel.limit}, {_wn}{_ex})) {{']
                return _loop_with_guards([f'for (net.minecraft.entity.Entity {var} : '
                        f'KfcGen.nearestNAnyType(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax}, {sel.limit}, {_wn})) {{'], sel, var)
            return _loop_with_guards([f'for (net.minecraft.entity.Entity {var} : '
                    f'KfcGen.allEntitiesAnyType(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax})) {{'], sel, var)
        types = resolve_entity_types(sel) if sel.type_is_tag else (
            [entity_type_java(sel.type_id)] if sel.type_id else None)
        if sel.type_is_tag and (not types or None in types):
            # 컴파일타임 타입태그 JSON 부재 -> 전 엔티티 순회 + 런타임 EntityType.isIn(#tag) 가드.
            neg = "" if sel.type_neg else "!"
            return _loop_with_guards([f'for (net.minecraft.entity.Entity {var} : '
                    f'KfcGen.allEntitiesAnyType(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax})) {{',
                    f'    if ({neg}(KfcGen.entityInTypeTag({var}, {jstr(sel.type_id)}))) continue;'], sel, var)
        if not types or None in types:
            return None
        arr = "new net.minecraft.entity.EntityType<?>[]{" + ", ".join(types) + "}"
        if (sel.limit or 0) >= 1:
            _ex = _limit_extra_lambda(sel)
            if _ex is None:
                return None
            _wn = "true" if _want_nearest(sel) else "false"
            if _ex:
                return [f'for (net.minecraft.entity.Entity {var} : '
                        f'KfcGen.nearestN(ctx, source.getPosition(), {arr}, {tp}, {tn}, {dmin}, {dmax}, {sel.limit}, {_wn}{_ex})) {{']
            _q = f'KfcGen.nearestN(ctx, source.getPosition(), {arr}, {tp}, {tn}, {dmin}, {dmax}, {sel.limit}, {_wn})'
            return _loop_with_guards([f'for (net.minecraft.entity.Entity {var} : {_q}) {{'], sel, var)
        _q = f'KfcGen.allEntities(ctx, source.getPosition(), {arr}, {tp}, {tn}, {dmin}, {dmax})'
        return _loop_with_guards([f'for (net.minecraft.entity.Entity {var} : {_q}) {{'], sel, var)
    return None


def emit_stopsound(nn: list[str], args: dict, em: Emitted) -> bool:
    holder = first_arg(args, "targets")
    sound = first_arg(args, "sound")   # 없으면 카테고리 전체 정지
    cat = next((n for n in nn[1:] if n in (
        "master", "music", "record", "weather", "block", "hostile",
        "neutral", "player", "ambient", "voice")), None)
    sel = parse_selector(holder) if holder else None
    if sel is None or sel.predicates or sel.scores or _sel_has_extra(sel) or sel.base not in ("a", "p", "s"):
        em.reason = f"stopsound 셀렉터({(holder or '?')[:25]}) 미지원(플레이어 한정)"
        return False
    cj = "null" if cat is None else jstr(cat)
    sj = "null" if sound is None else jstr(sound)
    call = f'KfcGen.stopSound(_sp, {cj}, {sj});'
    if sel.base == "s":
        em.java.append('if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _sp) ' + call)
        em.kind = "native"
        return True
    em.java.append("for (net.minecraft.server.network.ServerPlayerEntity _sp : ctx.allPlayers) {")
    conds = _tag_conds(sel, '_sp')
    _vc = _volume_cond(sel, "_sp")
    if _vc: conds.append(_vc)
    if conds:
        em.java.append(f'    if (!({" && ".join(conds)})) continue;')
    em.java.append("    " + call)
    em.java.append("}")
    em.kind = "native"
    return True


def emit_playsound(nn: list[str], args: dict, em: Emitted) -> bool:
    sound = first_arg(args, "sound")
    cat = next((n for n in nn if n in (
        "master", "music", "record", "weather", "block", "hostile",
        "neutral", "player", "ambient", "voice")), "master")
    holder = first_arg(args, "targets")
    pos = first_arg(args, "pos") or "~ ~ ~"
    vol = first_arg(args, "volume")
    pitch = first_arg(args, "pitch")
    minvol = first_arg(args, "minVolume")
    vol = vol if vol is not None else "1"
    pitch = pitch if pitch is not None else "1"
    minvol = minvol if minvol is not None else "0"
    if not sound or not holder:
        em.reason = "playsound 인자 부족"
        return False
    try:
        _mv = float(minvol)
    except ValueError:
        # 매크로/비상수 minVolume — 시맨틱 무시(종전 동작)는 고증 위반이므로 함수 폴백
        em.reason = f"playsound minVolume({minvol[:20]}) 비상수"
        return False
    pe = cond_pos_expr(pos)
    if pe is None:
        em.reason = f"playsound 좌표({pos}) 미지원"
        return False
    sel = parse_selector(holder)
    if sel is None or sel.predicates or sel.scores or _sel_has_extra(sel) or sel.base not in ("a", "p", "r", "s"):
        em.reason = f"playsound 셀렉터({holder[:25]}) 미지원(플레이어 한정)"
        return False
    # minVolume 시맨틱(바닐라 PlaySoundCommand): 가청 반경(vol>1?vol*16:16) 밖 대상은
    # minVolume ≤ 0 이면 미재생, > 0 이면 '플레이어 위치+소리방향×2'에서 minVolume 으로 재생.
    # KfcGen.playSound minVolume 오버로드가 완전 미러 — 0 이면 종전 시그니처(무변화) 유지.
    _mv_arg = f', {jfloat(minvol)}' if _mv != 0.0 else ''
    if sel.base == "s":
        call = (f'KfcGen.playSound(_ps, {jstr(sound)}, {jstr(cat)}, '
                f'{pe}, {jfloat(vol)}, {jfloat(pitch)}{_mv_arg});')
        em.java.append('if (executor instanceof net.minecraft.server.network.ServerPlayerEntity _ps) '
                       + call)
        em.kind = "native"
        return True
    # 다중 대상: 바닐라는 명령 실행 1회당 난수 시드 1개를 전 대상에 공유(랜덤 변형 동일)
    # → 시드를 루프 밖에서 1회 뽑아 전달한다.
    _sd = _fresh_var("_sndSd")
    em.java.append(f'long {_sd} = ctx.world.getRandom().nextLong();')
    call = (f'KfcGen.playSound(_ps, {jstr(sound)}, {jstr(cat)}, '
            f'{pe}, {jfloat(vol)}, {jfloat(pitch)}, {jfloat(minvol)}, {_sd});')
    em.java.append("for (net.minecraft.server.network.ServerPlayerEntity _ps : ctx.allPlayers) {")
    conds = _tag_conds(sel, '_ps')
    _vc = _volume_cond(sel, "_ps")
    if _vc: conds.append(_vc)
    if conds:
        em.java.append(f'    if (!({" && ".join(conds)})) continue;')
    em.java.append("    " + call)
    em.java.append("}")
    em.kind = "native"
    return True


def predicate_guards(predicates, var: str, player: bool = False, src_var: str = "source"):
    """predicate id 목록 -> var 대상 boolean 식 리스트. 미컴파일 predicate 있으면 None.

    안전성 원칙: 항상 instanceof 가드가 포함된 일반식 PREDICATES({E}) 만 사용한다.
    플레이어 전용 호출(getPlayerInput 등)이 들어간 predicate 라도 일반식은
    `(var instanceof ServerPlayerEntity _kpN && _kpN.…())` 형태라 var 의 정적 타입이
    Entity 든 ServerPlayerEntity 든 항상 컴파일·동작이 안전하다.
    (과거엔 player=True 일 때 PREDICATES_PLAYER 의 무가드 `{P}.getPlayerInput()` 을 썼는데,
     루프 변수가 Entity 로 선언되는 경로에서 Entity.getPlayerInput() 컴파일 에러를 유발하는
     잠재 버그였다. player 컨텍스트라도 instanceof 는 항상 참이라 손해가 없으므로 일반식으로 통일.)
    player 인자는 호출부 호환을 위해 남겨두되 무시한다.
    player-input 의 instanceof 바인딩 `_kp` 는 인덱스로 유니크화해 같은 식에서 다중 사용 시 충돌을 막는다.
    `!pred` 접두는 부정 가드로 변환.
    """
    out = []
    for i, pid in enumerate(predicates):
        neg = pid.startswith("!")
        key = pid[1:] if neg else pid
        ex = PREDICATES.get(key)
        if ex is None:
            # 컴파일타임 predicate JSON 부재 -> 런타임 LootCondition 평가로 대체.
            # src_var: 위치·문맥 의존 술어를 재바인딩 소스로 평가(_selector_entity_guards 가
            # 셀렉터의 src_var 를 전달). 미전달 경로는 "source"(단일식은 _resrc/리프치환이 잡음).
            pid_norm = key if ":" in key else "minecraft:" + key
            e = f'KfcGen.testPredicate({src_var}, {var}, {jstr(pid_norm)})'
        else:
            e = ex.replace("{P}", var).replace("{E}", var).replace("_kp", f"_kp{i}")
        out.append(f"!({e})" if neg else e)
    return out


def emit_tag_selector(verb: str, holder: str, name: str, em: Emitted) -> bool:
    """tag add/remove 의 셀렉터 홀더. @n/@e[limit=1]/@p -> 단일, @e[...]/@a -> 전체 루프.
       predicate 동반 시: 컴파일 가능한 predicate 면 본문 가드로 허용, 아니면 거부."""
    sel = parse_selector(holder)
    if sel is None or sel.team is not None or sel.name is not None or sel.level is not None or sel.advancements:
        em.reason = f"tag 셀렉터 홀더({holder[:25] if holder else '?'}) 미지원"
        return False
    if sel.predicates and predicate_guards(sel.predicates, "_t", player=False) is None:
        em.reason = f"tag 셀렉터 predicate 미해소({holder[:25]})"
        return False
    # nbt 필터 → 런타임 NbtHelper.matches 가드(부분일치). 단일/루프 모두 적용.
    _nbt_conds = [(jstr(ns), str(inv).lower()) for ns, inv in sel.nbt]
    fn = "addTag" if verb == "add" else "removeTag"
    single = (sel.base in ("n", "p", "r")) or (sel.limit == 1)
    if single:
        if sel.nbt:
            # nbt 동반 단일: single_entity_expr 미수용 → nearest*Where 로 직접 구성.
            tp = jarr_tags(sel.tags_pos); tn = jarr_tags(sel.tags_neg)
            lo, hi = sel.distance if sel.distance else (None, None)
            dmin = _dist_arg(lo); dmax = _dist_arg(hi)
            pred = " && ".join(f'KfcGen.nbtMatches(_ee, {ns}, {inv})' for ns, inv in _nbt_conds) or "true"
            types = resolve_entity_types(sel) if sel.type_is_tag else (
                [entity_type_java(sel.type_id)] if sel.type_id else None)
            if sel.type_id and (not types or None in types):
                em.reason = f"tag nbt 셀렉터 타입 미해소({holder[:25]})"
                return False
            if sel.scores or sel.predicates or sel.gamemode is not None:
                em.reason = f"tag nbt 셀렉터 복합필터({holder[:25]})"
                return False
            if types:
                arr = "new net.minecraft.entity.EntityType<?>[]{" + ", ".join(types) + "}"
                _fnw3 = "nearestEntityWhere" if _want_nearest(sel) else "firstEntityWhere"
                ent = (f'KfcGen.{_fnw3}(ctx, source.getPosition(), {arr}, {tp}, {tn}, '
                       f'{dmin}, {dmax}, _ee -> ({pred}))')
            else:
                _fnw4 = "nearestEntityAnyTypeWhere" if _want_nearest(sel) else "firstEntityAnyTypeWhere"
                ent = (f'KfcGen.{_fnw4}(ctx, source.getPosition(), {tp}, {tn}, '
                       f'{dmin}, {dmax}, _ee -> ({pred}))')
            em.java.append(f'{{ net.minecraft.entity.Entity _t = {ent};'
                           f' if (_t != null) KfcGen.{fn}(_t, {jstr(name)}); }}')
            em.kind = "native"
            return True
        ent = single_entity_expr(holder)
        if ent is None:
            em.reason = f"tag 단일 셀렉터({holder[:25]}) 해소 불가"
            return False
        pg = predicate_guards(sel.predicates, "_t", player=False) if sel.predicates else []
        cond = "_t != null" + "".join(f" && {g}" for g in pg)
        em.java.append(f'{{ net.minecraft.entity.Entity _t = {ent};'
                       f' if ({cond}) KfcGen.{fn}(_t, {jstr(name)}); }}')
        em.kind = "native"
        return True
    if sel.base == "a":
        em.java.append("for (net.minecraft.server.network.ServerPlayerEntity _t : ctx.allPlayers) {")
        conds = _tag_conds(sel, '_t')
        if sel.predicates:
            conds += predicate_guards(sel.predicates, "_t", player=True)
        if sel.distance is not None:
            dlo, dhi = sel.distance
            conds.append(f'KfcGen.posInRange(source.getPosition(), _t.getPos(), '
                         f'{_dist_arg(dlo)}, {_dist_arg(dhi)})')
        if sel.volume is not None:
            # 볼륨 셀렉터 @a[x,y,z,dx,dy,dz] — 박스 밖 플레이어를 걸러야 한다. 이 조건이
            # 빠지면 tag @a[box] add 가 전체 플레이어에 태그를 부여해(예: multi-hub-player
            # 회수 실패 → 방장 미이양) 바닐라와 어긋난다.
            dx, dy, dz = sel.volume
            conds.append(f'KfcGen.posInBox({box_origin_expr(sel, "source")}, {dx}, {dy}, {dz}, _t)')
        if sel.gamemode is not None:
            gexpr = f'KfcGen.gamemodeIs(_t, {jstr(sel.gamemode)})'
            conds.append(f'!{gexpr}' if sel.gamemode_neg else gexpr)
        conds += _score_conds(sel, '_t')
        for ns, inv in _nbt_conds:
            conds.append(f'KfcGen.nbtMatches(_t, {ns}, {inv})')
        if conds:
            em.java.append(f'    if (!({" && ".join(conds)})) continue;')
        em.java.append(f'    KfcGen.{fn}(_t, {jstr(name)});')
        em.java.append("}")
        em.kind = "native"
        return True
    types = resolve_entity_types(sel) if sel.type_is_tag else (
        [entity_type_java(sel.type_id)] if sel.type_id else None)
    runtime_type_tag = None
    if sel.type_is_tag and (not types or None in types):
        # 컴파일타임 엔티티타입 태그 JSON 부재 -> 런타임 EntityType.isIn(#tag) 필터로 처리.
        # (모드가 제공하는 #kartmobil:kartmodels 등은 게임 런타임엔 등록돼 있다.)
        runtime_type_tag = (sel.type_id, sel.type_neg)
        types = None
    elif (not types or None in types) and sel.type_id:
        em.reason = f"tag @e 셀렉터 타입 미해소({holder[:25]})"
        return False
    tp = jarr_tags(sel.tags_pos); tn = jarr_tags(sel.tags_neg)
    lo, hi = sel.distance if sel.distance else (None, None)
    dmin = _dist_arg(lo); dmax = _dist_arg(hi)
    # 바닐라 셀렉터 평가 순서 = 술어(scores/predicates/nbt/...) → sort → limit.
    # limit 가 있으면 scores 등을 루프 안 continue(=limit 후)로 붙이면 안 된다(첫 N개를 먼저
    # 자른 뒤 술어를 거르므로 거의 안 맞음 = 순위표/find-check 버그). limit 셀렉터는 모든
    # 비-type/tag/distance 술어를 nearestN/nearestNAnyType 의 extra 람다(수집 직후·limit 전)로
    # 넘긴다. _lambda_consumed=True 면 동일 술어를 루프 안에서 중복 부착하지 않는다.
    _has_limit = (sel.limit or 0) >= 1
    _wn = "true" if _want_nearest(sel) else "false"
    _lambda_consumed = False
    if types:
        arr = "new net.minecraft.entity.EntityType<?>[]{" + ", ".join(types) + "}"
        if _has_limit:
            _ex = _limit_extra_lambda(sel)
            if _ex is None:
                em.reason = f"tag @e[limit] 술어 미해소({holder[:25]})"
                return False
            _q = f'KfcGen.nearestN(ctx, source.getPosition(), {arr}, {tp}, {tn}, {dmin}, {dmax}, {sel.limit}, {_wn}{_ex})'
            _lambda_consumed = bool(_ex)
        else:
            _q = f'KfcGen.allEntities(ctx, source.getPosition(), {arr}, {tp}, {tn}, {dmin}, {dmax})'
    else:
        # 타입 미지정 @e (또는 런타임 타입태그) - 전 엔티티 순회
        if _has_limit:
            # 런타임 타입태그/태그-only + limit: 타입태그·scores·nbt 모두 limit 전에 평가.
            # (기존 allEntitiesAny 경로는 limit 를 아예 무시하던 잠재버그도 함께 해소.)
            _g = _loop_score_pred_conds(sel, "_pe")
            if _g is None:
                em.reason = f"tag @e[limit] 술어 미해소({holder[:25]})"
                return False
            _exc = []
            if runtime_type_tag is not None:
                tid, tneg = runtime_type_tag
                _chk = f'KfcGen.entityInTypeTag(_pe, {jstr(tid)})'
                _exc.append(f'!({_chk})' if tneg else _chk)
            _exc += list(_g) + _selector_extra_conds(sel, "_pe")
            _exl = f', _pe -> ({" && ".join(_order_guards(_exc))})' if _exc else ""
            _q = f'KfcGen.nearestNAnyType(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax}, {sel.limit}, {_wn}{_exl})'
            _lambda_consumed = True
        else:
            _q = f'KfcGen.allEntitiesAny(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax})'
    em.java.append(f'for (net.minecraft.entity.Entity _t : {_q}) {{')
    if sel.volume is not None:
        # 볼륨 셀렉터 @e[x,y,z,dx,dy,dz] — 박스 밖 엔티티 제외(누락 시 전체 대상이 됨).
        # (limit+volume 은 현행 팩에 없음; 있으면 별도로 수집 시점 필터가 필요.)
        _vdx, _vdy, _vdz = sel.volume
        em.java.append(f'    if (!KfcGen.posInBox({box_origin_expr(sel, "source")}, '
                       f'{_vdx}, {_vdy}, {_vdz}, _t)) continue;')
    if runtime_type_tag is not None and not _lambda_consumed:
        tid, tneg = runtime_type_tag
        chk = f'KfcGen.entityInTypeTag(_t, {jstr(tid)})'
        em.java.append(f'    if ({"" if tneg else "!"}({chk})) continue;')
    if not _lambda_consumed:
        for _c in _score_conds(sel, '_t'):
            em.java.append(f'    if (!{_c}) continue;')
        if sel.predicates:
            for g in predicate_guards(sel.predicates, "_t", player=False):
                em.java.append(f'    if (!({g})) continue;')
        for ns, inv in _nbt_conds:
            em.java.append(f'    if (!KfcGen.nbtMatches(_t, {ns}, {inv})) continue;')
    em.java.append(f'    KfcGen.{fn}(_t, {jstr(name)});')
    em.java.append("}")
    em.kind = "native"
    return True


def _num(v):
    try: return float(str(v))
    except (TypeError, ValueError): return None


def _time_to_ticks(v):
    """'10' / '10t' / '5s' / '1d' -> 정수 틱. 파싱불가 시 None."""
    s = str(v).strip()
    mult = 1.0
    if s.endswith("t"): s = s[:-1]
    elif s.endswith("s"): s, mult = s[:-1], 20.0
    elif s.endswith("d"): s, mult = s[:-1], 24000.0
    try: return int(float(s) * mult)
    except ValueError: return None


def _blockpos_java(pos) -> str | None:
    """좌표 문자열 -> BlockPos 자바식. 생략 -> 실행 위치. 상대(~/^) -> None(폴백)."""
    if pos is None or str(pos).strip() == "":
        return "net.minecraft.util.math.BlockPos.ofFloored(source.getPosition())"
    parts = str(pos).split()
    if len(parts) != 3 or any(("~" in p or "^" in p) for p in parts):
        return None
    try:
        xi, yi, zi = int(float(parts[0])), int(float(parts[1])), int(float(parts[2]))
    except ValueError:
        return None
    return f"new net.minecraft.util.math.BlockPos({xi}, {yi}, {zi})"


def _angle_java(a) -> str | None:
    """각도 -> float 리터럴. 상대(~/^)는 None(폴백)."""
    s = str(a).strip()
    if s.startswith("~") or s.startswith("^"):
        return None
    try:
        return f"{float(s)}f"
    except ValueError:
        return None


def _player_targets(em: Emitted, targets: str, var: str, body) -> bool | None:
    """플레이어/엔티티 대상 해소(@s/루프/단일) -> body(식). 미해소면 None."""
    if targets == "@s":
        em.java.append(f'if (executor != null) {body("executor")}')
        return True
    sel = parse_selector(targets)
    lo = entity_loop_open(sel, var) if sel else None
    if lo is None:
        ent = single_entity_expr(targets)
        if ent is None:
            return None
        em.java.append(f'{{ net.minecraft.entity.Entity {var} = {ent}; if ({var} != null) {body(var)} }}')
        return True
    em.java.extend(lo)
    em.java.append(f'    {body(var)}')
    em.java.append("}")
    return True


def emit_setworldspawn(nn: list[str], args: dict, em: Emitted) -> bool:
    """setworldspawn [pos] [angle] - 월드 스폰 설정."""
    bp = _blockpos_java(first_arg(args, "pos"))
    if bp is None:
        em.reason = "setworldspawn 좌표 미지원(절대/생략만)"; return False
    angle = first_arg(args, "angle")
    ang = "0.0f" if angle is None else _angle_java(angle)
    if ang is None:
        em.reason = "setworldspawn 각도 미지원(절대만)"; return False
    em.java.append(f"source.getWorld().setSpawnPos({bp}, {ang});")
    em.kind = "native"; return True


def emit_spawnpoint(nn: list[str], args: dict, em: Emitted) -> bool:
    """spawnpoint [targets] [pos] [angle] - 플레이어 스폰포인트 설정."""
    targets = first_arg(args, "targets") or "@s"
    bp = _blockpos_java(first_arg(args, "pos"))
    if bp is None:
        em.reason = "spawnpoint 좌표 미지원(절대/생략만)"; return False
    angle = first_arg(args, "angle")
    ang = "0.0f" if angle is None else _angle_java(angle)
    if ang is None:
        em.reason = "spawnpoint 각도 미지원(절대만)"; return False
    body = lambda p: f"KfcGen.setSpawnPoint(source, {p}, {bp}, {ang});"
    ok = _player_targets(em, targets, "_spnE", body)
    if ok is None:
        em.reason = f"spawnpoint 대상({targets[:20]}) 미해소"; return False
    em.kind = "native"; return True


def emit_enchant(nn: list[str], args: dict, em: Emitted) -> bool:
    """enchant <targets> <enchantment> [level] - 주손 아이템에 인챈트 부여."""
    targets = first_arg(args, "targets")
    ench = first_arg(args, "enchantment")
    if targets is None or ench is None:
        em.reason = "enchant 대상/인챈트 없음"; return False
    level = first_arg(args, "level")
    lvl = "1" if level is None else jint(level)
    body = lambda p: f"KfcGen.enchantHeld(source, {p}, {jstr(str(ench))}, {lvl});"
    ok = _player_targets(em, targets, "_encE", body)
    if ok is None:
        em.reason = f"enchant 대상({targets[:20]}) 미해소"; return False
    em.kind = "native"; return True


def _tp_rot_comp(v: str, field: str) -> str:
    """tp 회전 성분. field 'y'=yaw, 'x'=pitch. 상대(~)는 source 회전 기준."""
    if v == "~":
        return f"source.getRotation().{field}"
    if v.startswith("~"):
        return f"(source.getRotation().{field} + {jfloat(v[1:])})"
    return jfloat(v)


def _tp_relmask(location, rotation) -> int:
    """tp 의 '~'(상대) 성분 → 바닐라 TeleportCommand 의 movementFlags 비트마스크.
    X=1, Y=2, Z=4, Y_ROT=8, X_ROT=16. caret(^)·절대 좌표는 절대(플래그 없음).
    이 마스크가 있어야 teleportToWithRot 가 Entity.teleport 에 상대 플래그를 전달해
    탑승자 전파(getPassengerTeleportTarget)가 바닐라와 동일하게 동작한다 — 상대 회전 시
    차량 텔포가 탑승자 yaw 를 덮어쓰지 않아 디스플레이 리그 이중 회전을 막는다."""
    m = 0
    lp = str(location).split()
    if len(lp) == 3 and not any(p.startswith("^") for p in lp):
        if lp[0].startswith("~"): m |= 1
        if lp[1].startswith("~"): m |= 2
        if lp[2].startswith("~"): m |= 4
    if rotation is not None:
        rp = str(rotation).split()
        if len(rp) == 2:
            if rp[0].startswith("~"): m |= 8
            if rp[1].startswith("~"): m |= 16
    return m


def _tp_targets(em: Emitted, targets: str, body) -> bool | None:
    """tp 대상 해소(@s/루프/단일) 후 body(엔티티식) 호출. 미해소면 None."""
    if targets == "@s":
        em.java.append(f'if (executor != null) {body("executor")}')
        return True
    sel = parse_selector(targets)
    lo = entity_loop_open(sel, "_tpE") if sel else None
    if lo is None:
        ent = single_entity_expr(targets)
        if ent is None:
            return None
        em.java.append(f'{{ net.minecraft.entity.Entity _tpE = {ent}; if (_tpE != null) {body("_tpE")} }}')
        return True
    em.java.extend(lo)
    em.java.append(f'    {body("_tpE")}')
    em.java.append("}")
    return True


def emit_tp(nn: list[str], args: dict, em: Emitted) -> bool:
    """tp/teleport 전체: 엔티티 목적지 / 좌표(절대·상대·caret) / +회전 / +facing(좌표·엔티티)."""
    targets_raw = first_arg(args, "targets")
    dest_ent = first_arg(args, "destination")
    location = first_arg(args, "location")
    rotation = first_arg(args, "rotation")
    facing_loc = first_arg(args, "facingLocation")
    facing_ent = first_arg(args, "facingEntity")
    targets = targets_raw if targets_raw is not None else "@s"

    # ── 목적지가 엔티티 ──
    if dest_ent is not None and location is None:
        if re.fullmatch(r'MACROVAR_\d+', dest_ent):
            # 매크로 목적지($tp @s $(pos), pos="x y z"): 식별자 전략으로 엔티티 자리에 파싱됐지만
            # 런타임 값은 좌표 문자열 — tpMacroDest 가 바닐라 재파싱과 동일하게 좌표를 해석한다.
            ok = _tp_targets(em, targets,
                             lambda t: f'KfcGen.tpMacroDest(source, {t}, {jstr(dest_ent)});')
            if ok is None:
                em.reason = f"tp 대상({targets[:20]}) 미해소"; return False
            em.kind = "native"; return True
        dexpr = "executor" if dest_ent == "@s" else single_entity_expr(dest_ent)
        if dexpr is None:
            em.reason = f"tp 목적지 엔티티({dest_ent[:20]}) 미해소"; return False
        em.java.append("{")
        em.java.append(f"    net.minecraft.entity.Entity _tpDest = {dexpr};")
        ok = _tp_targets(em, targets,
                         lambda t: f"{{ if (_tpDest != null) KfcGen.teleportToEntity({t}, _tpDest); }}")
        em.java.append("}")
        if ok is None:
            em.reason = f"tp 대상({targets[:20]}) 미해소"; return False
        em.kind = "native"; return True

    # ── 목적지가 좌표 ──
    if location is None:
        em.reason = "tp 목적지 없음"; return False
    posv = cond_pos_expr(location, "source")
    if posv is None:
        em.reason = f"tp 좌표({location[:20]}) 미지원(혼합 caret)"; return False

    # 후처리: 회전 / facing
    rot = None
    face = None  # ("pos",) | ("ent", eyes_bool)
    if rotation is not None:
        rp = str(rotation).split()
        if len(rp) != 2:
            em.reason = "tp 회전 형식 오류"; return False
        rot = (_tp_rot_comp(rp[0], "y"), _tp_rot_comp(rp[1], "x"))   # (yaw, pitch)
    elif facing_loc is not None:
        fv = cond_pos_expr(facing_loc, "source")
        if fv is None:
            em.reason = "tp facing 좌표 미지원"; return False
        face = ("pos",)
    elif facing_ent is not None:
        fexpr = "executor" if facing_ent == "@s" else single_entity_expr(facing_ent)
        if fexpr is None:
            em.reason = f"tp facing 엔티티({facing_ent[:20]}) 미해소"; return False
        face = ("ent", ("eyes" in nn))

    em.java.append("{")
    em.java.append(f"    net.minecraft.util.math.Vec3d _tpPos = {posv};")
    if face == ("pos",):
        em.java.append(f"    net.minecraft.util.math.Vec3d _tpFace = {fv};")
    elif face is not None and face[0] == "ent":
        em.java.append(f"    net.minecraft.entity.Entity _tpFE = {fexpr};")

    if rot is not None:
        yaw, pitch = rot
        relmask = _tp_relmask(location, rotation)
        body = lambda t: f"KfcGen.teleportToWithRot({t}, _tpPos.x, _tpPos.y, _tpPos.z, {yaw}, {pitch}, {relmask});"
    elif face == ("pos",):
        body = lambda t: f"KfcGen.teleportToFacing({t}, _tpPos.x, _tpPos.y, _tpPos.z, _tpFace.x, _tpFace.y, _tpFace.z);"
    elif face is not None and face[0] == "ent":
        eyes = "true" if face[1] else "false"
        body = lambda t: f"{{ if (_tpFE != null) KfcGen.teleportToFacingEntity({t}, _tpPos.x, _tpPos.y, _tpPos.z, _tpFE, {eyes}); }}"
    else:
        # 전부 상대(~/^) 미세이동은 movePosition(보간/승객 유지), 절대 혼합은 풀 teleportTo.
        _all_rel = all(p and p[0] in "~^" for p in str(location).split())
        _call = "movePosition" if _all_rel else "teleportTo"
        body = lambda t: f"KfcGen.{_call}({t}, _tpPos.x, _tpPos.y, _tpPos.z);"

    ok = _tp_targets(em, targets, body)
    em.java.append("}")
    if ok is None:
        em.reason = f"tp 대상({targets[:20]}) 미해소"; return False
    em.kind = "native"; return True


def emit_place(nn: list[str], args: dict, em: Emitted) -> bool:
    """place feature <id> [pos] - 구성된 피처 배치. structure/jigsaw/template 는 1차 폴백."""
    sub = nn[1] if len(nn) > 1 else None
    if sub != "feature":
        em.reason = f"place {sub} 1차 미지원(feature 만)"; return False
    fid = first_arg(args, "feature") or first_arg(args, "id")
    if fid is None:
        # 인자 이름이 다를 경우 첫 값 사용
        for vs in args.values():
            if vs and vs[0].get("raw"):
                fid = vs[0]["raw"]; break
    if fid is None:
        em.reason = "place feature id 없음"; return False
    posj = _blockpos_java(first_arg(args, "pos"))
    if posj is None:
        em.reason = "place feature 좌표 미지원(절대/생략만)"; return False
    em.java.append(f"KfcGen.placeFeature(source, {jstr(str(fid))}, {posj});")
    em.kind = "native"; return True


def emit_spreadplayers(nn: list[str], args: dict, em: Emitted) -> bool:
    """spreadplayers <center> <spread> <maxRange> <respectTeams> <targets> - 분산 텔레포트.
       (RNG 산포라 바닐라와 비트동일은 아니나, 중심/최대범위/최소간격/표면배치/팀존중 계약 충족)"""
    if "under" in nn:
        em.reason = "spreadplayers under 변형 1차 미지원"; return False
    center = str(first_arg(args, "center") or "")
    parts = center.split()
    if len(parts) != 2 or any(("~" in p or "^" in p) for p in parts):
        em.reason = f"spreadplayers center({center[:20]}) 절대좌표만"; return False
    cx, cz = _num(parts[0]), _num(parts[1])
    spread = _num(first_arg(args, "spreadDistance"))
    maxr = _num(first_arg(args, "maxRange"))
    rt = first_arg(args, "respectTeams")
    tgt = first_arg(args, "targets")
    if None in (cx, cz, spread, maxr) or tgt is None:
        em.reason = "spreadplayers 인자 부족"; return False
    rtj = "true" if str(rt).lower() == "true" else "false"
    sel = parse_selector(tgt)
    lo = entity_loop_open(sel, "_spE") if sel else None
    em.java.append("{ java.util.List<net.minecraft.entity.Entity> _spT = new java.util.ArrayList<>();")
    if lo is not None:
        em.java.extend(lo)
        em.java.append("    _spT.add(_spE);")
        em.java.append("}")
    elif tgt == "@s":
        em.java.append("    if (executor != null) _spT.add(executor);")
    else:
        ent = single_entity_expr(tgt)
        if ent is None:
            em.reason = f"spreadplayers 대상({tgt[:20]}) 미해소"; return False
        em.java.append(f"    {{ net.minecraft.entity.Entity _spE = {ent}; if (_spE != null) _spT.add(_spE); }}")
    em.java.append(f"    KfcGen.spreadPlayers(source, {cx}, {cz}, {spread}, {maxr}, {rtj}, _spT);")
    em.java.append("}")
    em.kind = "native"; return True


def emit_worldborder(nn: list[str], args: dict, em: Emitted) -> bool:
    """worldborder set/add <size> [time] | center <x> <z> | damage amount/buffer | warning distance/time."""
    sub = nn[1] if len(nn) > 1 else None
    WB = "source.getWorld().getWorldBorder()"
    if sub in ("set", "add"):
        size = _num(first_arg(args, "distance"))
        if size is None:
            em.reason = f"worldborder {sub} 거리 파싱불가"; return False
        t = _num(first_arg(args, "time")) or 0.0
        tgt = f"{size}" if sub == "set" else f"_wb.getSize() + {size}"
        if t <= 0:
            if sub == "set":
                em.java.append(f"{WB}.setSize({size});")
            else:
                em.java.append(f"{{ net.minecraft.world.border.WorldBorder _wb = {WB}; _wb.setSize({tgt}); }}")
        else:
            ms = int(t * 1000)
            em.java.append(f"{{ net.minecraft.world.border.WorldBorder _wb = {WB}; "
                           f"_wb.interpolateSize(_wb.getSize(), {tgt}, {ms}L); }}")
        em.kind = "native"; return True
    if sub == "center":
        pos = first_arg(args, "pos") or ""
        parts = str(pos).split()
        if len(parts) != 2 or any(("~" in p or "^" in p) for p in parts):
            em.reason = f"worldborder center 좌표({pos[:20]}) 미지원(절대좌표만)"; return False
        x, z = _num(parts[0]), _num(parts[1])
        if x is None or z is None:
            em.reason = "worldborder center 좌표 파싱불가"; return False
        em.java.append(f"{WB}.setCenter({x}, {z});")
        em.kind = "native"; return True
    if sub == "damage":
        what = nn[2] if len(nn) > 2 else None
        val = _num(first_arg(args, "damagePerBlock") or first_arg(args, "distance"))
        if val is None:
            em.reason = "worldborder damage 값 파싱불가"; return False
        if what == "amount":
            em.java.append(f"{WB}.setDamagePerBlock({val});")
        elif what == "buffer":
            em.java.append(f"{WB}.setSafeZone({val});")
        else:
            em.reason = f"worldborder damage {what} 미지원"; return False
        em.kind = "native"; return True
    if sub == "warning":
        what = nn[2] if len(nn) > 2 else None
        val = first_arg(args, "distance") or first_arg(args, "time")
        if val is None:
            em.reason = "worldborder warning 값 없음"; return False
        if what == "distance":
            em.java.append(f"{WB}.setWarningBlocks({jint(val)});")
        elif what == "time":
            em.java.append(f"{WB}.setWarningTime({jint(val)});")
        else:
            em.reason = f"worldborder warning {what} 미지원"; return False
        em.kind = "native"; return True
    em.reason = f"worldborder {sub} 미지원(get 등)"; return False


def emit_damage(nn: list[str], args: dict, em: Emitted) -> bool:
    """damage <target> <amount> [<type>] - 엔티티에 데미지. by/at/from 동반은 1차 폴백."""
    if any(x in nn for x in ("at", "from")):
        em.reason = "damage at/from 1차 미지원"; return False
    if "by" in nn:
        # by <attacker>: DamageSources.create(key, attacker) — DamageCommand 동일(직접=원인=attacker)
        tgt = first_arg(args, "target")
        amount = _num(first_arg(args, "amount"))
        if tgt is None or amount is None:
            em.reason = "damage 대상/양 없음"; return False
        dtype = first_arg(args, "damageType")
        tj = "null" if dtype is None else jstr(str(dtype))
        arw = first_arg(args, "entity")
        aent = "executor" if arw == "@s" else (single_entity_expr(arw) if arw else None)
        if aent is None:
            em.reason = f"damage by 대상({arw}) 미해소"; return False
        body = lambda exp: (f'{{ net.minecraft.entity.Entity _dby = {aent};'
                            f' KfcGen.applyDamageBy({exp}, source.getWorld(), {amount}f, {tj}, _dby); }}')
        return _fanout_target(tgt, "_dmgE", body, em, "damage by")
    tgt = first_arg(args, "target")
    amount = _num(first_arg(args, "amount"))
    if tgt is None or amount is None:
        em.reason = "damage 대상/양 없음"; return False
    dtype = first_arg(args, "damageType")
    tj = "null" if dtype is None else jstr(str(dtype))
    body = lambda exp: f"KfcGen.applyDamage({exp}, source.getWorld(), {amount}f, {tj});"
    return _fanout_target(tgt, "_dmgE", body, em, "damage")


def emit_trigger(nn: list[str], args: dict, em: Emitted) -> bool:
    """trigger <objective> [add <v> | set <v>] - 실행 플레이어(@s)의 트리거 점수. enable/lock 시맨틱 재현."""
    obj = first_arg(args, "objective")
    if obj is None:
        em.reason = "trigger 목표 없음"; return False
    val = first_arg(args, "value")
    if "set" in nn:
        mode, v = 2, (jint(val) if val is not None else "0")
    elif "add" in nn:
        mode, v = 1, (jint(val) if val is not None else "1")
    else:
        mode, v = 1, "1"   # trigger <obj> == add 1
    em.java.append(f"KfcGen.triggerScore(source, {jstr(str(obj))}, {mode}, {v});")
    em.kind = "native"; return True


# ── as @e[type=..] 루프 본문의 '엔티티 집합 불변' 증명(화이트리스트, fail-closed) ──
# typeBucketRO(무복사 순회)는 순회 중 버킷이 증분 변형(snapAdd/snapRemove)되면
# ConcurrentModificationException / '선택 시점 고정 집합' 위반이 생긴다. 본문에
# 스폰·킬·브릿지·임의 함수호출·탑승/차원이동이 전혀 없음을 텍스트로 증명한다:
#   · 모든 KfcGen.<name>( 호출이 SAFE 집합에 속해야 하고 (미지 호출 = 거부)
#   · FQCN.execute*/instantExecute/원시 엔티티 제거·탑승 토큰이 없어야 한다.
_LOOP_SAFE_KFC = frozenset((
    "nameOf", "setScore", "addScore", "opScore", "resetScore", "getScore", "getScoreOfEntity",
    "readScore", "readScoreEnt", "scoreMatches", "scoreMatchesEntity", "entityScoreMatches",
    "scoreCmp", "scoreCmpL", "scoreCmpR", "enableTrigger", "addTag", "removeTag",
    "teamIs", "nameIs", "nbtMatches", "levelInRange", "entityInTypeTag", "entityTypeIs",
    "playSound", "soundEvent", "soundCat", "stopSound", "tellraw", "titleText",
    "titleActionbar", "titleTimes", "titleClear", "msgTo", "teamMsg",
    "localOffset", "floorScale", "decodeInts", "cat", "objRef", "snbtCompound", "snbtValue",
    "entityMergeSnbt", "entityPutSnbt", "entityGetDouble", "nbtGetEntity", "nbtGetStorage",
    "nbtGetBlock", "storagePutSnbt", "storagePutNumber", "storageGetDouble",
    "entityPathCount", "storagePathCount", "storageHasPath",
    "effectGive", "effectClear", "effectClearAll", "spawnParticle",
    "setBlock", "blockMatches", "blockStateMatches", "blockInTag", "posLoaded", "posInRange",
    "rangeBox", "anyEntity", "anyEntityAnyType", "anyEntityWhere", "anyPlayerWhere", "anyPlayer",
    "anyEntityInBox", "anyEntityScored", "anyEntityAnyTypeWhere", "anyEntityInTypeTag",
    "anyEntityItemsCond", "anyEntityItemsMatch",
    "firstEntity", "firstEntityWhere", "firstEntityAnyType", "firstEntityAnyTypeWhere",
    "nearestEntity", "nearestEntityWhere", "nearestEntityAnyType", "nearestEntityAnyTypeWhere",
    "nearestPlayer", "nearestPlayerWhere", "allEntities", "allEntitiesAny", "allEntitiesAnyType",
    "entitiesByTypeBox", "entitiesSnapshot", "typeBucketCopy", "typeBucketRO", "passengerFirst",
    "onPassengers", "onVehicle", "entityByUuid", "atEntity", "facing",
    "scheduleFunction", "scheduleNative", "scheduleClear", "getOrCreateContext",
))
_LOOP_UNSAFE_TOKENS = (
    ".execute(", ".executeReturn(", "_executeReturn(", "instantExecute", "runCommand",
    "startRiding", "stopRiding", "dismount", "teleport", ".kill(", ".discard(",
    "spawnEntity", "kickPlayer",
)
_KFC_CALL_RE = re.compile(r"KfcGen\.(\w+)\(")


def _loop_body_entity_safe(stmts) -> bool:
    text = "\n".join(s for s in stmts if s)
    for tok in _LOOP_UNSAFE_TOKENS:
        if tok in text:
            return False
    for name in _KFC_CALL_RE.findall(text):
        if name not in _LOOP_SAFE_KFC:
            return False
    return True


def emit_schedule(nn: list[str], args: dict, em: Emitted) -> bool:
    """schedule function <fn> <time> [append|replace] | schedule clear <fn>."""
    fn = first_arg(args, "function")
    if fn is None:
        em.reason = "schedule 함수 없음"; return False
    fn = str(fn)
    if fn.startswith("#"):
        em.reason = "schedule 함수태그(#) 1차 미지원"; return False
    if "clear" in nn:
        em.java.append(f"KfcGen.scheduleClear(source, {jstr(fn)});")
        em.kind = "native"; return True
    time = first_arg(args, "time")
    ticks = _time_to_ticks(time) if time is not None else None
    if ticks is None:
        em.reason = f"schedule 시간({time}) 파싱불가"; return False
    append = "append" in nn
    if ALL_FIDS and fn in ALL_FIDS and fn not in MACRO_FNS:
        # (#15) 변환된 네이티브 함수 → 자체 큐 스케줄러로 디스패치. 바닐라 타이머의
        # NBT 영속화 + FunctionTimerCallback(원본 mcfunction 인터프리트) 경로를 우회해
        # 발화 시 네이티브 호출 1회가 된다. 진입점이 tick 함수 디스패치 직후
        # KfcGen.tickNativeSchedule 을 호출(바닐라 #tick→타이머 상대 순서 보존).
        # 람다(비캡처=JVM 싱글턴) 사용 — merge_pass 의 호출부 재작성 정규식이
        # 본문 `FQCN.executeReturn(` 을 버킷 메서드로 함께 재작성한다.
        em.java.append(f"KfcGen.scheduleNative(source, {jstr(fn)}, {ticks}L, {str(append).lower()}, "
                       f"_sch -> {fqcn(fn)}.executeReturn(_sch));")
    else:
        # 미변환(외부 네임스페이스)/매크로 함수는 종전대로 바닐라 타이머(고증 동일).
        em.java.append(f"KfcGen.scheduleFunction(source, {jstr(fn)}, {ticks}L, {str(append).lower()});")
    em.kind = "native"; return True


def emit_give(nn: list[str], args: dict, em: Emitted) -> bool:
    """give <targets> <item> [count] - 플레이어에게 아이템 지급(오버플로우 자동 드롭)."""
    tgt = first_arg(args, "targets") or "@s"
    item = first_arg(args, "item")
    count = first_arg(args, "count")
    if item is None:
        em.reason = "give 아이템 없음"; return False
    item = str(item)
    cj = "1" if count is None else jint(count)
    if "[" in item or "{" in item:
        # 컴포넌트/데이터 동반 아이템 -> 런타임 ItemStackArgumentType 파싱(정확).
        ij = jstr(item)
        callc = (lambda p: f'if ({p} instanceof net.minecraft.server.network.ServerPlayerEntity _gp) '
                           f'KfcGen.giveItemString(source, _gp, {ij}, {cj});')
        return _fanout_target(tgt, "_gE", callc, em, "give")
    ij = jstr(item)
    call = (lambda p: f'if ({p} instanceof net.minecraft.server.network.ServerPlayerEntity _gp) '
                      f'KfcGen.giveItem(_gp, {ij}, {cj});')
    return _fanout_target(tgt, "_gE", call, em, "give")


def emit_gamerule(nn: list[str], args: dict, em: Emitted) -> bool:
    """gamerule <rule> <value> - 룰 설정. 룰명은 리터럴(nn[1]). 조회(값 없음)는 폴백."""
    rule = nn[1] if len(nn) > 1 else None
    if rule is None:
        em.reason = "gamerule 룰명 없음"; return False
    value = first_arg(args, "value")
    if value is None:
        em.reason = f"gamerule {rule} 조회(미지원)"; return False
    em.java.append(f'KfcGen.setGameRule(source, {jstr(rule)}, {jstr(str(value))});')
    em.kind = "native"; return True


def emit_xp(nn: list[str], args: dict, em: Emitted) -> bool:
    """xp add|set <players> <amount> [points|levels] - 플레이어 셀렉터 루프."""
    verb = "set" if "set" in nn else ("add" if "add" in nn else None)
    if verb is None:
        return False
    sel = first_arg(args, "target")
    amount = first_arg(args, "amount")
    if sel is None or amount is None:
        return False
    unit = "levels" if "levels" in nn else "points"
    method = {
        ("add", "points"): "addExperience",
        ("add", "levels"): "addExperienceLevels",
        ("set", "points"): "setExperiencePoints",
        ("set", "levels"): "setExperienceLevel",
    }[(verb, unit)]
    coll = bossbar_player_collection(sel, "_xpList")
    if coll is None:
        return False
    lines, expr = coll
    em.java.extend(lines)
    em.java.append(f'for (net.minecraft.server.network.ServerPlayerEntity _xpp : {expr}) {{')
    em.java.append(f'    _xpp.{method}({int(amount)});')
    em.java.append('}')
    return True


def bossbar_player_collection(sel_raw: str, var: str):
    """bossbar set players <sel> 의 플레이어 셀렉터 -> (자바 줄들, 컬렉션 식) 또는 None.
       @a[tag,predicate] / @s 만 1차 지원. type/scores/distance 동반은 보수적으로 None(브릿지)."""
    sel = parse_selector(sel_raw)
    if sel is None or sel.type_id or sel.distance is not None or _sel_has_extra(sel):
        return None
    if sel.predicates and predicate_guards(sel.predicates, "_pp", player=True) is None:
        return None

    def _score_guards():
        gs = []
        gs += _score_conds(sel, '_pp')
        return gs

    lines = [f'java.util.List<net.minecraft.server.network.ServerPlayerEntity> {var} '
             f'= new java.util.ArrayList<>();']
    if sel.base == "a":
        lines.append(f'for (net.minecraft.server.network.ServerPlayerEntity _pp : ctx.allPlayers) {{')
        conds = _tag_conds(sel, '_pp')
        _vc = _volume_cond(sel, "_pp")
        if _vc: conds.append(_vc)
        if sel.predicates:
            conds += predicate_guards(sel.predicates, "_pp", player=True)
        conds += _score_guards()
        if conds:
            lines.append(f'    if (!({" && ".join(conds)})) continue;')
        lines.append(f'    {var}.add(_pp);')
        lines.append("}")
        return lines, var
    if sel.base == "s":
        guards = ["executor instanceof net.minecraft.server.network.ServerPlayerEntity _pp"]
        for t in sel.tags_pos:
            guards.append(f'_pp.getCommandTags().contains({jstr(t)})')
        for t in sel.tags_neg:
            guards.append(f'!_pp.getCommandTags().contains({jstr(t)})')
        if sel.predicates:
            guards += predicate_guards(sel.predicates, "_pp", player=True)
        guards += _score_guards()
        lines.append(f'if ({" && ".join(guards)}) {var}.add(_pp);')
        return lines, var
    return None


def emit_bossbar(nn: list[str], args: dict, em: Emitted) -> bool:
    """bossbar add / set <prop> 네이티브화. source(ServerCommandSource) 가 본문에 있음.
       set: value/color/name/max/visible/players 지원. style/get/list/remove 는 미지원(브릿지)."""
    if len(nn) < 2:
        return False
    verb = nn[1]
    idr = first_arg(args, "id")
    if idr is None:
        em.reason = "bossbar id 없음"
        return False
    idj = jstr(idr)

    if verb == "add":
        name = first_arg(args, "name")
        if name is None:
            return False
        em.java.append(f'KfcGen.bossbarAdd(source, {idj}, {jstr(name)});')
        em.kind = "native"
        return True

    if verb == "get":
        em.java.append("// (침묵 조회) bossbar get — 함수 소스 피드백 억제, standalone 관측효과 없음")
        em.kind = "native"
        return True

    if verb == "set":
        prop = nn[3] if len(nn) > 3 else None
        if prop == "value":
            v = first_arg(args, "value")
            if v is None:
                return False
            em.java.append(f'KfcGen.bossbarSetValue(source, {idj}, {int(v)});')
            em.kind = "native"; return True
        if prop == "max":
            v = first_arg(args, "max")
            if v is None:
                return False
            em.java.append(f'KfcGen.bossbarSetMaxValue(source, {idj}, {int(v)});')
            em.kind = "native"; return True
        if prop == "color":
            color = nn[4] if len(nn) > 4 else None    # color 다음 리터럴(red/blue/...)
            if color is None:
                return False
            em.java.append(f'KfcGen.bossbarSetColor(source, {idj}, {jstr(color)});')
            em.kind = "native"; return True
        if prop == "name":
            name = first_arg(args, "name")
            if name is None:
                return False
            em.java.append(f'KfcGen.bossbarSetName(source, {idj}, {jstr(name)});')
            em.kind = "native"; return True
        if prop == "visible":
            v = first_arg(args, "visible")
            if v in ("true", "false"):
                em.java.append(f'KfcGen.bossbarSetVisible(source, {idj}, {v});')
                em.kind = "native"; return True
        if prop == "visible":
            vis = nn[4] if len(nn) > 4 else None       # true/false 리터럴
            if vis not in ("true", "false"):
                return False
            em.java.append(f'KfcGen.bossbarSetVisible(source, {idj}, {vis});')
            em.kind = "native"; return True
        if prop == "players":
            sel_raw = first_arg(args, "targets")
            if sel_raw is None:
                # `bossbar set <id> players`(인자 없음) = 전체 제거
                em.java.append(f'KfcGen.bossbarSetPlayers(source, {idj}, java.util.List.of());')
                em.kind = "native"; return True
            pc = bossbar_player_collection(sel_raw, "_bp")
            if pc is None:
                em.reason = f"bossbar players 셀렉터({sel_raw[:25]}) 미지원"
                return False
            lines, expr = pc
            em.java.append("{")
            for l in lines:
                em.java.append("    " + l)
            em.java.append(f'    KfcGen.bossbarSetPlayers(source, {idj}, {expr});')
            em.java.append("}")
            em.kind = "native"; return True
        if prop == "style":
            style = nn[4] if len(nn) > 4 else None     # progress/notched_6/notched_10/notched_12/notched_20
            if style is None:
                return False
            em.java.append(f'KfcGen.bossbarSetStyle(source, {idj}, {jstr(style)});')
            em.kind = "native"; return True
        em.reason = f"bossbar set {prop} 미지원"
        return False

    if verb == "remove":
        em.java.append(f'KfcGen.bossbarRemove(source, {idj});')
        em.kind = "native"; return True

    em.reason = f"bossbar {verb} 미지원"
    return False


def emit_scoreboard_displayname(nn: list[str], args: dict, em: Emitted) -> bool:
    """scoreboard players display name <targets> <objective> [<text>].
       텍스트 있으면 표시명 설정, 없으면 복원. 사이드바 라벨/난이도 표기 등에 사용."""
    holder = first_arg(args, "targets")
    obj_n = first_arg(args, "objective")
    if not holder or not obj_n:
        em.reason = "display name 대상/objective 없음"
        return False
    text = first_arg(args, "name")
    if text is not None:
        text_j = jstr(text)  # MACROVAR 치환은 후처리(substitute_macro_token)
        call = f'KfcGen.displayScoreName(source, sb, {{H}}, {jstr(obj_n)}, {text_j});'
    else:
        call = f'KfcGen.displayScoreNameReset(sb, {{H}}, {jstr(obj_n)});'

    hg = self_holder_guard(holder)
    if hg is not None:
        h, guard = hg
        stmt = call.replace("{H}", h)
        em.java.append(f'if ({guard}) {stmt}' if guard else stmt)
        em.kind = "native"
        return True
    if holder.startswith("#") or not holder.startswith("@"):
        em.java.append(call.replace("{H}", jstr(holder)))
        em.kind = "native"
        return True
    ent = single_entity_expr(holder)
    if ent is not None:
        em.java.append(f'{{ net.minecraft.entity.Entity _t = {ent};'
                       f' if (_t != null) {call.replace("{H}", "_t.getNameForScoreboard()")} }}')
        em.kind = "native"
        return True
    sel = parse_selector(holder)
    lo = entity_loop_open(sel, "_dnE") if sel else None
    if lo is None:
        em.reason = f"display name 셀렉터({holder[:25]}) 미지원"
        return False
    em.java.extend(lo)
    em.java.append("    " + call.replace("{H}", "_dnE.getNameForScoreboard()"))
    em.java.append("}")
    em.kind = "native"
    return True


def emit_scoreboard_numberformat(nn: list[str], args: dict, em: Emitted) -> bool:
    """scoreboard players display numberformat <targets> <objective> [fixed <text>|blank|...].
       순위 표시(timerdisplay 에 '$(rank)등' 고정)의 핵심 — 폴백되면 화면 갱신이 안 된다."""
    holder = first_arg(args, "targets")
    obj_n = first_arg(args, "objective")
    if not holder or not obj_n:
        em.reason = "numberformat 대상/objective 없음"
        return False
    # format type: numberformat 다음 토큰
    try:
        fmt = nn[nn.index("numberformat") + 1]
    except (ValueError, IndexError):
        fmt = None
    # fixed/blank/styled/(없음=reset). styled 는 미지원(폴백).
    if "fixed" in nn:
        contents = first_arg(args, "contents")
        if contents is None:
            em.reason = "numberformat fixed 텍스트 없음"
            return False
        text_j = jstr(contents)  # MACROVAR 치환은 후처리(substitute_macro_token)가 처리
        call = (f'KfcGen.displayNumberFormatFixed(source, sb, {{H}}, {jstr(obj_n)}, {text_j});')
    elif "blank" in nn:
        call = f'KfcGen.displayNumberFormatBlank(sb, {{H}}, {jstr(obj_n)});'
    elif fmt in (None, "objective") or fmt not in ("fixed", "blank", "styled"):
        # 인자 없는 numberformat = 기본 복원
        call = f'KfcGen.displayNumberFormatReset(sb, {{H}}, {jstr(obj_n)});'
    else:
        em.reason = f"numberformat {fmt} 미지원(폴백)"
        return False

    # holder 해소: @s / #이름 / 셀렉터 (call 에 'server' 가 등장하면 prelude 자동 주입)
    hg = self_holder_guard(holder)
    if hg is not None:
        h, guard = hg
        stmt = call.replace("{H}", h)
        em.java.append(f'if ({guard}) {stmt}' if guard else stmt)
        em.kind = "native"
        return True
    if holder.startswith("#") or not holder.startswith("@"):
        em.java.append(call.replace("{H}", jstr(holder)))
        em.kind = "native"
        return True
    ent = single_entity_expr(holder)
    if ent is not None:
        em.java.append(f'{{ net.minecraft.entity.Entity _t = {ent};'
                       f' if (_t != null) {call.replace("{H}", "_t.getNameForScoreboard()")} }}')
        em.kind = "native"
        return True
    sel = parse_selector(holder)
    lo = entity_loop_open(sel, "_nfE") if sel else None
    if lo is None:
        em.reason = f"numberformat 셀렉터({holder[:25]}) 미지원"
        return False
    em.java.extend(lo)
    em.java.append("    " + call.replace("{H}", "_nfE.getNameForScoreboard()"))
    em.java.append("}")
    em.kind = "native"
    return True


def emit_scoreboard(sub: str, args: dict, em: Emitted) -> bool:
    if sub == "reset":
        holder = args["targets"][0]["raw"]
        obj_n = args["objective"][0]["raw"] if args.get("objective") else None
        hg = self_holder_guard(holder)
        if hg is not None:
            h, guard = hg
            stmt = f'KfcGen.resetScore(sb, {h}, {("null" if obj_n is None else jstr(obj_n))});'
            em.java.append(f'if ({guard}) {stmt}' if guard else stmt)
            em.kind = "native"
            return True
        if holder.startswith("#") or not holder.startswith("@"):
            em.java.append(f'KfcGen.resetScore(sb, {jstr(holder)}, {("null" if obj_n is None else jstr(obj_n))});')
            em.kind = "native"
            return True
        ent = single_entity_expr(holder)
        if ent is not None:
            em.java.append(f'{{ net.minecraft.entity.Entity _t = {ent};'
                           f' if (_t != null) KfcGen.resetScore(sb, _t.getNameForScoreboard(), '
                           f'{("null" if obj_n is None else jstr(obj_n))}); }}')
            em.kind = "native"
            return True
        sel = parse_selector(holder)
        lo = entity_loop_open(sel, "_rstE") if sel else None
        if lo is None:
            em.reason = f"scoreboard reset 셀렉터({holder[:25]}) 미지원"
            return False
        em.java.extend(lo)
        em.java.append(f'    KfcGen.resetScore(sb, _rstE.getNameForScoreboard(), '
                       f'{("null" if obj_n is None else jstr(obj_n))});')
        em.java.append("}")
        em.kind = "native"
        return True
    if sub == "enable":
        holder = args["targets"][0]["raw"]
        obj_n = args["objective"][0]["raw"]
        hg = self_holder_guard(holder)
        if hg is not None:
            h, guard = hg
            stmt = f'KfcGen.enableTrigger(sb, {h}, {jstr(obj_n)});'
            em.java.append(f'if ({guard}) {stmt}' if guard else stmt)
            return True
        sel = parse_selector(holder)
        if sel is None or sel.predicates:
            em.reason = f"scoreboard enable 셀렉터({holder[:25]}) 미지원"
            return False
        if sel.base == "a":
            em.java.append("for (net.minecraft.server.network.ServerPlayerEntity _t : ctx.allPlayers) {")
            conds = _tag_conds(sel, '_t')
            _vc = _volume_cond(sel, "_t")
            if _vc: conds.append(_vc)
            conds += _selector_extra_conds(sel, "_t")
            if conds:
                em.java.append(f'    if (!({" && ".join(conds)})) continue;')
            em.java.append(f'    KfcGen.enableTrigger(sb, _t.getNameForScoreboard(), {jstr(obj_n)});')
            em.java.append("}")
            em.kind = "native"
            return True
        ent = single_entity_expr(holder)
        if ent is None:
            em.reason = f"scoreboard enable 셀렉터({holder[:25]}) 해소 불가"
            return False
        em.java.append(f'{{ net.minecraft.entity.Entity _t = {ent};'
                       f' if (_t != null) KfcGen.enableTrigger(sb, _t.getNameForScoreboard(), {jstr(obj_n)}); }}')
        em.kind = "native"
        return True
    if sub in ("add", "remove", "set"):
        holder = args["targets"][0]["raw"]
        obj = args["objective"][0]["raw"]
        n = args["score"][0]["raw"]
        hg = self_holder_guard(holder)
        if hg is None:
            # 셀렉터 홀더 -> 단일/전체 엔티티 루프로 처리
            return emit_scoreboard_selector(sub, holder, obj, n, em)
        h, guard = hg
        nj = jint(n)
        if sub == "set":
            stmt = f'KfcGen.setScore(sb, {h}, {jstr(obj)}, {nj});'
        elif sub == "add":
            stmt = f'KfcGen.addScore(sb, {h}, {jstr(obj)}, {nj});'
        else:
            stmt = f'KfcGen.addScore(sb, {h}, {jstr(obj)}, -({nj}));'
        em.java.append(f'if ({guard}) {stmt}' if guard else stmt)
        return True
    if sub == "operation":
        dhg = self_holder_guard(args["targets"][0]["raw"])
        do = args["targetObjective"][0]["raw"]
        op = args["operation"][0]["raw"]
        shg = self_holder_guard(args["source"][0]["raw"])
        so = args["sourceObjective"][0]["raw"]
        if dhg is None or shg is None:
            # operation 의 셀렉터 홀더: 대상이 단일이면 처리, 아니면 1차 브릿지(연쇄 시맨틱 복잡)
            return emit_scoreboard_op_selector(args, em)
        dh, dguard = dhg
        sh, sguard = shg
        guards = [g for g in (dguard, sguard) if g]
        stmt = f'KfcGen.opScore(sb, {dh}, {jstr(do)}, {jstr(op)}, {sh}, {jstr(so)});'
        if guards:
            stmt = f'if ({" && ".join(guards)}) {stmt}'
        em.java.append(stmt)
        return True
    return False


def emit_effect(nn: list[str], args: dict, em: Emitted) -> bool:
    """effect give/clear. 대상은 LivingEntity (@s/@a/@e/@n). give: seconds×20틱."""
    verb = "give" if "give" in nn else ("clear" if "clear" in nn else None)
    if verb is None:
        em.reason = "effect 하위명령 미지원"
        return False
    holder = first_arg(args, "targets")
    if not holder:
        em.reason = "effect 대상 없음"
        return False
    sel = parse_selector(holder)
    if sel is None or sel.scores or sel.predicates:
        em.reason = f"effect 셀렉터({holder[:25]}) 미지원"
        return False
    eff = first_arg(args, "effect")

    if verb == "give":
        if not eff:
            em.reason = "effect give 효과 없음"
            return False
        secs = first_arg(args, "seconds")
        amp = first_arg(args, "amplifier") or "0"
        hide = first_arg(args, "hideParticles")
        if secs is None or secs == "infinite":
            dur = "-1"
        else:
            try:
                dur = str(int(float(secs)) * 20)
            except ValueError:
                em.reason = f"effect give 지속시간({secs}) 미지원"
                return False
        show = "false" if (hide and hide.lower() == "true") else "true"
        call = f'KfcGen.effectGive({{E}}, {jstr(eff)}, {dur}, {amp}, {show});'
    else:  # clear
        call = (f'KfcGen.effectClear({{E}}, {jstr(eff)});' if eff
                else 'KfcGen.effectClearAll({E});')

    if sel.base == "s":
        em.java.append("if (executor != null) " + call.replace("{E}", "executor"))
        em.kind = "native"
        return True
    # 단일 셀렉터(@n/@p/@r/@e[limit=1]) -> 단일 엔티티 해소
    if sel.base in ("n", "p", "r") or sel.limit == 1:
        ent = single_entity_expr(holder)
        if ent is not None:
            stmt = call.replace("{E}", "_fx")
            em.java.append("{ net.minecraft.entity.Entity _fx = " + ent
                           + "; if (_fx != null) " + stmt + " }")
            em.kind = "native"
            return True
    loop = entity_loop_open(sel, "_fx")
    if loop is None:
        em.reason = f"effect 대상 루프({holder[:25]}) 미해소"
        return False
    em.java.extend(loop)
    em.java.append("    " + call.replace("{E}", "_fx"))
    em.java.append("}")
    em.kind = "native"
    return True


def emit_scoreboard_objectives(nn: list[str], args: dict, em: Emitted) -> bool:
    """scoreboard objectives add/remove. add 는 멱등(이미 있으면 무시). 그 외(setdisplay/modify)는 폴백."""
    oi = nn.index("objectives")
    verb = nn[oi + 1] if oi + 1 < len(nn) else None
    if verb == "add":
        name = first_arg(args, "objective")
        crit = first_arg(args, "criteria") or first_arg(args, "criterion") or "dummy"
        disp = first_arg(args, "displayName")
        if not name:
            em.reason = "objectives add 이름 없음"
            return False
        if disp is not None:
            em.java.append(f'KfcGen.ensureObjective(sb, {jstr(name)}, {jstr(crit)});')
            em.java.append(f'KfcGen.setObjectiveDisplay(sb, server, {jstr(name)}, {jstr(disp)});')
            em.kind = "native"
            return True
        em.java.append(f'KfcGen.ensureObjective(sb, {jstr(name)}, {jstr(crit)});')
        em.kind = "native"
        return True
    if verb == "modify":
        name = first_arg(args, "objective")
        if not name:
            em.reason = "objectives modify 이름 없음"
            return False
        if "displayname" in nn:
            disp = first_arg(args, "displayName")
            if disp is None:
                em.reason = "objectives modify displayname 값 없음"
                return False
            em.java.append(f'KfcGen.setObjectiveDisplay(sb, server, {jstr(name)}, {jstr(disp)});')
            em.kind = "native"
            return True
        if "numberformat" in nn:
            if "fixed" in nn:
                contents = first_arg(args, "contents") or first_arg(args, "format")
                if contents is None:
                    em.reason = "objectives modify numberformat fixed 텍스트 없음"
                    return False
                em.java.append(f'KfcGen.objectiveNumberFormatFixed(source, sb, {jstr(name)}, {jstr(contents)});')
            elif "blank" in nn:
                em.java.append(f'KfcGen.objectiveNumberFormatBlank(sb, {jstr(name)});')
            elif "styled" in nn:
                em.reason = "objectives modify numberformat styled 미지원"
                return False
            else:
                em.java.append(f'KfcGen.objectiveNumberFormatReset(sb, {jstr(name)});')
            em.kind = "native"
            return True
        if "rendertype" in nn:
            rt = "hearts" if "hearts" in nn else "integer"
            em.java.append(f'KfcGen.objectiveRenderType(sb, {jstr(name)}, {jstr(rt)});')
            em.kind = "native"
            return True
        if "displayautoupdate" in nn:
            v = first_arg(args, "value")
            if v in ("true", "false"):
                em.java.append(f'KfcGen.objectiveDisplayAutoUpdate(sb, {jstr(name)}, {v});')
                em.kind = "native"
                return True
        em.reason = f"objectives modify {[n for n in nn if n not in ('scoreboard','objectives','modify')]} 1차 미지원"
        return False
    if verb == "setdisplay":
        # scoreboard objectives setdisplay <slot> [<objective>]
        slot = first_arg(args, "slot")
        if not slot:
            em.reason = "objectives setdisplay 슬롯 없음"
            return False
        obj_n = first_arg(args, "objective")
        objj = "null" if obj_n is None else jstr(obj_n)
        em.java.append(f'KfcGen.setObjectiveDisplaySlot(sb, {jstr(slot)}, {objj});')
        em.kind = "native"
        return True
    if verb == "remove":
        name = first_arg(args, "objective")
        if not name:
            em.reason = "objectives remove 이름 없음"
            return False
        em.java.append(f'KfcGen.removeObjective(sb, {jstr(name)});')
        em.kind = "native"
        return True
    em.reason = f"scoreboard objectives {verb} 1차 미지원"
    return False


def emit_scoreboard_selector(sub: str, holder: str, obj: str, n: str, em: Emitted) -> bool:
    """scoreboard set/add/remove 의 셀렉터 홀더. @n/@e[limit=1]/@p -> 단일, @e[...] -> 전체 루프."""
    sel = parse_selector(holder)
    if sel is None:
        em.reason = f"scoreboard 셀렉터 홀더({holder[:25]}) 미지원"
        return False
    fn = {"set": "setScore", "add": "addScore", "remove": "addScore"}[sub]
    nj = jint(n)   # 매크로 토큰(MACROVAR_i) -> Integer.parseInt(...) 로 감싸 String->int 오류 방지
    val = f"-({nj})" if sub == "remove" else nj

    # 단일 대상(@n / limit=1 / @p / @s[..])
    single = (sel.base in ("n", "p", "r")) or (sel.limit == 1) or (sel.base == "s")
    if single:
        ent = single_entity_expr(holder)
        if ent is None:
            em.reason = f"scoreboard 단일 셀렉터({holder[:25]}) 해소 불가"
            return False
        em.java.append(f'{{ net.minecraft.entity.Entity _t = {ent};'
                       f' if (_t != null) KfcGen.{fn}(sb, _t.getNameForScoreboard(), {jstr(obj)}, {val}); }}')
        em.kind = "native"
        return True

    # 전체 루프(@e[...] / @a) -> entity_loop_open 으로 모든 가드(predicate/scores/type태그/extra) 일괄.
    lo2 = entity_loop_open(sel, "_t")
    if lo2 is None:
        em.reason = f"scoreboard 루프 셀렉터({holder[:25]}) 미해소"
        return False
    em.java.extend(lo2)
    em.java.append(f'    KfcGen.{fn}(sb, _t.getNameForScoreboard(), {jstr(obj)}, {val});')
    em.java.append("}")
    em.kind = "native"
    return True


def emit_scoreboard_op_selector(args: dict, em: Emitted) -> bool:
    """operation 의 셀렉터 홀더. 대상=단일, 소스=단일 인 경우만 1차 지원(가장 흔한 @n 패턴)."""
    dholder = args["targets"][0]["raw"]
    do = args["targetObjective"][0]["raw"]
    op = args["operation"][0]["raw"]
    sholder = args["source"][0]["raw"]
    so = args["sourceObjective"][0]["raw"]

    def name_expr(raw):
        hg = self_holder_guard(raw)
        if hg is not None:
            h, guard = hg
            return h, guard, None
        sel = parse_selector(raw)
        if sel is None or sel.predicates or _sel_has_extra(sel):
            return None
        single = (sel.base in ("n", "p", "r")) or (sel.limit == 1)
        if not single:
            return None  # operation 에 다중 대상은 연쇄 시맨틱 복잡 -> 미지원
        ent = single_entity_expr(raw)
        if ent is None:
            return None
        return None, None, ent

    d = name_expr(dholder); s = name_expr(sholder)
    if d is None and s is not None:
        # 다중 '대상' × 단일 소스 - 바닐라는 대상 각각에 연산 적용(소스 고정).
        dsel = parse_selector(dholder)
        if dsel is not None and not dsel.predicates and dsel.base in ("e", "a"):
            sexpr0, sguard0, sent0 = s
            pre = []
            if sent0 is not None:
                pre.append(f'net.minecraft.entity.Entity _ops = {sent0};')
                sname0 = '_ops.getNameForScoreboard()'; sguard0 = '_ops != null'
            else:
                sname0 = sexpr0
            lo = entity_loop_open(dsel, "_od") if dsel.base == "e" else None
            if dsel.base == "a":
                body = [f'for (net.minecraft.server.network.ServerPlayerEntity _od : ctx.allPlayers) {{']
                cs = [f'_od.getCommandTags().contains({jstr(t)})' for t in dsel.tags_pos]
                cs += [f'!_od.getCommandTags().contains({jstr(t)})' for t in dsel.tags_neg]
                _vc = _volume_cond(dsel, "_od")
                if _vc: cs.append(_vc)
                if cs:
                    body.append(f'    if (!({" && ".join(cs)})) continue;')
            elif lo is not None:
                body = list(lo)
            else:
                em.reason = f"scoreboard operation 다중 대상({dholder[:25]}) 타입 미해소"
                return False
            for _c in _score_conds(dsel, '_od'):
                body.append(f'    if (!{_c}) continue;')
            body.append(f'    KfcGen.opScore(sb, _od.getNameForScoreboard(), {jstr(do)}, {jstr(op)}, {sname0}, {jstr(so)});')
            body.append("}")
            if sguard0:
                body = [f'if ({sguard0}) {{'] + ["    " + b for b in body] + ["}"]
            em.java.append("{ " + " ".join(pre) + (" " if pre else ""))
            em.java.extend(body)
            em.java.append("}")
            em.kind = "native"
            return True
    if d is None or s is None:
        # 단일 '대상' × 다중 소스 - 바닐라 executeOperation 은 targets×sources 이중 루프로
        # (target, source) 쌍마다 연산을 순차 적용한다. 대상이 단일이면 소스 루프만 돌면
        # 모든 연산자(</>/+= 등은 누적, = 는 마지막 값)에서 바닐라와 동일 순서/결과.
        # (complete-same-time 의 `#max < @a[tag=...]` min-누적이 이 형태.)
        if s is None and d is not None:
            ssel = parse_selector(sholder)
            if ssel is not None and not ssel.predicates and ssel.base in ("e", "a"):
                dexpr0, dguard0, dent0 = d
                pre = []
                if dent0 is not None:
                    pre.append(f'net.minecraft.entity.Entity _opd = {dent0};')
                    dname0 = '_opd.getNameForScoreboard()'; dguard0 = '_opd != null'
                else:
                    dname0 = dexpr0
                lo = entity_loop_open(ssel, "_os") if ssel.base == "e" else None
                if ssel.base == "a":
                    body = ['for (net.minecraft.server.network.ServerPlayerEntity _os : ctx.allPlayers) {']
                    cs = [f'_os.getCommandTags().contains({jstr(t)})' for t in ssel.tags_pos]
                    cs += [f'!_os.getCommandTags().contains({jstr(t)})' for t in ssel.tags_neg]
                    _vc = _volume_cond(ssel, "_os")
                    if _vc: cs.append(_vc)
                    if cs:
                        body.append(f'    if (!({" && ".join(cs)})) continue;')
                elif lo is not None:
                    body = list(lo)
                else:
                    em.reason = f"scoreboard operation 다중 소스({sholder[:25]}) 타입 미해소"
                    return False
                for _c in _score_conds(ssel, '_os'):
                    body.append(f'    if (!{_c}) continue;')
                body.append(f'    KfcGen.opScore(sb, {dname0}, {jstr(do)}, {jstr(op)}, _os.getNameForScoreboard(), {jstr(so)});')
                body.append("}")
                if dguard0:
                    body = [f'if ({dguard0}) {{'] + ["    " + b for b in body] + ["}"]
                em.java.append("{ " + " ".join(pre) + (" " if pre else ""))
                em.java.extend(body)
                em.java.append("}")
                em.kind = "native"
                return True
        em.reason = "scoreboard operation 셀렉터 홀더(다중/predicate) 미지원"
        return False
    # 엔티티 식이 있으면 임시 변수로 뽑아 null 가드
    pre = []
    dexpr, dguard, dent = d
    sexpr, sguard, sent = s
    decls = []
    if dent is not None:
        decls.append(f'net.minecraft.entity.Entity _d = {dent};')
        dname = '_d.getNameForScoreboard()'; dnull = '_d != null'
    else:
        dname = dexpr; dnull = None
    if sent is not None:
        decls.append(f'net.minecraft.entity.Entity _s = {sent};')
        sname = '_s.getNameForScoreboard()'; snull = '_s != null'
    else:
        sname = sexpr; snull = None
    guards = [g for g in (dguard, sguard, dnull, snull) if g]
    body = f'KfcGen.opScore(sb, {dname}, {jstr(do)}, {jstr(op)}, {sname}, {jstr(so)});'
    if guards:
        body = f'if ({" && ".join(guards)}) {body}'
    em.java.append("{ " + " ".join(decls + [body]) + " }")
    em.kind = "native"
    return True


def _data_source_read_expr(skind: str, args: dict):
    """data modify ... set from <source> 의 NBT 읽기 식. 미지원이면 None."""
    spath = first_arg(args, "sourcePath")
    if not spath:
        return None
    if skind == "entity":
        ssel = first_arg(args, "source")
        if ssel == "@s":
            return f'KfcGen.nbtGetEntity(executor, {jstr(spath)})'
        sent = single_entity_expr(ssel) if ssel else None
        if sent is None:
            return None
        return f'KfcGen.nbtGetEntity({sent}, {jstr(spath)})'
    if skind == "storage":
        sid = first_arg(args, "source")
        if not sid:
            return None
        return f'KfcGen.nbtGetStorage(server, {jstr(sid)}, {jstr(spath)})'
    if skind == "block":
        spos = first_arg(args, "sourcePos")
        pe = cond_pos_expr(spos) if spos else None
        if pe is None:
            return None
        return f'KfcGen.nbtGetBlock(ctx.world, {pe}, {jstr(spath)})'
    return None


def _data_target_write(tkind: str, args: dict, valvar: str):
    """data modify <target> ... 의 NBT 쓰기(set) 식. 미지원이면 None."""
    tpath = first_arg(args, "targetPath")
    if not tpath:
        return None
    if tkind == "entity":
        tsel = first_arg(args, "target")
        if tsel == "@s":
            return f'KfcGen.nbtSetEntity(executor, {jstr(tpath)}, {valvar})'
        tent = single_entity_expr(tsel) if tsel else None
        if tent is None:
            return None
        return f'KfcGen.nbtSetEntity({tent}, {jstr(tpath)}, {valvar})'
    if tkind == "storage":
        tid = first_arg(args, "target")
        if not tid:
            return None
        return f'KfcGen.nbtSetStorage(server, {jstr(tid)}, {jstr(tpath)}, {valvar})'
    if tkind == "block":
        tpos = first_arg(args, "targetPos")
        pe = cond_pos_expr(tpos) if tpos else None
        if pe is None:
            return None
        return (f'KfcGen.blockSetElement(source.getWorld(), '
                f'net.minecraft.util.math.BlockPos.ofFloored({pe}), {jstr(tpath)}, {valvar})')
    return None


def _data_target_write_changed(tkind: str, args: dict, valvar: str):
    """data modify <target> set 의 '변경 종단 수'(int) 를 반환하는 쓰기 식. storage 만 지원(그 외 None).
       바닐라 DataCommand.executeModify: i = NbtPath.put 이 실제 바꾼 종단 수(값 불변 시 0),
       i==0 → 'Nothing changed' 실패(store→0), 성공 시 command result = i (iload i / ireturn 확인)."""
    tpath = first_arg(args, "targetPath")
    if not tpath:
        return None
    if tkind == "storage":
        tid = first_arg(args, "target")
        if not tid:
            return None
        return f'KfcGen.nbtSetStorageChanged(server, {jstr(tid)}, {jstr(tpath)}, {valvar})'
    return None


def emit_data(nn: list[str], args: dict, em: Emitted) -> bool:
    op = nn[1] if len(nn) > 1 else None       # modify / get / merge / remove
    tgtkind = nn[2] if len(nn) > 2 else None   # entity / storage / block

    # ---- data modify ----
    if op == "modify":
        # ── set string <src> [<start> [<end>]] (부분 문자열 복사) ──
        # 바닐라: 소스를 문자열화(asString) 후 [start,end) 서브스트링을 NbtString 으로 set.
        # (nn 의 'from' 검사보다 먼저 — 소스 storage 의 path 가 'from' 같은 이름일 수 있음)
        if "string" in nn:
            si_ = nn.index("string")
            skind = nn[si_ + 1] if si_ + 1 < len(nn) else None
            read = _data_source_read_expr(skind, args)
            if read is None:
                em.reason = f"data set string {skind} 소스 미지원"
                return False
            if tgtkind != "storage":
                em.reason = f"data set string -> {tgtkind} 타겟 1차 미지원"
                return False
            tid = first_arg(args, "target"); tpath = first_arg(args, "targetPath")
            if not tid or not tpath:
                em.reason = "data set string 대상/경로 없음"
                return False
            def _sidx(v, dflt):
                if v is None:
                    return dflt
                if re.fullmatch(r'MACROVAR_\d+', str(v)):
                    # 매크로 인덱스: 런타임 파싱(비수치면 numeric-wrap 이 줄 스킵)
                    return f'Integer.parseInt({jstr(v)})'
                try:
                    int(v); return str(v)
                except (ValueError, TypeError):
                    return None
            s_e = _sidx(first_arg(args, "start"), "0")
            e_e = _sidx(first_arg(args, "end"), "Integer.MIN_VALUE")   # 센티널 = 끝까지
            if s_e is None or e_e is None:
                em.reason = "data set string 인덱스 미지원"
                return False
            em.java.append(f'{{ net.minecraft.nbt.NbtElement _v = {read};'
                           f' if (_v != null) KfcGen.nbtSetStorageString(server, {jstr(tid)}, {jstr(tpath)},'
                           f' _v, {s_e}, {e_e}); }}')
            em.kind = "native"
            return True
        # set value / set from
        is_from = "from" in nn
        # ── set from <entity|storage|block> (NBT 복사) ──
        if is_from:
            mode = next((m for m in ("set", "append", "prepend", "merge") if m in nn), "set")
            fi = nn.index("from")
            skind = nn[fi + 1] if fi + 1 < len(nn) else None
            if mode in ("append", "prepend"):
                read = _data_source_read_expr(skind, args)
                if read is None:
                    em.reason = f"data {mode} from {skind} 소스 미지원"
                    return False
                prep = "true" if mode == "prepend" else "false"
                if tgtkind == "storage":
                    tid = first_arg(args, "target"); tpath = first_arg(args, "targetPath")
                    em.java.append(f'{{ net.minecraft.nbt.NbtElement _v = {read};'
                                   f' if (_v != null) KfcGen.nbtAppendStorage(server, {jstr(tid)}, {jstr(tpath)}, _v, {prep}); }}')
                    em.kind = "native"; return True
                if tgtkind == "entity":
                    tsel = first_arg(args, "target"); tpath = first_arg(args, "targetPath")
                    tent = "executor" if tsel == "@s" else single_entity_expr(tsel)
                    if tent is None:
                        em.reason = f"data {mode} entity 대상({tsel}) 미지원"; return False
                    em.java.append(f'{{ net.minecraft.nbt.NbtElement _v = {read}; net.minecraft.entity.Entity _te = {tent};'
                                   f' if (_v != null && _te != null) KfcGen.nbtAppendEntity(_te, {jstr(tpath)}, _v, {prep}); }}')
                    em.kind = "native"; return True
                em.reason = f"data {mode} from -> {tgtkind} 타겟 미지원"
                return False
            if mode != "set":
                em.reason = f"data modify {mode} from (리스트 복사 1차 미지원)"
                return False
            read = _data_source_read_expr(skind, args)
            write = _data_target_write(tgtkind, args, "_v")
            if read is None:
                em.reason = f"data set from {skind} 소스 미지원"
                return False
            if write is None:
                em.reason = f"data set from -> {tgtkind} 타겟 미지원"
                return False
            em.java.append(f'{{ net.minecraft.nbt.NbtElement _v = {read};'
                           f' if (_v != null) {write}; }}')
            em.kind = "native"
            return True
        if tgtkind == "entity":
            sel = first_arg(args, "target")
            path = first_arg(args, "targetPath")
            mode = next((m for m in ("set", "append", "prepend", "merge") if m in nn), "set")
            # append/prepend value {snbt} (리스트 추가) 지원. from 복사·merge 는 별도.
            if not is_from and mode in ("append", "prepend"):
                if sel == "@s":
                    ent_expr2 = "executor"
                else:
                    ent_expr2 = single_entity_expr(sel)
                    if ent_expr2 is None:
                        em.reason = f"data modify entity 셀렉터({sel}) 미지원"
                        return False
                val2 = args.get("value", [{}])[0]
                raw2 = val2.get("raw")
                if raw2 is None:
                    em.reason = "data modify entity append 값 raw 없음"
                    return False
                prep2 = "true" if mode == "prepend" else "false"
                if sel == "@s":
                    em.java.append(f'if (executor != null) KfcGen.entityAppendSnbt(executor, {jstr(path)}, {jstr(raw2)}, {prep2});')
                else:
                    em.java.append(f'{{ net.minecraft.entity.Entity _de = {ent_expr2};'
                                   f' if (_de != null) KfcGen.entityAppendSnbt(_de, {jstr(path)}, {jstr(raw2)}, {prep2}); }}')
                em.kind = "native"
                return True
            if is_from or mode != "set":
                em.reason = f"data modify entity {mode}{'/from' if is_from else ''} (NBT 복사/리스트 1차 미지원)"
                return False
            # 대상 엔티티 식 결정
            if sel == "@s":
                ent_expr, guard = "executor", "executor != null"
            else:
                ent_expr = single_entity_expr(sel)
                if ent_expr is None:
                    em.reason = f"data modify entity 셀렉터({sel}) 미지원"
                    return False
            val = args.get("value", [{}])[0]
            jval = nbt_value_java(val)
            # 알려진 경로(숫자) -> 자바 API (executor 한정)
            if sel == "@s" and path in KNOWN_ENTITY_PATHS and jval is not None:
                kind, tmpl = KNOWN_ENTITY_PATHS[path]
                em.java.append(f'if (executor != null) {tmpl.format(v=val["raw"])}')
                em.kind = "native"
                return True
            raw = val.get("raw")
            if raw is None:
                em.reason = "data modify entity 값 raw 없음"
                return False
            if sel == "@s":
                em.java.append(f'if (executor != null) KfcGen.entityPutSnbt(executor, {jstr(path)}, {jstr(raw)});')
            else:
                em.java.append(f'{{ net.minecraft.entity.Entity _de = {ent_expr};'
                               f' if (_de != null) KfcGen.entityPutSnbt(_de, {jstr(path)}, {jstr(raw)}); }}')
            em.kind = "native"
            return True
        elif tgtkind == "storage":
            sid = first_arg(args, "target")
            path = first_arg(args, "targetPath")
            if is_from:
                em.reason = "data modify storage set from (NBT 복사) 1차 미지원"
                return False
            mode = next((m for m in ("set", "append", "prepend", "merge", "insert") if m in nn), "set")
            if mode == "insert":
                idxr = first_arg(args, "index")
                valr = args.get("value", [{}])[0].get("raw")
                if idxr is None or valr is None:
                    em.reason = "data modify storage insert 인자 없음"
                    return False
                try:
                    idx = int(idxr)
                except ValueError:
                    em.reason = "data modify storage insert 인덱스 파싱불가"
                    return False
                if idx < 0:
                    em.reason = "data modify storage insert 음수 인덱스 1차 미지원"
                    return False
                em.java.append(f'KfcGen.storageInsertSnbt(server, {jstr(sid)}, {jstr(path)}, {idx}, {jstr(valr)});')
                em.kind = "native"
                return True
            val = args.get("value", [{}])[0]
            jval = nbt_value_java(val)
            if mode == "set" and jval is not None:
                # 숫자 값은 타입 보존 경로 유지
                ntype = {"NbtInt":"int","NbtFloat":"float","NbtDouble":"double"}.get(val.get("kind"),"int")
                em.java.append(f'KfcGen.storagePutNumber(server, {jstr(sid)}, {jstr(path)}, {jval}, {jstr(ntype)});')
                em.kind = "native"
                return True
            # 임의 SNBT 값(리스트/문자열/컴파운드) - 원문 raw 를 그대로 파싱해 기록
            raw = val.get("raw")
            if raw is None:
                em.reason = "data modify storage 값 raw 없음"
                return False
            em.java.append(f'KfcGen.storagePutSnbt(server, {jstr(sid)}, {jstr(path)}, {jstr(raw)}, {jstr(mode)});')
            em.kind = "native"
            return True
        if tgtkind == "block":
            pos = first_arg(args, "targetPos")
            path = first_arg(args, "targetPath")
            mode = next((m for m in ("set", "append", "prepend", "merge") if m in nn), "set")
            if "insert" in nn or "from" in nn or "string" in nn:
                em.reason = "data modify block insert/from/string 1차 미지원"
                return False
            valr = args.get("value", [{}])[0].get("raw")
            pe = cond_pos_expr(pos) if pos else None
            if pe is None or not path or valr is None:
                em.reason = "data modify block 좌표/경로/값 미지원"
                return False
            em.java.append(f'KfcGen.blockPutSnbt(source.getWorld(), '
                           f'net.minecraft.util.math.BlockPos.ofFloored({pe}), '
                           f'{jstr(path)}, {jstr(valr)}, {jstr(mode)});')
            em.kind = "native"
            return True
        return False

    # ---- data remove ----
    if op == "remove":
        path = first_arg(args, "path")
        if tgtkind == "entity":
            sel = first_arg(args, "target")
            if sel == "@s":
                em.java.append(f'if (executor != null) KfcGen.entityRemovePath(executor, {jstr(path)});')
                em.kind = "native"
                return True
            ent = single_entity_expr(sel)
            if ent is None:
                em.reason = f"data remove entity 셀렉터({sel}) 미해소"
                return False
            em.java.append(f'{{ net.minecraft.entity.Entity _de = {ent};'
                           f' if (_de != null) KfcGen.entityRemovePath(_de, {jstr(path)}); }}')
            em.kind = "native"
            return True
        elif tgtkind == "storage":
            sid = first_arg(args, "target")
            em.java.append(f'KfcGen.storageRemovePath(server, {jstr(sid)}, {jstr(path)});')
            em.kind = "native"
            return True
        return False

    # ---- data get (단독; 보통 store 와 함께 쓰임. 단독은 부수효과 없어 무시 가능하나 안전히 브릿지) ----
    if op == "get":
        em.reason = "단독 data get (부수효과 없음 - 보통 store 와 결합; 단독은 브릿지)"
        return False

    # ---- data merge ----
    if op == "merge":
        if tgtkind == "entity":
            sel = first_arg(args, "target")
            raw = first_arg(args, "nbt")
            if raw is None:
                em.reason = "data merge entity nbt 없음"
                return False
            if sel == "@s":
                em.java.append(f'if (executor != null) KfcGen.entityMergeSnbt(executor, {jstr(raw)});')
            else:
                ent_expr = single_entity_expr(sel)
                if ent_expr is None:
                    em.reason = f"data merge entity 셀렉터({sel}) 미지원"
                    return False
                em.java.append(f'{{ net.minecraft.entity.Entity _de = {ent_expr};'
                               f' if (_de != null) KfcGen.entityMergeSnbt(_de, {jstr(raw)}); }}')
            em.kind = "native"
            return True
        if tgtkind == "storage":
            sid = first_arg(args, "target")
            raw = first_arg(args, "nbt")
            if raw is None or not sid:
                em.reason = "data merge storage nbt 없음"
                return False
            em.java.append(f'KfcGen.storageMergeSnbt(server, {jstr(sid)}, {jstr(raw)});')
            em.kind = "native"
            return True
        em.reason = f"data merge {tgtkind} (NBT 컴파운드 병합 - 1차 미지원)"
        return False

    return False


def nbt_value_java(val: dict) -> str | None:
    """NBT 값 -> 자바 리터럴. 숫자류만 (문자열/컴파운드는 None->브릿지)."""
    kind = val.get("kind")
    raw = val.get("raw", "")
    if kind == "NbtInt":
        return raw
    if kind == "NbtFloat":
        return raw if raw.endswith(("f", "F")) else raw + "f"
    if kind == "NbtDouble":
        return raw.rstrip("dD")
    if kind == "NbtShort":
        return f"(short)({raw.rstrip('sS')})"
    if kind == "NbtByte":
        r = raw.strip()
        if r.lower() in ("true", "false"):
            return "(byte)1" if r.lower() == "true" else "(byte)0"
        return f"(byte)({r.rstrip('bB')})"
    if kind == "NbtLong":
        return raw if raw.endswith(("l","L")) else raw + "L"
    return None  # NbtString / NbtCompound / NbtList -> 1차 브릿지


# 알려진 엔티티 NBT 경로 -> 자바 API. 미지원 경로는 NBT 트리 조작 헬퍼로.
KNOWN_ENTITY_PATHS = {
    "Pos[0]": ("set", "if (executor != null) executor.setPos({v}, executor.getY(), executor.getZ());"),
    "Pos[1]": ("set", "if (executor != null) executor.setPos(executor.getX(), {v}, executor.getZ());"),
    "Pos[2]": ("set", "if (executor != null) executor.setPos(executor.getX(), executor.getY(), {v});"),
}


# ───────────────────────── execute emit ─────────────────────────
# execute 의 수정자를 의미별로 분해.
def _coord_str_expr(tok):
    """좌표 토큰을 자바 String 식으로(MACROVAR 포함 가능)."""
    t = str(tok)
    if "MACROVAR_" not in t:
        return jstr(t)
    parts = [p for p in re.split(r'(MACROVAR_\d+)', t) if p]
    return " + ".join(p if p.startswith("MACROVAR_") else jstr(p) for p in parts)


def _abs_coord_java(p, axis):
    """절대 좌표 토큰(상대 ~ / 캐럿 ^ 아님) -> 자바 double 식.
       바닐라 Vec3 인자(tp/positioned/summon/facing 등)의 centerIntegers 규칙:
       x(0)/z(2) 축에서 '정수 리터럴'(소수점 없음)은 블록 중심으로 +0.5, y(1)축·소수점 리터럴은 그대로.
       매크로 변수 좌표는 확장 문자열을 런타임(KfcGen.coord)에서 동일 규칙으로 처리한다."""
    center = axis in (0, 2)
    t = str(p)
    if "MACROVAR_" in t:
        return f'KfcGen.coord({_coord_str_expr(t)}, {"true" if center else "false"})'
    v = float(t)
    if center and '.' not in t:        # 정수 리터럴만 센터링
        v += 0.5
    return repr(v)


def _col_expr(raw):
    """forceload ColumnPos('x z' 블록좌표, ~상대/절대/매크로) -> (xExpr, zExpr) double 식. 미지원 None."""
    if not raw:
        return None
    parts = str(raw).split()
    if len(parts) != 2:
        return None
    out = []
    for i, p in enumerate(parts):
        base = 'source.getPosition().' + ('x', 'z')[i]
        if p == '~':
            out.append(base)
        elif p.startswith('~'):
            try:
                float(p[1:])
            except ValueError:
                return None
            out.append(f'({base} + {jdouble(p[1:])})')
        elif re.fullmatch(r'MACROVAR_\d+', p):
            out.append(f'Double.parseDouble({jstr(p)})')
        else:
            try:
                float(p)
            except ValueError:
                return None
            out.append(jdouble(p))
    return (out[0], out[1])


def cond_pos_expr(raw: str, src_var: str = "source"):
    """조건(if block/loaded)·tp·summon·facing 용 좌표 식 - src_var 기준 절대/상대/caret -> Vec3d 식.
       절대 정수 x/z 는 바닐라 centerIntegers(+0.5) 적용(if block/loaded 는 floor 라 결과 불변)."""
    parts = raw.split()
    if len(parts) != 3:
        return None
    if all(p.startswith('^') for p in parts):
        v = [jdouble(p[1:] or '0') for p in parts]   # 캐럿(로컬) 오프셋은 센터링 안 함
        return (f'KfcGen.localOffset({src_var}.getPosition(), {src_var}.getRotation(), '
                f'{v[0]}, {v[1]}, {v[2]})')
    if any(p.startswith('^') for p in parts):
        return None
    # 순수 상대 `~ ~ ~` 는 소스 위치 그 자체 — new Vec3d(pos.x,pos.y,pos.z) 는 값이 동일한
    # 중복 할당(+getPosition 3회)이다. Vec3d 불변이므로 getPosition() 을 그대로 반환한다
    # (playSound 등 ~32만 사이트의 할당 제거). 오프셋/절대좌표가 하나라도 있으면 종전대로.
    if all(p == '~' for p in parts):
        return f'{src_var}.getPosition()'
    comps = []
    for i, p in enumerate(parts):
        base = f'{src_var}.getPosition().' + ('x', 'y', 'z')[i]
        comps.append(base if p == '~' else (f'({base} + {jdouble(p[1:])})' if p.startswith('~') else _abs_coord_java(p, i)))
    return f'new net.minecraft.util.math.Vec3d({", ".join(comps)})'


def pos_rebind_expr(raw: str, src_var: str):
    """positioned <pos> -> withPosition 식. caret(^)/상대(~)/절대 지원. 혼합(^와 ~/절대)은 None."""
    parts = raw.split()
    if len(parts) != 3:
        return None
    if all(p.startswith('^') for p in parts):
        vals = [jdouble(p[1:] or '0') for p in parts]
        return (f'{src_var}.withPosition(KfcGen.localOffset({src_var}.getPosition(), '
                f'{src_var}.getRotation(), {vals[0]}, {vals[1]}, {vals[2]}))')
    if any(p.startswith('^') for p in parts):
        return None  # caret 혼합은 마크도 불허
    comps = []
    for i, p in enumerate(parts):
        base = f'{src_var}.getPosition().' + ('x', 'y', 'z')[i]
        if p == '~':
            comps.append(base)
        elif p.startswith('~'):
            comps.append(f'({base} + {jdouble(p[1:])})')
        else:
            comps.append(_abs_coord_java(p, i))   # positioned <pos> 도 centerIntegers 적용
    return f'{src_var}.withPosition(new net.minecraft.util.math.Vec3d({", ".join(comps)}))'


def rot_rebind_expr(raw: str, src_var: str):
    """rotated <yaw> <pitch> -> withRotation 식. Vec2f(x=pitch, y=yaw)."""
    parts = raw.split()
    if len(parts) != 2:
        return None
    def comp(v, field):
        if v == '~':
            return f'{src_var}.getRotation().{field}'
        if v.startswith('~'):
            return f'({src_var}.getRotation().{field} + {jfloat(v[1:])})'
        return jfloat(v)
    yaw = comp(parts[0], 'y')
    pitch = comp(parts[1], 'x')
    return f'{src_var}.withRotation(new net.minecraft.util.math.Vec2f({pitch}, {yaw}))'


# rebind 뒤에 와도 안전한(위치 비의존) head 노드들
_REBIND_SAFE_AFTER = {"if", "unless", "score", "target", "targetObjective", "matches", "range",
                      "source", "sourceObjective", "positioned", "pos", "rotated", "rotation",
                      "items", "entity", "entities", "slots", "item_predicate", "run"}



_on_depth = 0  # on passengers/vehicle 재귀 깊이 - 변수명 유일화용 (emit_line 마다 리셋)


def emit_execute_with_src(line: str, chain: list[dict], em: Emitted, src_var: str) -> bool:
    """emit_execute 를 돌리되, 생성된 본문의 'source' 를 src_var 로 치환.
       on passengers/vehicle 의 재귀 본문에서 새 소스를 쓰게 하기 위함."""
    tmp = Emitted(line=line)
    if not emit_execute(line, chain, tmp):
        em.reason = tmp.reason
        return False
    em.java = [re.sub(r'\bsource\b', src_var, b) for b in tmp.java]
    em.kind = tmp.kind
    return True


# ─────────────────────────────────────────────────────────────────────────────
# compile_execute_v2 - execute 를 "소스 변환 파이프라인" 폴드로 일반화.
#
#   execute m1 m2 ... mN run leaf  =  m1 을 접고 그 결과 각각에 m2 ..., 최내곽에서 leaf 1회.
#   각 모디파이어는 코드 형태가 셋 중 하나:
#     - 게이트(if/unless)         : ["if (cond) {"], ["}"]
#     - 리바인드(positioned/...)  : 소스 변수 교체 (parse_modifiers 재사용)
#     - 팬아웃(as/on/...)         : for/if 루프 프레임 + ctx(실행자-소스) 교체
#   리프는 기존 emit_target 으로 뽑고 executor/source 를 현재 ctx 변수로 치환.
#   store 는 모디파이어가 아니라 결과 싱크(다음 단계). 골격은 store 미포함 -> 기존 경로가 처리.
#
# 회귀 방어: 기존 _emit_execute_legacy 가 먼저 시도되고, 실패할 때만 폴드가 호출(순증가만).
# ─────────────────────────────────────────────────────────────────────────────

def _v2_store_dst(sub, exe, src):
    """store 서브슬라이스 -> writer(valexpr)->list[str]. result 만 지원(success/block/bossbar -> None)."""
    nodes, args = split_chain(sub)
    nn = [n["node"] for n in nodes]
    if len(nn) < 3 or nn[0] != "store" or nn[1] != "result":
        return None
    dsttype = nn[2]
    if dsttype == "score":
        holder, obj = store_score_dst(args)
        if holder is None or obj is None:
            return None
        if holder == "@s":
            name = f'({exe} == null ? "<no-executor>" : {exe}.getNameForScoreboard())'
            return lambda v: [f'if ({exe} != null) KfcGen.setScore(sb, {name}, {jstr(obj)}, (int)({v}));']
        h = holder_expr(holder)
        if h is not None:
            return lambda v: [f'KfcGen.setScore(sb, {h}, {jstr(obj)}, (int)({v}));']
        ent = single_entity_expr(holder)
        if ent is None:
            return None
        ent = _v2_subst([ent], exe, src)[0]
        return lambda v: [f'{{ net.minecraft.entity.Entity _se = {ent};'
                          f' if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), '
                          f'{jstr(obj)}, (int)({v})); }}']
    if dsttype == "storage":
        sid = first_arg(args, "target"); path = first_arg(args, "path")
        if sid is None or path is None:
            return None
        ntype = nbt_store_type(nn, 0); scale = first_arg(args, "scale") or "1"
        return lambda v: [f'KfcGen.storagePutNumber(server, {jstr(sid)}, {jstr(path)}, '
                          f'{scaled(v, scale)}, {jstr(ntype)});']
    if dsttype == "entity":
        esel = first_arg(args, "target"); path = first_arg(args, "path")
        if path is None:
            return None
        etype = nbt_store_type(nn, 0); escale = first_arg(args, "scale") or "1"
        ent = exe if esel == "@s" else None
        if ent is None:
            ent = single_entity_expr(esel)
            if ent is None:
                return None
            ent = _v2_subst([ent], exe, src)[0]
        return lambda v: [f'{{ net.minecraft.entity.Entity _se = {ent};'
                          f' if (_se != null) KfcGen.entityPutNumberPath(_se, {jstr(path)}, '
                          f'{jstr(etype)}, {scaled(v, escale)}); }}']
    return None


def _v2_is_multi(sel_raw):
    """셀렉터가 다중(루프 필요)인지. 단일(@s/@n/@p/@r/limit=1)이면 False."""
    sel = parse_selector(sel_raw)
    if sel is None:
        return False
    if sel.base == "s" or sel.base in ("n", "p", "r"):
        return False
    if sel.limit == 1:
        return False
    return True  # @a / @e(무제한)


def _v2_segments(head: list[dict]):
    """head 를 순서대로 분할:
       ("pre", subslice) | ("as", sel) | ("on", rel) | ("posrot", (mode, sel))
       팬아웃은 as/on 및 다중 셀렉터 at/rotated as/positioned as. 단일 at/rotated/positioned 는 pre."""
    segs = []
    i = 0
    pre_start = 0

    def flush(end):
        if end > pre_start:
            segs.append(("pre", head[pre_start:end]))

    def targets_after(k):
        """head[k] 이후의 targets 노드+arg 에서 sel_raw 와 다음 인덱스 반환."""
        if k < len(head) and head[k].get("node") == "targets":
            if k + 1 < len(head) and head[k + 1].get("arg") == "targets":
                return head[k + 1]["value"]["raw"], k + 2
            return head[k].get("range"), k + 1
        return None, k

    while i < len(head):
        node = head[i].get("node")
        if node == "store":
            flush(i)
            STOP = {"if", "unless", "as", "at", "on", "run", "store",
                    "positioned", "rotated", "facing", "anchored", "align", "in"}
            j = i + 1
            while j < len(head):
                nd = head[j].get("node")
                if nd is not None and nd in STOP:
                    break
                j += 1
            segs.append(("store", head[i:j]))
            i = pre_start = j
        elif node == "facing" and i + 1 < len(head) and head[i + 1].get("node") == "entity":
            # facing entity <sel> [anchor]  - 다중이면 팬아웃, 단일/좌표 facing 은 pre
            sel_raw = head[i + 2].get("range") if (i + 2 < len(head)
                       and head[i + 2].get("node") == "targets") else None
            anchor_raw = head[i + 3].get("range") if (i + 3 < len(head)
                          and head[i + 3].get("node") == "anchor") else "feet"
            if sel_raw is not None and _v2_is_multi(sel_raw):
                flush(i)
                segs.append(("facing", (sel_raw, anchor_raw == "eyes")))
                STOP = {"if", "unless", "as", "at", "on", "run", "store",
                        "positioned", "rotated", "facing", "anchored", "align", "in"}
                j = i + 1
                while j < len(head):
                    nd = head[j].get("node")
                    if nd is not None and nd in STOP:
                        break
                    j += 1
                i = pre_start = j
            else:
                i += 1
        elif node == "as":
            sel_raw, j = targets_after(i + 1)
            if sel_raw is None:
                return None
            flush(i)
            segs.append(("as", sel_raw))
            i = pre_start = j
        elif node == "on":
            rel = head[i + 1].get("node") if i + 1 < len(head) else None
            if rel not in ("passengers", "vehicle", "attacker", "target", "origin", "controller", "owner"):
                return None
            flush(i)
            segs.append(("on", rel))
            i = pre_start = i + 2
        elif node == "at":
            sel_raw, j = targets_after(i + 1)
            if sel_raw is not None and _v2_is_multi(sel_raw):
                flush(i)
                segs.append(("posrot", ("at", sel_raw)))
                i = pre_start = j
            else:
                i += 1  # 단일 at -> pre(parse_modifiers)
        elif node in ("rotated", "positioned") and i + 1 < len(head) and head[i + 1].get("node") == "as":
            sel_raw, j = targets_after(i + 2)
            mode = "rotated_as" if node == "rotated" else "positioned_as"
            # rotated/positioned as <셀렉터> 는 parse_modifiers(pre)가 처리 못 하므로
            # 단일-다중 무관하게 항상 팬아웃(loop+rebind). 루프가 0/1/N 엔티티를 균일하게 처리
            # (0개면 leaf 미실행 - 바닐라 동일). @s 면 executor 1회 루프.
            if sel_raw is not None:
                flush(i)
                segs.append(("posrot", (mode, sel_raw)))
                i = pre_start = j
            else:
                i += 1
        else:
            i += 1
    flush(len(head))
    return segs


def _v2_fanout_facing(sel_raw, eyes, exe, src, depth):
    """facing entity <셀렉터> - 각 엔티티를 바라보는 회전 팬아웃(실행자 불변).
       단일(@s/@n/@p/@r/limit=1)은 루프 없이 1회 재바인딩."""
    sel = parse_selector(sel_raw)
    if sel is None:
        return None
    nt, ns = f"_vT{depth}", f"_vS{depth}"
    single = (sel.base == "s") or (sel.base in ("n", "p", "r")) or (sel.limit == 1)
    if single:
        ent = single_entity_expr(sel_raw)
        if ent is None:
            return None
        ent = _v2_subst([ent], exe, src)[0]
        opens = [f"{{ net.minecraft.entity.Entity {nt} = {ent};",
                 f"  if ({nt} != null) {{",
                 f"    ServerCommandSource {ns} = KfcGen.facingEntity({src}, {nt}, {'true' if eyes else 'false'});"]
        return (opens, ["} }"], exe, ns)
    loop = entity_loop_open(sel, nt)
    if loop is None:
        return None
    loop = _v2_subst(loop, exe, src)
    rebind = f"ServerCommandSource {ns} = KfcGen.facingEntity({src}, {nt}, {'true' if eyes else 'false'});"
    opens = list(loop) + ["    " + rebind]
    return (opens, ["}"], exe, ns)


def _v2_fanout_posrot(mode, sel_raw, exe, src, depth):
    """at/rotated as/positioned as <셀렉터> - 위치/회전 팬아웃(실행자 불변, 소스만 교체).
       단일(@s/@n/@p/@r/limit=1)은 루프 없이 single_entity_expr 로 1회 재바인딩."""
    sel = parse_selector(sel_raw)
    if sel is None:
        return None
    nt, ns = f"_vT{depth}", f"_vS{depth}"

    def _rebind_expr(tv):
        if mode == "at":
            return f"KfcGen.atEntity({src}, {tv})"
        elif mode == "rotated_as":
            return (f"{src}.withRotation("
                    f"new net.minecraft.util.math.Vec2f({tv}.getPitch(), {tv}.getYaw()))")
        else:  # positioned_as
            return f"{src}.withPosition({tv}.getPos())"

    single = (sel.base == "s") or (sel.base in ("n", "p", "r")) or (sel.limit == 1)
    if single:
        ent = single_entity_expr(sel_raw)
        if ent is None:
            return None
        ent = _v2_subst([ent], exe, src)[0]
        opens = [f"{{ net.minecraft.entity.Entity {nt} = {ent};",
                 f"  if ({nt} != null) {{",
                 f"    ServerCommandSource {ns} = {_rebind_expr(nt)};"]
        return (opens, ["} }"], exe, ns)

    loop = entity_loop_open(sel, nt)
    if loop is None:
        return None
    loop = _v2_subst(loop, exe, src)
    opens = list(loop) + ["    ServerCommandSource " + ns + " = " + _rebind_expr(nt) + ";"]
    return (opens, ["}"], exe, ns)   # 실행자(exe) 불변


def _v2_subst(lines, exe, src):
    out = []
    for b in lines:
        if exe != "executor":
            b = re.sub(r'\bexecutor\b', exe, b)
        if src != "source":
            b = re.sub(r'\bsource\b', src, b)
        out.append(b)
    return out




def _nullprop_rebinds(rebinds):
    """rebind 체인에서 한 번 nullable(셀렉터 매치실패/facingEntity) 이 생기면, 이후 그 소스를
       deref 하는 모든 rebind 를 (base == null ? null : expr) 로 균일 래핑한다.
       (개별 핸들러의 ': null' 삼항이 base 자신의 null 은 못 막는 문제를 일괄 해소.)"""
    out = []
    nullable = set()
    src_pat = re.compile(r'\b(kfcSrc\w*|source|_asSrc\w*|_vS\d+|_on\w*)\b')
    for line in rebinds:
        m = re.match(r'^(\s*)ServerCommandSource (\w+) = (.+);\s*$', line)
        if not m:
            out.append(line); continue
        ind, var, rhs = m.groups()
        refs = set(src_pat.findall(rhs)) - {var}
        base_null = refs & nullable
        if base_null:
            base = sorted(base_null)[0]
            if not rhs.lstrip().startswith(f'({base} == null ? null'):
                rhs = f'({base} == null ? null : {rhs})'
        out.append(f'{ind}ServerCommandSource {var} = {rhs};')
        if (': null)' in rhs) or ('KfcGen.facingEntity(' in rhs) or ('== null ? null' in rhs):
            nullable.add(var)
    return out


def _rebinds_nullable(rebinds) -> bool:
    """셀렉터 rebind 의 매치실패(null 소스) 가능성이 체인에 존재하는지.
       facingEntity 는 대상 엔티티 없으면 null 반환(fork 사망)하므로 nullable."""
    return any((" : null)" in s) or ("== null ? null" in s) or ("KfcGen.facingEntity(" in s)
               for s in (rebinds or []))


def _v2_final_src(rebinds, default):
    last = default
    for s in rebinds:
        m = re.search(r'ServerCommandSource\s+(\w+)\s*=', s)
        if m:
            last = m.group(1)
    return last


def _v2_pre_frame(sub, exe, src):
    """조건/단순리바인드 구간 -> (opens, closes, new_src). 미지원/루프/store 면 None."""
    mods = parse_modifiers(sub, src)
    if mods is None:
        return None
    conds, loops, uns, rebinds = mods
    if uns or loops:
        return None
    if exe != "executor":
        rebinds = _v2_subst(rebinds, exe, src)
        conds = _v2_subst(conds, exe, src)
    new_src = _v2_final_src(rebinds, src)
    opens, closes = [], []
    if rebinds:
        opens += ["{"] + ["    " + s for s in rebinds]
        closes = ["}"] + closes
        if _rebinds_nullable(rebinds):
            opens.append(f"    if ({new_src} != null) {{")
            closes = ["    }"] + closes
    for c in conds:
        opens.append(f"if ({c}) {{")
        closes = ["}"] + closes
    return (opens, closes, new_src)


def _v2_fanout_as(sel_raw, exe, src, depth):
    sel = parse_selector(sel_raw)
    if sel is None:
        return None
    ne, ns = f"_vE{depth}", f"_vS{depth}"
    single = (sel.base == "s") or (sel.base in ("n", "p", "r")) or (sel.limit == 1)
    if single:
        ent = single_entity_expr(sel_raw)
        if ent is None:
            return None
        ent = _v2_subst([ent], exe, src)[0]
        opens = [f"{{ net.minecraft.entity.Entity {ne} = {ent};",
                 f"  if ({ne} != null) {{",
                 f"    ServerCommandSource {ns} = {src}.withEntity({ne});"]
        return (opens, ["} }"], ne, ns)
    loop = entity_loop_open(sel, ne)
    if loop is None:
        return None
    loop = _v2_subst(loop, exe, src)
    opens = list(loop) + [f"    ServerCommandSource {ns} = {src}.withEntity({ne});"]
    return (opens, ["}"], ne, ns)


def _v2_fanout_on(rel, exe, src, depth):
    ne, ns = f"_vE{depth}", f"_vS{depth}"
    _single = {"vehicle": "onVehicle", "attacker": "onAttacker", "target": "onTarget", "origin": "onOrigin", "controller": "onController", "owner": "onOwner"}
    if rel in _single:
        opens = [f"{{ ServerCommandSource {ns} = KfcGen.{_single[rel]}({src});",
                 f"  if ({ns} != null) {{",
                 f"    net.minecraft.entity.Entity {ne} = {ns}.getEntity();"]
        return (opens, ["} }"], ne, ns)
    if rel == "passengers":
        opens = [f"for (ServerCommandSource {ns} : KfcGen.onPassengers({src})) {{",
                 f"    net.minecraft.entity.Entity {ne} = {ns}.getEntity();"]
        return (opens, ["}"], ne, ns)
    return None


def _v2_leaf(line, tail, exe, src):
    if not tail:
        return None
    cmd = next((s["node"] for s in tail if "node" in s), None)
    if cmd is None:
        return None
    inner = Emitted(line=line)
    if not emit_target(line, cmd, tail, inner):
        return None
    return _v2_subst(inner.java, exe, src)


def compile_execute_v2(line: str, chain: list[dict], em: Emitted) -> bool:
    run_idx = next((i for i, s in enumerate(chain)
                    if s.get("node") == "run" and s.get("type") == "LiteralCommandNode"), None)
    if run_idx is None:
        return False
    head = chain[1:run_idx]
    tail = chain[run_idx + 1:]
    segs = _v2_segments(head)
    if segs is None:
        return False
    if not any(k in ("as", "on", "posrot", "facing") for k, _ in segs):
        return False
    exe, src = "executor", "source"
    frames = []
    sinks = []          # store 결과 싱크: writer(valexpr)->list[str], 등록 시점 ctx 로 빌드
    depth = 0
    for kind, payload in segs:
        if kind == "pre":
            fr = _v2_pre_frame(payload, exe, src)
            if fr is None:
                return False
            opens, closes, src = fr
            frames.append((opens, closes))
        elif kind == "store":
            w = _v2_store_dst(payload, exe, src)
            if w is None:
                return False
            sinks.append(w)
        elif kind == "as":
            depth += 1
            fr = _v2_fanout_as(payload, exe, src, depth)
            if fr is None:
                return False
            opens, closes, exe, src = fr
            frames.append((opens, closes))
        elif kind == "on":
            depth += 1
            fr = _v2_fanout_on(payload, exe, src, depth)
            if fr is None:
                return False
            opens, closes, exe, src = fr
            frames.append((opens, closes))
        elif kind == "posrot":
            depth += 1
            mode, sel_raw = payload
            fr = _v2_fanout_posrot(mode, sel_raw, exe, src, depth)
            if fr is None:
                return False
            opens, closes, exe, src = fr
            frames.append((opens, closes))
        elif kind == "facing":
            depth += 1
            sel_raw, eyes = payload
            fr = _v2_fanout_facing(sel_raw, eyes, exe, src, depth)
            if fr is None:
                return False
            opens, closes, exe, src = fr
            frames.append((opens, closes))
        else:
            return False

    if sinks:
        # store: 리프를 '값 모드'로 컴파일(result 정수) 후 각 싱크에 기록.
        sv = Emitted(line=line)
        valexpr = source_value_expr(tail, sv, self_expr=exe, src_expr=src)
        if valexpr is None:
            return False
        leaf = list(sv.side_effects)
        leaf.append(f"int _stv = {valexpr};")
        for w in sinks:
            leaf += w("_stv")
    else:
        leaf = _v2_leaf(line, tail, exe, src)
        if leaf is None:
            return False

    body = leaf
    for opens, closes in reversed(frames):
        body = opens + ["    " + b for b in body] + closes
    em.java.extend(body)
    em.kind = "native"
    return True


def _emit_store_cond(head, em) -> bool:
    """execute [<선행수정자>] store result score <H> <O> if|unless <조건>  (run 없음).
       저장 값 = store 뒤 조건의 결과:
         if entity <sel>      -> 매치 엔티티 수 (unless 면 0개일때 1 else 0)
         if score <h> <o> matches <range> -> 조건 참이면 1 else 0 (unless 면 반전)
       선행 수정자(if score 등 단순 조건/리바인드)는 가드로 감싼다."""
    if not any(s.get("node") == "store" for s in head):
        return False
    sidx = next(i for i, s in enumerate(head) if s.get("node") == "store")
    pre = head[:sidx]
    cond_idx = next((i for i in range(sidx + 1, len(head))
                     if head[i].get("node") in ("if", "unless")), None)
    if cond_idx is None:
        em.reason = "store 무-run 값조건 없음"
        return False
    directive = head[sidx:cond_idx]
    value_part = head[cond_idx:]

    dnn = [s["node"] for s in directive if "node" in s]
    if len(dnn) < 2 or dnn[1] != "result":
        em.reason = "store 무-run result 외 미지원"
        return False
    if dnn[2:3] != ["score"]:
        em.reason = f"store {dnn[2] if len(dnn) > 2 else '?'} <조건> 미지원"
        return False

    _, dargs = split_chain(directive)
    holder, obj = store_score_dst(dargs)
    h = holder_expr(holder) if holder else None
    dst_pre = []
    if h is not None:
        dst_write = lambda valexpr: f'KfcGen.setScore(sb, {h}, {jstr(obj)}, {valexpr});'
    else:
        # 대상이 셀렉터(@n[...] 등 단일 해소형) — 바닐라 store 는 인자 해소 시점(값 조건
        # 평가 전)에 타깃을 확정하므로, 먼저 해소해 두고 값 계산 후 기록한다.
        dent = single_entity_expr(holder) if holder else None
        if dent is None:
            em.reason = f"store 대상 셀렉터({holder}) <조건> 미지원"
            return False
        dv = _fresh_var("_stDst")
        dst_pre.append(f'net.minecraft.entity.Entity {dv} = {dent};')
        dst_write = lambda valexpr: (
            f'if ({dv} != null) KfcGen.setScore(sb, {dv}.getNameForScoreboard(), '
            f'{jstr(obj)}, {valexpr});')

    vnn = [s["node"] for s in value_part if "node" in s]
    _, vargs = split_chain(value_part)
    if vnn.count("if") + vnn.count("unless") != 1:
        em.reason = "store 무-run 다중 값조건 미지원"
        return False
    negate = "unless" in vnn
    ci = vnn.index("unless" if negate else "if")
    ctype = vnn[ci + 1] if ci + 1 < len(vnn) else None

    guard_lines, guard_close = [], []
    if pre:
        pmods = parse_modifiers(pre)
        if pmods is None:
            em.reason = "store 선행수정자 파싱 실패"
            return False
        pconds, ploops, puns, prebinds = pmods
        if puns or ploops:
            em.reason = f"store 선행수정자 미지원: {puns or 'loop'}"
            return False
        if prebinds:
            guard_lines += ["{"] + ["    " + s for s in prebinds]
            guard_close = ["}"] + guard_close
        for c in pconds:
            guard_lines.append(f"if ({c}) {{")
            guard_close = ["}"] + guard_close

    body = []
    if ctype == "entity":
        sel_raw = first_arg(vargs, "entities")
        sel = parse_selector(sel_raw) if sel_raw else None
        if sel is None:
            em.reason = f"store if entity 셀렉터({str(sel_raw)[:25]}) 미지원"
            return False
        if sel.base in ("n", "p", "r") or sel.limit:
            em.reason = "store if entity 단일/limit 카운트 미지원"
            return False
        lo = entity_loop_open(sel, "_ce")
        if lo is None:
            em.reason = f"store if entity({str(sel_raw)[:25]}) 해소 불가"
            return False
        cnt = _fresh_var("scnt")
        body.append(f"int {cnt} = 0;")
        body.extend(lo)
        body.append(f"    {cnt}++;")
        body.append("}")
        valexpr = f"({cnt} == 0 ? 1 : 0)" if negate else cnt
    elif ctype == "score":
        if "matches" not in vnn:
            em.reason = "store if score 비교(matches 외) 미지원"
            return False
        sh = first_arg(vargs, "target")
        so = first_arg(vargs, "targetObjective")
        shg = holder_expr(sh) if sh else None
        slo, shi = parse_range(first_arg(vargs, "range") or "")
        lo_j = _scbound(slo, "Integer.MIN_VALUE")
        hi_j = _scbound(shi, "Integer.MAX_VALUE")
        if shg is not None and so:
            basec = f"KfcGen.scoreMatches(sb, {shg}, {jstr(so)}, {lo_j}, {hi_j})"
        else:
            # 홀더가 셀렉터(@n[...] 등 단일 해소형) — 엔티티 ScoreHolder 로 직접 매칭
            # (미설정 = 불일치, 바닐라 동일). 해소 실패(대상 없음)도 불일치.
            sent = single_entity_expr(sh) if sh else None
            if sent is None or not so:
                em.reason = f"store if score 셀렉터홀더({sh}) 미지원"
                return False
            basec = f"KfcGen.scoreMatchesEntity(sb, {sent}, {jstr(so)}, {lo_j}, {hi_j})"
        valexpr = f"(!({basec}) ? 1 : 0)" if negate else f"({basec} ? 1 : 0)"
    elif ctype == "data":
        # store result ... if data <entity|storage> <path> (run 없음) -> 매칭 원소 수 저장
        # (unless 면 0개일 때 1 else 0). 바닐라 if data 의 result = NbtPath.count.
        kind3 = vnn[ci + 2] if ci + 2 < len(vnn) else None
        pth = first_arg(vargs, "path")
        if not pth:
            em.reason = "store if data 경로 없음"
            return False
        if kind3 == "entity":
            ent = first_arg(vargs, "source") or first_arg(vargs, "target")
            if not ent:
                em.reason = "store if data entity 대상 없음"
                return False
            if ent == "@s":
                cexpr = f'KfcGen.entityPathCount(executor, {jstr(pth)})'
            else:
                eexpr = single_entity_expr(ent)
                if eexpr is None:
                    em.reason = f"store if data entity 셀렉터({ent[:20]}) 미해소"
                    return False
                cexpr = f'KfcGen.entityPathCount({eexpr}, {jstr(pth)})'
        elif kind3 == "storage":
            sid = first_arg(vargs, "source") or first_arg(vargs, "target")
            if not sid:
                em.reason = "store if data storage id 없음"
                return False
            cexpr = f'KfcGen.storagePathCount(server, {jstr(sid)}, {jstr(pth)})'
        else:
            em.reason = f"store if data {kind3 or '?'} 미지원"
            return False
        valexpr = f"({cexpr} == 0 ? 1 : 0)" if negate else cexpr
    else:
        em.reason = f"store if {ctype} <조건> 미지원"
        return False

    body = dst_pre + body
    body.append(dst_write(valexpr))
    if guard_lines:
        em.java.extend(guard_lines)
        em.java.extend(["    " + b for b in body])
        em.java.extend(guard_close)
    else:
        em.java.extend(body)
    em.kind = "native"
    return True



def emit_execute(line: str, chain: list[dict], em: Emitted) -> bool:
    """기존 경로 우선 -> 실패 시 compile_execute_v2 폴드(회귀 0, 순증가)."""
    if _emit_execute_legacy(line, chain, em):
        return True
    em2 = Emitted(line=line)
    if compile_execute_v2(line, chain, em2):
        em.java = em2.java
        em.kind = em2.kind
        return True
    return False


def _emit_execute_legacy(line: str, chain: list[dict], em: Emitted) -> bool:
    """
    체인을 'run' 기준으로 둘로 나눈다:
      head = execute 수정자들 (as/at/if/on/positioned/facing/store/...)
      tail = run 뒤의 타겟 커맨드
    head 의 조건/루프를 자바로 만들고, tail 을 그 안에 넣는다.
    네이티브 불가 수정자가 있으면 False(-> 브릿지).
    """
    # run 인덱스 찾기
    global _on_depth
    run_idx = next((i for i, s in enumerate(chain)
                    if s.get("node") == "run" and s.get("type") == "LiteralCommandNode"), None)

    head = chain[1:run_idx] if run_idx is not None else chain[1:]  # execute 리터럴 제외
    tail = chain[run_idx + 1:] if run_idx is not None else []

    # ---- run 없는 store result <조건> (엔티티 수/score matches 캡처) ----
    if run_idx is None and any(s.get("node") == "store" for s in head):
        if _emit_store_cond(head, em):
            return True
        return False  # em.reason 은 _emit_store_cond 가 채움

    # ---- as <셀렉터> + 컨텍스트 수정자(on/at/facing/rotated as) ----
    # as 가 head 선두이고 그 뒤에 재바인딩성 수정자가 오면, 셀렉터 루프 + 본문 재귀로 처리한다.
    # (parse_modifiers 단독으론 on/at 등을 못 풀어 __AS_LOOP__ 로 폴백되던 경로를 구제)
    if head and head[0].get("node") == "as":
        nn_head = [s.get("node") for s in head if "node" in s]
        has_ctx_mod = any(n in ("on", "facing") for n in nn_head[1:])
        # at <비-@s> 또는 rotated as <셀렉터> 도 포함
        if not has_ctx_mod:
            for i, s in enumerate(head[1:], 1):
                if s.get("node") == "at":
                    for s2 in head[i + 1:i + 3]:
                        if s2.get("arg") == "targets" and s2["value"]["raw"] != "@s":
                            has_ctx_mod = True
                if s.get("node") == "rotated":
                    nxt = head[i + 1] if i + 1 < len(head) else None
                    if nxt and nxt.get("node") == "as":
                        has_ctx_mod = True
        if has_ctx_mod:
            sel_raw = next((s["value"]["raw"] for s in head if s.get("arg") == "targets"), "")
            sel0 = parse_selector(sel_raw)
            if sel0 is not None:
                if _emit_as_loop_recursive(line, head, tail, em, sel0):
                    return True
                # 재귀 실패 시 아래 일반 경로로 진행(폴백 사유는 em.reason 에 기록됨)

    # ---- at <루프 셀렉터> (@a[tag] / 다중 @e) ----
    # 위치+회전만 대상으로 재바인딩(executor 불변). 대상이 여럿이면 루프로 각각 실행.
    at_idx = None
    for i, s in enumerate(head):
        if s.get("node") == "at":
            # 바로 뒤 targets 인자
            nx = head[i + 1] if i + 1 < len(head) else None
            traw = None
            for s2 in head[i + 1:i + 3]:
                if s2.get("arg") == "targets":
                    traw = s2["value"]["raw"]
            if traw and traw != "@s":
                psel = parse_selector(traw)
                is_single = psel and ((psel.base in ("n", "p", "r")) or psel.limit == 1)
                if not is_single:  # 루프 대상만 여기서 처리(단일은 parse_modifiers rebind)
                    at_idx = i
                    at_raw = traw
                    at_sel = psel
                    break
    if at_idx is not None:
        _on_depth += 1
        depth = _on_depth
        av = f"_atSrc{depth}"
        pre_head = head[:at_idx]
        post_head = head[at_idx + 2:]  # at + targets(arg는 node아님) 다음
        # at 의 targets 는 arg 라 node 시퀀스에선 at 다음이 바로 post. arg 제거 위해 필터.
        post_head = [s for s in post_head if not (s.get("arg") == "targets")]
        pre_mods = parse_modifiers(pre_head)
        if pre_mods is None:
            _on_depth -= 1
            return False
        pre_conds, pre_loops, pre_uns, pre_rebinds = pre_mods
        if pre_uns:
            em.reason = f"at 앞 수정자 미지원: {pre_uns}"
            _on_depth -= 1
            return False
        loop_open = entity_loop_open(at_sel, f"_atE{depth}")
        if loop_open is None and at_sel is not None and at_sel.base == "s":
            # at @s[guards] = 실행자 단일. 루프가 아니라 가드 통과 시 실행자 위치로 positioned.
            g = selector_cond(at_sel)
            if g is None:
                em.reason = f"at @s[{at_raw[:20]}] 가드 미해소"
                _on_depth -= 1
                return False
            inner = Emitted(line=line)
            rebuilt = [{"node": "execute", "type": "LiteralCommandNode"}] + post_head
            if tail:
                rebuilt += [{"node": "run", "type": "LiteralCommandNode"}] + tail
            if not emit_execute_with_src(line, rebuilt, inner, av):
                em.reason = inner.reason or f"at @s[...] 본문 미지원"
                _on_depth -= 1
                return False
            _on_depth -= 1
            guard = "executor != null" if g == "true" else f"executor != null && ({g})"
            out = [f'if ({guard}) {{',
                   f'    net.minecraft.server.command.ServerCommandSource {av} = '
                   f'KfcGen.atEntity(source, executor);']
            for b in inner.java:
                out.append("    " + b)
            out.append("}")
            wrapped = out
            for cond in reversed(pre_conds):
                wrapped = [f'if ({cond}) {{'] + ["    " + b for b in wrapped] + ["}"]
            if pre_rebinds:
                wrapped = pre_rebinds + wrapped
            em.java.extend(wrapped)
            em.kind = "native"
            return True
        if loop_open is None:
            em.reason = f"at {at_raw[:25]} 루프 미해소"
            _on_depth -= 1
            return False
        inner = Emitted(line=line)
        rebuilt = [{"node": "execute", "type": "LiteralCommandNode"}] + post_head
        if tail:
            rebuilt += [{"node": "run", "type": "LiteralCommandNode"}] + tail
        if not emit_execute_with_src(line, rebuilt, inner, av):
            em.reason = inner.reason or f"at {at_raw[:25]} 본문 미지원"
            _on_depth -= 1
            return False
        _on_depth -= 1
        out = list(loop_open)  # for (Entity _atEN : ...) {
        out.append(f'    ServerCommandSource {av} = KfcGen.atEntity(source, _atE{depth});')
        for b in inner.java:
            out.append("    " + b)
        out.append("}")
        wrapped = out
        for cond in reversed(pre_conds):
            wrapped = [f'if ({cond}) {{'] + ["    " + b for b in wrapped] + ["}"]
        if pre_rebinds:
            wrapped = pre_rebinds + wrapped
        em.java.extend(wrapped)
        em.kind = "native"
        return True

    # ---- on passengers / on vehicle ----
    # source 엔티티를 승객(루프)/탈것(단일)으로 재바인딩하고, 그 이후 전체를 새 컨텍스트로 재귀.
    on_idx = next((i for i, s in enumerate(head)
                   if s.get("node") == "on"), None)
    if on_idx is not None:
        # store result ... on vehicle (on vehicle)* run <소스>:
        #   store 가 on-재바인딩 컨텍스트의 소스 값을 캡처. emit_store 가 직접 처리.
        head_nodes = [s.get("node") for s in head]
        if "store" in head_nodes and tail:
            rest = head_nodes[on_idx:]
            if len(rest) % 2 == 0 and all(
                    rest[k] == "on" and rest[k + 1] == "vehicle"
                    for k in range(0, len(rest), 2)):
                st = Emitted(line=line)
                if emit_store(line, head, tail, st):
                    # store 앞 if/unless 조건(있으면)으로 감싸기
                    si2 = head_nodes.index("store")
                    cmods = parse_modifiers(head[:si2])
                    wrapped = st.java
                    if cmods is not None and not cmods[2]:   # unsupported 없을 때만
                        for cond in reversed(cmods[0]):
                            wrapped = [f'if ({cond}) {{'] + ["    " + b for b in wrapped] + ["}"]
                    em.java.extend(wrapped)
                    em.kind = st.kind
                    return True
        rel = None
        for s in head[on_idx + 1:on_idx + 2]:
            rel = s.get("node")
        if rel not in ("passengers", "vehicle", "attacker", "target", "origin", "controller", "owner"):
            em.reason = f"on {rel} (1차 미지원)"
            return False
        _on_depth += 1
        depth = _on_depth
        ov, oe = f"_on{depth}", f"_onEnt{depth}"
        pre_head = head[:on_idx]                               # on 이전 수정자
        post_head = head[on_idx + 2:]                          # on X 이후 수정자
        # on 이전 수정자(조건/위치)는 현재 source 기준으로 평가
        pre_mods = parse_modifiers(pre_head)
        if pre_mods is None:
            _on_depth -= 1
            return False
        pre_conds, pre_loops, pre_uns, pre_rebinds = pre_mods
        if pre_uns:
            em.reason = f"on 앞 수정자 미지원: {pre_uns}"
            _on_depth -= 1
            return False
        # on 앞의 재바인딩(positioned/rotated/rotated as <셀렉터> 등)의 최종 소스를
        # on 의 입력으로 써야 한다. (이전엔 pre_rebinds 를 버리고 원본 source 를 써서
        # `rotated as @p ... on passengers` 같은 회전 리바인드가 통째로 누락됐음.)
        pre_src = "source"
        if pre_rebinds:
            m = re.match(r'\s*ServerCommandSource (kfcSrc\d+)', pre_rebinds[-1])
            if m:
                pre_src = m.group(1)
            # kfcSrc 변수명을 on-depth 로 네임스페이스 - 중첩 재귀 본문이 쓰는 평범한 kfcSrcN 과
            # 같은 메서드에서 충돌(Java 는 중첩 블록 동명 지역변수 불가)하지 않도록.
            def _ns(s):
                return re.sub(r'\bkfcSrc(\d+)\b', rf'kfcSrcOn{depth}_\1', s)
            pre_rebinds = [_ns(b) for b in pre_rebinds]
            pre_conds = [_ns(c) for c in pre_conds]
            pre_src = _ns(pre_src)
        # on 이후 + tail 을 새 소스(ov)로 재귀 emit
        inner = Emitted(line=line)
        rebuilt = [{"node": "execute", "type": "LiteralCommandNode"}] + post_head
        if tail:
            rebuilt += [{"node": "run", "type": "LiteralCommandNode"}] + tail
        if not emit_execute_with_src(line, rebuilt, inner, ov):
            em.reason = inner.reason or f"on {rel} 본문 미지원"
            _on_depth -= 1
            return False
        inner_body = inner.java
        # on 으로 바뀐 executor 도 ov 기준이어야 - executor 참조를 oe 로 치환
        inner_body = [re.sub(r'\bexecutor\b', oe, b) for b in inner_body]
        _on_depth -= 1
        out = []
        _single = {"vehicle": "onVehicle", "attacker": "onAttacker", "target": "onTarget", "origin": "onOrigin", "controller": "onController", "owner": "onOwner"}
        if rel in _single:
            out.append(f"{{ ServerCommandSource {ov} = KfcGen.{_single[rel]}({pre_src});")
            out.append(f"  if ({ov} != null) {{")
            out.append(f"    net.minecraft.entity.Entity {oe} = {ov}.getEntity();")
            for b in inner_body:
                out.append("    " + b)
            out.append("  } }")
        else:  # passengers
            out.append(f"for (ServerCommandSource {ov} : KfcGen.onPassengers({pre_src})) {{")
            out.append(f"    net.minecraft.entity.Entity {oe} = {ov}.getEntity();")
            for b in inner_body:
                out.append("    " + b)
            out.append("}")
        # on 앞 재바인딩은 선언이므로 가드 밖(앞)에 두고, 조건+on루프만 null 가드로 감싼다.
        inner_w = out
        for cond in reversed(pre_conds):
            inner_w = [f'if ({cond}) {{'] + ["    " + b for b in inner_w] + ["}"]
        if pre_rebinds and _rebinds_nullable(pre_rebinds):
            inner_w = [f'if ({pre_src} != null) {{'] + ["    " + b for b in inner_w] + ["}"]
        wrapped = list(pre_rebinds) + inner_w
        for loop in reversed(pre_loops):
            wrapped = [loop] + ["    " + b for b in wrapped] + ["}"]
        # 재바인딩 변수(kfcSrcN)가 같은 함수 내 다른 줄과 충돌하지 않도록 블록 격리
        if pre_rebinds:
            wrapped = ["{"] + ["    " + b for b in wrapped] + ["}"]
        em.java.extend(wrapped)
        em.kind = "native"
        return True

    mods = parse_modifiers(head, "source")
    if mods is None:
        return False  # 지원 안 하는 수정자 -> 브릿지

    conds, loops, unsupported, rebind_stmts = mods
    if unsupported:
        if unsupported.startswith("__AS_LOOP__"):
            return emit_as_loop(line, head, tail, em)
        if unsupported.startswith("__STORE__"):
            if not emit_store(line, head, tail, em):
                return False
            body = em.java
            # store 앞의 위치/회전 재바인딩(positioned/rotated/facing)이 있으면, store 본문
            # (특히 run <소스>=함수/데이터 호출)의 source 를 재바인딩된 소스로 치환해야 한다.
            # (이전엔 rebind 문장만 emit 하고 본문은 원본 source 를 써서, facing/positioned 가
            #  store 의 run 대상 함수에 전달되지 않았음 - 벽충돌 밀어내기 방향 오류의 원인.)
            if rebind_stmts:
                m = re.match(r'\s*ServerCommandSource (kfcSrc\d+)', rebind_stmts[-1])
                rsrc = m.group(1) if m else "source"
                body = [re.sub(r'\bsource\b', rsrc, b) for b in body]
            # store 앞의 if/unless 조건들로 감싸기 (conds 는 parse_modifiers 가 채움)
            wrapped = body
            for cond in reversed(conds):
                wrapped = [f'if ({cond}) {{'] + ["    " + b for b in wrapped] + ["}"]
            if rebind_stmts and _rebinds_nullable(rebind_stmts):
                wrapped = [f'if ({rsrc} != null) {{'] + ["    " + b for b in wrapped] + ["}"]
            if rebind_stmts:
                # 재바인딩 변수(kfcSrcN)가 같은 함수 내 다른 줄과 충돌하지 않도록 블록 격리
                wrapped = ["{"] + ["    " + s for s in rebind_stmts] + ["    " + w for w in wrapped] + ["}"]
            em.java = wrapped
            return True
        em.reason = f"execute 수정자 미지원: {unsupported}"
        return False

    # tail(타겟) emit - 임시 Emitted 에 담아 본문으로
    inner = Emitted(line=line)
    if tail:
        tail_command = next((s["node"] for s in tail if "node" in s), None)
        ok = emit_target(line, tail_command, tail, inner)
        if not ok:
            # run 대상(타겟 커맨드)이 네이티브 불가면 그 구체 사유를 위로 전파
            # (이게 없으면 emit_line 이 일반 "execute 네이티브화 불가" 로 뭉뚱그림).
            em.reason = inner.reason or f"run {tail_command} 네이티브화 불가"
            return False
    else:
        return False

    # 본문은 마지막 재바인딩 소스를 써야 한다. parse_modifiers 가 만든 마지막 kfcSrcN 으로 치환.
    body = inner.java
    final_src = "source"
    body_nullable = False
    if rebind_stmts:
        # 마지막 rebind 가 선언한 변수명 추출
        m = re.match(r'\s*ServerCommandSource (kfcSrc\d+)', rebind_stmts[-1])
        if m:
            final_src = m.group(1)
        body = [re.sub(r'\bsource\b', final_src, b) for b in body]
        body_nullable = _rebinds_nullable(rebind_stmts)

    # 조건/루프로 본문 감싸기 (조건은 이미 cur_src 기준으로 생성됨)
    wrapped = body
    for cond in reversed(conds):
        wrapped = [f'if ({cond}) {{'] + ["    " + b for b in wrapped] + ["}"]
    # 셀렉터 rebind 매치실패 = fork 사망. 조건식도 nullable 소스를 참조하므로
    # 조건+본문 전체를 final_src != null 로 감싼다(조건 평가 NPE 방지).
    if body_nullable:
        wrapped = [f'if ({final_src} != null) {{'] + ["    " + b for b in wrapped] + ["}"]
    for loop in reversed(loops):
        wrapped = [loop["open"]] + ["    " + b for b in wrapped] + ["}"]

    # 재바인딩 문장을 가장 앞에 - 등장 순서대로 누적돼 있고, 조건/본문이 이를 참조한다.
    # 변수명(kfcSrcN) 충돌 방지를 위해 블록 스코프로 격리.
    if rebind_stmts:
        wrapped = ["{"] + ["    " + s for s in rebind_stmts] + ["    " + w for w in wrapped] + ["}"]

    em.java.extend(wrapped)
    if loops or rebind_stmts or any("// gated" in c for c in conds):
        em.kind = "native"
    return True


def _resrc(expr, src):
    """조건/재바인딩용 셀렉터 식의 하드코딩된 bare 'source' 를 현재 rebind 소스(cur_src)로 치환.
       single_entity_expr/nearest_entity_java 는 위치 기준을 'source.getPosition()' 으로
       고정 생성하는데, execute at/positioned/rotated/facing 로 소스가 재바인딩(kfcSrcN)된
       뒤의 셀렉터 조건은 그 재바인딩 위치를 써야 한다.
         예) `execute at @s if data entity @n[tag=..,distance=..1.39] data.bgm run ...`
             — BGM 함수는 카트에서 먼 위치에서 호출되므로 원본 source 로 @n 을 찾으면
               1.39칸 내 대상이 없어 조건이 거짓이 되고 카트별 고정 BGM 기믹이 소실됐다.
       cur_src=='source' 면 무변경. executor 는 위치 재바인딩에 불변이라 치환 대상 아님
       (single_entity_expr 이 executor 를 그대로 두는 것과 일관)."""
    if expr is None or src == "source":
        return expr
    return re.sub(r'\bsource\b', src, expr)


def parse_modifiers(head: list[dict], src_var: str = "source"):
    """execute 수정자 시퀀스를 (conds, loops, unsupported) 로.
       conds: 자바 boolean 식 리스트
       loops: {open: 'for(...){'} 리스트
       unsupported: 비면 None-아님 (지원 안 하는 첫 수정자 설명)"""
    nodes, args = split_chain(head)
    nn = [n["node"] for n in nodes]
    conds, loops = [], []
    cur_src = src_var      # 현재 소스 변수(positioned/rotated/align 이 갱신)
    rebinds = []           # 누적 재바인딩 문장 - 등장 순서대로 본문 앞에 배치
    src_nullable = False   # 셀렉터 rebind 매치실패(null 소스) 가능 여부

    i = 0
    arg_cursor = {}  # 같은 인자명 여러 번 -> 순서대로 소비
    def next_arg(name):
        idx = arg_cursor.get(name, 0)
        arg_cursor[name] = idx + 1
        vals = args.get(name, [])
        return vals[idx] if idx < len(vals) else None

    while i < len(nn):
        tok = nn[i]
        if tok == "if" or tok == "unless":
            neg = (tok == "unless")
            sub = nn[i + 1] if i + 1 < len(nn) else None
            if sub == "score":
                # 노드 시퀀스: if score target targetObjective <disc> ...
                #   <disc> == "matches" -> 범위 검사
                #   <disc> in {<,<=,=,>=,>} -> 비교형
                disc = nn[i + 4] if i + 4 < len(nn) else None
                tgt = next_arg("target")
                tobj = next_arg("targetObjective")
                h = holder_expr(tgt["raw"]) if tgt else None
                # 셀렉터 홀더(@s[tag], @n, @p, @e[limit=1]) -> 단일 엔티티 식
                ent_expr = None
                if h is None and tgt:
                    ent_expr = _resrc(single_entity_expr(tgt["raw"]), cur_src)
                    if ent_expr is None:
                        return ("UNS", [], f"if score 비-@s/# 홀더: {tgt}", [])
                if disc == "matches":
                    rng = next_arg("range")
                    lo, hi = parse_range(rng["raw"])
                    lo = _scbound(lo, "Integer.MIN_VALUE")
                    hi = _scbound(hi, "Integer.MAX_VALUE")
                    if ent_expr is not None:
                        # 셀렉터 홀더: neg 를 helper 안에서(null 체크 뒤) 적용 -> 빈 셀렉터는 if/unless 무관 미실행
                        c = (f'KfcGen.entityScoreMatches(sb, {ent_expr}, {jstr(tobj["raw"])}, '
                             f'{lo}, {hi}, {"true" if neg else "false"})')
                        conds.append(c)
                    else:
                        c = f'KfcGen.scoreMatches(sb, {h}, {jstr(tobj["raw"])}, {lo}, {hi})'
                        conds.append(f'!({c})' if neg else c)
                    i = skip_after(nn, i, "range")
                    continue
                elif disc in ("<", "<=", "=", ">=", ">"):
                    src = next_arg("source")
                    sobj = next_arg("sourceObjective")
                    hb = holder_expr(src["raw"]) if src else None
                    src_ent = None
                    if hb is None and src:
                        src_ent = _resrc(single_entity_expr(src["raw"]), cur_src)
                        if src_ent is None:
                            return ("UNS", [], f"if score 비교형 비-@s/# 소스홀더: {src}", [])
                    # 대상/소스가 셀렉터(단일 엔티티)면 식을 그대로 인자로 넘긴다
                    #  (오버로드가 1회 평가 + null->false 처리).
                    if ent_expr is not None or src_ent is not None:
                        a_arg = ent_expr if ent_expr is not None else h
                        b_arg = src_ent if src_ent is not None else hb
                        # 셀렉터 홀더: neg 를 helper 안에서(null 체크 뒤) 적용
                        c = (f'KfcGen.scoreCmp(sb, {a_arg}, {jstr(tobj["raw"])}, '
                             f'{jstr(disc)}, {b_arg}, {jstr(sobj["raw"])}, {"true" if neg else "false"})')
                        conds.append(c)
                        i = skip_after(nn, i, "sourceObjective")
                        continue
                    c = (f'KfcGen.scoreCmp(sb, {h}, {jstr(tobj["raw"])}, '
                         f'{jstr(disc)}, {hb}, {jstr(sobj["raw"])})')
                    conds.append(f'!({c})' if neg else c)
                    i = skip_after(nn, i, "sourceObjective")
                    continue
                else:
                    return ("UNS", [], f"if score 판별자({disc}) 미지원", [])
            elif sub == "entity":
                # if entity <selector> : @s -> 실행자 검사 / @e-@p-@n -> 존재 검사
                if "data" in nn[i:i + 3]:
                    return ("UNS", [], "if data entity (NBT 조건, 1차 미지원)", [])
                ent = next_arg("entities")
                # @s[nbt={SelectedItemSlot:N}] (+scores) - 핫바 선택슬롯 검사
                if ent:
                    ssc = at_s_selecteditem_cond(ent["raw"])
                    if ssc is not None:
                        conds.append(f'!({ssc})' if neg else ssc)
                        i = skip_after(nn, i, "entities")
                        continue
                    efc = at_effect_cond(ent["raw"])   # nbt={active_effects:[{id:..}]}
                    if efc is not None:
                        conds.append(f'!({efc})' if neg else efc)
                        i = skip_after(nn, i, "entities")
                        continue
                sel = parse_selector(ent["raw"]) if ent else None
                if sel is None:
                    return ("UNS", [], f"if entity 셀렉터 파싱 실패: {ent}", [])
                c = selector_cond(sel, cur_src)
                if c is None:
                    return ("UNS", [], f"if entity @{sel.base} 미지원 필터", [])
                conds.append(f'!({c})' if neg else c)
                i = skip_after(nn, i, "entities")
                continue
            elif sub == "items":
                # if items entity <entities> <slots> <item_predicate>
                src_kind = nn[i + 2] if i + 2 < len(nn) else None
                if src_kind != "entity":
                    return ("UNS", [], f"if items {src_kind} (block 소스 1차 미지원)", [])
                ent = next_arg("entities")
                slot = next_arg("slots")
                pred = next_arg("item_predicate")
                if not ent or not slot:
                    return ("UNS", [], "if items 대상/슬롯 없음", [])
                # ── if/unless items 시맨틱(바닐라 바이트코드 확인): 대상 셀렉터가 아무도
                #    못 찾으면 ENTITY_NOT_FOUND 예외 = '조건 명령 실패' → if/unless 모두 미실행.
                #    따라서 negate(unless)는 반드시 '아이템 매치'에만 적용해야 하며(KfcGen.itemsCond*
                #    헬퍼가 소비), 조건식 전체를 !() 로 감싸면 '대상 없음'이 unless 참으로 둔갑한다
                #    (@s[scores=팀] 팀모자 매틱 재장착 버그의 원인).
                neg_j = "true" if neg else "false"
                parsed = parse_clear_item(pred["raw"] if pred else "*")
                slot_j = jstr(slot["raw"])
                if parsed is None:
                    # 간이 파서가 못 푸는 복합 술어(컴포넌트/enchantments/서브술어) —
                    # 바닐라 ItemPredicateArgumentType 런타임 파싱 경로로 위임(관측 동등).
                    pred_j = jstr(pred["raw"]) if pred else jstr("*")
                    def _pred_call(eexpr):
                        return (f'KfcGen.itemsCondPred({cur_src}.getServer(), '
                                f'{eexpr}, {slot_j}, {pred_j}, {neg_j})')
                    if ent["raw"] == "@s":
                        c = _pred_call("executor")
                    else:
                        psel = parse_selector(ent["raw"])
                        if psel is not None and psel.base == "s":
                            g = selector_cond(psel, cur_src)
                            if g is None:
                                return ("UNS", [], f"if items @s[{ent['raw'][:20]}] 가드 미해소", [])
                            # 필터 불통과 = 대상 없음 = 미실행(가드는 negate 바깥, 항상 양성)
                            c = f'({g} && {_pred_call("executor")})'
                        else:
                            eexpr = _resrc(single_entity_expr(ent["raw"]), cur_src)
                            if eexpr is None:
                                return ("UNS", [], f"if items 대상({ent['raw'][:20]}) 미해소(복합 술어)", [])
                            c = _pred_call(eexpr)
                    conds.append(c)
                    i = skip_after(nn, i, "item_predicate")
                    continue
                item_id, custom_nbt = parsed
                nbt_j = "null" if custom_nbt is None else jstr(custom_nbt)
                iid_j = "null" if item_id == "*" else jstr(item_id)
                if ent["raw"] == "@s":
                    c = f'KfcGen.itemsCond(executor, {slot_j}, {iid_j}, {nbt_j}, {neg_j})'
                    conds.append(c)
                    i = skip_after(nn, i, "item_predicate")
                    continue
                else:
                    _psel0 = parse_selector(ent["raw"])
                    if _psel0 is not None and _psel0.base == "s":
                        # @s[필터] — 셀렉터는 실행자 자신만 가리킴. 필터(가드)는 negate 바깥.
                        g0 = selector_cond(_psel0, cur_src)
                        if g0 is None:
                            return ("UNS", [], f"if items @s[{ent['raw'][:20]}] 가드 미해소", [])
                        c = (f'({g0} && KfcGen.itemsCond(executor, '
                             f'{slot_j}, {iid_j}, {nbt_j}, {neg_j}))')
                        conds.append(c)
                        i = skip_after(nn, i, "item_predicate")
                        continue
                    eexpr = _resrc(single_entity_expr(ent["raw"]), cur_src)
                    if eexpr is not None:
                        # 단일 셀렉터(@n/@p/…): 해소 실패(null) = 대상 없음 = 미실행 — itemsCond 가 처리
                        c = f'KfcGen.itemsCond({eexpr}, {slot_j}, {iid_j}, {nbt_j}, {neg_j})'
                    else:
                        # 멀티 타겟(@a/@e/@n …) 존재검사. 태그만 있는 셀렉터는 네이티브,
                        # 복잡 필터(scores/distance/type 등)는 정확성 위해 브릿지.
                        # 매치 엔티티 0명 = 바닐라 예외(미실행) — anyXxxItemsCond 가 처리.
                        _isel = parse_selector(ent["raw"])
                        if _isel is None or not _sel_tags_only(_isel):
                            return ("UNS", [], f"if items 대상({ent['raw'][:20]}) 미해소", [])
                        _tp = java_str_array(_isel.tags_pos); _tn = java_str_array(_isel.tags_neg)
                        if _isel.base in ("a", "p", "r"):
                            c = f'KfcGen.anyPlayerItemsCond(ctx, {_tp}, {_tn}, {slot_j}, {iid_j}, {nbt_j}, {neg_j})'
                        elif _isel.base in ("e", "n"):
                            c = f'KfcGen.anyEntityItemsCond(ctx, {_tp}, {_tn}, {slot_j}, {iid_j}, {nbt_j}, {neg_j})'
                        else:
                            return ("UNS", [], f"if items 대상({ent['raw'][:20]}) 미해소", [])
                conds.append(c)
                i = skip_after(nn, i, "item_predicate")
                continue
            elif sub == "data":
                # if data entity @s <path> / if data storage <id> <path> - 존재 검사
                kind3 = nn[i + 2] if i + 2 < len(nn) else None
                if kind3 == "entity":
                    ent = next_arg("source") or next_arg("target")
                    p = next_arg("path")
                    if not ent or not p:
                        return ("UNS", [], "if data entity 경로 없음", [])
                    praw = p["raw"]
                    # path 가 '{...}' 로 시작하면 NbtPath 가 아니라 컴파운드 부분일치 검사
                    # (예: if data entity @s {brightness:{sky:15,block:15}}). entityHasPath 는
                    # 경로 존재 검사용이라 이 형태를 처리 못한다 → entityMatchesNbt 로 분기.
                    is_compound_match = praw.lstrip().startswith("{")
                    if ent["raw"] == "@s":
                        eexpr = "executor"
                    else:
                        eexpr = _resrc(single_entity_expr(ent["raw"]), cur_src)
                        if eexpr is None:
                            return ("UNS", [], f"if data entity 셀렉터({ent['raw'][:20]}) 미해소", [])
                    if is_compound_match:
                        c = f'KfcGen.entityMatchesNbt({eexpr}, {jstr(praw)})'
                    else:
                        c = f'KfcGen.entityHasPath({eexpr}, {jstr(praw)})'
                elif kind3 == "storage":
                    sid = next_arg("source") or next_arg("target")
                    p = next_arg("path")
                    if not sid or not p:
                        return ("UNS", [], "if data storage 인자 없음", [])
                    c = (f'KfcGen.storageHasPath(source.getServer(), '
                         f'{jstr(sid["raw"])}, {jstr(p["raw"])})')
                else:
                    return ("UNS", [], f"if data {kind3} (1차 미지원)", [])
                conds.append(f'!({c})' if neg else c)
                i = skip_after(nn, i, "path")
                continue
            elif sub == "predicate":
                pid = next_arg("predicate")
                if not pid:
                    return ("UNS", [], "if predicate 이름 없음", [])
                expr = PREDICATES.get(pid["raw"])
                if expr is None:
                    pid_norm = pid["raw"] if ":" in pid["raw"] else "minecraft:" + pid["raw"]
                    # 술어는 location_check/weather/position 등 위치·문맥 의존 조건을 담을 수 있으므로
                    # execute at/positioned/rotated 로 재바인딩된 소스(cur_src)로 평가해야 한다.
                    # (bare source 는 원본 실행 위치라, at @s 뒤 위치 술어가 어긋난다.)
                    c = f'KfcGen.testPredicate({cur_src}, executor, {jstr(pid_norm)})'
                else:
                    c = expr.replace("{E}", "executor")
                conds.append(f'!({c})' if neg else c)
                i = skip_after(nn, i, "predicate")
                continue
            elif sub == "function":
                fname = next_arg("name")
                if not fname:
                    return ("UNS", [], "if function 이름 없음", [])
                fid_called = fname["raw"]
                if fid_called in MACRO_FNS:
                    return ("UNS", [], f"if function(매크로 {fid_called[:20]}) 1차 미지원", [])
                # 함수의 executeReturn 결과가 0이 아니면 참 (mcfunction return 값 시맨틱).
                # 반드시 현재 rebind 소스(cur_src)로 호출 - facing/positioned/rotated 로 바뀐
                # 위치-회전 문맥이 조건 함수에 전달돼야 한다(예: 벽충돌 facing->wall-condition).
                if ALL_FIDS and fid_called not in ALL_FIDS:
                    _ns, _p = fid_called.split(":", 1) if ":" in fid_called else ("minecraft", fid_called)
                    c = (f'(KfcGen.instantExecuteFunctionReturn({cur_src}, '
                         f'net.minecraft.util.Identifier.of({jstr(_ns)}, {jstr(_p)})) != 0)')
                else:
                    c = f'({fqcn(fid_called)}.executeReturn({cur_src}) != 0)'
                conds.append(f'!{c}' if neg else c)
                i = skip_after(nn, i, "name")
                continue
            elif sub == "loaded":
                pos = next_arg("pos")
                pe = cond_pos_expr(pos["raw"], cur_src) if pos else None
                if pe is None:
                    return ("UNS", [], "if loaded 좌표 미지원", [])
                c = f'KfcGen.posLoaded(ctx.world, {pe})'
                conds.append(f'!({c})' if neg else c)
                i = skip_after(nn, i, "pos")
                continue
            elif sub == "block":
                pos = next_arg("pos")
                blk = next_arg("block")
                pe = cond_pos_expr(pos["raw"], cur_src) if pos else None
                if pe is None or not blk:
                    return ("UNS", [], "if block 좌표/블록 미지원", [])
                braw = blk["raw"]
                # 블록 종류 + 상태([..]) 지원. NBT({..})는 1차 미지원(블록엔티티 NBT 비교 필요).
                nbt_part = ""; state_part = ""; base = braw
                bm = re.search(r'\{', braw)
                if bm:
                    nbt_part = braw[bm.start():]; base = braw[:bm.start()]
                sm = re.search(r'\[(.*?)\]', base)
                if sm:
                    state_part = sm.group(1).strip(); base = base[:sm.start()]
                base = base.strip()
                if nbt_part:
                    return ("UNS", [], "if block NBT 술어 미지원", [])
                if state_part:
                    c = f'KfcGen.blockStateMatches(ctx.world, {pe}, {jstr(base)}, {jstr(state_part)})'
                    conds.append(f'!({c})' if neg else c)
                    i = skip_after(nn, i, "block")
                    continue
                if base.startswith("#"):
                    tagid = base[1:]
                    if ":" not in tagid:
                        tagid = "minecraft:" + tagid
                    # 항상 런타임 태그 멤버십 검사. 변환 시점 전개(BLOCK_TAGS)는 데이터팩 내부
                    # 태그만 재귀 해소할 수 있어 `#minecraft:fences` 같은 바닐라 중첩 태그 멤버를
                    # 조용히 누락한다(벽 판정 오류의 원인). 태그 JSON 은 모드 리소스로 복사돼
                    # 런타임에 레지스트리로 로드되므로 게임이 중첩까지 정확히 해소한다.
                    c = f'KfcGen.blockInTag(ctx.world, {pe}, {jstr(tagid)})'
                else:
                    ids = [base if ":" in base else "minecraft:" + base]
                    arr = "new String[]{" + ", ".join(jstr(x) for x in ids) + "}"
                    c = f'KfcGen.blockMatches(ctx.world, {pe}, {arr})'
                conds.append(f'!({c})' if neg else c)
                i = skip_after(nn, i, "block")
                continue
            elif sub == "blocks":
                p1 = next_arg("start"); p2 = next_arg("end"); pd = next_arg("destination")
                bmode = "masked" if "masked" in nn else "all"
                e1 = cond_pos_expr(p1["raw"], cur_src) if p1 else None
                e2 = cond_pos_expr(p2["raw"], cur_src) if p2 else None
                ed = cond_pos_expr(pd["raw"], cur_src) if pd else None
                if e1 is None or e2 is None or ed is None:
                    return ("UNS", [], "if blocks 좌표 미지원", [])
                _mk = "true" if bmode == "masked" else "false"
                c = (f'KfcGen.blocksMatch({cur_src}.getWorld(), '
                     f'net.minecraft.util.math.MathHelper.floor(({e1}).x), net.minecraft.util.math.MathHelper.floor(({e1}).y), net.minecraft.util.math.MathHelper.floor(({e1}).z), '
                     f'net.minecraft.util.math.MathHelper.floor(({e2}).x), net.minecraft.util.math.MathHelper.floor(({e2}).y), net.minecraft.util.math.MathHelper.floor(({e2}).z), '
                     f'net.minecraft.util.math.MathHelper.floor(({ed}).x), net.minecraft.util.math.MathHelper.floor(({ed}).y), net.minecraft.util.math.MathHelper.floor(({ed}).z), {_mk})')
                conds.append(f'!({c})' if neg else c)
                i = skip_after(nn, i, "destination")
                continue
            elif sub == "dimension":
                d = next_arg("dimension")
                if not d:
                    return ("UNS", [], "if dimension 인자 없음", [])
                _did = d["raw"] if ":" in d["raw"] else "minecraft:" + d["raw"]
                c = f'{cur_src}.getWorld().getRegistryKey().getValue().toString().equals({jstr(_did)})'
                conds.append(f'!({c})' if neg else c)
                i = skip_after(nn, i, "dimension")
                continue
            else:
                return ("UNS", [], f"if {sub} (미지원)", [])
        elif tok == "as":
            sel = next_arg("targets")
            raw = sel["raw"] if sel else ""
            if raw == "@s":
                pass  # self 유지
            else:
                # 셀렉터 루프 - emit_as_loop 에서 처리하도록 신호
                return ("UNS", [], f"__AS_LOOP__", [])
            i += 1
            continue
        elif tok == "at":
            sel = next_arg("targets")
            raw_at = sel["raw"] if sel else ""
            if raw_at == "@s":
                # at @s = positioned as @s + rotated as @s.
                # no-op 이 아니다: on/as 재바인딩 후엔 소스 회전이 부모에서 상속돼 executor(@s)
                # 자기 회전과 다를 수 있다(예: on passengers 후 source 는 카트 회전, executor 는 모델).
                # executor 의 위치+회전으로 재바인딩해야 이후 `rotate @s ~` 의 '~' 가 @s 자기
                # 회전을 쓴다. (이전 no-op 은 모델이 카트(초기)방향으로 스냅하는 버그를 유발했음.)
                nv = f"kfcSrc{_uid()}"
                rebinds.append(
                    f'ServerCommandSource {nv} = (executor != null ? KfcGen.atEntity({cur_src}, executor) : {cur_src});')
                cur_src = nv
                i += 1
                continue
            # at <단일 셀렉터> - 그 엔티티의 위치+회전으로 소스 재바인딩 (executor 는 불변)
            psel = parse_selector(raw_at)
            # @s[...] (필터 포함)은 항상 단일(실행자). 가드 통과 시 executor 위치/회전, 아니면 null.
            if psel is not None and psel.base == "s":
                g = selector_cond(psel, cur_src)
                if g is None:
                    return ("UNS", [], f"at @s[{raw_at[:20]}] 가드 미해소", [])
                nv = f"kfcSrc{_uid()}"
                guard = "executor != null" if g == "(executor != null)" else g
                rebinds.append(
                    f'ServerCommandSource {nv} = (({guard}) ? KfcGen.atEntity({cur_src}, executor) : null);')
                cur_src = nv
                src_nullable = True
                i += 1
                continue
            single = psel and ((psel.base in ("n", "p", "r")) or psel.limit == 1)
            if not single:
                return ("UNS", [], f"at {raw_at[:25]} (비단일 위치 재바인딩 1차 미지원)", [])
            ent = _resrc(single_entity_expr(raw_at), cur_src)
            if ent is None:
                return ("UNS", [], f"at {raw_at[:25]} (단일 엔티티 해소 불가)", [])
            ev = f"_atE{len(rebinds)+1}"
            nv = f"kfcSrc{_uid()}"
            rebinds.append(f'net.minecraft.entity.Entity {ev} = {ent};')
            rebinds.append(
                f'ServerCommandSource {nv} = ({ev} != null ? KfcGen.atEntity({cur_src}, {ev}) : null);')
            cur_src = nv
            src_nullable = True
            i += 1
            continue
        elif tok == "positioned":
            # positioned as @s | positioned <pos>
            nxt = nn[i + 1] if i + 1 < len(nn) else None
            if nxt == "as":
                tsel = next_arg("targets")
                traw = tsel["raw"] if tsel else ""
                if traw == "@s":
                    nv = f"kfcSrc{_uid()}"
                    rebinds.append(f'ServerCommandSource {nv} = (executor != null ? '
                                   f'{cur_src}.withPosition(executor.getPos()) : null);')
                    cur_src = nv
                    src_nullable = True
                    i += 2
                    continue
                # positioned as <단일 셀렉터> - 그 엔티티의 위치로
                ent = _resrc(single_entity_expr(traw), cur_src)
                if ent is None:
                    return ("UNS", [], f"positioned as {traw[:20]} (단일 해소 불가)", [])
                ev = f"_posE{len(rebinds)+1}"
                nv = f"kfcSrc{_uid()}"
                rebinds.append(f'net.minecraft.entity.Entity {ev} = {ent};')
                rebinds.append(f'ServerCommandSource {nv} = ({ev} != null ? '
                               f'{cur_src}.withPosition({ev}.getPos()) : null);')
                cur_src = nv
                src_nullable = True
                i += 2
                continue
            praw = next_arg("pos")
            expr = pos_rebind_expr(praw["raw"], cur_src) if praw else None
            if expr is None:
                return ("UNS", [], f"positioned {praw and praw['raw']} (좌표 미지원)", [])
            nv = f"kfcSrc{_uid()}"
            if src_nullable:
                expr = f'({cur_src} == null ? null : {expr})'
            rebinds.append(f'ServerCommandSource {nv} = {expr};')
            cur_src = nv
            i += 2
            continue
        elif tok == "rotated":
            nxt = nn[i + 1] if i + 1 < len(nn) else None
            if nxt == "as":
                tsel = next_arg("targets")
                traw = tsel["raw"] if tsel else ""
                if traw == "@s":
                    nv = f"kfcSrc{_uid()}"
                    rebinds.append(
                        f'ServerCommandSource {nv} = (executor != null ? {cur_src}.withRotation('
                        f'new net.minecraft.util.math.Vec2f(executor.getPitch(), executor.getYaw())) : null);')
                    cur_src = nv
                    src_nullable = True
                    i += 2
                    continue
                # rotated as <단일 셀렉터> - 그 엔티티의 회전을 복사
                ent = _resrc(single_entity_expr(traw), cur_src)
                if ent is None:
                    return ("UNS", [], f"rotated as {traw[:25]} (단일 엔티티 해소 불가)", [])
                nv = f"kfcSrc{_uid()}"
                ev = f"_rotE{len(rebinds)+1}"
                rebinds.append(f'net.minecraft.entity.Entity {ev} = {ent};')
                rebinds.append(
                    f'ServerCommandSource {nv} = ({ev} != null ? {cur_src}.withRotation('
                    f'new net.minecraft.util.math.Vec2f({ev}.getPitch(), {ev}.getYaw())) : null);')
                cur_src = nv
                src_nullable = True
                i += 2
                continue
            rraw = next_arg("rot") or next_arg("rotation")
            expr = rot_rebind_expr(rraw["raw"], cur_src) if rraw else None
            if expr is None:
                return ("UNS", [], f"rotated {rraw and rraw['raw']} (회전 미지원)", [])
            nv = f"kfcSrc{_uid()}"
            if src_nullable:
                expr = f'({cur_src} == null ? null : {expr})'
            rebinds.append(f'ServerCommandSource {nv} = {expr};')
            cur_src = nv
            i += 2
            continue
        elif tok == "align":
            araw = next_arg("axes")
            if not araw:
                return ("UNS", [], "align 축 없음", [])
            axes = araw["raw"]
            comps = []
            for ax in ("x", "y", "z"):
                p = f"{cur_src}.getPosition().{ax}"
                comps.append(f"Math.floor({p})" if ax in axes else p)
            nv = f"kfcSrc{_uid()}"
            _al = (f'{cur_src}.withPosition(new net.minecraft.util.math.Vec3d({", ".join(comps)}))')
            if src_nullable:
                _al = f'({cur_src} == null ? null : {_al})'
            rebinds.append(f'ServerCommandSource {nv} = {_al};')
            cur_src = nv
            i += 2
            continue
        elif tok == "facing":
            nxt = nn[i + 1] if i + 1 < len(nn) else None
            if nxt == "entity":
                tsel = next_arg("targets")
                anchor = next_arg("anchor")
                traw = tsel["raw"] if tsel else ""
                eyes = "true" if (anchor and anchor["raw"] == "eyes") else "false"
                ent = _resrc(single_entity_expr(traw), cur_src)
                if ent is None:
                    return ("UNS", [], f"facing entity {traw[:20]} (단일 해소 불가)", [])
                ev = f"_faceE{_uid()}"
                nv = f"kfcSrc{_uid()}"
                rebinds.append(f'net.minecraft.entity.Entity {ev} = {ent};')
                rebinds.append(f'ServerCommandSource {nv} = KfcGen.facingEntity({cur_src}, {ev}, {eyes});')
                cur_src = nv
                src_nullable = True   # facingEntity 매치실패 -> null(fork 사망)
                i = skip_after(nn, i, "anchor") if anchor else (i + 2)
                continue
            praw = next_arg("pos")
            pe = cond_pos_expr(praw["raw"], cur_src) if praw else None
            if pe is None:
                return ("UNS", [], f"facing {praw and praw['raw']} (좌표 미지원)", [])
            nv = f"kfcSrc{_uid()}"
            _fc = f'KfcGen.facing({cur_src}, {pe})'
            if src_nullable:
                _fc = f'({cur_src} == null ? null : {_fc})'
            rebinds.append(f'ServerCommandSource {nv} = {_fc};')
            cur_src = nv
            i += 2
            continue
        elif tok == "anchored":
            anchor = next_arg("anchor")
            araw = anchor["raw"] if anchor else "feet"
            nv = f"kfcSrc{_uid()}"
            fn = "anchorEyes" if araw == "eyes" else "anchorFeet"
            _an = f'KfcGen.{fn}({cur_src})'
            if src_nullable:
                _an = f'({cur_src} == null ? null : {_an})'
            rebinds.append(f'ServerCommandSource {nv} = {_an};')
            cur_src = nv
            i = skip_after(nn, i, "anchor") if anchor else (i + 2)
            continue
        elif tok == "in":
            # [검증] ExecuteCommand: withWorld + DimensionType.getCoordinateScaleFactor 로
            # x/z 만 스케일(y 불변). 미존재 차원 → null 소스(fork 사망 — nullable 전파).
            d = next_arg("dimension")
            if not d:
                return ("UNS", [], "in 차원 인자 없음", [])
            _did = d["raw"] if ":" in d["raw"] else "minecraft:" + d["raw"]
            nv = f"kfcSrc{_uid()}"
            _inx = f'KfcGen.inDimension({cur_src}, {jstr(_did)})'
            if src_nullable:
                _inx = f'({cur_src} == null ? null : {_inx})'
            rebinds.append(f'ServerCommandSource {nv} = {_inx};')
            cur_src = nv
            src_nullable = True
            i = skip_after(nn, i, "dimension")
            continue
        elif tok == "on":
            return ("UNS", [], f"{tok} (컨텍스트 수정자 - 1차 미지원)", [])
        elif tok == "store":
            return (conds, [], "__STORE__", _nullprop_rebinds(rebinds))
        else:
            i += 1
    return (conds, loops, None, _nullprop_rebinds(rebinds))


def parse_clear_item(pred: str):
    """clear 아이템 술어 -> (item_id_or_#tag, custom_data_snbt|None). 미지원이면 None.
       지원: '*', '<id>', '#<tag>', '<id>[custom_data(=|~){...}]'."""
    pred = (pred or "*").strip()
    if re.fullmatch(r'MACROVAR_\d+', pred):
        # 매크로 아이템 자리($clear @s $(item)): 값은 임의 술어(id/태그/컴포넌트)일 수 있으므로
        # 런타임에 바닐라 ItemPredicateArgumentType 으로 파싱해 매칭한다(바닐라 재파싱과 동일).
        return (pred, "__RUNTIME_PRED__")
    if pred.startswith("#"):
        return (pred, None)
    m = re.match(r'^(\*|[a-z0-9_.:-]+)(?:\[(.*)\])?$', pred)
    if not m:
        return None
    item_id, comp = m.group(1), m.group(2)
    if not comp:
        return (item_id, None)
    cm = re.match(r'^(?:minecraft:)?custom_data[=~](\{.*\})$', comp.strip())
    if not cm:
        return None
    return (item_id, cm.group(1))




def skip_after(nn, i, token):
    """nn[i:] 에서 token 다음 인덱스로."""
    for j in range(i, len(nn)):
        if nn[j] == token:
            return j + 1
    return len(nn)


def _emit_as_loop_recursive(line, head, tail, em, sel, uuid_raw=None):
    """as <셀렉터> <컨텍스트 수정자...> run <타겟> 을, 셀렉터 루프 + 본문 재귀 emit 으로 변환.
       각 엔티티 e 에 대해 그 커맨드 소스(es)를 만들고, as 이후 chain 을 es 기준으로 재귀.
       uuid_raw: as <UUID리터럴> 단일 대상(sel=None) — entityByUuid 로 해소해 1회 실행."""
    _asE = _fresh_var("_asE")
    _asSrc = _fresh_var("_asSrc")
    if uuid_raw is not None:
        # UUID 단일 대상: as @s 케이스와 같은 '단일-open' 패턴(여는 중괄호 1개).
        # instanceof 패턴 바인딩이 null 체크를 겸한다(Entity 는 항상 Entity → null 만 거름).
        loop_open = [f'if (KfcGen.entityByUuid(ctx, {jstr(uuid_raw)}) '
                     f'instanceof net.minecraft.entity.Entity {_asE}) {{']
    else:
        loop_open = entity_loop_open(sel, _asE)
    if loop_open is None:
        if _sel_has_extra(sel):
            em.reason = "as 셀렉터 추가필드(nbt/team/level/name/adv) 폴백경로 미지원"
            return False
        # 타입 미지정 @e[limit=N] 등 - anyType 순회로
        if sel.base in ("e", "n") and not sel.type_id:
            tp = jarr_tags(sel.tags_pos); tn = jarr_tags(sel.tags_neg)
            lo, hi = sel.distance if sel.distance else (None, None)
            dmin = _dist_arg(lo); dmax = _dist_arg(hi)
            loop_open = [f'for (net.minecraft.entity.Entity {_asE} : KfcGen.allEntitiesAnyType('
                         f'ctx, executor, {tp}, {tn}, {dmin}, {dmax})) {{']
        else:
            if sel.base == "s":
                # as @s: 실행자 자신에 대한 1회 실행. 태그/타입/predicate/scores 가드 통과 시에만.
                guards = ["executor != null"]
                if sel.type_id:
                    jt = resolve_entity_types(sel) if sel.type_is_tag else [entity_type_java(sel.type_id)]
                    if not jt or None in jt:
                        em.reason = "as @s 타입 미해소"; return False
                    inner_t = " || ".join(f"executor.getType() == {t}" for t in jt)
                    guards.append(f"!({inner_t})" if sel.type_neg
                                  else (f"({inner_t})" if len(jt) > 1 else inner_t))
                for t in sel.tags_pos:
                    guards.append(f'executor.getCommandTags().contains({jstr(t)})')
                for t in sel.tags_neg:
                    guards.append(f'!executor.getCommandTags().contains({jstr(t)})')
                if sel.predicates:
                    pg = predicate_guards(sel.predicates, "executor", player=False)
                    if pg is None:
                        em.reason = "as @s predicate 미해소"; return False
                    guards += pg
                guards += _score_conds(sel, 'executor')
                # @s 의 distance 는 자기 자신(거리 0) -> 무시(항상 통과)
                loop_open = [f'if ({" && ".join(guards)}) {{ net.minecraft.entity.Entity {_asE} = executor;']
            else:
                em.reason = f"as 루프(재귀) 셀렉터 미해소: {sel.base}"
                return False

    # as 이후 chain 재구성: execute + (as/targets 제거한 head 나머지) + run + tail
    # ── as 앞 수정자/조건 보존 ──
    # [버그 수정] 기존엔 head[:as_idx] 를 통째로 버려 `execute if score ... as <UUID> at @s run tp ...`
    # 의 score 가드가 탈락 — ani1/stevemo:frame 컷씬에서 tp 가 매 틱 무조건 실행돼
    # 리그가 밀려나거나(^.1/^.074/^21 창), 추락(~-.5)하거나, 절대좌표로 스냅백(272)했다
    # (= "스티브 안 보임" + 매 틱 전진→스냅백 "롤백" 증상의 원인).
    # 바닐라 의미: as 앞 수정자/조건은 바깥 실행자·소스 기준으로 평가되므로,
    # parse_modifiers 로 조건(pconds)·재바인딩(prebinds)을 뽑아 루프 밖을 감싼다.
    # positioned/rotated 의 as 는 실행자 전환이 아니므로 emit_as_loop 과 동일하게 건너뛴다.
    as_idx = None
    _prev_node = None
    for _i, _s in enumerate(head):
        _nd = _s.get("node")
        if _nd == "as" and _prev_node not in ("positioned", "rotated"):
            as_idx = _i
            break
        if _nd is not None:
            _prev_node = _nd
    if as_idx is None:
        em.reason = "as 루프(재귀): 실행자 전환 as 없음"
        return False
    pre_conds, pre_rebinds = [], []
    pre_src = "source"
    pre_head = head[:as_idx]
    if pre_head:
        pconds, ploops, puns, prebinds = parse_modifiers(pre_head)
        if puns is not None or ploops:
            # as 앞 루프/미지원 수정자(at <루프셀렉터>, on 등)는 바깥 감싸기로 표현 불가
            # → 명시 거부(브릿지 폴백). 침묵 탈락보다 항상 안전하다.
            em.reason = f"as 루프(재귀) 앞 수정자 미지원: {puns or 'loops'}"
            return False
        pre_conds, pre_rebinds = pconds, prebinds
        if pre_rebinds:
            m = re.match(r'.*ServerCommandSource (kfcSrc\d+)', pre_rebinds[-1])
            if m:
                pre_src = m.group(1)
    rest = []
    rm_tn = rm_ta = False
    for item in head[as_idx + 1:]:
        if not rm_tn and item.get("node") == "targets":
            rm_tn = True; continue
        if not rm_ta and item.get("arg") == "targets":
            rm_ta = True; continue
        rest.append(item)
    rebuilt = [{"node": "execute", "type": "LiteralCommandNode"}] + rest
    if tail:
        rebuilt += [{"node": "run", "type": "LiteralCommandNode"}] + tail

    inner = Emitted(line=line)
    if not emit_execute_with_src(line, rebuilt, inner, _asSrc):
        em.reason = inner.reason or "as 루프(재귀) 본문 미지원"
        return False

    # 루프 본문: 각 엔티티의 소스 생성 후 본문. executor/source 참조를 루프 변수로 치환.
    body = inner.java
    # 본문 내 'executor' 는 as 의 self(_asE), 'source' 는 그 소스(_asSrc)
    body = [_nonnull_simplify(re.sub(r'\bexecutor\b', _asE, b), _asE) for b in body]

    out = list(loop_open)
    # 바닐라 as <셀렉터> 는 executor 만 교체하고 위치/회전/앵커/차원은 부모 소스에서 상속한다
    # (엔티티의 getCommandSource 로 새로 만들면 위치/회전이 엔티티 기본값으로 리셋돼 잘못됨).
    # source 는 호출 컨텍스트(on/at 등)에서 적절한 부모 소스로 치환된다.
    # as 앞 rebind(positioned 등)가 있으면 그 소스(pre_src)를 부모로 사용한다.
    out.append(f'    ServerCommandSource {_asSrc} = {pre_src}.withEntity({_asE});')
    for b in body:
        out.append("    " + b)
    out.append("}")
    # ── as 앞 조건/재바인딩으로 루프 전체를 감싼다(바닐라: 조건 불통과 시 0회 실행) ──
    if pre_conds or pre_rebinds:
        wrapped = ["{"]
        wrapped += ["    " + s for s in pre_rebinds]
        if pre_conds:
            wrapped.append(f'    if ({" && ".join(pre_conds)}) {{')
        wrapped += ["    " + s for s in out]
        if pre_conds:
            wrapped.append("    }")
        wrapped.append("}")
        out = wrapped
    em.java.extend(out)
    em.kind = "native"
    return True


def _es_used(varname: str, *code_blocks) -> bool:
    """as/단일 셀렉터 루프에서 `ServerCommandSource <varname> = ...withEntity(e)` 가
       실제로 후속 코드(mod_rebinds + body)에서 참조되는지 검사.
       [구조 최적화] 본문이 소스 컨텍스트(위치/회전/실행자)를 안 쓰고 엔티티 e 만
       직접 조작하는 경우(e.stopRiding()/e.addCommandTag()/KfcGen.xxx(e,...) 등),
       withEntity(e) 는 SCS 객체 1개 + name/displayName 문자열을 만들어 통째로 버리는
       dead 할당이다(실측: 생성물에 다수 존재). 참조되지 않으면 선언 자체를 생략한다.
       단어 경계로 검사해 kfcSrc2/kfcSrc20 같은 접두 오탐을 막는다."""
    pat = re.compile(r'\b' + re.escape(varname) + r'\b')
    for block in code_blocks:
        for line in block:
            if pat.search(line):
                return True
    return False


def emit_as_loop(line: str, head: list[dict], tail: list[dict], em: Emitted) -> bool:
    """
    execute as <selector> [at @s] [if ...] run <target>  ->  네이티브 엔티티/플레이어 루프.
    레퍼런스 src/ 패턴:
      for (Entity e : world.getEntitiesByType(TYPE, e -> e.getCommandTags().containsAll(...))) {
          ServerCommandSource s = e.getCommandSource(world).withSilent().withLevel(2);
          Target.execute(s);
      }
    1차 지원: as 다음에 (at @s)? 와 단순 run 타겟. 중간에 if/positioned 등 복합 수정자 있으면 브릿지.
    """
    nodes, args = split_chain(head)
    nn = [n["node"] for n in nodes]

    # ── 진짜 '실행자 전환 as' 찾기 ──
    # `positioned as <sel>` / `rotated as <sel>` 의 'as' 는 실행자 전환이 아니다.
    # head 를 직접 순회해, 직전 node 가 positioned/rotated 가 아닌 첫 as 를 찾는다.
    as_i = None
    prev_node = None
    for idx, item in enumerate(head):
        nd = item.get("node")
        if nd == "as" and prev_node not in ("positioned", "rotated"):
            as_i = idx
            break
        if nd is not None:
            prev_node = nd
    if as_i is None:
        em.reason = "as 루프: 실행자 전환 as 없음"
        return False
    # as 구간(as + targets 노드/인자) 끝 인덱스와 셀렉터 raw
    j = as_i + 1
    sel_raw = ""
    while j < len(head) and (head[j].get("node") == "targets" or head[j].get("arg") == "targets"):
        if head[j].get("arg") == "targets":
            sel_raw = head[j]["value"]["raw"]
        j += 1
    sel = parse_selector(sel_raw)
    if sel is None:
        # UUID 리터럴 대상(as 7437-0-14-0-0 ... — stevemo 컷씬): 단일 엔티티 1회 실행.
        # 재귀 경로가 as 이후 수정자(at/facing 등)까지 일괄 처리하므로 그리로 위임.
        if sel_raw and re.fullmatch(
                r'[0-9a-fA-F]{1,8}-[0-9a-fA-F]{1,4}-[0-9a-fA-F]{1,4}'
                r'-[0-9a-fA-F]{1,4}-[0-9a-fA-F]{1,12}', sel_raw):
            return _emit_as_loop_recursive(line, head, tail, em, None, uuid_raw=sel_raw)
        em.reason = f"as 셀렉터 파싱 실패: {sel_raw}"
        return False

    # ── as 앞/뒤 수정자 분리 ──
    # 바닐라 의미: as 앞 수정자(positioned as @s 등)는 '바깥 실행자/소스' 기준으로 평가돼
    # 루프 밖에 위치해야 하고, as 뒤 수정자는 루프 변수(각 대상) 기준이다.
    pre_head = head[:as_i]
    sub_head = head[j:]

    pre_src = "source"
    pre_conds, pre_rebinds = [], []
    if pre_head:
        pre_mods = parse_modifiers(pre_head)
        pconds, ploops, puns, prebinds = pre_mods
        if puns is not None or ploops:
            # as 앞 미지원/루프 수정자 - 재귀 경로 시도, 아니면 거부
            recursive_mods = ("on ", "at ", "facing", "rotated as", "positioned as", "anchored")
            if puns and any(puns.startswith(m) or m in puns for m in recursive_mods):
                return _emit_as_loop_recursive(line, head, tail, em, sel)
            em.reason = f"as 루프 앞 수정자 미지원: {puns or 'loops'}"
            return False
        pre_conds, pre_rebinds = pconds, prebinds
        if pre_rebinds:
            m = re.match(r'.*ServerCommandSource (kfcSrc\d+)', pre_rebinds[-1])
            if m:
                pre_src = m.group(1)
            # 루프 본문/post 의 kfcSrcN 과 충돌 방지 - pre 네임스페이스
            def _pns(s):
                return re.sub(r'\bkfcSrc(\d+)\b', r'kfcSrcPre\1', s)
            pre_rebinds = [_pns(b) for b in pre_rebinds]
            pre_conds = [_pns(c) for c in pre_conds]
            pre_src = _pns(pre_src)

    mconds, mloops, muns, mrebinds = parse_modifiers(sub_head)
    if muns is not None:
        if muns == "__AS_LOOP__":
            em.reason = "as 루프 중첩(as ... as ...) - 1차 미지원"
            return False
        if muns == "__STORE__":
            em.reason = "as 루프 + store - 1차 미지원"
            return False
        # as 이후에 on/at/facing 등 재바인딩 수정자가 있으면, 그 부분을 루프 변수 소스(es)로
        # 재귀 emit 한다. (parse_modifiers 가 단독으론 처리 못하는 컨텍스트 수정자)
        recursive_mods = ("on ", "at ", "facing", "rotated as", "positioned as", "anchored")
        if any(muns.startswith(m) or m in muns for m in recursive_mods):
            return _emit_as_loop_recursive(line, head, tail, em, sel)
        em.reason = f"as 루프 + {muns}"
        return False
    # 수정자 조건을 루프 변수 기준으로 치환 (대상별 평가)
    # 루프 변수 e 는 모든 사용 경로에서 non-null 보장(enhanced-for / `if (e != null)` 가드 내부)
    # → 치환으로 죽은 `e != null` 체크를 제거해 분기·바이트코드를 줄인다(시맨틱 불변).
    mod_conds = _nonnull_simplify_conds(
        [re.sub(r'\bsource\b', 'es', re.sub(r'\bexecutor\b', 'e', c)) for c in mconds], 'e')
    # 수정자 rebind 문장도 루프 변수(e/es) 기준으로 치환 - 조건이 이를 참조하므로 본문 선두에 배치
    mod_rebinds = []
    for stmt in mrebinds:
        s = re.sub(r'\bexecutor\b', 'e', stmt)
        s = re.sub(r'\bsource\b', 'es', s)
        mod_rebinds.append(_nonnull_simplify(s, 'e'))

    # 타겟 emit (루프 본문) - 셀렉터 컨텍스트에서 self = 루프 변수 e
    inner = Emitted(line=line)
    if not tail:
        return False
    tail_command = next((s["node"] for s in tail if "node" in s), None)
    if not emit_target(line, tail_command, tail, inner):
        em.reason = f"as 루프 본문({tail_command}) 네이티브화 불가"
        return False

    # 본문에서 executor/source 를 루프 변수로 치환
    body = []
    for stmt in inner.java:
        s = re.sub(r'\bexecutor\b', 'e', stmt)
        s = re.sub(r'\bsource\b', 'es', s)
        body.append(_nonnull_simplify(s, 'e'))
    # as 이후 수정자 리바인딩(positioned/rotated/at 등)의 최종 소스를 본문이 써야 한다.
    # (이전엔 리바인드 문장만 만들고 본문은 es 를 그대로 써서, `as @e ... positioned as @s
    #  rotated ~-90 run function X` 의 위치/회전이 X 에 전달되지 않았음 - 버텍스 배치 오작동.)
    if mod_rebinds:
        _bfinal = _v2_final_src(mod_rebinds, "es")
        if _bfinal != "es":
            body = [re.sub(r'\bes\b', _bfinal, b) for b in body]

    # [D-10] as+at 융합: 첫 수정자 리바인딩이 `at @s`(→ atEntity(es, e))이고, 나머지 리바인딩·
    # 조건·본문 어디에도 융합 전 소스(es) 참조가 남지 않으면 withEntity+atEntity 3-생성 체인을
    # KfcGen.withEntityAt(부모, e) 단일 생성으로 병합한다. es 참조가 남으면(= at 이전 위치 기준
    # 조건 등) 원형 유지(fail-closed) — atEntity 자체도 D-10 융합 1-생성이라 그 경우도 손해 없음.
    _fuse_at = None
    if mod_rebinds:
        _mAT = re.match(
            r'^\s*ServerCommandSource (kfcSrc\d+) = '
            r'(?:KfcGen\.atEntity\(es, e\)|\(e != null \? KfcGen\.atEntity\(es, e\) : es\));\s*$',
            mod_rebinds[0])
        if _mAT:
            _atv = _mAT.group(1)
            if not any(re.search(r'\bes\b', x)
                       for x in (mod_rebinds[1:] + mod_conds + body)):
                _fuse_at = _atv
    if _fuse_at is not None:
        mod_rebinds = [re.sub(r'\b' + _fuse_at + r'\b', 'es', x) for x in mod_rebinds[1:]]
        mod_conds = [re.sub(r'\b' + _fuse_at + r'\b', 'es', c) for c in mod_conds]
        body = [re.sub(r'\b' + _fuse_at + r'\b', 'es', b) for b in body]

    # 엔티티 타입 결정
    if sel.base in ("a", "p", "r"):
        # 플레이어 루프
        head_line = "for (ServerPlayerEntity e : ctx.allPlayers) {"
        src_line = (f"ServerCommandSource es = KfcGen.withEntityAt({pre_src}, e);"  # [D-10] as+at 단일 생성
                    if _fuse_at is not None else
                    f"ServerCommandSource es = {pre_src}.withEntity(e);")  # 바닐라 as: 부모 컨텍스트 상속
        type_filter = None
    else:
        jtypes = resolve_entity_types(sel)
        if jtypes is None and not sel.type_id:
            jtypes = "ANY"   # 타입 미지정 @e -> 전 엔티티 순회
        if jtypes is None:
            em.reason = f"as @e type={'#' if sel.type_is_tag else ''}{sel.type_id} - 엔티티타입 미해소(태그 JSON 없음/미지원 타입)"
            return False
        # type=!X : 양성 타입 순회는 정반대. 전 엔티티 순회 + 타입 제외 가드로 전환.
        if sel.type_neg and jtypes != "ANY":
            _excl = " || ".join(f"en.getType() == {t}" for t in jtypes)
            type_exclude_cond = f"!({_excl})"
            jtypes = "ANY"
        else:
            type_exclude_cond = None
        src_line = (f"ServerCommandSource es = KfcGen.withEntityAt({pre_src}, e);"  # [D-10] as+at 단일 생성
                    if _fuse_at is not None else
                    f"ServerCommandSource es = {pre_src}.withEntity(e);")  # 바닐라 as: 부모 컨텍스트 상속

    # 태그 필터 + scores 필터 (en. 기준; 플레이어 루프는 아래서 e. 로 치환)
    conds = []
    for t in sel.tags_pos:
        conds.append(f'en.getCommandTags().contains({jstr(t)})')
    for t in sel.tags_neg:
        conds.append(f'!en.getCommandTags().contains({jstr(t)})')
    # 볼륨 박스 필터(x/y/z/dx/dy/dz) - 누락 시 `as @a[박스] run` 이 박스를 무시하고
    # 태그만 맞으면 전원 실행됨(예: gameend 의 multiplayroom:play 가 대기방 밖 플레이어에게도 실행).
    # _entity_loop_open_core 와 동일하게 posInBox 조건을 넣어 바닐라 박스 평가와 일치시킨다.
    if sel.volume is not None:
        _vdx, _vdy, _vdz = sel.volume
        conds.append(f'KfcGen.posInBox({box_origin_expr(sel, pre_src)}, '
                     f'{_vdx}, {_vdy}, {_vdz}, en)')
    # distance= 필터 - 바닐라는 '소스 위치' 기준 (executor 위치 아님)
    if sel.distance:
        _dlo, _dhi = sel.distance
        _dmin = _dist_arg(_dlo)
        _dmax = _dist_arg(_dhi)
        conds.append(f'KfcGen.inRange({pre_src}.getPosition(), en, {_dmin}, {_dmax})')
    conds += _score_conds(sel, 'en')
    for pid in sel.predicates:
        expr = PREDICATES.get(pid)
        if expr is None:
            em.reason = f"as 루프 predicate({pid}) 컴파일 불가"
            return False
        conds.append(_nonnull_simplify(expr.replace("{E}", "en"), "en"))
    conds += _selector_extra_conds(sel, "en")
    filt = " && ".join(conds) if conds else "true"

    out = []
    # as 앞 수정자(pre)를 루프 밖에 배치하는 공통 래퍼
    def _wrap_pre(w):
        if pre_rebinds and (_rebinds_nullable(pre_rebinds)
                            or any('executor != null ?' in s for s in pre_rebinds)):
            w = [f'if ({pre_src} != null) {{'] + ["    " + b for b in w] + ["}"]
        for c in reversed(pre_conds):
            w = [f'if ({c}) {{'] + ["    " + b for b in w] + ["}"]
        if pre_rebinds:
            w = ["{"] + ["    " + b for b in (list(pre_rebinds) + w)] + ["}"]
        return w
    if _rebinds_nullable(mod_rebinds):
        _msrc = _v2_final_src(mod_rebinds, "es")
        mod_conds = [f'{_msrc} != null'] + mod_conds   # 매치실패 fork 사망 -> continue
    # ── @s: 셀렉터는 '소스(실행자) 엔티티 자신'만 가리킨다. 절대 월드를 순회하지 않는다. ──
    #   @s[scores=...]/@s[tag=...] 는 소스 엔티티가 필터를 만족할 때만 1회 실행한다.
    #   이전엔 @s 에 필터가 붙으면 base 가 a/p/r 도 limit==1 도 아니어서 @e(ANY)처럼
    #   resolve_entity_types -> jtypes="ANY" -> iterateEntities() 전 엔티티 스캔으로 떨어졌다.
    #   그 결과 멀티플레이에서 한 플레이어의 트리 하강이 '월드의 모든 매칭 엔티티'로 재귀해
    #   다른 플레이어의 노트를 그 플레이어 위치가 아닌 소스 위치로 교차 재생 -> 브금이 깨졌다
    #   (음정/위치/감쇠 등 소리 속성이 바닐라와 달라짐). 소스 엔티티 1개만 검사하도록 고정한다.
    if sel.base == "s":
        sconds = [re.sub(r'\ben\b', 'e', c) for c in conds]
        guard = " && ".join(["e != null"] + sconds)
        out.append(f'{{ net.minecraft.entity.Entity e = {pre_src}.getEntity();')
        out.append(f'  if ({guard}) {{')
        if _es_used("es", mod_rebinds, mod_conds, body):
            # [D-10] 융합 발동 시 at@s 리바인딩이 mod_rebinds 에서 제거돼 있으므로 여기서도
            # 단일 생성 withEntityAt 으로 만들어야 위치·회전 시맨틱이 보존된다.
            if _fuse_at is not None:
                out.append(f'    ServerCommandSource es = KfcGen.withEntityAt({pre_src}, e);')
            else:
                out.append(f'    ServerCommandSource es = {pre_src}.withEntity(e);')
        for s in mod_rebinds:
            out.append("    " + s)
        if mod_conds:
            out.append(f'    if (({" && ".join(mod_conds)})) {{')
            for b in body:
                out.append("        " + b)
            out.append("    }")
        else:
            for b in body:
                out.append("    " + b)
        out.append("} }")
        em.java.extend(_wrap_pre(out))
        em.kind = "native"
        return True
    # ── 단일 셀렉터(@n/@p/@r/limit=1): 루프가 아니라 '소스 위치 기준 최근접 1개' 선택 ──
    # (@n 을 전체 루프로 풀면 바닐라와 의미가 달라진다 - 최근접 하나만 실행 대상.)
    if sel.base in ("n", "p", "r") or sel.limit == 1:
        ent = single_entity_expr(sel_raw)
        if ent is None:
            em.reason = f"as 단일 셀렉터({sel_raw[:30]}) 해소 불가"
            return False
        ent = re.sub(r'\bsource\b', pre_src, ent)  # 셀렉터 평가 origin = as 앞 수정자 적용 소스
        out.append(f'{{ net.minecraft.entity.Entity e = {ent};')
        out.append('  if (e != null) {')
        if _es_used("es", mod_rebinds, mod_conds, body):
            # [D-10] 융합 발동 시 at@s 리바인딩이 mod_rebinds 에서 제거돼 있으므로 여기서도
            # 단일 생성 withEntityAt 으로 만들어야 위치·회전 시맨틱이 보존된다.
            if _fuse_at is not None:
                out.append(f'    ServerCommandSource es = KfcGen.withEntityAt({pre_src}, e);')
            else:
                out.append(f'    ServerCommandSource es = {pre_src}.withEntity(e);')
        for s in mod_rebinds:
            out.append("    " + s)
        if mod_conds:
            out.append(f'    if (({" && ".join(mod_conds)})) {{')
            for b in body:
                out.append("        " + b)
            out.append("    }")
        else:
            for b in body:
                out.append("    " + b)
        out.append("} }")
        em.java.extend(_wrap_pre(out))
        em.kind = "native"
        return True
    mod_guard = f'    if (!({" && ".join(mod_conds)})) continue;' if mod_conds else None
    mr1 = ["    " + s for s in mod_rebinds]    # 1단 들여쓰기(es 정의 뒤)
    mr2 = ["        " + s for s in mod_rebinds] # 2단(다중타입 루프)
    # [구조 최적화] es(withEntity(e)) 가 mod_rebinds/body 어디서도 참조되지 않으면
    # dead 할당(SCS 객체+name 문자열 낭비)이므로 선언 자체를 생략한다. 삽입부는
    # 빈 src_line 을 걸러 빈 줄이 남지 않게 한다.
    if not _es_used("es", mod_rebinds, mod_conds, body):
        src_line = ""
    lim = sel.limit
    if sel.base in ("a", "p", "r"):
        out.append("for (ServerPlayerEntity e : ctx.allPlayers) {")
        pconds = [re.sub(r'\ben\b', 'e', c) for c in conds]
        if pconds:
            out.append(f'    if (!({" && ".join(pconds)})) continue;')
        if src_line: out.append("    " + src_line)
        out.extend(mr1)
        if mod_guard:
            out.append(mod_guard)
        for b in body:
            out.append("    " + b)
        out.append("}")
    elif jtypes == "ANY":
        # 타입 미지정 @e -> 전 엔티티 순회 (+ limit). sort=nearest 면 거리순 정렬+제한.
        if _want_nearest(sel) and lim:
            _lo, _hi = sel.distance if sel.distance else (None, None)
            _dmn = "-1" if _lo is None else str(_lo)
            _dmx = "-1" if _hi is None else str(_hi)
            out.append(f"for (Entity e : KfcGen.nearestNAnyType(ctx, source.getPosition(), "
                       f"{jarr_tags(sel.tags_pos)}, {jarr_tags(sel.tags_neg)}, "
                       f"{_dmn}, {_dmx}, {lim}, true)) {{")
            out.append(f"    Entity en = e; if (!({filt})) continue;")
            if src_line: out.append("    " + src_line)
            out.extend(mr1)
            if mod_guard:
                out.append(mod_guard)
            for b in body:
                out.append("    " + b)
            out.append("}")
        else:
            if lim:
                out.append("{ int _lim = 0;")
            # 틱 단위 스냅샷 사용: 한 틱 안 다수의 as @e 루프가 world.iterateEntities() 를
            # 매번 재순회하던 비용 제거(enemy:tick 등 매 틱 수십 회).
            # 단, 본문이 임의 명령(summon/kill/run function)이라 순회 중 엔티티 집합이 바뀔 수 있다.
            # 바닐라 as @e 는 '선택 시점에 고정된 집합' 을 순회(루프 중 스폰/킬 무관)하므로,
            # 공유 스냅샷을 '복사' 해서 돈다 → 고증 정확 + ConcurrentModificationException 방지.
            # (복사는 얕은 참조 복사라 저렴; 필터/태그/위치는 참조에서 라이브로 읽어 영향 없음.)
            # 정렬 복사(passengerFirst)의 틱 캐시판 — 순서·집합 동일, 루프마다의 복사/정렬 제거.
            out.append("for (Entity e : KfcGen.passengerFirstSnap(ctx)) {")
            # 바닐라 @e/@n 기본 술어 Entity::isAlive (kill 직후 시체 20틱 제외 — 바이트코드 확인)
            out.append(f"    Entity en = e; if (!(en.isAlive() && ({filt}))) continue;")
            if src_line: out.append("    " + src_line)
            out.extend(mr1)
            if mod_guard:
                out.append(mod_guard)
            for b in body:
                out.append("    " + b)
            if lim:
                out.append(f"    if (++_lim >= {lim}) break;")
            out.append("}")
            if lim:
                out.append("}")
    elif len(jtypes) == 1:
        if lim:
            out.append("{ int _lim = 0;")
        # 바닐라 arbitrary 순회 순서 재현(고증): distance(상한)+limit 인 경우, 바닐라는
        # EntitySelector.appendEntitiesFromWorld 가 box != null 이면 collectEntitiesByType(filter,
        # box, ...) = SectionedEntityCache 섹션순으로 순회한다. no-box(EntityIndex 소환순)로 돌면
        # limit early-out 의 first-N 이 달라진다(겹친 vertex 등). 그래서 max거리+limit 일 때만
        # box 오버로드로 섹션순을 맞춘다(SET 동일, 순서만 바닐라화; limit 없으면 SET 무관이라 유지).
        _has_max = sel.distance is not None and sel.distance[1] is not None
        if lim and _has_max:
            out.append(f"for (Entity e : KfcGen.entitiesByTypeBox(ctx, {jtypes[0]}, "
                       f"KfcGen.rangeBox({pre_src}.getPosition(), {_dist_arg(sel.distance[1])}),")
            out.append(f"        en -> {filt})) {{")
        else:
            # 틱 스냅샷 타입버킷 복사 순회(월드 인덱스 재순회 + 결과 리스트 생성 제거).
            # 집합·순서 동일: 버킷 = 스냅샷(EntityIndex 순) 부분수열 = getEntitiesByType 순회 순서.
            # 바닐라 @e 기본 술어 isAlive 는 명시 가드로 재현(위 스냅샷(untyped) 경로와 동일).
            # (#4) 본문·필터·가드 전체가 '엔티티 집합/버킷을 바꿀 수 없는' 호출만으로 구성됨이
            # 화이트리스트 검사로 증명되면 복사 자체를 생략(typeBucketRO — 무복사 직접 순회).
            _bucket_fn = ("typeBucketRO" if _loop_body_entity_safe(
                [src_line or "", mod_guard or "", filt] + list(mr1) + list(body))
                else "typeBucketCopy")
            out.append(f"for (Entity e : KfcGen.{_bucket_fn}(ctx, {jtypes[0]})) {{")
            out.append(f"    Entity en = e; if (!(en.isAlive() && ({filt}))) continue;")
        if src_line: out.append("    " + src_line)
        out.extend(mr1)
        if mod_guard:
            out.append(mod_guard)
        for b in body:
            out.append("    " + b)
        if lim:
            out.append(f"    if (++_lim >= {lim}) break;")
        out.append("}")
        if lim:
            out.append("}")
    else:
        # 다중 타입(예: #kartmodels) -> 레퍼런스 src/ 처럼 타입 Set 순회
        typeset = ", ".join(jtypes)
        if lim:
            out.append("{ int _lim = 0;")
        # Set.of 는 매 실행 할당 + JVM-SALT 랜덤 순회순서(비결정). 배열 리터럴은 방출 순서
        # (=타입태그 해소 순서) 그대로 순회 — 결정적이며 pass-4 상수 호이스팅 대상이 된다.
        out.append(f"for (EntityType<?> kfcType : new net.minecraft.entity.EntityType<?>[]{{{typeset}}}) {{")
        out.append(f"    for (Entity e : ctx.world.getEntitiesByType(kfcType,")
        out.append(f"            en -> {filt})) {{")
        if src_line: out.append("        " + src_line)
        out.extend(mr2)
        if mod_guard:
            out.append("    " + mod_guard)
        for b in body:
            out.append("        " + b)
        if lim:
            out.append(f"        if (++_lim >= {lim}) break;")
        out.append("    }")
        if lim:
            out.append("    if (_lim >= {0}) break;".format(lim))
        out.append("}")
        if lim:
            out.append("}")

    em.java.extend(_wrap_pre(out))
    em.kind = "native"
    return True


def store_success_value_expr(tail: list[dict], em: Emitted,
                             self_expr: str = "executor", src_expr: str = "source") -> str | None:
    """execute store success ... run <소스> 의 성공(0/1) 값 식. data modify 소스만 네이티브 지원.
       (data get/scoreboard get 등은 success 시맨틱이 값과 달라 여기서 미지원→폴백.)
       - data modify ... set value <raw>  : NbtPath.put 변경 개수>0 (값이 실제 바뀌면 1)
       - data modify ... <mode> from ...  : 소스 존재=성공 (기존 from 경로 재사용, dres 0/1)
       바닐라: /data modify 의 store success = 수정된 노드가 1개 이상이면 1."""
    if not tail:
        em.reason = "store success 소스 없음"
        return None
    nodes, args = split_chain(tail)
    nn = [n["node"] for n in nodes]
    # ── scoreboard players set|add|remove 소스 ──
    # 바닐라 ScoreboardCommand: set/add/remove 는 홀더 컬렉션에 쓰고 정수를 반환할 뿐,
    # 실패(예외) 경로는 '홀더 셀렉터 해소 실패'(매치 0)뿐이다. 따라서
    #   · 리터럴 홀더(#가짜플레이어/이름): 해소 실패 없음 → 항상 성공(1)
    #   · @s / @s[...]: 실행자 부재·조건 불일치 = 해소 실패 → 커맨드 실패(0, 소스 미실행)
    # 그 외 셀렉터(@e/@a 등)는 '매치 0 = 실패' 판정까지 얹어야 하므로 1차 미지원(fail-closed).
    # (store 앞의 if/unless 는 emit_execute 가 바깥 가드로 감싼다 — 좌→우 시맨틱상
    #  조건 실패 시 store 자체가 등록되지 않아 아무것도 저장되지 않는 것과 일치.)
    if (nn[0] == "scoreboard" and len(nn) > 2 and nn[1] == "players"
            and nn[2] in ("set", "add", "remove")):
        holder = args["targets"][0]["raw"]
        obj = args["objective"][0]["raw"]
        n = args["score"][0]["raw"]
        hg = self_holder_guard(holder)
        if hg is None:
            em.reason = f"store success scoreboard 셀렉터 홀더({holder[:25]}) 1차 미지원"
            return None
        h, guard = hg
        if self_expr != "executor":
            h = re.sub(r'\bexecutor\b', self_expr, h)
            guard = re.sub(r'\bexecutor\b', self_expr, guard) if guard else guard
        nj = jint(n)
        verb = nn[2]
        if verb == "set":
            stmt = f'KfcGen.setScore(sb, {h}, {jstr(obj)}, {nj});'
        elif verb == "add":
            stmt = f'KfcGen.addScore(sb, {h}, {jstr(obj)}, {nj});'
        else:
            stmt = f'KfcGen.addScore(sb, {h}, {jstr(obj)}, -({nj}));'
        if guard is None:
            em.side_effects = [stmt]
            return "1"
        sres = _fresh_var("sres")
        em.side_effects = [f'int {sres} = 0;',
                           f'if ({guard}) {{ {stmt} {sres} = 1; }}']
        return sres
    if nn[0] == "data" and len(nn) > 1 and nn[1] == "merge" and (nn[2] if len(nn) > 2 else "") == "storage":
        # 바닐라 executeMerge: 병합 결과가 원본과 같으면 실패(0) — Changed 판정판 사용.
        sid = first_arg(args, "target")
        raw = first_arg(args, "nbt")
        if not sid or raw is None:
            em.reason = "store success data merge storage 인자 없음"
            return None
        return f'(KfcGen.storageMergeSnbtChanged(server, {jstr(sid)}, {jstr(raw)}) ? 1 : 0)'
    if nn[0] != "data" or "modify" not in nn:
        em.reason = "store success <non-data-modify> 소스 1차 미지원"
        return None
    tgtkind = nn[2] if len(nn) > 2 else None
    # set from ...: source_value_expr(dres=실제 변경 종단 수)를 success(0/1) 로 접는다.
    # (경로가 다중 종단에 매치되면 count>1 가능 — success 는 항상 0/1 이어야 한다.)
    if "from" in nn:
        v = source_value_expr(tail, em, self_expr, src_expr)
        if v is None:
            return None
        return f'(({v}) > 0 ? 1 : 0)'
    mode = next((m for m in ("set", "append", "prepend", "merge") if m in nn), "set")
    val = args.get("value", [{}])[0]
    raw = val.get("raw")
    if raw is None:
        em.reason = "store success data modify 값 raw 없음"
        return None
    if tgtkind == "storage":
        sid = first_arg(args, "target")
        path = first_arg(args, "targetPath")
        if sid is None or path is None:
            em.reason = "store success storage 대상/경로 없음"
            return None
        return (f'(KfcGen.storagePutSnbtChanged(server, {jstr(sid)}, {jstr(path)}, '
                f'{jstr(raw)}, {jstr(mode)}) ? 1 : 0)')
    if tgtkind == "entity":
        sel = first_arg(args, "target")
        path = first_arg(args, "targetPath")
        if path is None:
            em.reason = "store success entity 경로 없음"
            return None
        if sel == "@s":
            ent = self_expr
        else:
            ent = single_entity_expr(sel)
            if ent is None:
                em.reason = f"store success entity 대상({sel}) 미지원"
                return None
            if src_expr != "source":
                ent = re.sub(r'\bsource\b', src_expr, ent)
            if self_expr != "executor":
                ent = re.sub(r'\bexecutor\b', self_expr, ent)
        dres = _fresh_var("dres")
        em.side_effects = [
            f"int {dres} = 0;",
            f"{{ net.minecraft.entity.Entity _se = {ent};",
            f"  if (_se != null && KfcGen.entityPutSnbtChanged(_se, {jstr(path)}, {jstr(raw)})) {dres} = 1; }}",
        ]
        return dres
    em.reason = f"store success data modify {tgtkind} 1차 미지원"
    return None


def emit_store(line: str, head: list[dict], tail: list[dict], em: Emitted) -> bool:
    """
    execute [as @s] store result score|storage <대상> [<type> <scale>] run <소스>
    ->  소스 값을 계산해서 대상(스코어/NBT)에 저장.

    지원 소스:
      - data get <entity|storage> <path>     -> NBT 읽기
      - scoreboard players get <holder> <obj> -> 스코어 읽기
      - function ns:path                       -> 함수 반환값 (1차 미지원: return값 캡처 복잡)
    저장 대상:
      - score <holder> <obj>                  -> 스코어에
      - storage <id> <path> <type> <scale>    -> NBT에 (스케일 곱)
    head 에 as @s 외 다른 수정자 있으면 1차 브릿지.
    """
    nodes, args = split_chain(head)
    nn = [n["node"] for n in nodes]

    # store 위치
    if "store" not in nn:
        return False
    si = nn.index("store")
    # store 앞 수정자: as/at + 조건(if/unless ...) 노드는 허용
    #  (조건은 emit_execute 가 conds 로 따로 감싸므로 여기선 무시).
    #  하드 컨텍스트 수정자(positioned/on/rotated/...)가 섞이면 1차 브릿지.
    ALLOWED_PRE = {
        "as", "targets", "at", "if", "unless", "score", "entity", "entities",
        "matches", "range", "target", "targetObjective", "source",
        "sourceObjective", "<", "<=", "=", ">=", ">",
        # if-조건의 인자 노드들(조건 자체는 emit_execute 가 conds 로 따로 감쌈)
        "data", "path", "block", "pos", "loaded", "predicate",
        "items", "slots", "item_predicate", "biome", "dimension",
        "function", "name", "storage",
        # 위치/회전 재바인딩 수정자 - parse_modifiers 가 rebind 문장으로 환원하고
        # emit_execute 가 store 결과를 그 rebind 로 감싼다. 여기선 통과만.
        "positioned", "rotated", "facing", "align", "anchored",
        "rotation", "rot", "axes", "anchor",
    }
    pre = nn[:si]
    bad = [t for t in pre if t not in ALLOWED_PRE]
    if bad:
        em.reason = f"store 앞 수정자({bad}) - 1차 미지원"
        return False

    # store result|success score|storage ...
    store_verb = nn[si + 1]
    if store_verb not in ("result", "success"):
        em.reason = f"store {store_verb} (1차 미지원)"
        return False
    is_success = (store_verb == "success")   # success 는 소스 커맨드의 성공(0/1)을 저장
    dst_kind = nn[si + 2]   # score | storage

    # 저장 대상 파싱
    if dst_kind == "score":
        # targets, objective (store 뒤쪽 것)
        holder, obj = store_score_dst(args)
        h = holder_expr(holder)
        if h is not None:
            dst_writer = lambda valexpr: f'KfcGen.setScore(sb, {h}, {jstr(obj)}, {valexpr});'
        else:
            ent = single_entity_expr(holder)
            if ent is None:
                _ssel = parse_selector(holder)
                if _ssel is not None and _ssel.base in ("a", "p", "r") and _sel_tags_only(_ssel):
                    _tp = java_str_array(_ssel.tags_pos); _tn = java_str_array(_ssel.tags_neg)
                    dst_writer = lambda valexpr: (
                        f'{{ int _stv = {valexpr};'
                        f' for (net.minecraft.server.network.ServerPlayerEntity _se : ctx.allPlayers)'
                        f' if (KfcGen.entityHasTags(_se, {_tp}, {_tn}))'
                        f' KfcGen.setScore(sb, _se.getNameForScoreboard(), {jstr(obj)}, _stv); }}')
                elif _ssel is not None and _ssel.base in ("e", "n") and _sel_tags_only(_ssel):
                    _tp = java_str_array(_ssel.tags_pos); _tn = java_str_array(_ssel.tags_neg)
                    dst_writer = lambda valexpr: (
                        f'{{ int _stv = {valexpr};'
                        f' for (net.minecraft.entity.Entity _se : KfcGen.entitiesSnapshot(ctx))'
                        f' if (KfcGen.entityHasTags(_se, {_tp}, {_tn}))'
                        f' KfcGen.setScore(sb, _se.getNameForScoreboard(), {jstr(obj)}, _stv); }}')
                else:
                    em.reason = f"store score 대상이 셀렉터({holder})"
                    return False
            else:
                dst_writer = lambda valexpr: (
                    f'{{ net.minecraft.entity.Entity _se = {ent};'
                    f' if (_se != null) KfcGen.setScore(sb, _se.getNameForScoreboard(), {jstr(obj)}, {valexpr}); }}')
    elif dst_kind == "storage":
        sid = last_arg(args, "target")
        spath = last_arg(args, "path")
        ntype = nbt_store_type(nn, si)
        scale = first_arg(args, "scale") or "1"
        dst_writer = lambda valexpr: (
            f'KfcGen.storagePutNumber(server, {jstr(sid)}, {jstr(spath)}, '
            f'{scaled(valexpr, scale)}, {jstr(ntype)});')
    elif dst_kind == "entity":
        esel = last_arg(args, "target")
        epath = last_arg(args, "path")
        etype = nbt_store_type(nn, si)
        escale = first_arg(args, "scale") or "1"
        if not epath:
            em.reason = "store entity 경로 없음"
            return False
        if esel == "@s":
            ent = "executor"
        else:
            ent = single_entity_expr(esel)
            if ent is None:
                em.reason = f"store entity 대상 셀렉터({esel}) 미지원"
                return False
        dst_writer = lambda valexpr: (
            f'{{ net.minecraft.entity.Entity _se = {ent};'
            f' if (_se != null) KfcGen.entityPutNumberPath(_se, {jstr(epath)}, {jstr(etype)}, '
            f'{scaled(valexpr, escale)}); }}')
    elif dst_kind == "block":
        bpos = first_arg(args, "targetPos")
        bpath = first_arg(args, "path")
        btype = nbt_store_type(nn, si)
        bscale = first_arg(args, "scale") or "1"
        bpe = cond_pos_expr(bpos) if bpos else None
        if bpe is None or not bpath:
            em.reason = "store block 좌표/경로 미지원"
            return False
        dst_writer = lambda valexpr: (
            f'KfcGen.blockStoreNumber(source.getWorld(), '
            f'net.minecraft.util.math.BlockPos.ofFloored({bpe}), '
            f'{jstr(bpath)}, {scaled(valexpr, bscale)}, {jstr(btype)});')
    elif dst_kind == "bossbar":
        # store result bossbar <id> value|max run <소스>  -> 보스바 값/최대값 설정
        bid = first_arg(args, "id")
        field = nn[si + 4] if len(nn) > si + 4 else "value"
        if not bid or field not in ("value", "max"):
            em.reason = f"store bossbar {field} 미지원"
            return False
        fn = "bossbarSetValue" if field == "value" else "bossbarSetMaxValue"
        dst_writer = lambda valexpr: f'KfcGen.{fn}(source, {jstr(bid)}, {valexpr});'
    else:
        em.reason = f"store {dst_kind} (미지원)"
        return False

    # ── store dst 뒤(run 앞) 수정자 체인 처리 ──
    # /execute 시맨틱: store 뒤의 수정자도 좌→우로 적용되어 run 소스의 실행 컨텍스트를 바꾼다.
    # 기존엔 '순수 on vehicle 체인'만 처리하고 그 외(as/positioned/if 등)는 dst 파싱이 조용히
    # 지나쳤는데, 이는 소스가 @s/위치 의존일 때 원 컨텍스트로 오평가되는 오컴파일이었다
    # (예: swapspeed 의 `as @e[carB,limit=1]`, rkey-tp 의 `positioned $(posx)..`).
    # 순수 on-vehicle 체인은 기존 경로를 유지(출력 바이트 동일)하고, 그 외 조합은 아래
    # 일반 핸들러가 (_src, _self) 를 단계별로 재바인딩한다. 미지원 수정자는 폴백.
    _MID_MODS = {"as", "at", "positioned", "rotated", "facing", "anchored",
                 "align", "in", "on", "if", "unless"}
    mi = next((k for k in range(si + 2, len(nn)) if nn[k] in _MID_MODS), None)
    _rest0 = nn[mi:] if mi is not None else []
    _pure_on = bool(_rest0) and len(_rest0) % 2 == 0 and all(
        _rest0[k] == "on" and _rest0[k + 1] == "vehicle" for k in range(0, len(_rest0), 2))
    if mi is not None and not _pure_on:
        # 인자 노드의 raw 는 arg 엔트리에서 읽는다: _inject_macro_markers 가 MACROVAR 마커를
        # arg raw 에만 주입하고 node range 는 더미(재파싱 문자열) 그대로라, range 를 읽으면
        # 매크로 좌표/셀렉터가 더미 리터럴로 굳는다. 노드 순서↔동명 arg 순서로 짝짓는다.
        from collections import defaultdict as _dd, deque as _dq
        _q = _dd(_dq)
        for _s in head:
            if "arg" in _s:
                _q[_s["arg"]].append((_s.get("value") or {}).get("raw"))
        _nraw = []
        for _s in head:
            if "node" in _s:
                _nm = _s["node"]
                _r = _q[_nm].popleft() if _q[_nm] else None
                _nraw.append(_r if _r is not None else _s.get("range"))
        stmts = []
        cur = "source"; self_e = "executor"; nullable = False
        k = mi
        while k < len(nn):
            t = nn[k]
            if t == "as" and k + 1 < len(nn) and nn[k + 1] == "targets":
                raw = _nraw[k + 1] or ""
                if raw == "@s":
                    k += 2; continue
                ent = single_entity_expr(raw)
                if ent is None:
                    em.reason = f"store 중간 as {str(raw)[:20]} (단일 해소 불가)"; return False
                ev = _fresh_var("stAsE"); sv = _fresh_var("stSrc")
                stmts.append(f'net.minecraft.entity.Entity {ev} = {ent};')
                stmts.append(f'net.minecraft.server.command.ServerCommandSource {sv} = '
                             f'({ev} != null ? {cur}.withEntity({ev}) : null);')
                cur = sv; self_e = ev; nullable = True; k += 2
            elif t == "positioned" and k + 1 < len(nn) and nn[k + 1] == "pos":
                raw = _nraw[k + 1] or ""
                expr = pos_rebind_expr(raw, cur)
                if expr is None:
                    em.reason = f"store 중간 positioned {str(raw)[:20]} 미지원"; return False
                sv = _fresh_var("stSrc")
                if nullable:
                    expr = f'({cur} == null ? null : {expr})'
                stmts.append(f'net.minecraft.server.command.ServerCommandSource {sv} = {expr};')
                cur = sv; k += 2
            elif t == "on" and k + 1 < len(nn) and nn[k + 1] == "vehicle":
                sv = _fresh_var("stSrc"); ev = _fresh_var("stOnE")
                stmts.append(f'net.minecraft.server.command.ServerCommandSource {sv} = '
                             f'({cur} != null ? KfcGen.onVehicle({cur}) : null);')
                stmts.append(f'net.minecraft.entity.Entity {ev} = '
                             f'({sv} != null ? {sv}.getEntity() : null);')
                cur = sv; self_e = ev; nullable = True; k += 2
            else:
                em.reason = f"store 중간 수정자({t}) 미지원"; return False
        src_em2 = Emitted(line=line)
        valexpr = (store_success_value_expr(tail, src_em2, self_expr=self_e, src_expr=cur)
                   if is_success else
                   source_value_expr(tail, src_em2, self_expr=self_e, src_expr=cur))
        if valexpr is None:
            em.reason = src_em2.reason
            return False
        em.java.append("{")
        for s in stmts:
            em.java.append("  " + s)
        gi = "  "
        if nullable:
            em.java.append(f"  if ({cur} != null) {{")
            gi = "    "
        for s in src_em2.side_effects:
            em.java.append(gi + s)
        if src_em2.macro_guard:
            _gc = " && ".join(f"!KfcGen.macroEmpty({g})" for g in src_em2.macro_guard)
            em.java.append(gi + f"if ({_gc}) {dst_writer(valexpr)}")
        else:
            em.java.append(gi + dst_writer(valexpr))
        if nullable:
            em.java.append("  }")
        em.java.append("}")
        em.kind = "native"
        return True

    # store dst 뒤 `on vehicle (on vehicle)*` 재바인딩 체인 감지.
    #  store 는 그 재바인딩 컨텍스트에서 평가된 소스 값을 캡처한다(/execute 시맨틱).
    on_chain = 0
    if "on" in nn[si:]:
        oi = nn.index("on", si)
        rest = nn[oi:]
        if len(rest) % 2 == 0 and all(
                rest[k] == "on" and rest[k + 1] == "vehicle" for k in range(0, len(rest), 2)):
            on_chain = len(rest) // 2
        else:
            em.reason = "store on <비-vehicle 체인> 미지원"
            return False

    # 소스 커맨드 (run 뒤) -> 값 식
    src_em = Emitted(line=line)
    if on_chain:
        # 재바인딩된 소스(_stSrc)/실행자(_stSelf) 기준으로 소스 평가
        valexpr = (store_success_value_expr(tail, src_em, self_expr="_stSelf", src_expr="_stSrc")
                   if is_success else
                   source_value_expr(tail, src_em, self_expr="_stSelf", src_expr="_stSrc"))
        if valexpr is None:
            em.reason = src_em.reason
            return False
        rebound = "source"
        for _ in range(on_chain):
            rebound = f"KfcGen.onVehicle({rebound})"
        em.java.append(f"{{ net.minecraft.server.command.ServerCommandSource _stSrc = {rebound};")
        em.java.append("  if (_stSrc != null) {")
        em.java.append("    net.minecraft.entity.Entity _stSelf = _stSrc.getEntity();")
        for s in src_em.side_effects:
            em.java.append("    " + s)
        if src_em.macro_guard:
            _gc = " && ".join(f"!KfcGen.macroEmpty({g})" for g in src_em.macro_guard)
            em.java.append("    " + f"if ({_gc}) {dst_writer(valexpr)}")
        else:
            em.java.append("    " + dst_writer(valexpr))
        em.java.append("  } }")
        em.kind = "native"
        return True

    valexpr = (store_success_value_expr(tail, src_em) if is_success
               else source_value_expr(tail, src_em))
    if valexpr is None:
        em.reason = src_em.reason
        return False

    # 소스가 부수효과(scoreboard operation 등)를 동반하면 값 쓰기 전에 먼저 실행
    if src_em.side_effects:
        em.java.extend(src_em.side_effects)
    if src_em.macro_guard:
        _gc = " && ".join(f"!KfcGen.macroEmpty({g})" for g in src_em.macro_guard)
        em.java.append(f"if ({_gc}) {dst_writer(valexpr)}")
    else:
        em.java.append(dst_writer(valexpr))
    em.kind = "native"   # NBT/함수 호출 동반 -> gated
    return True


def store_score_dst(args: dict):
    """store result score 의 대상 holder/obj.
       as/at @s 가 앞서면 targets 리스트에 그 셀렉터가 먼저 들어오므로 store 대상은 마지막."""
    tlist = args.get("targets", [{}])
    olist = args.get("objective", [{}])
    holder = tlist[-1].get("raw") if tlist else None
    obj = olist[-1].get("raw") if olist else None
    return holder, obj


def nbt_store_type(nn: list[str], si: int) -> str:
    for t in ("byte", "short", "int", "long", "float", "double"):
        if t in nn[si:]:
            return t
    return "int"


def scaled(valexpr: str, scale: str) -> str:
    if "MACROVAR_" in str(scale):
        return f"({valexpr}) * {jdouble(scale)}"
    try:
        if float(scale) == 1.0:
            return valexpr
    except ValueError:
        pass
    return f"({valexpr}) * {scale}"


def source_value_expr(tail: list[dict], em: Emitted,
                      self_expr: str = "executor", src_expr: str = "source") -> str | None:
    """run 뒤 소스 -> int 값 식. self_expr/src_expr 로 @s(executor)-source 재바인딩 가능
       (store ... on vehicle ... run 처럼 컨텍스트가 바뀐 경우). 반환식과 side_effects 둘 다 치환."""
    r = _source_value_expr_raw(tail, em)
    if r is None:
        return None
    if self_expr != "executor" or src_expr != "source":
        def sub(s):
            s = re.sub(r'\bexecutor\b', self_expr, s)
            s = re.sub(r'\bsource\b', src_expr, s)
            return s
        r = sub(r)
        if em.side_effects:
            em.side_effects = [sub(s) for s in em.side_effects]
    return r


def _source_value_expr_raw(tail: list[dict], em: Emitted) -> str | None:
    """run 뒤 소스 커맨드 -> int 값을 내는 자바 식."""
    if not tail:
        em.reason = "store 소스 없음"
        return None
    nodes, args = split_chain(tail)
    nn = [n["node"] for n in nodes]
    cmd = nn[0]

    if cmd == "data" and "get" in nn:
        tgtkind = nn[2]  # entity | storage
        path = first_arg(args, "path")
        scale_arg = first_arg(args, "scale") or "1"
        # data get 의 결과 = floor(value * scale) (바닐라 MathHelper.floor; (int) 절삭 아님 - 음수에서 차이).
        # getAtPath(NbtPath) 라 리스트 인덱스(Rotation[0])도 지원.
        def _scaled_get(g):
            return f"KfcGen.floorScale({g}, {scale_arg})"
        if tgtkind == "entity":
            sel = first_arg(args, "target")
            if sel == "@s":
                return _scaled_get(f'KfcGen.entityGetDouble(executor, {jstr(path)})')
            ent = single_entity_expr(sel)
            if ent is None:
                em.reason = f"store←data get entity 셀렉터({sel}) 미지원"
                return None
            return _scaled_get(f'KfcGen.entityGetDouble({ent}, {jstr(path)})')
        elif tgtkind == "storage":
            sid = first_arg(args, "target")
            return _scaled_get(f'KfcGen.storageGetDouble(server, {jstr(sid)}, {jstr(path)})')
    elif cmd == "data" and ("modify" in nn) and ("string" in nn):
        # store result|success ... run data modify storage <t> <p> set string <src> [s [e]]
        # 바닐라 result = NbtPath.put 변경 종단 수(비문자열/범위 밖/값 불변 = 실패 → 0).
        si2 = nn.index("string")
        skind = nn[si2 + 1] if si2 + 1 < len(nn) else None
        if nn[2] != "storage":
            em.reason = "store←data set string 비-storage 대상 미지원"
            return None
        read = _data_source_read_expr(skind, args)
        tid = first_arg(args, "target"); tpath = first_arg(args, "targetPath")
        if read is None or not tid or not tpath:
            em.reason = "store←data set string 소스/대상 미지원"
            return None
        def _sidx2(v, dflt):
            if v is None:
                return dflt
            if re.fullmatch(r'MACROVAR_\d+', str(v)):
                return f'Integer.parseInt({jstr(v)})'
            try:
                int(v); return str(v)
            except (ValueError, TypeError):
                return None
        s_e = _sidx2(first_arg(args, "start"), "0")
        e_e = _sidx2(first_arg(args, "end"), "Integer.MIN_VALUE")
        if s_e is None or e_e is None:
            em.reason = "store←data set string 인덱스 미지원"
            return None
        vsrc = _fresh_var("vsrc"); dres = _fresh_var("dres")
        em.side_effects = [
            f"int {dres} = 0;",
            f"{{ net.minecraft.nbt.NbtElement {vsrc} = {read};",
            f"  if ({vsrc} != null) {dres} = KfcGen.nbtSetStorageString(server, "
            f"{jstr(tid)}, {jstr(tpath)}, {vsrc}, {s_e}, {e_e}); }}",
        ]
        return dres
    elif cmd == "data" and ("modify" in nn) and ("from" in nn):
        # store result|success ... run data modify <dst> set from <src>
        #  -> 바닐라 executeModify: result = NbtPath.put 이 실제 바꾼 종단 수(값 불변 시 0 → 실패, store 0).
        #     '소스 존재=1' 은 오답 — 발판(점프대)의 표지판 3번줄 감지가 "빈 문자열 리셋 후 복사,
        #     변경 없으면 0" 트릭이라, 항상 존재하는 sign messages[i] 소스에서 1 을 주면
        #     빈 줄인데도 존재 판정되어 플레이어 Yaw 회전이 스킵된다(바닐라와 상이).
        mode = next((m for m in ("set", "append", "prepend", "merge") if m in nn), "set")
        if mode != "set":
            em.reason = f"store←data modify {mode} from 미지원"
            return None
        fi = nn.index("from")
        skind = nn[fi + 1] if fi + 1 < len(nn) else None
        tgtkind = nn[2]
        vsrc = _fresh_var("vsrc"); dres = _fresh_var("dres")
        read = _data_source_read_expr(skind, args)
        wchg = _data_target_write_changed(tgtkind, args, vsrc)
        if read is None or wchg is None:
            em.reason = "store←data modify from 소스/타겟 미지원(변경 판정)"
            return None
        em.side_effects = [
            f"int {dres} = 0;",
            f"{{ net.minecraft.nbt.NbtElement {vsrc} = {read};",
            f"  if ({vsrc} != null) {dres} = {wchg}; }}",
        ]
        return dres
    elif cmd == "scoreboard" and "get" in nn:
        holder = first_arg(args, "target")
        obj = first_arg(args, "objective")
        h = holder_expr(holder)
        if h is not None:
            return f'KfcGen.getScore(sb, {h}, {jstr(obj)})'
        # 단일 셀렉터 홀더(@n/@p/@e[limit=1]) -> 그 엔티티의 스코어보드 이름으로 조회
        ent = single_entity_expr(holder)
        if ent is None:
            em.reason = f"store←scoreboard get 셀렉터({holder})"
            return None
        return f'KfcGen.getScoreOfEntity(sb, {ent}, {jstr(obj)})'
    elif cmd == "scoreboard" and "players" in nn and any(
            op in nn for op in ("operation", "add", "remove", "set")):
        # store result storage ... run scoreboard players operation <대상> ...
        #  -> operation 을 실행(부수효과)한 뒤, 그 대상의 최종 점수를 읽어 저장.
        sub = next(op for op in ("operation", "add", "remove", "set") if op in nn)
        dst_holder = first_arg(args, "targets")
        dst_obj = first_arg(args, "objective") or first_arg(args, "targetObjective")
        dh = holder_expr(dst_holder)
        if dh is None:
            em.reason = f"store←scoreboard {sub} 대상 셀렉터({dst_holder}) 미지원"
            return None
        # 소스 명령을 먼저 실행
        inner = Emitted(line="")
        if not emit_scoreboard(sub, args, inner):
            em.reason = inner.reason or f"store←scoreboard {sub} 실행 미지원"
            return None
        em.side_effects = list(inner.java)  # 값 읽기 전에 실행할 문장들
        return f'KfcGen.getScore(sb, {dh}, {jstr(dst_obj)})'
    elif cmd == "function":
        fid_called = first_arg(args, "name")
        if not fid_called:
            em.reason = "store←function 이름 없음"
            return None
        if ":" not in fid_called:
            fid_called = "minecraft:" + fid_called
        is_macro = fid_called in MACRO_FNS
        margs = "null"
        if is_macro:
            _mres = _macro_args_expr(args, nn)
            if _mres is None:
                em.reason = f"store←function(매크로 {fid_called[:20]}) with 소스 미해소"
                return None
            margs, _mguards = _mres
            em.macro_guard = list(_mguards)   # 빈 매크로변수면 store 문장 전체를 스킵(호출측이 감쌈)
        # 함수의 executeReturn 결과(마지막 return 값)를 저장. 매크로면 macroArgs 동반.
        if ALL_FIDS and fid_called not in ALL_FIDS:
            _ns, _p = fid_called.split(":", 1)
            idexpr = f'net.minecraft.util.Identifier.of({jstr(_ns)}, {jstr(_p)})'
            return (f'KfcGen.instantExecuteFunctionReturn(source, {idexpr}, {margs})'
                    if is_macro else
                    f'KfcGen.instantExecuteFunctionReturn(source, {idexpr})')
        return (f'{fqcn(fid_called)}.executeReturn(source, {margs})'
                if is_macro else f'{fqcn(fid_called)}.executeReturn(source)')
    elif cmd == "random" and "value" in nn:
        rng = first_arg(args, "range")
        lo, hi = parse_range(rng) if rng else (None, None)
        if lo is None or hi is None:
            em.reason = f"store←random value 범위({rng}) 미지원"
            return None
        def _rint(v):
            if re.fullmatch(r'MACROVAR_\d+', str(v)):
                # 매크로 끝점: 환원 후 런타임 파싱(비수치면 numeric-wrap 이 줄 스킵 — 바닐라 동등)
                return f'Integer.parseInt({jstr(v)})'
            try:
                int(v); return str(v)
            except (ValueError, TypeError):
                return None
        lo_e, hi_e = _rint(lo), _rint(hi)
        if lo_e is None or hi_e is None:
            em.reason = f"store←random value 정수범위 아님({rng})"
            return None
        # 바닐라 RandomCommand: world.getRandom() + MathHelper.nextBetween(양끝 포함)
        return f'ctx.world.getRandom().nextBetween({lo_e}, {hi_e})'
    elif cmd == "forceload" and ("add" in nn or "remove" in nn):
        # store ← forceload add|remove <from> [<to>]: 결과 = 상태가 실제 바뀐 청크 수(0=실패→0).
        add = "true" if "add" in nn else "false"
        fr = first_arg(args, "from")
        to = first_arg(args, "to") or fr
        fe = _col_expr(fr); te = _col_expr(to)
        if fe is None or te is None:
            em.reason = f"store←forceload 좌표({fr}) 미지원"
            return None
        return (f'KfcGen.forceloadChange(source, {fe[0]}, {fe[1]}, '
                f'{te[0]}, {te[1]}, {add})')
    elif cmd == "attribute" and "value" in nn and "get" in nn:
        tsel = first_arg(args, "target")
        attr = first_arg(args, "attribute")
        mid = first_arg(args, "id")
        if not attr or not mid:
            em.reason = "store←attribute 인자 부족"
            return None
        if tsel == "@s":
            ent = "executor"
        else:
            ent = single_entity_expr(tsel) if tsel else None
            if ent is None:
                em.reason = f"store←attribute 대상({tsel}) 미지원"
                return None
        # store result score 는 int - 바닐라 attribute get 의 result 는 value (스케일 미지정=1)
        return f'(int) KfcGen.attrModifierValue({ent}, {jstr(attr)}, {jstr(mid)})'
    elif cmd == "clear":
        tgt = first_arg(args, "targets") or "@s"
        item = first_arg(args, "item")
        maxc = first_arg(args, "maxCount")
        parsed = parse_clear_item(item) if item is not None else ("*", None)
        if parsed is None:
            em.reason = f"store←clear 술어({str(item)[:20]}) 미지원"
            return None
        iid, cdata = parsed
        mc = "-1" if maxc is None else jint(maxc)
        if cdata == "__RUNTIME_PRED__":
            iid_j = jstr(iid)
            if tgt == "@s":
                return f'KfcGen.clearItemsPred(source, executor, {iid_j}, {mc})'
            ent = single_entity_expr(tgt)
            if ent is None:
                em.reason = f"store←clear 대상({tgt[:20]}) 미지원"
                return None
            return f'KfcGen.clearItemsPred(source, {ent}, {iid_j}, {mc})'
        cd = "null" if cdata is None else jstr(cdata)
        iid_j = "null" if iid == "*" else jstr(iid)
        if tgt == "@s":
            return f'KfcGen.clearItems(executor, {iid_j}, {cd}, {mc})'
        ent = single_entity_expr(tgt)
        if ent is None:
            em.reason = f"store←clear 대상({tgt[:20]}) 미지원"
            return None
        return f'KfcGen.clearItems({ent}, {iid_j}, {cd}, {mc})'
    elif cmd == "execute":
        # store result ... run execute if entity <sel>  → 매치 엔티티 수(바닐라 result=count).
        # 단순형(단일 if entity, 다중/단일셀렉터/limit 아님)만 네이티브; 그 외는 브릿지.
        enn = nn[1:]
        if enn[:2] == ["if", "entity"] and (enn.count("if") + enn.count("unless")) == 1:
            sel_raw = first_arg(args, "entities")
            sel = parse_selector(sel_raw) if sel_raw else None
            if sel is None or sel.base in ("n", "p", "r", "s") or sel.limit:
                em.reason = f"store←execute if entity 셀렉터({str(sel_raw)[:25]}) 미지원"
                return None
            lo = entity_loop_open(sel, "_ece")
            if lo is None:
                em.reason = f"store←execute if entity({str(sel_raw)[:25]}) 해소 불가"
                return None
            cnt = _fresh_var("secnt")
            em.side_effects = [f"int {cnt} = 0;"] + lo + [f"    {cnt}++;", "}"]
            return cnt
        em.reason = "store 소스 execute 미지원"
        return None
    em.reason = f"store 소스 {cmd} 미지원"
    return None


def _particle_viewers_expr(raw: str) -> str | None:
    """particle viewers 셀렉터 -> List<ServerPlayerEntity> 식. @a[tag,distance] 등 지원.
       @a(전체)면 위치/태그 필터를 적용한 리스트. 미지원 셀렉터면 None(전역 표시로 폴백)."""
    sel = parse_selector(raw)
    if sel is None:
        return None
    if sel.base not in ("a", "p", "s", "r"):
        return None
    if _sel_has_extra(sel) or sel.scores or sel.predicates:
        return None
    conds = []
    for t in sel.tags_pos:
        conds.append(f'_pv.getCommandTags().contains({jstr(t)})')
    for t in sel.tags_neg:
        conds.append(f'!_pv.getCommandTags().contains({jstr(t)})')
    if sel.distance is not None:
        lo, hi = sel.distance
        conds.append(f'KfcGen.posInRange(source.getPosition(), _pv.getPos(), '
                     f'{_dist_arg(lo)}, {_dist_arg(hi)})')
    if sel.gamemode is not None:
        ge = f'KfcGen.gamemodeIs(_pv, {jstr(sel.gamemode)})'
        conds.append(f'!({ge})' if sel.gamemode_neg else ge)
    body = " && ".join(conds) if conds else "true"
    return f'KfcGen.filterPlayers(ctx, _pv -> ({body}))'


def first_arg(args: dict, name: str):
    v = args.get(name)
    return v[0].get("raw") if v else None


def last_arg(args: dict, name: str):
    """같은 이름 인자가 여러 번이면 마지막 값. store dst 의 target/id 는 조건(if/unless score)의
       target 뒤에 오므로(head 내 마지막), 전역 first_arg 대신 이걸 써야 정확하다."""
    v = args.get(name)
    return v[-1].get("raw") if v else None


def single_entity_expr(raw: str) -> str | None:
    """셀렉터 raw -> '단일 엔티티'를 내는 자바 식(Entity 또는 null). 지원 못하면 None.
       @s[tag,type] -> executor (태그/타입 가드를 삼항으로)
       @n / @e[limit=1] -> KfcGen.nearestEntity(...)
       @p[...] -> KfcGen.nearestPlayer(...)
       <UUID 리터럴> -> KfcGen.entityByUuid(...)
    """
    sel = parse_selector(raw)
    if sel is None:
        # UUID 리터럴 대상: 바닐라 EntitySelector 는 uuid 지정 시 서버의 '모든 월드'에서
        # getEntity(uuid) 로 찾는다(EntitySelector.getEntities 바이트코드 확인).
        # 컷씬(stevemo:frame, chagogo:chago_frame1/*)이 프레임마다 `data merge entity <uuid>`
        # / `as <uuid>` 를 쓰는데, 이 미지원이 틱 도달 함수 폴백 61개 중 55개의 원인이었다.
        if raw and re.fullmatch(
                r'[0-9a-fA-F]{1,8}-[0-9a-fA-F]{1,4}-[0-9a-fA-F]{1,4}'
                r'-[0-9a-fA-F]{1,4}-[0-9a-fA-F]{1,12}', raw):
            return f'KfcGen.entityByUuid(ctx, {jstr(raw)})'
        return None
    if _sel_has_extra(sel):
        return None  # nbt/advancements/team/level/name: 최근접 탐색 미수용 -> 폴백(정확성)

    if sel.base == "s":
        # 실행자 자신 + 태그/타입 가드. 조건 불만족이면 null 이 되도록 삼항.
        guards = []
        pg = predicate_guards(sel.predicates, "executor", player=False) if sel.predicates else []
        if pg is None:
            return None
        guards += pg
        for t in sel.tags_pos:
            guards.append(f'executor.getCommandTags().contains({jstr(t)})')
        for t in sel.tags_neg:
            guards.append(f'!executor.getCommandTags().contains({jstr(t)})')
        # scores={obj=N..M} 가드 - 이전엔 @s 분기에서 통째로 누락되어
        # `rotated as @s[...,scores=...]` / `facing entity @s[...]` 등에서 점수 필터가
        # 사라지고 모든 분기가 무조건 실행됐다(예: loop-main=0/1 동시 실행 -> rightspeed 상쇄).
        # 다른 셀렉터 분기(@p/@e)와 동일하게 scoreMatches 가드를 적용해 바닐라 무결성 회복.
        guards += _score_conds(sel, 'executor')
        # gamemode= 가드 (@s 가 플레이어일 때) - 마찬가지로 누락되어 있던 제약 복원.
        if sel.gamemode is not None:
            ge = f'KfcGen.gamemodeIs(executor, {jstr(sel.gamemode)})'
            guards.append(f'!({ge})' if sel.gamemode_neg else ge)
        if sel.type_id and not sel.type_is_tag:
            jt = entity_type_java(sel.type_id)
            if not jt:
                # 모드/커스텀 단일 타입 -> 런타임 레지스트리 비교
                tid = sel.type_id if ":" in sel.type_id else "minecraft:" + sel.type_id
                guards.append(f'KfcGen.entityTypeIs(executor, {jstr(tid)})')
            else:
                guards.append(f'executor.getType() == {jt}')
        elif sel.type_is_tag:
            jts = resolve_entity_types(sel)
            if not jts:
                # 컴파일타임 타입태그 부재 -> 런타임 EntityType.isIn(#tag)
                guards.append(f'KfcGen.entityInTypeTag(executor, {jstr(sel.type_id)})')
            else:
                guards.append("(" + " || ".join(f'executor.getType() == {t}' for t in jts) + ")")
        rc2 = rotation_conds(sel, "executor")
        if rc2 is None:
            return None
        guards.extend(rc2)
        _vc = _volume_cond(sel, "executor")
        if _vc: guards.append(_vc)
        if sel.distance is not None:
            # @s[distance=..N] = 실행자가 '현재 실행 위치'에서 distance 이내인지 검사(무의미 아님).
            #   positioned as @s[distance=..1] 등에서 나온다. source 위치 기준 inRange 가드로 네이티브화.
            _dl, _dh = sel.distance
            guards.append(f'KfcGen.inRange(source.getPosition(), executor, {_dist_arg(_dl)}, {_dist_arg(_dh)})')
        if not guards:
            return "executor"
        return f'(({" && ".join(guards)}) ? executor : null)'

    # 단일 엔티티/플레이어 탐색
    tp = jarr_tags(sel.tags_pos)
    tn = jarr_tags(sel.tags_neg)
    lo, hi = sel.distance if sel.distance else (None, None)
    dmin = _dist_arg(lo)
    dmax = _dist_arg(hi)

    if sel.base in ("p", "r") or (sel.base == "a" and sel.limit == 1):
        # @a[limit=1] 은 sort=arbitrary(위치 무관, 첫 매치) — @p(nearest)와 다르다.
        # 순위 판정처럼 '고정 기준점' 이 필요한 알고리즘에서 nearest 로 바꾸면 기준이 흔들린다.
        _is_arbitrary = (sel.base == "a")
        if sel.gamemode is not None or sel.scores or sel.predicates or sel.x_rotation or sel.y_rotation or sel.volume is not None:
            pc = _tag_conds(sel, '_pe')
            if sel.gamemode is not None:
                ge = f'KfcGen.gamemodeIs(_pe, {jstr(sel.gamemode)})'
                pc.append(f'!({ge})' if sel.gamemode_neg else ge)
            pc += _score_conds(sel, '_pe')
            rcp = rotation_conds(sel, "_pe")
            if rcp is None:
                return None
            pc += rcp
            if sel.predicates:
                pgp = predicate_guards(sel.predicates, "_pe", player=True)
                if pgp is None:
                    return None
                pc += pgp
            if sel.distance is not None:
                dl5, dh5 = sel.distance
                pc.append(f'KfcGen.posInRange(source.getPosition(), _pe.getPos(), '
                          f'{_dist_arg(dl5)}, {_dist_arg(dh5)})')
            _vc = _volume_cond(sel, "_pe")
            if _vc: pc.append(_vc)
            body5 = " && ".join(pc) if pc else "true"
            if _is_arbitrary:
                return f'KfcGen.firstPlayerWhere(ctx, _pe -> ({body5}))'
            if sel.base == "r":
                return f'KfcGen.randomPlayerWhere(ctx, _pe -> ({body5}))'
            return f'KfcGen.nearestPlayerWhere(ctx, source.getPosition(), _pe -> ({body5}))'
        if _is_arbitrary:
            return f'KfcGen.firstPlayer(ctx, {tp}, {tn}, {dmin}, {dmax}, source.getPosition())'
        if sel.base == "r":
            # @r = 균등 랜덤(바닐라). nearest 해소 divergence 교정.
            return f'KfcGen.randomPlayer(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax})'
        _fnp = "nearestPlayer" if _want_nearest(sel) else "firstPlayer"
        return f'KfcGen.{_fnp}(ctx, source.getPosition(), {tp}, {tn}, {dmin}, {dmax})'
    if sel.base in ("n", "e"):
        types = resolve_entity_types(sel)
        if sel.type_id and not sel.type_is_tag:
            jt = entity_type_java(sel.type_id)
            types = [jt] if jt else None
        # 단일후보(@n/@e[limit=1])에서만 nearest* 사용. @e 무제한은 루프 경로.
        if not (sel.base == "n" or sel.limit == 1):
            if not types or sel.type_neg:
                return None
        # 타입 미해소(커스텀 타입태그/모드 타입/type=!X) -> 전 엔티티 순회 + 런타임 가드.
        if not types or sel.type_neg:
            extra = []
            if sel.type_is_tag:
                e = f'KfcGen.entityInTypeTag(_ee, {jstr(sel.type_id)})'
                extra.append(f'!({e})' if sel.type_neg else e)
            elif sel.type_id:
                tid = sel.type_id if ":" in sel.type_id else "minecraft:" + sel.type_id
                e = f'KfcGen.entityTypeIs(_ee, {jstr(tid)})'
                extra.append(f'!({e})' if sel.type_neg else e)
            extra += _score_conds(sel, '_ee')
            rce = rotation_conds(sel, "_ee")
            if rce is None:
                return None
            extra += rce
            if sel.predicates:
                pge = predicate_guards(sel.predicates, "_ee", player=False)
                if pge is None:
                    return None
                extra += pge
            _vc = _volume_cond(sel, "_ee")
            if _vc: extra.append(_vc)
            body6 = " && ".join(extra) if extra else "true"
            _fnw = "nearestEntityAnyTypeWhere" if _want_nearest(sel) else "firstEntityAnyTypeWhere"
            return (f'KfcGen.{_fnw}(ctx, source.getPosition(), {tp}, {tn}, '
                    f'{dmin}, {dmax}, _ee -> ({body6}))')
        arr = "new net.minecraft.entity.EntityType<?>[]{" + ", ".join(types) + "}"
        if sel.gamemode is not None:
            return None  # @e/@n 의 gamemode 는 미지원 - 무시 대신 거부(정확성)
        ec = []
        ec += _score_conds(sel, '_ee')
        rce = rotation_conds(sel, "_ee")
        if rce is None:
            return None
        ec += rce
        if sel.predicates:
            pge = predicate_guards(sel.predicates, "_ee", player=False)
            if pge is None:
                return None
            ec += pge
        _vc = _volume_cond(sel, "_ee")
        if _vc: ec.append(_vc)
        if ec:
            _fnw2 = "nearestEntityWhere" if _want_nearest(sel) else "firstEntityWhere"
            return (f'KfcGen.{_fnw2}(ctx, source.getPosition(), {arr}, {tp}, {tn}, '
                    f'{dmin}, {dmax}, _ee -> ({" && ".join(ec)}))')
        _fnt2 = "nearestEntity" if _want_nearest(sel) else "firstEntity"
        return f'KfcGen.{_fnt2}(ctx, source.getPosition(), {arr}, {tp}, {tn}, {dmin}, {dmax})'
    return None



def jarr_tags(tags: list) -> str:
    """태그 리스트 -> 자바 String[] 리터럴."""
    if not tags:
        return "KfcGen.NO_TAGS"   # 공유 빈 배열 — 호출당 new String[0] 할당 제거
    return "new String[]{" + ", ".join(jstr(t) for t in tags) + "}"




# ───────────────────────── 식별자 -> 자바 FQCN ─────────────────────────
def fqcn(fid: str) -> str:
    """'kartmobil:move/movetp/tp' -> '<GROUP>.kartmobil.move.movetp.Tp'"""
    ns, path = fid.split(":", 1)
    segs = path.split("/")
    pkg = ".".join([sanitize(ns)] + [sanitize(s) for s in segs[:-1]])
    cls = pascal(segs[-1])
    base = GROUP
    return f'{base}.{pkg}.{cls}' if pkg else f'{base}.{cls}'

JAVA_KEYWORDS = {
    "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", "double", "else", "enum",
    "extends", "final", "finally", "float", "for", "goto", "if", "implements",
    "import", "instanceof", "int", "interface", "long", "native", "new",
    "package", "private", "protected", "public", "return", "short", "static",
    "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while", "true", "false", "null",
    "var", "record", "yield", "sealed", "permits", "non-sealed",
    "_",   # Java 9+ 예약어: 단독 밑줄은 식별자/패키지명으로 불가
}

def sanitize(s):
    s = re.sub(r'[^A-Za-z0-9_]', '_', s) or "_"
    if s[0].isdigit():
        s = "_" + s
    if s in JAVA_KEYWORDS:
        s = s + "_"
    return s

def pascal(s):
    parts = re.split(r'[^A-Za-z0-9]+', s)
    n = "".join(p[:1].upper() + p[1:] for p in parts if p) or "Fn"
    # 모든 생성 클래스명에 'Kfc' 접두사 — MC/자바 네이티브 클래스명(Entity/Item/World 등)과의
    # 충돌 방지. 호출부(fqcn)·정의부(function_to_class) 모두 이 함수를 거치므로 일관 적용.
    return "Kfc" + n


# ───────────────────────── 한 줄 처리 ─────────────────────────
# 마크 1.21.5 vanilla 루트 명령 집합. 이 밖의 ok:true command 는 무효 명령으로 간주
# (mcfunction 로드 시 함수 전체 거부). stopsound 처럼 실제 유효한 것을 빠뜨리면
# 멀쩡한 함수를 죽이므로, 누락 없이 정의한다.
# [!] ParseDumper 가 실제 dispatcher 루트 목록(commands.json)을 덤프하면 그걸 우선 사용.
VANILLA_COMMANDS = {
    "advancement", "attribute", "ban", "ban-ip", "banlist", "bossbar", "clear", "clone",
    "damage", "data", "datapack", "debug", "defaultgamemode", "deop", "difficulty",
    "effect", "enchant", "execute", "experience", "fill", "fillbiome", "forceload",
    "function", "gamemode", "gamerule", "give", "help", "item", "jfr", "kick", "kill",
    "list", "locate", "loot", "me", "msg", "op", "pardon", "pardon-ip", "particle",
    "perf", "place", "playsound", "publish", "random", "recipe", "reload", "return",
    "ride", "rotate", "save-all", "save-off", "save-on", "say", "schedule", "scoreboard",
    "seed", "setblock", "setidletimeout", "setworldspawn", "spawnpoint", "spectate",
    "spreadplayers", "stop", "stopsound", "summon", "tag", "team", "teammsg", "teleport",
    "tell", "tellraw", "tp", "tick", "time", "title", "transfer", "trigger", "w",
    "weather", "whitelist", "worldborder", "xp", "fillbiome", "dialog", "waypoint",
    "rotate", "test",  # 1.21.x 추가분 포함
}

# 외부에서 실제 dispatcher 루트 목록을 주입하면 그것으로 교체 (정확도 우선)
_dispatcher_roots: set | None = None

def is_valid_root_command(command) -> bool:
    """command 가 유효한 마크 루트 명령인가. None/빈 문자열/집합 밖이면 무효."""
    if not command:
        return False
    valid = _dispatcher_roots if _dispatcher_roots is not None else VANILLA_COMMANDS
    return command in valid


def emit_line(obj: dict) -> Emitted:
    global _on_depth
    _on_depth = 0
    em = Emitted(line=obj["line"])

    if obj.get("macro"):
        return emit_macro(obj, em)
    if not obj.get("ok"):
        # 파싱 실패: 미해소(tag/predicate/element)는 브릿지 유지(실제론 정상),
        # 진짜 문법오류(Unknown command/Expected/Incorrect)는 함수 거부.
        err = obj.get("error", "") or "parse failed"
        if is_real_syntax_error(err):
            return em.reject(f"문법 오류로 함수 거부: {err[:60]}")
        return em.bridge(f"파싱 실패(미해소, 브릿지): {err[:50]}")

    command = obj.get("command")
    chain = obj.get("chain", [])

    # ok:true 인데 루트 command 가 무효 -> mcfunction 에서 함수 전체 거부
    if not is_valid_root_command(command):
        return em.reject(f"무효 루트 명령 '{command}' - 함수 거부")

    if command == "execute":
        if emit_execute(em.line, chain, em):
            return em
        return em.bridge(em.reason or "execute 네이티브화 불가")
    else:
        if emit_target(em.line, command, chain, em):
            return em
        return em.bridge(f"{command} 네이티브화 불가(1차)")



def _inject_macro_markers(line, reparsed_line, macro_vars, chain):
    """숫자/문자열 더미 재파싱된 chain 에 MACROVAR_<i> 를 주입.
       원본-재파싱 줄의 공백 토큰열이 1:1 정렬될 때만 안전 적용, 아니면 None."""
    orig = line.lstrip("$").split(" ")
    rep = reparsed_line.split(" ")
    if len(orig) != len(rep):
        return None
    # 더미 위치 -> 마커 토큰
    diff = {}
    for i, (a, b) in enumerate(zip(orig, rep)):
        if a == b:
            continue
        if "$(" not in a:
            return None  # 더미 외 차이 - 정렬 실패
        mk = a
        for name in re.findall(r'\$\(([\w\-]+)\)', a):
            if name not in macro_vars:
                return None
            mk = mk.replace(f"$({name})", f"MACROVAR_{macro_vars.index(name)}")
        diff[i] = mk
    if not diff:
        return None
    # chain 사본의 raw/range 를 rep 토큰 커서 동기화로 갱신
    import copy as _copy
    out = _copy.deepcopy(chain)
    pos = 0
    consumed = set()   # apply 가 실제 마커로 치환한 diff 위치들
    def apply(span_text):
        nonlocal pos
        toks = span_text.split(" ")
        # rep[pos..] 에서 toks 연속 일치 지점 전진 탐색
        p = pos
        while p + len(toks) <= len(rep):
            if rep[p:p + len(toks)] == toks:
                newt = []
                for j, t in enumerate(toks):
                    if (p + j) in diff:
                        newt.append(diff[p + j]); consumed.add(p + j)
                    else:
                        newt.append(t)
                pos = p + len(toks)
                return " ".join(newt)
            p += 1
        return None
    for step in out:
        if "arg" in step:
            raw = step.get("value", {}).get("raw")
            if isinstance(raw, str) and raw:
                nv = apply(raw)
                if nv is None:
                    return None
                step["value"]["raw"] = nv
    # 모든 더미 위치가 '정확히' 마커로 환원됐는지 검증. 하나라도 arg raw 밖(리터럴 노드 자리)에
    # 남거나 apply 오매칭으로 건너뛰면 그 더미("MACROVAR"/0/minecraft:stone/{})가 생성 코드에
    # 리터럴로 박힌다(예: 버그텍스처 아이템). 부분 주입은 절대 native 로 내보내지 않고
    # 브릿지(=바닐라 엔진 실행)로 폴백한다.
    if consumed != set(diff.keys()):
        return None
    return out


def emit_macro(obj: dict, em: Emitted) -> Emitted:
    """매크로 줄 처리.
       ParseDumper 가 $(var)->더미 치환 후 재파싱해 chain 을 줬다면(reparsed:true),
       일반 emit 경로로 변환한 뒤 자바 코드의 더미를 macroArgs 파라미터로 환원한다.
       식별자 전략(MACROVAR_<i>)으로 재파싱된 경우만 안전하게 네이티브화 가능
       (숫자 더미는 변수 위치 구분 불가 -> 인자받는 브릿지로 폴백)."""
    macro_vars = obj.get("macroVars", [])
    reparsed = obj.get("reparsed", False)
    reparsed_line = obj.get("reparsedLine", "")

    if not reparsed or not obj.get("ok"):
        # 재파싱 실패 -> 인자받는 브릿지(문맥 유지). 런타임에 $(var) 치환 후 instantExecuteCommand.
        return macro_bridge(obj, em, macro_vars)

    # 식별자 전략으로 재파싱됐는지 = reparsedLine 에 MACROVAR_ 가 있는지
    use_ident = "MACROVAR_" in reparsed_line
    command = obj.get("command")
    chain = obj.get("chain", [])
    if not use_ident:
        # 숫자 더미 재파싱: 원본↔재파싱 줄을 토큰 단위로 정렬해 더미 위치를 역추적,
        # chain 의 raw 에 MACROVAR_<i> 마커를 주입해 식별자 경로와 합류시킨다.
        chain = _inject_macro_markers(obj.get("line", ""), reparsed_line, macro_vars, chain)
        if chain is None:
            return macro_bridge(obj, em, macro_vars)

    # 일반 emit 경로로 변환 (chain 에 MACROVAR_<i> 가 리터럴로 들어있음)
    inner = Emitted(line=reparsed_line)
    ok = (emit_execute(reparsed_line, chain, inner) if command == "execute"
          else emit_target(reparsed_line, command, chain, inner))
    if not ok:
        return macro_bridge(obj, em, macro_vars)

    # 자바 코드의 MACROVAR_<i> 리터럴을 macroArgs 환원.
    # 더미가 어떻게 들어갔냐에 따라:
    #  - 숫자 자리: 코드에 MACROVAR_0 이 식별자로 들어가면 컴파일 안 됨 -> 그 경우 brige.
    #    하지만 식별자 전략이 파싱 통과했다는 건 그 자리가 식별자/이름 허용 자리라는 뜻.
    #    스코어 값/NBT 숫자 자리는 식별자 전략으로는 애초에 파싱 실패했을 것.
    #  - 따라서 여기 오는 건 이름/문자열 자리 변수. macroArgs.get("var") (String) 로 치환.
    body = []
    used_vars = set()
    for stmt in inner.java:
        s = stmt
        for i, var in enumerate(macro_vars):
            token = f"MACROVAR_{i}"
            # (?!\d): MACROVAR_1 이 MACROVAR_10 에 오매칭되지 않도록 경계 강제
            if re.search(re.escape(token) + r'(?!\d)', s):
                # 문자열 리터럴 안이면 "..." 안의 토큰 -> 문자열 연결로
                s = substitute_macro_token(s, token, var)
                used_vars.add(var)
        body.append(s)

    # 바닐라 매크로 시맨틱: 치환된 매크로 인자가 빈 문자열/비수치라서 명령이 파싱 실패하면
    # 그 '한 줄'만 조용히 스킵된다(함수 전체는 계속). 변환 코드는 Integer/Double/Float.parseInt 로
    # 즉시 파싱하므로 NumberFormatException 으로 서버가 죽는다. 수치 파싱을 포함한 줄은
    # try-catch 로 감싸 파싱 실패 시 그 줄만 건너뛰어 바닐라 동작과 일치시킨다.
    body = _wrap_macro_numeric_parse(body)

    em.java = body
    em.kind = inner.kind  # 순수 자바면 native 유지, inner 가 폴백이면 dispatch 전파
    em.reason = f"매크로 네이티브화(변수: {', '.join(sorted(used_vars))})"
    em.macro_params = sorted(used_vars)
    return em


def _wrap_macro_numeric_parse(stmts: list[str]) -> list[str]:
    """수치 매크로 파싱(Integer/Double/Float.parse*(...macroArgs.get...))을 포함한 자바 문장을
       try-catch 로 감싸, 빈/비수치/null 인자일 때 KfcGen.MACRO_FAIL 을 던진다.
       바닐라(Macro$VariableLine.instantiate, 바이트코드 확인)는 치환 라인 파싱 실패 시
       MacroException 으로 함수 호출 '전체'가 실패한다(어떤 줄도 미실행) — 매크로 함수의
       executeReturn 본문 전체 try 가 이 신호를 잡아 즉시 return 0 한다.
       (Integer.parseInt(null)->NumberFormatException, Double/Float.parseXxx(null)->NullPointerException
        이므로 둘 다 잡아야 double/float 매크로 인자 null 시 서버 크래시를 막을 수 있다.)
       단, 변수 선언문(`Type var = expr;`)은 그 변수가 이후 줄에서 참조될 수 있으므로
       선언을 try 밖으로 빼고 할당만 try 안에서 한다(스코프 보존)."""
    out = []
    for s in stmts:
        st = s.strip()
        has_parse = bool(re.search(r'(Integer\.parseInt|Double\.parseDouble|Float\.parseFloat)\s*\(', st))
        if not (has_parse and "macroArgs.get(" in st) or st.startswith("//"):
            out.append(s)
            continue
        # 블록을 열거나(`... {`) 닫는(`}` 포함) 줄은 통째 try 로 감싸면 중괄호 짝이 깨진다.
        if st.endswith("{") or "}" in st:
            # 특수 케이스: `if (<파싱 포함 조건>) {` — 조건 평가에서 파싱이 터진다.
            mif = re.match(r'^if\s*\((.+)\)\s*\{$', st)
            if mif and "macroArgs.get(" in mif.group(1):
                indent = s[:len(s) - len(s.lstrip())]
                cvar = f"_mcond{_uid()}"
                out.append(f'{indent}boolean {cvar} = false;'
                           f' try {{ {cvar} = ({mif.group(1)}); }} catch (NumberFormatException | NullPointerException _nfe) {{ throw KfcGen.MACRO_FAIL; }}')
                out.append(f'{indent}if ({cvar}) {{')
                continue
            # 한 줄에서 완결된 블록(`{ ...; }` — 여는/닫는 중괄호 수 균형)은 통째로 감싸도
            # 중괄호 짝이 유지된다. 균형이 안 맞으면(블록이 다음 줄로 이어짐) 감싸지 않는다.
            if st.count("{") == st.count("}") and st.endswith("}"):
                indent = s[:len(s) - len(s.lstrip())]
                out.append(f'{indent}try {{ {st} }} catch (NumberFormatException | NullPointerException _nfe) {{ throw KfcGen.MACRO_FAIL; }}')
                continue
            out.append(s)
            continue
        indent = s[:len(s) - len(s.lstrip())]
        # 선언문 패턴: `<type> <name> = <expr>;`  (type 은 패키지/제네릭 포함 가능)
        m = re.match(r'^([\w.$]+(?:<[^=;]*>)?)\s+(\w+)\s*=\s*(.+);\s*$', st)
        if m:
            jtype, name, expr = m.group(1), m.group(2), m.group(3)
            if jtype not in ("return", "if", "for", "while", "else", "try", "catch"):
                default = "0" if jtype in ("int", "long", "short", "byte") else (
                    "0.0" if jtype in ("double", "float") else "null")
                out.append(f'{indent}{jtype} {name} = {default};'
                           f' try {{ {name} = {expr}; }} catch (NumberFormatException | NullPointerException _nfe) {{ throw KfcGen.MACRO_FAIL; }}')
                continue
        # 단순 문장(세미콜론 종료, 중괄호 없음)만 통째로 감싼다.
        if st.endswith(";"):
            out.append(f'{indent}try {{ {st} }} catch (NumberFormatException | NullPointerException _nfe) {{ throw KfcGen.MACRO_FAIL; }}')
        else:
            out.append(s)
    return out


def substitute_macro_token(stmt: str, token: str, var: str) -> str:
    """자바 문장에서 더미 토큰을 macroArgs.get(var) 로 치환.
       문장을 따옴표 기준 세그먼트(리터럴/코드)로 정확히 분할해,
       리터럴 안 토큰은 문자열 연결로, 코드 자리 토큰은 식별자 치환으로 처리한다.
       (이전 정규식 `"[^"]*TOKEN[^"]*"` 은 닫는따옴표~여는따옴표 구간을
        가짜 리터럴로 오인해 이웃 리터럴을 삼키는 버그가 있었음.)"""
    repl = f'macroArgs.get({jstr(var)})'
    # 토큰 경계: MACROVAR_1 이 MACROVAR_10/11… 의 접두사이므로, 뒤에 숫자가 이어지면
    # 다른(더 긴) 토큰이다. 리터럴 split/코드 치환 모두 (?!\d) 경계를 강제하지 않으면
    # 2자리 인덱스 변수(23변수 give 등)에서 `speed+"0"` 류 오치환이 생긴다.
    tok_re = re.compile(re.escape(token) + r'(?!\d)')
    segs = []           # (kind 'S'|'C', text) - S 는 따옴표 포함 리터럴
    cur, in_s, esc = '', False, False
    for ch in stmt:
        if in_s:
            cur += ch
            if esc:
                esc = False
            elif ch == '\\':
                esc = True
            elif ch == '"':
                segs.append(('S', cur)); cur = ''; in_s = False
        else:
            if ch == '"':
                if cur:
                    segs.append(('C', cur)); cur = ''
                cur = '"'; in_s = True
            else:
                cur += ch
    if cur:
        segs.append(('S' if in_s else 'C', cur))
    out = []
    for kind, seg in segs:
        if not tok_re.search(seg):
            out.append(seg); continue
        if kind == 'S':
            inner_s = seg[1:-1]
            parts = tok_re.split(inner_s)
            pieces = []
            for k, p in enumerate(parts):
                if k > 0:
                    pieces.append(repl)
                if p:
                    pieces.append('"' + p + '"')
            out.append(" + ".join(pieces) if pieces else jstr(""))
        else:
            out.append(re.sub(r'\b' + re.escape(token) + r'\b(?!\d)', repl, seg))
    return "".join(out)


def macro_bridge(obj: dict, em: Emitted, macro_vars: list) -> Emitted:
    """네이티브화 못한 매크로 줄 - 함수 단위 폴백 신호(dispatch).
       assemble 이 instantExecuteFunction(source, id, macroArgs) 로 원본 mcfunction 을
       실행하므로 $(var) 런타임 치환은 함수 매니저가 처리한다."""
    em.kind = "dispatch"
    em.reason = "매크로 줄 네이티브화 불가 - 함수 폴백"
    em.macro_params = list(dict.fromkeys(macro_vars))
    em.java = ['// ⛔ 매크로 자바 변환 불가(함수 폴백)']
    return em


def is_real_syntax_error(err: str) -> bool:
    """파싱 실패 사유가 '진짜 문법오류'(->함수 거부)인지 '미해소'(->정상, 브릿지)인지 판정.
       태그/predicate/element 미해소는 런타임엔 정상이므로 브릿지 유지."""
    el = err.lower()
    # 미해소 - 실제론 정상 (False)
    if any(s in el for s in (
        "block tag", "item tag", "entity tag", "fluid tag", "game event tag",
        "predicate", "failed to get element", "failed to parse structure",
        "unknown loot", "advancement", "recipe", "function tag", "biome", "structure",
        "dimension", "enchantment", "attribute modifier")):
        return False
    # 진짜 문법오류 (True)
    if any(s in el for s in (
        "unknown command", "incomplete command", "expected", "incorrect argument",
        "unknown or incomplete", "whitespace", "trailing data", "invalid")):
        return True
    return False


def main():
    path = sys.argv[1] if len(sys.argv) > 1 else \
        str(Path(__file__).parent / "fixtures" / "trees_sample.json")
    # 데이터팩 태그 로드 (엔티티 타입 해소용). 2번째 인자로 데이터팩 루트.
    dp = sys.argv[2] if len(sys.argv) > 2 else "/home/claude/project/kartriderpack"
    if Path(dp).exists():
        load_entity_type_tags(dp)
    data = json.loads(Path(path).read_text(encoding="utf-8"))

    native = gated = bridge = 0
    print("=" * 70)
    for obj in data:
        em = emit_line(obj)
        tag = {"native": "✅NATIVE", "gated": "🔶GATED", "bridge": "🌉BRIDGE"}[em.kind]
        print(f"\n[{tag}] {em.line[:78]}")
        if em.kind == "bridge":
            print(f"        reason: {em.reason}")
        for j in em.java:
            print(f"        {j}")
        native += em.kind == "native"
        gated += em.kind == "gated"
        bridge += em.kind == "bridge"
    print("\n" + "=" * 70)
    tot = len(data)
    print(f"total: {tot} lines | native {native} | gated {gated} | bridge {bridge} "
          f"| native-rate {100*(native+gated)//tot}%")


if __name__ == "__main__":
    main()

# 단순 위임(노드 경로 무관, command 만으로 핸들러 결정) 명령 디스패치 테이블.
# emit_target 의 거대한 if/elif 체인에서 순수 위임 분기를 분리 — 명령 추가/수정 용이.
_DISPATCH_SIMPLE = {
    "effect": emit_effect, "xp": emit_xp, "experience": emit_xp,
    "give": emit_give, "gamerule": emit_gamerule, "worldborder": emit_worldborder,
    "damage": emit_damage, "trigger": emit_trigger, "schedule": emit_schedule,
    "place": emit_place, "spreadplayers": emit_spreadplayers,
    "setworldspawn": emit_setworldspawn, "spawnpoint": emit_spawnpoint,
    "enchant": emit_enchant, "bossbar": emit_bossbar, "data": emit_data,
    "stopsound": emit_stopsound, "playsound": emit_playsound,
    "tp": emit_tp, "teleport": emit_tp,
}
# (무결성 감사 패스: 문맥 전달 source→cur_src 치환 보강 완료)