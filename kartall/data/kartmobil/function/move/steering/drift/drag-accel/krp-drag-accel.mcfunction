execute unless score #kartangle kartmain matches -525..525 run scoreboard players set @s kartdrifttime 2
execute unless score #kartangle kartmain matches -525..525 run return 0

execute if score @s kartdrifttime matches 5.. run scoreboard players add @s kartmove 120

execute if score @s kartdrifttime matches 5.. if score #kartangle kartmain matches -300..300 run scoreboard players add @s kartmove 40
execute if score @s kartdrifttime matches 5.. if score #kartangle kartmain matches -300..300 run function kartmobil:move/steering/drift/drag-accel/drift-accel