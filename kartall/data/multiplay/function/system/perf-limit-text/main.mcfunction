#ex-dual-boost-end-time kartboostduration
scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 1

#부스터 파티클
particle dust{color:[1f, 0.5f, 0.5f], scale:1} ~ ~ ~ .75 .75 .75 1 1

#부스터 소리
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/jiu/boost-sound
#부스터 불꽃
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost