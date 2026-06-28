    execute positioned as @s run tp @e[tag=kart-vertex-all,limit=9,type=area_effect_cloud] ~ ~ ~

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-left
    execute on passengers if entity @s[tag=kartmodelsaddle] rotated as @s on vehicle as @e[tag=kart-vertex-all,tag=kart-vertex-left,limit=3,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~-90 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-right
    execute on passengers if entity @s[tag=kartmodelsaddle] rotated as @s on vehicle as @e[tag=kart-vertex-all,tag=kart-vertex-right,limit=3,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~90 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-front
    execute on passengers if entity @s[tag=kartmodelsaddle] rotated as @s on vehicle as @e[tag=kart-vertex-all,tag=kart-vertex-front,limit=3,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~ 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main

    scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-rear
    execute on passengers if entity @s[tag=kartmodelsaddle] rotated as @s on vehicle as @e[tag=kart-vertex-all,tag=kart-vertex-rear,limit=3,distance=..2.6,type=area_effect_cloud] positioned as @s rotated ~180 0 run function kartmain:collision/rectangle-hitbox/tp-tree/main
particle electric_spark ~ ~1 ~
execute at @n[tag=kart-vertex-all,distance=..2.6,type=area_effect_cloud] run particle electric_spark ~ ~1 ~

tag @e[tag=kart-vertex-all,tag=wall-collision-detected,limit=9,distance=..2.6,type=area_effect_cloud] remove wall-collision-detected
execute as @n[tag=kart-vertex-all,distance=..2.6,type=area_effect_cloud] at @s if function kartmobil:move/movetp/wallcollision/hitbox/condition run tag @s add wall-collision-detected

execute if entity @e[tag=kart-vertex-all,tag=wall-collision-detected,limit=9,distance=..2.6,type=area_effect_cloud] rotated ~180 ~ positioned as @s run tp @s ^ ^ ^0.1
execute if entity @e[tag=kart-vertex-all,tag=wall-collision-detected,limit=9,distance=..2.6,type=area_effect_cloud] run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode2-detect-collision