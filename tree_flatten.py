#!/usr/bin/env python3
"""
tree_flatten.py — [pass-2.7] 스코어 디스패치 트리 평탄화.

배경
----
mcfunction 에는 산술 분기가 없어, 대량 시분할 팩(NBS 음악, 키프레임 애니메이션 등)은
"점수 범위 이진 탐색"을 함수 트리로 흉내낸다:

    # ns:tree/0_1023
    execute as @s[scores={obj=0..41120}] run function ns:tree/0_511
    execute as @s[scores={obj=40960..82160}] run function ns:tree/512_1023

1:1 변환하면 재생 중 플레이어당 매 틱 log2(N)단의 메서드 프레임 + 단마다 1~2회의
objective 해시 조회 + Integer 박싱이 발생하고, 산출물의 절반 이상이 이 트리 노드다
(kartall 실측: 194,112 함수 중 tree/ 112,927개 = 58%).

이 패스는 그런 순수-디스패치 서브그래프를 감지해 하나의 정적 구간 테이블 + 반복 워커로
컴파일하고, 외부에서 참조되지 않는 내부 노드 레코드를 제거한다.

바닐라 무결성 논증
------------------
'노드'는 본문 전체가 아래 두 형태의 줄로만 구성되고 objective 가 동일한 함수다:

    execute as @s[scores={OBJ=RANGE}] run function CHILD      (P1)
    execute if score @s OBJ matches RANGE run function CHILD  (P2)

두 형태 모두 실행 시맨틱은 "실행자 존재 && 점수 RANGE 매치 → CHILD 를 같은 실행자로
depth-first 실행"이며, 조건 평가 자체는 부수효과가 없다. 따라서:

  · 워커는 트리를 원본과 동일한 방문 순서(형제 = 줄 순서, 자식 우선)로 하강한다.
    범위가 겹쳐 형제가 둘 다 실행되는 경우도 순서 그대로 재현된다.
  · 점수 읽기는 "터미널(비-노드 함수) 호출 사이"에만 값이 바뀔 수 있다 — 노드 자체는
    아무것도 쓰지 않으므로. 워커는 시작 시 1회 + '터미널 호출 직후마다' 재읽기한다.
    → 각 조건이 관측하는 값은 원본이 각 노드에서 재읽던 값과 정확히 일치한다
    (터미널이 OBJ 를 쓰는 팩이라도 무결성 유지 — 쓰기 여부 분석 자체가 불필요).
  · `as @s` 의 withEntity(실행자 자신) 는 소스 내용을 바꾸지 않으므로 터미널에는
    원본 source 를 그대로 전달한다 (실행자 null 이면 모든 조건 불일치 = 진입시 반환).
  · 실행자가 null 이거나 점수 미설정(null)이면 원본의 모든 노드 조건이 거짓 → 워커도
    즉시 반환.

안전 게이트 (fail-closed)
-------------------------
  · 클러스터에 사이클(루프 함수) 존재 → 스킵.
  · 터미널이 매크로 함수(records 기준) → 스킵 (호출 시그니처 상이).
  · 노드 레코드가 브릿지/세그먼트/매크로를 포함 → 그 노드는 터미널로 취급.
  · 외부(비클러스터 자바 코드·tick/load 태그·데이터팩 함수 태그)에서 참조되는 노드는
    삭제하지 않고 '엔트리'로 유지 — 본문만 워커 위임으로 교체.
  · 엣지 수가 상한(문자열 인코딩 한계)을 넘는 클러스터 → 스킵.

산출
----
  · {group}.generated.KfcTree<k>.java — 구간 테이블(문자열 인코딩) + 워커 + 터미널 스위치.
  · 엔트리 노드 레코드: executeReturn 이 워커 호출로 교체된 소형 클래스.
  · 내부 노드 레코드: records 리스트에서 제거(버킷에 실리지 않음).

이 패스는 bucketize 전에 실행된다. 워커의 터미널 호출은 per-함수 FQCN 으로 방출되고,
bucketize 의 전역 호출 재작성(step 6/7)이 버킷 메서드 호출로 바꿔 준다.
"""
from __future__ import annotations
import re
from pathlib import Path

INT_MIN = -2147483648
INT_MAX = 2147483647
MIN_CLUSTER_NODES = 4          # 이보다 작은 클러스터는 평탄화 이득이 없음
MAX_EDGES_PER_CLUSTER = 12000  # 문자열 인코딩(5자/int, 리터럴 65535B) 보수 상한
TERM_SWITCH_SEG = 300          # 터미널 스위치 세그먼트 크기(JIT HugeMethodLimit 8KB 방어)

# ── mcfunction 노드 줄 패턴 ──────────────────────────────────────────────
_OBJ = r'[A-Za-z0-9_.+-]+'
_FID = r'[a-z0-9_.-]+:[a-z0-9_./-]+'
_RANGE = r'(?:-?\d+\.\.-?\d+|-?\d+\.\.|\.\.-?\d+|-?\d+)'
_P1 = re.compile(rf'^execute as @s\[scores=\{{({_OBJ})=({_RANGE})\}}\] run function ({_FID})$')
_P2 = re.compile(rf'^execute if score @s ({_OBJ}) matches ({_RANGE}) run function ({_FID})$')
# [범용 노드] scores 키 N개(순서 임의) → function. 주키(정렬탐색 축) + 부가 상수 가드 K개.
_PN = re.compile(rf'^execute as @s\[scores=\{{([^{{}}\]]+)\}}\] run function ({_FID})$')

# ── [가드 흡수 — 범용] 스코어 가드 터미널 + 상수 리프 패턴 ──────────────────
# 가드 줄: scores 키 1..N개(순서 임의, objective 임의) → 리프 호출. 시분할 팩 공통 형태.
_PAIRT = re.compile(rf'^execute as @s\[scores=\{{([^{{}}\]]+)\}}\] run function ({_FID})$')
_KV = re.compile(rf'^({_OBJ})=({_RANGE})$')
# notes 줄: playsound <snd> <cat> @s ~ ~ ~ <vol> <pitch> [minVol]  (전부 상수)
_NOTE = re.compile(r'^playsound ([a-z0-9_.:/-]+) '
                   r'(master|music|record|weather|block|hostile|neutral|player|ambient|voice) '
                   r'@s ~ ~ ~ ([0-9]*\.?[0-9]+) ([0-9]*\.?[0-9]+)(?: ([0-9]*\.?[0-9]+))?$')
_SETT = re.compile(rf'^scoreboard players (set|add|remove) @s ({_OBJ}) (-?\d+)$')
_TAGL = re.compile(r'^tag @s (add|remove) ([A-Za-z0-9_.+-]+)$')
MAX_ABS_OPS = 12000    # ops 인코딩 상한(엣지 상한과 동일 근거)


def _fbits(v: float) -> int:
    import struct as _st
    return _st.unpack('<i', _st.pack('<f', v))[0]


def _pair_lines(lines, obj):
    """[범용] 모든 줄이 '스코어 가드 → 리프 호출'이면 [([(gobj,lo,hi),...], child), ...].
       키 개수/순서/objective 임의. 원본은 줄 평가 시점에 각 objective 를 읽으므로
       해석기도 엔트리마다 가드 objective 를 재읽는다. 아니면 None."""
    if not lines:
        return None
    out = []
    for ln in lines:
        m = _PAIRT.match(ln)
        if not m:
            return None
        body, child = m.groups()
        guards = []
        for kv in body.split(","):
            mk = _KV.match(kv.strip())
            if not mk:
                return None            # 형식 밖(공백/중첩 등) — fail-closed
            lo, hi = _parse_range(mk.group(2))
            guards.append((mk.group(1), lo, hi))
        if not guards:
            return None
        out.append((guards, child))
    return out


def _notes_ops(lines):
    """[범용] 상수 리프면 순서 보존 ops, 아니면 None.
       op = ('note', snd,cat,vol,pitch,minvol) | ('set'|'add', obj, n) | ('tagadd'|'tagrm', name)
       set/add/remove 는 KfcGen.setScore/addScore(Entity 판), tag 는 addTag/removeTag —
       모두 기존 네이티브 emit 과 동일 헬퍼라 캐시 무효화 규약도 동일하다."""
    if lines is None:
        return None
    ops = []
    for ln in lines:
        m = _NOTE.match(ln)
        if m:
            ops.append(('note', m.group(1), m.group(2),
                        float(m.group(3)), float(m.group(4)), float(m.group(5) or 0.0)))
            continue
        m = _SETT.match(ln)
        if m:
            verb, o, n = m.group(1), m.group(2), int(m.group(3))
            if verb == 'set':
                ops.append(('set', o, n))
            else:
                ops.append(('add', o, n if verb == 'add' else -n))
            continue
        m = _TAGL.match(ln)
        if m:
            ops.append(('tagadd' if m.group(1) == 'add' else 'tagrm', m.group(2)))
            continue
        return None
    return ops

_CALL_RE = re.compile(r'([A-Za-z_][\w.]+)\.(?:execute|executeReturn)\s*\(')


def _parse_range(r: str) -> tuple[int, int]:
    if ".." in r:
        lo_s, hi_s = r.split("..", 1)
        lo = int(lo_s) if lo_s else INT_MIN
        hi = int(hi_s) if hi_s else INT_MAX
    else:
        lo = hi = int(r)
    return lo, hi


def _node_lines(lines: list[str]):
    """함수의 줄들이 전부 노드 패턴이면 (obj, [(lo,hi,child,extra), ...]) 반환, 아니면 None.
       [범용] 각 줄은 scores 가드 N키(순서 임의). 클러스터 전체에서 등장한 objective 중
       '가장 넓은 범위를 갖는 하나'를 주키(obj, 정렬탐색 축)로 잡고, 나머지 키는 부가 상수
       가드 extra=[(gobj,lo,hi),...] 로 노드에 부착한다. 워커가 자식 전개 전에 extra 를 재읽어
       검사 — 원본 줄 평가 시점 재읽기와 동일. 주키가 없는 줄(부가만)은 노드 아님(None)."""
    if not lines:
        return None
    # 1차: 각 줄 파싱 → [(obj별 범위 dict, child)]
    parsed = []
    for ln in lines:
        m = _P1.match(ln) or _P2.match(ln)
        if m:
            parsed.append(({m.group(1): _parse_range(m.group(2))}, m.group(3)))
            continue
        m = _PN.match(ln)
        if not m:
            return None
        gmap = {}
        for kv in m.group(1).split(","):
            mk = _KV.match(kv.strip())
            if not mk:
                return None
            if mk.group(1) in gmap:
                return None            # 동일 objective 중복 키 — 미지원(fail-closed)
            gmap[mk.group(1)] = _parse_range(mk.group(2))
        parsed.append((gmap, m.group(2)))   # _PN 은 그룹 2개(scores body, child)
    # 2차: 주키 선정 — 모든 줄에 공통 등장하는 objective 중 (전 노드 통일 위해) 이름 최소.
    common = None
    for gmap, _c in parsed:
        ks = set(gmap)
        common = ks if common is None else (common & ks)
    if not common:
        return None                    # 공통 주키 없음 — 노드 아님
    obj = min(common)                  # 결정적 선택(클러스터 통일은 flatten 쪽에서 obj 일치로 강제)
    edges = []
    for gmap, child in parsed:
        lo, hi = gmap[obj]
        extra = [(o, r[0], r[1]) for o, r in gmap.items() if o != obj]
        edges.append((lo, hi, child, extra))
    return obj, edges


def collect_function_lines(dp_src) -> dict[str, list[str]]:
    """datapack_io 소스 → {fid: [정리된 명령줄...]} (convert.extract 와 동일 규칙:
       CRLF 제거, 백슬래시 줄-연속 결합, 빈 줄/주석 제거)."""
    fn_files = dp_src.glob("data/*/function/**/*.mcfunction")
    if not fn_files:
        fn_files = dp_src.glob("data/*/functions/**/*.mcfunction")
    out = {}
    for rel_path in fn_files:
        parts = rel_path.split("/")
        try:
            di = parts.index("data")
            ns = parts[di + 1]
            try:
                fi = parts.index("function", di)
            except ValueError:
                fi = parts.index("functions", di)
        except ValueError:
            continue
        rel = "/".join(parts[fi + 1:]).removesuffix(".mcfunction")
        fid = f"{ns}:{rel}"
        text = dp_src.read_text(rel_path, encoding="utf-8", errors="replace").replace("\r", "")
        joined = []
        buf = None
        for pl in text.split("\n"):
            cur = pl if buf is None else buf + pl
            if cur.endswith("\\") and not cur.endswith("\\\\"):
                buf = cur[:-1]
                continue
            joined.append(cur)
            buf = None
        if buf is not None:
            joined.append(buf)
        lines = []
        for raw in joined:
            s = raw.strip()
            if not s or s.startswith("#"):
                continue
            lines.append(s)
        out[fid] = lines
    return out


def _encode_ints(vals: list[int]) -> str:
    """int 배열 → 프린터블 문자열(5자/int, base-90, '"'/'\\' 회피).
       KfcGen.decodeInts 와 역함수 관계."""
    chars = []
    for v in vals:
        u = (v - INT_MIN) & 0xFFFFFFFF
        digs = []
        for _ in range(5):
            digs.append(u % 90)
            u //= 90
        for d in reversed(digs):
            c = d + 35
            if c >= 92:          # '\\'(92) 는 건너뜀 → 코드 한 칸 당김
                c += 1
            chars.append(chr(c))
    return "".join(chars)


def _tag_fids(dp_src) -> set:
    """데이터팩 함수 태그(json)가 가리키는 fid 집합 — 태그로 참조되는 노드는 엔트리 유지."""
    import json as _json
    fids = set()
    if dp_src is None:
        return fids
    for pat in ("data/*/tags/function/**/*.json", "data/*/tags/functions/**/*.json"):
        for rel in dp_src.glob(pat):
            try:
                data = _json.loads(dp_src.read_text(rel, encoding="utf-8", errors="replace"))
            except Exception:
                continue
            for v in data.get("values", []):
                s = v.get("id") if isinstance(v, dict) else v
                if isinstance(s, str):
                    fids.add(s.lstrip("#"))
    return fids


def flatten_trees(records: list, src_root: Path, group: str, dp_src,
                  tags: dict, fid_to_fqcn, verbose: bool = True,
                  lines_map: dict | None = None) -> dict:
    """records 를 제자리 수정(엔트리 교체·내부 노드 제거)하고 KfcTree 클래스를 디스크에 쓴다.
       lines_map 을 주면 데이터팩 재스캔을 생략(convert 가 pass-2.8 과 공유)."""
    src_root = Path(src_root)
    stats = {"clusters": 0, "nodes": 0, "removed": 0, "entries": 0, "skipped": 0}

    if lines_map is None:
        lines_map = collect_function_lines(dp_src)
    if not lines_map:
        return stats

    # ── 1) 노드 감지 ──
    nodes = {}          # fid -> (obj, [(lo,hi,child_fid), ...])
    for fid, lines in lines_map.items():
        nl = _node_lines(lines)
        if nl is not None:
            nodes[fid] = nl

    if len(nodes) < MIN_CLUSTER_NODES:
        return stats

    by_fid = {}
    for c in records:
        if getattr(c, "fid", None):
            by_fid[c.fid] = c

    # 노드 레코드가 브릿지/세그/매크로면 노드에서 제외(터미널 취급) — fail-closed.
    for fid in list(nodes):
        c = by_fid.get(fid)
        if c is not None and ("instantExecuteFunction" in c.text
                              or "executeReturn_seg" in c.text
                              or c.is_macro):
            del nodes[fid]

    # ── 2) 클러스터(약연결 성분, 동일 obj 에지만) ──
    adj = {f: [] for f in nodes}
    for f, (obj, edges) in nodes.items():
        for (_lo, _hi, ch, _ex) in edges:
            if ch in nodes and nodes[ch][0] == obj:
                adj[f].append(ch)
    parent = {f: f for f in nodes}
    def _find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]
            x = parent[x]
        return x
    def _union(a, b):
        ra, rb = _find(a), _find(b)
        if ra != rb:
            parent[ra] = rb
    for f, chs in adj.items():
        for ch in chs:
            _union(f, ch)
    comps = {}
    for f in nodes:
        comps.setdefault(_find(f), []).append(f)

    # ── 3) 외부 참조 스캔 ──
    # 자바 측: 비클러스터 레코드 + on-disk 파일(ModEntry/stub 등)이 부르는 FQCN.
    all_node_fids = set(nodes)
    # [NBS] 쌍노드/notes 후보 사전 감지 — 외부참조 스캔·레코드 제거 판단에 포함
    nbs_pairs = {}     # guard-terminal fid -> [child_fid ...]
    for _f, _ls in lines_map.items():
        if _f in nodes or not _ls:
            continue
        _pl = _pair_lines(_ls, None)
        if _pl is not None:
            nbs_pairs[_f] = [c for (_g, c) in _pl]
    nbs_children = set()
    for _cs in nbs_pairs.values():
        nbs_children.update(_cs)
    node_fqcn = {}
    for f in all_node_fids | set(nbs_pairs) | nbs_children:
        c = by_fid.get(f)
        node_fqcn[f] = c.fqcn if c is not None else fid_to_fqcn(f, group)
    fqcn_to_fid = {v: k for k, v in node_fqcn.items()}

    ext_ref = set()      # 외부에서 참조되는 노드 fid
    for c in records:
        _fid0 = getattr(c, "fid", None)
        if _fid0 in all_node_fids or _fid0 in nbs_pairs:
            continue     # 클러스터 노드/쌍노드 간 참조는 내부(흡수·제거 판단은 별도)
        t = c.text
        if ".execute" not in t:
            continue
        for m in _CALL_RE.finditer(t):
            fid = fqcn_to_fid.get(m.group(1))
            if fid is not None:
                ext_ref.add(fid)
    for jf in src_root.rglob("*.java"):
        try:
            t = jf.read_text(encoding="utf-8")
        except OSError:
            continue
        if ".execute" not in t:
            continue
        for m in _CALL_RE.finditer(t):
            fid = fqcn_to_fid.get(m.group(1))
            if fid is not None:
                ext_ref.add(fid)
    # tick/load 태그 + 데이터팩 함수 태그
    _refables = all_node_fids | set(nbs_pairs) | nbs_children
    for k in ("tick", "load"):
        for fid in (tags or {}).get(k, []):
            if fid in _refables:
                ext_ref.add(fid)
    for fid in _tag_fids(dp_src):
        if fid in _refables:
            ext_ref.add(fid)

    # ── 4) 클러스터별 코드 생성 ──
    gen_dir = src_root / Path(*f"{group}.generated".split("."))
    gen_dir.mkdir(parents=True, exist_ok=True)
    try:
        from convert import write_if_changed as _wic
    except ImportError:
        def _wic(path, text):
            try:
                if path.exists() and path.read_text(encoding="utf-8") == text:
                    return False
            except OSError:
                pass
            path.write_text(text, encoding="utf-8")
            return True

    removed_fids = set()
    nbs_abs_children = {}   # notes child fid -> [pair fid ...] (흡수된 것만)
    tree_idx = 0
    for _root, comp in sorted(comps.items(), key=lambda kv: sorted(kv[1])[0]):
        comp = sorted(comp)
        if len(comp) < MIN_CLUSTER_NODES:
            stats["skipped"] += 1
            continue
        obj = nodes[comp[0]][0]

        # 사이클 검사(색칠 DFS) — 루프 함수는 스킵(fail-closed)
        WHITE, GRAY, BLACK = 0, 1, 2
        color = {f: WHITE for f in comp}
        cyclic = False
        for start in comp:
            if color[start] != WHITE:
                continue
            stack = [(start, 0)]
            color[start] = GRAY
            while stack:
                f, i = stack[-1]
                chs = adj[f]
                if i < len(chs):
                    stack[-1] = (f, i + 1)
                    ch = chs[i]
                    if color[ch] == GRAY:
                        cyclic = True
                        break
                    if color[ch] == WHITE:
                        color[ch] = GRAY
                        stack.append((ch, 0))
                else:
                    color[f] = BLACK
                    stack.pop()
            if cyclic:
                break
        if cyclic:
            stats["skipped"] += 1
            continue

        # 엔트리 = 외부 참조 노드 ∪ (클러스터 내 부모 없음 & 레코드 존재)
        in_parent = set()
        for f in comp:
            for ch in adj[f]:
                in_parent.add(ch)
        entries = []
        for f in comp:
            if f in ext_ref or (f not in in_parent):
                if f in by_fid:                 # 레코드 없으면 교체 불가 — 엔트리 아님
                    entries.append(f)
        if not entries:
            stats["skipped"] += 1
            continue

        # 터미널 수집 + 매크로 게이트
        terminals = []      # fid 순서 보존(결정적)
        term_id = {}
        macro_bail = False
        for f in comp:
            for (_lo, _hi, ch, _ex) in nodes[f][1]:
                if ch in nodes and nodes[ch][0] == obj:
                    continue
                if ch not in term_id:
                    tc = by_fid.get(ch)
                    if tc is not None and tc.is_macro:
                        macro_bail = True
                        break
                    term_id[ch] = len(terminals)
                    terminals.append(ch)
            if macro_bail:
                break
        if macro_bail:
            stats["skipped"] += 1
            continue

        # ── [NBS 흡수] 쌍노드 터미널(+notes 리프) → 데이터 테이블 분류 ──
        # fail-closed: 패턴 불일치/objt 불일치/매크로/ops 상한 초과 → 그 터미널은 메서드 호출 유지.
        abs_set = {}     # term fid -> [(guards, ops, child), ...]  (guards=[(gobj,lo,hi),...])
        for ch in terminals:
            pl = _pair_lines(lines_map.get(ch), None)
            if pl is None:
                continue
            tc2 = by_fid.get(ch)
            if tc2 is not None and tc2.is_macro:
                continue
            ents = []
            bad = False
            for (guards, child) in pl:
                cc = by_fid.get(child)
                if cc is not None and cc.is_macro:
                    bad = True; break
                ops = _notes_ops(lines_map.get(child))
                if ops is None:
                    bad = True; break
                ents.append((guards, ops, child))
            if bad or not ents:
                continue
            abs_set[ch] = ents
        if sum(len(e[1]) for v in abs_set.values() for e in v) > MAX_ABS_OPS:
            abs_set = {}
        if abs_set:
            terminals = [t for t in terminals if t in abs_set] + [t for t in terminals if t not in abs_set]
            term_id = {t: i for i, t in enumerate(terminals)}
        n_abs = sum(1 for t in terminals if t in abs_set)

        # 노드 인덱싱 + 평탄 에지 배열 (+ 부가 상수 가드 게이트)
        nid = {f: i for i, f in enumerate(comp)}
        lo_arr, hi_arr, tgt_arr = [], [], []
        es_arr, ee_arr = [], []
        xs_arr, xe_arr = [], []          # 에지 → 부가가드 슬라이스
        xobj, xlo, xhi = [], [], []      # 부가가드: objective idx, 범위
        _nobj_key, _nobj_names = {}, []
        def _noid(o):
            if o not in _nobj_key:
                _nobj_key[o] = len(_nobj_names); _nobj_names.append(o)
            return _nobj_key[o]
        has_extra = False
        for f in comp:
            es_arr.append(len(lo_arr))
            for (lo, hi, ch, extra) in nodes[f][1]:
                lo_arr.append(lo)
                hi_arr.append(hi)
                xs_arr.append(len(xobj))
                for (go, glo2, ghi2) in extra:
                    xobj.append(_noid(go)); xlo.append(glo2); xhi.append(ghi2)
                    has_extra = True
                xe_arr.append(len(xobj))
                if ch in nid:
                    tgt_arr.append(nid[ch])
                else:
                    tgt_arr.append(-(term_id[ch] + 1))
            ee_arr.append(len(lo_arr))
        if len(lo_arr) > MAX_EDGES_PER_CLUSTER:
            stats["skipped"] += 1
            continue

        # 워커 스택 상한(정확 시뮬레이션, DAG 메모): g(n) = 처리 중 최대 잔여 스택
        gmemo = {}
        def _g(n):
            if n in gmemo:
                return gmemo[n]
            k = ee_arr[n] - es_arr[n]
            best = k
            for i in range(k):
                tg = tgt_arr[es_arr[n] + i]
                rest = k - 1 - i
                if tg >= 0:
                    best = max(best, rest + _g(tg))
            gmemo[n] = best
            return best
        max_stack = 4
        for f in entries:
            max_stack = max(max_stack, _g(nid[f]) + 2)

        # 부가 상수 가드(다중키 노드) 게이트 — 없으면 배열/검사 생략(NBS 등 단일키는 코드 불변).
        if has_extra:
            _xdecl = (
                f'    private static final int[] XS = KfcGen.decodeInts("{_encode_ints(xs_arr)}");\n'
                f'    private static final int[] XE = KfcGen.decodeInts("{_encode_ints(xe_arr)}");\n'
                f'    private static final int[] XOBJ = KfcGen.decodeInts("{_encode_ints(xobj)}");\n'
                f'    private static final int[] XLO = KfcGen.decodeInts("{_encode_ints(xlo)}");\n'
                f'    private static final int[] XHI = KfcGen.decodeInts("{_encode_ints(xhi)}");\n'
                f'    private static final String[] NOBJ = {{ {", ".join(chr(34)+x+chr(34) for x in _nobj_names)} }};\n')
            _xgate = (
                "            if (XS[ed] < XE[ed]) {\n"
                "                boolean _xp = true;\n"
                "                for (int g = XS[ed]; g < XE[ed]; g++) {   // 부가 상수 가드 재읽기(원본 줄 평가 시점)\n"
                "                    Integer xv = KfcGen.readScoreEnt(sb, e, NOBJ[XOBJ[g]]);\n"
                "                    if (xv == null || xv < XLO[g] || xv > XHI[g]) { _xp = false; break; }\n"
                "                }\n"
                "                if (!_xp) continue;\n"
                "            }\n")
        else:
            _xdecl = ""
            _xgate = ""

        cls_name = f"KfcTree{tree_idx}"
        tree_idx += 1
        tree_fqcn = f"{group}.generated.{cls_name}"

        # ── [NBS] 흡수 테이블 + absTerm 코드 생성 ──
        abs_code = ""
        if n_abs:
            pes, pee = [], []                       # 터미널 → 엔트리 슬라이스
            gs_a, ge_a = [], []                     # 엔트리 → 가드 슬라이스
            gobj, glo, ghi = [], [], []             # 가드: objective idx + 범위
            aops, aope = [], []
            osnd, oa, ob, oc = [], [], [], []
            snd_key = {}
            snd_ids, snd_cats = [], []
            obj_key = {}
            obj_names = []
            tag_key = {}
            tag_names = []
            def _oid(o):
                if o not in obj_key:
                    obj_key[o] = len(obj_names); obj_names.append(o)
                return obj_key[o]
            def _tid2(x):
                if x not in tag_key:
                    tag_key[x] = len(tag_names); tag_names.append(x)
                return tag_key[x]
            for t in terminals[:n_abs]:
                pes.append(len(gs_a))
                for (guards, ops, _child) in abs_set[t]:
                    gs_a.append(len(gobj))
                    for (go, lo2, hi2) in guards:
                        gobj.append(_oid(go)); glo.append(lo2); ghi.append(hi2)
                    ge_a.append(len(gobj))
                    aops.append(len(osnd))
                    for op in ops:
                        if op[0] == 'note':
                            _k, snd, cat, vol, pitch, minvol = op
                            key = (snd, cat)
                            if key not in snd_key:
                                snd_key[key] = len(snd_ids)
                                snd_ids.append(snd); snd_cats.append(cat)
                            osnd.append(snd_key[key]); oa.append(_fbits(pitch))
                            ob.append(_fbits(vol)); oc.append(_fbits(minvol))
                        elif op[0] == 'set':
                            osnd.append(-1); oa.append(op[2]); ob.append(_oid(op[1])); oc.append(0)
                        elif op[0] == 'add':
                            osnd.append(-2); oa.append(op[2]); ob.append(_oid(op[1])); oc.append(0)
                        elif op[0] == 'tagadd':
                            osnd.append(-3); oa.append(_tid2(op[1])); ob.append(0); oc.append(0)
                        else:   # tagrm
                            osnd.append(-4); oa.append(_tid2(op[1])); ob.append(0); oc.append(0)
                    aope.append(len(osnd))
                pee.append(len(gs_a))
            _sid = ", ".join(f'"{x}"' for x in snd_ids)
            _sct = ", ".join(f'"{x}"' for x in snd_cats)
            abs_code = f"""
    // ── [가드 흡수 — 범용] 스코어 가드 터미널 + 상수 리프를 데이터 테이블로 ──
    // 원본: 가드 줄들(execute as @s[scores={{k1=..,k2=..,...}}] run function LEAF) +
    //       상수 리프(playsound@s / scoreboard set|add|remove @s / tag @s add|remove).
    //       줄 순서 그대로 해석 실행. 가드 objective 는 엔트리(원본 줄) 평가 시점마다 재읽기 —
    //       선행 리프의 쓰기가 후행 가드에 관측되는 원본 시맨틱 그대로.
    private static final int[] PES = KfcGen.decodeInts("{_encode_ints(pes)}");
    private static final int[] PEE = KfcGen.decodeInts("{_encode_ints(pee)}");
    private static final int[] GS = KfcGen.decodeInts("{_encode_ints(gs_a)}");
    private static final int[] GE = KfcGen.decodeInts("{_encode_ints(ge_a)}");
    private static final int[] GOBJ = KfcGen.decodeInts("{_encode_ints(gobj)}");
    private static final int[] GLO = KfcGen.decodeInts("{_encode_ints(glo)}");
    private static final int[] GHI = KfcGen.decodeInts("{_encode_ints(ghi)}");
    private static final int[] AOPS = KfcGen.decodeInts("{_encode_ints(aops)}");
    private static final int[] AOPE = KfcGen.decodeInts("{_encode_ints(aope)}");
    private static final String[] OBJ_NAMES = {{ {", ".join(f'"{x}"' for x in obj_names)} }};
    private static final String[] TAG_NAMES = {{ {", ".join(f'"{x}"' for x in tag_names)} }};
    private static final int[] OSND = KfcGen.decodeInts("{_encode_ints(osnd)}");
    private static final int[] OA = KfcGen.decodeInts("{_encode_ints(oa)}");
    private static final int[] OB = KfcGen.decodeInts("{_encode_ints(ob)}");
    private static final int[] OC = KfcGen.decodeInts("{_encode_ints(oc)}");
    private static final String[] SND_ID = {{ {_sid} }};
    private static final String[] SND_CAT = {{ {_sct} }};
    private static net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent>[] SNDS;
    private static net.minecraft.sound.SoundCategory[] SCATS;

    @SuppressWarnings("unchecked")
    private static void initSnds() {{
        net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent>[] a =
                new net.minecraft.registry.entry.RegistryEntry[SND_ID.length];
        net.minecraft.sound.SoundCategory[] b = new net.minecraft.sound.SoundCategory[SND_ID.length];
        for (int i = 0; i < SND_ID.length; i++) {{ a[i] = KfcGen.soundEvent(SND_ID[i]); b[i] = KfcGen.soundCat(SND_CAT[i]); }}
        SNDS = a; SCATS = b;
    }}

    private static void absTerm(int t, ServerCommandSource source,
                                net.minecraft.entity.Entity e, ServerScoreboard sb, int s) {{
        boolean inited = false;
        net.minecraft.server.network.ServerPlayerEntity ps =
                (e instanceof net.minecraft.server.network.ServerPlayerEntity _p) ? _p : null;
        for (int pe = PES[t]; pe < PEE[t]; pe++) {{
            boolean pass = true;
            for (int g = GS[pe]; g < GE[pe]; g++) {{   // 가드 objective 는 평가 시점 재읽기(원본 동일)
                Integer gv = KfcGen.readScoreEnt(sb, e, OBJ_NAMES[GOBJ[g]]);
                if (gv == null || gv < GLO[g] || gv > GHI[g]) {{ pass = false; break; }}
            }}
            if (!pass) continue;
            if (!inited) {{ if (SNDS == null) initSnds(); inited = true; }}
            for (int i = AOPS[pe]; i < AOPE[pe]; i++) {{
                int sd = OSND[i];
                if (sd >= 0) {{
                    if (ps != null) KfcGen.playSound(ps, SNDS[sd], SCATS[sd], source.getPosition(),
                            Float.intBitsToFloat(OB[i]), Float.intBitsToFloat(OA[i]), Float.intBitsToFloat(OC[i]));
                }} else if (sd == -1) {{
                    KfcGen.setScore(sb, e, OBJ_NAMES[OB[i]], OA[i]);
                }} else if (sd == -2) {{
                    KfcGen.addScore(sb, e, OBJ_NAMES[OB[i]], OA[i]);
                }} else if (sd == -3) {{
                    KfcGen.addTag(e, TAG_NAMES[OA[i]]);
                }} else {{
                    KfcGen.removeTag(e, TAG_NAMES[OA[i]]);
                }}
            }}
        }}
    }}
"""

        # ── 터미널 스위치(세그먼트) ──
        term_methods = []
        n_seg = (len(terminals) + TERM_SWITCH_SEG - 1) // TERM_SWITCH_SEG
        for si in range(n_seg):
            cases = []
            for t in range(si * TERM_SWITCH_SEG, min((si + 1) * TERM_SWITCH_SEG, len(terminals))):
                if t < n_abs:
                    continue          # [NBS] 흡수 터미널 — absTerm 이 처리(케이스 불필요)
                ch = terminals[t]
                tc = by_fid.get(ch)
                fq = tc.fqcn if tc is not None else fid_to_fqcn(ch, group)
                cases.append(f"            case {t}: {fq}.executeReturn(source); return;")
            term_methods.append(
                f"    private static void term{si}(int t, ServerCommandSource source) {{\n"
                f"        switch (t) {{\n" + "\n".join(cases) + "\n        }\n    }")
        _sig = ("    private static void term(int t, ServerCommandSource source, "
                "net.minecraft.entity.Entity e, ServerScoreboard sb, int s) {\n")
        _abs_br = (f"        if (t < {n_abs}) {{ absTerm(t, source, e, sb, s); return; }}\n"
                   if n_abs else "")
        if n_seg <= 1:
            term_disp = (_sig + _abs_br + "        term0(t, source);\n    }")
        else:
            branches = []
            for si in range(n_seg):
                cond = f"t < {(si + 1) * TERM_SWITCH_SEG}" if si < n_seg - 1 else None
                call = f"term{si}(t, source)"
                branches.append(f"        if ({cond}) {{ {call}; return; }}" if cond
                                else f"        {call};")
            term_disp = (_sig + _abs_br + "\n".join(branches) + "\n    }")

        # ── 엔트리 run 메서드 ──
        run_methods = []
        entry_run = {}
        for k, f in enumerate(sorted(entries)):
            entry_run[f] = k
            run_methods.append(
                f"    /** entry: `{f}` */\n"
                f"    public static void run{k}(ServerCommandSource source) {{ walk(source, {nid[f]}); }}")

        code = f"""package {group}.generated;

import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.command.ServerCommandSource;

/** Auto-flattened score-dispatch tree — objective `{obj}`,
 *  {len(comp)} nodes / {len(lo_arr)} edges / {len(terminals)} terminals.
 *  원본 함수 트리의 방문 순서·재읽기 시맨틱을 정확히 재현하는 반복 워커(무결성 논증은
 *  tree_flatten.py 헤더 참조). 터미널 호출은 bucketize 가 버킷 메서드로 재작성한다. */
public final class {cls_name} {{
    private {cls_name}() {{ throw new UnsupportedOperationException(); }}

    private static final int[] LO = KfcGen.decodeInts("{_encode_ints(lo_arr)}");
    private static final int[] HI = KfcGen.decodeInts("{_encode_ints(hi_arr)}");
    private static final int[] TGT = KfcGen.decodeInts("{_encode_ints(tgt_arr)}");
    private static final int[] ES = KfcGen.decodeInts("{_encode_ints(es_arr)}");
    private static final int[] EE = KfcGen.decodeInts("{_encode_ints(ee_arr)}");
{_xdecl}    private static final int MAX_STACK = {max_stack};
{abs_code}
{chr(10).join(run_methods)}

    private static void walk(ServerCommandSource source, int node) {{
        net.minecraft.entity.Entity e = source.getEntity();
        if (e == null) return;
        ServerScoreboard sb = KfcGen.getOrCreateContext(source).scoreboard;
        Integer s0 = KfcGen.readScoreEnt(sb, e, "{obj}");
        if (s0 == null) return;
        int s = s0;
        int[] st = new int[MAX_STACK];
        int sp = 0;
        for (int i = EE[node] - 1; i >= ES[node]; i--) st[sp++] = i;
        while (sp > 0) {{
            int ed = st[--sp];
            if (s < LO[ed] || s > HI[ed]) continue;
{_xgate}            int tg = TGT[ed];
            if (tg >= 0) {{
                for (int i = EE[tg] - 1; i >= ES[tg]; i--) st[sp++] = i;
            }} else {{
                term(-tg - 1, source, e, sb, s);
                // 터미널만이 OBJ 를 변경할 수 있다 — 원본의 노드별 재읽기와 동일 관측.
                Integer r = KfcGen.readScoreEnt(sb, e, "{obj}");
                if (r == null) return;
                s = r;
            }}
        }}
    }}

{term_disp}

{chr(10).join(term_methods)}
}}
"""
        _wic(gen_dir / f"{cls_name}.java", code)

        # ── 엔트리 레코드 교체 ──
        for f in entries:
            c = by_fid[f]
            new_text = f"""package {c.package};

import net.minecraft.server.command.ServerCommandSource;

/** Auto-generated from datapack function `{f}`.
 *  [tree-flatten] 순수 스코어-디스패치 서브트리 → {cls_name}.run{entry_run[f]} 테이블 워커. */
public final class {c.cls} {{
    private {c.cls}() {{ throw new UnsupportedOperationException(); }}

    public static void execute(ServerCommandSource source) {{
        executeReturn(source);
    }}

    public static int executeReturn(ServerCommandSource source) {{
        {tree_fqcn}.run{entry_run[f]}(source);
        return 0;
    }}
}}
"""
            c.text = new_text
            c.size = len(new_text.encode("utf-8"))

        # ── 내부 노드 제거 대상 표시 ──
        for f in comp:
            if f not in entries and f in by_fid:
                removed_fids.add(f)

        # [NBS] 흡수 쌍노드 레코드 제거(외부 미참조) + notes 자식 추적
        for t in terminals[:n_abs]:
            for ent in abs_set[t]:
                nbs_abs_children.setdefault(ent[2], []).append(t)
            if t not in ext_ref and t in by_fid:
                removed_fids.add(t)

        stats["clusters"] += 1
        stats["nodes"] += len(comp)
        stats["entries"] += len(entries)
        if verbose:
            print(f"  [tree-flatten] {obj}: {len(comp)} nodes → {cls_name} "
                  f"({len(terminals)} terminals, {n_abs} absorbed, {len(entries)} entries)")

    # [NBS] notes 레코드 제거 — 모든 쌍노드 부모가 제거됐고 외부 미참조인 경우만(fail-closed)
    for child, parents in nbs_abs_children.items():
        if child in ext_ref or child not in by_fid:
            continue
        if all(p in removed_fids for p in parents):
            if not by_fid[child].is_macro:
                removed_fids.add(child)

    # records 제자리 축소
    if removed_fids:
        kept = [c for c in records if getattr(c, "fid", None) not in removed_fids]
        records.clear()
        records.extend(kept)
        stats["removed"] = len(removed_fids)

    return stats
