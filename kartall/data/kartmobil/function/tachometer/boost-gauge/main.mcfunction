execute if score @s kart-engine matches 7 run function kartmobil:tachometer/boost-gauge/1.0-main
execute if score @s kart-engine matches 1003 run function kartmobil:tachometer/boost-gauge/mario-main
execute if score @s kart-engine matches 1008 run function kartmobil:tachometer/boost-gauge/ds-main
execute if score @s kart-engine matches 9 run function kartmobil:tachometer/boost-gauge/krp-main

execute unless score @s kart-engine matches 7 unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1008 unless score @s kart-engine matches 9 run function kartmobil:tachometer/boost-gauge/normal-main