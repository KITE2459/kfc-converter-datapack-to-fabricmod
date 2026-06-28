$scoreboard players set @s kart-low-gravity $(value)

execute unless score @s kart-low-gravity matches 250.. run scoreboard players set @s kart-low-gravity 250
tag @s add kart-low-gravity