#ex-dual-boost-end-time kartboostduration
scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 1

#부스터 파티클
particle dust{color:[1f, 0f, 0f], scale:1} ~ ~ ~ .75 .75 .75 1 1

#부스터 불꽃
##sidite 분기
execute if score @s kartboosttime <= #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost

playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.5 1 0.5