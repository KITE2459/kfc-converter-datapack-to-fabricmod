#execute if score @s[tag=drag-accel] kartdrifttime matches 10.. run scoreboard players add @s kartmove 100

#execute if score @s[tag=drag-accel] kartdrifttime matches 13.. run function kartmobil:move/steering/drag-accel/drift-accel
#execute if score @s[tag=drag-accel] kartdrifttime matches 16.. run function kartmobil:move/steering/drag-accel/drift-accel
#execute if score @s[tag=drag-accel] kartdrifttime matches 19.. run function kartmobil:move/steering/drag-accel/drift-accel
#execute if score @s[tag=drag-accel] kartdrifttime matches 22.. run function kartmobil:move/steering/drag-accel/drift-accel
#execute if score @s[tag=drag-accel] kartdrifttime matches 25.. run function kartmobil:move/steering/drag-accel/drift-accel

execute unless score #kartangle kartmain matches -525..525 run scoreboard players set @s kartdrifttime 2
execute unless score #kartangle kartmain matches -525..525 run return 0

execute if score @s kartdrifttime matches 4.. run scoreboard players add @s kartmove 80

execute if score @s kartdrifttime matches 6.. run scoreboard players add @s kartmove 100
execute if score @s kartdrifttime matches 6.. run scoreboard players add @p[tag=kartpassenger] kartdrift 10

execute if score @s kartdrifttime matches 7.. run scoreboard players add @s kartmove 12

execute if score @s kartdrifttime matches 9.. run scoreboard players add @s kartmove 80
execute if score @s kartdrifttime matches 9.. run scoreboard players add @p[tag=kartpassenger] kartdrift 10

execute if score @s kartdrifttime matches 10.. run scoreboard players add @s kartmove 14
execute if score @s kartdrifttime matches 12.. run function kartmobil:move/steering/drift/drag-accel/drift-accel
execute if score @s kartdrifttime matches 13.. run scoreboard players add @s kartmove 14