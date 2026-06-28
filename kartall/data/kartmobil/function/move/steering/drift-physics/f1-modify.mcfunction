scoreboard players set #kart20 kartmain 20

scoreboard players set #kart200 kartmain 182
scoreboard players set #kart-1 kartmain -1

#속도가 높을수록 유효각을 좁힘
scoreboard players operation #allowed-angle kartmain = #kartspeed kartmove
execute if score #allowed-angle kartmain matches 160.. run scoreboard players set #allowed-angle kartmain 160
scoreboard players operation #kart200 kartmain -= #allowed-angle kartmain

scoreboard players operation #kartdrift-player kartmain *= #kart200 kartmain
scoreboard players operation #kartdrift-player kartmain /= #kart20 kartmain