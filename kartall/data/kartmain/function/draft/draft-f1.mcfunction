execute if score @s kartmain matches 1.. run particle minecraft:crit ~ ~1 ~ .3 .3 .3 1 1 normal @a[distance=..15]
execute if score @s kartmain matches 1..29 run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.3 0.25
execute if score @s kartmain matches 30.. run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.15 1.0
execute if score @s kartmain matches 30.. run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.15 0.5
execute if score @s kartmain matches 30.. run function kartmain:draft/draft-f1-gear