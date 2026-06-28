execute if entity @s[distance=..5] run return run function kartmain:collision/swapspeed/get-magnitude/mili
scoreboard players add #magnitude kartcollisiontime 10000
execute positioned ^ ^ ^5 run function kartmain:collision/swapspeed/get-magnitude/meter
