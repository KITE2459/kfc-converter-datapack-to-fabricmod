# NemoMediaSpin created via BDEngine
execute as @e[type=minecraft:block_display,tag=nemomediaspin] on passengers run kill @s
execute as @e[type=minecraft:block_display,tag=nemomediaspin] run kill @s

forceload remove 12328 -5802 12282 -5880
scoreboard players reset #media-remove track-anime