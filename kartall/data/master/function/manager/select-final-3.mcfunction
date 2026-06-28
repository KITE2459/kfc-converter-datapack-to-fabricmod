# 남은 5개 중 3개를 확정하고 게임 시작
# 1. 유효 후보(밴 안된 것) 추출
data modify storage master:game temp_candidates set value []

# 각 후보의 값이 -1인지 확인하고, 아니면 추가
scoreboard players set #temp_val master 0
execute store result score #temp_val master run data get storage master:game candidates[0]
execute unless score #temp_val master matches -1 run data modify storage master:game temp_candidates append from storage master:game candidates[0]

execute store result score #temp_val master run data get storage master:game candidates[1]
execute unless score #temp_val master matches -1 run data modify storage master:game temp_candidates append from storage master:game candidates[1]

execute store result score #temp_val master run data get storage master:game candidates[2]
execute unless score #temp_val master matches -1 run data modify storage master:game temp_candidates append from storage master:game candidates[2]

execute store result score #temp_val master run data get storage master:game candidates[3]
execute unless score #temp_val master matches -1 run data modify storage master:game temp_candidates append from storage master:game candidates[3]

execute store result score #temp_val master run data get storage master:game candidates[4]
execute unless score #temp_val master matches -1 run data modify storage master:game temp_candidates append from storage master:game candidates[4]

execute store result score #temp_val master run data get storage master:game candidates[5]
execute unless score #temp_val master matches -1 run data modify storage master:game temp_candidates append from storage master:game candidates[5]

execute store result score #temp_val master run data get storage master:game candidates[6]
execute unless score #temp_val master matches -1 run data modify storage master:game temp_candidates append from storage master:game candidates[6]

# 2. 5개 중 3개 랜덤 선택
data modify storage master:game current_targets set value []
# temp_candidates를 temp이름의 풀로 복사하여 뽑기 함수 재사용
data modify storage master:game temp_pool set from storage master:game temp_candidates

# 3번 랜덤 선택
function master:manager/pick-target-random
function master:manager/pick-target-random
function master:manager/pick-target-random

# 3. 기존 화면 제거
kill @e[tag=master.panel]

# 4. 선택된 3개 화면 표시
# 3개이므로 인덱스 0~2
scoreboard players set #panel_idx master 0
execute positioned -149.999 4 293.5 run function master:manager/summon-final-panel-loop

# 5. 게임 상태 변경
scoreboard players set #game_state master 3
title @a title {text:"맵 선정이 완료되었습니다!", "color":"green"}
