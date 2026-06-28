scoreboard players operation #gear-accel kartaccel = @s kartaccel
scoreboard players operation #gear-accel kartaccel += @s kartboost

#기어별 가속력 멀티플라이어
execute if score @s kartboostcount matches 1 store result storage minecraft:kartmain gear-accel int 1.35 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 2 store result storage minecraft:kartmain gear-accel int 1.22 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 3 store result storage minecraft:kartmain gear-accel int 1.1 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 4 store result storage minecraft:kartmain gear-accel int 0.90 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 5 store result storage minecraft:kartmain gear-accel int 0.75 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 6 store result storage minecraft:kartmain gear-accel int 0.66 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 7 store result storage minecraft:kartmain gear-accel int 0.60 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 8 store result storage minecraft:kartmain gear-accel int 0.55 run scoreboard players get #gear-accel kartaccel

execute store result score #gear-accel kartaccel run data get storage minecraft:kartmain gear-accel

#가속력. 음수여도 양수로 취급
execute if score #gear-accel kartaccel matches 0.. run scoreboard players operation @s kartmove += #gear-accel kartaccel
execute if score #gear-accel kartaccel matches ..-1 run scoreboard players operation @s kartmove -= #gear-accel kartaccel

#1단 속도 제한
execute if score @s kartboostcount matches 1 if score @s kartmove matches 30000.. run scoreboard players set @s kartmove 30000

# 드래프트 테스트
# function kartmain:draft/draft-f1-gear