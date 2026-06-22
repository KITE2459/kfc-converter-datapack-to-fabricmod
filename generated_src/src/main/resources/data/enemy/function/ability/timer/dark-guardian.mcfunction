execute if score @s enemy.skill-trigger.timer-cooldown matches 0 unless entity @n[tag=tower.data,scores={tower.y=-60}] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10

execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run data modify entity @s data.last_rotation set from entity @s Rotation
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run rotate @s facing entity @n[tag=tower.data,scores={tower.y=-60},sort=nearest,limit=1] eyes
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound minecraft:entity.wither.shoot record @a ~ ~ ~ 1.0 0.8
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^1 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^2 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^3 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^4 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^5 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^6 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^7 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^8 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^9 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 at @s positioned ^ ^ ^10 run particle minecraft:explosion ~ ~ ~ 0.6 0.2 0.6 0 8 force @a

execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^1 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^2 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^3 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^4 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^5 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^6 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^7 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^8 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^9 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 at @s positioned ^ ^ ^10 run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..2] tower.state.stun 100

execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run data modify entity @s Rotation set from entity @s data.last_rotation
execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 300

