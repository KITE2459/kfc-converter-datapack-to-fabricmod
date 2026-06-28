execute as @n[tag=kartmobil,distance=..50,type=#kartmobil:kartmobil] if score @s kartmove matches 278..6950 run scoreboard players remove @s kartmove 350
execute as @n[tag=kartmobil,distance=..50,type=#kartmobil:kartmobil] if score @s kartmove matches 6951..13900 run scoreboard players remove @s kartmove 800
execute as @n[tag=kartmobil,distance=..50,type=#kartmobil:kartmobil] if score @s kartmove matches 13901.. run scoreboard players remove @s kartmove 1100
particle minecraft:snowflake ~ ~0.3 ~ 0.5 0.3 0.5 0 10