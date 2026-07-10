#!/usr/bin/env python3
"""
datapack_io.py — 데이터팩 입력을 '디렉터리'와 'zip' 양쪽으로 투명하게 읽는 얇은 추상화.

목적: convert.py / emit.py 가 데이터팩을 읽는 모든 지점(함수 수집, 태그/predicate 로딩,
리소스 복사)을 단일 인터페이스로 통일해, 입력이 풀린 폴더든 .zip 이든 동일하게 동작.
zip 은 압축 해제 없이 zipfile 로 메모리에서 직접 읽는다(임시 디렉터리/디스크 전개 없음).

공통 인터페이스 (DirSource / ZipSource 둘 다 제공):
    .exists()                      → 데이터팩으로서 유효한가 (data/ 존재 등)
    .glob(pattern)                 → glob 패턴에 맞는 내부 상대경로 문자열 리스트
                                     (항상 '/' 구분, 예: "data/kartmain/function/loop.mcfunction")
    .read_text(rel, encoding, ...) → 해당 상대경로 텍스트
    .read_bytes(rel)               → 해당 상대경로 바이트
    .iter_files(under=None)        → (rel, bytes) 제너레이터 (리소스 복사용)
    .pack_meta_bytes()             → pack.mcmeta 바이트 또는 None

glob 패턴은 pathlib 의 글롭과 동일 의미(`*`, `**`, `?`)를 지원하되, 입력·출력 모두
POSIX('/') 경로 문자열로 정규화한다. 디렉터리 소스는 내부적으로 pathlib glob 을 쓰고,
zip 소스는 미리 읽어둔 엔트리 목록에 fnmatch 로 매칭한다.
"""
from __future__ import annotations

import io
import os
import re
import json
import fnmatch
import zipfile
from pathlib import Path, PurePosixPath


def _glob_to_regex(pattern: str) -> re.Pattern:
    """glob 패턴(`*` `**` `?`)을 POSIX 경로용 정규식으로. `**` 는 0개 이상 세그먼트."""
    # 토큰화하면서 정규식 조립 (fnmatch.translate 는 ** 를 제대로 다루지 못함)
    i, n = 0, len(pattern)
    out = ["^"]
    while i < n:
        c = pattern[i]
        if c == "*":
            if i + 1 < n and pattern[i + 1] == "*":
                # ** : 임의 깊이 (구분자 포함). 뒤따르는 '/' 흡수해서 0세그먼트도 허용.
                i += 2
                if i < n and pattern[i] == "/":
                    i += 1
                    out.append("(?:.*/)?")
                else:
                    out.append(".*")
                continue
            else:
                out.append("[^/]*")
        elif c == "?":
            out.append("[^/]")
        elif c == "/":
            out.append("/")
        else:
            out.append(re.escape(c))
        i += 1
    out.append("$")
    return re.compile("".join(out))


class DirSource:
    """풀린 디렉터리 형태의 데이터팩."""
    kind = "dir"

    def __init__(self, root: str | os.PathLike):
        self.root = Path(root)

    # ── 인터페이스 ──
    def exists(self) -> bool:
        return self.root.exists() and (self.root / "data").exists()

    def glob(self, pattern: str) -> list[str]:
        return sorted(
            p.relative_to(self.root).as_posix()
            for p in self.root.glob(pattern)
            if p.is_file()
        )

    def read_text(self, rel: str, encoding: str = "utf-8", errors: str = "strict") -> str:
        return (self.root / rel).read_text(encoding=encoding, errors=errors)

    def read_bytes(self, rel: str) -> bytes:
        return (self.root / rel).read_bytes()

    def iter_files(self, under: str | None = None):
        base = self.root if under is None else (self.root / under)
        if not base.exists():
            return
        for p in base.rglob("*"):
            if p.is_file():
                yield p.relative_to(self.root).as_posix(), p.read_bytes()

    def iter_stat(self, under: str | None = None):
        """(rel, abs_path, size, mtime_ns) — 내용 read 없이 stat 만. (증분 리소스 복사용)"""
        base = self.root if under is None else (self.root / under)
        if not base.exists():
            return
        for p in base.rglob("*"):
            if p.is_file():
                st = p.stat()
                yield p.relative_to(self.root).as_posix(), p, st.st_size, st.st_mtime_ns

    def pack_meta_bytes(self) -> bytes | None:
        pm = self.root / "pack.mcmeta"
        return pm.read_bytes() if pm.exists() else None


class ZipSource:
    """zip 으로 압축된 데이터팩 — 메모리에서 직접 읽는다(디스크 전개 없음).

    데이터팩이 zip 루트에 바로(`data/...`) 들어있을 수도, 한 단계 폴더 안에
    (`MyPack/data/...`) 들어있을 수도 있다. 'data/' 가 처음 등장하는 공통 prefix 를
    자동 감지해 내부 경로를 그 prefix 기준으로 정규화한다.
    """
    kind = "zip"

    def __init__(self, zip_path: str | os.PathLike):
        self.zip_path = Path(zip_path)
        # zip 파일을 경로로 직접 연다(전체를 메모리에 올리지 않음 — 100MB+ 대용량 대응).
        # zipfile 은 central directory 만 읽고, 각 엔트리는 read() 시 지연 압축해제한다.
        self._zf = zipfile.ZipFile(self.zip_path, "r")
        names = [n for n in self._zf.namelist() if not n.endswith("/")]
        self._prefix = self._detect_prefix(names)
        pl = len(self._prefix)
        # 내부 상대경로(prefix 제거, POSIX) → 원본 zip 엔트리명
        self._map: dict[str, str] = {}
        for n in names:
            if self._prefix and not n.startswith(self._prefix):
                continue
            rel = n[pl:]
            if rel:
                self._map[rel] = n
        self._rels = sorted(self._map.keys())

    @staticmethod
    def _detect_prefix(names: list[str]) -> str:
        """'data/' 또는 'pack.mcmeta' 가 위치한 공통 상위 prefix 를 찾는다."""
        cands = set()
        for n in names:
            posix = n.replace("\\", "/")
            idx = posix.find("data/")
            if idx >= 0 and (idx == 0 or posix[idx - 1] == "/"):
                cands.add(posix[:idx])
        if not cands:
            # pack.mcmeta 위치로도 시도
            for n in names:
                posix = n.replace("\\", "/")
                if posix.endswith("pack.mcmeta"):
                    cands.add(posix[: -len("pack.mcmeta")])
        if not cands:
            return ""
        # 가장 짧은(최상위) prefix 선택
        return min(cands, key=len)

    # ── 인터페이스 ──
    def exists(self) -> bool:
        return any(r == "data" or r.startswith("data/") for r in self._rels)

    def glob(self, pattern: str) -> list[str]:
        rx = _glob_to_regex(pattern)
        return [r for r in self._rels if rx.match(r)]

    def read_text(self, rel: str, encoding: str = "utf-8", errors: str = "strict") -> str:
        return self.read_bytes(rel).decode(encoding, errors=errors)

    def read_bytes(self, rel: str) -> bytes:
        name = self._map.get(rel)
        if name is None:
            raise FileNotFoundError(rel)
        return self._zf.read(name)

    def iter_files(self, under: str | None = None):
        pre = "" if under is None else (under.rstrip("/") + "/")
        for rel in self._rels:
            if pre and not rel.startswith(pre):
                continue
            yield rel, self._zf.read(self._map[rel])

    def pack_meta_bytes(self) -> bytes | None:
        name = self._map.get("pack.mcmeta")
        return self._zf.read(name) if name else None


def open_datapack(path: str | os.PathLike):
    """입력 경로가 .zip 이면 ZipSource, 디렉터리면 DirSource 를 반환.

    파일이면서 zip 매직(PK) 이거나 확장자가 .zip 이면 zip 으로 취급한다.
    """
    p = Path(path)
    if p.is_file():
        if p.suffix.lower() == ".zip" or zipfile.is_zipfile(p):
            return ZipSource(p)
        raise ValueError(f"datapack input is a file but not a zip: {p}")
    return DirSource(p)