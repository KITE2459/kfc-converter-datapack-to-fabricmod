execute store result score #temp game.return run function enemy:move/checkpoint/main with storage enemy temp
execute store result score #temp enemy.progress run data get storage enemy temp.speed 10000
scoreboard players operation @s enemy.progress += #temp enemy.progress
execute if score #temp game.return matches 1 run return 0
$execute positioned ^ ^ ^$(speed) run teleport ~ ~ ~
execute if score @s enemy.onGround matches 1.. run function enemy:move/sync
return 1