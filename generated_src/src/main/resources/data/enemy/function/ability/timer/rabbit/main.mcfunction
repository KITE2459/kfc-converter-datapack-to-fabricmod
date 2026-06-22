data modify storage enemy temp set from entity @s data

execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run playsound minecraft:entity.rabbit.jump record @a ~ ~ ~ 1.0 1.0
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] positioned ~ -59.5 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-1}] positioned ~ -59.1 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-2}] positioned ~ -58.8 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-3}] positioned ~ -58.6 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-4}] positioned ~ -58.5 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-5}] positioned ~ -58.6 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-6}] positioned ~ -58.8 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-7}] positioned ~ -59.1 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-8}] positioned ~ -59.5 ~ run function enemy:move/teleport with storage enemy temp
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-9}] positioned ~ -60.0 ~ run function enemy:move/teleport with storage enemy temp

execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=-20}] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 1