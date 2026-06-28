#변신 해제 카운트
execute unless score @s kartboosttime matches 1.. if score @s kart-transform-time-sr matches 1.. run scoreboard players remove @s kart-transform-time-sr 1

#지우엔진 자동변신
execute if score @s kart-engine matches 2 run function kartmobil:move/boost/boost-effect/jiu/auto-transform
execute if score @s kart-engine matches 1000 run function kartmobil:move/boost/boost-effect/jiu/auto-transform
execute if score @s kart-engine matches 1002 run function kartmobil:move/boost/boost-effect/jiu/auto-transform

#RUSH+ 자동충전
execute if score @s kart-engine matches 9 if score #kartspeed kartmove matches 50.. unless score @s kartdrifttime matches 1.. run scoreboard players add @s kartboostgauge 18

#부스터 허브
execute if score @s kartboosttime matches 1.. run function kartmobil:move/boost/boost-effect/boost-hub