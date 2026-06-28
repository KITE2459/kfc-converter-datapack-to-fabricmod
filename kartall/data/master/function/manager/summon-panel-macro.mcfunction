# 매크로: candidates 리스트에서 ID 추출 후 소환 매크로 호출
# 인자: {idx: 0}

# 1. ID 추출하여 temp storage에 저장
$data modify storage master:game current_map_arg.id set from storage master:game candidates[$(idx)]
$data modify storage master:game current_map_arg.idx set value $(idx)

# 2. 소환 매크로 호출 (ID와 IDX 전달)
function master:manager/summon-panel-data-macro with storage master:game current_map_arg
