#익시드 최고 600, 부스터를 안 쓰면 충전 가속
execute if entity @s[tag=!kart-exceed-active,tag=kart-drifting] unless score @p[tag=kartpassenger] kartboostgauge matches 6000.. run scoreboard players add @p[tag=kartpassenger] kartboostgauge 30

#게이지
execute store result storage minecraft:kartmain exceedgauge int 0.0333 run scoreboard players get @p[tag=kartpassenger] kartboostgauge
execute store result score #exp-gauge kartmain run data get storage minecraft:kartmain exceedgauge
execute as @a[tag=kart-listener] run function kartmobil:move/exp-gauge

#발동
execute if score @p[tag=kartpassenger] kartboostgauge matches 500..550 if entity @s[tag=!kart-exceed-active] run function kartmobil:move/krp-exceed/can-use
execute unless score @p[tag=kartpassenger] kartboostgauge matches 500.. if entity @s[tag=!kart-exceed-active,tag=kart-space-press] run function kartmobil:move/krp-exceed/cant-use
execute if score @p[tag=kartpassenger] kartboostgauge matches 500.. if entity @s[tag=kart-space-press] run function kartmobil:move/krp-exceed/start-use