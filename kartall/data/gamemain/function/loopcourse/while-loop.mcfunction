execute on passengers if entity @s[tag=kartsaddle] on passengers run playsound minecraft:entity.vex.hurt neutral @s[type=player] ~ ~ ~ 1 0
execute on passengers if entity @s[tag=kartsaddle] on passengers run effect give @s[type=player] minecraft:invisibility 1 1 true
execute on passengers if entity @s[tag=kartsaddle] on passengers run tag @s[type=player] add kart-listener
execute on passengers if entity @s[tag=kartsaddle] on passengers run tag @s[type=player] add kartpassenger

stopsound @a[tag=kart-listener] master minecraft:entity.player.hurt

execute on passengers run tag @s[tag=kartdirection] add kart-loop-direction

data modify storage minecraft:kartmain loop-data set from entity @s data.loop-data
execute store result score #anglespeed loop-main run data get storage minecraft:kartmain loop-data.anglespeed-real
execute store result score #endpitch loop-main run data get storage minecraft:kartmain loop-data.endpitch
execute store result score #rightspeed loop-main run data get storage minecraft:kartmain loop-data.rightspeed 10

    execute store result score @s kartmove run data get storage minecraft:kartmain loop-data.speed 20000
    execute on passengers if entity @s[tag=kart-old-velocity] store result score @s kartmove-remain run data get storage minecraft:kartmain loop-data.speed 20000

    function kartmobil:sound-and-fov/sound
    function kartmobil:tachometer/main

    execute on passengers if entity @s[tag=kartsaddle] on passengers run tag @s remove kart-listener
    execute on passengers if entity @s[tag=kartsaddle] on passengers run tag @s remove kartpassenger

execute if entity @s[tag=kart-loop-flipped-late2] run function gamemain:loopcourse/flip-after
tag @s[tag=kart-loop-flipped-late] add kart-loop-flipped-late2

execute if entity @e[tag=kart-loop-direction,limit=1,type=item_display,x_rotation=-90] run function gamemain:loopcourse/flip
execute if entity @e[tag=kart-loop-direction,limit=1,type=item_display,x_rotation=90] run function gamemain:loopcourse/flip

execute at @s run function gamemain:loopcourse/move-by-macro with storage minecraft:kartmain loop-data
execute at @s run function gamemain:loopcourse/rotate-model

scoreboard players operation @s loop-main += #anglespeed loop-main
execute if score @s[tag=!kart-reverse-loop] loop-main <= #endpitch loop-main run function gamemain:loopcourse/end-loop
execute if score @s[tag=kart-reverse-loop] loop-main >= #endpitch loop-main run function gamemain:loopcourse/end-loop

execute on passengers run tag @s[tag=kartdirection] remove kart-loop-direction