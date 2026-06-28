tag @s add kart-speed-fixed-by-pad
tag @s add kart-direction-fixed-by-pad

#고스트 버그 해결
scoreboard players set @s kartcollisiontime 15
function kartmobil:rkey-ghost/show

$execute store result entity @s data.kartmove-fixed-by-pad int 1 run scoreboard players set @s kartmove $(jumppadfixspeed)