#execute store result storage minecraft:kartmain gear-accel int 0.07 run scoreboard players get #gear-accel kartaccel
#execute store result score #gear-accel kartaccel run data get storage minecraft:kartmain gear-accel

#execute if score #gear-accel kartaccel matches 0.. run scoreboard players operation @s kartmove += #gear-accel kartaccel
#execute if score #gear-accel kartaccel matches ..-1 run scoreboard players operation @s kartmove -= #gear-accel kartaccel

scoreboard players operation @s kart-ers-gauge -= @s kartboostduration

scoreboard players add @s kartmove 75

scoreboard players set @s kart-ers 1

execute if score @s kart-ers-gauge matches 600.. unless score @s kartcollisiontime matches 18.. run scoreboard players set @s kartcollisiontime 30