
title @s times 0 20 20
#랩 설정
scoreboard players set @s trackselect-lap 0

execute store result score #max-lap trackselect-lap run function trackselect:read-track-data/getmaxlap with entity @n[tag=trackselect-timeattack-marker] data.track
function trackselect:read-track-data/movetomap with entity @n[tag=trackselect-timeattack-marker] data.track
execute as @n[tag=trackselect-timeattack-marker] run function trackselect:read-track-data/execute-etc-command

#쉐도우
    #쉐도우 저장소 초기화
    execute if score @s timecount matches 1 run data modify storage timeattack:timeattack-shadows temp-shadow set value {pos:[],rotation:[],booster:[],model:"practicekart-org",playername:"Ghost",hideplayer:0}

    #쉐도우를 [timeattack-shadows temp-shadow-play]에 로드
    execute store result storage timeattack:timeattack-shadows tempmap int 1 run scoreboard players get @s trackselect-map-id
    function timeattack:system/shadow/loadshadow with storage timeattack:timeattack-shadows

    #쉐도우가 있는지 없는지
    execute store result score #shadow-length trackselect-map-id run data get storage timeattack:timeattack-shadows temp-shadow-play.pos

scoreboard players add @s timecount 1

function checkpoint:system/init

