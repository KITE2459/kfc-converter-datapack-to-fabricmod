execute if entity @s[tag=kart-stop] run return 1

execute unless score @s kart-engine matches 1004..1006 unless score @s kart-engine matches 1007 run function kartmobil:control/control
execute if score @s kart-engine matches 1005 run function kartmobil:control/gear-mode/control
execute if score @s kart-engine matches 1006 run function kartmobil:control/f1-mode/control
execute if score @s kart-engine matches 1007 run function kartmobil:control/rally-mode/control

execute if score @s kart-engine matches ..999 if entity @p[tag=kartpassenger,tag=!ignore-nodelay-detect] run function kartmobil:control/nodelay-detect/main