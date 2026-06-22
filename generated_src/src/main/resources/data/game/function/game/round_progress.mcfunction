execute if score game game.difficulty matches 5 run function game:infinity/round_progress with storage game setup
execute if score game game.difficulty matches 5 run return 0

$execute as @n[tag=map.spawn_point,tag=game] at @s run function api:enemy/spawn with storage map $(map).$(difficulty).round[$(round)].tick_$(time)
$execute store result score #temp time run data get storage map $(map).$(difficulty).round[$(round)].end
execute if score timer gameState >= #temp time run scoreboard players set stage gameState 3
execute if score timer gameState >= #temp time run give @a yellow_dye[item_name=[{text:"라운드 스킵",bold:true}]] 1
execute if score timer gameState >= #temp time run scoreboard players add #temp time 300