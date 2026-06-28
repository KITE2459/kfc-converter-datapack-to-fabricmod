time set day
weather clear

execute if score #map trackselect-map-id matches -1 run return 1

data remove storage track-list number-search-result
execute store result storage gamemain:getmapdata number int 1 run scoreboard players get #map trackselect-map-id
function trackselect:read-track-data/get-track-by-number/main with storage gamemain:getmapdata

execute unless data storage track-list number-search-result run return 1

data modify entity @e[tag=gamemain-mapdata-marker,limit=1] data.track set from storage track-list number-search-result

execute store result score #max-lap trackselect-lap run function trackselect:read-track-data/getmaxlap with entity @n[tag=gamemain-mapdata-marker] data.track
function trackselect:read-track-data/movetomap with entity @e[tag=gamemain-mapdata-marker,limit=1] data.track
execute as @e[tag=gamemain-mapdata-marker,limit=1] run function trackselect:read-track-data/execute-etc-command