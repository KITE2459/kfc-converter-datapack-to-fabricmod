execute store result storage enemy temp.y double 1 run data get entity @s Pos[1] 1.0
execute store result score #temp game.return run function enemy:move/checkpoint/checkpoint with storage enemy temp
execute if score #temp game.return matches 1 run return 1
function enemy:move/checkpoint/dest_point with storage enemy temp