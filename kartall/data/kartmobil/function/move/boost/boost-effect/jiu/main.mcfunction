#ex-dual-boost-end-time kartboostduration
scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 1

scoreboard players operation #ex-boost-first-tick-minus-20 kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick-minus-20 kartboostduration 20

#부스터 파티클
particle dust{color:[0, 1, 1], scale:1} ~ ~ ~ .75 .75 .75 1 1

#부스터 소리
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/jiu/boost-sound

#부스터 불꽃 보이기
##sidite 분기
execute if score @s kartboosttime <= #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#서서히 줄어드는 불
execute if score @s kartboosttime matches 1..11 on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kart-boost-flame,tag=kart-boost-flame-show,tag=!kart-boost-flame-slow-fade,type=#kartmobil:kartmodels] run function kartmobil:move/boost/boost-effect/jiu/dualboost-slow-fade
execute if score @s[tag=shadow-main] kartboosttime matches 1..11 on passengers if entity @s[tag=kart-boost-flame,tag=kart-boost-flame-show,tag=!kart-boost-flame-slow-fade,type=#kartmobil:kartmodels] run function kartmobil:move/boost/boost-effect/jiu/dualboost-slow-fade

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/jiu/boost-end