particle minecraft:dust{color:[0f,0f,0f],scale:2f} ~ ~0.05 ~ 1 0 1 0 2 normal @a
execute if entity @s[tag=enemy.dark-field.large] run particle minecraft:dust{color:[0f,0f,0f],scale:2f} ~ ~0.05 ~ 4.5 0 4.5 0 30 normal @a
scoreboard players remove @s enemy.dark-field.timer 1
execute if score @s enemy.dark-field.timer matches ..0 run kill @s
