# 파티클
function tower:attack/fire/hitscan/hit-marker/animation/main with storage tower temp

# 특수 효과
function tower:attack/fire/hitscan/hit-marker/move/hitscan-attribute with storage tower temp

# 적중 판정
execute unless score @s hitscan-marker.area matches 1.. unless score @s bullet.ability.chain matches 1.. run function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox with storage tower temp
execute if score #temp game.return matches 41 run function tower:attack/fire/hitscan/hit-marker/move/hit with storage tower temp
execute if score #temp game.return matches 42 run return 0

# 텔레포트
tp @s ^ ^ ^0.25

scoreboard players remove @s bullet.life 1

# 광역 관통
execute if score @s[scores={bullet.life=..0}] hitscan-marker.area matches 1.. run function tower:attack/fire/hitscan/hit-marker/move/hit with storage tower temp

# 체인
execute if score @s[scores={bullet.life=..0}] bullet.ability.chain matches 1.. run function tower:attack/fire/hitscan/hit-marker/move/hit with storage tower temp

# 소멸
execute if score @s bullet.life matches ..0 run kill @s
execute if score @s bullet.life matches ..0 run return 0

# 이동
execute at @s run function tower:attack/fire/hitscan/hit-marker/move/ray with storage tower temp