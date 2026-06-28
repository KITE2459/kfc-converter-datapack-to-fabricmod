
execute if score @s licensecount matches 5 run tp @s -64 4 434 0 0

#11초(220) 안에 완주
bossbar set minecraft:license max 15250

bossbar set minecraft:license name {translate:"00:15.250 내로 부스터 1개 이상 보유한 채 완주","color":"aqua"}

execute store result bossbar minecraft:license value run scoreboard players get time timermain

#z7엔진 부스터
execute if score @s licensecount matches 20 on vehicle on vehicle if score @s kartboostcount matches 1.. run tellraw @p {"translate":"해당 단계에서는 [Z7 엔진]의 능력을 사용할 수 없습니다."}
execute if score @s licensecount matches 20 on vehicle on vehicle run scoreboard players set @s kartboostcount 0

execute if score time timermain matches 15251.. run function license:system/retirefail
#완주 못하면 리타이어

#결승선을 지나기
execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ end_stone on passengers as @s[tag=kartsaddle] on passengers if entity @s[type=player] if score @n[tag=kartmobil,type=#kartmobil:kartmobil] kartboostcount matches 1.. run function license:system/l2/mission1complete
execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ end_stone on passengers as @s[tag=kartsaddle] on passengers if entity @s[type=player] unless score @n[tag=kartmobil,type=#kartmobil:kartmobil] kartboostcount matches 1.. run tellraw @s {translate:"부스터를 1개 이상 보유하지 않아 탈락했습니다.","color":"red"}
execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ end_stone on passengers as @s[tag=kartsaddle] on passengers if entity @s[type=player] unless score @n[tag=kartmobil,type=#kartmobil:kartmobil] kartboostcount matches 1.. run function license:system/retire