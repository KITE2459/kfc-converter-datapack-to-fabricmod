tag @s remove kart-drifting

#A2 - 순부 쿨타임
execute if score @s kartdrifttime matches 8.. if entity @s[tag=force-instant-boost] on passengers run scoreboard players set @s[tag=kartdirection] kartdrifttime 20
execute if score @s kartdrifttime matches 8.. if score @s[tag=!burst-instant-boost] kart-engine matches 6 on passengers run scoreboard players set @s[tag=kartdirection] kartdrifttime 20
execute if score @s kartdrifttime matches 7.. if score @s[tag=burst-instant-boost] kart-engine matches 6 on passengers run scoreboard players set @s[tag=kartdirection] kartdrifttime 20
execute if score @s kartdrifttime matches 8.. if score @s kart-engine matches 8 on passengers run scoreboard players set @s[tag=kartdirection] kartdrifttime 20

#톡톡이모드 톡톡이 끝날때 감속
scoreboard players remove @s[tag=drag-accel,scores={kartdrifttime=8..}] kartmove 600
scoreboard players remove @s[tag=drag-accel,scores={kartdrifttime=14..}] kartmove 600
scoreboard players remove @s[tag=drag-accel,scores={kartdrifttime=20..}] kartmove 600
scoreboard players set @s kartdrifttime 0

#팀부 게이지 채우기
scoreboard players operation #temp kart-teamboost-gauge = @s kart-teamboost-gauge
scoreboard players operation @s kart-teamboost-gauge = @s kartboostgauge
scoreboard players operation @s kart-teamboost-gauge -= #temp kart-teamboost-gauge

execute if score @s kartboostgaugecharge matches 100.. run scoreboard players set @s kart-teamboost-gauge 0

#드리프트 이펙트 안 보이게
function kartmobil:move/steering/drift/control-drift-effect/hide

#F1 엔진 드리프트 게이지 초기화
execute if score @s kart-engine matches 1006 run scoreboard players operation @s kartdrift = @s f1-kartdrift-origin

execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-drift/end-0