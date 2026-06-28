execute if entity @s[tag=kart-vertex-front] run scoreboard players operation #hitbox-radius kartcollisiontime = #hitbox-front-temp kartcollisiontime
execute if entity @s[tag=kart-vertex-rear] run scoreboard players operation #hitbox-radius kartcollisiontime = #hitbox-rear-temp kartcollisiontime

execute if entity @s[tag=kart-vertex-left] run scoreboard players operation #hitbox-radius kartcollisiontime = #hitbox-left-temp kartcollisiontime
execute if entity @s[tag=kart-vertex-right] run scoreboard players operation #hitbox-radius kartcollisiontime = #hitbox-right-temp kartcollisiontime