$function tower:bullet/spawn/model/$(model) with entity @s
execute as @n[tag=bullet.data] at @s unless score @s bullet.attack matches 0.. run function tower:bullet/spawn/malloc