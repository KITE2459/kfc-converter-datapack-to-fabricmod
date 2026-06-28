execute unless entity @s[distance=..0.005] run scoreboard players add #magnitude kartcollisiontime 10
#execute positioned ^ ^ ^0.005 if entity @s[distance=..0.005] run function kartmain:collision/swapspeed/get-magnitude/old2

execute positioned ^ ^ ^0.005 unless entity @s[distance=..0.005] if entity @s[distance=..100] run function kartmain:collision/swapspeed/get-magnitude/old2
