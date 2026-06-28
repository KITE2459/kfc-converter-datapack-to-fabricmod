scoreboard players remove #hub-player-count-half multi-main 1

execute as @r[tag=multi-hub-player,scores={kart-team=-1}] run scoreboard players set @s kart-team 0

execute if score #hub-player-count-half multi-main matches 1.. run function multiplay:hub/team-assign/random-team-loop