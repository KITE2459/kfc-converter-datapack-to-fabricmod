#effect give @a[tag=kart-listener] minecraft:dolphins_grace 3 169 true

execute if score @p[tag=kartpassenger] kartboostgauge matches 1.. run scoreboard players remove @p[tag=kartpassenger] kartboostgauge 150

execute if score @p[tag=kartpassenger] kartboostgauge matches 1.. run scoreboard players add @s kartmove 225
execute if score @p[tag=kartpassenger] kartboostgauge matches 1.. unless score @s kartmove matches 24325.. run scoreboard players add @s kartmove 500
execute if score @p[tag=kartpassenger] kartboostgauge matches 1.. rotated ~ 0 run particle snowflake ~ ~1 ~ .5 .5 .5 0.2 2 normal @a

execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-exceed/off-set-0

execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 run scoreboard players set @p[tag=kartpassenger] kartboostgauge 0
execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 run stopsound @a[tag=kart-listener] neutral minecraft:item.elytra.flying
execute if score @p[tag=kartpassenger] kartboostgauge matches ..0 run tag @s remove kart-exceed-active