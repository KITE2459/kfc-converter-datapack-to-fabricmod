execute if score @s[tag=drag-accel] kartdrifttime matches 4.. run function kartmobil:move/steering/drift/drag-accel/mode-drag-accel

execute unless score @s[tag=!drag-accel] kart-engine matches 1000..1003 unless score @s[tag=!drag-accel] kart-engine matches 1006 run function kartmobil:move/steering/drift/drag-accel/default-drag-accel

execute if score @s kart-engine matches 1000 run function kartmobil:move/steering/drift/drag-accel/n1-drag-accel
#execute if score @s kart-engine matches 1001 run function kartmobil:move/steering/drift/drag-accel/n1-drag-accel
#execute if score @s kart-engine matches 1001 run function kartmobil:move/steering/drift/drag-accel/rx-drag-accel
execute if score @s kart-engine matches 1002 run function kartmobil:move/steering/drift/drag-accel/a2-d-drag-accel
execute if score @s kart-engine matches 9 run function kartmobil:move/steering/drift/drag-accel/krp-drag-accel