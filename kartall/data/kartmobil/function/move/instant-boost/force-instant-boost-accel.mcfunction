scoreboard players add @s kartmove 155

#듀부가 있는 엔진은 순부가속 너프
execute if score @s kart-engine matches 0 run scoreboard players remove @s kartmove 50
execute if score @s kart-engine matches 1 run scoreboard players remove @s kartmove 50
execute if score @s kart-engine matches 5 run scoreboard players remove @s kartmove 50
execute if score @s kart-engine matches 7 run scoreboard players remove @s kartmove 50
execute if score @s kart-engine matches 10 run scoreboard players remove @s kartmove 40
execute if score @s kart-engine matches 1001 run scoreboard players remove @s kartmove 50
