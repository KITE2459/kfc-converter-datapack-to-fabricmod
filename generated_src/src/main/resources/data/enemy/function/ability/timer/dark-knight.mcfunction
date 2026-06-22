execute if score @s enemy.skill-trigger.timer-cooldown matches 0 unless entity @n[tag=tower.data,scores={tower.y=-60}] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10

execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run data modify entity @s data.last_rotation set from entity @s Rotation
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run rotate @s facing entity @n[tag=tower.data,scores={tower.y=-60},sort=nearest,limit=1] eyes
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.zombie.ambient record @a ~ ~ ~ 1.0 0.6
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 as @n[tag=tower.data,scores={tower.y=-60},sort=nearest,limit=1] at @s run particle minecraft:explosion ~ ~ ~ 2.5 0.2 2.5 0 30 force @a

execute if score @s enemy.skill-trigger.timer-cooldown matches -20 as @n[tag=tower.data,scores={tower.y=-60},sort=nearest,limit=1] at @s run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..5] tower.state.stun 100

execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run data modify entity @s Rotation set from entity @s data.last_rotation
execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 300

