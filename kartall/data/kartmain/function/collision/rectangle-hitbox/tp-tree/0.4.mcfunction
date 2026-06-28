scoreboard players remove #hitbox-length-temp kartcollisiontime 4

execute if score #hitbox-length-temp kartcollisiontime matches 2.. positioned ^ ^ ^0.2 run return run function kartmain:collision/rectangle-hitbox/tp-tree/0.2
execute if score #hitbox-length-temp kartcollisiontime matches 1.. positioned ^ ^ ^0.1 run return run function kartmain:collision/rectangle-hitbox/tp-tree/0.1

function kartmain:collision/rectangle-hitbox/tp-tree/final