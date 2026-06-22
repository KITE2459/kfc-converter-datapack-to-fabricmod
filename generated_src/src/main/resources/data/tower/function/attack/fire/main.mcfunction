# 총알 발사
$execute if score @s[tag=tower.muzzle] tower.attack_countdown matches ..0 run function tower:attack/fire/$(type)/main with storage tower temp

# 공격 쿨타임 초기화
execute if score @s[tag=tower.muzzle] tower.attack_countdown matches ..0 run scoreboard players operation @s tower.attack_countdown = @s tower.attack_speed
execute if score @s[tag=tower.muzzle,tag=farm] tower.attack_countdown = @s tower.attack_speed run scoreboard players remove @s tower.attack_countdown 1
scoreboard players operation #temp tower.attack_countdown = @s tower.attack_countdown
execute on vehicle on passengers run scoreboard players operation @s tower.animation = #temp tower.attack_countdown