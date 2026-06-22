data modify storage enemy temp set from entity @s data

data modify storage enemy temp.speed set value 1.0
function enemy:move/teleport with storage enemy temp
execute at @s run function enemy:ability/timer/dash-zombie/teleport
scoreboard players set @s enemy.skill-trigger.timer-cooldown 20