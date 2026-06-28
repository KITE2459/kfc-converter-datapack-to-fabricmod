#부스터 발판 타이머
execute if score @s kartboostpadtime matches 1.. run scoreboard players remove @s kartboostpadtime 1

#랠리, 기어 컷
execute if score @s kart-engine matches 1007 run return run function kartmobil:move/boost/boost-effect/dummy/rally/auto-transform
execute if score @s kart-engine matches 1005 run return run function kartmobil:move/boost/boost-effect/dummy/gear/auto-transform
execute if score @s kart-engine matches 1006 run return run function kartmobil:move/boost/boost-effect/dummy/f1/auto-transform

    #부스터 사용 여부 전달용 이펙트
    #execute if score @s kartboosttime matches 1.. run effect give @a[tag=kart-listener] minecraft:dolphins_grace 3 169 true
    #execute unless score @s[tag=!kart-exceed-active] kartboosttime matches 1.. run effect clear @a[tag=kart-listener] minecraft:dolphins_grace

    #s2c 대응 과도기 버전
    #execute unless score @s[tag=!kart-exceed-active] kartboosttime matches 1.. on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/off-set-0

#출부
execute if score @s startboosttime matches 1.. run function kartmobil:move/boost/startboost/main

#부스터 타이머
scoreboard players remove @p[tag=kartpassenger,scores={kartboosttime=1..}] kartboosttime 1

#EX - 부스터 바로 사용
execute if score @s kart-engine matches 1 if entity @s[tag=kart-boost-press] if score @s kartboostcount matches 1.. run scoreboard players set @s kartboosttime 0
execute if score @s kart-engine matches 1 if entity @s[tag=kart-boost-press] if score @s kartboostcount matches 1.. run function kartmobil:move/boost/boost-effect/force-cut-boost

#부스터 사용 / 순부칠때 부스터 안나가게
execute if entity @s[tag=kart-w-press] on passengers if score @s[tag=kartdirection] kartdrifttime matches 1.. run return 1
execute if score @s[tag=kart-w-pressed,tag=kart-boost-pressed,tag=!kart-cant-use-boost-in-air] kartboosttime matches ..0 if score @s kartmove matches 1000.. if score @s kartboostcount matches 1.. rotated as @p[tag=kartpassenger,scores={kartboosttime=..0}] run function kartmobil:move/boost/useboost

#키엔진 부스터 자동 사용
execute if score @s[tag=kart-w-pressed,tag=!kart-cant-use-boost-in-air] kart-engine matches 1002 if score @s kartboosttime matches ..0 if score @s kartmove matches 800.. if score @s kartboostcount matches 1.. rotated as @p[tag=kartpassenger,scores={kartboosttime=..0}] run function kartmobil:move/boost/useboost

    #부스터 가속
    execute if score @s kartboosttime matches 1.. unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1008 run scoreboard players operation @s kartmove += @s kartboost

    #MK 부스터 스피드 증가
    execute if score @s kartboosttime matches 1.. if score @s kart-engine matches 1003 run scoreboard players add @p[tag=kartpassenger] kartspeed 84

    # DS 부스터 스피드 증가
    execute if score @s kartboosttime matches 1.. if score @s kart-engine matches 1008 run scoreboard players add @p[tag=kartpassenger] kartspeed 75

    #듀얼부스터
    execute if score @s kart-engine matches 0 run function kartmobil:move/boost/dualboost-accel/x
    execute if score @s kart-engine matches 1 run function kartmobil:move/boost/dualboost-accel/ex
    execute if score @s kart-engine matches 5 run function kartmobil:move/boost/dualboost-accel/v1
    execute if score @s kart-engine matches 7 run function kartmobil:move/boost/dualboost-accel/1.0
    execute if score @s kart-engine matches 10 run function kartmobil:move/boost/dualboost-accel/xun

    execute if score @s kart-engine matches 1001 run function kartmobil:move/boost/dualboost-accel/x
    execute if score @s kart-engine matches 9 run function kartmobil:move/boost/dualboost-accel/krp
    
    #퓨전부스터
    execute if entity @s[tag=kart-fusion-boost-use] run scoreboard players add @s kartmove 25

#부스터 이펙트
function kartmobil:move/boost/boost-effect/boost-main