attribute @s minecraft:entity_interaction_range modifier add 0-0-0-0-1699 10 add_value

tag @s add trackselect-inroom

scoreboard players add #player-count trackselect-map-id 1

#보스바 및 타이머
scoreboard players add @s trackselect-map-id 1
execute store result bossbar trackselect value run scoreboard players get @s trackselect-map-id
execute if score @s trackselect-map-id matches 1200.. run function trackselect:track-ui/return-to-room

#2명 이상이 있으면 킥당하기
execute if score #player-count trackselect-map-id matches 2.. run function trackselect:track-ui/return-to-room

#인터랙션
execute as @e[tag=track-theme-interaction,type=interaction] at @s on attacker run function trackselect:track-ui/show-track-ui
execute as @e[tag=track-theme-interaction,type=interaction] at @s on target run function trackselect:track-ui/show-track-ui

execute as @e[tag=track-theme-return-interaction,type=interaction] at @s on attacker run function trackselect:track-ui/return-to-room
execute as @e[tag=track-theme-return-interaction,type=interaction] at @s on target run function trackselect:track-ui/return-to-room

execute as @e[tag=track-track-interaction,type=interaction] at @s on attacker run function trackselect:track-ui/select-track/main
execute as @e[tag=track-track-interaction,type=interaction] at @s on target run function trackselect:track-ui/select-track/main
execute as @e[tag=track-track-interaction,type=interaction] run data remove entity @s interaction
execute as @e[tag=track-track-interaction,type=interaction] run data remove entity @s attack

execute as @e[tag=track-track-return-interaction,type=interaction] at @s on attacker as @e[tag=track-main] at @s run function trackselect:track-ui/show-theme-ui
execute as @e[tag=track-track-return-interaction,type=interaction] at @s on target as @e[tag=track-main] at @s run function trackselect:track-ui/show-theme-ui

clone 49 6 185 49 6 185 -134 5 188