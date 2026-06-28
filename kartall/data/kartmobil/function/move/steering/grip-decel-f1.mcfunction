# 그립주행 감속 - F1
# 코너링 수치 보정, 범위 40~175
execute store result storage temp temp int 0.01 run scoreboard players get @s kartcorner
execute if score @s kartmaxboostcount matches 3.. store result storage temp temp int 0.02 run scoreboard players get @s kartcorner
execute store result score #temp kartcorner run data get storage temp temp

# 조향각 부호 제거
execute store result score #temp-angle kartmain run scoreboard players get #kartangle kartmain
execute if score #temp-angle kartmain matches ..0 store result storage temp temp int -1 run scoreboard players get #temp-angle kartmain
execute if score #temp-angle kartmain matches ..0 store result score #temp-angle kartmain run data get storage temp temp

execute if score #temp-angle kartmain matches 10..149 run scoreboard players operation @s kartmove -= #temp kartcorner
execute if score #temp-angle kartmain matches 20..149 run scoreboard players operation @s kartmove -= #temp kartcorner
execute if score #temp-angle kartmain matches 30..149 run scoreboard players operation @s kartmove -= #temp kartcorner
execute if score #temp-angle kartmain matches 40..149 run scoreboard players operation @s kartmove -= #temp kartcorner
execute if score #temp-angle kartmain matches 50..149 run scoreboard players operation @s kartmove -= #temp kartcorner
execute if score #temp-angle kartmain matches 60..149 run scoreboard players operation @s kartmove -= #temp kartcorner
execute if score #temp-angle kartmain matches 70..149 run scoreboard players operation @s kartmove -= #temp kartcorner
execute if score #temp-angle kartmain matches 80..149 run scoreboard players operation @s kartmove -= #temp kartcorner

execute unless score #temp-angle kartmain matches 150.. run return 0
scoreboard players remove #temp-angle kartmain 150
execute store result storage temp temp int 0.1 run scoreboard players get #temp-angle kartmain
execute if score @s kartmaxboostcount matches 3.. store result storage temp temp int 0.25 run scoreboard players get #temp-angle kartmain
execute store result score #temp-angle kartmain run data get storage temp temp
scoreboard players operation #temp kartcorner *= #temp-angle kartmain
scoreboard players operation @s kartmove -= #temp kartcorner