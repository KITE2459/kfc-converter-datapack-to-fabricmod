#get delta
scoreboard players operation #delta kartcollisiontime = #direction-b kartcollisiontime
scoreboard players operation #delta kartcollisiontime -= #direction-a kartcollisiontime
execute if score #delta kartcollisiontime matches 180.. run scoreboard players remove #delta kartcollisiontime 360
execute if score #delta kartcollisiontime matches ..-180 run scoreboard players add #delta kartcollisiontime 360

#get constant x
scoreboard players set #x kartcollisiontime 200
scoreboard players set #bunmo kartcollisiontime 100
scoreboard players operation #bunmo kartcollisiontime -= #100e-old kartcollisiontime
scoreboard players operation #x kartcollisiontime /= #bunmo kartcollisiontime

execute store result score #direction-a kartcollisiontime run data get entity @n[tag=carAdirection,type=item_display] Rotation[0]
execute store result score #direction-b kartcollisiontime run data get entity @n[tag=carBdirection,type=item_display] Rotation[0]

#calculate new delta
scoreboard players operation #new-delta kartcollisiontime = #delta kartcollisiontime
scoreboard players operation #new-delta kartcollisiontime /= #x kartcollisiontime

scoreboard players operation #direction-b-new kartcollisiontime = #direction-b kartcollisiontime
scoreboard players operation #direction-a-new kartcollisiontime = #direction-a kartcollisiontime

scoreboard players operation #direction-b-new kartcollisiontime -= #new-delta kartcollisiontime
scoreboard players operation #direction-a-new kartcollisiontime += #new-delta kartcollisiontime

execute store result storage minecraft:kartmain directiona int 1 run scoreboard players get #direction-b-new kartcollisiontime
execute store result storage minecraft:kartmain directionb int 1 run scoreboard players get #direction-a-new kartcollisiontime

#ignore calculated angle when |delta| > 90
execute unless score #delta kartcollisiontime matches -135..135 store result storage minecraft:kartmain directiona int 1 run scoreboard players get #direction-b kartcollisiontime
execute unless score #delta kartcollisiontime matches -135..135 store result storage minecraft:kartmain directionb int 1 run scoreboard players get #direction-a kartcollisiontime

function kartmain:collision-old/swapspeed/apply-calculate-direction with storage kartmain