#ex-dual-boost-end-time kartboostduration
scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 1


#부스터 파티클
particle dust{color:[0.4862f, 1f, 0.4862f], scale:1} ~ ~ ~ .75 .75 .75 1 1

#부스터 소리
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/krp/boost-sound

#듀얼부스터 이펙트
execute if score @s kartboosttime matches 31 run function kartmobil:move/boost/boost-effect/krp/dualboost-transform-particle
execute if score @s kartboosttime matches 20 run function kartmobil:move/boost/boost-effect/krp/dualboost-burst-particle
execute if score @s kartboosttime matches ..20 run function kartmobil:move/boost/boost-effect/x/dualboost-particle

#부스터 불꽃 및 블록 보이기
##sidite 분기
execute if score @s kartboosttime matches 31 run function kartmobil:move/boost/boost-effect/show-boost-model/show-block
execute if score @s kartboosttime matches 2..30 run function kartmobil:move/boost/boost-effect/show-boost-model/show-block-fast

##sidite 분기
execute if score @s kartboosttime matches ..20 run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#퓨전부스터
execute if entity @s[tag=kart-fusion-boost-use] run particle minecraft:happy_villager ~ ~1 ~ .5 .5 .5 10 2

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost