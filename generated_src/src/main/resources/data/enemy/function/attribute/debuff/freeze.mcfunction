scoreboard players set #temp enemy.attribute.timer 10
scoreboard players operation #temp enemy.state.freeze = @s enemy.attribute.timer
scoreboard players operation #temp enemy.state.freeze %= #temp enemy.attribute.timer
execute if score #temp enemy.state.freeze matches 0 run scoreboard players remove @s enemy.state.freeze 1
particle minecraft:snowflake ~ ~1 ~ 0.3 0.6 0.3 0 1 force