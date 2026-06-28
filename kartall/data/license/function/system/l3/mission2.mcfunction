
execute if score @s licensecount matches 5 run tp @s 8045 75 -8042 90 0
#11초(220) 안에 완주
bossbar set minecraft:license max 15500

bossbar set minecraft:license name {translate:"00:15.500 내로 완주","color":"aqua"}

execute store result bossbar minecraft:license value run scoreboard players get time timermain

execute if score time timermain matches 15501.. run function license:system/retirefail
execute on vehicle on vehicle at @s if block ~ ~-0.5 ~ grass_block on passengers as @s[tag=kartsaddle] on passengers if entity @s[type=player] run function license:system/retirefail
#완주 못하면 리타이어

#결승선을 지나기
execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ end_stone on passengers as @s[tag=kartsaddle] on passengers if entity @s[type=player] run function license:system/l3/mission2complete

tag @e[tag=mykart,type=#kartmobil:kartmobil] remove mykart