#ex-dual-boost-end-time kartboostduration

scoreboard players operation #ex-boost-first-tick2 kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick2 kartboostduration 1

scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 4

scoreboard players operation #ex-boost-sec-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-sec-tick kartboostduration 10

scoreboard players operation #ex-boost-first-tick-minus-2 kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick-minus-2 kartboostduration 14

scoreboard players operation #ex-boost-sec-tick2 kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-sec-tick2 kartboostduration 18

#부스터 파티클
particle dust{color:[1f, 0f, 1f], scale:1} ~ ~ ~ .75 .75 .75 1 1

#SR
execute unless score @s kart-transform-time-sr matches 15.. run scoreboard players add @s kart-transform-time-sr 1

execute if score @s kart-transform-time-sr matches 0..12 run scoreboard players operation #half kartboost = @s kartboost
execute if score @s kart-transform-time-sr matches 0..12 run scoreboard players operation #half kartboost /= #kart3 kartmain
execute if score @s kart-transform-time-sr matches 0..12 run scoreboard players operation @s kartmove -= @s kartboost
execute if score @s kart-transform-time-sr matches 0..12 run scoreboard players operation @s kartmove += #half kartboost

execute if score @s kart-transform-time-sr matches 14 on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/ready-set-2

#부스터 소리
execute if score @s kartboosttime = #ex-boost-first-tick2 kartboostduration run function kartmobil:move/boost/boost-effect/sr/boost-sound
execute if score @s kartboosttime = #ex-boost-sec-tick kartboostduration run function kartmobil:move/boost/boost-effect/sr/boost-sound-2
execute if score @s kartboosttime = #ex-boost-sec-tick2 kartboostduration run function kartmobil:move/boost/boost-effect/sr/boost-sound-3

#부스터 블록과 불꽃 보이기
##sidite 분기
execute if score @s kartboosttime <= #ex-boost-first-tick-minus-2 kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-block-fast

execute if score @s kartboosttime <= #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-block

execute if score @s kartboosttime <= #ex-boost-first-tick-minus-2 kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost