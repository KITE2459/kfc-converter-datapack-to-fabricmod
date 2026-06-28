scoreboard players set #vertex-detected kartcollisiontime 0
scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-right
scoreboard players add #hitbox-length kartcollisiontime 1
execute on passengers if entity @s[tag=kartmodelsaddle] rotated as @s rotated ~-90 0 run function kartmain:collision/rectangle-hitbox/boundary-detect-tree/main
execute if score #vertex-detected kartcollisiontime matches 0 run return 0

scoreboard players set #vertex-detected kartcollisiontime 0
scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-left
scoreboard players add #hitbox-length kartcollisiontime 1
execute on passengers if entity @s[tag=kartmodelsaddle] rotated as @s rotated ~90 0 run function kartmain:collision/rectangle-hitbox/boundary-detect-tree/main
execute if score #vertex-detected kartcollisiontime matches 0 run return 0

scoreboard players set #vertex-detected kartcollisiontime 0
scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-rear
scoreboard players add #hitbox-length kartcollisiontime 1
execute on passengers if entity @s[tag=kartmodelsaddle] rotated as @s rotated ~ 0 run function kartmain:collision/rectangle-hitbox/boundary-detect-tree/main
execute if score #vertex-detected kartcollisiontime matches 0 run return 0

scoreboard players operation #hitbox-length kartcollisiontime = @s kart-hitbox-front
scoreboard players add #hitbox-length kartcollisiontime 1
execute on passengers if entity @s[tag=kartmodelsaddle] rotated as @s rotated ~180 0 run function kartmain:collision/rectangle-hitbox/boundary-detect-tree/main

#상하 경계 / 아래로 0.5 위로 1
execute positioned ~ ~7.5 ~ unless entity @e[tag=kart-vertex,limit=5,distance=..8,type=area_effect_cloud] run scoreboard players set #vertex-detected kartcollisiontime 0
execute positioned ~ ~-7 ~ unless entity @e[tag=kart-vertex,limit=5,distance=..8,type=area_effect_cloud] run scoreboard players set #vertex-detected kartcollisiontime 0

