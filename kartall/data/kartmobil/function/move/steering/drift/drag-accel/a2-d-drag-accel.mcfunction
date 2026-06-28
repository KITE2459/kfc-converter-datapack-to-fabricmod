execute unless score #kartangle kartmain matches -600..600 run return 0

execute if score @s kartdrifttime matches 4.. run scoreboard players add @s kartmove 100

execute if score @s[tag=!drag-accel] kartdrifttime matches 6.. run function kartmobil:move/steering/drift/drag-accel/drift-accel
execute if score @s kartdrifttime matches 8.. run scoreboard players add @s kartmove 12

execute if score @s[tag=!drag-accel] kartdrifttime matches 10.. run function kartmobil:move/steering/drift/drag-accel/drift-accel
execute if score @s kartdrifttime matches 12.. run scoreboard players add @s kartmove 15

execute if score @s[tag=!drag-accel] kartdrifttime matches 14.. run function kartmobil:move/steering/drift/drag-accel/drift-accel