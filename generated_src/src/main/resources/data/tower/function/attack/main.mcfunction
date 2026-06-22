# tower.muzzle에서 실행
execute as @n[tag=tower.data] if score @s tower.state.stun matches 1.. run return 0
# muzzle storage 복제
data modify storage tower temp set from entity @n[tag=tower.data] data
execute store result storage tower temp.number int 1 run scoreboard players get @n[tag=tower.data] tower.number

# 적 탐지
$execute positioned ~ -60 ~ if entity @n[tag=enemy.target,distance=..$(range)] run function tower:attack/targeting/main with storage tower temp
$execute positioned ~ -60 ~ unless entity @n[tag=enemy.target,distance=..$(range)] unless score @s tower.attack_countdown = @s tower.attack_speed run function tower:attack/decrese_countdown

# 공격
execute if score @s tower.attack_countdown matches ..0 run function tower:attack/fire/main with storage tower temp.Bullet