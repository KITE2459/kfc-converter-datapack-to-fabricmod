function trackselect:track-ui/remove-ui

#킥당하기
execute if score @s trackselect-game-id matches 1 run tp @s -139 4 236
execute if score @s trackselect-game-id matches 2 run tp @s -80 4 236 90 0
execute if score @s trackselect-game-id matches 3 run tp @s -18 -1 156 180 0
execute if score @s trackselect-game-id matches 4 run tp @s -73 4 205 0 0

scoreboard players reset @s trackselect-game-id