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
             force_bridge_prefixes=(), trace_prefixes=()):
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


def _mp_convert_raw(item):
    """워커(raw 입력): (fid, [raw_line,...]) -> (..., called). json.loads 를 워커에서 수행."""
    import json as _json
    from assemble import function_to_class as _f2c
    fid, raws = item
    objs = [_json.loads(r) for r in raws]
    jc = _f2c(fid, objs, group=_W_GROUP)
    return (fid, jc.package, jc.cls, jc.code,
            jc.native_lines, jc.gated_lines, jc.bridge_lines, jc.fully_converted,
            _collect_called(objs))


def _mp_convert_local_raw(item, group):
    """단일 프로세스 raw 변환(_mp_convert_raw 와 동일 반환)."""
    import json as _json
    from assemble import function_to_class as _f2c
    fid, raws = item
    objs = [_json.loads(r) for r in raws]
    jc = _f2c(fid, objs, group=group)
    return (fid, jc.package, jc.cls, jc.code,
            jc.native_lines, jc.gated_lines, jc.bridge_lines, jc.fully_converted,
            _collect_called(objs))


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
    # 데이터팩 입력을 한 번 열어(디렉터리/zip 투명) 모든 로더-리소스 복사에서 재사용.
    dp_src = open_datapack(datapack_root) if datapack_root else None
    if dp_src is not None:
        emit.load_entity_type_tags(dp_src)
        emit.load_block_tags(dp_src)
        emit.load_predicates(dp_src)

    out_root = Path(out_dir)
    # 이전 변환의 잔재 .java/리소스가 남아 빌드를 깨는 것을 막는다(예: 삭제/改名된 함수의 옛 클래스).
    # 단, 생성물인 out/src 만 비우고 빌드환경(build.gradle/gradle.properties/wrapper)·캐시
    # (.gradle/build)·개발월드(run)·사용자 수정은 보존한다. (--no-clean 으로 끌 수 있음)
    if clean:
        import shutil as _sh
        _src = out_root / "src"
        if _src.exists():
            try:
                _sh.rmtree(_src)
                print(f"[generate] cleaned previous source tree: {_src} (build env/caches kept)")
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
            _records.append(_mp_mod.Cls(None, code))
        else:
            pkg_dir = src_root / Path(*pkg.split("."))
            pkg_dir.mkdir(parents=True, exist_ok=True)
            (pkg_dir / f"{cls}.java").write_text(code, encoding="utf-8")
        stats["native"] += nat
        stats["gated"] += gat
        stats["bridge"] += br
        fn_meta.append((fid, pkg, cls, nat, gat, br, full))
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
                                _force_prefixes, _trace_prefixes)) as pool:
            # chunksize 로 IPC 오버헤드 감소. 함수가 많을수록 크게.
            for res in pool.imap_unordered(_conv_worker, _items, chunksize=64):
                _write_result(res)
        _src = "explicit KFC_JOBS" if _jobs_env > 0 else "auto (max usable cores)"
        print(f"[generate] pass-2: parallel convert with {jobs} workers [{_src}]")

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
            f.write_text(jc.code, encoding="utf-8")
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
        (gen_dir / "KfcGen.java").write_text(kg, encoding="utf-8")
        print(f"[generate] KfcGen.java -> {group}.generated")
    else:
        print("[!]  KfcGen.java is not next to convert.py - manual placement needed")

    write_report(out_root, fn_meta, stats, group)

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
    tags = load_tags(trees_path)
    write_entrypoint(src_root, group, tags, generated_fids)
    write_resources(out_root, group, tags, dp_src)

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
                        p.write_text(c.text, encoding="utf-8")

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
        tick_calls.append(f"            {fid_to_fqcn(fid, group)}.execute(src);")
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
        }});
    }}
}}
"""
    (src_root / Path(*group.split("."))).mkdir(parents=True, exist_ok=True)
    (src_root / Path(*group.split(".")) / "ModEntry.java").write_text(code, encoding="utf-8")
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
        copied = skipped_ticks = 0
        _seen_dirs = set()   # 생성한 부모 디렉터리 캐시 — 188k 파일에서 mkdir 중복 syscall 제거
        # data/ 이하 전 파일을 메모리에서 읽어 그대로 기록 (zip 도 디스크 전개 없이).
        for rel, blob in dp_src.iter_files(under="data"):
            relposix = rel[len("data/"):]   # data/ 제거한 내부 상대경로
            # tick 함수 태그만 제외 (자바 진입점이 매 틱 담당 -> 중복 구동 방지).
            # load 태그는 데이터팩에 보존 - 바닐라 함수 매니저가 월드 로드/리로드 시점에
            # 정확한 시맨틱(스폰청크 로드 후, /reload 포함)으로 실행한다.
            if relposix in ("minecraft/tags/function/tick.json",
                            "minecraft/tags/functions/tick.json"):
                skipped_ticks += 1
                continue
            dst = dst_data / relposix
            parent = dst.parent
            if parent not in _seen_dirs:        # 같은 디렉터리는 한 번만 mkdir
                parent.mkdir(parents=True, exist_ok=True)
                _seen_dirs.add(parent)
            dst.write_bytes(blob)
            copied += 1
        # pack.mcmeta 도 복사 (있으면)
        pm = dp_src.pack_meta_bytes()
        if pm is not None:
            (res / "pack.mcmeta").write_bytes(pm)
        print(f"[generate] copied datapack resources: {copied} files (excluded {skipped_ticks} tick/load tags - replaced by entrypoint)")
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