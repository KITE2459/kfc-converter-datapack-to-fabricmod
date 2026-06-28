execute if score @p[tag=kartpassenger] kartboostgauge matches 1.. run scoreboard players remove @p[tag=kartpassenger] kartboostgauge 85
#execute if score @p[tag=kartpassenger] kartboostgauge matches 1.. rotated ~ 0 run particle snowflake ~ ~1 ~ .5 .5 .5 0.2 2 normal @a

#충.방 증가
execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 run clear @p[tag=kartpassenger] *[minecraft:custom_data~{xun:1}]
execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 on passengers if entity @s[tag=kartmodelsaddle] on passengers if entity @s[tag=xun-charger-shield] run data merge entity @s {transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[0f,0f,0f]}}

execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 run scoreboard players remove @s kartmaxboostcount 1
execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 run scoreboard players remove @s kartdefense 20
execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 run scoreboard players set @p[tag=kartpassenger] kartboostgauge 0
execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 run tag @s remove kart-charger-active