scoreboard players operation #kartairresist kartmove = @s kartmove

#공기저항(카트 스피드 스펙을 고려)
scoreboard players operation #kartairresistconst kartmove += @s kartspeed


#기어별 가속력 멀티플라이어
execute if score @s kartboostcount matches 1 store result storage minecraft:kartmain gear-airresist int 0.63 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 2 store result storage minecraft:kartmain gear-airresist int 0.862 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 3 store result storage minecraft:kartmain gear-airresist int 1.062 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 4 store result storage minecraft:kartmain gear-airresist int 1.25 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 5 store result storage minecraft:kartmain gear-airresist int 1.438 run scoreboard players get #kartairresistconst kartmove
# execute if score @s kartboostcount matches 6 store result storage minecraft:kartmain gear-airresist int 1.6025 run scoreboard players get #kartairresistconst kartmove

execute store result score #kartairresistconst kartmove run data get storage minecraft:kartmain gear-airresist

#공기저항공식 - 속도의 제곱에 비례
scoreboard players operation #kartairresist kartmove *= #kart4 kartmain
scoreboard players operation #kartairresist kartmove /= #kartairresistconst kartmove

scoreboard players operation #kartairresist kartmove *= #kartairresist kartmove

scoreboard players operation #kartairresist kartmove *= #kart4 kartmain
scoreboard players operation #kartairresist kartmove /= #kartairresistconst kartmove

execute if score @s kartmove matches 1.. run scoreboard players operation @s kartmove -= #kartairresist kartmove
execute if score @s kartmove matches ..-1 run scoreboard players operation @s kartmove += #kartairresist kartmove
