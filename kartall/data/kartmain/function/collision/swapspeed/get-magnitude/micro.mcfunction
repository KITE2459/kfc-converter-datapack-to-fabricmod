execute if entity @s[distance=..0.05] run return run function kartmain:collision/swapspeed/get-magnitude/nano
scoreboard players add #magnitude kartcollisiontime 100
execute positioned ^ ^ ^0.05 run function kartmain:collision/swapspeed/get-magnitude/micro