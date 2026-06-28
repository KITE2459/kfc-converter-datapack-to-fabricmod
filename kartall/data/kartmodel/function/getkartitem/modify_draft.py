import csv
import os
import math
import json

# 폴더 경로
folder_path = '/home/kart/kart/world/datapacks/kartmodelpack v2.4/data/kartmodel/function/getkartitem'

# 등급별 설정
grade_configs = {
    'legend': {
        'base': 0, 
        'csv_filename': 'legend_kart_max_speed_rankings.csv',
        'files': ['getlegendkart.mcfunction', 'getlegendkart-2.mcfunction']
    },
    'rare': {
        'base': 20, 
        'csv_filename': 'rare_kart_max_speed_rankings.csv',
        'files': ['getrarekart.mcfunction', 'getrarekart-2.mcfunction']
    },
    'common': {
        'base': 40, 
        'csv_filename': 'common_kart_max_speed_rankings.csv',
        'files': ['getcommonkart.mcfunction', 'getcommonkart-2.mcfunction']
    }
}

for grade, config in grade_configs.items():
    csv_filepath = os.path.join(folder_path, config['csv_filename'])
    if not os.path.exists(csv_filepath):
        print(f"CSV 파일을 찾을 수 없음: {csv_filepath}")
        continue
    
    # CSV 읽어서 model별 rank 딕셔너리 생성
    model_rank = {}
    with open(csv_filepath, 'r', encoding='utf-8-sig') as f:
        reader = csv.DictReader(f)
        for row in reader:
            model = row.get('model', '').strip()
            if model:
                model_rank[model] = int(row['rank'])
    
    if not model_rank:
        print(f"{grade} 등급 CSV에 데이터가 없음")
        continue
    
    n = len(model_rank)
    group_size = math.ceil(n / 20)
    base = config['base']
    
    print(f"{grade} 등급: {n}개 카트, 그룹 크기: {group_size}, 베이스: {base}")
    
    # 각 .mcfunction 파일 수정
    for mcfile in config['files']:
        mcfilepath = os.path.join(folder_path, 'getofficialkart', mcfile)
        if not os.path.exists(mcfilepath):
            print(f".mcfunction 파일을 찾을 수 없음: {mcfilepath}")
            continue
        
        lines = []
        with open(mcfilepath, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        
        new_lines = []
        for line in lines:
            line = line.strip()
            if not line.startswith('function kartmain:makekart '):
                new_lines.append(line + '\n')
                continue
            
            json_str = line[len('function kartmain:makekart '):]
            try:
                kart_data = json.loads(json_str)
                model = kart_data.get('model', '').strip()
                if model in model_rank:
                    rank = model_rank[model]
                    group_index = (rank - 1) // group_size
                    new_draft = base + group_index + 1
                    kart_data['draft'] = new_draft
                new_json_str = json.dumps(kart_data, ensure_ascii=False)
                new_line = f'function kartmain:makekart {new_json_str}\n'
                new_lines.append(new_line)
            except json.JSONDecodeError:
                new_lines.append(line + '\n')
        
        with open(mcfilepath, 'w', encoding='utf-8') as f:
            f.writelines(new_lines)
        
        print(f"{mcfile} 수정 완료")
    
    print(f"{grade} 등급 수정 완료")

print("모든 등급 수정 완료")