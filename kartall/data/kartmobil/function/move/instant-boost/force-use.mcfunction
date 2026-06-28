#execute on passengers if score @s[tag=kartdirection] startboosttime matches 1.. run return 1

tag @s add a2-using-instant-boost

scoreboard players add @s kartmove 250
scoreboard players set @s kartboosttime 10
execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/on-set-1

execute as @e[limit=3] run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 1.5
playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 1
playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75 1
playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75 1

execute rotated as @p[tag=kartpassenger] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 50 force @a[tag=kart-listener]
execute rotated as @p[tag=kartpassenger] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 20 force @a[tag=kart-listener]
execute rotated as @p[tag=kartpassenger] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 10 force @a[tag=!kart-listener]

execute on passengers run scoreboard players set @s[tag=kartdirection] kartdrifttime 0