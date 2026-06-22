data remove entity @s equipment.mainhand
data modify entity @s data.speed set value 0.25
execute at @s run playsound item.shield.break record @a ~ ~ ~ 2.0 1.0
data modify entity @s data.defence set value 0
scoreboard players set @s enemy.defence 0
tag @s remove enemy.immune.stun
scoreboard players set @s enemy.skill-trigger.hp -1