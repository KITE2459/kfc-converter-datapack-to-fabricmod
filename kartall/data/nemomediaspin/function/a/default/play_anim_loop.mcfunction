# NemoMediaSpin created via BDEngine
scoreboard players set #media-is-playing track-anime 1

execute as @e[tag=nemomediaspin_root,type=block_display] at @s run tag @s remove animation_pause
execute as @e[tag=nemomediaspin_root,type=block_display] at @s run tag @s add animation_loop
execute as @e[tag=nemomediaspin_root,type=block_display] at @s run data modify entity @e[type=minecraft:block_display,tag=nemomediaspin_camera,limit=1,sort=nearest] teleport_duration set value 0
schedule function nemomediaspin:k/default/start 0.1s
