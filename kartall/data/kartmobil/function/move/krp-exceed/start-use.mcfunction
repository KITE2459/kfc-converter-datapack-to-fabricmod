playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 0
playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 1
playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 0
playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 1
playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 0.75 1
playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 0.75 1

#FOV 당기기
scoreboard players operation #krp-exceed-fov kartboostgauge = #krp-exceed kartboostgauge
scoreboard players operation #krp-exceed-fov kartboostgauge /= #kart100 kartmain
execute on passengers unless score @s[tag=kartdirection] kartmove matches 25.. run scoreboard players operation @s kartmove += #krp-exceed-fov kartboostgauge

#구간별 익시드 고정 가속
scoreboard players add @p[tag=kartpassenger,scores={kartboostgauge=0..2999}] kartboostgauge 1000
scoreboard players add @p[tag=kartpassenger,scores={kartboostgauge=3000..5899}] kartboostgauge 2000
scoreboard players add @p[tag=kartpassenger,scores={kartboostgauge=5900..}] kartboostgauge 4000

#익시드 동적 가속
scoreboard players operation #krp-exceed kartboostgauge = @p[tag=kartpassenger] kartboostgauge
scoreboard players operation #krp-exceed kartboostgauge /= #kart2 kartmain
scoreboard players operation @s kartmove += #krp-exceed kartboostgauge

#드래프트 발동중 너프
execute unless score @s kartmain matches 60..100 run scoreboard players remove @p[tag=kartpassenger,scores={kartboostgauge=0..2999}] kartmove 1150
execute unless score @s kartmain matches 60..100 run scoreboard players remove @p[tag=kartpassenger,scores={kartboostgauge=3000..5899}] kartmove 2250
execute unless score @s kartmain matches 60..100 run scoreboard players remove @p[tag=kartpassenger,scores={kartboostgauge=5900..}] kartmove 4250

scoreboard players set @p[tag=kartpassenger] kartboostgauge 0

execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:crit ~ ~1 ~ .25 .25 .25 1.25 25 normal @a[distance=..15]
execute on passengers rotated as @s[tag=kartdirection,type=item_display] on vehicle rotated ~ 0 run particle minecraft:gust ~ ~1 ~ .75 .75 .75 10 2 normal @a[distance=..15]

scoreboard players set @s kartmain 120
tag @s remove kart-draft-charging