execute if entity @n[tag=tower.core,distance=..5] run function enemy:ability/timer/sculk-titan/ground-break
execute unless entity @n[tag=tower.core,distance=..5] if entity @n[tag=tower.core] run function enemy:ability/timer/sculk-titan/sculk-sonicwave
execute unless entity @n[tag=tower.core] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10