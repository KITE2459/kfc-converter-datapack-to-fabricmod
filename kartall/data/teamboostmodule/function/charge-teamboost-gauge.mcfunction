
execute if score @s kart-team matches 0 run scoreboard players operation @s kart-teamboost-gauge /= #redteam-count kart-teamboost
execute if score @s kart-team matches 0 run scoreboard players operation #redteam-gauge kart-teamboost-gauge += @s kart-teamboost-gauge

execute if score @s kart-team matches 1 run scoreboard players operation @s kart-teamboost-gauge /= #blueteam-count kart-teamboost
execute if score @s kart-team matches 1 run scoreboard players operation #blueteam-gauge kart-teamboost-gauge += @s kart-teamboost-gauge

scoreboard players set @s kart-teamboost-gauge 0