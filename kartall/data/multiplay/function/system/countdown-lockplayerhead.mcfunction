execute store result score #multi-player-rotation kartcorner run data get entity @s Rotation[0]
execute store result score #multi-kart-rotation kartcorner run data get entity @n[tag=kartmobil,type=#kartmobil:kartmobil] Rotation[0]

scoreboard players operation #multi-player-rotation kartcorner -= #multi-kart-rotation kartcorner
execute if score #multi-player-rotation kartcorner matches 180.. run scoreboard players remove #multi-player-rotation kartcorner 360
execute if score #multi-player-rotation kartcorner matches ..-180 run scoreboard players add #multi-player-rotation kartcorner 360

execute unless score #multi-player-rotation kartcorner matches -45..45 at @n[tag=kartmobil,type=#kartmobil:kartmobil] run rotate @s ~ 30