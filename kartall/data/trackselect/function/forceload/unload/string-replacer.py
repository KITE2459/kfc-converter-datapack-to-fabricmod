import os
import time
import zipfile
import io
from pathlib import Path
from concurrent.futures import ThreadPoolExecutor

replace = "remove"
search = "add"

# search = "~ ~ ~"
# replace = "^0 ^ ^"

# ────────────────────────────────────────────────────────
# 1. 일반 파일 처리 함수
# ────────────────────────────────────────────────────────
def process_file(path: Path):
    try:
        # 파일 내용 교체
        text = path.read_text(encoding="utf-8")
        new_text = text.replace(search, replace)
        if text != new_text:
            path.write_text(new_text, encoding="utf-8")
            print(f"{path} changed")

        #파일 이름에 load → unload 교체
        if "load" in path.name:
            new_name = path.with_name(path.name.replace("load", "unload"))
            path.rename(new_name)
            print(f"{path} renamed to {new_name}")

    except Exception as e:
        return f"Error processing {path}: {e}"
    return None

# ────────────────────────────────────────────────────────
# 2. 디렉터리 내 .mcfunction 병렬 처리
# ────────────────────────────────────────────────────────
def process_directory(base_dir: Path):
    files = list(base_dir.rglob("*.mcfunction"))
    with ThreadPoolExecutor() as executor:
        for result in executor.map(process_file, files):
            if result:
                print(result)

# ────────────────────────────────────────────────────────
# 3. ZIP 파일을 메모리에서 수정하여 덮어쓰기
# ────────────────────────────────────────────────────────
def modify_zip_in_memory(zip_path: Path):
    print(f"[ZIP] {zip_path.name} start")
    new_zip_buffer = io.BytesIO()

    with zipfile.ZipFile(zip_path, 'r') as old_zip, \
         zipfile.ZipFile(new_zip_buffer, 'w', zipfile.ZIP_DEFLATED) as new_zip:

        for item in old_zip.infolist():
            original_data = old_zip.read(item.filename)
            new_filename = item.filename

            # 파일 내용 교체
            if item.filename.endswith(".mcfunction"):
                try:
                    text = original_data.decode("utf-8")
                    new_text = text.replace(search, replace)
                    original_data = new_text.encode("utf-8")
                    print(f"{item.filename} changed-zip")
                except Exception as e:
                    print(f"  - ⚠️ fail ({item.filename}): {e}")

            #파일 이름 load → unload 교체
            if "load" in item.filename:
                new_filename = item.filename.replace("load", "unload")
                print(f"{item.filename} renamed to {new_filename}")

            #새 zip에 저장 (이름 변경 반영)
            new_zip.writestr(new_filename, original_data)

    zip_path.write_bytes(new_zip_buffer.getvalue())
    print(f"[ZIP] {zip_path.name} complete")

# ────────────────────────────────────────────────────────
# 4. 메인
# ────────────────────────────────────────────────────────
def main():
    print("== 실행 시작 ==")
    start_time = time.perf_counter()

    base_dir = Path.cwd()

    # 1. ZIP 파일 먼저 처리 (하위 디렉터리 포함)
    for zip_file in base_dir.rglob("*.zip"):
        modify_zip_in_memory(zip_file)

    # 2. 일반 디렉터리 처리
    process_directory(base_dir)

    end_time = time.perf_counter()
    print(f"== 실행 종료: 총 소요 시간 {end_time - start_time:.2f}초 ==")


if __name__ == "__main__":
    main()
