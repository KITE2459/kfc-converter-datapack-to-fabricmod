execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] at @n[tag=tower.core] run particle minecraft:block{block_state:"minecraft:blackstone"} ~ ~0.1 ~ 2 0.2 2 0 500 force
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] at @n[tag=tower.core] run scoreboard players set @e[tag=tower.data,distance=..5] tower.state.stun 120
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] at @n[tag=tower.core] run playsound minecraft:block.anvil.place record @a ~ ~ ~ 0.5 0.5
tag @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] remove enemy.skill-loop.2
tag @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] add enemy.skill-loop.1
scoreboard players set @s[scores={enemy.skill-trigger.timer-cooldown=..-40}] enemy.skill-trigger.timer-cooldown 150