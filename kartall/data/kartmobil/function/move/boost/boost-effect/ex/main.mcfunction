#ex-dual-boost-end-time kartboostduration
scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 1

scoreboard players operation #ex-boost-first-tick-minus-2 kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick-minus-2 kartboostduration 3

scoreboard players operation #ex-dual-boost-end-time kartboostduration = @s kartboostduration
scoreboard players remove #ex-dual-boost-end-time kartboostduration 20

#일반 부스터 파티클
execute if score @s kartboosttime < #ex-dual-boost-end-time kartboostduration unless score @s kartboosttime matches 32..40 on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 1.25 1 normal @a
particle dust{color:[1, 0, 0], scale:1} ~ ~ ~ .75 .75 .75 1 1

#듀얼부스터 이펙트
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/ex/dualboost-transform-particle
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/ex/dualboost-burst-particle
execute if score @s kartboosttime >= #ex-dual-boost-end-time kartboostduration run function kartmobil:move/boost/boost-effect/ex/dualboost-particle

#부스터 불꽃 및 블록 보이기
##sidite 분기
execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-block-fast
execute if score @s kartboosttime = #ex-boost-first-tick-minus-2 kartboostduration run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#마무리 모션
execute if score @s kartboosttime = #ex-dual-boost-end-time kartboostduration run function kartmobil:move/boost/boost-effect/force-cut-boost
execute if score @s kartboosttime = #ex-dual-boost-end-time kartboostduration on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/on-set-1
execute if score @s kartboosttime = #ex-dual-boost-end-time kartboostduration run playsound minecraft:block.fire.extinguish neutral @a[tag=kart-listener] ~ ~ ~ 1 1
execute if score @s kartboosttime = #ex-dual-boost-end-time kartboostduration on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 12 force @a[tag=kart-listener]

execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost