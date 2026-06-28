execute if score @s kartmove matches ..1000 run scoreboard players reset @s[tag=kartdirection,scores={kartdrifttime=1..}] kartdrifttime

    execute on passengers if score @s[tag=kartdirection] kartdrifttime matches 20 on vehicle on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-instant-boost/refresh-set-2
    execute on passengers if score @s[tag=kartdirection] kartdrifttime matches 1..19 on vehicle on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-instant-boost/on-set-1
    execute on passengers if score @s[tag=kartdirection] kartdrifttime matches 0 on vehicle on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-instant-boost/off-set-0

    execute on passengers if entity @s[tag=kartdirection] run scoreboard players remove @s[scores={kartdrifttime=1..}] kartdrifttime 1

#순부 사용
execute if entity @s[tag=kart-w-press] on passengers if score @s[tag=kartdirection] kartdrifttime matches 1.. if score #floor-distance floor-distance matches ..2 on vehicle run function kartmobil:move/instant-boost/use
execute if entity @s[tag=a2-using-instant-boost] if score @s kartboosttime matches 1.. if score @s[tag=!burst-instant-boost] kart-engine matches 6 run scoreboard players add @s kartmove 352
execute if entity @s[tag=a2-using-instant-boost] if score @s kartboosttime matches 1.. if score @s[tag=burst-instant-boost] kart-engine matches 6 run scoreboard players add @s kartmove 552
execute if entity @s[tag=a2-using-instant-boost] if score @s kartboosttime matches 1.. if score @s kart-engine matches 8 run scoreboard players add @s kartmove 68

#순부 시 FOV 과장(a2) / direction의 kartmove
execute if entity @s[tag=a2-using-instant-boost,tag=!burst-instant-boost] if score @s kartboosttime matches 1.. if score @s kart-engine matches 6 on passengers unless score @s[tag=kartdirection] kartmove matches 25.. run scoreboard players add @s kartmove 1
execute if entity @s[tag=a2-using-instant-boost,tag=!burst-instant-boost] if score @s kartboosttime matches 5.. if score @s kart-engine matches 6 on passengers unless score @s[tag=kartdirection] kartmove matches 25.. run scoreboard players add @s kartmove 2

execute if entity @s[tag=a2-using-instant-boost,tag=burst-instant-boost] if score @s kartboosttime matches 1.. if score @s kart-engine matches 6 on passengers unless score @s[tag=kartdirection] kartmove matches 25.. run scoreboard players add @s kartmove 3
execute if entity @s[tag=a2-using-instant-boost,tag=burst-instant-boost] if score @s kartboosttime matches 5.. if score @s kart-engine matches 6 on passengers unless score @s[tag=kartdirection] kartmove matches 25.. run scoreboard players add @s kartmove 6

#순부 쿨타임 게이지
execute on passengers store result storage minecraft:kartmain instantgauge int 10.5 if entity @s[tag=kartdirection] run scoreboard players get @s kartdrifttime
execute store result score #exp-gauge kartmain run data get storage minecraft:kartmain instantgauge
execute as @a[tag=kart-listener] run function kartmobil:move/exp-gauge