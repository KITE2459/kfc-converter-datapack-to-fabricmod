execute unless score #floor-distance floor-distance matches ..1 run return 1

#카러플 고속턴
execute if score @s[tag=kart-drifting] kart-engine matches 9 run scoreboard players add @s kartmove 400
execute if score @s[tag=kart-drifting] kart-engine matches 9 run scoreboard players add @p[tag=kartpassenger] kartdrift 250
execute if score @s[tag=kart-drifting] kart-engine matches 9 run return 1

#번아웃
execute if entity @s[tag=!kart-stop] if score @s kartboostgaugecharge matches 1.. run scoreboard players add @s kartboostgauge 35

function kartmobil:control/burning-effect

execute if score @s kartmove matches 1000.. run scoreboard players add @s kartmove 250
execute if score @s kartmove matches ..1000 run scoreboard players set @s kartmove 999