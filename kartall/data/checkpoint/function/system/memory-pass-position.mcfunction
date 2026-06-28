scoreboard players set #memory-position check-num 0
execute on vehicle on vehicle at @s unless block ~ ~-0.5 ~ #kartmobil:ignoreblock run scoreboard players set #memory-position check-num 1
execute on vehicle on vehicle at @s unless block ~ ~-1 ~ #kartmobil:ignoreblock run scoreboard players set #memory-position check-num 1
execute on vehicle on vehicle at @s unless block ~ ~-1.5 ~ #kartmobil:ignoreblock run scoreboard players set #memory-position check-num 1
execute on vehicle on vehicle at @s unless block ~ ~-2 ~ #kartmobil:ignoreblock run scoreboard players set #memory-position check-num 1
execute on vehicle on vehicle at @s unless block ~ ~-2.5 ~ #kartmobil:ignoreblock run scoreboard players set #memory-position check-num 1

execute if score #memory-position check-num matches 1 on vehicle on vehicle run data modify entity @s data.pos set from entity @s Pos
execute on vehicle on vehicle at @s run data modify entity @s data.pos2 set from entity @n[tag=checkpoint-passed-temp] Pos