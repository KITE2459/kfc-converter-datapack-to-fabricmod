import re
import os

# ─────────────────────────────────────────────
#  공통 유틸 함수
# ─────────────────────────────────────────────

def extract_outermost_braces(s):
    """문자열에서 가장 바깥쪽 중괄호 블록을 반환"""
    start = None
    depth = 0
    for i, c in enumerate(s):
        if c == '{':
            if depth == 0:
                start = i
            depth += 1
        elif c == '}':
            depth -= 1
            if depth == 0 and start is not None:
                return s[start:i+1]
    return None

def get_passengers(nbt_str):
    """Passengers 배열 안의 각 엔티티 NBT를 리스트로 반환 (중괄호 포함)"""
    match = re.search(r'Passengers\s*:\s*\[(.*)](?=[,}])', nbt_str, re.DOTALL)
    if not match:
        return []

    inner = match.group(1)
    results = []
    depth = 0
    current = ''
    in_string = False
    i = 0
    while i < len(inner):
        c = inner[i]
        if c == '"' and (i == 0 or inner[i-1] != '\\'):
            in_string = not in_string
        if not in_string:
            if c == '{':
                if depth == 0:
                    current = ''
                depth += 1
            if depth > 0:
                current += c
            if c == '}':
                depth -= 1
                if depth == 0:
                    results.append(current.strip().replace(" ", ""))
        else:
            if depth > 0:
                current += c
        i += 1
    return results

def remove_passengers_tag(nbt_str):
    """NBT 문자열에서 Passengers:[...] 태그를 제거해 반환"""
    i = 0
    while i < len(nbt_str):
        if nbt_str[i:i+10] == 'Passengers':
            j = i + 10
            while j < len(nbt_str) and nbt_str[j] in ' \t\r\n:':
                j += 1
            if j < len(nbt_str) and nbt_str[j] == '[':
                depth = 1
                j += 1
                while j < len(nbt_str) and depth > 0:
                    if nbt_str[j] == '[':
                        depth += 1
                    elif nbt_str[j] == ']':
                        depth -= 1
                    j += 1
                end = j
                after = end
                while after < len(nbt_str) and nbt_str[after] in ' \t\r\n':
                    after += 1
                if after < len(nbt_str) and nbt_str[after] == ',':
                    end = after + 1
                return nbt_str[:i] + nbt_str[end:]
        i += 1
    return nbt_str

def has_tag(nbt, tag, numeric_tag=-1):
    """NBT에 특정 태그(+ 숫자 태그)가 있는지 확인"""
    is_find = False
    tags_match = re.search(r'Tags:\[([^\]]*)\]', nbt)
    if tags_match:
        tags = tags_match.group(1).split(",")
        for t in tags:
            if t.strip('"') == tag:
                is_find = True
        for t in tags:
            if is_find and numeric_tag != -1 and t.strip('"').isdigit():
                if t.strip('"') == numeric_tag:
                    return True
    if numeric_tag == -1:
        return is_find
    return False

def get_numeric_tag(nbt):
    """Tags 배열에서 숫자 태그 값을 반환"""
    tags_match = re.search(r'Tags:\[([^\]]*)\]', nbt)
    if tags_match:
        tags = tags_match.group(1).split(",")
        for t in tags:
            if t.strip('"').isdigit():
                return t.strip('"')

def get_transform(nbt):
    """transformation:[...] 값을 반환"""
    transform_match = re.search(r'transformation:\[([^\]]*)\]', nbt)
    if transform_match:
        return transform_match.group(1)


# ─────────────────────────────────────────────
#  변신부스터 압축 (kart-boost-move)
# ─────────────────────────────────────────────

def process_boost_line(summon_line):
    """
    변신부스터 Passengers가 있는 summon 줄을 압축 처리.
    반환: (압축된 줄 or None, 상태 메시지)
    """
    if "boost-transform" in summon_line:
        return None, "이미 압축됨"
    if "kart-boost-move" not in summon_line:
        return None, "변신부스터 없음"

    passengers = get_passengers(summon_line)
    if not passengers:
        return None, "Passengers 파싱 실패"

    remove_passengers = remove_passengers_tag(extract_outermost_braces(summon_line))
    new_passengers = []
    final_passengers = []

    for i in passengers:
        inner = i[1:-1]  # 중괄호 제거
        if has_tag(inner, "kart-boost-move-start"):
            numeric_tag = get_numeric_tag(inner)

            found_end = False
            end_transform = ""
            for j in passengers:
                if has_tag(j, "kart-boost-move-end", numeric_tag):
                    end_transform = get_transform(j)
                    found_end = True

            start_transform_data = "boost-transform-start:[" + get_transform(inner) + "]"
            end_transform_data   = "boost-transform-end:[" + end_transform + "]"

            data_match = re.search(r'data:\{([^}]*)\}', inner)
            if found_end:
                nbt_data = (data_match.group(1) + "," if data_match else "") + start_transform_data + "," + end_transform_data
            else:
                nbt_data = (data_match.group(1) + "," if data_match else "") + start_transform_data

            inner = re.sub(r'data:\{.*?\},\s*', '', inner)
            inner += ",data:{" + nbt_data + "}"

        new_passengers.append("{" + inner + "}")

    # kart-boost-move-end 항목 제거
    for p in new_passengers:
        if not has_tag(p, "kart-boost-move-end"):
            final_passengers.append(p)

    result = "summon block_display ~ ~ ~ " + remove_passengers[0:-1] + ",Passengers:[" + ",".join(final_passengers) + "]}"
    result = result.replace("{,Passengers", "{Passengers")
    return result, "압축 성공"


# ─────────────────────────────────────────────
#  뛰라이더 압축 (kart-run-anime)
# ─────────────────────────────────────────────

def process_runider_line(summon_line):
    """
    뛰라이더 Passengers가 있는 summon 줄을 압축 처리.
    반환: (압축된 줄 or None, 상태 메시지)
    """
    if "run-anime-transform" in summon_line:
        return None, "이미 압축됨"
    if "kart-run-anime" not in summon_line:
        return None, "뛰라이더 없음"

    passengers = get_passengers(summon_line)
    if not passengers:
        return None, "Passengers 파싱 실패"

    remove_passengers = remove_passengers_tag(extract_outermost_braces(summon_line))
    new_passengers = []
    final_passengers = []

    for i in passengers:
        inner = i[1:-1]
        if has_tag(inner, "kart-run-anime-mid"):
            numeric_tag = get_numeric_tag(inner)
            first_transform = ""
            last_transform  = ""

            for j in passengers:
                if has_tag(j, "kart-run-anime-first", numeric_tag):
                    first_transform = get_transform(j)
                elif has_tag(j, "kart-run-anime-last", numeric_tag):
                    last_transform = get_transform(j)

            mid_transform_data   = "run-anime-transform-mid:["   + get_transform(inner) + "]"
            first_transform_data = "run-anime-transform-first:[" + first_transform      + "]"
            last_transform_data  = "run-anime-transform-last:["  + last_transform       + "]"

            data_match = re.search(r'data:\{([^}]*)\}', inner)
            if data_match:
                nbt_data = data_match.group(1) + "," + first_transform_data + "," + mid_transform_data + "," + last_transform_data
            else:
                nbt_data = first_transform_data + "," + mid_transform_data + "," + last_transform_data

            inner = re.sub(r'data:\{.*?\},\s*', '', inner)
            inner += ",data:{" + nbt_data + "}"

        new_passengers.append("{" + inner + "}")

    # first / last 항목 제거
    for p in new_passengers:
        if not (has_tag(p, "kart-run-anime-first") or has_tag(p, "kart-run-anime-last")):
            final_passengers.append(p)

    result = "summon block_display ~ ~ ~ " + remove_passengers[0:-1] + ",Passengers:[" + ",".join(final_passengers) + "]}"
    result = result.replace("{,Passengers", "{Passengers")
    return result, "압축 성공"


# ─────────────────────────────────────────────
#  파일 처리
# ─────────────────────────────────────────────

def process_file(file_path, output_dir):
    file_name = os.path.splitext(os.path.basename(file_path))[0]

    with open(file_path, 'rb') as f:
        data = f.read().decode('utf-8')

    lines = data.split('\n')
    processed_lines = []

    boost_count        = 0
    runider_count      = 0
    boost_already_done = False  # 변신부스터가 이미 압축된 파일인지
    runider_already_done = False  # 뛰라이더가 이미 압축된 파일인지

    for line in lines:
        stripped = line.strip()

        # summon block_display 줄이고 Passengers가 있을 때만 처리 시도
        if stripped.startswith('summon block_display') and 'Passengers' in line:

            current_line = line

            # 뛰라이더 시도 (먼저 적용)
            if "kart-run-anime" in current_line and not runider_already_done:
                compressed, status = process_runider_line(current_line)
                if compressed:
                    current_line = compressed
                    runider_count += 1
                elif status == "이미 압축됨":
                    runider_already_done = True

            # 변신부스터 시도 (뛰라이더 압축 결과에 이어서 적용)
            if "kart-boost-move" in current_line and not boost_already_done:
                compressed, status = process_boost_line(current_line)
                if compressed:
                    current_line = compressed
                    boost_count += 1
                elif status == "이미 압축됨":
                    boost_already_done = True

            processed_lines.append(current_line)

        else:
            # summon이 아니거나 Passengers 없는 줄 (kart-special-ability 등) → 원본 유지
            processed_lines.append(line)

    # 둘 다 이미 압축된 경우만 건너뜀
    if boost_already_done and runider_already_done:
        print(f"{file_name} 는 변신부스터와 뛰라이더가 이미 압축되었으므로 건너뜁니다.")
        return
    if boost_already_done and boost_count == 0 and runider_count == 0:
        print(f"{file_name} 는 변신부스터가 이미 압축되었으므로 건너뜁니다.")
        return
    if runider_already_done and boost_count == 0 and runider_count == 0:
        print(f"{file_name} 는 뛰라이더가 이미 압축되었으므로 건너뜁니다.")
        return

    if boost_count == 0 and runider_count == 0:
        print(f"{file_name} 에는 압축할 내용이 없으므로 건너뜁니다.")
        return

    out_path = os.path.join(output_dir, file_name + ".mcfunction")
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write('\n'.join(processed_lines))

    parts = []
    if boost_count   > 0: parts.append(f"변신부스터 {boost_count}개")
    if runider_count > 0: parts.append(f"뛰라이더 {runider_count}개")
    print(f"{file_name} 압축 완료 ({', '.join(parts)})")


# ─────────────────────────────────────────────
#  메인
# ─────────────────────────────────────────────

input("모든 .mcfunction의 변신부스터 및 뛰라이더를 압축해 result 폴더에 저장합니다.\n진행하려면 아무 글자나 입력하세요.\n")

input_dir  = "./"
output_dir = "./result/"
os.makedirs(output_dir, exist_ok=True)

files = [f for f in os.listdir(input_dir) if f.endswith(".mcfunction")]

if not files:
    print("현재 디렉토리에 .mcfunction 파일이 없습니다.")
else:
    for file_path in files:
        process_file(file_path, output_dir)