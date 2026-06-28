#출부 / 루프 동안에는 R키 안 됨
execute on vehicle on vehicle on passengers if entity @s[tag=kartdirection,scores={startboosttime=1..},type=minecraft:item_display] run return 1
execute on vehicle on vehicle if entity @s[tag=kart-loop-kart] run return 1

execute as @n[tag=multi-spectator,distance=..10] at @s run function multiplay:system/spectator/spectate-target
execute as @n[tag=timeattack-spectator,distance=..10] at @s run function timeattack:system/spectator/spectate-target
execute as @n[tag=dev-attack-spectator,distance=..10] at @s run function devbattle:system/spectator/spectate-target
execute as @n[tag=updown-spectator,distance=..10] at @s run function updown:system/spectator/spectate-target

execute on vehicle on vehicle run tag @s add mykart

tag @s add kart-return-temp

#체크포인트로 이동
execute on vehicle on vehicle if data entity @s data.pos2 run data modify storage kartmain posx set from entity @s data.pos2[0]
execute on vehicle on vehicle if data entity @s data.pos2 run data modify storage kartmain posy set from entity @s data.pos2[1]
execute on vehicle on vehicle if data entity @s data.pos2 run data modify storage kartmain posz set from entity @s data.pos2[2]
execute on vehicle on vehicle if data entity @s data.pos2 at @s run function gamemain:rkey-tp with storage kartmain

#가까운 다음 체크포인트 보기
execute at @s as @e[tag=check-point,distance=..200,type=marker] if score @s check-num = @p[tag=kart-return-temp] check-next facing entity @s feet run rotate @p[tag=kart-return-temp] ~ ~
execute at @s as @e[tag=check-point,distance=..200,type=marker] if score @s check-num = @p[tag=kart-return-temp] check-next facing entity @s feet as @p[tag=kart-return-temp] on vehicle on vehicle on passengers run rotate @s[tag=kartdirection] ~ ~

tag @s remove kart-return-temp
tag @e[tag=kart-return-check] remove kart-return-check
tag @e[tag=kart-return-check-next] remove kart-return-check-next

#효과
scoreboard players set @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kartcollisiontime 50

scoreboard players set @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kartmove 0
scoreboard players add @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kartboostgauge 1500

scoreboard players set @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kartmovey 0

execute if score @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kart-engine matches 9 run scoreboard players set @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kartboostcount 1
execute if score @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kart-engine matches 1005 run scoreboard players set @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kartboostcount 1
execute if score @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kart-engine matches 1006 run scoreboard players set @e[tag=mykart,limit=1,type=#kartmobil:kartmobil] kartboostcount 1
function rkey:play

#/summon minecraft:marker ~ ~ ~ {Tags:["kartreturnpoint"]}

execute on vehicle on vehicle run function kartmobil:move/movetp/landing-tag-remove
execute on vehicle on vehicle run tag @s remove mykart