#선 안에 들어오는 꼭짓점의 스코어 올리기
execute positioned ^ ^ ^8 if entity @e[tag=kart-vertex,limit=5,distance=..8,type=area_effect_cloud] run scoreboard players set #vertex-detected kartcollisiontime 1
execute positioned ^ ^ ^8 run scoreboard players add @e[tag=kart-vertex,limit=5,distance=..8,type=area_effect_cloud] kartcollisiontime 1