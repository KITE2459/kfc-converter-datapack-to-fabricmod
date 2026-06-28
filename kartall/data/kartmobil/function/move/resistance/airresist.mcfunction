scoreboard players operation #kartairresist kartmove = @s kartmove

#카트 스피드 스펙에 따른 공기저항 + 오프셋
scoreboard players operation #kartairresistconst kartmove += @s kartspeed
scoreboard players operation #kartairresistconst kartmove += @p[tag=kartpassenger] kartspeed
scoreboard players set @p[tag=kartpassenger] kartspeed 0

#공기저항공식 - 속도의 제곱에 비례
scoreboard players operation #kartairresist kartmove *= #kart4 kartmain
scoreboard players operation #kartairresist kartmove /= #kartairresistconst kartmove

scoreboard players operation #kartairresist kartmove *= #kartairresist kartmove

scoreboard players operation #kartairresist kartmove *= #kart4 kartmain
scoreboard players operation #kartairresist kartmove /= #kartairresistconst kartmove

execute if score @s kartmove matches 1.. run scoreboard players operation @s kartmove -= #kartairresist kartmove
execute if score @s kartmove matches ..-1 run scoreboard players operation @s kartmove += #kartairresist kartmove