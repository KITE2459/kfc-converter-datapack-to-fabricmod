execute as @e[tag=kartmobil,tag=kart-teamboost,scores={kart-team=1},type=#kartmobil:kartmobil] on passengers if entity @s[tag=kartsaddle] on passengers run tag @s add kart-team-passenger
#TODO
execute store result score #blueteam-count kart-teamboost run execute if entity @a[tag=kart-team-passenger]

bossbar set minecraft:blueteam-gauge players @a[tag=kart-team-passenger]
execute store result bossbar minecraft:blueteam-gauge value run scoreboard players get #blueteam-gauge kart-teamboost-gauge
execute if score #blueteam-gauge kart-teamboost-gauge matches 4000.. as @e[tag=kartmobil,tag=kart-teamboost,scores={kart-team=1},type=#kartmobil:kartmobil] run function teamboostmodule:team/get-teamboost
execute if score #blueteam-gauge kart-teamboost-gauge matches 4000.. run scoreboard players set #blueteam-gauge kart-teamboost-gauge 0

tag @a[tag=kart-team-passenger] remove kart-team-passenger