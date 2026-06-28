execute positioned as @s if function kartmobil:move/movetp/wallcollision/hitbox/condition run return 0

scoreboard players operation #hitbox-radius-temp kartcollisiontime = #hitbox-radius kartcollisiontime

scoreboard players set #kart-pushing-wall kartcollisiontime 0
function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/start-tree

execute if score #kart-pushing-wall kartcollisiontime matches 1 run scoreboard players set #is-collision kartcollisiontime 1
execute if score #kart-pushing-wall kartcollisiontime matches 1 positioned as @s run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/push