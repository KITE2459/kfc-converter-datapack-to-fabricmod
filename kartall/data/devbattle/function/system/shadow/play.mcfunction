#초기화
summon marker ~ ~ ~ {Tags:["kart-shadow"]}

#부스터 타이밍
execute store result score @e[tag=shadow-models,tag=shadow-main,type=item_display,limit=1] kartboosttime run data get storage devattack:dev-attack-shadows temp-shadow-play.booster[0]
data remove storage devattack:dev-attack-shadows temp-shadow-play.booster[0]

#뛰라이더 연출
execute store result score #kartspeed kartmove run data get storage devattack:dev-attack-shadows temp-shadow-play.kartspeed[0]
data remove storage devattack:dev-attack-shadows temp-shadow-play.kartspeed[0]
execute as @e[tag=shadow-models,tag=shadow-main,type=item_display,limit=1] at @s if entity @e[tag=kart-run-anime,tag=kart-run-anime-mid,distance=..0.3,type=#kartmobil:kartmodels] run function kartmobil:move/run-anime/main

#부스터 연출
execute as @e[tag=shadow-models,tag=shadow-main,type=item_display,limit=1] at @s run function devbattle:system/shadow/boost

#섀도우의 위치가 로딩되는지 확인
execute store result storage minecraft:kartmain shadowposx float 1 run data get storage devattack:dev-attack-shadows temp-shadow-play.pos[0][0]
execute store result storage minecraft:kartmain shadowposy float 1 run data get storage devattack:dev-attack-shadows temp-shadow-play.pos[0][1]
execute store result storage minecraft:kartmain shadowposz float 1 run data get storage devattack:dev-attack-shadows temp-shadow-play.pos[0][2]
function devbattle:system/shadow/shadow-pos-macro with storage minecraft:kartmain

#모델링 관리
execute if score #shadow-loaded dev-count matches 0 run kill @e[tag=shadow-models]
execute if score #shadow-loaded dev-count matches 1 unless entity @e[tag=shadow-models] positioned ~ ~-2 ~ run function devbattle:system/shadow/summonmodel

#위치 설정(쉐도우가 탄 아머스탠드)
execute if score #shadow-loaded dev-count matches 1 run data modify entity @e[tag=shadow-models,tag=shadow-main,type=item_display,limit=1] Pos set from storage devattack:dev-attack-shadows temp-shadow-play.pos[0]
data remove storage devattack:dev-attack-shadows temp-shadow-play.pos[0]

#회전각도 설정(모델)
execute as @e[tag=kart-shadow,type=marker] run data modify entity @s Rotation set from storage devattack:dev-attack-shadows temp-shadow-play.rotation[0]
execute at @e[tag=kart-shadow,limit=1,type=marker] as @e[tag=shadow-models,distance=..100,type=#kartmobil:kartmodels] run rotate @s ~ ~

data remove storage devattack:dev-attack-shadows temp-shadow-play.rotation[0]


kill @e[tag=kart-shadow]