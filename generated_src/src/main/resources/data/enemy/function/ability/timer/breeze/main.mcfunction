execute if score @s enemy.skill-trigger.timer-cooldown matches -4 run data modify entity @s data.speed set value 1.0
execute if score @s enemy.skill-trigger.timer-cooldown matches -4 run playsound minecraft:entity.breeze.charge record @a ~ ~ ~ 1.0 1.0
execute if score @s enemy.skill-trigger.timer-cooldown matches -8..-4 run particle explosion ~ ~ ~ 0 0 0 0 1 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches -15 run playsound minecraft:entity.breeze.jump record @a ~ ~ ~ 1.0 1.0
execute if score @s enemy.skill-trigger.timer-cooldown matches -20..-15 run particle explosion ~ ~ ~ 0 0 0 0 1 force @a
data modify storage enemy temp set from entity @s data
execute if score @s enemy.skill-trigger.timer-cooldown matches -15 positioned ~ -59.3 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -16 positioned ~ -58.7 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -17 positioned ~ -58.2 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -18 positioned ~ -57.8 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -19 positioned ~ -57.5 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -20 positioned ~ -57.3 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -21 positioned ~ -57.2 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -22 positioned ~ -57.2 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -23 positioned ~ -57.3 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -24 positioned ~ -57.5 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -25 positioned ~ -57.8 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -26 positioned ~ -58.2 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -27 positioned ~ -58.7 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -28 positioned ~ -59.3 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -29 positioned ~ -60 ~ run function enemy:move/teleport with storage enemy temp
execute if score @s enemy.skill-trigger.timer-cooldown matches -30 run playsound minecraft:entity.breeze.land record @a ~ ~ ~ 1.0 1.0
execute if score @s enemy.skill-trigger.timer-cooldown matches -30 run particle minecraft:explosion ~ ~ ~ 0 0 0 0 1 force @a
execute if score @s enemy.skill-trigger.timer-cooldown matches -30 run data modify entity @s data.speed set value 0.22
execute if score @s enemy.skill-trigger.timer-cooldown matches -30 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 100