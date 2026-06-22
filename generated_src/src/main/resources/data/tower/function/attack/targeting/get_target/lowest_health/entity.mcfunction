execute if score @s enemy.hp < max enemy.hp run scoreboard players set #temp game.return 1
execute if score @s enemy.hp < max enemy.hp run scoreboard players operation max enemy.hp = @s enemy.hp
