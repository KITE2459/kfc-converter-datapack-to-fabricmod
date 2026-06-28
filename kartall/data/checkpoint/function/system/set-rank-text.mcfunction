execute store result storage checkpoint:checkpoint rank byte 1 run scoreboard players operation @s multi-instant-rank += #complete-player-count multi-rank

scoreboard players operation @s timerdisplay = player-count multi-instant-rank
scoreboard players operation @s timerdisplay -= @s multi-instant-rank
function checkpoint:system/set-rank-text-macro with storage checkpoint:checkpoint

scoreboard players operation @s multi-instant-rank -= #complete-player-count multi-rank