title @s title ""
title @s subtitle {translate:"체크포인트 통과","color":"aqua"}
function lapsound:play

scoreboard players add @s check-prev 1
scoreboard players add @s check-num 1
scoreboard players add @s check-next 1

#만약 B를 증가시킬 때, B의 번호에 해당하는 체크포인트가 존재하지 않는다면, 해당 플레이어는 마지막 체크포인트 통과로 간주하고, 결승선을 통과할 때 랩 카운트가 올라가거나 1랩 맵의 경우 완주하며, A는 0, B는 1로 초기화됨.
tag @s add check-pass-last
execute as @e[distance=..200,tag=check-point,type=marker] if score @s check-num = @p[tag=check-player-temp,distance=..5] check-num run tag @s add checkpoint-passed-temp
execute as @e[distance=..200,tag=check-point,type=marker] if score @s check-num = @p[tag=check-player-temp,distance=..5] check-next run tag @p[tag=check-player-temp,distance=..5] remove check-pass-last


#만약 두 맵이 가까이 붙어있을 경우 마지막 체크포인트 감지가 안될 수 있음. 이런 경우 명시적으로 마지막 체크포인트를 정하도록 하고 있음
execute as @e[distance=..200,tag=check-last-tag,tag=check-point,type=marker] if score @s check-num = @p[tag=check-player-temp,distance=..5] check-num run tag @p[tag=check-player-temp,distance=..5] add check-pass-last

#위치 기록해두고 R키 위치로 활용
function checkpoint:system/memory-pass-position

#멀티 플레이일 경우 순위 감지 실행
execute if entity @a[tag=kart-multi-player] run function checkpoint:system/multi-sort/main

tag @e[tag=checkpoint-passed-temp] remove checkpoint-passed-temp