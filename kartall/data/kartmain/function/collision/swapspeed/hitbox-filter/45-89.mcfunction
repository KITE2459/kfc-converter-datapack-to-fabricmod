scoreboard players operation #angle-90 kartcollisiontime = #angle kartcollisiontime
scoreboard players remove #angle-90 kartcollisiontime 90

scoreboard players operation #nemo-result kartcollisiontime = #angle-90 kartcollisiontime
scoreboard players operation #nemo-result kartcollisiontime *= #kart45 kartmain

    scoreboard players set #bunmo kartcollisiontime 45
    scoreboard players operation #bunmo kartcollisiontime += #angle-90 kartcollisiontime
    scoreboard players operation #bunmo kartcollisiontime *= #hitbox-round kartcollisiontime
    scoreboard players operation #bunmo kartcollisiontime /= #kart4 kartmain

    scoreboard players operation #bunmo kartcollisiontime -= #angle-90 kartcollisiontime

    scoreboard players operation #nemo-result kartcollisiontime /= #bunmo kartcollisiontime
    scoreboard players add #nemo-result kartcollisiontime 90