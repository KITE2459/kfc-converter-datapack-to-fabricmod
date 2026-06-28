#가속력. 음수여도 양수로 취급
execute if score @s kartaccel matches 0.. run scoreboard players operation @s kartmove += @s kartaccel
execute if score @s kartaccel matches ..-1 run scoreboard players operation @s kartmove -= @s kartaccel

#마리오카트엔진 추가 가속
execute if score @s kart-engine matches 1003 run scoreboard players operation @s kartmove += @s kartboost
execute if score @s kart-engine matches 1008 run scoreboard players operation @s kartmove += @s kartboost
