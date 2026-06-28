#직선
execute if entity @s[tag=kart-vertex-additional] run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode1-straight

#대각선
execute if entity @s[tag=kart-vertex,tag=kart-vertex-front] run scoreboard players operation #hitbox-radius kartcollisiontime = #hitbox-radius-temp kartcollisiontime

#애매한 대각선
execute if entity @s[tag=kart-vertex,tag=kart-vertex-rear] run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode1-if-bit-diagonal

tag @s remove kart-vertex-nearest