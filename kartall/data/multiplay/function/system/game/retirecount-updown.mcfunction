scoreboard players add #retire-time multi-main 1

execute if score #retire-time multi-main matches 1 run title @a title {translate:"30","color":"gray"}
title @a subtitle {translate:"1위가 완주했습니다!","color":"yellow"}

execute if score #retire-time multi-main matches 200 run title @a title {translate:"20","color":"gray"}
execute if score #retire-time multi-main matches 300 run title @a title {translate:"15","color":"gray"}

execute if score #retire-time multi-main matches 401 run title @a title {translate:"10","color":"gray"}
execute if score #retire-time multi-main matches 420 run title @a title {translate:"9","color":"gray"}
execute if score #retire-time multi-main matches 440 run title @a title {translate:"8","color":"gray"}
execute if score #retire-time multi-main matches 460 run title @a title {translate:"7","color":"gray"}
execute if score #retire-time multi-main matches 480 run title @a title {translate:"6","color":"gray"}
execute if score #retire-time multi-main matches 500 run title @a title {translate:"5","color":"gray"}
execute if score #retire-time multi-main matches 520 run title @a title {translate:"4","color":"gray"}
execute if score #retire-time multi-main matches 540 run title @a title {translate:"3","color":"gray"}
execute if score #retire-time multi-main matches 560 run title @a title {translate:"2","color":"gray"}
execute if score #retire-time multi-main matches 580 run title @a title {translate:"1","color":"gray"}
execute if score #retire-time multi-main matches 600 run title @a title {translate:"경기 종료","color":"gray"}


execute if score #retire-time multi-main matches 1 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 200 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 300 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785

execute if score #retire-time multi-main matches 401 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 420 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 440 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 460 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 480 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 500 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 520 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 540 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 560 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 580 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 600 as @a at @s run playsound minecraft:item.goat_horn.sound.5 weather @s ~ ~ ~ 1 1
#남은 사람은 전부 리타
execute if score #retire-time multi-main matches 640 as @a[tag=kart-multi-player] at @s run function multiplay:system/game/retire
execute if score #retire-time multi-main matches 640 run function multiplay:system/game/gameend
