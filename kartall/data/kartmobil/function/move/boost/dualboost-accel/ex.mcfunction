scoreboard players operation #ex-dual-boost-end-time kartboostduration = @s kartboostduration
scoreboard players remove #ex-dual-boost-end-time kartboostduration 20

#리버스 듀얼부스터
execute if score @s kartboosttime >= #ex-dual-boost-end-time kartboostduration run scoreboard players add @s kartmove 150
