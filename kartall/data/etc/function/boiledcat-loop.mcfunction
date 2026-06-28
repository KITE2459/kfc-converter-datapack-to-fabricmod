scoreboard players add $boiledcat-spin kite-generic 1

execute if score $boiledcat-spin kite-generic matches 1 run execute as @e[sort=arbitrary,limit=25,distance=..10,tag=boiled_mine,type=item_display] at @s run data modify entity @s Rotation[0] set value 120f
execute if score $boiledcat-spin kite-generic matches 5 run execute as @e[sort=arbitrary,limit=25,distance=..10,tag=boiled_mine,type=item_display] at @s run data modify entity @s Rotation[0] set value 240f
execute if score $boiledcat-spin kite-generic matches 9 run execute as @e[sort=arbitrary,limit=25,distance=..10,tag=boiled_mine,type=item_display] at @s run data modify entity @s Rotation[0] set value 0f
execute if score $boiledcat-spin kite-generic matches 12.. run scoreboard players reset $boiledcat-spin kite-generic

playsound minecraft:block.deepslate.break master @a[distance=..30] 167 97 -1370 .2 0
particle block{block_state:"minecraft:deepslate_diamond_ore"} 166.5 97 -1370 0.6 0.6 0.6 1 20 normal


