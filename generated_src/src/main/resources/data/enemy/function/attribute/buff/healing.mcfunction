# 타이머 확인
scoreboard players set #temp enemy.attribute.timer 10
scoreboard players operation #temp enemy.attribute.healing = @s enemy.attribute.timer
scoreboard players operation #temp enemy.attribute.healing %= #temp enemy.attribute.timer

# 힐링 효과
execute if score #temp enemy.attribute.healing matches 0 run scoreboard players operation @s enemy.hp += @s enemy.attribute.healing
execute if score @s enemy.hp > @s enemy.max_hp run scoreboard players operation @s enemy.hp = @s enemy.max_hp