execute if entity @s[distance=..0.005] run return 0
scoreboard players add #magnitude kartcollisiontime 10
execute positioned ^ ^ ^0.005 run function kartmain:collision/swapspeed/get-magnitude/nano