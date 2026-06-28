playsound minecraft:block.fire.extinguish neutral @a[tag=kart-listener] ~ ~ ~ 0.1 1
playsound minecraft:entity.rabbit.death neutral @a[tag=kart-listener] ~ ~ ~ 0.5 0.75 0.5
playsound minecraft:entity.axolotl.hurt neutral @a[tag=kart-listener] ~ ~ ~ 0.15 1.75 0.15

execute on passengers rotated as @s[tag=kartdirection] on vehicle rotated ~ 0 positioned ^0.5 ^0.1 ^-0.5 run particle minecraft:campfire_cosy_smoke ^ ^ ^ 0 0 0 0 1
execute on passengers rotated as @s[tag=kartdirection] on vehicle rotated ~ 0 positioned ^-0.5 ^0.1 ^-0.5 run particle minecraft:campfire_cosy_smoke ^ ^ ^ 0 0 0 0 1