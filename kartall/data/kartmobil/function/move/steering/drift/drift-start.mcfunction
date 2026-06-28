tag @s add kart-drifting

scoreboard players operation @s kart-teamboost-gauge = @s kartboostgauge

# F1 엔진 드리프트 게이지 초기화
execute if score @s kart-engine matches 1006 store result storage temp temp int 0.1 run scoreboard players get @s kartdrift
execute if score @s kart-engine matches 1006 store result score @s kartdrift run data get storage temp temp


function kartmobil:move/steering/drift/control-drift-effect/show
execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-drift/start-1