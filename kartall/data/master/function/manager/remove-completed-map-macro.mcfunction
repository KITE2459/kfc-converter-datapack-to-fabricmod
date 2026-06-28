# 매크로: 클리어 UI 및 데이터 처리
# 인자: completed_map_id

# UI 업데이트 및 데이터 변경
# 값을 -1 (Clear) 등으로 변경하여 인덱스를 유지해야 panel_X와 매핑이 가능함.

# 1. UI 업데이트 (값을 확인해서 해당 위치의 패널 처리)
$execute if data storage master:game current_targets[0] value $(completed_map_id) run function master:manager/update-clear-ui {panel_idx:0}
$execute if data storage master:game current_targets[1] value $(completed_map_id) run function master:manager/update-clear-ui {panel_idx:1}
$execute if data storage master:game current_targets[2] value $(completed_map_id) run function master:manager/update-clear-ui {panel_idx:2}

# 2. 리스트 데이터 변경 (값을 -1로 변경하여 인덱스 유지)
$execute if data storage master:game current_targets[0] value $(completed_map_id) run data modify storage master:game current_targets[0] set value -1
$execute if data storage master:game current_targets[1] value $(completed_map_id) run data modify storage master:game current_targets[1] set value -1
$execute if data storage master:game current_targets[2] value $(completed_map_id) run data modify storage master:game current_targets[2] set value -1
