#드리프트 시작
execute unless score @s kart-engine matches 1007 unless score @s kart-engine matches 1008 unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1006 if score @s kartmove matches 2780.. unless score #kartangle kartmain matches -250..250 run function kartmobil:move/steering/drift/drift-start
execute if score @s kart-engine matches 1007 if score @s kartmove matches 2780.. unless score #kartangle kartmain matches -150..150 run function kartmobil:move/steering/drift/drift-start

#F1 엔진 드리프트
execute if score @s kart-engine matches 1006 if score @s kartmove matches 2780.. run function kartmobil:move/steering/drift/f1-drift-start