scoreboard players remove #kart-skid-distance-temp kartdrift 8

execute if score #kart-skid-distance-temp kartdrift matches 4.. positioned ^ ^ ^0.4 run return run function kartmobil:move/steering/drift/skidparticle/skid-tree/second-tree/particle-tree/side-tree/0.4
execute if score #kart-skid-distance-temp kartdrift matches 2.. positioned ^ ^ ^0.2 run return run function kartmobil:move/steering/drift/skidparticle/skid-tree/second-tree/particle-tree/side-tree/0.2
execute if score #kart-skid-distance-temp kartdrift matches 1.. positioned ^ ^ ^0.1 run return run function kartmobil:move/steering/drift/skidparticle/skid-tree/second-tree/particle-tree/side-tree/0.1

function kartmobil:move/steering/drift/skidparticle/skid-tree/second-tree/particle-tree/side-tree/final