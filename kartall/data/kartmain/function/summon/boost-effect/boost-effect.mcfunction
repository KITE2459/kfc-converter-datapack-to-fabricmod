#1.0엔진 - 변부 무시
execute if score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine matches 7 run function kartmain:summon/boost-effect/1.0-ignore-boost

#기본 부스터 블록
execute unless entity @e[tag=kart-boost-blocks,distance=..0.3,type=#kartmobil:kartmodels] run function kartmain:summon/boost-effect/boost-block

#기본 부스터 불꽃(바이크 고려)
execute unless entity @e[distance=..0.3,tag=kartmobil,tag=kartbike,type=#kartmobil:kartmobil] unless entity @e[tag=kart-boost-flame,distance=..0.3,type=#kartmobil:kartmodels] run function kartmain:summon/boost-effect/boost-effect-kart
execute if entity @e[distance=..0.3,tag=kartmobil,tag=kartbike,type=#kartmobil:kartmobil] unless entity @e[tag=kart-boost-flame,distance=..0.3,type=#kartmobil:kartmodels] run function kartmain:summon/boost-effect/boost-effect-bike

#기본 인터폴레이션 시간
function kartmain:summon/boost-effect/set-default-interpolation

#움직이는 부스터 트랜스폼(압축기 파이썬으로 미리 처리했다면 실행 x)
execute if entity @e[tag=kart-boost-move-end,distance=..0.3,type=#kartmobil:kartmodels] as @e[tag=kart-boost-move,tag=kart-boost-move-start,distance=..0.3,type=#kartmobil:kartmodels] run function kartmain:summon/boost-effect/boost-move/main
kill @e[tag=kart-boost-move-end,distance=..0.3,type=#kartmobil:kartmodels]

#뛰라이더(압축기 파이썬으로 미리 처리했다면 실행 x) 
execute if entity @e[tag=kart-run-anime-first,distance=..0.3,type=#kartmobil:kartmodels] as @e[tag=kart-run-anime,tag=kart-run-anime-mid,distance=..0.3,type=#kartmobil:kartmodels] run function kartmain:summon/boost-effect/run-anime/main
kill @e[tag=kart-run-anime,tag=!kart-run-anime-mid,distance=..0.3,type=#kartmobil:kartmodels]

#중앙에서 퍼져나오는 노즐
execute as @e[tag=kart-boost-move,tag=kart-boost-block,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s data.boost-transform-end set from entity @s transformation
execute as @e[tag=kart-boost-move,tag=kart-boost-block,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s transformation.scale set value [0f,0f,0f]
execute as @e[tag=kart-boost-move,tag=kart-boost-block,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s transformation.translation set value [0f,0f,0f]
execute as @e[tag=kart-boost-move,tag=kart-boost-block,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s data.boost-transform-start set from entity @s transformation

#불꽃 트랜스폼 저장
execute as @e[tag=kart-boost-flame,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s data.boost-transform-end set from entity @s transformation

#xun 부능효과 실드
execute if score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine matches 10 run function kartmain:summon/boost-effect/xun-charger-shield

#불꽃 시각적 초기화
execute as @e[tag=kart-boost-flame,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s transformation.scale[1] set value 0f
execute as @e[tag=kart-boost-flame,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s view_range set value 0f

#부스터 노즐 시각적 초기화
execute as @e[tag=kart-boost-block,distance=..0.3,type=#kartmobil:kartmodels] run data merge entity @s {left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],transformation:{translation:[0f,0f,0f],scale:[0f,0f,0f]}}

#바로 보이는 부스터 노즐 시각적 초기화 / 차고(unless)
execute unless score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine matches 8 as @e[tag=kart-boost-instant,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s view_range set value 0f
execute unless entity @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] as @e[tag=kart-boost-instant,distance=..0.3,type=#kartmobil:kartmodels] run data modify entity @s view_range set value 0f

#PRO / 마리오 엔진 - 언제나 변신
execute if score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine matches 8 run tag @e[tag=kartmobil,tag=karttemp,type=#kartmobil:kartmobil] add pro-transform-need
execute if score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine matches 1003 run tag @e[tag=kartmobil,tag=karttemp,type=#kartmobil:kartmobil] add pro-transform-need
execute if score @e[tag=kartmobil,tag=karttemp,distance=..1,limit=1,type=#kartmobil:kartmobil] kart-engine matches 1008 run tag @e[tag=kartmobil,tag=karttemp,type=#kartmobil:kartmobil] add pro-transform-need
