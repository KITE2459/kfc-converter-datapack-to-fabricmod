scoreboard players add #rank-print-temp multi-rank 1
# 로그텔로우 실행
execute as @a if score @s multi-rank = #rank-print-temp multi-rank run function multiplay:system/game/logtellraw

execute if score team-battle multi-main matches 0 as @a if score @s multi-rank = #rank-print-temp multi-rank run tellraw @a [{"score":{"name":"#rank-print-temp","objective":"multi-rank","color":"yellow"},"color":"yellow"},{translate:"등 ","color":"yellow"}, {"selector":"@s","color":"aqua"},{translate:" | ","color":"yellow"},{"nbt":"multi-record[0]","storage":"timermain","interpret":true},{"nbt":"multi-record-subtick[0]","color":"yellow","storage":"timermain","interpret":false}]
execute if score team-battle multi-main matches 1 as @a if score @s multi-rank = #rank-print-temp multi-rank run function multiplay:system/game/team-rank

data remove storage minecraft:timermain multi-record[0]

execute if score #rank-print-temp multi-rank < #max-rank multi-rank run function multiplay:system/game/printrank