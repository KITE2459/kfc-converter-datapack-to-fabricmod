# 플레이어 아이템 가져오기
data remove storage tower temp
data remove storage tower temp2
#item replace block 0 -61 0 container.0 from entity @s weapon.mainhand
#item replace block 0 -61 0 container.1 from entity @s weapon.offhand
data modify storage tower temp set from entity @s SelectedItem
data modify storage tower temp2 set from entity @s equipment.offhand

# 블루프린트 감지
execute if data storage tower temp.components.minecraft:custom_data.Tower_Status run function tower:spawn/blueprint/main
execute if entity @s[tag=blueprint_player] unless data storage tower temp.components.minecraft:custom_data.Tower_Status run function tower:spawn/blueprint/remove

# 타워 소환
execute if data storage tower temp2.components.minecraft:custom_data.Tower_Status run function tower:spawn/summon/main