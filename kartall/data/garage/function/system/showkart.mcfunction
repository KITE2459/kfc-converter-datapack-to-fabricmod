scoreboard players add @s garage-time 1
execute if entity @a[distance=..4] if score @s garage-time matches 10.. run tag @s add show-transform
execute if score @s garage-time matches 10.. run scoreboard players remove @s[tag=!show-transform] garage-time 1

execute if entity @s[tag=garage-show-unsecurity] positioned ~ ~-3 ~ if block ~ ~ ~ chest run function garage:system/security-chest/change-to-barrel

#크기 줄어들기
execute if score @s garage-time matches 1 run data modify entity @s item set from block ~ ~-3 ~ Items[0]
execute if score @s garage-time matches 1 run data merge entity @s {start_interpolation:0,interpolation_duration:5,billboard:"none",transformation:{scale:[0f,0f,0f],right_rotation:[0f,1f,0f,0f],translation:[0f,0f,0f]}}

#카트 보이기
execute if score @s garage-time matches 4 positioned ~ ~-0.5 ~ run kill @e[tag=garage-text,distance=..0.01]
execute if score @s garage-time matches 4 run function garage:system/summon-kart-model with entity @s item.components.minecraft:custom_data

#인터랙션 및 설명
execute if score @s garage-time matches 1 run summon interaction ~ ~-0.5 ~ {width:2.5f,height:1.5f,Tags:["garage-interaction"]}
execute if score @s garage-time matches 1 positioned ~ ~-0.5 ~ run summon text_display ~ ~ ~ {view_range:0.2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[1f,0.1f,1.5f],scale:[0.5f,0.5f,0.5f]},text:{"bold":true,"color":"yellow",translate:"클릭해서 획득"}}
execute as @e[tag=garage-interaction,type=interaction] at @s run function garage:system/interaction

#function garage:system/give-kart
#부스터와 모델 초기화
execute if score @s garage-time matches 4 run function garage:system/boost-and-model-init

#카트 이름
execute if score @s garage-time matches 4 positioned ~ ~-0.5 ~ run summon text_display ~ ~ ~ {Tags:["garage-text"],view_range:0.2,transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[1f,0.25f,1.5f],scale:[0.75f,0.75f,0.75f]},background:-16777216}
execute if score @s garage-time matches 4 run data modify entity @n[tag=garage-text] text set from entity @s item.components.minecraft:custom_name

#부스터 및 변신
execute if score @s garage-time matches 15 positioned ~ ~-0.5 ~ run particle minecraft:trial_spawner_detection_ominous ~ ~ ~ .5 .5 .5 0 20

execute if score @s garage-time matches 15 run scoreboard players set @s kartboosttime 37
execute if score @s garage-time matches 15 positioned ~ ~-0.5 ~ run function kartmobil:move/boost/boost-effect/show-boost-model/show-block
execute if score @s garage-time matches 30 positioned ~ ~-0.5 ~ run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame


execute if score @s garage-time matches 30.. if entity @s[tag=kart-use-run-anime] run function garage:system/show-run-anime