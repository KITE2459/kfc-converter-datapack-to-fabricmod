
data modify entity @s data.loop-data set from entity @n[tag=loop-main,type=area_effect_cloud] data
data modify entity @s data.loop-data.anglespeed-real set from entity @s data.loop-data.anglespeed
scoreboard players reset @s loop-main

execute on passengers run data modify entity @s[tag=kartdirection] Rotation set from entity @n[tag=loop-main,type=area_effect_cloud] data.startrotation
scoreboard players add @s kartboostgauge 2000

execute on passengers run scoreboard players set @s[tag=kartdirection] loop-main 0
execute if entity @s[tag=!karthideplayer] run function gamemain:loopcourse/summon-player

execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-allow-model-rotation/disabled-0

    #역방향회전일 시
    tag @s remove kart-reverse-loop
    execute store result score #anglespeed loop-main run data get entity @s data.loop-data.anglespeed-real
    execute if score #anglespeed loop-main matches 1.. run tag @s add kart-reverse-loop
    execute if score #anglespeed loop-main matches 1.. run function kartmobil:flip-kart/main
    execute if score #anglespeed loop-main matches 1.. on passengers run scoreboard players set @s[tag=kartdirection] loop-main 1

tag @s add kart-loop-kart
tag @s remove kartmobil