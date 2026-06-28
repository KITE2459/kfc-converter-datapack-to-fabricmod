#알고리즘:
#1. 두 개의 히트박스 수치를 이용해 꼭짓점을 알 수 있다. 이렇게 모든 꼭짓점 위치에 엔티티를 놓는다. 중심에도 놓는다.
#상대 카트의 전후좌우중 한 방향을 선택하고 그 [반대 방향]으로 n-a만큼 이동한다. n의 범위를 갖는 원 안에 1에서 만든 꼭짓점이 있는지 검사한다. 범위에 들어오는 꼭짓점은 숫자를 1 올린다. (단, a는 선택한 방향으로의 히트박스 너비)
#숫자가 4인 꼭짓점이 존재한다면  충돌한 것이다.

#꼭짓점 배치
    tp @e[tag=kart-vertex,limit=5,type=area_effect_cloud] ~ ~ ~
    scoreboard players set @e[tag=kart-vertex,limit=5,type=area_effect_cloud] kartcollisiontime 0

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-left
    execute as @e[tag=kart-vertex,tag=kart-vertex-left,limit=2,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~-90 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-right
    execute as @e[tag=kart-vertex,tag=kart-vertex-right,limit=2,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~90 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-front
    execute as @e[tag=kart-vertex,tag=kart-vertex-front,limit=2,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~ 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-rear
    execute as @e[tag=kart-vertex,tag=kart-vertex-rear,limit=2,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~180 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

#상대방 기준 겹침 판단
    execute as @e[tag=kart-enemy,limit=1,distance=..2.6,type=#kartmobil:kartmobil] at @s run function kartmain:collision/rectangle-hitbox/calc-enemy
    execute if score #vertex-detected kartcollisiontime matches 0 run return run function kartmain:collision/rectangle-hitbox/finish-calc

execute if entity @e[tag=kart-vertex,scores={kartcollisiontime=4..},limit=5,distance=..2.6,type=area_effect_cloud] run scoreboard players set #crashed kartcollisiontime 1

function kartmain:collision/rectangle-hitbox/finish-calc