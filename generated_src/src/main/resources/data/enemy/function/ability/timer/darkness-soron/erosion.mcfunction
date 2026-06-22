execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run particle minecraft:explosion ~ ~ ~ 10 0.2 10 0 120 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.ender_dragon.growl record @a ~ ~ ~ 0.7 0.8

execute if score @s enemy.skill-trigger.timer-cooldown matches -20 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60}] tower.state.stun 60

execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run tag @s remove enemy.skill-loop.3
execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run tag @s add enemy.skill-loop.5
execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
