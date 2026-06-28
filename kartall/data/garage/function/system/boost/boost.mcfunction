scoreboard players remove @s kartboosttime 1

#뭐하누~ 으하하하
execute if score @s kartboosttime matches 48 if entity @s[tag=dog] at @a[limit=1,sort=arbitrary,tag=kartpassenger,distance=..0.3] run playsound minecraft:entity.wolf.ambient neutral @a[distance=..0.1]
execute if score @s kartboosttime matches 42 if entity @s[tag=dog] at @a[limit=1,sort=arbitrary,tag=kartpassenger,distance=..0.3] run playsound minecraft:entity.wolf.ambient neutral @a[distance=..0.1]

#듀얼부스터
execute if score @s kartboosttime matches 37 run function kartmobil:move/boosteffect/dualboostcharge
execute if score @s kartboosttime matches ..25 run function kartmobil:move/boosteffect/dualboost

execute if score @s kartboosttime matches 26.. unless score @s kartboosttime matches 32..40 rotated as @e[limit=1,sort=arbitrary,distance=..0.00001,tag=kartdirectiontemp,type=marker] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 1.25 4 force @a

execute if score @s kartboosttime matches ..2 run function kartmobil:move/boosteffect/dualboostend

