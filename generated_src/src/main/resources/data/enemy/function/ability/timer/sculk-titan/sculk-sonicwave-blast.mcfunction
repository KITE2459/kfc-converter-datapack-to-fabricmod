scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 85
particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
tp @s ^ ^ ^1
scoreboard players remove @s enemy.hp 1
execute if score @s enemy.hp matches ..0 run kill @s