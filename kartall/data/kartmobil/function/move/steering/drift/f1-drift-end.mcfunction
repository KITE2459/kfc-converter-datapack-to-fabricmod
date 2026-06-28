scoreboard players set #kart200 kartmain 180
scoreboard players set #kart-1 kartmain -1

#속도가 높을수록 유효각을 좁힘
scoreboard players operation #allowed-angle kartmain = #kartspeed kartmove
execute if score #allowed-angle kartmain matches 160.. run scoreboard players set #allowed-angle kartmain 160
scoreboard players operation #kart200 kartmain -= #allowed-angle kartmain

scoreboard players set #allowed-angle kartmain 1
scoreboard players operation #allowed-angle kartmain *= #kart200 kartmain
scoreboard players operation #allowed-angle-minus kartmain = #allowed-angle kartmain
scoreboard players operation #allowed-angle-minus kartmain *= #kart-1 kartmain


execute if score #allowed-angle-minus kartmain < #kartangle kartmain if score #kartangle kartmain < #allowed-angle kartmain run function kartmobil:move/steering/drift/drift-end