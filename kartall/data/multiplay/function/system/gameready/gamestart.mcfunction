# scoreboard players reset #load multi-main
# scoreboard players reset #pre multi-main
title @a times 0 20 20
title @a title {translate:"로딩중...","color":"green"}

scoreboard players set #game multi-main 1
scoreboard players set #time multi-main 1
# scoreboard players reset * multi-spectate-quit
scoreboard objectives remove multi-spectate-quit
scoreboard objectives add multi-spectate-quit dummy

scoreboard players reset @a nbs_retiresoun
scoreboard players reset @a nbs_completeso
scoreboard players reset @a nbs_complete2
scoreboard players reset @a nbs_rkey

tag @a[x=-26,y=-1,z=172,dx=22,dy=3,dz=-19] add kart-multi-player
execute as @a[tag=kart-multi-player] run function bgm-room:manage-bgm/stopbgm
scoreboard players reset @a[tag=kart-multi-player] trackselect-lap
scoreboard players reset @a[tag=kart-multi-player] multi-rank

execute as @a[tag=kart-multi-player] run ride @s dismount

#완주한 인원 초기화
scoreboard players reset #complete-player-count multi-rank

#게임 아이디 변동 및 플레이어에게 할당, 체크포인트 초기화
scoreboard players add #max-id game-id 1
execute as @a[tag=kart-multi-player] run scoreboard players operation @s game-id = #max-id game-id
execute as @a[tag=kart-multi-player] run function checkpoint:system/init

effect give @a[tag=kart-multi-player] minecraft:invisibility 1 1 true
effect give @a[tag=kart-multi-player] minecraft:unluck 1 1 true

function multiplay:system/lever/lock-on

#맵 설정 후 tp
scoreboard players set #map trackselect-map-id 0
execute store result score #map trackselect-map-id run data get entity @n[tag=trackselect-multi-marker] data.track.random
execute if score #map trackselect-map-id matches 1..5 run function multiplay:system/gameready/random
execute store result score #map trackselect-map-id run function trackselect:read-track-data/get-track-number with entity @n[tag=trackselect-multi-marker] data.track
execute store result score #max-lap trackselect-lap run function trackselect:read-track-data/getmaxlap with entity @n[tag=trackselect-multi-marker] data.track

execute as @a[tag=kart-multi-player] run function trackselect:read-track-data/movetomap with entity @n[tag=trackselect-multi-marker] data.track
execute as @n[tag=trackselect-multi-marker] run function trackselect:read-track-data/execute-etc-command

function multiplay:system/countdown-notify

    # forceloadextend
    # 공식 멀티전용 기능
    execute if score #is-official-multi multi-main matches 1 if score #forceloadextend kartmain matches 1 run function trackselect:forceload/all-unload
    execute if score #is-official-multi multi-main matches 1 if score #forceloadextend kartmain matches 1 run function multiplay:system/gameready/server-forceload-extend with entity @n[tag=trackselect-multi-marker] data.track

#게임 준비 상태로 바꾸기
data modify entity @e[tag=multi-state-text,limit=1] text set value [{translate:"경기 ","color":"yellow"},{translate:"관전하기","color":"aqua"}]

#관전방
tag @a[x=-34,y=3,z=159,dx=4,dy=4,dz=10] add multi-spectate-zone
