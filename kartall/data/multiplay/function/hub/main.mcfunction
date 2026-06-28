tag @a[x=-26,y=7,z=154,dx=22,dy=-8,dz=19] add multi-hub-player
tag @a[x=39,y=12,z=181,dx=10,dy=-8,dz=12] add multi-hub-player
tag @a[x=-215,y=4,z=-271,dx=115,dy=7,dz=-32] add multi-hub-player

execute as @a[tag=multi-hub-player,predicate=!kartmobil:shift] if items entity @s weapon *[minecraft:custom_data~{kartmobil:1}] run tag @s add multi-hub-ready
execute as @a[tag=multi-hub-player,predicate=!kartmobil:shift] if predicate kartmobil:ifride run tag @s add multi-hub-ready

#execute unless score #game multi-main matches 1 run team join multi-ready @a[tag=multi-hub-ready]
title @a[tag=multi-hub-player,tag=!multi-hub-ready,predicate=!kartmobil:shift] actionbar [{translate:"🚗 카트를 들지 않아 ","color":"green"},{translate:"연습카트","color":"yellow"},{translate:"를 탑승합니다 🚗","color":"green"}]
title @a[tag=multi-hub-player,tag=!multi-hub-ready,predicate=kartmobil:shift] actionbar [{translate:"! 웅크리기를 떼 주세요 !","color":"red"}]

execute as @a[tag=multi-hub-player,tag=multi-hub-ready] at @s unless items entity @s weapon *[minecraft:custom_data~{kartmobil:1}] run title @s actionbar [{translate:"선택한 카트: ","color":"yellow"},{"entity":"@n[tag=kartdatacarrier,distance=..4,type=item_display]","interpret":true,"nbt":"data.itemdata.components.minecraft:custom_name"}]
execute as @a[tag=multi-hub-player,tag=multi-hub-ready] if items entity @s weapon *[minecraft:custom_data~{kartmobil:1}] run title @s actionbar [{translate:"선택한 카트: ","color":"yellow"},{"entity":"@s","interpret":true,"nbt":"SelectedItem.components.minecraft:custom_name"}]

#팀전
execute if score team-battle multi-main matches 1 as @e[x=-26,y=-1,z=172,dx=22,dy=3,dz=-19,type=item] if entity @s[nbt={Item:{id:"minecraft:leather_helmet",count:1}}] run kill @s
execute if score team-battle multi-main matches 1 as @e[x=-26,y=-1,z=172,dx=22,dy=3,dz=-19,type=item] if entity @s[nbt={Item:{id:"minecraft:jigsaw",count:1}}] run kill @s
execute if score team-battle multi-main matches 1 as @a[tag=multi-hub-player] run function multiplay:hub/team-player

execute unless score team-battle multi-main matches 1 as @a[tag=multi-hub-player] if items entity @s armor.head leather_helmet run item replace entity @s armor.head with air

#관전룸
title @a[tag=!multi-hub-player,x=-34,y=3,z=154,dx=7,dy=4,dz=19] actionbar [{translate:"! 2층에서는 경기에 참가할 수 없습니다 !","color":"red"}]

execute positioned -4 -4 171 as @e[type=item,distance=..4] at @s if block ~ ~-0.5 ~ minecraft:smooth_red_sandstone run function multiplay:hub/china-trashbin