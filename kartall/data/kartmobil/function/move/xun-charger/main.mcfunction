
#게이지
execute store result storage minecraft:kartmain chargergauge int 0.0333 run scoreboard players get @p[tag=kartpassenger] kartboostgauge
execute store result score #exp-gauge kartmain run data get storage minecraft:kartmain chargergauge
execute as @a[tag=kart-listener] run function kartmobil:move/exp-gauge

#발동
execute if score @p[tag=kartpassenger] kartboostgauge matches 6000 if entity @s[tag=!kart-charger-active] run function kartmobil:move/xun-charger/can-use
execute unless score @p[tag=kartpassenger] kartboostgauge matches 6000.. if entity @s[tag=!kart-charger-active,tag=kart-space-press] run function kartmobil:move/xun-charger/cant-use
execute if score @p[tag=kartpassenger] kartboostgauge matches 6000.. if entity @s[tag=kart-space-press] run function kartmobil:move/xun-charger/start-use

#익시드 가속
execute if entity @s[tag=kart-charger-active] run function kartmobil:move/xun-charger/using