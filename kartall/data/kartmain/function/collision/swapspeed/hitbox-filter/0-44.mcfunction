scoreboard players operation #angle kartcollisiontime = #angle kartcollisiontime
scoreboard players operation #nemo-result kartcollisiontime = #angle kartcollisiontime

scoreboard players operation #nemo-result kartcollisiontime *= #kart45 kartmain

    scoreboard players set #bunmo kartcollisiontime 45
    scoreboard players operation #bunmo kartcollisiontime -= #angle kartcollisiontime
    scoreboard players operation #bunmo kartcollisiontime *= #hitbox-round kartcollisiontime
    scoreboard players operation #bunmo kartcollisiontime /= #kart4 kartmain

    scoreboard players operation #bunmo kartcollisiontime += #angle kartcollisiontime

    scoreboard players operation #nemo-result kartcollisiontime /= #bunmo kartcollisiontime