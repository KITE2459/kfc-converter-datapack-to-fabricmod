# 타워 소환
execute as @a at @s run function tower:spawn/main

# 타워 상태 (기절, 버프 등) 감지
execute as @e[tag=tower.data,type=marker] run function tower:state/main

# 타워 공격
execute as @e[tag=tower.muzzle,type=marker] at @s run function tower:attack/main with entity @s data

# 타워 애니메이션
execute as @e[tag=tower.data,type=marker] at @s run function tower:animation/main

# 총알 루프	
function tower:bullet/main

# 타워 업그레이드
execute as @e[tag=tower.hitbox,type=interaction] at @s run function tower:upgrade/main
execute as @e[tag=tower.upgrade_range] at @s if score @n[tag=tower.core] player.id matches 1.. run function tower:upgrade/show_range with entity @n[tag=tower.data] data

# 무기고
function tower:armory/main