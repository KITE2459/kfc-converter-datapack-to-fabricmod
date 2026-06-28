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
    .mcfunction 파일을 읽어서 Base64 값들을 최적화하고 새 파일로 저장
    """
    # 파일 읽기
    with open(input_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # "value \"...\"" 패턴의 Base64 값 찾기
    # 정규표현식: set value "로 시작하고 "로 끝나는 Base64 문자열
    pattern = r'(set value ")([A-Za-z0-9+/=]+)(")'
    # pattern = r'(value:")([A-Za-z0-9+/=]+)(")'
    
    matches = re.findall(pattern, content)
    
    print(f"📄 파일: {input_file}")
    print(f"🔍 발견된 Base64 값: {len(matches)}개\n")
    
    # 각 Base64 값을 최적화하여 교체
    optimized_count = 0
    for prefix, base64_value, suffix in matches:
        optimized_value = optimize_base64_value(base64_value)
        
        if optimized_value != base64_value:
            content = content.replace(
                f'{prefix}{base64_value}{suffix}',
                f'{prefix}{optimized_value}{suffix}'
            )
            optimized_count += 1
            
            # 크기 비교 출력
            original_size = len(base64_value)
            optimized_size = len(optimized_value)
            reduction = ((original_size - optimized_size) / original_size) * 100
            print(f"✅ 최적화 완료: {original_size} → {optimized_size} bytes (-{reduction:.1f}%)")
    
    # 출력 파일명 생성
    base_name = os.path.splitext(os.path.basename(input_file))[0]
    output_file = f"{base_name}.mcfunction"
    
    # 파일 저장
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"\n💾 저장 완료: {output_file}")
    print(f"📊 최적화된 항목: {optimized_count}/{len(matches)}")
    
    return output_file


if __name__ == "__main__":
    import sys
    
    print("=" * 60)
    print("mcfunction 파일 Base64 최적화 도구")
    print("=" * 60)
    print()
    
    # 명령행 인자로 파일명을 받거나, 기본값으로 'startset.mcfunction' 사용
    if len(sys.argv) > 1:
        input_file = sys.argv[1]
    else:
        input_file = 'startset.mcfunction'
    
    # 파일 존재 여부 확인
    if not os.path.exists(input_file):
        print(f"❌ 오류: '{input_file}' 파일을 찾을 수 없습니다.")
        print(f"💡 사용법: python mcfunction_optimizer.py [파일명]")
        print(f"   예시: python mcfunction_optimizer.py startset.mcfunction")
        sys.exit(1)
    
    # 파일 처리
    process_mcfunction_file(input_file)
