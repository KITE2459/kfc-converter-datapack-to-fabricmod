#ex-dual-boost-end-time kartboostduration
scoreboard players operation #ex-boost-first-tick kartboostduration = @s kartboostduration
scoreboard players remove #ex-boost-first-tick kartboostduration 1

#일반 부스터 파티클
execute if score @s kartboosttime matches 36.. unless score @s kartboosttime matches 32..40 on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 1.25 1 normal @a[tag=kart-listener]
particle dust{color:[0.45f, 0f, 1f], scale:1} ~ ~ ~ .75 .75 .75 1 1

execute if score @s kartboosttime = #ex-boost-first-tick kartboostduration run function kartmobil:move/boost/boost-effect/xun/boost-sound-start

#듀얼부스터 이펙트
execute if score @s kartboosttime matches 42 run function kartmobil:move/boost/boost-effect/xun/dualboost-transform-particle
execute if score @s kartboosttime matches 35 run function kartmobil:move/boost/boost-effect/xun/dualboost-burst-particle
execute if score @s kartboosttime matches ..35 run function kartmobil:move/boost/boost-effect/xun/dualboost-particle

#부스터 불꽃 및 블록 보이기
##sidite 분기
execute if score @s kartboosttime matches 42 run function kartmobil:move/boost/boost-effect/show-boost-model/show-block
execute if score @s kartboosttime matches 2..41 run function kartmobil:move/boost/boost-effect/show-boost-model/show-block-fast
execute if score @s kartboosttime matches 2..35 run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost