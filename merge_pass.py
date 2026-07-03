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

MERGE_MAX = 40 * 1024
METHOD_CAP = 64 * 1024
CLASS_CAP = 600 * 1024

FID_RE = re.compile(r'Auto-generated from datapack function `([^`]+)`')
PKG_RE = re.compile(r'^package\s+([\w.]+);', re.M)
CLS_RE = re.compile(r'public final class (\w+)')
MACRO_SIG_RE = re.compile(r'executeReturn\(ServerCommandSource\s+\w+,\s*Map<String,\s*String>')

class Cls:
    def __init__(self, path: Path, text: str):
        self.path = path
        self.text = text
        self.fid = (FID_RE.search(text) or [None, None])[1] if FID_RE.search(text) else None
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
    over = [f for f,c in classes.items() if c.size > METHOD_CAP]
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
        if c.size > method_cap:
            new = bridge_oversized(c)
            c.path.write_text(new, encoding="utf-8")
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
            parent.path.write_text(newp, encoding="utf-8")
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
    """클래스 text 에서 execute/executeReturn 두 메서드를 public static 으로, mang 접두 이름으로 추출."""
    ex = _find_method(text, r'public static void execute\(')
    rt = _find_method(text, r'public static int executeReturn\(')
    if not ex or not rt:
        return None
    ex_t = ex[2].replace("public static void execute(", f"public static void {mang}_execute(", 1)
    rt_t = rt[2].replace("public static int executeReturn(", f"public static int {mang}_executeReturn(", 1)
    # execute 래퍼 내부 executeReturn(...) 위임 → mang 이름
    ex_t = re.sub(r'\bexecuteReturn\(', f'{mang}_executeReturn(', ex_t)
    return ex_t, rt_t

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
            ex_t, rt_t = two
            members.append((c, mang, ex_t, rt_t))
            imports.update(_imports_of(c.text))
            remap[c.fqcn] = (bfqcn, mang)
        bucket_of[bi] = (cls_name, members, imports)

    # 3) 전역 호출 재작성기: <oldFQCN>.execute( -> <bfqcn>.<mang>_execute(
    #    효율을 위해 한 번에: 토큰 단위로 찾아 remap 적용.
    call_re = re.compile(r'([A-Za-z_][\w.]+)\.(execute|executeReturn)\s*\(')
    def rewrite_calls(text):
        def _r(m):
            fq, meth = m.group(1), m.group(2)
            if fq in remap:
                bfqcn, mang = remap[fq]
                return f'{bfqcn}.{mang}_{meth}('
            return m.group(0)
        return call_re.sub(_r, text)

    # 4) 버킷 클래스 텍스트 생성(메서드 본문 내부 호출도 재작성)
    bucket_dir = src_root / Path(*bucket_pkg.split("."))
    bucket_dir.mkdir(parents=True, exist_ok=True)
    base_imp = {f"{group}.generated.KfcGen", "net.minecraft.server.command.ServerCommandSource"}
    written = 0
    for bi, (cls_name, members, imports) in bucket_of.items():
        body_methods = []
        for (c, mang, ex_t, rt_t) in members:
            body_methods.append("    " + rewrite_calls(ex_t).strip())
            body_methods.append("    " + rewrite_calls(rt_t).strip())
        imp_lines = sorted(set(l[len("import "):].rstrip(";") for l in imports) | base_imp)
        proj = [i for i in imp_lines if i.startswith(group)]
        mc = [i for i in imp_lines if not i.startswith(group)]
        import_block = "\n".join(f"import {i};" for i in proj + mc)
        code = (f"package {bucket_pkg};\n\n{import_block}\n\n"
                f"/** Auto-bucketed: {len(members)} datapack functions. */\n"
                f"public final class {cls_name} {{\n"
                f"    private {cls_name}() {{ throw new UnsupportedOperationException(); }}\n\n"
                + "\n\n".join(body_methods) + "\n}\n")
        (bucket_dir / f"{cls_name}.java").write_text(code, encoding="utf-8")
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
            jf.write_text(nt, encoding="utf-8")

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
        if c.size > method_cap:
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
            ex_t, rt_t = two
            members.append((c, mang, ex_t, rt_t))
            imports.update(_imports_of(c.text))
            remap[c.fqcn] = (bfqcn, mang)
        bucket_of[bi] = (cls_name, members, imports)

    # 5) 전역 호출 재작성기 (<oldFQCN>.execute( -> <bfqcn>.<mang>_execute()
    call_re = re.compile(r'([A-Za-z_][\w.]+)\.(execute|executeReturn)\s*\(')
    def rewrite_calls(text):
        def _r(m):
            fq, meth = m.group(1), m.group(2)
            if fq in remap:
                bfqcn, mang = remap[fq]
                return f'{bfqcn}.{mang}_{meth}('
            return m.group(0)
        return call_re.sub(_r, text)

    # 6) 버킷 클래스 작성 (파일 기반 bucketize 와 동일 템플릿)
    bucket_dir = src_root / Path(*bucket_pkg.split("."))
    bucket_dir.mkdir(parents=True, exist_ok=True)
    base_imp = {f"{group}.generated.KfcGen", "net.minecraft.server.command.ServerCommandSource"}
    written = 0
    for bi, (cls_name, members, imports) in bucket_of.items():
        body_methods = []
        for (c, mang, ex_t, rt_t) in members:
            body_methods.append("    " + rewrite_calls(ex_t).strip())
            body_methods.append("    " + rewrite_calls(rt_t).strip())
        imp_lines = sorted(set(l[len("import "):].rstrip(";") for l in imports) | base_imp)
        proj = [i for i in imp_lines if i.startswith(group)]
        mc = [i for i in imp_lines if not i.startswith(group)]
        import_block = "\n".join(f"import {i};" for i in proj + mc)
        code = (f"package {bucket_pkg};\n\n{import_block}\n\n"
                f"/** Auto-bucketed: {len(members)} datapack functions. */\n"
                f"public final class {cls_name} {{\n"
                f"    private {cls_name}() {{ throw new UnsupportedOperationException(); }}\n\n"
                + "\n\n".join(body_methods) + "\n}\n")
        (bucket_dir / f"{cls_name}.java").write_text(code, encoding="utf-8")
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
            jf.write_text(nt, encoding="utf-8")

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
        if c.size > METHOD_CAP:
            new = bridge_oversized(c)
            c.path.write_text(new, encoding="utf-8")
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