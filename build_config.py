#!/usr/bin/env python3
"""
build_config.py — 통합 빌드 파이프라인의 환경 변수(마인크래프트/Fabric 버전 등)를 한 곳에 모은다.

확장 설계 의도:
  변환기는 두 개의 큰 외부 변수에 묶인다 — (1) 마인크래프트 버전, (2) Fabric 로더/API 버전.
  이 둘이 바뀌면 ① 헤드리스 파서가 링크할 MC/Fabric jar 와 ② 생성 코드가 의존하는 API 시그니처가
  달라진다. 그 변동 지점을 이 한 파일로 격리해, 새 버전 지원 시 여기(또는 프로파일)만 갈아끼우면
  되도록 한다. 코어 로직(emit/assemble/convert)은 버전 무관하게 유지.

현재 기본 프로파일: MC 1.21.5 / Fabric Loader 0.18.4.
"""
from __future__ import annotations

import os
import json
from dataclasses import dataclass, field, asdict
from pathlib import Path


@dataclass
class BuildProfile:
    """하나의 (MC, Fabric) 조합에 대한 빌드 환경."""
    name: str = "mc1.21.5-fabric0.18.4"
    minecraft_version: str = "1.21.5"
    fabric_loader_version: str = "0.18.4"
    yarn_mappings: str = "1.21.5+build.1"      # 파서/생성 코드가 쓰는 매핑(yarn)
    fabric_api_version: str = ""               # 비우면 loom 이 호환 버전 자동 선택

    # 헤드리스 파서 실행에 필요한 클래스패스(런타임 jar 들).
    #   - 비워두면 convert.py 가 gradle 로 자동 해소(권장) 하거나,
    #   - 명시하면 그 경로들을 직접 사용(오프라인/고정 환경).
    parser_classpath: list[str] = field(default_factory=list)
    parser_main: str = "datapackconvert.parser.HeadlessParser"

    # JVM 옵션 (대용량 데이터팩 파싱용 힙 등).
    jvm_args: list[str] = field(default_factory=lambda: ["-Xmx4G"])

    # 생성 코드가 타깃하는 자바 버전(Loom/MC 요구치에 맞춤).
    java_release: str = "21"

    def to_json(self) -> str:
        return json.dumps(asdict(self), ensure_ascii=False, indent=2)

    @staticmethod
    def from_file(path: str | os.PathLike) -> "BuildProfile":
        data = json.loads(Path(path).read_text(encoding="utf-8"))
        return BuildProfile(**data)


# 기본 프로파일 (현 타깃)
DEFAULT_PROFILE = BuildProfile()


# 추가 프로파일을 여기에 등록하면 --profile <name> 으로 선택 가능.
# 예) "mc1.21.1-fabric0.16.0": BuildProfile(name=..., minecraft_version="1.21.1", ...)
PROFILES: dict[str, BuildProfile] = {
    DEFAULT_PROFILE.name: DEFAULT_PROFILE,
}


def get_profile(name: str | None = None,
                profile_file: str | None = None) -> BuildProfile:
    """프로파일 해소: 파일 경로가 주어지면 그걸, 이름이 등록돼 있으면 그걸, 아니면 기본."""
    if profile_file:
        return BuildProfile.from_file(profile_file)
    if name and name in PROFILES:
        return PROFILES[name]
    if name:
        raise KeyError(f"unknown profile: {name} (available: {list(PROFILES)})")
    return DEFAULT_PROFILE