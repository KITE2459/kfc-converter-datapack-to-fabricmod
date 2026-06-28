execute if entity @s[distance=..0.5] run return run function kartmain:collision/swapspeed/get-magnitude/micro
scoreboard players add #magnitude kartcollisiontime 1000
execute positioned ^ ^ ^0.5 run function kartmain:collision/swapspeed/get-magnitude/mili
