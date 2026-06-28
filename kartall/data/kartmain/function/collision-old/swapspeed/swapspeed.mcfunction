scoreboard players operation #kart-speed-a kartmove = @n[tag=carA,type=#kartmobil:kartmobil] kartmove
scoreboard players operation #kart-speed-b kartmove = @n[tag=carB,type=#kartmobil:kartmobil] kartmove

scoreboard players set #first-const kartcollisiontime 100
scoreboard players operation #first-const kartcollisiontime -= #100e-dynamic kartcollisiontime

scoreboard players set #second-const kartcollisiontime 100
scoreboard players operation #second-const kartcollisiontime += #100e-dynamic kartcollisiontime

    #v1 = {(100 - 100e)v1 + (100 + 100e)v2} / 200
    scoreboard players operation #v1 kartcollisiontime = #first-const kartcollisiontime
    scoreboard players operation #v1 kartcollisiontime *= #kart-speed-a kartmove

    scoreboard players operation #v2 kartcollisiontime = #second-const kartcollisiontime
    scoreboard players operation #v2 kartcollisiontime *= #kart-speed-b kartmove

    scoreboard players operation #v1 kartcollisiontime += #v2 kartcollisiontime
    scoreboard players operation #v1 kartcollisiontime /= #kart200 kartmain

scoreboard players operation @n[tag=carA,type=#kartmobil:kartmobil] kartmove = #v1 kartcollisiontime

    #v2 = {(100 + 100e)v1 + (100 - 100e)v2} / 200
    scoreboard players operation #v1 kartcollisiontime = #second-const kartcollisiontime
    scoreboard players operation #v1 kartcollisiontime *= #kart-speed-a kartmove

    scoreboard players operation #v2 kartcollisiontime = #first-const kartcollisiontime
    scoreboard players operation #v2 kartcollisiontime *= #kart-speed-b kartmove

    scoreboard players operation #v2 kartcollisiontime += #v1 kartcollisiontime
    scoreboard players operation #v2 kartcollisiontime /= #kart200 kartmain

scoreboard players operation @n[tag=carB,type=#kartmobil:kartmobil] kartmove = #v2 kartcollisiontime