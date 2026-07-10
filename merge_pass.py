#!/usr/bin/env python3
"""
merge_pass.py — 생성된 자바 클래스 트리의 '말단부터 용량 검사 → 병합' 후처리.

정책(사용자 확정):
  · 병합 방식 = '메서드 흡수': 말단 함수의 execute/executeReturn 를 호출자 클래스 안의
    private static 메서드로 그대로 복사하고, 호출부만 로컬 메서드 호출로 바꾼다.
    (각 메서드 바이트코드 크기는 불변 → 64KB 메서드 한계를 새로 위반하지 않음)
  · 단일 호출자 말단만 병합(2곳 이상에서 호출되면 클래스로 유지 → 중복 폭증 방지).
  · 용량 측정 = 생성된 .java 소스 바이트 크기(근사).
      - 말단 클래스 소스 ≤ MERGE_MAX(기본 40KB) 이면 병합 후보.
      - 흡수 후 호출자 클래스 소스가 CLASS_CAP(기본 ~600KB, 상수풀 65535 보수 근사)을 넘으면 그 건은 병합 안 함.
      - 단일 함수의 executeReturn 메서드 소스가 METHOD_CAP(기본 64KB) 초과 = 규칙 위반 →
        네이티브 클래스로 컴파일 불가 가능 → 원본 mcfunction 호출(브릿지)로 유지.
  · 말단부터(콜그래프 위상 역순) 반복: 흡수로 부모가 새 말단이 되면 다시 검사.

핀(절대 제거 금지) 대상:
  · 함수 태그(tick/load 등)가 직접 가리키는 함수.
  · 외부에서 fid 문자열로 디스패치되는 함수(동적 function 후보 등)는 클래스 호출 엣지로
    잡히면 일반 처리, 아니면 핀으로 받는다.
"""
from __future__ import annotations
import re, hashlib
from pathlib import Path

def _write_if_changed(path, text):
    try:
        if path.exists() and path.read_text(encoding="utf-8") == text:
            return False
    except OSError:
        pass
    path.write_text(text, encoding="utf-8")
    return True

MERGE_MAX = 40 * 1024
METHOD_CAP = 64 * 1024
# ── 오버사이즈 판정 메트릭: '바이트코드 근사' 크기 ──
# javac 의 실제 한계(메서드 Code 속성 65535B)는 '바이트코드 명령'에만 적용된다.
# 문자열 리터럴 내용은 상수풀(개별 상수는 emit 의 KfcGen.cat 분할로 65535B 미만 보장)로
# 빠지고 메서드에는 ldc 참조 몇 바이트만 남으므로, 소스 전체 바이트(c.size)로 판정하면
# "코드는 작고 SNBT 만 거대한" 모델 프레임 함수(pend4/f*, stevemo:frame 등)를
# 과잉 브릿지하게 된다. → 문자열 리터럴 '내부'를 제외한 소스 바이트로 판정한다.
def _strip_str_contents(text: str) -> str:
    out=[]; ins=False; esc=False
    for ch in text:
        if ins:
            if esc: esc=False
            elif ch=='\\': esc=True
            elif ch=='"':
                ins=False; out.append('"')
        else:
            out.append(ch)
            if ch=='"': ins=True
    return ''.join(out)

def _strip_noncode(text: str) -> str:
    """문자열 '내용' + 주석(// 및 /* */)을 제거한 코드 골격.
    [근거] 둘 다 바이트코드 0바이트: 문자열 내용은 상수풀로, 주석은 아예 클래스파일에
    없다. 종전 근사는 주석을 코드로 세어, 62KB 원본 명령을 그대로 담은 `// 주석` 한 줄
    때문에 소환 2줄짜리 모델 함수(lpeng/cutscene 계열 156개)를 과잉 브릿지했다.
    문자열 상태를 먼저 판정하므로 문자열 안의 `//`(URL 등)는 주석으로 오인하지 않는다."""
    out = []; ins = False; esc = False; lc = False; bc = False
    i = 0; n = len(text); prev = ''
    while i < n:
        ch = text[i]
        if lc:
            if ch == '\n':
                lc = False; out.append(ch)
        elif bc:
            if prev == '*' and ch == '/':
                bc = False; prev = ''; i += 1; continue
        elif ins:
            if esc: esc = False
            elif ch == '\\': esc = True
            elif ch == '"':
                ins = False; out.append('"')
        else:
            if ch == '/' and i + 1 < n and text[i+1] == '/':
                lc = True; i += 2; prev = ''; continue
            if ch == '/' and i + 1 < n and text[i+1] == '*':
                bc = True; i += 2; prev = ''; continue
            out.append(ch)
            if ch == '"': ins = True
        prev = ch; i += 1
    return ''.join(out)

def _bc_proxy_size(text: str) -> int:
    """문자열 내용·주석 제외 소스 바이트 — 메서드 바이트코드 상한의 보수적 근사."""
    return len(_strip_noncode(text).encode("utf-8"))

# javac Code 속성 한계(65535B)는 '메서드별'이며, seg 분할이 이미 메서드를 잘게 쪼개므로
# 오버사이즈 판정도 메서드별이어야 한다(클래스 총합 기준은 seg 이후 과잉 브릿지).
# 임계는 근사 오차(수치 리터럴 밀집 코드의 소스↔바이트코드 비율 ~0.9~1.2) 방어를 위해
# 64KB 가 아닌 56KB — 이 마진 덕에 62KB 근사치(lpeng 계열)는 보수적으로 브릿지 유지된다.
METHOD_BC_CAP = 56 * 1024
# 클래스파일 상수풀 엔트리 한계(65535) 가드 — 고유 리터럴 근사가 이를 위협하면 브릿지.
# (거대 문자열 클래스는 버킷 배치가 c.size(문자열 포함) 기준이라 단독 버킷 → 풀 공유 없음.)
CONST_POOL_GUARD = 60000

# ── [디버깅/이분탐색 토글] 구버전 오버사이즈 판정 복원 ──
# True 면 '총 소스 바이트 > 64KB(METHOD_CAP)' 인 클래스도 브릿지한다(구버전 규칙과 합집합).
# [2026-07 해결] 컷씬 롤백/스티브 미표시의 원인은 네이티브화 자체가 아니라 emit 의
# `if score ... as <UUID리터럴>` 체인에서 as 앞 조건이 탈락하던 버그였다
# (_emit_as_loop_recursive 수정). 근본 수정 후 거대 컷씬 함수 네이티브화를 복원한다.
LEGACY_SIZE_BRIDGE = False

_METH_RE = re.compile(r'(?:public|private)\s+static\s+[\w.<>?\[\], ]+?\s\w+\([^)]*\)\s*\{')

def _bc_proxy_max_method(text: str) -> int:
    """클래스 내 '가장 큰 메서드'의 바이트코드 근사 크기."""
    mx = 0
    for m in _METH_RE.finditer(text):
        s = m.end(); d = 1; i = s
        while i < len(text) and d > 0:
            c = text[i]
            if c == '{': d += 1
            elif c == '}': d -= 1
            i += 1
        mx = max(mx, _bc_proxy_size(text[s:i]))
    return mx

_LIT_NUM_RE = re.compile(r'(?<![\w.])-?\d+(?:\.\d+)?(?:[eE]-?\d+)?[fFdDlL]?(?![\w.])')
_LIT_STR_RE = re.compile(r'"(?:[^"\\]|\\.)*"')

def _oversized(text: str) -> bool:
    """브릿지 필요 판정: (a) 최대 메서드 바이트코드 근사 > METHOD_BC_CAP,
       또는 (b) 고유 리터럴(문자열+수치) 근사가 상수풀 한계를 위협.
       LEGACY_SIZE_BRIDGE=True 면 (c) 총 소스 바이트 > METHOD_CAP(구버전 규칙)도 브릿지."""
    if LEGACY_SIZE_BRIDGE and len(text.encode("utf-8")) > METHOD_CAP:
        return True
    if _bc_proxy_max_method(text) > METHOD_BC_CAP:
        return True
    # 상수풀 근사: 문자열 리터럴은 원문에서(내용 무관 1엔트리), 수치 리터럴은 주석·문자열
    # 내용을 제외한 코드 골격에서만 센다(주석 속 행렬 숫자 등 가짜 엔트리 방지).
    pool = len(set(_LIT_STR_RE.findall(text))) + len(set(_LIT_NUM_RE.findall(_strip_noncode(text))))
    return pool > CONST_POOL_GUARD
CLASS_CAP = 600 * 1024

FID_RE = re.compile(r'Auto-generated from datapack function `([^`]+)`')
PKG_RE = re.compile(r'^package\s+([\w.]+);', re.M)
CLS_RE = re.compile(r'public final class (\w+)')
MACRO_SIG_RE = re.compile(r'executeReturn\(ServerCommandSource\s+\w+,\s*Map<String,\s*String>')

class Cls:
    def __init__(self, path: Path, text: str, *, fid=None, package=None, cls_name=None):
        self.path = path
        self.text = text
        # 메타(fid/package/cls)는 convert 의 워커 결과에 이미 있으므로, 주어지면 191k×3 회의
        # 정규식 재추출을 생략한다(메인 직렬 절감). 미지정 호출(디스크 경로)은 기존대로 파싱.
        if fid is not None and package is not None and cls_name is not None:
            self.fid = fid; self.package = package; self.cls = cls_name
        else:
            m = FID_RE.search(text); self.fid = m.group(1) if m else None
            self.package = PKG_RE.search(text).group(1)
            self.cls = CLS_RE.search(text).group(1)
        self.fqcn = f"{self.package}.{self.cls}"
        self.is_macro = bool(MACRO_SIG_RE.search(text))
        self.size = len(text.encode("utf-8"))
        self.callees = set()      # fqcn set this class calls
        self.has_bridge = "instantExecuteFunction" in text

def _scan_callees(text, fqcn_set):
    out = set()
    for m in re.finditer(r'([\w.]+)\.(?:execute|executeReturn)\s*\(', text):
        f = m.group(1)
        if f in fqcn_set and f != "KfcGen":
            out.add(f)
    return out

def load_tree(src_root: Path, group: str):
    classes = {}        # fqcn -> Cls (생성된 함수 클래스만)
    external_refs = set()  # 비생성 파일(ModEntry 등)이 FQCN 으로 부르는 클래스 → 절대 제거 금지
    nongen = []
    for jf in src_root.rglob("*.java"):
        txt = jf.read_text(encoding="utf-8")
        if "Auto-generated from datapack function" in txt:
            c = Cls(jf, txt)
            classes[c.fqcn] = c
        else:
            nongen.append(txt)
    genset = set(classes)
    for c in classes.values():
        c.callees = _scan_callees(c.text, genset)
    # 비생성 파일이 직접 부르는 생성 클래스는 핀(엔트리포인트 tick 등).
    for txt in nongen:
        for m in re.finditer(r'([\w.]+)\.(?:execute|executeReturn)\s*\(', txt):
            if m.group(1) in genset:
                external_refs.add(m.group(1))
    return classes, external_refs

if __name__ == "__main__":
    import sys
    src = Path(sys.argv[1] if len(sys.argv) > 1 else "gen_tree/src/main/java")
    classes, external_refs = load_tree(src, "kartriderpack")
    # 역방향: callers
    callers = {f: set() for f in classes}
    for f, c in classes.items():
        for cal in c.callees:
            callers.setdefault(cal, set()).add(f)
    leaves = [f for f, c in classes.items() if not c.callees]
    single_caller_leaves = [f for f in leaves if len(callers.get(f, set())) == 1]
    macro_leaves = [f for f in single_caller_leaves if classes[f].is_macro]
    print(f"클래스 {len(classes)}, 말단 {len(leaves)}, 단일호출자말단 {len(single_caller_leaves)} (그중 매크로 {len(macro_leaves)})")
    elig = [f for f in single_caller_leaves if classes[f].size <= MERGE_MAX]
    print(f"병합후보(≤40KB 단일호출자말단): {len(elig)}")
    over = [f for f,c in classes.items() if _oversized(c.text)]
    print(f"64KB 초과(브릿지 대상): {len(over)} -> {[classes[f].fid for f in over]}")
    # 콜수 분포
    multi = [f for f in leaves if len(callers.get(f,set()))>=2]
    print(f"공유말단(2곳+ 호출, 유지): {len(multi)}")


# ─────────────────────────── 변환부 ───────────────────────────
def _find_method(text, sig_regex):
    """sig_regex 매치 지점부터 중괄호 균형으로 메서드 한 개(시그니처~닫는 '}')를 추출.
       반환 (start, end, method_text). 못 찾으면 None."""
    m = re.search(sig_regex, text)
    if not m:
        return None
    i = text.index("{", m.start())
    depth = 0; j = i
    in_s = in_c = in_lc = esc = False; q = None; prev = ""
    while j < len(text):
        ch = text[j]
        if in_lc:
            if ch == "\n": in_lc = False
        elif in_c:
            if prev == "*" and ch == "/": in_c = False
        elif in_s:
            if esc: esc = False
            elif ch == "\\": esc = True
            elif ch == q: in_s = False
        else:
            if prev == "/" and ch == "/": in_lc = True
            elif prev == "/" and ch == "*": in_c = True
            elif ch in ('"', "'"): in_s = True; q = ch
            elif ch == "{": depth += 1
            elif ch == "}":
                depth -= 1
                if depth == 0:
                    return (m.start(), j + 1, text[m.start():j + 1])
        prev = ch; j += 1
    return None

def _imports_of(text):
    return [l for l in text.split("\n") if l.startswith("import ")]

def _mangle(fid):
    h = hashlib.md5(fid.encode()).hexdigest()[:8]
    base = re.sub(r'[^A-Za-z0-9]', '_', fid)
    # 해시를 앞에 둬 길이 절단(_[:72]) 시에도 항상 보존 → 고유성 보장.
    return f"_m_{h}_{base}"[:72]

def _bridge_body(fid, is_macro):
    ns, path = fid.split(":", 1)
    idexpr = f'net.minecraft.util.Identifier.of("{ns}", "{path}")'
    if is_macro:
        return (f"        // [용량 한계 64KB 초과] 원본 mcfunction 호출로 유지\n"
                f"        KfcGen.instantExecuteFunction(source, {idexpr}, macroArgs);\n"
                f"        return 0;")
    return (f"        // [용량 한계 64KB 초과] 원본 mcfunction 호출로 유지(반환값 전파)\n"
            f"        return KfcGen.instantExecuteFunctionReturn(source, {idexpr});")

def bridge_oversized(c: Cls) -> str:
    """executeReturn 본문을 instantExecuteFunction 브릿지로 치환한 새 소스 반환."""
    ret = _find_method(c.text, r'public static int executeReturn\(')
    if not ret:
        return c.text
    s, e, mtext = ret
    # 시그니처 줄과 닫는 '}' 만 남기고 본문 교체
    head = mtext[:mtext.index("{") + 1]
    new_method = head + "\n" + _bridge_body(c.fid, c.is_macro) + "\n    }"
    return c.text[:s] + new_method + c.text[e:]

def absorb(parent: Cls, leaf: Cls) -> str:
    """leaf 의 두 메서드를 parent 안 private 메서드로 흡수하고 호출부를 로컬로 바꾼 parent 소스 반환."""
    mang = _mangle(leaf.fid)
    if "executeReturn_seg" in leaf.text:
        return None   # JIT 분할 조각 보유 함수 — 인라이닝 시 조각 누락 위험, 버킷 경로로 처리
    ex = _find_method(leaf.text, r'public static void execute\(')
    rt = _find_method(leaf.text, r'public static int executeReturn\(')
    if not ex or not rt:
        return None
    ex_t = ex[2]; rt_t = rt[2]
    # private 로, 이름 치환
    ex_t = ex_t.replace("public static void execute(", f"private static void {mang}_execute(", 1)
    rt_t = rt_t.replace("public static int executeReturn(", f"private static int {mang}_executeReturn(", 1)
    # execute 래퍼 내부의 executeReturn(...) 위임 호출을 로컬명으로
    ex_t = re.sub(r'\bexecuteReturn\(', f'{mang}_executeReturn(', ex_t)
    # leaf 가 자기 자신을 FQCN 으로 재귀 호출하는 경우(희소) 로컬화
    rt_t = rt_t.replace(f"{leaf.fqcn}.executeReturn(", f"{mang}_executeReturn(")
    rt_t = rt_t.replace(f"{leaf.fqcn}.execute(", f"{mang}_execute(")
    injected = "\n\n    " + ex_t.strip() + "\n\n    " + rt_t.strip() + "\n"

    # parent 호출부 재작성: <leaf.fqcn>.execute( -> mang_execute(,  .executeReturn( -> mang_executeReturn(
    ptext = parent.text
    ptext = ptext.replace(f"{leaf.fqcn}.executeReturn(", f"{mang}_executeReturn(")
    ptext = ptext.replace(f"{leaf.fqcn}.execute(", f"{mang}_execute(")
    # 주석의 fqcn 흔적은 그대로 둬도 무방

    # leaf 의 import 를 parent 에 병합
    p_imports = _imports_of(ptext)
    add = [imp for imp in _imports_of(leaf.text) if imp not in p_imports]
    if add:
        # 마지막 import 줄 뒤에 삽입
        last = p_imports[-1]
        idx = ptext.rindex(last) + len(last)
        ptext = ptext[:idx] + "\n" + "\n".join(add) + ptext[idx:]

    # 클래스 마지막 '}' 직전에 흡수 메서드 삽입
    last_brace = ptext.rstrip().rfind("}")
    ptext = ptext[:last_brace] + injected + ptext[last_brace:]
    return ptext


def run_merge(src_root: Path, group: str, pins: set | None = None,
              merge_max=MERGE_MAX, method_cap=METHOD_CAP, class_cap=CLASS_CAP,
              verbose=True):
    pins = set(pins or set())   # fid 집합
    classes, external_refs = load_tree(src_root, group)
    # 비생성 파일(엔트리포인트 등)이 FQCN 으로 부르는 클래스의 fid 를 핀에 추가.
    for fqcn in external_refs:
        if fqcn in classes:
            pins.add(classes[fqcn].fid)
    stats = {"bridged": 0, "merged": 0, "passes": 0}

    # 1) 오버사이즈 → 브릿지
    for f, c in list(classes.items()):
        if _oversized(c.text):   # 문자열 제외 바이트코드 근사
            new = bridge_oversized(c)
            _write_if_changed(c.path, new)
            c.text = new; c.size = len(new.encode()); c.callees = set()
            c.has_bridge = True
            stats["bridged"] += 1
            if verbose: print(f"  [bridge] {c.fid} ({c.size} bytes > {method_cap})")

    # 2) 말단부터 단일호출자 흡수 (고정점까지 반복)
    while True:
        # 콜그래프 재계산
        for c in classes.values():
            c.callees = _scan_callees(c.text, set(classes))
        callers = {f: set() for f in classes}
        for f, c in classes.items():
            for cal in c.callees:
                if cal in callers:
                    callers[cal].add(f)
        progressed = False
        # 말단(콜리 없음) 중 단일호출자, 핀 아님, ≤merge_max
        for f, c in list(classes.items()):
            if f not in classes: continue
            if c.callees: continue                  # 말단만
            if c.fid in pins: continue
            cs = callers.get(f, set())
            if len(cs) != 1: continue               # 단일 호출자만
            pf = next(iter(cs))
            if pf == f: continue                     # 자기 호출만 → 스킵
            if c.size > merge_max: continue
            parent = classes.get(pf)
            if parent is None: continue
            if parent.size + c.size > class_cap: continue   # 흡수 후 클래스 상한
            # 흡수
            newp = absorb(parent, c)
            if newp is None: continue
            parent.text = newp
            _write_if_changed(parent.path, newp)
            parent.size = len(newp.encode())
            # 말단 파일 제거
            try: c.path.unlink()
            except OSError: pass
            del classes[f]
            stats["merged"] += 1
            progressed = True
            if verbose and stats["merged"] % 25 == 0:
                print(f"  [merge] {stats['merged']} 흡수...")
        stats["passes"] += 1
        if not progressed:
            break
    if verbose:
        print(f"[merge_pass] passes={stats['passes']} bridged={stats['bridged']} "
              f"merged={stats['merged']} 남은클래스={len(classes)}")
    return stats


def validate(src_root: Path, group: str) -> dict:
    """병합 후 트리 정합성 검사: 댕글링 호출 / 중괄호 균형 / 흡수 메서드명 중복."""
    import collections
    files = list(Path(src_root).rglob("*.java"))
    fqcns = set()
    for jf in files:
        t = jf.read_text(encoding="utf-8")
        pm = PKG_RE.search(t); cm = CLS_RE.search(t)
        if pm and cm:
            fqcns.add(pm.group(1) + "." + cm.group(1))
    dangling = 0; unbal = 0; dup = 0
    callre = re.compile(r'([A-Za-z_][\w.]+)\.(?:execute|executeReturn)\s*\(')
    for jf in files:
        t = jf.read_text(encoding="utf-8")
        if "Auto-generated from datapack function" not in t:
            continue  # KfcGen/ModEntry 등은 균형 검사 제외(문자열 내 중괄호 오탐 방지)
        for m in callre.finditer(t):
            f = m.group(1)
            if f.startswith(group + ".") and f not in fqcns and not f.endswith(".KfcGen"):
                dangling += 1
        if t.count("{") != t.count("}"):
            unbal += 1
        names = re.findall(r'private static (?:void|int) (_m_\w+?)_(?:execute|executeReturn)\(', t)
        for n, k in collections.Counter(names).items():
            if k > 2:
                dup += 1
    return {"files": len(files), "dangling": dangling, "unbalanced": unbal, "dup_methods": dup}


# ═══════════════════════════ 버킷화(대규모 팩 클래스수 감축) ═══════════════════════════
# 호출그래프가 희박해 병합으로 못 줄이는 초대형 팩(수만~수십만 함수)에서, 여러 함수를
# 한 '버킷 클래스'의 public static 메서드로 묶어 클래스 수를 수백 배 감축한다(중복 없음).
# 모든 함수 참조(FQCN.execute/executeReturn)는 버킷 메서드 호출로 전역 재작성한다.

def _extract_two_methods(text, mang):
    """클래스 text 에서 execute/executeReturn(+세그먼트 조각들)을 mang 접두 이름으로 추출.
       세그먼트: JIT HugeMethodLimit(8KB) 대응 분할이 만든
       `private static Integer executeReturn_seg<k>(...)` — 버킷에는 여러 함수의 조각이
       모이므로 반드시 mang 접두로 개명하고, 본체의 조각 호출도 함께 치환한다."""
    ex = _find_method(text, r'public static void execute\(')
    rt = _find_method(text, r'public static int executeReturn\(')
    if not ex or not rt:
        return None
    ex_t = ex[2].replace("public static void execute(", f"public static void {mang}_execute(", 1)
    rt_t = rt[2].replace("public static int executeReturn(", f"public static int {mang}_executeReturn(", 1)
    # execute 래퍼 내부 executeReturn(...) 위임 → mang 이름
    # (executeReturn_seg<k>( 는 뒤가 '(' 가 아니라 '_' 라 이 정규식에 매치되지 않음 — 안전)
    ex_t = re.sub(r'\bexecuteReturn\(', f'{mang}_executeReturn(', ex_t)
    segs = []
    for sm in re.finditer(r'private static Integer executeReturn_seg(\d+)\(', text):
        k = sm.group(1)
        found = _find_method(text, r'private static Integer executeReturn_seg' + k + r'\(')
        if not found:
            continue
        st = found[2].replace(f"private static Integer executeReturn_seg{k}(",
                              f"private static Integer {mang}_executeReturn_seg{k}(", 1)
        segs.append(st)
    if segs:
        # 본체(rt_t)의 조각 호출 개명
        rt_t = re.sub(r'\bexecuteReturn_seg(\d+)\(', lambda m: f'{mang}_executeReturn_seg{m.group(1)}(', rt_t)
    return ex_t, rt_t, segs

def _prune_empty_dirs(root, verbose=True):
    """버킷 병합으로 per-함수 .java 가 모두 사라진 뒤 남는 빈 패키지 폴더를 정리한다.
       bottom-up(os.walk topdown=False)으로 돌며, 처리 시점에 비어 있는 디렉터리만
       rmdir 한다. 자식이 먼저 제거되면 부모도 비게 되어 연쇄적으로 정리된다.
       파일이 남은 폴더(buckets/, generated/KfcGen, ModEntry 등)는 비지 않으므로 보존된다."""
    import os
    root = Path(root)
    removed = 0
    for dirpath, _dirnames, _filenames in os.walk(root, topdown=False):
        d = Path(dirpath)
        if d == root:
            continue
        try:
            if not any(d.iterdir()):   # 매번 새로 확인 → 앞서 제거된 자식 반영
                d.rmdir()
                removed += 1
        except OSError:
            pass
    if verbose and removed:
        print(f"[bucketize] pruned {removed} empty directories")
    return removed


def bucketize(src_root: Path, group: str, pins: set | None = None,
              bucket_max_fns=200, bucket_max_bytes=350 * 1024, verbose=True):
    """생성된 1함수=1클래스 트리를 버킷 클래스로 재편. 반환 stats."""
    src_root = Path(src_root)
    classes, external_refs = load_tree(src_root, group)
    if not classes:
        return {"buckets": 0, "functions": 0}
    # 결정적 순서(패키지/클래스명) — 관련 함수가 같은 버킷에 모이도록 정렬
    items = sorted(classes.values(), key=lambda c: c.fqcn)

    # 1) 버킷 할당
    buckets = []   # list of list[Cls]
    cur = []; cur_bytes = 0
    for c in items:
        if cur and (len(cur) >= bucket_max_fns or cur_bytes + c.size > bucket_max_bytes):
            buckets.append(cur); cur = []; cur_bytes = 0
        cur.append(c); cur_bytes += c.size
    if cur:
        buckets.append(cur)

    bucket_pkg = f"{group}.buckets"
    # 2) fid/oldFQCN → (bucketFQCN, mang) 전역 맵
    remap = {}     # oldFQCN -> (bucketFQCN, mang)
    bucket_of = {} # bucket index -> (cls_name, [(c, mang, ex_t, rt_t)], imports set)
    for bi, group_cls in enumerate(buckets):
        cls_name = f"Bucket{bi}"
        bfqcn = f"{bucket_pkg}.{cls_name}"
        members = []; imports = set()
        for c in group_cls:
            mang = _mangle(c.fid)
            two = _extract_two_methods(c.text, mang)
            if two is None:
                continue
            ex_t, rt_t, seg_ts = two
            members.append((c, mang, ex_t, rt_t, seg_ts))
            imports.update(_imports_of(c.text))
            remap[c.fqcn] = (bfqcn, mang)
        bucket_of[bi] = (cls_name, members, imports)

    # 3) 전역 호출 재작성기: <oldFQCN>.execute|executeReturn( -> <bfqcn>.<mang>_executeReturn(
    #    [래퍼 제거] execute(void)는 executeReturn 위임 3줄 래퍼로 항상 순수(assemble 템플릿
    #    3종 전수 확인)이고, emit 은 모든 호출을 executeReturn 으로 방출한다(실측: 버킷 내
    #    _execute 호출부 0곳). 버킷에는 executeReturn 만 싣고, 혹시 남은 .execute( 호출은
    #    executeReturn 으로 통일한다(void 반환이던 호출은 식으로 쓰일 수 없어 문장 위치 보장
    #    → 반환 int 무시 = 래퍼와 동일 시맨틱). → 메서드 수 절반(클래스파일/메타스페이스).
    call_re = re.compile(r'([A-Za-z_][\w.]+)\.(execute|executeReturn)\s*\(')
    def rewrite_calls(text):
        def _r(m):
            fq = m.group(1)
            if fq in remap:
                bfqcn, mang = remap[fq]
                return f'{bfqcn}.{mang}_executeReturn('
            return m.group(0)
        return call_re.sub(_r, text)

    # 4) 버킷 클래스 텍스트 생성(메서드 본문 내부 호출도 재작성)
    bucket_dir = src_root / Path(*bucket_pkg.split("."))
    bucket_dir.mkdir(parents=True, exist_ok=True)
    base_imp = {f"{group}.generated.KfcGen", "net.minecraft.server.command.ServerCommandSource"}
    written = 0
    for bi, (cls_name, members, imports) in bucket_of.items():
        body_methods = []
        for (c, mang, ex_t, rt_t, seg_ts) in members:
            body_methods.append("    " + rewrite_calls(rt_t).strip())
            for st in seg_ts:   # JIT 분할 조각(있으면) — 개명본을 함께 이동
                body_methods.append("    " + rewrite_calls(st).strip())
        imp_lines = sorted(set(l[len("import "):].rstrip(";") for l in imports) | base_imp)
        proj = [i for i in imp_lines if i.startswith(group)]
        mc = [i for i in imp_lines if not i.startswith(group)]
        import_block = "\n".join(f"import {i};" for i in proj + mc)
        code = (f"package {bucket_pkg};\n\n{import_block}\n\n"
                f"/** Auto-bucketed: {len(members)} datapack functions. */\n"
                f"public final class {cls_name} {{\n"
                f"    private {cls_name}() {{ throw new UnsupportedOperationException(); }}\n\n"
                + "\n\n".join(body_methods) + "\n}\n")
        _write_if_changed(bucket_dir / f"{cls_name}.java", code)
        written += 1

    # 5) 원본 per-함수 파일 제거
    for c in classes.values():
        try: c.path.unlink()
        except OSError: pass

    # 6) 비생성 파일(ModEntry 등) 호출 재작성
    for jf in src_root.rglob("*.java"):
        if jf.parent == bucket_dir:
            continue
        t = jf.read_text(encoding="utf-8")
        if "generated" in jf.parts and jf.stem == "KfcGen":
            continue
        nt = rewrite_calls(t)
        if nt != t:
            _write_if_changed(jf, nt)

    # 7) 비워진 패키지 폴더 정리(병합 후 per-함수 .java 가 전부 사라진 디렉터리들)
    _prune_empty_dirs(src_root, verbose=verbose)

    if verbose:
        print(f"[bucketize] {len(classes)} functions -> {written} bucket classes "
              f"(<= {bucket_max_fns} fns / {bucket_max_bytes//1024}KB each)")
    return {"buckets": written, "functions": len(classes)}


# ═══════════════════════ 메모리 버킷화 (디스크 왕복 제거) ═══════════════════════
# generate 가 함수별 .java 를 디스크에 쓰면, 위 bucketize 는 그걸 전부 되읽고(load_tree)
# 버킷으로 합친 뒤 원본 .java 를 다시 삭제한다 — 대형 팩(수십만 함수)에서 '쓰기+되읽기+삭제'
# 왕복이 generate I/O 의 주 비용이다. 이 함수는 메모리상의 생성 클래스 레코드(Cls, path=None)
# 에서 곧장 버킷 .java 를 생성해 그 왕복을 없앤다. 산출(버킷 클래스 바이트)은 파일 기반
# bucketize 와 동일하다(동일 정렬·동일 할당·동일 재작성).
#   · records: 'Auto-generated from datapack function' 클래스들(=per-함수 생성물)만.
#              stub(브릿지)·KfcGen·ModEntry 는 기존과 동일하게 디스크 파일로 남는다.
#   · 비생성 on-disk 파일(ModEntry/stub 등)의 호출부 재작성은 파일 기반과 동일하게 수행.
def bucketize_records(records, src_root: Path, group: str,
                      bucket_max_fns: int = 200, bucket_max_bytes: int = 350 * 1024,
                      method_cap: int = METHOD_CAP, verbose: bool = True):
    src_root = Path(src_root)
    records = [c for c in records if getattr(c, "fid", None)]
    if not records:
        return {"buckets": 0, "functions": 0, "bridged": 0}

    # 1) 오버사이즈(>64KB 메서드) → mcfunction 브릿지 (메모리상 text 치환; 기준은 파일 기반과 동일)
    bridged = 0
    for c in records:
        if _oversized(c.text):   # 문자열 제외 바이트코드 근사
            c.text = bridge_oversized(c)
            c.size = len(c.text.encode("utf-8"))
            bridged += 1
            if verbose:
                print(f"  [bridge>64KB] {c.fid}")

    # 2) 결정적 순서(파일 기반 bucketize 와 동일: fqcn 정렬)
    items = sorted(records, key=lambda c: c.fqcn)

    # 3) 버킷 할당 (동일 규칙: ≤bucket_max_fns 개 / ≤bucket_max_bytes)
    buckets = []
    cur = []; cur_bytes = 0
    for c in items:
        if cur and (len(cur) >= bucket_max_fns or cur_bytes + c.size > bucket_max_bytes):
            buckets.append(cur); cur = []; cur_bytes = 0
        cur.append(c); cur_bytes += c.size
    if cur:
        buckets.append(cur)

    # 4) fqcn → (bucketFQCN, mang) 전역 맵 + 버킷 멤버 메서드 추출
    bucket_pkg = f"{group}.buckets"
    remap = {}
    bucket_of = {}
    for bi, group_cls in enumerate(buckets):
        cls_name = f"Bucket{bi}"
        bfqcn = f"{bucket_pkg}.{cls_name}"
        members = []; imports = set()
        for c in group_cls:
            mang = _mangle(c.fid)
            two = _extract_two_methods(c.text, mang)
            if two is None:
                continue
            ex_t, rt_t, seg_ts = two
            members.append((c, mang, ex_t, rt_t, seg_ts))
            imports.update(_imports_of(c.text))
            remap[c.fqcn] = (bfqcn, mang)
        bucket_of[bi] = (cls_name, members, imports)

    # 5) 전역 호출 재작성기 (<oldFQCN>.execute|executeReturn( -> <bfqcn>.<mang>_executeReturn()
    #    [래퍼 제거] 파일 기반 bucketize 와 동일 근거 — execute 래퍼는 순수 위임이므로
    #    버킷에 싣지 않고, 모든 호출을 executeReturn 으로 통일한다.
    call_re = re.compile(r'([A-Za-z_][\w.]+)\.(execute|executeReturn)\s*\(')
    def rewrite_calls(text):
        def _r(m):
            fq = m.group(1)
            if fq in remap:
                bfqcn, mang = remap[fq]
                return f'{bfqcn}.{mang}_executeReturn('
            return m.group(0)
        return call_re.sub(_r, text)

    # 6) 버킷 클래스 작성 (파일 기반 bucketize 와 동일 템플릿)
    bucket_dir = src_root / Path(*bucket_pkg.split("."))
    bucket_dir.mkdir(parents=True, exist_ok=True)
    base_imp = {f"{group}.generated.KfcGen", "net.minecraft.server.command.ServerCommandSource"}
    written = 0
    for bi, (cls_name, members, imports) in bucket_of.items():
        body_methods = []
        for (c, mang, ex_t, rt_t, seg_ts) in members:
            body_methods.append("    " + rewrite_calls(rt_t).strip())
            for st in seg_ts:   # JIT 분할 조각(있으면) — 개명본을 함께 이동
                body_methods.append("    " + rewrite_calls(st).strip())
        imp_lines = sorted(set(l[len("import "):].rstrip(";") for l in imports) | base_imp)
        proj = [i for i in imp_lines if i.startswith(group)]
        mc = [i for i in imp_lines if not i.startswith(group)]
        import_block = "\n".join(f"import {i};" for i in proj + mc)
        code = (f"package {bucket_pkg};\n\n{import_block}\n\n"
                f"/** Auto-bucketed: {len(members)} datapack functions. */\n"
                f"public final class {cls_name} {{\n"
                f"    private {cls_name}() {{ throw new UnsupportedOperationException(); }}\n\n"
                + "\n\n".join(body_methods) + "\n}\n")
        _write_if_changed(bucket_dir / f"{cls_name}.java", code)
        written += 1

    # 7) 비생성 on-disk 파일(ModEntry/stub 등) 호출 재작성 (KfcGen·버킷 제외) — 파일 기반과 동일
    for jf in src_root.rglob("*.java"):
        if jf.parent == bucket_dir:
            continue
        if "generated" in jf.parts and jf.stem == "KfcGen":
            continue
        t = jf.read_text(encoding="utf-8")
        nt = rewrite_calls(t)
        if nt != t:
            _write_if_changed(jf, nt)

    if verbose:
        print(f"[bucketize:mem] {len(records)} functions -> {written} bucket classes "
              f"(<= {bucket_max_fns} fns / {bucket_max_bytes//1024}KB each), bridged {bridged} "
              f"(no per-function file round-trip)")
    return {"buckets": written, "functions": len(records), "bridged": bridged}


def run_postpass(src_root: Path, group: str, pins: set | None = None,
                 strategy: str = "bucket", bucket_threshold: int = 3000,
                 verbose: bool = True):
    """후처리 통합 진입점.
       1) 오버사이즈(>64KB 메서드) → mcfunction 브릿지(항상).
       2) strategy (기본 'bucket'):
          - 'bucket': 여러 함수를 버킷 클래스로 묶어 클래스 수 대폭 감축(기본·권장).
          - 'absorb': 말단 단일호출자 메서드흡수 병합(호출지역성 보존; 소형 팩용 대안).
          - 'auto'  : 클래스 수 > bucket_threshold 면 bucket, 아니면 absorb.
          - 'none'  : 브릿지만(1함수=1클래스 유지).
    """
    src_root = Path(src_root)
    pins = set(pins or set())
    classes, external_refs = load_tree(src_root, group)
    for fqcn in external_refs:
        if fqcn in classes:
            pins.add(classes[fqcn].fid)
    n = len(classes)

    # 1) 오버사이즈 브릿지
    bridged = 0
    for f, c in list(classes.items()):
        if _oversized(c.text):   # 문자열 제외 바이트코드 근사
            new = bridge_oversized(c)
            _write_if_changed(c.path, new)
            c.text = new; c.size = len(new.encode())
            bridged += 1
            if verbose: print(f"  [bridge>64KB] {c.fid}")

    if strategy == "auto":
        strategy = "bucket" if n > bucket_threshold else "absorb"
    if verbose:
        print(f"[postpass] {n} classes, bridged {bridged}, strategy={strategy}")

    if strategy == "bucket":
        st = bucketize(src_root, group, pins=pins, verbose=verbose)
        st["bridged"] = bridged
        return st
    elif strategy == "absorb":
        # 기존 말단 흡수(브릿지는 위에서 이미 처리했으므로 run_merge 의 브릿지는 사실상 no-op)
        st = run_merge(src_root, group, pins=pins, verbose=verbose)
        return st
    else:
        return {"bridged": bridged, "merged": 0}

# ════════════════════════════════════════════════════════════════════
#  [pass-4] 상수 배열 호이스팅 — 생성 자바의 속도/크기 최적화 (범용, 시맨틱 불변)
#
#  생성 코드가 호출 인자로 방출하는 `new String[]{...}` / `new EntityType<?>[]{...}`
#  리터럴은 실행마다 힙 할당을 만든다(틱 함수에서 초당 수백~수천 회). 요소가 전부
#  컴파일타임 상수(문자열 리터럴 / EntityType.상수)인 리터럴만 클래스 수준
#  `private static final` 필드로 승격하고 사용처를 필드 참조로 바꾼다.
#    · 할당 제거(GC 压), 메서드 바이트코드 축소(JIT 인라이닝 예산 확보)
#    · 안전성: KfcGen 헬퍼는 배열 인자를 변형하지 않음(전수 확인) → 공유 안전.
#    · 문자열/주석 내부의 우연한 패턴은 라인-로컬 따옴표/`//` 상태 검사로 제외.
#    · KfcGen.java(수기 런타임)는 제외. 멱등: 이미 삽입된 필드 선언 라인은 건너뜀.
# ════════════════════════════════════════════════════════════════════

_STR_LIT = r'"(?:[^"\\]|\\.)*"'
_SA_RE = re.compile(r'new String\[\]\{(' + _STR_LIT + r'(?:\s*,\s*' + _STR_LIT + r')*|\s*)\}')
_ET_ELEM = r'(?:net\.minecraft\.entity\.)?EntityType\.[A-Z_][A-Z_0-9]*'
_ET_RE = re.compile(r'new net\.minecraft\.entity\.EntityType<\?>\[\]\{(' + _ET_ELEM
                    + r'(?:\s*,\s*' + _ET_ELEM + r')*)\}')
# 불변 값 객체 생성 호출 — 인자가 전부 리터럴이면 호출마다 검증/할당이 낭비 → static 필드로.
#   · Identifier.of("ns","path"): 호출당 네임스페이스/경로 문자 검증 + 할당. Identifier 불변.
#   · new Vec3d(x,y,z) (숫자 리터럴): 순수 불변 수학 객체.
_ID_RE = re.compile(r'(?<![\w.])(?:net\.minecraft\.util\.)?Identifier\.of\(\s*'
                    + _STR_LIT + r'\s*,\s*' + _STR_LIT + r'\s*\)')
_NUM_LIT = r'-?\d+(?:\.\d+)?(?:[eE]-?\d+)?[fFdD]?'
_V3_RE = re.compile(r'new net\.minecraft\.util\.math\.Vec3d\(\s*' + _NUM_LIT
                    + r'\s*,\s*' + _NUM_LIT + r'\s*,\s*' + _NUM_LIT + r'\s*\)')
_V2_RE = re.compile(r'new net\.minecraft\.util\.math\.Vec2f\(\s*' + _NUM_LIT
                    + r'\s*,\s*' + _NUM_LIT + r'\s*\)')   # Vec2f: public final x,y — 불변 확인
# 거대 문자열 연결(KfcGen.cat) — 인자가 전부 리터럴이면 클래스로드 1회 연결로 호이스팅
# (소환 함수가 호출될 때마다 수십만 자 StringBuilder 연결을 반복하지 않도록).
_CAT_RE = re.compile(r'KfcGen\.cat\(\s*' + _STR_LIT + r'(?:\s*,\s*' + _STR_LIT + r')*\s*\)')
_CLASS_OPEN_RE = re.compile(r'^[ \t]*public\s+(?:final\s+)?class\s+\w+[^{\n]*\{', re.M)

def _code_pos_ok(text: str, pos: int) -> bool:
    """pos 가 (라인 기준) 문자열 리터럴/라인주석 밖의 '코드 위치'인지 검사."""
    ls = text.rfind('\n', 0, pos) + 1
    in_str = False; esc = False
    i = ls
    while i < pos:
        c = text[i]
        if in_str:
            if esc: esc = False
            elif c == '\\': esc = True
            elif c == '"': in_str = False
        else:
            if c == '"': in_str = True
            elif c == '/' and i + 1 < pos and text[i+1] == '/':
                return False          # 라인 주석 내부
        i += 1
    return not in_str

# ── SNBT 리터럴 → static final 파싱 필드 승격 (pre-parsed 오버로드 호출로 재작성) ──
#   entityMergeSnbt(<e>, "SNBT")            → entityMergeSnbt(<e>, KFC_NBTC_k)   (NbtCompound)
#   storagePutSnbt(server,"id","p","SNBT","mode") → ...(...,KFC_NBTV_k,...)      (NbtElement)
#   변환 시점 상수 SNBT 를 클래스 로드 때 1회 파싱 → 호출당 SNBT_CACHE 조회/문자열 해시/copy용
#   조회 제거 + 메서드 바이트코드 축소. 리터럴 인자만 승격(매크로·동적 문자열은 그대로 String 판).
_SNBT_STR_FULL = re.compile(r'\s*"(?:[^"\\]|\\.)*"\s*\Z')

def _scan_call_args(body: str, needle: str):
    """body 안 모든 `needle`(= 'KfcGen.foo(') 호출의 (needle_start, close_idx, [(argstart,argend),...]).
       문자열/괄호/대괄호/중괄호 상태를 추적해 SNBT 안의 쉼표·괄호·따옴표에 속지 않는다."""
    out = []; i = 0; L = len(body)
    while True:
        j = body.find(needle, i)
        if j < 0: break
        i = j + len(needle)
        if not _code_pos_ok(body, j):        # 문자열/주석 내부 = 코드 아님
            continue
        k = j + len(needle)                  # '(' 다음 문자
        depth_p = 1; depth_b = 0; depth_c = 0
        in_str = False; esc = False
        seg_start = k; args = []; close_idx = -1
        while k < L:
            c = body[k]
            if in_str:
                if esc: esc = False
                elif c == '\\': esc = True
                elif c == '"': in_str = False
            else:
                if c == '"': in_str = True
                elif c == '(': depth_p += 1
                elif c == ')':
                    depth_p -= 1
                    if depth_p == 0:
                        args.append((seg_start, k)); close_idx = k; break
                elif c == '[': depth_b += 1
                elif c == ']': depth_b -= 1
                elif c == '{': depth_c += 1
                elif c == '}': depth_c -= 1
                elif c == ',' and depth_p == 1 and depth_b == 0 and depth_c == 0:
                    args.append((seg_start, k)); seg_start = k + 1
            k += 1
        if close_idx >= 0:
            out.append((j, close_idx, args))
    return out

def _promote_snbt_text(body: str, decls: list, fields: dict, start_seq: int) -> tuple[str, int, int]:
    """리터럴 인자를 파싱된 static final 필드로 승격하고 pre-parsed 오버로드 호출로 재작성.
       (새 body, 치환수, 다음 seq). 다인자 스펙(playSound)은 원자적 — 대상 인자가 '전부'
       리터럴일 때만 승격(부분 승격 시 매칭 오버로드 없음). 리터럴 아닌 인자(매크로/동적)는 원판 유지."""
    seq = start_seq; n = 0
    # (needle, 허용 인자수 집합|None, [(arg_idx, ftype, parse_fn, prefix), ...])
    _OBJR = ("KfcGen.ObjRef", "KfcGen.objRef", "KFC_OBJ_")
    specs = [
        ("KfcGen.entityMergeSnbt(", {2}, [
            (1, "net.minecraft.nbt.NbtCompound", "KfcGen.snbtCompound", "KFC_NBTC_")]),
        ("KfcGen.storagePutSnbt(", {5}, [
            (3, "net.minecraft.nbt.NbtElement", "KfcGen.snbtValue", "KFC_NBTV_")]),
        ("KfcGen.playSound(", {6, 7, 8, 9, 10}, [   # (p, snd, cat, [Vec3d|xyz], vol, pitch[, minVol[, seed]])
            (1, "net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent>",
             "KfcGen.soundEvent", "KFC_SND_"),
            (2, "net.minecraft.sound.SoundCategory", "KfcGen.soundCat", "KFC_SCAT_")]),
        # ── objective 핸들 승격: 상수 objective 이름 → static final ObjRef ──
        # 호출당 sb.getNullableObjective(문자열 해시+맵 조회) → 세대검사 1회 + 필드 참조.
        # 리터럴 인자만 승격(매크로/동적 objective 는 String 판 유지). opScore 는 두
        # objective 가 전부 리터럴일 때만(원자성 — 부분 승격 시 매칭 오버로드 없음).
        ("KfcGen.scoreMatches(",       {5}, [(2, *_OBJR)]),
        ("KfcGen.scoreMatchesEntity(", {5}, [(2, *_OBJR)]),
        ("KfcGen.readScoreEnt(",       {3}, [(2, *_OBJR)]),
        ("KfcGen.readScore(",          {3}, [(2, *_OBJR)]),
        ("KfcGen.getScore(",           {3}, [(2, *_OBJR)]),
        ("KfcGen.getScoreOfEntity(",   {3}, [(2, *_OBJR)]),
        ("KfcGen.setScore(",           {4}, [(2, *_OBJR)]),
        ("KfcGen.addScore(",           {4}, [(2, *_OBJR)]),
        ("KfcGen.opScore(",            {6}, [(2, *_OBJR), (5, *_OBJR)]),
    ]

    def _field_for(ftype, pfn, prefix, lit_s):
        nonlocal seq
        key = (prefix, lit_s)
        name = fields.get(key)
        if name is None:
            name = f"{prefix}{seq}"; seq += 1
            fields[key] = name
            decls.append(f"    private static final {ftype} {name} = {pfn}({lit_s});")
        return name

    repls = []   # (arg_start, arg_end, field_name)
    for needle, argcs, targets in specs:
        for (nstart, close_idx, args) in _scan_call_args(body, needle):
            if argcs is not None and len(args) not in argcs:
                continue
            if any(ti >= len(args) for (ti, *_ ) in targets):
                continue
            # 원자성: 대상 인자가 전부 순수 리터럴일 때만 승격
            lits = []
            ok = True
            for (ti, ftype, pfn, prefix) in targets:
                a0, a1 = args[ti]
                lit = body[a0:a1]
                if not _SNBT_STR_FULL.match(lit):
                    ok = False; break
                lits.append((a0, a1, lit.strip(), ftype, pfn, prefix))
            if not ok:
                continue
            for (a0, a1, lit_s, ftype, pfn, prefix) in lits:
                name = _field_for(ftype, pfn, prefix, lit_s)
                repls.append((a0, a1, name)); n += 1
    # 오른쪽부터 적용(인덱스 안정)
    for a0, a1, name in sorted(repls, key=lambda r: r[0], reverse=True):
        lead = body[a0:a1]
        pad = lead[:len(lead)-len(lead.lstrip())]
        body = body[:a0] + pad + name + body[a1:]
    return body, n, seq


def hoist_constants_text(text: str) -> tuple[str, int]:
    """단일 클래스 소스에서 상수 배열 리터럴을 static final 필드로 호이스팅.
       (변경된 텍스트, 치환 건수) 반환. 클래스 선언을 못 찾으면 무변경."""
    mcls = _CLASS_OPEN_RE.search(text)
    if mcls is None:
        return text, 0
    insert_at = mcls.end()

    fields: dict[str, str] = {}      # 리터럴 원문 -> 필드명
    decls: list[str] = []
    n = 0

    def _sub(regex: re.Pattern, prefix: str, ftype: str, src: str) -> str:
        nonlocal n
        out = []
        last = 0
        for m in regex.finditer(src):
            # 우리가 만든 필드 선언 라인 자체는 재치환 금지(멱등)
            ls = src.rfind('\n', 0, m.start()) + 1
            line_head = src[ls:m.start()]
            if 'private static final' in line_head or not _code_pos_ok(src, m.start()):
                continue
            lit = m.group(0)
            name = fields.get(lit)
            if name is None:
                name = f"{prefix}{len([k for k in fields if fields[k].startswith(prefix)])}"
                fields[lit] = name
                decls.append(f"    private static final {ftype} {name} = {lit};")
            out.append(src[last:m.start()]); out.append(name)
            last = m.end(); n += 1
        out.append(src[last:])
        return ''.join(out)

    body = text[insert_at:]
    body = _sub(_SA_RE, "KFC_SA_", "String[]", body)
    body = _sub(_ET_RE, "KFC_ET_", "net.minecraft.entity.EntityType<?>[]", body)
    body = _sub(_ID_RE, "KFC_ID_", "net.minecraft.util.Identifier", body)
    body = _sub(_V3_RE, "KFC_V3_", "net.minecraft.util.math.Vec3d", body)
    body = _sub(_V2_RE, "KFC_V2_", "net.minecraft.util.math.Vec2f", body)
    body = _sub(_CAT_RE, "KFC_CS_", "String", body)
    # SNBT 리터럴 → 파싱된 static final 필드 승격(pre-parsed 오버로드 호출로 재작성)
    body, _snbt_n, _ = _promote_snbt_text(body, decls, fields, len(decls))
    n += _snbt_n
    if n == 0:
        return text, 0
    return text[:insert_at] + "\n" + "\n".join(decls) + body, n

def hoist_constants_tree(src_root: Path, verbose: bool = False) -> dict:
    """src_root 아래 모든 생성 .java 에 호이스팅 적용(KfcGen.java 제외)."""
    files = 0; repl = 0
    for p in sorted(Path(src_root).rglob("*.java")):
        if p.name == "KfcGen.java":
            continue
        try:
            t = p.read_text(encoding="utf-8")
        except Exception:
            continue
        nt, k = hoist_constants_text(t)
        if k:
            p.write_text(nt, encoding="utf-8")
            files += 1; repl += k
            if verbose:
                print(f"  [hoist] {p.name}: {k}")
    return {"files": files, "hoisted": repl}