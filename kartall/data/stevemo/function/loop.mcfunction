scoreboard players operation #loop1 kartstevecut *= #-1 kartstevecut
execute if score #loop1 kartstevecut matches 1 run tellraw @s "루프 ON!"
execute if score #loop1 kartstevecut matches -1 run tellraw @s "루프 OFF!"