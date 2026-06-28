#작은 카트일 경우 좁은 범위로 계산
execute if score #kart-hitbox-mode kartcollisiontime matches 1 if entity @s[tag=kart-hitbox-normal] unless entity @e[tag=kartmobil,distance=0.01..3.75,limit=1,type=#kartmobil:kartmobil] run return 1
execute if score #kart-hitbox-mode kartcollisiontime matches 1 if entity @s[tag=kart-hitbox-mid] unless entity @e[tag=kartmobil,distance=0.01..3,limit=1,type=#kartmobil:kartmobil] run return 1
execute if score #kart-hitbox-mode kartcollisiontime matches 1 if entity @s[tag=kart-hitbox-small] unless entity @e[tag=kartmobil,distance=0.01..2.25,limit=1,type=#kartmobil:kartmobil] run return 1

#서로 같은 카트끼리 2번 실행 방지
execute if entity @s[tag=kart-collision-executed] if entity @e[tag=kartmobil,tag=kart-collision-executed,distance=0.01..4,limit=1,type=#kartmobil:kartmobil] run return 1
tag @e[tag=kartmobil,distance=..4,limit=2,sort=nearest,type=#kartmobil:kartmobil] add kart-collision-executed

#태그 전처리
tag @s add carA
execute unless score #kart-hitbox-mode kartcollisiontime matches 1 run tag @n[tag=kartmobil,tag=!carA,distance=0.01..1.6,type=#kartmobil:kartmobil] add carB
execute if score #kart-hitbox-mode kartcollisiontime matches 1 run tag @n[tag=kartmobil,tag=!carA,distance=0.01..4,type=#kartmobil:kartmobil] add carB

tag @s add car-collision
tag @e[tag=carB,distance=..4,limit=1,type=#kartmobil:kartmobil] add car-collision

#B가 없으면 무시
execute unless entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] run return run function kartmain:collision/finish-collision

#보트 엔진 무시
execute at @s if score @s kart-engine matches 1004 run return run function kartmain:collision/finish-collision
execute at @s if score @e[tag=carB,distance=..4,limit=1,type=#kartmobil:kartmobil] kart-engine matches 1004 run return run function kartmain:collision/finish-collision

#큰 카트 좌우 히트박스 조절
execute if entity @s[tag=kart-stop] run return run function kartmain:collision/finish-collision
execute if score @s startboosttime matches 1.. if entity @s[tag=kart-wide-hitbox] run function kartmain:collision/hitbox-half

#사각형 히트박스 감지
execute if score #kart-hitbox-mode kartcollisiontime matches 1 run function kartmain:collision/rectangle-hitbox/main
execute if score #kart-hitbox-mode kartcollisiontime matches 1 if entity @s[tag=kart-hitbox-half] run function kartmain:collision/hitbox-revert
execute if score #kart-hitbox-mode kartcollisiontime matches 1 if score #crashed kartcollisiontime matches 0 run return run function kartmain:collision/finish-collision

#충돌 사이즈 분리
    #작은&큰
    execute unless score #kart-hitbox-mode kartcollisiontime matches 1 if entity @s[tag=kart-small-size] as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] if entity @s[tag=!kart-small-size] unless entity @s[distance=..1.5] run return run function kartmain:collision/finish-collision
    execute unless score #kart-hitbox-mode kartcollisiontime matches 1 if entity @s[tag=!kart-small-size] as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] if entity @s[tag=kart-small-size] unless entity @s[distance=..1.5] run return run function kartmain:collision/finish-collision
    #작은&작은
    execute unless score #kart-hitbox-mode kartcollisiontime matches 1 if entity @s[tag=kart-small-size] as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] if entity @s[tag=kart-small-size] unless entity @s[distance=..1.4] run return run function kartmain:collision/finish-collision

#탑승자에게 태그 주기
execute on passengers if entity @s[tag=kartsaddle,predicate=kartmobil:ifpassenger,type=#kartmobil:kartsaddle] on passengers run tag @s[predicate=kartmobil:ifride,type=player] add kartpassenger-collision
execute as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] on passengers if entity @s[tag=kartsaddle,predicate=kartmobil:ifpassenger,type=#kartmobil:kartsaddle] on passengers run tag @s[predicate=kartmobil:ifride,type=player] add kartpassenger-collision

#소리 듣기
function kartmain:collision/add-kart-listener-collision

#방향 엔티티
execute on passengers if entity @s[tag=kartdirection,type=item_display] run tag @s add carAdirection
execute as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] on passengers if entity @s[tag=kartdirection,type=item_display] run tag @s add carBdirection
 
#충돌 연산 하기 - 쿨타임이 10 이상이면 리셋 포인트이므로 무시, 10 미만이면 하기
execute unless score @s kartcollisiontime matches 1.. unless score @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartcollisiontime matches 10.. run function kartmain:collision/collisionmain
execute if score @s kartweirdcollisioncount matches 5.. unless score @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartcollisiontime matches 10.. run function kartmain:collision/collisionmain
execute if score @s kartweirdcollisioncount matches 5.. run scoreboard players set @s kartweirdcollisioncount 0

#서로 밀어내기
execute if score #kart-hitbox-mode kartcollisiontime matches 1 unless score @s kartcollisiontime matches 10.. unless score @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartcollisiontime matches 10.. run function kartmain:collision/pushslowly/rectangle
execute unless score #kart-hitbox-mode kartcollisiontime matches 1 unless score @s kartcollisiontime matches 10.. unless score @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartcollisiontime matches 10.. run function kartmain:collision/pushslowly/circle

#마무리
function kartmain:collision/finish-collision