
execute on passengers rotated as @s[tag=kart-loop-direction,scores={loop-main=0}] on vehicle on passengers if entity @s[tag=kartmodelsaddle] on passengers run rotate @s ~ ~


summon marker ~ ~ ~ {Tags:["aaaaaaaaaaaaaaa"]}

execute on passengers rotated as @s[tag=kart-loop-direction,scores={loop-main=1}] run rotate @e[tag=aaaaaaaaaaaaaaa,limit=1,type=marker] ~180 ~
execute on passengers if entity @s[tag=kart-loop-direction,scores={loop-main=1}] store result entity @e[tag=aaaaaaaaaaaaaaa,limit=1,type=marker] Rotation[1] float -1 run data get entity @s Rotation[1]

execute on passengers if entity @s[tag=kart-loop-direction,scores={loop-main=1}] on vehicle rotated as @e[tag=aaaaaaaaaaaaaaa,limit=1,type=marker] on passengers if entity @s[tag=kartmodelsaddle] on passengers run rotate @s ~ ~

    scoreboard players set #loop360 loop-main 360
    scoreboard players operation #loop-main loop-main = @s loop-main 
    scoreboard players operation #loop-main loop-main %= #loop360 loop-main

    scoreboard players operation #loop-main-2 loop-main = #loop-main loop-main 
    execute if score #loop-main loop-main matches 180..360 run scoreboard players remove #loop-main-2 loop-main 180
    execute if score #loop-main loop-main matches 180..360 run scoreboard players set #loop-main-3 loop-main 180
    execute if score #loop-main loop-main matches 180..360 run scoreboard players operation #loop-main-3 loop-main -= #loop-main-2 loop-main 
    execute if score #loop-main loop-main matches 180..360 run scoreboard players operation #loop-main-2 loop-main = #loop-main-3 loop-main 

    execute if score #rightspeed loop-main matches 0.. run scoreboard players operation #loop-main-2 loop-main *= #kart-1 kartmain

    execute store result storage kartmain loooprotation float 1 run scoreboard players operation #loop-main-2 loop-main /= #kart2 kartmain
    data modify storage kartmain loooprotation2 set from storage kartmain loop-data.startrotation[0]

    execute on passengers if entity @s[tag=kartsaddle] on passengers if entity @s[type=player] run function gamemain:loopcourse/player-yaw with storage kartmain

kill @e[tag=aaaaaaaaaaaaaaa,type=marker]