
#일반 부스터 파티클
execute if score @s kartboosttime matches 26.. unless score @s kartboosttime matches 32..40 on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 1.25 1

#듀얼부스터 이펙트
execute if score @s kartboosttime matches 37 run function kartmobil:move/boost/boost-effect/1.0/dualboost-transform-particle
execute if score @s kartboosttime matches 25 run function kartmobil:move/boost/boost-effect/1.0/dualboost-burst-particle
execute if score @s kartboosttime matches ..25 run function kartmobil:move/boost/boost-effect/1.0/dualboost-particle

#부스터 불꽃 및 블록 보이기
execute if score @s kartboosttime matches 37 run function kartmobil:move/boost/boost-effect/show-boost-model/show-block
##sidite 분기
execute if score @s kartboosttime matches 2..36 run function kartmobil:move/boost/boost-effect/show-boost-model/show-block-fast
execute if score @s kartboosttime matches 2..25 run function kartmobil:move/boost/boost-effect/show-boost-model/show-flame

#마무리 모션
execute if score @s kartboosttime matches 0 run function kartmobil:move/boost/boost-effect/force-cut-boost
execute if score @s kartboosttime matches 0 run scoreboard players set @p[tag=kartpassenger] kartboosttime 4