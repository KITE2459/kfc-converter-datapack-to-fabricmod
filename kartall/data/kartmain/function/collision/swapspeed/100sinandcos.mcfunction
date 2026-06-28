summon text_display .0 0 .0 {Tags:["kart-tri"]}

data remove storage kartmain cos
data modify storage kartmain cos set value {axis:[1f,0f,0f]}

execute store result storage kartmain cos.angle double 1 run scoreboard players operation #angle kartcollisiontime %= #kart360 kartmain
execute store result storage kartmain cos.angle double 0.000003490656 run data get storage kartmain cos.angle 10000

data modify entity @e[tag=kart-tri,limit=1,type=text_display] transformation.left_rotation set from storage kartmain cos

execute store result score #cos-theta kartcollisiontime run data get entity @e[tag=kart-tri,limit=1,type=text_display] transformation.left_rotation[3] 100000
execute if score #angle kartcollisiontime matches 180.. run scoreboard players operation #cos-theta kartcollisiontime *= #kart-1 kartmain
scoreboard players operation #cos-theta kartcollisiontime /= #kart1000 kartmain

execute store result score #sin-theta kartcollisiontime run data get entity @e[tag=kart-tri,limit=1,type=text_display] transformation.left_rotation[0] 100000
execute if score #angle kartcollisiontime matches 180.. run scoreboard players operation #sin-theta kartcollisiontime *= #kart-1 kartmain
scoreboard players operation #sin-theta kartcollisiontime /= #kart1000 kartmain

kill @e[tag=kart-tri,type=text_display]