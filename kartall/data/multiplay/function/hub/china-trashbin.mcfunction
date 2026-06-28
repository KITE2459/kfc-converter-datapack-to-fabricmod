scoreboard players set #china-trashcan multi-main 0
execute if predicate kartmobil:0.5random run scoreboard players set #china-trashcan multi-main 1

execute if score #china-trashcan multi-main matches 0 run summon area_effect_cloud ~ ~-0.25 ~ {Radius:15f,Particle:{type:"block",block_state:"minecraft:air"},CustomNameVisible:1b,Duration:21,CustomName:'중다이!'}
execute if score #china-trashcan multi-main matches 1 run summon area_effect_cloud ~ ~ ~ {Radius:15f,Particle:{type:"block",block_state:"minecraft:air"},CustomNameVisible:1b,Duration:21,CustomName:'리타이어입니다!'}
kill @s

execute at @s run playsound minecraft:block.composter.fill_success weather @a