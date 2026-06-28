import base64
import json
import re
import os

def optimize_base64_value(base64_input):
    """
    Base64로 인코딩된 텍스처 데이터를 최적화
    SKIN URL만 추출하여 반환
    """
    try:
        # Base64 디코딩
        decoded = base64.b64decode(base64_input).decode('utf-8')
        
        # JSON 파싱
        data = json.loads(decoded)
        
        # SKIN URL만 추출
        optimized_data = {}
        if 'textures' in data and 'SKIN' in data['textures']:
            optimized_data['textures'] = {
                'SKIN': {
                    'url': data['textures']['SKIN'].get('url', '')
                }
            }
        
        # JSON을 문자열로 변환 (공백 없이 압축)
        optimized_json = json.dumps(optimized_data, separators=(',', ':'))
        
        # Base64 인코딩
        optimized_base64 = base64.b64encode(optimized_json.encode('utf-8')).decode('utf-8')
        
        return optimized_base64
        
    except Exception as e:
        print(f"⚠️  Base64 값 최적화 실패: {e}")
        return base64_input  # 실패시 원본 반환


def process_mcfunction_file(input_file):
    """
    .mcfunction 파일을 읽어서 Base64 값들을 최적화하고 원본 파일을 덮어씀
    """
    # 파일 존재 여부 확인
    if not os.path.exists(input_file):
        print(f"❌ 오류: '{input_file}' 파일을 찾을 수 없습니다.")
        return False
    
    # 파일 읽기
    with open(input_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # "icon":"..." 패턴의 Base64 값 찾기
    pattern = r'(icon:")([A-Za-z0-9+/=]+)(")'
    
    matches = re.findall(pattern, content)
    
    print(f"\n📄 파일: {input_file}")
    print(f"🔍 발견된 Base64 값: {len(matches)}개")
    
    if len(matches) == 0:
        print("   ℹ️  최적화할 항목이 없습니다.")
        return True
    
    # 각 Base64 값을 최적화하여 교체
    optimized_count = 0
    total_original_size = 0
    total_optimized_size = 0
    
    for prefix, base64_value, suffix in matches:
        optimized_value = optimize_base64_value(base64_value)
        
        if optimized_value != base64_value:
            content = content.replace(
                f'{prefix}{base64_value}{suffix}',
                f'{prefix}{optimized_value}{suffix}'
            )
            optimized_count += 1
            
            # 크기 비교
            original_size = len(base64_value)
            optimized_size = len(optimized_value)
            total_original_size += original_size
            total_optimized_size += optimized_size
            
            reduction = ((original_size - optimized_size) / original_size) * 100
            print(f"   ✅ 항목 #{optimized_count}: {original_size} → {optimized_size} bytes (-{reduction:.1f}%)")
    
    # 원본 파일 덮어쓰기
    with open(input_file, 'w', encoding='utf-8') as f:
        f.write(content)
    
    if optimized_count > 0:
        total_reduction = ((total_original_size - total_optimized_size) / total_original_size) * 100
        print(f"   📊 최적화 완료: {optimized_count}/{len(matches)}개")
        print(f"   📉 총 크기 감소: {total_original_size} → {total_optimized_size} bytes (-{total_reduction:.1f}%)")
    
    return True


if __name__ == "__main__":
    print("=" * 70)
    print("mcfunction 파일 Base64 최적화 도구 (icon 필드 전용)")
    print("=" * 70)
    
    # 처리할 파일 목록
    target_files = [
        'bgm-room/function/startset.mcfunction',
        'trackselect/function/startset.mcfunction'
    ]
    
    success_count = 0
    
    for file_path in target_files:
        if process_mcfunction_file(file_path):
            success_count += 1
    
    print("\n" + "=" * 70)
    print(f"🎉 작업 완료: {success_count}/{len(target_files)}개 파일 처리됨")
    print("=" * 70)