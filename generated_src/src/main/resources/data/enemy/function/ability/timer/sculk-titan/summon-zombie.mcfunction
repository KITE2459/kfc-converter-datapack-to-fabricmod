execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run summon marker ~ ~ ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:5,model:skulk-zombie}}
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] as @n[tag=mob-gen,tag=not-allocated] run tag @s remove not-allocated
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run tag @s add map.spawn_point
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run function enemy:spawn/summon/main {model:catalyst-zombie}
execute if entity @s[scores={enemy.skill-trigger.timer-cooldown=0}] run tag @s remove map.spawn_point
tag @s[scores={enemy.skill-trigger.timer-cooldown=..-50}] remove enemy.skill-loop.1
tag @s[scores={enemy.skill-trigger.timer-cooldown=..-50}] add enemy.skill-loop.2
scoreboard players set @s[scores={enemy.skill-trigger.timer-cooldown=..-50}] enemy.skill-trigger.timer-cooldown 300