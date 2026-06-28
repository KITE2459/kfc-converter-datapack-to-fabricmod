summon marker ~ ~ ~ {Tags:["check-point","check-large","check-temp"]}


scoreboard players reset check-summon-max check-num

execute as @e[tag=check-point,tag=!check-temp,distance=..200,type=marker] run scoreboard players operation check-summon-max check-num > @s check-num
execute as @e[tag=check-point,tag=!check-temp,distance=..200,type=marker] if score check-summon-max check-num = @s check-num run scoreboard players operation @e[tag=check-temp,limit=1,type=marker] check-num = @s check-num

scoreboard players add @e[tag=check-temp,limit=1,type=marker] check-num 1

tag @e[tag=check-temp,type=marker] remove check-temp