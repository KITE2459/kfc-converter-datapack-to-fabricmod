scoreboard players set #2 enemy.onGround 2
scoreboard players operation #temp enemy.onGround = @s enemy.onGround
scoreboard players operation #temp enemy.onGround %= #2 enemy.onGround

execute if score #temp enemy.onGround matches 1 run data modify entity @s OnGround set value true
execute unless score #temp enemy.onGround matches 1 run data modify entity @s OnGround set value false
scoreboard players remove @s enemy.onGround 1