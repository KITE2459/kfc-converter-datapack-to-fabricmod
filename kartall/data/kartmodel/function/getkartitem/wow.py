#!/usr/bin/env python3
"""카트 mcfunction 파일의 수치를 일괄 조정하는 스크립트."""

import argparse
import json5
import re
from pathlib import Path
from typing import Dict, Optional

CATEGORY_PREFIXES = (
    ("getcommonkart", "일반"),
    ("getrarekart", "레어"),
    ("getlegendkart", "레전드"),
    ("getuniquekart", "유니크"),
    ("getspecialkart", "스페셜"),
    ("getseniorkart", "클래식"),
)

SINGLE_QUOTE_OBJECT = re.compile(r":'(\{[^']*\})'")
BOOL_FIX = re.compile(r'\b(True|False)\b')

def resolve_category(stem: str) -> Optional[str]:
    """파일명 접두사로 카테고리를 판별한다."""
    for prefix, label in CATEGORY_PREFIXES:
        if stem.startswith(prefix):
            return label
    return None

def load_payload(payload: str) -> Dict[str, object]:
    """NBT 유사 포맷을 JSON으로 변환한 뒤 파싱한다."""
    payload = SINGLE_QUOTE_OBJECT.sub(r":\1", payload)
    payload = BOOL_FIX.sub(lambda m: m.group(1).lower(), payload)
    # 따옴표 없는 문자열 값 처리 (숫자, 불리언 제외)
    # 예: item:sabera2 -> item:"sabera2"
    payload = re.sub(r'(?<=:)\s*([a-zA-Z_][a-zA-Z0-9_\-\.]*)(?=[,}])', r'"\1"', payload)
    return json5.loads(payload)

def to_nbt_string(data):
    """Dict를 NBT 유사 문자열로 변환한다."""
    def value_to_str(v):
        if isinstance(v, dict):
            return '{' + ', '.join(f'{k}:{value_to_str(val)}' for k, val in v.items()) + '}'
        elif isinstance(v, str):
            return f'"{v}"'
        elif isinstance(v, bool):
            return str(v).lower()
        else:
            return str(v)
    return '{' + ', '.join(f'{k}:{value_to_str(v)}' for k, v in data.items()) + '}'

def adjust_stats(payload: str, stat: str, delta: int) -> str:
    """주어진 payload에서 특정 stat을 delta만큼 조정한다."""
    data = load_payload(payload)
    if stat in data and isinstance(data[stat], int):
        data[stat] = data[stat] + delta
    # 다시 문자열로 변환
    return to_nbt_string(data)

def process_file(file_path: Path, stat: str, delta: int):
    """파일을 처리하여 stat을 조정한다."""
    with file_path.open('r', encoding='utf-8') as f:
        lines = f.readlines()
    
    new_lines = []
    for line in lines:
        line = line.strip()
        if line.startswith('function kartmain:makekart {'):
            # payload 추출
            start = line.find('{')
            end = line.rfind('}')
            if start != -1 and end != -1:
                payload = line[start:end+1]
                adjusted_payload = adjust_stats(payload, stat, delta)
                new_line = line[:start] + adjusted_payload + line[end+1:]
                new_lines.append(new_line)
            else:
                new_lines.append(line)
        else:
            new_lines.append(line)
    
    with file_path.open('w', encoding='utf-8') as f:
        f.write('\n'.join(new_lines) + '\n')

def main():
    parser = argparse.ArgumentParser(description="카트 수치를 일괄 조정합니다.")
    parser.add_argument('--folder', type=str, default='karts', help='카트 파일이 있는 폴더 (기본: karts)')
    parser.add_argument('--stat', type=str, required=True, choices=['speed', 'accel', 'boost', 'corner', 'drift', 'gauge', 'boosttime', 'maxboostcount', 'defense', 'size', 'draft'], help='조정할 수치')
    parser.add_argument('--delta', type=int, required=True, help='조정 값 (양수: 증가, 음수: 감소)')
    parser.add_argument('--category', type=str, default='전체', choices=['전체'] + [label for _, label in CATEGORY_PREFIXES], help='적용할 등급 (기본: 전체)')
    
    args = parser.parse_args()
    
    folder_path = Path(args.folder)
    if not folder_path.exists() or not folder_path.is_dir():
        print(f"폴더가 존재하지 않습니다: {folder_path}")
        return
    
    stat = args.stat
    delta = args.delta
    category = args.category
    
    print(f"폴더: {folder_path}")
    print(f"수치: {stat}")
    print(f"변화: {delta}")
    print(f"등급: {category}")
    
    for file_path in folder_path.glob('*.mcfunction'):
        if file_path.is_file():
            file_category = resolve_category(file_path.stem)
            if category == '전체' or file_category == category:
                print(f"처리 중: {file_path.name}")
                process_file(file_path, stat, delta)
    
    print("완료되었습니다.")

if __name__ == '__main__':
    main()