execute unless entity @s[distance=..0.05] run scoreboard players add #magnitude kartcollisiontime 100
execute positioned ^ ^ ^0.05 if entity @s[distance=..0.05] run function kartmain:collision/swapspeed/get-magnitude/old2

execute positioned ^ ^ ^0.05 unless entity @s[distance=..0.05] if entity @s[distance=..100] run function kartmain:collision/swapspeed/get-magnitude/old
