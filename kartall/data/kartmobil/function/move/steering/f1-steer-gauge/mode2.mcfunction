
#100 (a / b)
scoreboard players operation #kartangle kartboostcount = #kartangleabs kartmain
scoreboard players operation #kartangle kartboostcount *= #kart100 kartmain
scoreboard players operation #kartangle kartboostcount /= #steeranglelimit kartboostcount

scoreboard players operation #kartangle kartboostcount *= #kart2 kartmain
#kartangle kartmain
#게이지
execute store result storage minecraft:kartmain steergauge int 1 run scoreboard players get #kartangle kartboostcount
execute store result score #exp-gauge kartmain run data get storage minecraft:kartmain steergauge
execute as @a[tag=kart-listener] run function kartmobil:move/exp-gauge