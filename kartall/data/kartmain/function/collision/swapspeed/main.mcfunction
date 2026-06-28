
#방향, 속력, 충돌방어력 저장
execute store result score #direction-a kartcollisiontime run data get entity @e[tag=carAdirection,limit=1,type=item_display] Rotation[0]
execute store result score #direction-b kartcollisiontime run data get entity @e[tag=carBdirection,limit=1,type=item_display] Rotation[0]

scoreboard players operation #kart-a-speed kartcollisiontime = @s kartmove
scoreboard players operation #kart-b-speed kartcollisiontime = @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartmove

#두 원의 탄성 충돌 연산
function kartmain:collision/swapspeed/calc
function kartmain:collision/swapspeed/swapspeedy

#뭉개기를 많이 했을 경우 벌을 받아야겠지?
scoreboard players remove @s[scores={kartweirdcollisioncount=4..,kartmove=5000..}] kartmove 5000
scoreboard players remove @e[tag=carB,scores={kartweirdcollisioncount=4..,kartmove=5000..},limit=1,type=#kartmobil:kartmobil] kartmove 5000