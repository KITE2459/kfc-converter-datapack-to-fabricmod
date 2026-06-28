
scoreboard players operation #kart-skid-distance-temp kartdrift = @s kart-hitbox-front
scoreboard players remove #kart-skid-distance-temp kartdrift 4
execute if score @s kart-engine matches 1007 on passengers rotated as @s[tag=kartmodelsaddle] rotated ~180 0 on vehicle run function kartmobil:move/steering/drift/skidparticle/skid-tree/second-tree/particle-tree/main