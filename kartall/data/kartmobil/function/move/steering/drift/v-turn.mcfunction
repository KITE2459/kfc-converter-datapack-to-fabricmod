playsound minecraft:block.fire.extinguish neutral @a[tag=kart-listener] ~ ~ ~ 0.1 1
playsound minecraft:block.nether_bricks.break neutral @a[tag=kart-listener] ~ ~ ~ 1 1.5

execute on passengers rotated as @s[tag=kartdirection] on vehicle rotated ~ 0 positioned ^0.5 ^0.1 ^-0.5 run particle minecraft:flame ~ ~ ~ 0 0 0 0.01 1 force @a[tag=kart-listener]
execute on passengers rotated as @s[tag=kartdirection] on vehicle rotated ~ 0 positioned ^-0.5 ^0.1 ^-0.5 run particle minecraft:flame ~ ~ ~ 0 0 0 0.01 1 force @a[tag=kart-listener]
execute on passengers rotated as @s[tag=kartdirection] on vehicle rotated ~ 0 positioned ^0.5 ^0.1 ^-0.5 run particle minecraft:campfire_cosy_smoke ^ ^ ^ 0 0 0 0 1 force @a[tag=kart-listener]
execute on passengers rotated as @s[tag=kartdirection] on vehicle rotated ~ 0 positioned ^-0.5 ^0.1 ^-0.5 run particle minecraft:campfire_cosy_smoke ^ ^ ^ 0 0 0 0 1 force @a[tag=kart-listener]

#V턴 감속
execute if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartmove -= @s kartcorner
execute if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartmove -= @s kartcorner