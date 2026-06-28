#1. 현재 부가속을 2로 나누고 그것을 가속에 더한다
#2. 가속에 100을 추가로 더한다

scoreboard players operation @s kartboost /= #kart2 kartmain
scoreboard players operation @s kartaccel += @s kartboost

scoreboard players add @s kartaccel 120
scoreboard players remove @s kartboost 80

scoreboard players remove @s kartboostgaugecharge 50

scoreboard players add @s kartcorner 25

tag @s remove force-instant-boost

execute as @n[tag=kartsaddle] run function kartmobil:s2c-value/skill-instant-boost/true-1