import base64
import json
import re
import os
import math
from pathlib import Path

VALID_TAGS = {
    'kart-boost-block',
    'kart-boost-instant',
    'kart-boost-fade',
    'kart-boost-flame',
    'kart-boost-move-start',
    'kart-boost-move-fast',
    'kart-boost-move-end',
    'kart-run-anime-first',
    'kart-run-anime-mid',
    'kart-run-anime-last',
    'drift-effect',
    'kart-special-ability'
}


def optimize_base64_value(base64_input):
    """
    Base64로 인코딩된 텍스처 데이터를 최적화
    SKIN URL만 추출하여 반환
    """
    try:
        decoded = base64.b64decode(base64_input).decode('utf-8')
        data = json.loads(decoded)

        optimized_data = {}
        if 'textures' in data and 'SKIN' in data['textures']:
            optimized_data['textures'] = {
                'SKIN': {
                    'url': data['textures']['SKIN'].get('url', '')
                }
            }

        optimized_json = json.dumps(optimized_data, separators=(',', ':'))
        optimized_base64 = base64.b64encode(optimized_json.encode('utf-8')).decode('utf-8')

        return optimized_base64

    except Exception as e:
        print(f"⚠️  Base64 값 최적화 실패: {e}")
        return base64_input


def round_float_value(match, mode='decimal'):
    """
    부동소수점 버림 (truncate)
    mode='decimal': 소수점 5자리 유지
    """
    value = match.group(1)
    suffix = match.group(2)

    try:
        float_val = float(value)

        if float_val == int(float_val) and 'e' not in value.lower():
            return match.group(0)

        scale = 10 ** 5
        truncated = math.trunc(float_val * scale) / scale

        result = f"{truncated:.5f}".rstrip('0').rstrip('.')
        return f"{result}{suffix}"

    except ValueError:
        return match.group(0)


def clean_tags(match):
    """
    Tags 블록에서 유효하지 않은 태그 제거
    유효한 태그가 하나도 없으면 Tags 블록 자체 제거
    """
    tags_content = match.group(1)
    tokens = re.findall(r'"([^"]+)"|([A-Za-z0-9_-]+)', tags_content)
    valid = [q or u for q, u in tokens if (q or u) in VALID_TAGS]
    if not valid:
        return ''
    formatted = ','.join(valid)
    return f',Tags:[{formatted}]'


def apply_additional_optimizations(content):
    """
    추가 최적화 규칙 적용
    1. ,item_display:"none" / "undefined" 제거
    2. ,Count:1 제거
    3. profile 내부의 id:[I;...], 제거
    4. 비어있거나 모든 값이 false인 Properties 제거
    5. minecraft: 네임스페이스 전체 제거
    6. 모든 부동소수점 소수점 5자리로 축소
    7. leading zero 제거 (0.xxx → .xxx)
    8. Tags 내 불필요한 태그 제거
    9~11. 큰 따옴표 제거
    """
    removed_count = {
        'item_display': 0,
        'count': 0,
        'profile_id': 0,
        'empty-properties': 0,
        'vanilla-namespace': 0,
        'float-precision': 0,
        'leading-zero': 0,
        'tags': 0,
        'quoted-string': 0
    }

    # 1. ,item_display:"none" / "undefined" 제거
    for pattern in [r',item_display:"none"', r',item_display:"undefined"']:
        matches = re.findall(pattern, content)
        removed_count['item_display'] += len(matches)
        content = re.sub(pattern, '', content)

    # 2. ,Count:1 제거
    pattern2 = r',Count:1'
    removed_count['count'] = len(re.findall(pattern2, content))
    content = re.sub(pattern2, '', content)

    # 3. profile 내부의 id:[I;...], 제거
    pattern3 = r'("minecraft:profile":\{)id:\[I;[^\]]+\],'
    removed_count['profile_id'] = len(re.findall(pattern3, content))
    content = re.sub(pattern3, r'\1', content)

    # 4. 비어있거나 모든 값이 false인 Properties 제거
    pattern4 = r',Properties:\{(?:[a-z]+:"false"(?:,[a-z]+:"false")*)?\}'
    removed_count['empty-properties'] = len(re.findall(pattern4, content))
    content = re.sub(pattern4, '', content)

    # 5. minecraft: 네임스페이스 전체 제거
    pattern5 = r'minecraft:'
    removed_count['vanilla-namespace'] = len(re.findall(pattern5, content))
    content = re.sub(pattern5, '', content)

    # 6. 모든 부동소수점 소수점 5자리로 축소 (소수점 6자리 이상인 값만 처리)
    before = content
    content = re.sub(r'(-?[0-9]+\.[0-9]{6,})(f)', round_float_value, content)
    removed_count['float-precision'] = sum(
        1 for a, b in zip(before.split('f'), content.split('f')) if a != b
    )

    # 7. leading zero 제거 (0.xxx → .xxx, -0.xxx → -.xxx)
    pattern7 = r'(?<![0-9])(-?)0(\.[0-9])'
    removed_count['leading-zero'] = len(re.findall(pattern7, content))
    content = re.sub(pattern7, r'\1\2', content)

    # 8. Tags 내 불필요한 태그 제거
    pattern8 = r',Tags:\[([^\]]*)\]'
    # kart-boost-move-end 태그가 존재하면 태그 작업 취소
    if re.search(r'kart-boost-move-end', content):
        print("   ⚠️  slide-optimizer를 먼저 실행해주세요 (태그 작업 취소)")
        removed_count['tags'] = 0
    elif re.search(r'kart-run-anime-last', content):
        print("   ⚠️  뛰라이더를 먼저 압축해주세요 (태그 작업 취소)")
        removed_count['tags'] = 0
    else:
        before_tags = content
        content = re.sub(pattern8, clean_tags, content)
        removed_count['tags'] = len(re.findall(pattern8, before_tags)) - len(re.findall(pattern8, content))


    # 9. 따옴표 제거 (value:"..." 형태는 제외)
    # 9. id:"값" / name:"값" / value:"값" → 따옴표 제거
    pattern9 = r'((?:id|name|value):)"([^"]*)"'
    removed_count['quoted-string'] = len(re.findall(pattern9, content))
    content = re.sub(pattern9, r'\1\2', content)

    # 10. value:~~~= 끝의 = 제거
    pattern10 = r'(value:[A-Za-z0-9+/]*)=+'
    removed_count['trailing-equals'] = len(re.findall(pattern10, content))
    content = re.sub(pattern10, r'\1', content)


    pattern11 = r'"(profile)"'
    removed_count['quoted-string'] = len(re.findall(pattern11, content))
    content = re.sub(pattern11, r'\1', content)

    return content, removed_count

def process_mcfunction_file(input_file, output_dir=None):
    """
    .mcfunction 파일을 읽어서 최적화하고 새 파일로 저장
    """
    with open(input_file, 'r', encoding='utf-8') as f:
        content = f.read()

    original_size = len(content.encode('utf-8'))
    print(f"📄 파일: {input_file}  ({original_size:,} bytes)")

    # Base64 최적화
    pattern = r'(value:")([A-Za-z0-9+/=]+)(")'
    matches = re.findall(pattern, content)
    print(f"🔍 발견된 Base64 값: {len(matches)}개")

    optimized_count = 0
    for prefix, base64_value, suffix in matches:
        optimized_value = optimize_base64_value(base64_value)
        if optimized_value != base64_value:
            content = content.replace(
                f'{prefix}{base64_value}{suffix}',
                f'{prefix}{optimized_value}{suffix}'
            )
            optimized_count += 1
            orig_sz = len(base64_value)
            opt_sz = len(optimized_value)
            reduction = ((orig_sz - opt_sz) / orig_sz) * 100
            print(f"   ✅ Base64: {orig_sz} → {opt_sz} bytes (-{reduction:.1f}%)")

    # 추가 최적화
    content, removed_count = apply_additional_optimizations(content)

    # 결과 출력
    if removed_count['item_display'] > 0:
        print(f"   ✅ item_display 제거: {removed_count['item_display']}개")
    if removed_count['count'] > 0:
        print(f"   ✅ Count:1 제거: {removed_count['count']}개")
    if removed_count['profile_id'] > 0:
        print(f"   ✅ profile id 배열 제거: {removed_count['profile_id']}개")
    if removed_count['empty-properties'] > 0:
        print(f"   ✅ 빈 Properties 제거: {removed_count['empty-properties']}개")
    if removed_count['vanilla-namespace'] > 0:
        print(f"   ✅ minecraft: 네임스페이스 제거: {removed_count['vanilla-namespace']}개")
    if removed_count['float-precision'] > 0:
        print(f"   ✅ 부동소수점 정밀도 축소: {removed_count['float-precision']}개")
    if removed_count['leading-zero'] > 0:
        print(f"   ✅ leading zero 제거: {removed_count['leading-zero']}개")
    if removed_count['tags'] > 0:
        print(f"   ✅ 불필요한 Tags 블록 제거: {removed_count['tags']}개")
    if removed_count['quoted-string'] > 0:
        print(f"   ✅ string 따옴표 제거: {removed_count['quoted-string']}개")

    total_optimized = optimized_count + sum(removed_count.values())
    if total_optimized == 0:
        print("   ⏭️  최적화할 내용 없음\n")
        return None

    # 출력 파일 경로
    if output_dir:
        output_file = os.path.join(output_dir, os.path.basename(input_file))
    else:
        output_file = str(input_file)

    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(content)

    optimized_size = len(content.encode('utf-8'))
    total_reduction = ((original_size - optimized_size) / original_size) * 100
    print(f"   💾 저장: {output_file}")
    print(f"   📊 파일 크기: {original_size:,} → {optimized_size:,} bytes (-{total_reduction:.1f}%)\n")

    return output_file


def process_all_mcfunction_files(directory='.', output_dir=None):
    """
    지정된 디렉토리의 모든 .mcfunction 파일을 처리
    """
    mcfunction_files = list(Path(directory).glob('*.mcfunction'))

    if not mcfunction_files:
        print(f"❌ '{directory}' 디렉토리에서 .mcfunction 파일을 찾을 수 없습니다.")
        return

    print(f"📂 디렉토리: {os.path.abspath(directory)}")
    print(f"🔍 발견된 파일: {len(mcfunction_files)}개\n")

    if output_dir:
        os.makedirs(output_dir, exist_ok=True)
        print(f"📁 출력 디렉토리: {output_dir}\n")

    processed_count = 0
    for file_path in mcfunction_files:
        result = process_mcfunction_file(str(file_path), output_dir)
        if result:
            processed_count += 1

    print("=" * 60)
    print(f"✨ 완료! 총 {processed_count}/{len(mcfunction_files)}개 파일 최적화")
    print("=" * 60)


if __name__ == "__main__":
    import sys

    print("=" * 60)
    print("mcfunction 파일 최적화 도구")
    print("=" * 60)
    print()

    # 사용법:
    #   특정 파일: python3 model-optimizer.py kite-practice-cyber.mcfunction
    #   디렉토리:  python3 model-optimizer.py ./input ./output
    #   현재 디렉토리: python3 model-optimizer.py

    if len(sys.argv) > 1:
        target = sys.argv[1]

        # 특정 파일 지정인 경우
        if target.endswith('.mcfunction'):
            if not os.path.exists(target):
                print(f"❌ 오류: '{target}' 파일을 찾을 수 없습니다.")
                sys.exit(1)
            output_dir = sys.argv[2] if len(sys.argv) > 2 else None
            process_mcfunction_file(target, output_dir)

        # 디렉토리 지정인 경우
        else:
            if not os.path.exists(target):
                print(f"❌ 오류: '{target}' 디렉토리를 찾을 수 없습니다.")
                sys.exit(1)
            output_dir = sys.argv[2] if len(sys.argv) > 2 else None
            process_all_mcfunction_files(target, output_dir)

    # 인자 없으면 현재 디렉토리 전체 처리
    else:
        process_all_mcfunction_files('.', '.')