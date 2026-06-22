execute if score @s enemy.state.stun matches 1.. run return 0
execute if entity @s[tag=enemy.skill-trigger.low-hp] if score @s enemy.hp <= @s enemy.skill-trigger.hp run function enemy:ability/low-hp/main with entity @s data
execute if score @s enemy.skill-trigger.timer-cooldown matches ..100000 run scoreboard players remove @s enemy.skill-trigger.timer-cooldown 1
execute if score @s enemy.skill-trigger.timer-cooldown matches ..0 run function enemy:ability/timer/main with entity @s data