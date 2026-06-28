execute if entity @p[distance=..1] run return 1
execute if entity @n[distance=..2,type=item_frame] run return 1
execute if block ~ ~ ~ minecraft:structure_void run return 1
execute if block ~ ~ ~ minecraft:stone_button run return 1
execute if block ~ ~ ~ minecraft:oak_pressure_plate run return 1
execute if block ~ ~ ~ #minecraft:signs run return 1

$setblock ~ ~ ~ $(id)