#drift-start와 동일한 유효각 계산을 사용
scoreboard players set #kart200 kartmain 180
scoreboard players operation #allowed-angle kartmain = #kartspeed kartmove
execute if score #allowed-angle kartmain matches 160.. run scoreboard players set #allowed-angle kartmain 160
scoreboard players operation #kart200 kartmain -= #allowed-angle kartmain

scoreboard players set #allowed-angle kartmain 2
scoreboard players operation #allowed-angle kartmain *= #kart200 kartmain
scoreboard players operation #steeranglelimit kartboostcount = #allowed-angle kartmain



function kartmobil:move/steering/f1-steer-gauge/mode2