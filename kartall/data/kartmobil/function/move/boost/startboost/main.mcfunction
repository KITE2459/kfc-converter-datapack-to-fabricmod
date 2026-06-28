execute if score @s kart-engine matches 1007 run return 1
execute if score @s kart-engine matches 1005..1006 run return 1

#fov 당기기
execute on passengers run scoreboard players add @s[tag=kartdirection] kartmove 2

#RUSH+ 출부 길이 늘리기
#execute if score @s[tag=!krp-extended] kart-engine matches 9 if score @s startboosttime matches 2 run function kartmobil:move/boost/startboost/krp-extend

#부스터 정보 전달
#effect give @a[tag=kart-listener] minecraft:dolphins_grace 3 169 true
execute if score @s startboosttime matches 25 on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/on-set-1
execute if score @s startboosttime matches 25 if score @s kart-engine matches 11 on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/ready-set-2

execute if score @s startboosttime matches 1 unless score @s kartboosttime matches 1.. on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/off-set-0

    #즉시 충돌 방지
    execute if score @s startboosttime matches 25 unless score @s kart-engine matches 4 run function kartmobil:rkey-ghost/show
    execute if score @s startboosttime matches 25 unless score @s kart-engine matches 4 run scoreboard players set @s kartcollisiontime 8

    #Z7 고스트 출부
    execute if score @s startboosttime matches 25 if score @s kart-engine matches 4 run scoreboard players set @s kartcollisiontime 41

    #RUSH+ 원커팅 출부
    execute if score @s startboosttime matches 13 if score @s kart-engine matches 9 run scoreboard players add @s kartboostgauge 1000

execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run playsound minecraft:entity.breeze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0 1
execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run playsound minecraft:entity.breeze.hurt neutral @a[tag=kart-listener] ~ ~ ~ 1 0 1

execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:crit ^ ^1 ^2 .25 .25 .25 1.25 25 normal @a[tag=kart-listener]
execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 10 normal @a[tag=kart-listener]

#바람이펙트
execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:crit ^ ^1 ^1 .25 .25 .25 2 25 normal @a[tag=kart-listener]
execute if score @s startboosttime matches 17 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:crit ^ ^1 ^1 .25 .25 .25 2 25 normal @a[tag=kart-listener]

execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 25 normal @a[tag=kart-listener]
execute if score @s startboosttime matches 13 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 25 normal @a[tag=kart-listener]
execute if score @s startboosttime matches 7 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 25 normal @a[tag=kart-listener]
execute if score @s startboosttime matches 1 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 25 normal @a[tag=kart-listener]

execute if score @s startboosttime matches 25 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:gust ^ ^1 ^3 .75 .75 .75 10 1 normal @a[tag=kart-listener]
execute if score @s startboosttime matches 13 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:gust ^ ^1 ^3 .75 .75 .75 10 1 normal @a[tag=kart-listener]
execute if score @s startboosttime matches 7 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:gust ^ ^1 ^3 .75 .75 .75 10 1 normal @a[tag=kart-listener]
execute if score @s startboosttime matches 1 on passengers rotated as @s[tag=kartdirection] rotated ~ 0 run particle minecraft:gust ^ ^1 ^3 .75 .75 .75 10 1 normal @a[tag=kart-listener]

execute at @p[tag=kartpassenger] run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.5 1 0.5

#출부 가속(A2는 적용되지 않음, 1.0, PRO는 너프)
execute store result storage kartmain kart-start-accel int 2 run scoreboard players get @s kartaccel
execute if score @s kart-engine matches 7..8 store result storage kartmain kart-start-accel int 1 run scoreboard players get @s kartaccel
execute store result score #kart-start-accel kartaccel run data get storage kartmain kart-start-accel

execute unless score @s kart-engine matches 6 run scoreboard players operation @s kartmove += #kart-start-accel kartaccel

scoreboard players remove @s startboosttime 1