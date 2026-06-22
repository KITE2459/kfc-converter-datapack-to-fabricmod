scoreboard players set #360 enemy.rotation 360
execute store result score #temp enemy.rotation run data get entity @n[tag=map.check_point] Rotation[0]
execute store result score #temp2 enemy.rotation run data get entity @s Rotation[0]
scoreboard players operation #temp2 enemy.rotation %= #360 enemy.rotation
execute if score #temp enemy.rotation = #temp2 enemy.rotation run return 0
execute at @n[tag=map.check_point] run tp @s ~ -60 ~ ~ ~
execute on passengers at @s run rotate @s ~ ~
scoreboard players set @s enemy.onGround 50
return 1