
#충돌 성공
playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~ ~ ~ 0.5 2
particle flame ^ ^ ^0.4 .04 .04 .04 0.1 1 normal @a[tag=kart-listener]

#기어엔진 벽감속
execute if score @s kart-engine matches 1005 run scoreboard players remove @s kartmove 20

#벽충돌페널티
execute if entity @s[tag=wall-crash] unless score @s kartcollisiontime matches 1.. run function kartmobil:move/movetp/wallcollision/wall-crash