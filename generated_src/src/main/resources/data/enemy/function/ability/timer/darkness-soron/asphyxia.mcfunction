execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run tag @e[tag=tower.target.darkness-soron] remove tower.target.darkness-soron
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 as @n[tag=tower.core,sort=nearest,limit=1] run tag @s add tower.target.darkness-soron
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 as @e[tag=tower.target.darkness-soron,limit=1] at @s run particle minecraft:explosion ~ ~ ~ 0.8 0.2 0.8 0 20 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.wither.shoot record @a ~ ~ ~ 0.8 0.4

execute if score @s enemy.skill-trigger.timer-cooldown matches -100 as @e[tag=tower.target.darkness-soron,tag=tower.core,limit=1] at @s run kill @e[distance=..0.001,tag=tower,tag=!tower.core]
execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run kill @e[tag=tower.target.darkness-soron,tag=tower.core,limit=1]
execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run tag @e[tag=tower.target.darkness-soron] remove tower.target.darkness-soron

execute if score @s enemy.skill-trigger.timer-cooldown matches -160 run tag @s remove enemy.skill-loop.5
execute if score @s enemy.skill-trigger.timer-cooldown matches -160 run tag @s add enemy.skill-loop.1
execute if score @s enemy.skill-trigger.timer-cooldown matches -160 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
