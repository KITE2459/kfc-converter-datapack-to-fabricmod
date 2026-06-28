scoreboard players remove @s kartmove 750

execute on passengers rotated as @s[tag=kartdirection] on vehicle rotated ~ 0 positioned ^0.5 ^0.1 ^-0.5 run particle minecraft:flame ~ ~ ~ 0 0 0 0.01 1 force @a[tag=kart-listener]
execute on passengers rotated as @s[tag=kartdirection] on vehicle rotated ~ 0 positioned ^-0.5 ^0.1 ^-0.5 run particle minecraft:flame ~ ~ ~ 0 0 0 0.01 1 force @a[tag=kart-listener]

playsound minecraft:entity.axolotl.hurt neutral @a[tag=kart-listener] ~ ~ ~ 0.15 1.75 0.15
playsound minecraft:block.nether_bricks.break neutral @a[tag=kart-listener]