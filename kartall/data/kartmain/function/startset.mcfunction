#아이디시스템(회수용)
scoreboard objectives add kartid dummy

#엔진 차별화용(플레이어)
scoreboard objectives add kart-engine dummy

#타이어 차별화용
scoreboard objectives add kart-tire dummy

#성능 차별화용
scoreboard objectives add kartspeed dummy
scoreboard objectives add kartaccel dummy
scoreboard objectives add kartboost dummy
scoreboard objectives add kartcorner dummy

scoreboard objectives add kart-low-gravity dummy

scoreboard objectives add kartdrift dummy
scoreboard objectives add f1-kartdrift-origin dummy
scoreboard objectives add kartboostgaugecharge dummy
scoreboard objectives add kartboostduration dummy
scoreboard objectives add kartmaxboostcount dummy
scoreboard objectives add kart-draft dummy
scoreboard objectives add kart-draft-time dummy

scoreboard objectives add kartdefense dummy
#scoreboard objectives add kart-weight dummy

#SR엔진용
scoreboard objectives add kart-transform-time-sr dummy

#카트 물리엔진 변수
scoreboard objectives add kartmain dummy
scoreboard objectives add kartmove dummy
scoreboard objectives add kartmove-remain dummy
scoreboard objectives add kartmovey dummy

scoreboard objectives add floor-distance dummy

scoreboard objectives add kartboosttime dummy
scoreboard objectives add kartboostcount dummy
scoreboard objectives add kartboostgauge dummy

scoreboard objectives add kartcollisiontime dummy
scoreboard objectives add kartweirdcollisioncount dummy
scoreboard objectives add kartboostpadtime dummy

#부스터키 연타 감지
scoreboard objectives add kart-nodelay-detect dummy
scoreboard objectives add kart-nodelay-timer dummy

#방향키 엔진
scoreboard objectives add kart-wasd-rotation-speed dummy

#드리프트한 시간 - 톡톡이 모드를 위함
scoreboard objectives add kartdrifttime dummy

#뛰라이더 애님
scoreboard objectives add kart-run-anime dummy
scoreboard objectives add kart-run-anime-state dummy

#rotafix
scoreboard objectives add rotafix dummy

#상수
scoreboard players set #kart-1 kartmain -1

scoreboard players set #kart2 kartmain 2
scoreboard players set #kart3 kartmain 3
scoreboard players set #kart4 kartmain 4
scoreboard players set #kart5 kartmain 5
scoreboard players set #kart6 kartmain 6
scoreboard players set #kart8 kartmain 8
scoreboard players set #kart10 kartmain 10
scoreboard players set #kart12 kartmain 12
scoreboard players set #kart15 kartmain 15

scoreboard players set #kart20 kartmain 20
scoreboard players set #kart26 kartmain 26

scoreboard players set #kart30 kartmain 30
scoreboard players set #kart40 kartmain 40
scoreboard players set #kart45 kartmain 45
scoreboard players set #kart50 kartmain 50
scoreboard players set #kart55 kartmain 55
scoreboard players set #kart64 kartmain 64
scoreboard players set #kart90 kartmain 90

scoreboard players set #kart100 kartmain 100
scoreboard players set #kart138 kartmain 138
scoreboard players set #kart139 kartmain 139
scoreboard players set #kart200 kartmain 200
scoreboard players set #kart278 kartmain 278
scoreboard players set #kart360 kartmain 360

scoreboard players set #kart500 kartmain 500
scoreboard players set #kart1000 kartmain 1000
scoreboard players set #kart2000 kartmain 2000

scoreboard players set #angle-before-filter kartcollisiontime 0

#충돌방어력 계수
execute unless score #kart-defense-mul kartcollisiontime matches 0.. run scoreboard players set #kart-defense-mul kartcollisiontime 400

#물리 모드
execute unless score #physics-mode kartcollisiontime matches 0.. run scoreboard players set #physics-mode kartcollisiontime 1

#히트박스 모드
execute unless score #kart-hitbox-mode kartcollisiontime matches 0.. run scoreboard players set #kart-hitbox-mode kartcollisiontime 1

#벽충돌 모드
execute unless score #wall-hitbox-mode kartcollisiontime matches 0.. run scoreboard players set #wall-hitbox-mode kartcollisiontime 1

#반발계수
execute unless score #100e-old kartcollisiontime matches 0.. run scoreboard players set #100e-old kartcollisiontime 54
execute unless score #100e kartcollisiontime matches 0.. run scoreboard players set #100e kartcollisiontime 59

#마찰계수
execute unless score #100f kartcollisiontime matches 0.. run scoreboard players set #100f kartcollisiontime 57

#라운드 히트박스
execute unless score #hitbox-round kartcollisiontime matches 0.. run scoreboard players set #hitbox-round kartcollisiontime 14

#감속알림
bossbar add deceleration {translate:"감속 발생","color":"red","bold":true}
bossbar set minecraft:deceleration color red
bossbar set minecraft:deceleration value 100

#보트 엔진
function kartmobil:boat-engine/startset

#원점을 사용하기 위한 강제로드
execute positioned 0 0 0 run forceload add ~ ~
execute positioned 0 0 -1 run forceload add ~ ~
execute positioned -1 0 0 run forceload add ~ ~
execute positioned -1 0 -1 run forceload add ~ ~

#히트박스 차별화
scoreboard objectives add kart-hitbox-left dummy
scoreboard objectives add kart-hitbox-right dummy
scoreboard objectives add kart-hitbox-front dummy
scoreboard objectives add kart-hitbox-rear dummy

scoreboard objectives add kart-hitbox-min dummy
scoreboard objectives add kart-hitbox-radius dummy
scoreboard objectives add kart-hitbox-radius-projection dummy

    kill @e[tag=kart-vertex,type=area_effect_cloud]
    kill @e[tag=kart-vertex-additional,type=area_effect_cloud]
    execute positioned 0 0 0 run function kartmain:collision/rectangle-hitbox/summon-vertex

# F1 엔진 관련
scoreboard objectives add kart-ers dummy
scoreboard objectives add kart-ers-gauge dummy
scoreboard objectives add kart-ers-prev-speed dummy

# 카트 성능제한 트리거
scoreboard objectives add kart-performance-limit trigger
scoreboard objectives add kart-performance-limit-level dummy

# DS 엔진 관련
scoreboard objectives add ds-steering-time dummy
scoreboard objectives add ds-steering-type dummy
scoreboard objectives add ds-steering-prev-type dummy