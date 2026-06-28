

#그립감속
execute if score @s[tag=!kart-drifting] kart-engine matches 1006 run function kartmobil:move/steering/grip-decel-f1
execute if score @s[tag=!kart-drifting] kart-engine matches 1005 run function kartmobil:move/steering/grip-decel-gear

#키보드 엔진들
execute if score @s kart-engine matches 1002 run function kartmobil:move/steering/wasd-control/main
execute if score @s kart-engine matches 1003 run function kartmobil:move/steering/wasd-control/mariokart
execute if score @s kart-engine matches 1008 run function kartmobil:move/steering/wasd-control/ds
#드리프트 시작-중간-끝

tag @s[tag=kart-direction-fixed-by-pad] remove kart-drifting
execute if entity @s[tag=!kart-drifting,tag=!kart-direction-fixed-by-pad] run function kartmobil:move/steering/drift/drift-start-condition
execute if entity @s[tag=kart-drifting,tag=!kart-direction-fixed-by-pad] run function kartmobil:move/steering/drift/drift-while
execute if entity @s[tag=kart-drifting,tag=!kart-direction-fixed-by-pad] run function kartmobil:move/steering/drift/drift-end-condition
execute unless score @s[tag=!kart-drifting] kart-engine matches 1006..1007 unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1008 if score @s kartboostgauge matches 2000.. run function kartmobil:move/steering/drift/getboost

#모델링 회전
execute if score @s kart-engine matches 7 if score @s[tag=!kart-stop] kartmove matches 1.. run function kartmobil:move/steering/modeldir-calculate/1.0-modeldir
execute unless score @s kart-engine matches 7 if score @s[tag=!kart-stop] kartmove matches 1.. run function kartmobil:move/steering/modeldir-calculate/modeldir

#F1 스티어 게이지
execute if score @s kart-engine matches 1006 run function kartmobil:move/steering/f1-steer-gauge/main