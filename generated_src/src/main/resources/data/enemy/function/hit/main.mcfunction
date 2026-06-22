# 데미지 계산
function enemy:hit/calculate_defence

# 디버프 적용
execute if entity @s[tag=!enemy.dark-field.immune] if score #temp enemy.state.stun matches 1.. unless score @s[tag=!enemy.immune.stun] enemy.state.stun-immune matches 1.. if score @s enemy.state.stun <= #temp enemy.state.stun run scoreboard players operation @s enemy.state.stun = #temp enemy.state.stun
execute if score #temp enemy.state.freeze matches 1.. if score @s[tag=!enemy.immune.freeze,tag=!enemy.dark-field.immune] enemy.state.freeze <= #temp enemy.state.freeze run scoreboard players operation @s enemy.state.freeze = #temp enemy.state.freeze
execute if score #temp enemy.state.poison matches 1.. run scoreboard players operation @s[tag=!enemy.immune.poison,tag=!enemy.dark-field.immune] enemy.state.poison += #temp enemy.state.poison
execute if score #temp enemy.state.flame matches 1.. run scoreboard players operation @s[tag=!enemy.immune.flame,tag=!enemy.dark-field.immune] enemy.state.flame += #temp enemy.state.flame
execute if score #temp enemy.state.bleed matches 1.. run scoreboard players operation @s[tag=!enemy.immune.bleed,tag=!enemy.dark-field.immune] enemy.state.bleed += #temp enemy.state.bleed
execute if score #temp enemy.state.weakness matches 1.. run scoreboard players operation @s[tag=!enemy.immune.weakness,tag=!enemy.dark-field.immune] enemy.state.weakness += #temp enemy.state.weakness

# 체력 감소
scoreboard players operation @s enemy.hp -= #damage enemy.hp
execute if entity @s[type=!end_crystal,type=!enderman] if score #damage enemy.hp matches 1.. run damage @s 0.001 out_of_world

# 테그 제거
tag @s remove enemy.target.hit

# 사망 검사
execute unless score @s enemy.hp matches 1.. run function enemy:hit/death with entity @s data