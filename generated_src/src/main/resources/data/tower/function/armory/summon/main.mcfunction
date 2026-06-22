scoreboard players add @s amory-time 1
execute if entity @a[distance=..4] if score @s amory-time matches 10.. run tag @s add show-transform
execute if score @s amory-time matches 10.. run scoreboard players remove @s[tag=!show-transform] amory-time 1

execute store result score #temp game.return as @p run function tower:armory/interact/is-bought with entity @n[type=minecraft:item_display] item.components.minecraft:custom_data.Tower_Status

# 크기 줄어들기
execute if score @s amory-time matches 1 run data modify entity @s item set from block ~ ~-3 ~ Items[0]
execute if score #temp game.return matches 1 if score @s amory-time matches 1 run data merge entity @s {start_interpolation:0,interpolation_duration:5,billboard:"center",transformation:{scale:[0f,0f,0f],right_rotation:[0f,1f,0f,0f],translation:[0f,0f,0f]}}

# 타워 보이기
execute if score #temp game.return matches 1 if score @s amory-time matches 4 positioned ~ ~-0.5 ~ run kill @e[tag=armory-text,distance=..0.01]
execute if score #temp game.return matches 1 if score @s amory-time matches 4 run function tower:armory/summon/model with entity @s item.components.minecraft:custom_data

#인터랙션 및 설명
execute if score @s amory-time matches 1 run summon interaction ~ ~-0.5 ~ {width:2.5f,height:1.5f,Tags:["armory-interaction"]}
execute if score @s amory-time matches 1 positioned ~ ~-0.5 ~ run summon text_display ~ ~ ~ {view_range:0.2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[1f,0.1f,1.5f],scale:[0.5f,0.5f,0.5f]},text:{"bold":true,"color":"yellow",translate:"클릭해서 획득"}}
execute as @e[tag=armory-interaction,type=interaction] at @s run function tower:armory/summon/interaction

#타워 이름
execute if score @s amory-time matches 4 positioned ~ ~-0.5 ~ run summon text_display ~ ~ ~ {Tags:["armory-text"],view_range:0.2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[1f,0.25f,1.5f],scale:[0.75f,0.75f,0.75f]},background:-16777216}
execute if score @s amory-time matches 4 run data modify entity @n[tag=armory-text] text set from entity @s item.components.minecraft:item_name

execute if score @s amory-time matches 4 positioned ~ ~-0.5 ~ rotated ~ ~ as @e[distance=..0.001,type=text_display] run rotate @s ~ ~