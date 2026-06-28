scoreboard players add $planet-diamond-spin kite-generic 1

execute if score $planet-diamond-spin kite-generic matches 1 run execute as @e[sort=arbitrary,limit=3,distance=..25,tag=planet-diamond,type=item_display] at @s run data modify entity @s Rotation[0] set value 120f
execute if score $planet-diamond-spin kite-generic matches 31 run execute as @e[sort=arbitrary,limit=3,distance=..25,tag=planet-diamond,type=item_display] at @s run data modify entity @s Rotation[0] set value 240f
execute if score $planet-diamond-spin kite-generic matches 61 run execute as @e[sort=arbitrary,limit=3,distance=..25,tag=planet-diamond,type=item_display] at @s run data modify entity @s Rotation[0] set value 0f
execute if score $planet-diamond-spin kite-generic matches 90.. run scoreboard players reset $planet-diamond-spin kite-generic

