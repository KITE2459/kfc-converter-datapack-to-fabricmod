playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75 1
playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75 1

execute rotated as @p[tag=kartpassenger] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 10 normal @a[tag=kart-listener]
execute rotated as @p[tag=kartpassenger] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 8 normal @a[tag=kart-listener]

#부스터 시간(카트 성능의 부스터시간 대입)
scoreboard players operation @s kartboosttime = @s kartboostduration
scoreboard players remove @s kartboostcount 1

#부능효과 게이지
# execute if score @s[tag=!kart-charger-active] kart-engine matches 10 run scoreboard players add @p[tag=kartpassenger] kartboostgauge 1200
execute if score @s[tag=!kart-charger-active] kart-engine matches 10 if score @p[tag=kartpassenger] kartboostgauge matches ..6000 run scoreboard players add @p[tag=kartpassenger] kartboostgauge 1200
#퓨전부스터
tag @s[tag=kart-fusion-boost] add kart-fusion-boost-use

#부스터 제어신호 / SR 연속 부스터
execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/on-set-1
execute if score @s kart-engine matches 11 if score @s kart-transform-time-sr matches 14 on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/ready-set-2