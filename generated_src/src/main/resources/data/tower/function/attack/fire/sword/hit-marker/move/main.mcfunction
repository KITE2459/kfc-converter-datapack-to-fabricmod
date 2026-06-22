execute store result storage tower temp.number int 1 run scoreboard players get @n[tag=tower.data] tower.number
execute run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~-60 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~-50 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~-40 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~-30 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~-20 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~-10 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~10 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~20 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~30 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~40 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~50 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
execute rotated ~60 ~ run function tower:attack/fire/sword/hit-marker/move/sweep with storage tower temp
scoreboard players set #temp game.return 42
execute store result score #damage enemy.hp run scoreboard players get @s bullet.attack
execute store result score #temp enemy.state.stun run scoreboard players get @s bullet.attribute.stun
execute store result score #temp enemy.state.freeze run scoreboard players get @s bullet.attribute.freeze
execute store result score #temp enemy.state.poison run scoreboard players get @s bullet.attribute.poison
execute store result score #temp enemy.state.flame run scoreboard players get @s bullet.attribute.flame
execute store result score #temp enemy.state.bleed run scoreboard players get @s bullet.attribute.bleed
execute store result score #temp enemy.state.weakness run scoreboard players get @s bullet.attribute.weakness
execute as @e[tag=sword-hit-area] at @s run function enemy:hit/main
execute as @e[tag=sword-hit-area] run tag @s remove sword-hit-area
kill @s