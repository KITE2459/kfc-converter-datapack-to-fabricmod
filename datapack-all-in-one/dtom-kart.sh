#!/bin/bash
# 스크립트(루트)가 위치한 디렉터리를 기준으로 datapacks 경로 자동 설정
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# 대상 디렉터리 (datapacks 경로 설정)
DATAPACKS_DIR="$SCRIPT_DIR/datapacks-kart"
cd

# 1️⃣ 폴더를 ZIP으로 압축하고 원본 폴더 삭제
echo "모든 폴더를 ZIP으로 압축 중..."
for folder in "$DATAPACKS_DIR"/*/; do
    if [ -d "$folder" ]; then
        folder_name=$(basename "$folder")  # 폴더 이름 추출
        (cd "$folder" && zip -0 -r "../${folder_name}.zip" .)
        echo "압축 완료: ${folder_name}.zip"
    fi
done

# 원본 폴더 삭제
echo "원본 폴더 삭제 중..."
for folder in "$DATAPACKS_DIR"/*/; do
    if [ -d "$folder" ]; then
        rm -rf "$folder"
        echo "삭제 완료: $(basename "$folder")"
    fi
done

cd "$DATAPACKS_DIR"

# 임시 작업 디렉터리 생성
TMP_DIR="temp_merge"
mkdir -p "$TMP_DIR"

# 최종 출력 디렉터리
FINAL_DIR="$TMP_DIR/data"
mkdir -p "$FINAL_DIR"

# load.json 및 tick.json을 병합할 배열 초기화
declare -A load_set
declare -A tick_set
load_order=() # JSON 병합 시 순서를 관리할 배열
tick_order=()

# 모든 ZIP 파일을 반복 (kartriderpack*을 먼저 처리)
for zip in kartriderpack*.zip *.zip; do
    [[ -f "$zip" ]] || continue # 파일이 없는 경우 스킵
    echo "Processing $zip..."
    
    # 강제 덮어쓰기 옵션 (-o) 사용하여 압축 해제
    unzip -o -q "$zip" -d "$TMP_DIR"

    # load.json 병합
    load_json="$TMP_DIR/data/minecraft/tags/function/load.json"
    if [[ -f "$load_json" ]]; then
        values=$(sed -n 's/.*"values":\s*\[\(.*\)\].*/\1/p' "$load_json" | tr -d ' ')
        IFS=',' read -ra json_values <<< "$values"
        for value in "${json_values[@]}"; do
            if [[ -z "${load_set[$value]}" ]]; then
                load_set["$value"]=1
                # kartriderpack*인 경우 앞에 추가, 아닌 경우 뒤에 추가
                if [[ "$zip" == kartriderpack* ]]; then
                    load_order=("$value" "${load_order[@]}")
                else
                    load_order+=("$value")
                fi
            fi
        done
    fi

    # tick.json 병합
    tick_json="$TMP_DIR/data/minecraft/tags/function/tick.json"
    if [[ -f "$tick_json" ]]; then
        values=$(sed -n 's/.*"values":\s*\[\(.*\)\].*/\1/p' "$tick_json" | tr -d ' ')
        IFS=',' read -ra json_values <<< "$values"
        for value in "${json_values[@]}"; do
            if [[ -z "${tick_set[$value]}" ]]; then
                tick_set["$value"]=1
                # kartriderpack*인 경우 앞에 추가, 아닌 경우 뒤에 추가
                if [[ "$zip" == kartriderpack* ]]; then
                    tick_order=("$value" "${tick_order[@]}")
                else
                    tick_order+=("$value")
                fi
            fi
        done
    fi
done

# JSON 값들을 정리하여 새로운 JSON 파일 생성
load_json_final="$TMP_DIR/data/minecraft/tags/function/load.json"
tick_json_final="$TMP_DIR/data/minecraft/tags/function/tick.json"
mkdir -p "$(dirname "$load_json_final")"

# load.json 작성
{
    echo '{ "values": ['
    first=true
    for value in "${load_order[@]}"; do
        if [ "$first" = true ]; then
            first=false
        else
            echo -n ", "
        fi
        echo -n "$value"
    done
    echo " ]}"
} > "$load_json_final"

# tick.json 작성
{
    echo '{ "values": ['
    first=true
    for value in "${tick_order[@]}"; do
        if [ "$first" = true ]; then
            first=false
        else
            echo -n ", "
        fi
        echo -n "$value"
    done
    echo " ]}"
} > "$tick_json_final"

echo "mcfunction 파일 처리 중..."
find "$TMP_DIR" -name "*.mcfunction" -print0 | \
xargs -0 -P "$(nproc)" sed -i \
    '/execute if score #kfc-converted kartmain matches 0/d;
     s/if score #kfc-converted kartmain matches 1 //g;
     s/execute run //g'
echo "mcfunction 처리 완료!"

# echo "mcfunction 파일 처리 중..."
# find "$TMP_DIR" -name "*.mcfunction" -print0 | \
# xargs -0 -P "$(nproc)" sed -i \
#     '/execute if score #kfc-kartriderpack kartmain matches 0/d;
#     /execute if score #kfc-gamesystem kartmain matches 1/d;
#      s/if score #kfc-kartriderpack kartmain matches 1 //g;
#      s/if score #kfc-gamesystem kartmain matches 0 //g;
#      s/execute run //g'
# echo "mcfunction 처리 완료!"

# 최종 ZIP 파일 생성
echo "Creating kartall-kart.zip..."
cd "$TMP_DIR"
zip -6 -qr "../kartall-kart.zip" *
cd ..

# 정리
cd "$DATAPACKS_DIR"
mv kartall-kart.zip ..
rm -rf "$DATAPACKS_DIR"/*
mv ../kartall-kart.zip .

echo "모든 작업 완료!"

cd