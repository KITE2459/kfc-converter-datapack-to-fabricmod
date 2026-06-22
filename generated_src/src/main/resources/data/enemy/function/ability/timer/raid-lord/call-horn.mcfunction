scoreboard players operation #temp enemy.skill-trigger.timer-cooldown = @s enemy.skill-trigger.timer-cooldown
playsound item.goat_horn.sound.6 weather @a ~ ~ ~
execute as @n[tag=map.spawn_point,tag=game] at @s run function enemy:ability/timer/raid-lord/call-horn-spawn_point


tag @s[scores={enemy.skill-trigger.timer-cooldown=..-101}] remove enemy.skill-loop.2
tag @s[scores={enemy.skill-trigger.timer-cooldown=..-101}] add enemy.skill-loop.3
execute if score @s enemy.skill-trigger.timer-cooldown matches -101 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 150