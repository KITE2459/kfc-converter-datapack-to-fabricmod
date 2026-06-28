# 매크로: ID 추출 후 게임 시작
# 인자: idx

$data modify storage master:game current_play_id set from storage master:game current_targets[$(idx)]

# ID를 스코어보드에 설정
execute store result score @p mastermap run data get storage master:game current_play_id

# 게임 시작 트리거 (readytoplay 호출)
execute as @p run function master:readytoplay
