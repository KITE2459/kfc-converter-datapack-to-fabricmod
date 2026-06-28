
execute if entity @p[tag=kartpassenger,predicate=kartmobil:a,predicate=!kartmobil:d,predicate=kartmobil:space] run tag @s add kart-mario-a
execute if entity @p[tag=kartpassenger,predicate=kartmobil:d,predicate=!kartmobil:a,predicate=kartmobil:space] run tag @s add kart-mario-d

tag @s[tag=kart-mario-a] add kart-drifting
tag @s[tag=kart-mario-d] add kart-drifting

scoreboard players set @s kartboostgauge 500

execute if entity @s[tag=!kart-jumped] if score #floor-distance floor-distance matches ..1 run function kartmobil:move/steering/wasd-control/dummy-mini-jump