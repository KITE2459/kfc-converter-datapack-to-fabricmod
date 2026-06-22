execute store result score #temp enemy.speed run data get storage enemy temp.speed 10000
scoreboard players set #temp enemy.state.freeze 100
scoreboard players operation #temp enemy.state.freeze -= @s enemy.state.freeze
scoreboard players operation #temp enemy.state.freeze *= #temp enemy.speed
execute store result storage enemy temp.speed float 0.000001 run scoreboard players get #temp enemy.state.freeze