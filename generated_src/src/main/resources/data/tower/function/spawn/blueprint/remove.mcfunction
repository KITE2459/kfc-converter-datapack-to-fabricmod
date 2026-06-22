# remove blueprint linked to this player (@s)
scoreboard players operation buffer blueprint_id = @s blueprint_id
execute at @s as @e[type=minecraft:block_display,tag=blueprint] if score @s blueprint_id = buffer blueprint_id run kill @s
execute at @s as @e[type=minecraft:marker,tag=blueprint_range] if score @s blueprint_id = buffer blueprint_id run kill @s

# clear player state
scoreboard players reset @s blueprint_id
scoreboard players reset @s tower.number

tag @s remove blueprint_player