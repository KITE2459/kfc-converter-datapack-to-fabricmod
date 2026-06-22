execute store result score #temp enemy.number run random value 1..3
playsound entity.witch.drink record @a ~ ~ ~ 2.0 1.0
execute if score #temp enemy.number matches 1 run scoreboard players set @s enemy.attribute.healing 5
execute if score #temp enemy.number matches 2 unless entity @s[tag=enemy.attribute.heavy] run scoreboard players operation @s enemy.hp += @s enemy.hp
execute if score #temp enemy.number matches 2 unless entity @s[tag=enemy.attribute.heavy] run scoreboard players operation @s enemy.max_hp += @s enemy.max_hp
execute if score #temp enemy.number matches 2 unless entity @s[tag=enemy.attribute.heavy] run tag @s add enemy.attribute.heavy
execute if score #temp enemy.number matches 3 unless entity @s[tag=enemy.attribute.speed] run data modify entity @s data.speed set value 0.18
execute if score #temp enemy.number matches 3 unless entity @s[tag=enemy.attribute.speed] run tag @s add enemy.attribute.speed
scoreboard players set @s enemy.skill-trigger.hp -1
scoreboard players set @s enemy.skill-trigger.timer-cooldown 80