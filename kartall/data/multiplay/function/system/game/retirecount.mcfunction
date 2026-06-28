scoreboard players add #retire-time multi-main 1

execute if score #retire-time multi-main matches 1 run title @a title {translate:"10","color":"gray"}
execute if score #retire-time multi-main matches 20 run title @a title {translate:"9","color":"gray"}
execute if score #retire-time multi-main matches 40 run title @a title {translate:"8","color":"gray"}
execute if score #retire-time multi-main matches 60 run title @a title {translate:"7","color":"gray"}
execute if score #retire-time multi-main matches 80 run title @a title {translate:"6","color":"gray"}
execute if score #retire-time multi-main matches 100 run title @a title {translate:"5","color":"gray"}
execute if score #retire-time multi-main matches 120 run title @a title {translate:"4","color":"gray"}
execute if score #retire-time multi-main matches 140 run title @a title {translate:"3","color":"gray"}
execute if score #retire-time multi-main matches 160 run title @a title {translate:"2","color":"gray"}
execute if score #retire-time multi-main matches 180 run title @a title {translate:"1","color":"gray"}
execute if score #retire-time multi-main matches 200 run title @a title {translate:"경기 종료","color":"gray"}

execute if score #retire-time multi-main matches 1 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 20 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 40 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 60 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 80 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 100 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 120 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 140 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 160 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 180 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.785
execute if score #retire-time multi-main matches 200 as @a at @s run playsound minecraft:item.goat_horn.sound.5 weather @s ~ ~ ~ 1 1
#남은 사람은 전부 리타
execute if score #retire-time multi-main matches 200 as @a[tag=kart-multi-player] at @s run function bgm-room:manage-bgm/stopbgm
execute if score #retire-time multi-main matches 200 as @a[tag=kart-multi-player] at @s run function retiresound:play
execute if score #retire-time multi-main matches 240 as @a[tag=kart-multi-player] at @s run function multiplay:system/game/retire
execute if score #retire-time multi-main matches 240 run function multiplay:system/game/gameend
