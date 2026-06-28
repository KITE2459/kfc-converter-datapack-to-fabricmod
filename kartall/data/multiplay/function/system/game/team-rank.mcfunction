execute if score #rank-print-temp multi-rank matches 1 run scoreboard players set #rank-team-score multi-rank 10
execute if score #rank-print-temp multi-rank matches 2 run scoreboard players set #rank-team-score multi-rank 8

execute if score #rank-print-temp multi-rank matches 3 run scoreboard players set #rank-team-score multi-rank 6
execute if score #rank-print-temp multi-rank matches 4 run scoreboard players set #rank-team-score multi-rank 5
execute if score #rank-print-temp multi-rank matches 5 run scoreboard players set #rank-team-score multi-rank 4
execute if score #rank-print-temp multi-rank matches 6 run scoreboard players set #rank-team-score multi-rank 3
execute if score #rank-print-temp multi-rank matches 7 run scoreboard players set #rank-team-score multi-rank 2
execute if score #rank-print-temp multi-rank matches 8 run scoreboard players set #rank-team-score multi-rank 1
execute if score #rank-print-temp multi-rank matches 9.. run scoreboard players set #rank-team-score multi-rank 0

tellraw @a [{"score":{"name":"#rank-print-temp","objective":"multi-rank","color":"yellow"},"color":"yellow"},{translate:"등 ","color":"yellow"}, {"selector":"@s"},{translate:" | ","color":"yellow"},{"nbt":"multi-record[0]","storage":"timermain","interpret":true},{"nbt":"multi-record-subtick[0]","color":"yellow","storage":"timermain","interpret":false},{translate:" +","color":"yellow"},{"score":{"name":"#rank-team-score","objective":"multi-rank","color":"yellow"},"color":"yellow"}]

execute if entity @s[team=redteam] run scoreboard players operation #redteam-score multi-rank += #rank-team-score multi-rank
execute if entity @s[team=blueteam] run scoreboard players operation #blueteam-score multi-rank += #rank-team-score multi-rank