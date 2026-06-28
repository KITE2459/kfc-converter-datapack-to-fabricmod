
execute if score #hitbox-radius-temp kartcollisiontime matches 8.. positioned ^ ^ ^0.8 run return run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/0.8
execute if score #hitbox-radius-temp kartcollisiontime matches 4.. positioned ^ ^ ^0.4 run return run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/0.4
execute if score #hitbox-radius-temp kartcollisiontime matches 2.. positioned ^ ^ ^0.2 run return run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/0.2
execute if score #hitbox-radius-temp kartcollisiontime matches 1.. positioned ^ ^ ^0.1 run return run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/0.1

say 거리가 0인 값을 집어넣으면 어떡하니!