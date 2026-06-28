scoreboard players set #is-collision kartcollisiontime 0
scoreboard players set #is-projection-success kartcollisiontime 0

execute positioned as @s rotated 0 0 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/main
execute positioned as @s rotated 180 0 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/main
execute positioned as @s rotated 90 0 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/main
execute positioned as @s rotated -90 0 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/main

execute if score #is-projection-success kartcollisiontime matches 0 positioned as @s rotated 45 0 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/main-diagonal
execute if score #is-projection-success kartcollisiontime matches 0 positioned as @s rotated -45 0 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/main-diagonal
execute if score #is-projection-success kartcollisiontime matches 0 positioned as @s rotated 135 0 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/main-diagonal
execute if score #is-projection-success kartcollisiontime matches 0 positioned as @s rotated -135 0 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/main-diagonal

execute if score #is-collision kartcollisiontime matches 1 run function kartmobil:move/movetp/wallcollision/hitbox/success