execute store result storage tower temp.Bullet.time int 1 run scoreboard players get @n[tag=bullet.data] bullet.floor.time
execute store result storage tower temp.Bullet.range int 1 run scoreboard players get @n[tag=bullet.data] bullet.floor.range
data modify storage tower temp.Bullet.attribute.floor set from entity @n[tag=bullet.data] data.Bullet.flooring
execute if entity @n[tag=bullet.flooring] run function tower:bullet/spawn/floor/main with storage tower temp.Bullet
execute on passengers if entity @s[type=item_display] run tag @s add bullet.remove
execute on passengers unless entity @s[type=item_display] run kill @s
kill @s