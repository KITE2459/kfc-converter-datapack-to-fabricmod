execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run tag @s add enemy.attribute.taunt

execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run tag @s remove enemy.attribute.taunt
execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 320
