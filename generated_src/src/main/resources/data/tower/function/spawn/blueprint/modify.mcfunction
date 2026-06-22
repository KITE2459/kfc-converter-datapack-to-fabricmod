execute store result score buffer tower.number run data get storage minecraft:tower temp.components.minecraft:custom_data.Tower_Status.number
execute if score buffer tower.number = @s tower.number run return fail
# apply size/translation to player's blueprint block_display
execute as @e[type=minecraft:block_display,tag=blueprint,scores={blueprint_id=1..}] if score @s blueprint_id = @p blueprint_id run data modify entity @s transformation.scale[0] set from entity @p SelectedItem.components.minecraft:custom_data.Tower_Status.size
execute as @e[type=minecraft:block_display,tag=blueprint,scores={blueprint_id=1..}] if score @s blueprint_id = @p blueprint_id run data modify entity @s transformation.scale[2] set from entity @p SelectedItem.components.minecraft:custom_data.Tower_Status.size
execute as @e[type=minecraft:block_display,tag=blueprint,scores={blueprint_id=1..}] if score @s blueprint_id = @p blueprint_id run data modify entity @s transformation.translation[0] set from entity @p SelectedItem.components.minecraft:custom_data.Tower_Status.translation
execute as @e[type=minecraft:block_display,tag=blueprint,scores={blueprint_id=1..}] if score @s blueprint_id = @p blueprint_id run data modify entity @s transformation.translation[2] set from entity @p SelectedItem.components.minecraft:custom_data.Tower_Status.translation
# cache tower number on player
execute store result score @s tower.number run data get storage minecraft:tower temp.components.minecraft:custom_data.Tower_Status.number