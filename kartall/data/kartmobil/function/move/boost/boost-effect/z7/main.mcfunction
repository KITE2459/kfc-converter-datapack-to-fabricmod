#ex-dual-boost-end-time kartboostduration
scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 1

scoreboard players operation #ex-boost-first-tick-minus-20 kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick-minus-20 kartboostduration 20

scoreboard players operation #ex-boost-first-tick-minus-26 kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick-minus-26 kartboostduration 26

#부스터 파티클
particle dust{color:[1f, 0.5f, 0f], scale:1} ~ ~ ~ .75 .75 .75 1 1 normal @a

#부스터 소리
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/z7/boost-sound

#부스터 블록
##sidite 분기
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration unless score @s kartboosttime matches ..2 run function kartmobil:move/boost/boost-effect/show-boost-model/show-block
execute if score @s kartboosttime < #ex-boost-first-tick kartboostduration unless score @s kartboosttime matches ..2 run function kartmobil:move/boost/boost-effect/show-boost-model/show-block-fast

#변신 전조
execute if score @s kartboosttime = #ex-boost-first-tick-minus-20 kartboostduration unless score @s kartboosttime matches ..2 run function kartmobil:move/boost/boost-effect/z7/boost-sound-2

#부스터 불꽃
execute if score @s kartboosttime = #ex-boost-first-tick-minus-26 kartboostduration unless score @s kartboosttime matches ..2 run function kartmobil:move/boost/boost-effect/z7/dualboost-particle
##sidite 분기
execute if score @s kartboosttime <= #ex-boost-first-tick-minus-26 kartboostduration unless score @s kartboosttime matches ..2 run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost