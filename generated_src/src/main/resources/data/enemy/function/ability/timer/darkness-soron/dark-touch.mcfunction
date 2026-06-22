execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run particle minecraft:explosion ~ ~ ~ 3.5 0.2 3.5 0 35 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.wither.ambient record @a ~ ~ ~ 1.0 0.7

execute if score @s enemy.skill-trigger.timer-cooldown matches -60 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..7] tower.state.stun 150

execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run tag @s remove enemy.skill-loop.2
execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run tag @s add enemy.skill-loop.3
execute if score @s enemy.skill-trigger.timer-cooldown matches -80 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
