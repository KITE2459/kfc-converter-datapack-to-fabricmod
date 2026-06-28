execute rotated -90 0 positioned ^ ^ ^1 run function guardrailtool:straightray
execute rotated 180 0 positioned ^ ^ ^1 run function guardrailtool:straightray

execute rotated 45 0 run function guardrailtool:diagonalray
execute rotated -45 0 positioned ^ ^ ^1.414 run function guardrailtool:diagonalray
execute rotated 135 0 positioned ^ ^ ^1.414 run function guardrailtool:diagonalray
execute rotated -135 0 positioned ^ ^ ^1.414 run function guardrailtool:diagonalray

execute if score @s carrot matches 1.. run scoreboard players reset @s carrot
