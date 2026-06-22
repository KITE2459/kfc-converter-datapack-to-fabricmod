execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~ ~ ~-2 run function enemy:spawn/summon/main {model:healing-crystal}
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~2 ~ ~ run function enemy:spawn/summon/main {model:healing-crystal}
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~-2 ~ ~ run function enemy:spawn/summon/main {model:healing-crystal}
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~ ~ ~2 run function enemy:spawn/summon/main {model:healing-crystal}

execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~ ~ ~-2 run tag @n[tag=enemy.data,nbt={data:{id:"healing-crystal"}},distance=..1,sort=nearest,limit=1] add enemy.skill.raid-lord.healing-crystal
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~2 ~ ~ run tag @n[tag=enemy.data,nbt={data:{id:"healing-crystal"}},distance=..1,sort=nearest,limit=1] add enemy.skill.raid-lord.healing-crystal
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~-2 ~ ~ run tag @n[tag=enemy.data,nbt={data:{id:"healing-crystal"}},distance=..1,sort=nearest,limit=1] add enemy.skill.raid-lord.healing-crystal
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 positioned ~ ~ ~2 run tag @n[tag=enemy.data,nbt={data:{id:"healing-crystal"}},distance=..1,sort=nearest,limit=1] add enemy.skill.raid-lord.healing-crystal

execute if entity @e[tag=enemy.skill.raid-lord.healing-crystal] run scoreboard players set @s enemy.attribute.healing 750
execute if entity @e[tag=enemy.skill.raid-lord.healing-crystal] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 0

execute unless entity @e[tag=enemy.skill.raid-lord.healing-crystal] run scoreboard players set @s enemy.attribute.healing 0
execute unless entity @e[tag=enemy.skill.raid-lord.healing-crystal] run tag @s remove enemy.skill-loop.3
execute unless entity @e[tag=enemy.skill.raid-lord.healing-crystal] run tag @s add enemy.skill-loop.1
execute unless entity @e[tag=enemy.skill.raid-lord.healing-crystal] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150
