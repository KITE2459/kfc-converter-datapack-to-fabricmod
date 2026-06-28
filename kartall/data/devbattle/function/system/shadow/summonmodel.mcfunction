summon marker ~ ~ ~ {Tags:["dev-model-summon-temp"]}
execute as @n[tag=dev-model-summon-temp] at @s run function kartmain:summon/summon-model with storage devattack:dev-attack-shadows temp-shadow-play
kill @e[tag=dev-model-summon-temp]

#모델 태그 달고 값수정
summon item_display ~ ~ ~ {Tags:["kartdatacarrier","shadow-models"]}

function kartmain:summon/manage-tag
execute store result score @n[tag=kartdatacarrier,tag=shadow-models,type=item_display] kart-engine run data get storage devattack:dev-attack-shadows temp-shadow-play.engine
execute as @n[tag=kartdatacarrier,tag=shadow-models,type=item_display] run function kartmain:summon/boost-effect/boost-effect

tag @e[distance=..0.01,type=#kartmobil:kartmodels] add shadow-models
tag @e[distance=..0.01,tag=shadow-models] remove kartmodel
tag @e[distance=..0.01,tag=shadow-models] remove kartentity
tag @e[distance=..0.01,tag=shadow-models] remove karttemp

kill @e[distance=..0.01,tag=shadow-models,tag=drift-effect]

#아머스탠드에 태우기
summon item_display ~ ~ ~ {teleport_duration:2,Tags:["shadow-models","shadow-main"]}

#쉐도우 게임 아이디 설정
scoreboard players operation @n[tag=shadow-models,tag=shadow-main,type=item_display] game-id = #max-id game-id

#개발자 모델
function devbattle:system/shadow/summon-dev-model

#탑승
execute as @e[tag=shadow-models,tag=!kart-shadow-player,tag=!kart-shadow-saddle] run ride @s dismount
execute as @e[tag=shadow-models,tag=!kart-shadow-player,tag=!kart-shadow-saddle] run ride @s mount @n[tag=shadow-models,tag=shadow-main,type=item_display]

#섀도우 엔진 설정
execute store result score @e[tag=shadow-models,tag=shadow-main,type=item_display,limit=1] kart-engine run data get storage devattack:dev-attack-shadows temp-shadow-play.engine

#섀도우 부스터 시간 설정
execute store result score @e[tag=shadow-models,tag=shadow-main,type=item_display,limit=1] kartboostduration run data get storage devattack:dev-attack-shadows temp-shadow-play.boostduration