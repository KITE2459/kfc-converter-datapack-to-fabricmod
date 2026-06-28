scoreboard players add $factory-gear-spin kite-generic 1

execute if score $factory-gear-spin kite-generic matches 1 run execute as @e[sort=arbitrary,limit=41,distance=..10,tag=factory_gear,type=item_display] at @s run data modify entity @s Rotation[0] set value 120f
execute if score $factory-gear-spin kite-generic matches 31 run execute as @e[sort=arbitrary,limit=41,distance=..10,tag=factory_gear,type=item_display] at @s run data modify entity @s Rotation[0] set value 240f
execute if score $factory-gear-spin kite-generic matches 61 run execute as @e[sort=arbitrary,limit=41,distance=..10,tag=factory_gear,type=item_display] at @s run data modify entity @s Rotation[0] set value 0f
execute if score $factory-gear-spin kite-generic matches 90.. run scoreboard players reset $factory-gear-spin kite-generic

