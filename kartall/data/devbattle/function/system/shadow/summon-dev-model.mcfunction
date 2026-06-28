execute positioned ~ ~-1 ~ if score #dev dev-count matches 1 run function devbattle:system/shadow/model/logmodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 2 run function devbattle:system/shadow/model/kitemodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 3 run function devbattle:system/shadow/model/anothermodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 4 run function devbattle:system/shadow/model/pangchmodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 5 run function devbattle:system/shadow/model/nektermodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 6 run function devbattle:system/shadow/model/pengmodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 7 run function devbattle:system/shadow/model/gdmodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 8 run function devbattle:system/shadow/model/towermodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 9 run function devbattle:system/shadow/model/siditemodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 10 run function devbattle:system/shadow/model/daomodel
execute positioned ~ ~-1 ~ if score #dev dev-count matches 11 run function devbattle:system/shadow/model/ecycemodel

#닉네임 소환
summon text_display ~ ~ ~ {Tags:["shadow-text","kart-shadow-player","shadow-models"],billboard:"vertical",see_through:1b,alignment:"center",transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.5f,0f],scale:[1f,1f,1f]},text:{"color":"white",translate:"Shadow"}}
data modify entity @e[tag=shadow-text,limit=1] text set from storage devattack:dev-attack-shadows temp-shadow-play.playername

execute positioned ~ ~-1 ~ run tag @e[type=#kartmobil:kartmodels,distance=..0.01] add kart-shadow-player
tag @e[tag=kart-shadow-player,type=#kartmobil:kartmodels] add shadow-models

summon cod ~ ~ ~ {Tags:["kart-shadow-saddle","shadow-models"],NoAI:1b,Silent:1b,Invulnerable:1b,NoGravity:1b,DeathLootTable:"minecraft:empty"}
ride @n[tag=kart-shadow-saddle] mount @n[tag=shadow-models,tag=shadow-main,type=item_display]
effect give @n[tag=kart-shadow-saddle] minecraft:invisibility infinite 1 true

execute as @e[tag=kart-shadow-player,type=#kartmobil:kartmodels] run ride @s dismount
execute as @e[tag=kart-shadow-player,type=#kartmobil:kartmodels] run ride @s mount @n[tag=kart-shadow-saddle]
execute as @e[tag=kart-shadow-player,type=#kartmobil:kartmodels] run data merge entity @s {teleport_duration:1}

#안장높이조절
execute store result score #saddle-height kartdefense run data get storage devattack:dev-attack-shadows temp-shadow-play.saddle-height
execute if score #dev dev-count matches 6 run scoreboard players set #saddle-height kartdefense 175
execute store result entity @n[tag=kart-shadow-saddle] attributes[{id:"minecraft:scale"}].base float 0.0116665 run scoreboard players add #saddle-height kartdefense 86