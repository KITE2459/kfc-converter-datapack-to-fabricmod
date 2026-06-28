tag @s remove a2-using-instant-boost

scoreboard players set #pad-scale kartmovey 9000
execute if score @s kart-engine matches 1005 run scoreboard players set #pad-scale kartmovey 3000
execute if score @s kart-engine matches 1006 run scoreboard players set #pad-scale kartmovey 3000
# execute if score @s kart-engine matches 1007 run scoreboard players set #pad-scale kartmovey 3000

scoreboard players set #pad-third-text-exist kartmovey 0

#방향 변경
data modify storage minecraft:kartmain pad-no-text-string set value '""'
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs store result score #pad-second-text-exist kartmovey run data modify storage minecraft:kartmain pad-no-text-string set from block ~ ~-1 ~ front_text.messages[1]
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs if score #pad-second-text-exist kartmovey matches 1 run data modify storage minecraft:kartmain boostpaddirection set from block ~ ~-1 ~ front_text.messages[1]
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs if score #pad-second-text-exist kartmovey matches 1 run function kartmobil:move/movetp/jump-and-boost/pad-direction-fix-macro with storage minecraft:kartmain

#패드 파워
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs run data modify storage minecraft:kartmain jumppadscale set from block ~ ~-1 ~ front_text.messages[0]
execute if block ~ ~-1 ~ #minecraft:all_hanging_signs run function kartmobil:move/movetp/jump-and-boost/pad-power-scale-macro with storage minecraft:kartmain
scoreboard players operation @s kartmove += #pad-scale kartmovey

#이펙트
execute if block ~ ~1 ~ #kartmobil:ignoreblock at @a[tag=kartpassenger] run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
execute if block ~ ~1 ~ #kartmobil:ignoreblock at @a[tag=kartpassenger] run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
execute if block ~ ~1 ~ #kartmobil:ignoreblock at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75

execute if block ~ ~1 ~ #kartmobil:ignoreblock at @s rotated as @a[tag=kartpassenger] run particle minecraft:crit ^ ^1 ^2 .25 .25 .25 1.25 50 force @a
execute if block ~ ~1 ~ #kartmobil:ignoreblock at @s rotated as @a[tag=kartpassenger] run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 10 force @a
execute if block ~ ~1 ~ #kartmobil:ignoreblock at @s rotated as @a[tag=kartpassenger] run particle minecraft:soul_fire_flame ^ ^1 ^2 .25 .25 .25 1 75 force @a

execute on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kart-boost] run function kartmobil:move/boost/boost-effect/force-cut-boost

scoreboard players set @s kartboostpadtime 8
scoreboard players set @s kartboosttime 15

execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/on-set-1
execute if score @s kart-engine matches 11 on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/ready-set-2