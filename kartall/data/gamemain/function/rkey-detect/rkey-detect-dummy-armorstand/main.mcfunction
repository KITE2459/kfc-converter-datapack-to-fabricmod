#인벤이 비어있으면 주기
execute unless items entity @s container.* minecraft:armor_stand if data entity @s Inventory[{Slot:9b}].components run function gamemain:rkey-detect/savehelditem with entity @s Inventory[{Slot:9b}]
execute unless items entity @s container.* minecraft:armor_stand unless data entity @s Inventory[{Slot:9b}].components run function gamemain:rkey-detect/savehelditem-2 with entity @s Inventory[{Slot:9b}]
execute unless items entity @s container.* minecraft:armor_stand run item replace entity @s container.9 with armor_stand

#F키 혹은 휠클릭을 하면 R키 작동
execute unless items entity @s container.9 minecraft:armor_stand if items entity @s weapon minecraft:armor_stand run function gamemain:rkey-by-user
execute unless items entity @s container.9 minecraft:armor_stand run clear @s armor_stand

kill @e[type=item,nbt={Item:{id:"minecraft:armor_stand"}},distance=..5]