scoreboard players add voidtick voidtime 1

execute if score voidtick voidtime >= voidspeed voidtime unless score voidtime voidtime matches 1 unless score voidtime voidtime matches 3 as @a if items entity @s weapon minecraft:structure_void at @s rotated 0 90 align xyz positioned ~0.5 ~0.5 ~0.5 run function voidviewer:verticalray
execute if score voidtick voidtime >= voidspeed voidtime unless score voidtime voidtime matches 2 unless score voidtime voidtime matches 4 as @a if items entity @s weapon minecraft:structure_void at @s rotated 0 -90 align xyz positioned ~0.5 ~0.5 ~0.5 positioned ^ ^ ^1 run function voidviewer:verticalray

execute if score voidtick voidtime >= voidspeed voidtime run scoreboard players add voidtime voidtime 1
execute if score voidtick voidtime >= voidspeed voidtime run scoreboard players reset voidtick voidtime
execute if score voidtime voidtime matches 5.. run scoreboard players set voidtime voidtime 1