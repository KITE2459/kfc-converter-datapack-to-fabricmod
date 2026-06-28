#충돌 중이 아닐때 브레이크 / 부능효과 도중 브레이크 강화
execute unless score @s kartcollisiontime matches 5..8 unless score @s kart-engine matches 1006 run scoreboard players remove @s kartmove 1000
execute unless score @s kartcollisiontime matches 5..8 if score @s kart-engine matches 1006 run scoreboard players remove @s kartmove 250
execute if entity @s[tag=kart-charger-active] run function kartmobil:control/xun-charger-brake

execute if score @s kartmove matches ..-1 run scoreboard players set @s kartmove 0

#부스탑(번아웃)
execute unless score @s kart-engine matches 7 unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1008 unless score @s kart-engine matches 1006 if entity @p[tag=kartpassenger,predicate=kartmobil:w] run function kartmobil:control/burning
