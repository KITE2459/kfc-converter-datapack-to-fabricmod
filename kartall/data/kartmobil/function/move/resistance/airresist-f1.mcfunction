scoreboard players operation #kartairresist kartmove = @s kartmove

#공기저항(카트 스피드 스펙을 고려)
scoreboard players operation #kartairresistconst kartmove += @s kartspeed

#기어별 가속력 멀티플라이어
execute if score @s kartboostcount matches 1 store result storage minecraft:kartmain gear-airresist int 0.725 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 2 store result storage minecraft:kartmain gear-airresist int 0.843 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 3 store result storage minecraft:kartmain gear-airresist int 0.964 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 4 store result storage minecraft:kartmain gear-airresist int 1.12 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 5 store result storage minecraft:kartmain gear-airresist int 1.285 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 6 store result storage minecraft:kartmain gear-airresist int 1.44 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 7 store result storage minecraft:kartmain gear-airresist int 1.582 run scoreboard players get #kartairresistconst kartmove
execute if score @s kartboostcount matches 8 store result storage minecraft:kartmain gear-airresist int 1.725 run scoreboard players get #kartairresistconst kartmove

execute store result score #kartairresistconst kartmove run data get storage minecraft:kartmain gear-airresist

# ers 활성화 시 공기저항 저하
# K:				340/400/465/530/595/667/739/818
# 속도:				130/160/190/220/250/280/310/340
# 목표 속도 증가율:	 00%/16%/20%/16%/12%/08%/04%/04%
# 목표 속도:		130/185/228/255/280/302/323/350
# K값이 1% 증가할 때, 속도 증가율은 대략 1.7% 증가

#공기저항공식 - 속도의 제곱에 비례
scoreboard players operation #kartairresist kartmove *= #kart4 kartmain
scoreboard players operation #kartairresist kartmove /= #kartairresistconst kartmove

scoreboard players operation #kartairresist kartmove *= #kartairresist kartmove

scoreboard players operation #kartairresist kartmove *= #kart4 kartmain
scoreboard players operation #kartairresist kartmove /= #kartairresistconst kartmove

execute if score @s kartmove matches 1.. run scoreboard players operation @s kartmove -= #kartairresist kartmove
execute if score @s kartmove matches ..-1 run scoreboard players operation @s kartmove += #kartairresist kartmove