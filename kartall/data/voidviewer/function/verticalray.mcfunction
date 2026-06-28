execute rotated -90 0 positioned ^ ^ ^1 run function voidviewer:straightray
execute rotated 180 0 positioned ^ ^ ^1 run function voidviewer:straightray

execute rotated 45 0 run function voidviewer:diagonalray
execute rotated -45 0 positioned ^ ^ ^1.414 run function voidviewer:diagonalray
execute rotated 135 0 positioned ^ ^ ^1.414 run function voidviewer:diagonalray
execute rotated -135 0 positioned ^ ^ ^1.414 run function voidviewer:diagonalray

execute if entity @s[distance=..15] positioned ^ ^ ^1 run function voidviewer:verticalray







