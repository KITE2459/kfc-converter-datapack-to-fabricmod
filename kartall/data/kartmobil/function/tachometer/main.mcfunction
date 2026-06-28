execute if entity @s[tag=kart-stop] run return 1

execute unless score @s kart-engine matches 1004..1006 unless score @s kart-engine matches 1007 run function kartmobil:tachometer/boost-gauge/main
execute if score @s kart-engine matches 1005 run function kartmobil:tachometer/boost-gauge/gear-main
execute if score @s kart-engine matches 1006 run function kartmobil:tachometer/boost-gauge/f1-main
execute if score @s kart-engine matches 1007 run function kartmobil:tachometer/boost-gauge/rally-main