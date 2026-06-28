scoreboard players operation #gear-accel kartaccel = @s kartaccel
scoreboard players operation #gear-accel kartaccel += @s kartboost

#기어별 가속력 멀티플라이어
execute if score @s kartboostcount matches 1 store result storage minecraft:kartmain gear-accel int 3 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 2 store result storage minecraft:kartmain gear-accel int 2.45 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 3 store result storage minecraft:kartmain gear-accel int 2.02 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 4 store result storage minecraft:kartmain gear-accel int 1.45 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 5 store result storage minecraft:kartmain gear-accel int 0.81 run scoreboard players get #gear-accel kartaccel

#6단은 280에 부스터가 켜짐
execute if score @s kartboostcount matches 6 unless score #kartspeed kartmove matches 140.. store result storage minecraft:kartmain gear-accel int 0.635 run scoreboard players get #gear-accel kartaccel
execute if score @s kartboostcount matches 6 if score #kartspeed kartmove matches 140.. store result storage minecraft:kartmain gear-accel int 0.7 run scoreboard players get #gear-accel kartaccel

execute store result score #gear-accel kartaccel run data get storage minecraft:kartmain gear-accel

#가속력. 음수여도 양수로 취급
execute if score #gear-accel kartaccel matches 0.. run scoreboard players operation @s kartmove += #gear-accel kartaccel
execute if score #gear-accel kartaccel matches ..-1 run scoreboard players operation @s kartmove -= #gear-accel kartaccel
