execute if entity @s[tag=tower.data] run function tower:state/main

# 타워 공격
execute if entity @s[tag=tower.muzzle] at @s run function tower:attack/main with entity @s data

# 타워 애니메이션
execute if entity @s[tag=tower.animation] at @s run function tower:animation/main

# 타워 업그레이드
execute if entity @s[tag=tower.hitbox] at @s run function tower:upgrade/main
execute if entity @s[tag=tower.upgrade_range] at @s if score @n[tag=tower.core] player.id matches 1.. run function tower:upgrade/show_range with entity @n[tag=tower.data] data

# 무기고
function tower:armory/main