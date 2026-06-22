execute as @e[tag=enemy.data] at @s run function enemy:attribute/main
execute as @e[tag=enemy.core] at @s run function enemy:move/main
execute as @e[tag=enemy.text] run function enemy:animation/reload-text
execute as @e[tag=mob-gen] at @s run function enemy:ability/mob_generator/main with entity @s data
execute as @e[type=marker,tag=enemy.dark-field] at @s run function enemy:ability/dark-field/tick
function enemy:ability/tick