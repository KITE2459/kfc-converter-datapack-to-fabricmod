execute if score @s[tag=tower.animation0] tower.animation matches 10 run data modify entity @s item.components.minecraft:charged_projectiles set value [{id:"minecraft:arrow",count:1b}]
execute if score @s[tag=tower.animation0] tower.animation matches 10 run playsound item.crossbow.quick_charge_1 record @a ~ ~ ~ 0.7 1
execute if score @s[tag=tower.animation0] tower.animation matches 1 run data modify entity @s item.components.minecraft:charged_projectiles set value []
execute if score @s[tag=tower.animation0] tower.animation matches 1 run playsound item.crossbow.shoot record @a ~ ~ ~ 0.5 1