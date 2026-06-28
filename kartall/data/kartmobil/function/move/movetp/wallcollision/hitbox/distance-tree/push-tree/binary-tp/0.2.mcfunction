scoreboard players set #kart-pushing-wall-temp kartcollisiontime 0
execute facing entity @s feet if function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/wall-condition run scoreboard players set #kart-pushing-wall-temp kartcollisiontime 1

execute if score #kart-pushing-wall-temp kartcollisiontime matches 1 positioned as @s positioned ^ ^ ^-0.1 run tp ~ ~ ~
execute if score #kart-pushing-wall-temp kartcollisiontime matches 1 positioned ^ ^ ^-0.1 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/binary-tp/0.1

execute if score #kart-pushing-wall-temp kartcollisiontime matches 0 positioned as @s positioned ^ ^ ^0.1 run tp ~ ~ ~
execute if score #kart-pushing-wall-temp kartcollisiontime matches 0 positioned ^ ^ ^0.1 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/binary-tp/0.1