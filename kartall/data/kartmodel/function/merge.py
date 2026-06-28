import re
import os

def merge_summons_from_file(input_file, output_file):
    if not os.path.exists(input_file):
        print(f"파일을 찾을 수 없습니다: {input_file}")
        return

    # 정규표현식: summon [엔티티ID] [좌표] {NBT}
    # 좌표 부분은 무시하고 ID와 NBT만 추출합니다.
    pattern = re.compile(r"summon\s+([\w:]+)\s+[\d~\.\s-]+\s+({.*})")
    
    passengers = []

    with open(input_file, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    for line in lines:
        line = line.strip()
        if not line or line.startswith("#"): # 빈 줄이나 주석 제외
            continue
            
        match = pattern.search(line)
        if match:
            entity_id = match.group(1).strip()
            nbt_data = match.group(2).strip()
            
            # Passengers 리스트 안에 들어갈 개별 항목 생성 {id:"...", ...}
            # nbt_data에서 양 끝의 { }를 떼어내고 id를 붙입니다.
            inner_nbt = nbt_data[1:-1]
            passenger_entry = f'{{id:"{entity_id}",{inner_nbt}}}'
            passengers.append(passenger_entry)

    if not passengers:
        print("합칠 summon 명령어를 찾지 못했습니다.")
        return

    # 모든 요소를 담을 '임의의 0(부모 엔티티)' 생성
    # 여기서는 주로 쓰이는 'marker' 엔티티를 부모로 사용합니다. (보이지 않고 부하가 적음)
    # 원하신다면 block_display 등으로 바꿀 수 있습니다.
    passengers_str = ",".join(passengers)
    
    # 최종 명령어 구성
    # 부모 엔티티는 Marker를 사용하며, 태그를 붙여 관리하기 쉽게 만듭니다.
    combined_command = f'summon minecraft:item_display ~ ~ ~ {{Passengers:[{passengers_str}]}}'

    with open(output_file, 'w', encoding='utf-8') as f:
        f.write("# Merged summon command\n")
        f.write(combined_command)

    print(f"병합 완료! 생성된 파일: {output_file}")

# --- 실행 부분 ---
# input.mcfunction 파일을 읽어서 merged.mcfunction으로 저장합니다.
merge_summons_from_file('loggamja-invkart-old.mcfunction', 'temp-output.mcfunction')