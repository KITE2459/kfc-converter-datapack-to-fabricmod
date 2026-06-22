scoreboard players set #count wave 1
scoreboard players operation #inc wave = #delta wave
execute if score #inc wave matches 26.. run scoreboard players set #inc wave 25
scoreboard players set #div wave 15
scoreboard players operation #inc wave /= #div wave
scoreboard players operation #count wave += #inc wave
execute if score #count wave matches 5.. run scoreboard players set #count wave 5
execute if score #count wave matches ..4 run scoreboard players operation #mod wave = game wave
$execute if score #count wave matches ..4 run scoreboard players remove #mod wave $(unlock)
execute if score #count wave matches ..4 run scoreboard players set #intv wave 5
execute if score #count wave matches ..4 run scoreboard players operation #mod wave %= #intv wave
execute if score #count wave matches ..4 unless score #mod wave matches 0 run return 0
execute unless score timer gameState matches 0 run return 0
scoreboard players operation #loop wave = #count wave
function game:infinity/spawn_loop
