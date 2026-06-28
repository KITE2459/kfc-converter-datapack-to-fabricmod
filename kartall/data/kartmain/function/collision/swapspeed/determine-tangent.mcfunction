#vector-relative
execute rotated as @e[tag=kart-normal-line,limit=1,type=marker] run rotate @e[tag=kart-tangent-line,limit=1,type=marker] ~90 ~
execute store result score #vector-tangent-temp kartcollisiontime run data get entity @e[tag=kart-tangent-line,limit=1,type=marker] Rotation[0]

    scoreboard players operation #vector-tangent-temp kartcollisiontime -= #vector-relative kartcollisiontime
    execute if score #vector-tangent-temp kartcollisiontime matches 180.. run scoreboard players remove #vector-tangent-temp kartcollisiontime 360
    execute if score #vector-tangent-temp kartcollisiontime matches ..-180 run scoreboard players add #vector-tangent-temp kartcollisiontime 360
    execute if score #vector-tangent-temp kartcollisiontime matches ..-1 run scoreboard players operation #vector-tangent-temp kartcollisiontime *= #kart-1 kartmain

    execute unless score #vector-tangent-temp kartcollisiontime matches ..90 rotated as @e[tag=kart-normal-line,limit=1,type=marker] run rotate @e[tag=kart-tangent-line,limit=1,type=marker] ~-90 ~