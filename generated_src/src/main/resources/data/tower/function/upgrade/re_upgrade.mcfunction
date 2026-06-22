scoreboard players operation #temp player.id = @s player.id
execute as @e[tag=tower.hitbox] at @s if score @s player.last_upgrade_id = #temp player.id run function tower:upgrade/re_interact