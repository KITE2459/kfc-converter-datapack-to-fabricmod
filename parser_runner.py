#!/usr/bin/env python3
"""
parser_runner.py - 헤드리스 파서(HeadlessParser.java)를 JVM subprocess 로 실행한다.

기존 파이프라인은 ParseDumper 를 gradle runServer 로 띄워(=게임 부팅) lines.json->trees.json
을 만들었다. 이 모듈은 그 단계를 '게임 부팅 없는 단일 JVM 호출'로 대체해, convert.py 가
extract->parse->generate 를 한 번에 잇도록 한다.

클래스패스 해소 우선순위:
  1) BuildProfile.parser_classpath 가 명시돼 있으면 그대로 사용.
  2) 아니면 gradle 로 런타임 클래스패스를 추출(loom 프로젝트가 있을 때).
  3) 둘 다 없으면 명확한 에러로 안내(사용자가 --parser-cp 로 지정).

파서 클래스(HeadlessParser)는 사전 컴파일된 클래스 디렉터리(혹은 jar)로 클래스패스에 포함된다.
"""
from __future__ import annotations

import os
import sys
import shutil
import subprocess
from pathlib import Path

from build_config import BuildProfile


def _java_exe() -> str:
    jh = os.environ.get("JAVA_HOME")
    if jh:
        cand = Path(jh) / "bin" / ("java.exe" if os.name == "nt" else "java")
        if cand.exists():
            return str(cand)
    return "java"


def _resolve_classpath(profile: BuildProfile, project_dir: str | None,
                       extra_cp: list[str]) -> list[str]:
    cp: list[str] = list(profile.parser_classpath)
    # extra_cp 항목이 pathsep(:, ;) 으로 묶인 문자열일 수 있으므로 분해해 평탄화.
    for item in extra_cp:
        cp += [c for c in item.split(os.pathsep) if c]

    if not profile.parser_classpath and project_dir:
        # gradle 로 런타임 클래스패스 추출 (loom 프로젝트 가정).
        # build.gradle 에 아래 task 가 있으면 한 줄로 경로를 뱉는다:
        #   tasks.register('printRuntimeClasspath') {
        #     doLast { println sourceSets.main.runtimeClasspath.asPath }
        #   }
        gradlew = Path(project_dir) / ("gradlew.bat" if os.name == "nt" else "gradlew")
        if gradlew.exists():
            try:
                out = subprocess.run(
                    [str(gradlew), "-q", "printRuntimeClasspath"],
                    cwd=project_dir, capture_output=True, text=True, timeout=600)
                line = [l for l in out.stdout.splitlines() if os.pathsep in l or l.endswith(".jar")]
                if line:
                    cp += line[-1].strip().split(os.pathsep)
            except Exception as e:
                print(f"[parser] failed to extract gradle classpath: {e}", file=sys.stderr)
    return [c for c in cp if c]


def run_headless_parse(lines_json: str, trees_json: str,
                       profile: BuildProfile,
                       parser_classes: str,
                       project_dir: str | None = None,
                       extra_classpath: list[str] | None = None) -> None:
    """lines.json -> trees.json 을 헤드리스 파서로 변환.

    parser_classes: HeadlessParser.class 가 있는 디렉터리 또는 jar.
    """
    cp = _resolve_classpath(profile, project_dir, extra_classpath or [])
    # parser_classes 도 pathsep 묶음일 수 있으므로 분해해 앞에 삽입.
    pc_items = [c for c in parser_classes.split(os.pathsep) if c]
    cp = pc_items + cp
    if len(cp) <= 1:
        raise RuntimeError(
            "could not resolve parser classpath. one of the following is required:\n"
            "  - set MC/Fabric runtime jar paths in BuildProfile.parser_classpath, or\n"
            "  - --project <loom project dir> (with a printRuntimeClasspath task), or\n"
            "  - --parser-cp <path(s)> to specify directly")

    cp_str = os.pathsep.join(cp)
    cmd = [_java_exe(), *profile.jvm_args, "-cp", cp_str,
           profile.parser_main, lines_json, trees_json]
    print(f"[parser] headless parse: {len(cp)} classpath entries, "
          f"MC {profile.minecraft_version} / Fabric {profile.fabric_loader_version}")
    r = subprocess.run(cmd)
    if r.returncode != 0:
        raise RuntimeError(f"headless parser failed (exit {r.returncode})")


def compile_parser(parser_src: str, out_dir: str,
                   profile: BuildProfile, classpath: list[str]) -> str:
    """HeadlessParser.java 를 컴파일해 out_dir 에 클래스 생성. out_dir 반환."""
    Path(out_dir).mkdir(parents=True, exist_ok=True)
    # classpath 항목이 pathsep 묶음 문자열일 수 있으므로 평탄화.
    flat_cp = []
    for item in classpath:
        flat_cp += [c for c in item.split(os.pathsep) if c]
    classpath = flat_cp
    javac = "javac"
    jh = os.environ.get("JAVA_HOME")
    if jh:
        cand = Path(jh) / "bin" / ("javac.exe" if os.name == "nt" else "javac")
        if cand.exists():
            javac = str(cand)
    cmd = [javac, "--release", profile.java_release,
           "-cp", os.pathsep.join(classpath), "-d", out_dir, parser_src]
    r = subprocess.run(cmd, capture_output=True, text=True)
    if r.returncode != 0:
        raise RuntimeError(f"parser compile failed:\n{r.stderr}")
    return out_dir