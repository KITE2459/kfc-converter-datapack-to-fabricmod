title @s title {translate:"완주!","color":"yellow"}
# mastermap에 따라 다른 서브타이틀 표시 가능
# 데이터를 통해 동적으로 로드
execute store result storage master:temp map_id int 1 run scoreboard players get @s mastermap
function master:system/game/complete-resolve with storage master:temp

bossbar set minecraft:master players

scoreboard players reset @s mastercount
# BGM 끄기
function bgm-room:manage-bgm/stopbgm

function master:sound/complete

time set day
weather clear
tp @s -138.5 4 296.5 0 0

# 타이머 스탑
function timerpack:stop

# 멀티 트랙 진행 상황 업데이트
execute if score #game_state master matches 3 run function master:manager/map-complete

