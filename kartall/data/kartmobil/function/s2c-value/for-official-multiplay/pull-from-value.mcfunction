execute unless score #is-official-multi multi-main matches 1 run return 0

execute store result score @s kart-engine run attribute @s minecraft:explosion_knockback_resistance modifier value get kart-engine
execute store result score @s kart-performance-limit-level run attribute @s minecraft:explosion_knockback_resistance modifier value get kart-performance-limit-level
execute store result score @s kart-tire run attribute @s minecraft:explosion_knockback_resistance modifier value get kart-tire
