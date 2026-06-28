

execute if score @s licensecount matches 5 run function license:movetomap


#11초(220) 안에 완주
bossbar set minecraft:license max 61600

bossbar set minecraft:license name {translate:"01:01.600 내로 완주","color":"aqua"}

execute store result bossbar minecraft:license value run scoreboard players get time timermain

execute if score time timermain matches 61601.. run function license:system/retirefail
#완주 못하면 리타이어

#체크포인트 통과
execute if score @s licensecount matches 61.. run function checkpoint:system/player-main

#결승선을 지나기
execute on vehicle on vehicle at @s if block ~ ~-1.5 ~ magma_block on passengers as @s[tag=kartsaddle] on passengers if entity @s[tag=check-pass-last] run function license:system/passline
execute if score @s trackselect-lap >= #max-lap trackselect-lap run function license:system/pro/mission4complete
