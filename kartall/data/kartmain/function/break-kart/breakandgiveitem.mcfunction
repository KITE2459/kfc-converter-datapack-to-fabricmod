#내 손에 카트가 있으면 give
$execute if items entity @s weapon *[minecraft:custom_data~{kartmobil:1}] run loot give @s loot {"pools":[{"rolls":1,"entries":[{"type":"minecraft:item","name":"$(id)","functions":[{"function":"minecraft:set_components","components":$(components)}]}]}]}

#다른 템이 있으면 그 템을 치우고 카트 주기
execute unless items entity @s weapon *[minecraft:custom_data~{kartmobil:1}] if items entity @s weapon * if data entity @s SelectedItem.components run function kartmain:break-kart/savehelditem with entity @s SelectedItem
execute unless items entity @s weapon *[minecraft:custom_data~{kartmobil:1}] if items entity @s weapon * unless data entity @s SelectedItem.components run function kartmain:break-kart/savehelditem-2 with entity @s SelectedItem
$execute unless items entity @s weapon *[minecraft:custom_data~{kartmobil:1}] if items entity @s weapon * run loot replace entity @s weapon loot {"pools":[{"rolls":1,"entries":[{"type":"minecraft:item","name":"$(id)","functions":[{"function":"minecraft:set_components","components":$(components)}]}]}]}

#내 손이 비어있으면
$execute unless items entity @s weapon * run loot replace entity @s weapon loot {"pools":[{"rolls":1,"entries":[{"type":"minecraft:item","name":"$(id)","functions":[{"function":"minecraft:set_components","components":$(components)}]}]}]}
