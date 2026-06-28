xp set @s 0 points
xp set @s 40 levels

scoreboard players operation #exp-gauge-temp kartmain = #exp-gauge kartmain

execute if score #exp-gauge-temp kartmain matches 128.. run xp add @s 128 points
execute if score #exp-gauge-temp kartmain matches 128.. run scoreboard players remove #exp-gauge-temp kartmain 128

execute if score #exp-gauge-temp kartmain matches 64.. run xp add @s 64 points
execute if score #exp-gauge-temp kartmain matches 64.. run scoreboard players remove #exp-gauge-temp kartmain 64

execute if score #exp-gauge-temp kartmain matches 32.. run xp add @s 32 points
execute if score #exp-gauge-temp kartmain matches 32.. run scoreboard players remove #exp-gauge-temp kartmain 32

execute if score #exp-gauge-temp kartmain matches 16.. run xp add @s 16 points
execute if score #exp-gauge-temp kartmain matches 16.. run scoreboard players remove #exp-gauge-temp kartmain 16

execute if score #exp-gauge-temp kartmain matches 8.. run xp add @s 8 points
execute if score #exp-gauge-temp kartmain matches 8.. run scoreboard players remove #exp-gauge-temp kartmain 8

execute if score #exp-gauge-temp kartmain matches 4.. run xp add @s 4 points
execute if score #exp-gauge-temp kartmain matches 4.. run scoreboard players remove #exp-gauge-temp kartmain 4

execute if score #exp-gauge-temp kartmain matches 2.. run xp add @s 2 points
execute if score #exp-gauge-temp kartmain matches 2.. run scoreboard players remove #exp-gauge-temp kartmain 2

execute if score #exp-gauge-temp kartmain matches 1.. run xp add @s 1 points
execute if score #exp-gauge-temp kartmain matches 1.. run scoreboard players remove #exp-gauge-temp kartmain 1