playsound minecraft:block.barrel.open neutral @a[tag=kart-listener] ~ ~ ~ 0.5 0.5

execute if score #kartspeed kartmove matches 13.. at @a[tag=kartpassenger] run playsound minecraft:block.barrel.open neutral @a[tag=kart-listener] ~ ~ ~ 0.15 0.5 0.5
execute if score #kartspeed kartmove matches 32.. at @a[tag=kartpassenger] run playsound minecraft:block.barrel.open neutral @a[tag=kart-listener] ~ ~ ~ 0.15 0.7 0.5

#fov 조절용
scoreboard players operation #kartspeedtemp kartmove = #kartspeed kartmove
execute on passengers if score @s[tag=kartdirection] kartmove matches 1.. run scoreboard players operation #kartspeedtemp kartmove += @s kartmove
execute store result storage kartmain kartspeedfov float 0.0006 run scoreboard players get #kartspeedtemp kartmove

#엔진음 조절용
scoreboard players operation #kartspeedtemp kartmove = #kartspeed kartmove
execute if score #kartspeedtemp kartmove matches 192.. run scoreboard players set #kartspeedtemp kartmove 192
execute store result storage kartmain kartsound float 0.009 run scoreboard players add #kartspeedtemp kartmove 30

execute if score #kartspeed kartmove matches 32.. run function kartmobil:sound-and-fov/new/soundmacro with storage kartmain
execute store result score #kartspeedtemp kartmove run data get storage kartmain kartspeedfov 100000
execute as @p[tag=kartpassenger] run function kartmobil:sound-and-fov/speedfov



