execute on passengers as @s[tag=bullet.data] if score @s bullet.life matches ..0 on vehicle run function tower:bullet/remove
execute store result score #temp bullet.life run scoreboard players get @s bullet.speed
function tower:bullet/move/ray