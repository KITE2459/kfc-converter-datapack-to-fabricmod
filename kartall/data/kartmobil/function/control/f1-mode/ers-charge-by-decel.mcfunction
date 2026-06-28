scoreboard players operation #ers-charge kart-ers-gauge = #ers-decel kart-ers-gauge
scoreboard players operation #ers-charge kart-ers-gauge *= #kartspeed kartmove
scoreboard players operation #ers-charge kart-ers-gauge /= #kart100 kartmain

#브레이크(S) 입력 중이면 ERS 회수량 추가 보정
execute if entity @p[tag=kartpassenger,predicate=kartmobil:s] run scoreboard players operation #ers-charge kart-ers-gauge *= #kart3 kartmain

scoreboard players operation @s kart-ers-gauge += #ers-charge kart-ers-gauge
