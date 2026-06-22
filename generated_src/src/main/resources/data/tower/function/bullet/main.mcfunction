execute as @e[tag=bullet.remove] at @s run kill @s
execute as @e[tag=bullet.core] at @s run function tower:bullet/move/main
execute as @e[tag=bullet.floor] at @s run function tower:bullet/floor/floor