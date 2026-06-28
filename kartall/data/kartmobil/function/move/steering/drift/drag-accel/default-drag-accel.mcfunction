execute unless score #kartangle kartmain matches -600..600 run return 0

execute if score @s kartdrifttime matches 10.. run scoreboard players add @s kartmove 2
execute if score @s kartdrifttime matches 15.. run scoreboard players add @s kartmove 4
execute if score @s kartdrifttime matches 20.. run scoreboard players add @s kartmove 7
execute if score @s kartdrifttime matches 25.. run scoreboard players add @s kartmove 11
execute if score @s kartdrifttime matches 30.. run scoreboard players add @s kartmove 16
execute if score @s kartdrifttime matches 35.. run scoreboard players add @s kartmove 22

execute if score @s kartdrifttime matches 15.. run scoreboard players add @p[tag=kartpassenger] kartdrift 1
execute if score @s kartdrifttime matches 20.. run scoreboard players add @p[tag=kartpassenger] kartdrift 1
execute if score @s kartdrifttime matches 25.. run scoreboard players add @p[tag=kartpassenger] kartdrift 1
execute if score @s kartdrifttime matches 30.. run scoreboard players add @p[tag=kartpassenger] kartdrift 1
execute if score @s kartdrifttime matches 35.. run scoreboard players add @p[tag=kartpassenger] kartdrift 1