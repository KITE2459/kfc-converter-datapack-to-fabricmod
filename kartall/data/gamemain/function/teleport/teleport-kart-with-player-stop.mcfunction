execute as @e[tag=multi-spectator,distance=..5] at @s run function multiplay:system/spectator/spectate-target
execute as @e[tag=timeattack-spectator,distance=..5] at @s run function timeattack:system/spectator/spectate-target
execute as @e[tag=dev-attack-spectator,distance=..5] at @s run function devbattle:system/spectator/spectate-target
execute as @e[tag=updown-spectator,distance=..5] at @s run function updown:system/spectator/spectate-target
execute as @e[tag=parkour-spectator,distance=..5] at @s run function master:system/spectator/spectate-target
execute as @e[tag=master-spectator,distance=..5] at @s run function updown:system/spectator/spectate-target

execute on passengers if entity @s[tag=kartsaddle] on passengers run tag @s[type=player] add kartplayertemp

$execute as @a[tag=kartplayertemp] at @s run rotate @s $(xrot) $(yrot)
$execute at @s rotated $(xrot) $(yrot) on passengers run rotate @s[tag=kartdirection] ~ ~

$execute positioned $(x) $(y) $(z) align y positioned ~ ~0.05 ~ run tp ~ ~ ~
function gamemain:teleport/kfcsync

execute at @s run playsound minecraft:entity.enderman.teleport weather @a[tag=kartplayertemp] ~ ~ ~ 1 1 1

# tag @a[tag=kartplayertemp] add needsync
tag @a[tag=kartplayertemp] remove kartplayertemp

execute if score @n[tag=mykart,type=#kartmobil:kartmobil] kart-engine matches 9 run scoreboard players set @n[tag=mykart,type=#kartmobil:kartmobil] kartboostcount 1
execute if score @n[tag=mykart,type=#kartmobil:kartmobil] kart-engine matches 1005 run scoreboard players set @n[tag=mykart,type=#kartmobil:kartmobil] kartboostcount 1
execute if score @n[tag=mykart,type=#kartmobil:kartmobil] kart-engine matches 1006 run scoreboard players set @n[tag=mykart,type=#kartmobil:kartmobil] kartboostcount 1

scoreboard players add @s kartboostgauge 2000
scoreboard players set @s kartcollisiontime 50
scoreboard players set @s kartmove 0