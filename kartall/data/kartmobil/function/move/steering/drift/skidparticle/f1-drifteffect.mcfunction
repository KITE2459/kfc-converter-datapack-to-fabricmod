playsound minecraft:block.fire.extinguish neutral @a[tag=kart-listener] ~ ~ ~ 0.1 1
playsound minecraft:entity.rabbit.death neutral @a[tag=kart-listener] ~ ~ ~ 0.5 0.75 0.5
playsound minecraft:entity.axolotl.hurt neutral @a[tag=kart-listener] ~ ~ ~ 0.15 1.75 0.15

#파티클
# scoreboard players operation #kartdriftparticle kartmain = @s kartmove

tag @a[distance=..0.3] add kartskidshow
tag @a[tag=kart-listener] add kartskidshow

execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 positioned ^0.5 ^0.1 ^-0.5 run particle minecraft:campfire_cosy_smoke ^ ^ ^ 0 0 0 0 1
execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 positioned ^-0.5 ^0.1 ^-0.5 run particle minecraft:campfire_cosy_smoke ^ ^ ^ 0 0 0 0 1

tag @a[tag=kartskidshow] remove kartskidshow