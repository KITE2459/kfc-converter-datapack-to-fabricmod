
#50까지는 증가 그 후로는 감소
scoreboard players operation #kartangletemp kartmain = #kartangle kartmain
execute if score #kartangle kartmain matches ..-550 run scoreboard players set #kartangletemp kartmain -1100
execute if score #kartangle kartmain matches ..-550 run scoreboard players operation #kartangletemp kartmain -= #kartangle kartmain
execute if score #kartangle kartmain matches 550.. run scoreboard players set #kartangletemp kartmain 1100
execute if score #kartangle kartmain matches 550.. run scoreboard players operation #kartangletemp kartmain -= #kartangle kartmain

scoreboard players operation #kartangletemp kartmain /= #kart2 kartmain

execute store result score #kartplayerangle kartmain on passengers as @s[tag=kartdatacarrier,type=item_display] run data get entity @s Rotation[0] 10
scoreboard players operation #kartplayerangle kartmain += #kartangletemp kartmain
#예외
execute if score #kartplayerangle kartmain matches 1800.. run scoreboard players remove #kartplayerangle kartmain 3600
execute if score #kartplayerangle kartmain matches ..-1800 run scoreboard players add #kartplayerangle kartmain 3600

execute on passengers as @s[tag=kartdatacarrier,type=item_display] store result entity @s Rotation[0] float 0.1 run scoreboard players get #kartplayerangle kartmain

execute on passengers as @s[tag=kartdatacarrier,type=item_display] rotated as @s on vehicle on passengers as @s[tag=kartmodelsaddle,type=item_display] run rotate @s ~ 0
execute on passengers as @s[tag=kartdatacarrier,type=item_display] rotated as @s on vehicle on passengers run rotate @s[tag=kartsaddle,type=#kartmobil:modeldir] ~ 0