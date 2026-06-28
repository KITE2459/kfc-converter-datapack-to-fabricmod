#1. 법선 방향과 상대속도 차이인 vt의 각도를 구함 = #angle
#2. 필터에 통과
#3. 부호를 복구시킨 뒤, 상대속도에서 vt만큼 회전한 것을 다시 법선 방향으로 설정

execute store result score #angle kartcollisiontime run data get entity @e[tag=kart-normal-line,limit=1,type=marker] Rotation[0]
    #get angle between a and normal
    scoreboard players operation #angle kartcollisiontime -= #vector-relative kartcollisiontime
    execute if score #angle kartcollisiontime matches 180.. run scoreboard players remove #angle kartcollisiontime 360
    execute if score #angle kartcollisiontime matches ..-180 run scoreboard players add #angle kartcollisiontime 360
    
    scoreboard players set #angle-sign kartcollisiontime 1
    execute if score #angle kartcollisiontime matches ..-1 run scoreboard players set #angle-sign kartcollisiontime -1
    execute if score #angle kartcollisiontime matches ..-1 run scoreboard players operation #angle kartcollisiontime *= #kart-1 kartmain
    scoreboard players operation #angle-before-filter kartcollisiontime = #angle kartcollisiontime

function kartmain:collision/swapspeed/hitbox-filter/filter

execute store result storage kartmain kartrerotatenormal int 1 run scoreboard players operation #angle kartcollisiontime *= #angle-sign kartcollisiontime

function kartmain:collision/swapspeed/hitbox-filter/re-rotate-normal with storage kartmain