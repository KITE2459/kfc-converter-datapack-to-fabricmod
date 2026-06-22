execute as @e[tag=tower.core] if score @s player.id = #temp player.id run function tower:upgrade/cancel
execute as @n[tag=tower.core] run scoreboard players operation @s player.id = #temp player.id
execute as @e[tag=tower.hitbox] if score @s player.last_upgrade_id = #temp player.id run scoreboard players reset @s player.last_upgrade_id
scoreboard players operation @s player.last_upgrade_id = #temp player.id