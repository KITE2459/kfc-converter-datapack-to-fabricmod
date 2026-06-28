tag @a remove trackselect-inroom
scoreboard players reset #player-count trackselect-map-id

execute as @a[scores={trackselect-game-id=1..},x=34,y=-64,z=169,dx=21,dy=384,dz=21] at @s run function trackselect:track-ui/player
execute as @a[tag=!trackselect-inroom] run attribute @s minecraft:entity_interaction_range modifier remove minecraft:0-0-0-0-1699

#킥
execute as @a[scores={trackselect-game-id=1..},tag=!trackselect-inroom] at @s run function trackselect:track-ui/return-to-room
execute as @a[x=34,y=4,z=169,dx=21,dy=29,dz=21,tag=!op] unless score @s trackselect-game-id matches 1.. at @s run function gamemain:noleftplayer/kickleftplayer

bossbar set trackselect players @a[scores={trackselect-game-id=1..}]

scoreboard players add #forceloadextend-timer kartmain 1
execute if score #forceloadextend-timer kartmain matches 200.. run function trackselect:forceloadextend-detect/detect