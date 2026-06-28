#카트 무시
execute at @s unless score @s kart-engine matches 1004 run return 1
execute at @s unless score @n[tag=kartmobil,distance=0.01..1.4,type=#kartmobil:kartmobil] kart-engine matches 1004 run return 1

#전처리
tag @s add carA
tag @n[tag=kartmobil,tag=!carA,distance=0.01..1.4,type=#kartmobil:kartmobil] add carB

tag @s add car-collision
tag @n[tag=carB,type=#kartmobil:kartmobil] add car-collision

#서로 밀어내기
function kartmain:collision/pushslowly

#충돌 연산 하기 - 쿨타임이 10 이상이면 리셋 포인트이므로 무시, 10 미만이면 하기
execute unless score @n[tag=carB,type=#kartmobil:kartmobil] kartcollisiontime matches 4.. run function kartmobil:boat-engine/move/swap-speed

#마무리
tag @e[tag=car-collision] remove car-collision
tag @e[tag=carB] remove carB
tag @s remove carA