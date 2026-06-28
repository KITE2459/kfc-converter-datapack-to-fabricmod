#마찰저항
execute if score @s kartmove matches 1.. run scoreboard players remove @s kartmove 20
execute if score @s kartmove matches 1000.. if block ~ ~-0.5 ~ soul_sand run tag @p[tag=kartpassenger] add kartdeceleration
execute if score @s kartmove matches 1000.. unless block ~ ~-0.5 ~ soul_sand if block ~ ~-1.5 ~ soul_sand run tag @p[tag=kartpassenger] add kartdeceleration

#소울샌드저항
execute if entity @p[tag=kartpassenger,tag=kartdeceleration] run scoreboard players remove @s kartmove 350

#공기저항
execute if score @s kart-engine matches 1007 run return run function kartmobil:move/resistance/airresist-rally
execute if score @s kart-engine matches 1005 run return run function kartmobil:move/resistance/airresist-gear
execute if score @s kart-engine matches 1006 run return run function kartmobil:move/resistance/airresist-f1
function kartmobil:move/resistance/airresist