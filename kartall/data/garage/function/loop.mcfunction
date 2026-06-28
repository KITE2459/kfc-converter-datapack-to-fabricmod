
#플레이어가 가까이 가면 카트 보여주기
execute at @a[tag=!multi-hub-player,tag=!kart-multi-player] run tag @e[distance=..5,tag=garage-kart,type=item_display] add garage-show
execute at @a[tag=!multi-hub-player,tag=!kart-multi-player,gamemode=creative] run tag @e[distance=..5,tag=garage-kart,type=item_display] add garage-show-unsecurity
execute as @e[tag=garage-kart,tag=garage-show,type=item_display] at @s run function garage:system/showkart

#숨기기
execute as @e[tag=garage-kart,tag=garage-show,type=item_display] at @s unless entity @a[distance=..5] run function garage:system/hidekart

#엔진 선택 인터랙션
execute as @e[tag=kart-engine-interaction,type=interaction] at @s run function garage:engine-interaction
execute as @e[tag=kart-tire-interaction,type=interaction] at @s run function garage:tire-interaction

function garage:fast-garage/loop

#커스텀
function garage:custom-model-maker/main

#엔더진주 클리어
execute positioned 5176 4 5155 run tag @a[distance=..256] add garage-near-player
execute positioned 5176 4 5155 run clear @a[tag=!garage-near-player,gamemode=adventure] minecraft:ender_pearl
execute positioned 5176 4 5155 run tag @a[tag=garage-near-player] remove garage-near-player

execute positioned 5176 4 5155 as @e[type=minecraft:ender_pearl,distance=..256] at @s run function garage:enderpearl