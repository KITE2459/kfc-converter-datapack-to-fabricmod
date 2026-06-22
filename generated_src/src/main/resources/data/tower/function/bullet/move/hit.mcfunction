execute store result score #damage enemy.hp run scoreboard players get @n[tag=bullet.data] bullet.attack
execute store result score #temp enemy.state.stun run scoreboard players get @n[tag=bullet.data] bullet.attribute.stun
execute store result score #temp enemy.state.freeze run scoreboard players get @n[tag=bullet.data] bullet.attribute.freeze
execute store result score #temp enemy.state.poison run scoreboard players get @n[tag=bullet.data] bullet.attribute.poison
execute store result score #temp enemy.state.flame run scoreboard players get @n[tag=bullet.data] bullet.attribute.flame
execute store result score #temp enemy.state.bleed run scoreboard players get @n[tag=bullet.data] bullet.attribute.bleed
execute store result score #temp enemy.state.weakness run scoreboard players get @n[tag=bullet.data] bullet.attribute.weakness
execute as @n[tag=enemy.target.hit] at @s run function enemy:hit/main
tp @s ~ ~ ~
execute as @n[tag=bullet.core] run function tower:bullet/remove
return 1