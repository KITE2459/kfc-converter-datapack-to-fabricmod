# 틱 수명 감소
scoreboard players remove #temp bullet.life 1

# 텔레포트

# 적중 판정
execute as @n[tag=enemy.target,distance=..5] at @s run function tower:bullet/move/detect_hitbox
execute if entity @n[tag=enemy.target.hit] if function tower:bullet/move/hit run return 0

# function tower:bullet/move/detect_hitbox

tp @s ^ ^ ^0.25
execute if score #temp bullet.life matches ..0 on passengers as @s[tag=bullet.data] run scoreboard players remove @s bullet.life 1
execute if score #temp bullet.life matches ..0 run return 0

# 이동
execute at @s run function tower:bullet/move/ray