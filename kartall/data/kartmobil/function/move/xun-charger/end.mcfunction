clear @p[tag=kartpassenger] *[minecraft:custom_data~{xun:1}]
execute on passengers if entity @s[tag=kartmodelsaddle] on passengers if entity @s[tag=xun-charger-shield] run data merge entity @s {transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[0f,0f,0f]}}

scoreboard players remove @s kartmaxboostcount 1
scoreboard players remove @s kartdefense 20
scoreboard players set @p[tag=kartpassenger] kartboostgauge 0
tag @s remove kart-charger-active