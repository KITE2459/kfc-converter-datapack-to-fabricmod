# 타이머 확인
scoreboard players set #temp enemy.attribute.timer 10
scoreboard players operation #temp enemy.attribute.dealing = @s enemy.attribute.timer
scoreboard players operation #temp enemy.attribute.dealing %= #temp enemy.attribute.timer

# 피해 효과
execute if score #temp enemy.attribute.dealing matches 0 run scoreboard players operation @s enemy.hp -= @s enemy.attribute.dealing
execute if score @s enemy.hp > @s enemy.max_hp run scoreboard players operation @s enemy.hp = @s enemy.max_hp