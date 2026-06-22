execute unless data entity @s SelectedItem.components.minecraft:custom_data.Tower_Status run tellraw @s {"text":"주 손에 타워 아이템을 들어야 합니다.","color":"red"}
execute unless data entity @s SelectedItem.components.minecraft:custom_data.Tower_Status run return fail

execute anchored eyes positioned ^ ^ ^3 rotated as @s positioned ~ ~-1.62 ~ rotated ~ 0 run function tower:armory/create_place

tellraw @s {"text":"무기고를 소환했습니다.","color":"green"}