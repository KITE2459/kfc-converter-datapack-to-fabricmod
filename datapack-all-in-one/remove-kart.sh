#!/bin/bash
# 스크립트(루트)가 위치한 디렉터리를 기준으로 datapacks 경로 자동 설정
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DATAPACKS_DIR="$SCRIPT_DIR/datapacks"
# rm -rf "$DATAPACKS_DIR"/
rm -rf "$DATAPACKS_DIR"/.git
rm -rf "$DATAPACKS_DIR"/aaadevrecorder*
rm -rf "$DATAPACKS_DIR"/bgm-old
rm -rf "$DATAPACKS_DIR"/tools
rm -rf "$DATAPACKS_DIR"/auto_push.sh
rm -rf "$DATAPACKS_DIR"/*.py
rm -rf "$DATAPACKS_DIR"/*.json
rm -rf "$DATAPACKS_DIR"/*.txt
rm -rf "$DATAPACKS_DIR"/*.md