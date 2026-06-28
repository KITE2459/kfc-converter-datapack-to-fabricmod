$data modify storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow set from storage devattack:dev-attack-shadows temp-shadow
#플레이어 닉네임과 카트 모델링 저장
tag @s add shadow-player
#setblock -139 -1 236 oak_sign{front_text:{messages:[{{"selector":"@a[tag=shadow-player,limit=1]"}},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 1 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"LogGamja"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 2 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"KITE2459"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 3 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"anotherone_yt"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 4 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"Pangch"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 5 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"_Nekter_"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 6 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"L_Peng"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 7 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"GhangDhang"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 8 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"Towercrain"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 9 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"Sidite"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 10 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"다오"},{translate:""},{translate:""},{translate:""}]}} destroy
execute if score #dev dev-count matches 11 run setblock -139 -1 236 oak_sign{front_text:{messages:[{translate:"ECYCEeeee"},{translate:""},{translate:""},{translate:""}]}} destroy

tag @s remove shadow-player
$data modify storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow.playername set from block -139 -1 236 front_text.messages[0]

$execute if data entity @n[tag=kart-special-ability,distance=..5] data.hide-player run data modify storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow.hideplayer set value 1
$execute unless data entity @n[tag=kart-special-ability,distance=..5] data.hide-player run data modify storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow.hideplayer set value 0

$execute if data entity @n[tag=kart-special-ability,distance=..5] data.saddle-height run data modify storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow.saddle-height set from entity @n[tag=kart-special-ability,distance=..5] data.saddle-height
$execute unless data entity @n[tag=kart-special-ability,distance=..5] data.saddle-height run data modify storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow.saddle-height set value 0

$data modify storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow.model set from entity @n[tag=kartdatacarrier] data.itemdata.components.minecraft:custom_data.model

$execute store result storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow.boostduration int 1 run scoreboard players get @n[tag=kartmobil,type=#kartmobil:kartmobil] kartboostduration
$execute store result storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow.engine int 1 run scoreboard players get @n[tag=kartmobil,type=#kartmobil:kartmobil] kart-engine
