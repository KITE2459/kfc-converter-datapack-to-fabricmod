scoreboard players set max enemy.progress -2147483648
scoreboard players set #temp game.return 0
$tag @e[tag=target_$(number),tag=enemy.target,limit=1] remove target_$(number)
#$execute if data storage tower temp.Bullet.attribute.freeze as @e[distance=..$(range),tag=enemy.target] unless score @s enemy.state.freeze matches 1.. run function tower:attack/targeting/get_target/front/entity
$execute unless score #temp game.return matches 1 as @e[distance=..$(range),tag=enemy.target] run function tower:attack/targeting/get_target/front/entity
$execute as @e[distance=..$(range),tag=enemy.target] if score @s enemy.progress = max enemy.progress run tag @s add target_$(number)