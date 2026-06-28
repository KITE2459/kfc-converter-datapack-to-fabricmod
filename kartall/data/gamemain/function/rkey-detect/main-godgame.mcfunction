#F키 하면 R 작동
execute if items entity @s weapon minecraft:armor_stand[custom_data={rkey:1}] run function gamemain:rkey
execute if items entity @s weapon minecraft:armor_stand[custom_data={rkey:1}] run function gamemain:rkey-detect/swaphand

#재지급
execute unless items entity @s weapon.offhand minecraft:armor_stand[custom_data={rkey:1}] if data entity @s equipment.offhand.components run function gamemain:rkey-detect/savehelditem with entity @s equipment.offhand
execute unless items entity @s weapon.offhand minecraft:armor_stand[custom_data={rkey:1}] unless data entity @s equipment.offhand.components run function gamemain:rkey-detect/savehelditem-2 with entity @s equipment.offhand

execute store result score #armor-count kartmain run clear @s minecraft:armor_stand[custom_data={rkey:1}] 0

execute if score #armor-count kartmain matches 2.. run clear @s minecraft:armor_stand[custom_data={rkey:1}]
execute unless items entity @s weapon.offhand minecraft:armor_stand[custom_data={rkey:1}] run item replace entity @s weapon.offhand with armor_stand[custom_data={rkey:1},item_model="air",custom_name=["R키 감지용 아이템[",{"keybind":"key.swapOffhand"},"]"]]

kill @e[type=item,nbt={Item:{id:"minecraft:armor_stand"},components:{"minecraft:custom_data":{rkey:1}}},distance=..5]