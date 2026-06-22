# 타겟 확보		1:가장 앞의 적 | 2:가장 뒤의 적 | 3:가장 가까운 적 | 4:가장 먼 적 | 5:체력이 가장 낮은 적 | 6:체력이 가장 높은 적
execute positioned ~ -60 ~ if score @s tower.target_mode matches 1 run function tower:attack/targeting/get_target/front/main with storage tower temp
execute positioned ~ -60 ~ if score @s tower.target_mode matches 2 run function tower:attack/targeting/get_target/back/main with storage tower temp
execute positioned ~ -60 ~ if score @s tower.target_mode matches 3 run function tower:attack/targeting/get_target/nearest/main with storage tower temp
execute positioned ~ -60 ~ if score @s tower.target_mode matches 4 run function tower:attack/targeting/get_target/farthest/main with storage tower temp
execute positioned ~ -60 ~ if score @s tower.target_mode matches 5 run function tower:attack/targeting/get_target/lowest_health/main with storage tower temp
execute positioned ~ -60 ~ if score @s tower.target_mode matches 6 run function tower:attack/targeting/get_target/highest_health/main with storage tower temp
execute positioned ~ -60 ~ unless score @s tower.target_mode matches 1..6 run function tower:attack/targeting/get_target/front/main with storage tower temp

$execute positioned ~ -60 ~ if entity @e[tag=enemy.attribute.taunt,distance=..$(range),type=#minecraft:target] run function tower:attack/targeting/taunt with storage tower temp

# 포탑 회전
execute on vehicle on passengers if entity @s[tag=tower.head] run function tower:attack/targeting/rotate with storage tower temp

# 공격 카운트다운 감소
scoreboard players remove @s tower.attack_countdown 1
scoreboard players operation #temp tower.attack_countdown = @s tower.attack_countdown
execute on vehicle on passengers run scoreboard players operation @s tower.animation = #temp tower.attack_countdown