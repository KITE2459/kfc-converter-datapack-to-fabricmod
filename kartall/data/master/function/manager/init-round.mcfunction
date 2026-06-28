# master:config available_maps 없으면 초기화 (1~20 예시)
execute unless data storage master:config available_maps run data modify storage master:config available_maps set value [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]

# 1. 맵 풀 복사
data modify storage master:game pool set from storage master:config available_maps

# 2. candidates 초기화
data modify storage master:game candidates set value []

# 3. 7개 뽑기 (기존 패널 제거 후)
kill @e[tag=master.panel]
kill @e[tag=master.interaction]

scoreboard players set #i master 0
function master:manager/pick-7-random

# 4. 소환 시작위치로 이동 후 배치 시작
# (현재 위치에서 x축으로 3칸씩 이동하며 배치한다고 가정. 초기 offset 0)
scoreboard players set #panel_idx master 0
# summon-panel-loop will create panels at player's location
execute positioned ~ ~ ~ run function master:manager/summon-panel-loop

# 마스터 타이틀 소환
kill @e[tag=master.title]
execute if score clear-data masterstage matches 0 run summon text_display -149.999 8 296 {Tags:["master.text","master.title"],Rotation:[-90F,0F],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[6f,6f,6f]},text:[{text:"Master ",bold:true,color:gray}],background:16711680}
execute if score clear-data masterstage matches 1.. run summon text_display -149.999 8 296 {Tags:["master.text","master.title"],Rotation:[-90F,0F],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[6f,6f,6f]},text:[{text:"Master ",bold:true,color:red},{"score":{"name":"clear-data","objective":"masterstage"},bold:false,color:yellow}],background:16711680}

# 5. 상태 변경
scoreboard players set #game_state master 1
title @a[scores={masterstage=1..}] title {text:"맵 선택","color":"aqua"}
title @a[scores={masterstage=1..}] subtitle {text:"제외할 맵을 2개 선택하세요","color":"red"}
scoreboard players set #banned_count master 0
