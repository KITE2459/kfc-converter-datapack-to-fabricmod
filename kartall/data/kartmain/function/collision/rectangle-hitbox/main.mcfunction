scoreboard players set #crashed kartcollisiontime 0

#속도가 빠른 차가 우선: 느린 차가 상대방
execute if score @e[tag=carA,limit=1,type=#kartmobil:kartmobil] kartmove < @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartmove run tag @e[tag=carA,limit=1,type=#kartmobil:kartmobil] add kart-enemy
execute if score @e[tag=carA,limit=1,type=#kartmobil:kartmobil] kartmove >= @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartmove run tag @e[tag=carB,limit=1,type=#kartmobil:kartmobil] add kart-enemy

#tag @e[tag=car-collision,limit=1,sort=random,type=#kartmobil:kartmobil] add kart-enemy
tag @e[tag=car-collision,tag=kart-enemy,limit=1,type=#kartmobil:kartmobil] add kart-enemy-first

#첫번째 카트 계산
execute as @e[tag=car-collision,tag=!kart-enemy,limit=1,type=#kartmobil:kartmobil] positioned as @s on passengers if entity @s[tag=kartmodelsaddle] rotated as @s on vehicle run function kartmain:collision/rectangle-hitbox/calc

#if: 충돌 감지 시 바로 탈출
execute if score #crashed kartcollisiontime matches 1 run return run function kartmain:collision/rectangle-hitbox/finish

#else: 첫번째에서 선택받지 못한 카트
tag @e[tag=car-collision,tag=!kart-enemy,limit=1,type=#kartmobil:kartmobil] add kart-enemy
tag @e[tag=car-collision,tag=kart-enemy-first,limit=1,type=#kartmobil:kartmobil] remove kart-enemy

#두번째 카트 계산
execute as @e[tag=car-collision,tag=kart-enemy-first,limit=1,type=#kartmobil:kartmobil] positioned as @s on passengers if entity @s[tag=kartmodelsaddle] rotated as @s on vehicle run function kartmain:collision/rectangle-hitbox/calc

function kartmain:collision/rectangle-hitbox/finish