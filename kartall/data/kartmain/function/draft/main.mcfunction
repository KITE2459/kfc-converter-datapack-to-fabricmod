tag @s add kartself

#드래프트 쿨타임
execute if score @s kartmain matches 60.. run scoreboard players add @s kartmain 1
execute if score @s kartmain matches 200.. unless score @s kart-engine matches 1006 run scoreboard players reset @s kartmain

#드래프트 충전 / 강제로 끊김
execute if score @s kartaccel matches 0.. if entity @e[tag=kartmobil,type=#kartmobil:kartmobil,distance=1..30] run function kartmain:draft/charge-system
execute if entity @s[tag=kart-draft-charging] unless entity @e[tag=kartmobil,type=#kartmobil:kartmobil,distance=1..30] run function kartmain:draft/draft-end

#드래프트 발동 이후 가속
execute if score @s kartaccel matches 0.. if score @s kartmain matches 60.. run function kartmain:draft/draft-after

tag @s remove kartself
