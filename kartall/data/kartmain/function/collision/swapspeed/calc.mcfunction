#https://www.desmos.com/geometry/fx1q66xmee
#added friction

execute if score #kart-a-speed kartcollisiontime matches ..1000 if score #kart-b-speed kartcollisiontime matches ..1000 run return run function kartmain:collision/swapspeed/exeption/main

#constant
    #(100+100e)
    scoreboard players operation #100+100e kartcollisiontime = #100e kartcollisiontime
    scoreboard players add #100+100e kartcollisiontime 100

    #get past position
    summon marker ~ ~ ~ {Tags:["kart-normal-line"]}
    summon marker ~ ~ ~ {Tags:["kart-tangent-line"]}

    function kartmain:collision/swapspeed/1tick-past/main

#get relative velocity
execute store result storage kartmain kartvector1 double 0.0005 run scoreboard players get #kart-a-speed kartcollisiontime
execute store result storage kartmain kartvector2 double 0.0005 run scoreboard players get #kart-b-speed kartcollisiontime

execute at @e[tag=kart-a-past,limit=1,type=marker] run function kartmain:collision/swapspeed/vector-add/get-relative with storage kartmain
    scoreboard players set #magnitude kartcollisiontime 0
    execute at @e[tag=kart-a-past,limit=1,type=marker] as @e[tag=kart-vector-result-relative,limit=1,type=marker] facing entity @s feet run function kartmain:collision/swapspeed/get-magnitude/main
    
    scoreboard players operation #magnitude-relative kartcollisiontime = #magnitude kartcollisiontime
    execute store result score #vector-relative kartcollisiontime run data get entity @e[tag=kart-vector-result-relative,limit=1,type=marker] Rotation[0]

#summon marker ~ ~ ~ {Tags:["kart-normal-line"]}
#summon marker ~ ~ ~ {Tags:["kart-tangent-line"]}

#calculate impulse and friction
execute positioned as @e[tag=kart-a-past,limit=1,type=marker] facing entity @e[tag=kart-b-past,limit=1,type=marker] feet run rotate @e[tag=kart-normal-line,limit=1,type=marker] ~ ~
        
        #THIS BLOCK IS UNUSED NOW

        #apply a filter to normal line to simulate round square collision
        execute unless score #kart-hitbox-mode kartcollisiontime matches 1 run function kartmain:collision/swapspeed/hitbox-filter/main

        #process abnormal collision(cos < 0)
        execute if score #angle-before-filter kartcollisiontime matches 136.. run return run function kartmain:collision/swapspeed/kill-entity
        execute if score #angle-before-filter kartcollisiontime matches 91.. run return run function kartmain:collision/swapspeed/exeption/main

    #get angle between a and normal
    execute store result score #vector-vt kartcollisiontime run data get entity @e[tag=kart-normal-line,limit=1,type=marker] Rotation[0]
    scoreboard players operation #vector-vt kartcollisiontime -= #vector-relative kartcollisiontime
    execute if score #vector-vt kartcollisiontime matches 180.. run scoreboard players remove #vector-vt kartcollisiontime 360
    execute if score #vector-vt kartcollisiontime matches ..-180 run scoreboard players add #vector-vt kartcollisiontime 360
    
    #clamp if (cos < 0)
    #execute if score #angle kartcollisiontime matches 91.. run return run function kartmain:collision/swapspeed/exeption/main
    execute unless score #vector-vt kartcollisiontime matches -90..90 run function kartmain:collision/swapspeed/exeption/clamp

    execute if score #vector-vt kartcollisiontime matches ..-1 run scoreboard players operation #vector-vt kartcollisiontime *= #kart-1 kartmain
    scoreboard players operation #angle kartcollisiontime = #vector-vt kartcollisiontime

        #normal line projection
        function kartmain:collision/swapspeed/100sinandcos
        execute if score #sin-theta kartcollisiontime matches ..-1 run scoreboard players operation #sin-theta kartcollisiontime *= #kart-1 kartmain
        execute if score #cos-theta kartcollisiontime matches ..-1 run scoreboard players operation #cos-theta kartcollisiontime *= #kart-1 kartmain

            #defense
            function kartmain:collision/swapspeed/calc-defense

            #[(100+100e) / 200] * 100cos(angle) * relative | force
            scoreboard players operation #cos-theta kartcollisiontime *= #100+100e kartcollisiontime

            scoreboard players operation #impulse-magnitude-a kartcollisiontime = #cos-theta kartcollisiontime
            scoreboard players operation #impulse-magnitude-a kartcollisiontime *= #magnitude-relative-a kartcollisiontime
            scoreboard players operation #impulse-magnitude-a kartcollisiontime /= #kart2 kartmain
            #scoreboard players operation #impulse-magnitude-a kartcollisiontime /= #ma+mb kartcollisiontime
            #scoreboard players operation #impulse-magnitude-a kartcollisiontime *= #mass-b kartcollisiontime

            scoreboard players operation #impulse-magnitude-b kartcollisiontime = #cos-theta kartcollisiontime
            scoreboard players operation #impulse-magnitude-b kartcollisiontime *= #magnitude-relative-b kartcollisiontime
            scoreboard players operation #impulse-magnitude-b kartcollisiontime /= #kart2 kartmain
            #scoreboard players operation #impulse-magnitude-b kartcollisiontime /= #ma+mb kartcollisiontime
            #scoreboard players operation #impulse-magnitude-b kartcollisiontime *= #mass-a kartcollisiontime
        
            #[100d / 200] * 100sin(angle) * relative | friction
            scoreboard players operation #sin-theta kartcollisiontime *= #100f kartcollisiontime

            scoreboard players operation #sin-theta-a kartcollisiontime = #sin-theta kartcollisiontime
            scoreboard players operation #sin-theta-b kartcollisiontime = #sin-theta kartcollisiontime

            scoreboard players operation #sin-theta-a kartcollisiontime *= #magnitude-relative-a kartcollisiontime
            scoreboard players operation #sin-theta-b kartcollisiontime *= #magnitude-relative-b kartcollisiontime

#collision - a
execute positioned as @e[tag=kart-b-past,limit=1,type=marker] facing entity @e[tag=kart-a-past,limit=1,type=marker] feet run rotate @e[tag=kart-normal-line,limit=1,type=marker] ~ 0
function kartmain:collision/swapspeed/determine-tangent

    execute store result storage kartmain kartvector1 double 0.00000005 run scoreboard players get #impulse-magnitude-a kartcollisiontime
    execute store result storage kartmain kartvector2 double 0.0005 run scoreboard players get #kart-a-speed kartcollisiontime
    execute store result storage kartmain kartvector3 double -0.000000025 run scoreboard players get #sin-theta-a kartcollisiontime

    scoreboard players set #magnitude kartcollisiontime 0
    execute at @e[tag=kart-a-past,limit=1,type=marker] run function kartmain:collision/swapspeed/vector-add/get-vector-a with storage kartmain
    execute at @e[tag=kart-a-past,limit=1,type=marker] as @e[tag=kart-vector-result-a,limit=1,type=marker] facing entity @s feet run function kartmain:collision/swapspeed/get-magnitude/main

    ##apply speed
    scoreboard players operation @s kartmove = #magnitude kartcollisiontime

#collision - b
execute rotated as @e[tag=kart-normal-line,limit=1,type=marker] run rotate @e[tag=kart-normal-line,limit=1,type=marker] ~180 0
function kartmain:collision/swapspeed/determine-tangent

    execute store result storage kartmain kartvector1 double 0.00000005 run scoreboard players get #impulse-magnitude-b kartcollisiontime
    execute store result storage kartmain kartvector2 double 0.0005 run scoreboard players get #kart-b-speed kartcollisiontime
    execute store result storage kartmain kartvector3 double 0.000000025 run scoreboard players get #sin-theta-b kartcollisiontime

    scoreboard players set #magnitude kartcollisiontime 0
    execute at @e[tag=kart-b-past,limit=1,type=marker] run function kartmain:collision/swapspeed/vector-add/get-vector-b with storage kartmain
    execute at @e[tag=kart-b-past,limit=1,type=marker] as @e[tag=kart-vector-result-b,limit=1,type=marker] facing entity @s feet run function kartmain:collision/swapspeed/get-magnitude/main

    ##apply speed
    scoreboard players operation @e[tag=carB,limit=1,type=#kartmobil:kartmobil] kartmove = #magnitude kartcollisiontime

##apply direction
data modify entity @e[tag=carAdirection,limit=1,type=item_display] Rotation[0] set from entity @e[tag=kart-vector-result-a,limit=1,type=marker] Rotation[0]
data modify entity @e[tag=carBdirection,limit=1,type=item_display] Rotation[0] set from entity @e[tag=kart-vector-result-b,limit=1,type=marker] Rotation[0]

function kartmain:collision/swapspeed/kill-entity
