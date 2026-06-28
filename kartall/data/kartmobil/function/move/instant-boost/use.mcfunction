tag @s add a2-using-instant-boost

scoreboard players add @s[tag=!burst-instant-boost] kartmove 250
scoreboard players add @s[tag=burst-instant-boost] kartmove 400

scoreboard players set @s[tag=!burst-instant-boost] kartboosttime 10
scoreboard players set @s[tag=burst-instant-boost] kartboosttime 7
execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/on-set-1

    #A2엔진 사운드 / 버스트 순부 분기
    execute if score @s kart-engine matches 6 as @e[limit=3] run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 1 1
    execute if score @s kart-engine matches 6 run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 1
    execute if score @s kart-engine matches 6 run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75 1
    execute if score @s kart-engine matches 6 run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75 1

    execute if score @s[tag=!burst-instant-boost] kart-engine matches 6 as @e[limit=3] run playsound minecraft:entity.breeze.hurt neutral @a[tag=kart-listener] ~ ~ ~ 0.9 0.7 0.9
    execute if score @s[tag=burst-instant-boost] kart-engine matches 6 as @e[limit=3] run playsound minecraft:entity.breeze.death neutral @a[tag=kart-listener] ~ ~ ~ 0.9 1 0.9

    #PRO엔진 사운드
    execute if score @s kart-engine matches 8 as @e[limit=2] run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1.3 1.3 1
    execute if score @s kart-engine matches 8 run playsound minecraft:entity.breeze.jump neutral @a[tag=kart-listener] ~ ~ ~ 2 0.7 1

execute rotated as @p[tag=kartpassenger] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 50 force @a[tag=kart-listener]
execute rotated as @p[tag=kartpassenger] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 20 force @a[tag=kart-listener]
execute rotated as @p[tag=kartpassenger] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 10 force @a[tag=!kart-listener]

execute on passengers run scoreboard players set @s[tag=kartdirection] kartdrifttime 0
