execute if score #delta wave matches 8.. run return 0
scoreboard players operation #count wave = #delta wave
scoreboard players add #count wave 5
scoreboard players operation #end wave = #count wave
scoreboard players remove #end wave 1
scoreboard players set #intv wave 20
scoreboard players operation #end wave *= #intv wave
scoreboard players operation #infinity_end time > #end wave
scoreboard players operation #mod wave = timer gameState
scoreboard players set #intv wave 20
scoreboard players operation #mod wave %= #intv wave
execute unless score #mod wave matches 0 run return 0
scoreboard players operation #step wave = timer gameState
scoreboard players operation #step wave /= #intv wave
scoreboard players operation #cmp wave = #step wave
scoreboard players operation #cmp wave -= #count wave
execute if score #cmp wave matches ..-1 run function game:infinity/spawn_one
