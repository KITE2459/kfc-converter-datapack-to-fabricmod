scoreboard players operation #temp player.id = @s player.id
$execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id as @n[tag=tower.muzzle] run scoreboard players set @s tower.target_mode $(mode)
execute as @e[tag=tower.core] at @s if score @s player.id = #temp player.id as @n[tag=tower.hitbox] run scoreboard players operation @s player.last_upgrade_id = #temp player.id
function tower:upgrade/re_upgrade