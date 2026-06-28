#꼭짓점 배치
    tp @e[tag=kart-vertex,limit=5,type=area_effect_cloud] ~ ~ ~

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-left
    execute as @e[tag=kart-vertex,tag=kart-vertex-left,limit=2,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~-90 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    execute at @e[tag=kart-vertex-left,limit=2,distance=..2.6,type=area_effect_cloud] run particle minecraft:electric_spark ~ ~0.1 ~ 0 -999 0 1000 0 force @a[tag=kart-debug]
    execute at @e[tag=kart-vertex-left,limit=2,distance=..2.6,type=area_effect_cloud] run particle minecraft:electric_spark ~ ~1.5 ~ 0 -999 0 1000 0 force @a[tag=kart-debug]


    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-right
    execute as @e[tag=kart-vertex,tag=kart-vertex-right,limit=2,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~90 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    execute at @e[tag=kart-vertex-right,limit=2,distance=..2.6,type=area_effect_cloud] run particle minecraft:electric_spark ~ ~0.1 ~ 0 -999 0 1000 0 force @a[tag=kart-debug]
    execute at @e[tag=kart-vertex-right,limit=2,distance=..2.6,type=area_effect_cloud] run particle minecraft:electric_spark ~ ~1.5 ~ 0 -999 0 1000 0 force @a[tag=kart-debug]

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-front
    execute as @e[tag=kart-vertex,tag=kart-vertex-front,limit=2,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~ 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-rear
    execute as @e[tag=kart-vertex,tag=kart-vertex-rear,limit=2,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~180 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

execute at @e[tag=kart-vertex,limit=5,distance=..2.6,type=area_effect_cloud] run particle minecraft:electric_spark ~ ~0.1 ~ 0 -999 0 1000 0 force @a[tag=kart-debug]
execute at @e[tag=kart-vertex,limit=5,distance=..2.6,type=area_effect_cloud] run particle minecraft:electric_spark ~ ~1.5 ~ 0 -999 0 1000 0 force @a[tag=kart-debug]
#particle dust{color:[1,0,0],scale:1}

function kartmain:collision/rectangle-hitbox/finish-calc