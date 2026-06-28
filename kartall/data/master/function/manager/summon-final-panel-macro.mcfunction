# 매크로: current_targets 리스트에서 ID 추출 후 소환 매크로 호출
# 인자: {idx: 0}

# 1. ID 추출하여 temp storage에 저장 (이번엔 current_targets 에서)
$data modify storage master:game current_map_arg.id set from storage master:game current_targets[$(idx)]
$data modify storage master:game current_map_arg.idx set value $(idx)

# 2. 소환 매크로 호출 (ID와 IDX 전달)
# (이미 존재하는 summon-panel-data-macro 재사용)
# 주의: 재사용 시 태그가 panel_0, panel_1 등으로 붙는데, 이것이 click/select 로직에 영향을 줄 수 있음.
# 하지만 게임 상태가 3(플레이 중)이면 밴 로직이 실행되지 않으므로 괜찮음.
# 다만 클릭 시 '맵 플레이' 기능을 넣는다면 구분 필요.
function master:manager/summon-panel-data-macro with storage master:game current_map_arg
