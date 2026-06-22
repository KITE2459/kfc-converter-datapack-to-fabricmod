# 타이머 확인
scoreboard players set #temp enemy.attribute.timer 4
scoreboard players operation #temp enemy.state.flame = @s enemy.attribute.timer
scoreboard players operation #temp enemy.state.flame %= #temp enemy.attribute.timer

# 화염 효과
execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 5.. run scoreboard players set #flame enemy.state.flame 1
execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 10.. run scoreboard players add #flame enemy.state.flame 1
execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 20.. run scoreboard players add #flame enemy.state.flame 1
execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 40.. run scoreboard players add #flame enemy.state.flame 1
execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 80.. run scoreboard players add #flame enemy.state.flame 1
execute if score #temp enemy.state.flame matches 0 if score @s enemy.state.flame matches 160.. run scoreboard players add #flame enemy.state.flame 1
execute if score #temp enemy.state.flame matches 0 run scoreboard players operation @s enemy.hp -= #flame enemy.state.flame

# 스택 감소
execute if score #temp enemy.state.flame matches 0 store result storage temp flame int 0.9 run scoreboard players get @s enemy.state.flame
execute if score #temp enemy.state.flame matches 0 store result score @s enemy.state.flame run data get storage temp flame

# 데이터 보기용 
execute if score #temp enemy.state.flame matches 0 run scoreboard players operation flame statistics += #flame enemy.state.flame