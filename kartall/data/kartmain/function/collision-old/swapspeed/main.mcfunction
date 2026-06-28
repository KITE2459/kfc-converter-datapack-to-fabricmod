#반발계수 e * 100
scoreboard players operation #100e-dynamic kartcollisiontime = #100e-old kartcollisiontime

execute store result score #direction-a kartcollisiontime run data get entity @n[tag=carAdirection,type=item_display] Rotation[0]
execute store result score #direction-b kartcollisiontime run data get entity @n[tag=carBdirection,type=item_display] Rotation[0]

#속력을 스왑
function kartmain:collision-old/swapspeed/normal-line-calib
    scoreboard players operation #100e-dynamic kartcollisiontime -= #100e-offset kartcollisiontime
    execute if score #100e-dynamic kartcollisiontime matches ..-1 run scoreboard players set #100e-dynamic kartcollisiontime 0
    execute if score #100e-dynamic kartcollisiontime matches 100.. run scoreboard players set #100e-dynamic kartcollisiontime 100
function kartmain:collision-old/swapspeed/swapspeed

#A와 B의 y벡터를 스왑
execute store result storage kartmain kartAdefense int 1 run scoreboard players get @n[tag=carA,type=#kartmobil:kartmobil] kartdefense
execute store result storage kartmain kartBdefense int 1 run scoreboard players get @n[tag=carB,type=#kartmobil:kartmobil] kartdefense
function kartmain:collision-old/swapspeed/swap-direction

execute if score #from-new-collision kartcollisiontime matches 1 run return 1

#y속력바꾸기
function kartmain:collision-old/swapspeed/swapspeedy

#뭉개기를 많이 했을 경우 벌을 받아야겠지?
scoreboard players remove @e[tag=carA,scores={kartweirdcollisioncount=4..,kartmove=5000..},limit=1,type=#kartmobil:kartmobil] kartmove 5000
scoreboard players remove @e[tag=carB,scores={kartweirdcollisioncount=4..,kartmove=5000..},limit=1,type=#kartmobil:kartmobil] kartmove 5000