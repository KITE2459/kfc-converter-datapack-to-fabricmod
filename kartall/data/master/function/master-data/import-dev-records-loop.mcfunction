# 맵 ID 매크로 실행
execute store result storage master:temp iterator int 1 run scoreboard players get #map_iterator master
function master:master-data/import-dev-records-check with storage master:temp

# 다음 반복 (최대 999)
scoreboard players add #map_iterator master 1
execute if score #map_iterator master matches ..999 run function master:master-data/import-dev-records-loop
