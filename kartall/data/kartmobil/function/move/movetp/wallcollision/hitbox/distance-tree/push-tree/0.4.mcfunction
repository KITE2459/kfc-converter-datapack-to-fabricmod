scoreboard players remove #hitbox-radius-temp kartcollisiontime 4

execute if score #hitbox-radius-temp kartcollisiontime matches 2.. positioned ^ ^ ^0.2 run return run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/0.2
execute if score #hitbox-radius-temp kartcollisiontime matches 1.. positioned ^ ^ ^0.1 run return run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/0.1

function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/final