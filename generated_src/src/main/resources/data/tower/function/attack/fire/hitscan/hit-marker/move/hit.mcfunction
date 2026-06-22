scoreboard players set #temp game.return 42

execute store result score #damage enemy.hp run scoreboard players get @s bullet.attack
execute store result score #temp enemy.state.stun run scoreboard players get @s bullet.attribute.stun
execute store result score #temp enemy.state.freeze run scoreboard players get @s bullet.attribute.freeze
execute store result score #temp enemy.state.poison run scoreboard players get @s bullet.attribute.poison
execute store result score #temp enemy.state.flame run scoreboard players get @s bullet.attribute.flame
execute store result score #temp enemy.state.bleed run scoreboard players get @s bullet.attribute.bleed
execute store result score #temp enemy.state.weakness run scoreboard players get @s bullet.attribute.weakness
$execute unless entity @n[tag=hitscan-area-effect] as @n[tag=target_$(number)] at @s run function enemy:hit/main
execute as @e[tag=hitscan-area-effect] at @s run function enemy:hit/main
execute as @e[tag=hitscan-area-effect] run tag @s remove hitscan-area-effect
kill @e[tag=hitscan-marker]
return 1