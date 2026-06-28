execute if entity @s[tag=kartbike] run function kartmobil:move/steering/drift/skidparticle/particle

scoreboard players operation #kart-skid-distance-temp kartdrift = @s kart-hitbox-left
scoreboard players remove #kart-skid-distance-temp kartdrift 2
execute if entity @s[tag=!kartbike] on passengers rotated as @s[tag=kartmodelsaddle] rotated ~-90 0 on vehicle run function kartmobil:move/steering/drift/skidparticle/skid-tree/second-tree/particle-tree/side-tree/main

scoreboard players operation #kart-skid-distance-temp kartdrift = @s kart-hitbox-right
scoreboard players remove #kart-skid-distance-temp kartdrift 2
execute if entity @s[tag=!kartbike] on passengers rotated as @s[tag=kartmodelsaddle] rotated ~90 0 on vehicle run function kartmobil:move/steering/drift/skidparticle/skid-tree/second-tree/particle-tree/side-tree/main