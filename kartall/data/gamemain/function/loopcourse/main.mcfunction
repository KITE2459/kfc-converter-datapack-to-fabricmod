tag @s add loop-main

execute as @e[tag=kartmobil,distance=..15,type=#kartmobil:kartmobil] at @s if block ~ ~-0.5 ~ test_instance_block run function gamemain:loopcourse/start-loop
execute as @e[tag=kartmobil,distance=..15,type=#kartmobil:kartmobil] at @s if block ~ ~-1.5 ~ test_instance_block run function gamemain:loopcourse/start-loop

tag @s remove loop-main