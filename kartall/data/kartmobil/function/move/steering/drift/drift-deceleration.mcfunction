execute if score #kartangle kartmain matches -100..100 run return 0

scoreboard players operation #kartdecel kartmain = #kartangleabs kartmain

execute if score @s kart-engine matches 7 run scoreboard players set #kartdecel kartmain 0
execute if score @s kart-engine matches 1006 run scoreboard players set #kartdecel kartmain 0
execute unless score @s kart-engine matches 7 unless score @s kart-engine matches 1006 run scoreboard players operation #kartdecel kartmain /= #kart6 kartmain

#V턴
execute if entity @s[tag=kart-hairpin-unlimit] unless score @s kart-engine matches 7 on passengers if entity @s[tag=kartdirectiontemp,x_rotation=70..,type=item_display] on vehicle run function kartmobil:move/steering/drift/v-turn

#마리오카트 엔진은 감속이 없음
execute unless score @s[tag=kart-drifting] kart-engine matches 1003 unless score @s[tag=kart-drifting] kart-engine matches 1008 if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartmove -= @s kartcorner

execute unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1008 if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartmove -= #kartangleabs kartmain
execute unless score @s kart-engine matches 1003 unless score @s kart-engine matches 1008 if score #kartspeed kartmove matches 25.. run scoreboard players operation @s kartmove += #kartdecel kartmain