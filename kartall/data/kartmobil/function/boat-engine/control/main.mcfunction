
tag @s remove boat-a
tag @s remove boat-d
tag @s remove boat-w
tag @s remove boat-s

execute if entity @p[tag=boat-player,predicate=kartmobil:a,predicate=!kartmobil:d] run tag @s add boat-a
execute if entity @p[tag=boat-player,predicate=kartmobil:d,predicate=!kartmobil:a] run tag @s add boat-d
execute if entity @p[tag=boat-player,predicate=kartmobil:w,predicate=!kartmobil:s] run tag @s add boat-w
execute if entity @p[tag=boat-player,predicate=kartmobil:s,predicate=!kartmobil:w] run tag @s add boat-s

#단위는 100000을 곱해서 처리함. 즉 1 = 100000

#회전 처리
execute if entity @s[tag=boat-a] run scoreboard players remove @s boat-angle-speed 100000
execute if entity @s[tag=boat-d] run scoreboard players add @s boat-angle-speed 100000

#이동 처리
scoreboard players set #front-vector boat-move-x 0
scoreboard players set #front-vector boat-move-z 0

execute if entity @s[tag=boat-w] at @s on passengers rotated as @s[tag=kartdirection] on vehicle run function kartmobil:boat-engine/control/make-front-vector-4000
execute if entity @s[tag=boat-w] run scoreboard players operation @s boat-move-x += #front-vector boat-move-x
execute if entity @s[tag=boat-w] run scoreboard players operation @s boat-move-z += #front-vector boat-move-z

execute if entity @s[tag=boat-s] at @s on passengers rotated as @s[tag=kartdirection] on vehicle run function kartmobil:boat-engine/control/make-front-vector-500
execute if entity @s[tag=boat-s] run scoreboard players operation @s boat-move-x -= #front-vector boat-move-x
execute if entity @s[tag=boat-s] run scoreboard players operation @s boat-move-z -= #front-vector boat-move-z

scoreboard players operation front-vector boat-move-x += #front-vector boat-move-x