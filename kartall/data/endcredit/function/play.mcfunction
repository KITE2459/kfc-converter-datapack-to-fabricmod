scoreboard players set play endcredit 1

summon minecraft:villager -1305.5 5.5 8.0 {NoAI:1b,Invurnerable:1b,Silent:1b,Tags:[creditpoint],Rotation:[90f,0f],active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b,show_icon:0b,ambient:0b}]}
execute positioned -1305.5 5.5 8.0 run forceload add ~ ~

execute as @e[tag=kartsteve] at @s run tp @s -1313.5 5.0 14.5
kill @e[tag=kitemodel]
kill @e[tag=loggamjamodel]
kill @e[tag=sansbike2]
kill @e[tag=ttower]
kill @e[tag=pangchmodel]
kill @e[tag=mandick]
kill @e[tag=pengmodel]
kill @e[tag=eggmobile1]
kill @e[tag=sonic1]
kill @e[tag=anotherone]
kill @e[tag=endtext]
kill @e[tag=creditmap]
