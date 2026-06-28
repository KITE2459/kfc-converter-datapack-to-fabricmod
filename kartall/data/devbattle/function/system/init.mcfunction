
title @s times 0 20 20

#랩 설정
scoreboard players set @s trackselect-lap 0

execute store result score #max-lap trackselect-lap run function trackselect:read-track-data/getmaxlap with entity @n[tag=trackselect-devbattle-marker] data.track
function trackselect:read-track-data/movetomap with entity @n[tag=trackselect-devbattle-marker] data.track
execute as @n[tag=trackselect-devbattle-marker] run function trackselect:read-track-data/execute-etc-command

#쉐도우
    #쉐도우 저장소 초기화
    execute if score @s dev-count matches 1 run data modify storage devattack:dev-attack-shadows temp-shadow set value {pos:[],rotation:[],booster:[],model:"practicekart-org",playername:"Ghost",hideplayer:0}

    #쉐도우를 [dev-attack-shadows temp-shadow-play]에 로드
    execute store result storage devattack:dev-attack-shadows tempmap int 1 run scoreboard players get @s trackselect-map-id
    execute store result storage devattack:dev-attack-shadows tempdev int 1 run scoreboard players get #dev dev-count
    function devbattle:system/shadow/loadshadow with storage devattack:dev-attack-shadows

    #쉐도우가 있는지 없는지. 없다면 새로 기록하는 모드가 됨
    execute store result score #shadow-length trackselect-map-id run data get storage devattack:dev-attack-shadows temp-shadow-play.pos

scoreboard players add @s dev-count 1

function checkpoint:system/init

