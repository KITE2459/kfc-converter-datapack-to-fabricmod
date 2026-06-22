execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] run scoreboard players set @e[tag=tower.data,scores={tower.y=-60},distance=..10] tower.state.stun 55


tag @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] remove enemy.skill-loop.1
tag @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] add enemy.skill-loop.2
execute if score @s enemy.skill-trigger.timer-cooldown matches -40 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150