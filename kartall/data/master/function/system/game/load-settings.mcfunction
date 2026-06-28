# 맵별 데이터 설정 (Target Time 등)
# mastermap에 따라 설정

# 초기화
scoreboard players set #target-time master -1

# Map 1
execute if score @p mastermap matches 1 run scoreboard players set #target-time master 120000
execute if score @p mastermap matches 1 run bossbar set minecraft:master name {translate:"02:00.000 내로 완주","color":"aqua"}

# Map 2
execute if score @p mastermap matches 2 run scoreboard players set #target-time master 180000
execute if score @p mastermap matches 2 run bossbar set minecraft:master name {translate:"03:00.000 내로 완주","color":"aqua"}

# Map 3
execute if score @p mastermap matches 3 run scoreboard players set #target-time master 300000
execute if score @p mastermap matches 3 run bossbar set minecraft:master name {translate:"05:00.000 내로 완주","color":"aqua"}

# Dynamic Config Apply (from master:config)
execute store result storage master:temp current_map_id.map_id int 1 run scoreboard players get @p mastermap
function master:system/game/load-settings-resolve with storage master:temp current_map_id

# 보스바 이름 설정 실패 시 기본 이름 (이미 설정되었는지 확인이 어려우므로 target-time으로 유추)
execute if score #target-time master matches -1 run bossbar set minecraft:master name {translate:"제한시간 내 완주","color":"aqua"}

# 설정되지 않은 경우 기본값 (10분)
execute if score #target-time master matches -1 run scoreboard players set #target-time master 600000

# 보스바 Max 설정
execute store result bossbar minecraft:master max run scoreboard players get #target-time master
execute if score #target-time master matches -1 run bossbar set minecraft:master name {translate:"제한시간 내 완주","color":"aqua"}

# 보스바 Value 설정 (꽉 채우기)
execute store result bossbar minecraft:master value run scoreboard players get #target-time master

# 기본 이름 설정 (오류 방지용)
execute if score #target-time master matches -1 run bossbar set minecraft:master name {translate:"제한시간 내 완주","color":"aqua"}
