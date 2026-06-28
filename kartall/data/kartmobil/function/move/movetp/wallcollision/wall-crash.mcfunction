

execute unless block ~0.5 ~ ~ #kartmobil:ignoreblock on passengers store result entity @s[tag=kartdirectiontemp,type=item_display] Rotation[0] float -1 run data get entity @s[tag=kartdirectiontemp,type=item_display] Rotation[0]
execute unless block ~-0.5 ~ ~ #kartmobil:ignoreblock on passengers store result entity @s[tag=kartdirectiontemp,type=item_display] Rotation[0] float -1 run data get entity @s[tag=kartdirectiontemp,type=item_display] Rotation[0]

execute unless block ~ ~ ~0.5 #kartmobil:ignoreblock on passengers rotated as @s[tag=kartdirectiontemp,type=item_display] run rotate @s ~180 ~
execute unless block ~ ~ ~0.5 #kartmobil:ignoreblock on passengers store result entity @s[tag=kartdirectiontemp,type=item_display] Rotation[0] float -1 run data get entity @s[tag=kartdirectiontemp,type=item_display] Rotation[0]

execute unless block ~ ~ ~-0.5 #kartmobil:ignoreblock on passengers rotated as @s[tag=kartdirectiontemp,type=item_display] run rotate @s ~180 ~
execute unless block ~ ~ ~-0.5 #kartmobil:ignoreblock on passengers store result entity @s[tag=kartdirectiontemp,type=item_display] Rotation[0] float -1 run data get entity @s[tag=kartdirectiontemp,type=item_display] Rotation[0]

#속도 감쇄
execute store result storage minecraft:kartmain kart-wall-collision int 0.75 run scoreboard players get @s kartmove
execute store result score @s kartmove run data get storage minecraft:kartmain kart-wall-collision

scoreboard players set @s kartcollisiontime 8

#빙글빙글 도는 갓겜
execute if entity @s if predicate kartmobil:0.1random if predicate kartmobil:0.2random run tag @s add mad-crash-spin
execute if entity @s if predicate kartmobil:0.6random run tag @s[tag=mad-crash-spin] add mad-crash-spin-reverse

playsound minecraft:entity.player.attack.crit neutral @a ~ ~ ~ 1 2
playsound minecraft:entity.zombie.attack_iron_door neutral @a ~ ~ ~ 0.1 2
playsound minecraft:entity.generic.big_fall neutral @a ~ ~ ~ 1 1
playsound minecraft:entity.generic.small_fall neutral @a ~ ~ ~ 1 1
playsound minecraft:entity.zombie.attack_wooden_door neutral @a ~ ~ ~ 1 2