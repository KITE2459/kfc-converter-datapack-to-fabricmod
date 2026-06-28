$data modify storage timeattack:timeattack-shadows map$(tempmap)-shadow set from storage timeattack:timeattack-shadows temp-shadow
#플레이어 닉네임과 카트 모델링 저장
tag @s add shadow-player
setblock -139 -1 236 oak_sign{front_text:{messages:[{"selector":"@a[tag=shadow-player,limit=1]"},{translate:"'s Shadow"},{translate:""},{translate:""},{translate:""}]}} destroy
tag @s remove shadow-player
$data modify storage timeattack:timeattack-shadows map$(tempmap)-shadow.playername set from block -139 -1 236 front_text.messages[0]


$execute if data entity @n[tag=kart-special-ability,distance=..5] data.hide-player run data modify storage timeattack:timeattack-shadows map$(tempmap)-shadow.hideplayer set value 1
$execute unless data entity @n[tag=kart-special-ability,distance=..5] data.hide-player run data modify storage timeattack:timeattack-shadows map$(tempmap)-shadow.hideplayer set value 0

$execute if data entity @n[tag=kart-special-ability,distance=..5] data.saddle-height run data modify storage timeattack:timeattack-shadows map$(tempmap)-shadow.saddle-height set from entity @n[tag=kart-special-ability,distance=..5] data.saddle-height
$execute unless data entity @n[tag=kart-special-ability,distance=..5] data.saddle-height run data modify storage timeattack:timeattack-shadows map$(tempmap)-shadow.saddle-height set value 0


$data modify storage timeattack:timeattack-shadows map$(tempmap)-shadow.model set from entity @n[tag=kartdatacarrier] data.itemdata.components.minecraft:custom_data.model

$execute store result storage timeattack:timeattack-shadows map$(tempmap)-shadow.boostduration int 1 run scoreboard players get @n[tag=kartmobil,type=#kartmobil:kartmobil] kartboostduration
$execute store result storage timeattack:timeattack-shadows map$(tempmap)-shadow.engine int 1 run scoreboard players get @n[tag=kartmobil,type=#kartmobil:kartmobil] kart-engine
