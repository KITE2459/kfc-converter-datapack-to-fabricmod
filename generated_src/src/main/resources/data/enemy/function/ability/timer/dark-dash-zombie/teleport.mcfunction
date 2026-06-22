# execute if entity @n[tag=map.check_point,distance=..0.001] if function enemy:ability/timer/dash-zombie/checkpoint run return 0
# execute if entity @n[tag=map.dest_point,distance=..0.001] if function enemy:ability/timer/dash-zombie/dest_point run return 0

execute store result score #temp game.return run function enemy:move/teleport with storage enemy temp
execute if score #temp game.return matches 0 run return 0
execute at @s run function enemy:ability/timer/dark-dash-zombie/teleport