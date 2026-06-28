scoreboard players operation #hitbox-radius kartcollisiontime = #hitbox-radius-temp kartcollisiontime
execute if entity @s[tag=kart-vertex-left] run scoreboard players operation #hitbox-radius kartcollisiontime += #hitbox-left-temp kartcollisiontime
execute if entity @s[tag=kart-vertex-right] run scoreboard players operation #hitbox-radius kartcollisiontime += #hitbox-right-temp kartcollisiontime
scoreboard players operation #hitbox-radius kartcollisiontime /= #kart2 kartmain
