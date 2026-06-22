execute if score game game.difficulty matches 5 run function game:infinity/round_wait with storage game setup
execute if score game game.difficulty matches 5 run return 0

$execute store result score #total_rounds temp run data get storage map $(map).$(difficulty).round
execute if score timer gameState >= #temp time if score game wave >= #total_rounds temp store result score #enemy_count temp if entity @e[tag=enemy]
execute if score timer gameState >= #temp time if score game wave >= #total_rounds temp if score #enemy_count temp matches 0 run function game:game/victory
execute if score game wave >= #total_rounds temp run return 0
execute if items entity @a weapon.offhand yellow_dye run scoreboard players set stage gameState 1
execute if items entity @a weapon.offhand yellow_dye run scoreboard players add game wave 1
execute if items entity @a weapon.offhand yellow_dye run scoreboard players set timer gameState -1
execute if score timer gameState >= #temp time run scoreboard players set stage gameState 1
execute if score timer gameState >= #temp time run scoreboard players add game wave 1
execute if score timer gameState >= #temp time run scoreboard players set timer gameState -1
execute unless entity @n[tag=enemy] run scoreboard players set stage gameState 1
execute unless entity @n[tag=enemy] run scoreboard players add game wave 1
execute unless entity @n[tag=enemy] run scoreboard players set timer gameState -1