scoreboard players remove @s[tag=!shadow-main] kartboosttime 1

#황구 소리
execute if score @s kartboosttime matches 48 if entity @s[tag=dog,distance=..0.3] at @p[tag=kartpassenger] run playsound minecraft:entity.wolf.ambient neutral @a[tag=kart-listener]
execute if score @s kartboosttime matches 42 if entity @s[tag=dog,distance=..0.3] at @p[tag=kartpassenger] run playsound minecraft:entity.wolf.ambient neutral @a[tag=kart-listener]

#부스터 패드 탈 때
execute if score @s kartboostpadtime matches 1.. on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:soul_fire_flame ^ ^1 ^1 .75 .75 .75 0 3 force @a[tag=kart-listener]

#엔진별 부스터 차이
execute if score @s kart-engine matches 0 run function kartmobil:move/boost/boost-effect/x/main
execute if score @s kart-engine matches 1 run function kartmobil:move/boost/boost-effect/ex/main
execute if score @s kart-engine matches 2 run function kartmobil:move/boost/boost-effect/jiu/main
execute if score @s kart-engine matches 3 run function kartmobil:move/boost/boost-effect/new/main
execute if score @s kart-engine matches 4 run function kartmobil:move/boost/boost-effect/z7/main
execute if score @s kart-engine matches 5 run function kartmobil:move/boost/boost-effect/v1/main
execute if score @s kart-engine matches 6 run function kartmobil:move/boost/boost-effect/a2/main
execute if score @s kart-engine matches 7 run function kartmobil:move/boost/boost-effect/1.0/main
execute if score @s kart-engine matches 8 run function kartmobil:move/boost/boost-effect/pro/main
execute if score @s kart-engine matches 9 run function kartmobil:move/boost/boost-effect/krp/main
execute if score @s kart-engine matches 10 run function kartmobil:move/boost/boost-effect/xun/main
execute if score @s kart-engine matches 11 run function kartmobil:move/boost/boost-effect/sr/main

execute if score @s kart-engine matches 1000 run function kartmobil:move/boost/boost-effect/dummy/n1/main
execute if score @s kart-engine matches 1001 run function kartmobil:move/boost/boost-effect/x/main
execute if score @s kart-engine matches 1002 run function kartmobil:move/boost/boost-effect/dummy/key/main
execute if score @s kart-engine matches 1003 run function kartmobil:move/boost/boost-effect/dummy/mario/main
execute if score @s kart-engine matches 1008 run function kartmobil:move/boost/boost-effect/dummy/mario/main


#부스터 끊기
execute unless score @s kart-engine matches 7 unless score @s kart-engine matches 1005..1006 unless score @s kart-engine matches 1007 if entity @p[tag=kartpassenger,predicate=!kartmobil:w] run scoreboard players set @s kartboosttime 0
execute unless score @s kart-engine matches 7 unless score @s kart-engine matches 1005..1006 unless score @s kart-engine matches 1007 if entity @p[tag=kartpassenger,predicate=!kartmobil:w] run function kartmobil:move/boost/boost-effect/force-cut-boost
