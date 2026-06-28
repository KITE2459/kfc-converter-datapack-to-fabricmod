scoreboard players operation #hitbox-front-temp kartcollisiontime = @s kart-hitbox-front
scoreboard players operation #hitbox-rear-temp kartcollisiontime = @s kart-hitbox-rear
scoreboard players operation #hitbox-left-temp kartcollisiontime = @s kart-hitbox-left
scoreboard players operation #hitbox-right-temp kartcollisiontime = @s kart-hitbox-right
scoreboard players operation #hitbox-radius-temp kartcollisiontime = @s kart-hitbox-radius
scoreboard players set #kart-push-loop-limit kartcollisiontime 15

#직선
execute as @e[tag=kart-vertex-nearest,limit=1,distance=..2.6,type=area_effect_cloud] run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode1-directions