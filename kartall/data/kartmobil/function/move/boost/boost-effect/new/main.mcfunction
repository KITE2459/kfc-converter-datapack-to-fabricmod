#ex-dual-boost-end-time kartboostduration
scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 1

scoreboard players operation #ex-boost-first-tick-minus-2 kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick-minus-2 kartboostduration 2

#부스터 파티클
particle dust{color:[0f, 1f, 0f], scale:1} ~ ~ ~ .75 .75 .75 1 1

#부스터 소리
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/new/boost-sound

#부스터 블록과 불꽃 보이기
##sidite 분기
execute if score @s kartboosttime <= #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-block-fast
execute if score @s kartboosttime <= #ex-boost-first-tick-minus-2 kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost