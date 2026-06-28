summon marker ~ 0.0 ~ {Tags:["loop-player-translate"]}

data modify entity @n[tag=loop-player-translate,type=marker] Pos[1] set from entity @s transformation.translation[1]

#execute at @n[tag=loop-player-translate,type=marker] run tp @n[tag=loop-player-translate,type=marker] ~ ~0.45 ~
execute store result storage kartmain translateoffset double 0.0000091552734375 run data get entity @n[tag=kartsaddle] attributes[{id:"minecraft:scale"}].base 32768

function gamemain:loopcourse/translate-player-macro with storage kartmain

data modify entity @s transformation.translation[1] set from entity @n[tag=loop-player-translate,type=marker] Pos[1]

#execute store result entity @s transformation.translation[1] float 1 run data get entity @n[tag=loop-player-translate,type=marker] Pos[1]



kill @e[tag=loop-player-translate,type=marker]