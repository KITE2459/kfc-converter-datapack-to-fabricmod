scoreboard players operation #temp player.id = @s player.id
execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id run function tower:upgrade/confirm_tower