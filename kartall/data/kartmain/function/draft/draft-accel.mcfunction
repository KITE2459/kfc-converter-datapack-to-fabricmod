tag @s remove kart-draft-charging

playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 0
playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 1
playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 0
playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 1
playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 0.75 1
playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 0.75 1

effect give @a[tag=kart-listener] minecraft:wind_charged 20 169 false
execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-draft/burst-set-2

scoreboard players add @s kartmove 1150

    #랠리엔진은 지속 스피드가 없으므로 임펄스를 버프
    execute if score @s kart-engine matches 1007 run scoreboard players add @s kartmove 1250

    #기어엔진 너프
    execute if score @s kart-engine matches 1005 run scoreboard players remove @s kartmove 750

    #RUSH+ 고스트 드래프트 + 가속2배
    execute if score @s kart-engine matches 9 run scoreboard players add @s kartmove 750
    execute if score @s kart-engine matches 9 run scoreboard players set @s kartcollisiontime 34

execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:crit ~ ~1 ~ .25 .25 .25 1.25 25 normal @a[distance=..15]