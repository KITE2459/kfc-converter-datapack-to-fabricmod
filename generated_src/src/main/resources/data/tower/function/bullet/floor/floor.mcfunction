scoreboard players remove @s bullet.floor.time 1
scoreboard players set #7 enemy.attribute.timer 4
scoreboard players operation #temp enemy.attribute.timer = @s bullet.floor.time
scoreboard players operation #temp enemy.attribute.timer %= #7 enemy.attribute.timer
execute unless score #temp enemy.attribute.timer matches 0 run return 0

execute at @s[tag=floor-2] run function tower:bullet/floor/floor-on {range:2}
execute at @s[tag=floor-2.5] run function tower:bullet/floor/floor-on {range:2.5}
execute at @s[tag=floor-3] run function tower:bullet/floor/floor-on {range:3}
execute at @s[tag=floor-3.5] run function tower:bullet/floor/floor-on {range:3.5}
execute at @s[tag=floor-4] run function tower:bullet/floor/floor-on {range:4}
execute at @s[tag=floor-5] run function tower:bullet/floor/floor-on {range:5}
execute at @s[tag=floor-6] run function tower:bullet/floor/floor-on {range:6}
execute at @s[tag=floor-7] run function tower:bullet/floor/floor-on {range:7}
execute at @s[tag=floor-8] run function tower:bullet/floor/floor-on {range:8}
execute at @s[tag=floor-9] run function tower:bullet/floor/floor-on {range:9}
execute at @s[tag=floor-10] run function tower:bullet/floor/floor-on {range:10}
execute at @s[tag=floor-11] run function tower:bullet/floor/floor-on {range:11}
execute at @s[tag=floor-12] run function tower:bullet/floor/floor-on {range:12}
execute at @s[tag=floor-13] run function tower:bullet/floor/floor-on {range:13}