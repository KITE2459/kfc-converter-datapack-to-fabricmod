scoreboard players add @s cutscene 1

execute if score @s cutscene matches 10.. run function cutscene:cutscene/spectate

summon area_effect_cloud ^ ^ ^-2 {CustomNameVisible:1b,Duration:0,CustomName:{"color":"red",translate:"F5를 눌러 1인칭 화면으로 시청해주세요"},Particle:{type:"block",block_state:"minecraft:air"}}

effect give @s minecraft:invisibility 20 1 true

execute if score @s cutscene matches 2 positioned 158 4 -713 run forceload add ~ ~
execute if score @s cutscene matches 2 run tp @s 154.88 5.36 -708.23 218.10 16.35
execute if score @s cutscene matches 2 run summon villager 154.88 5.36 -708.23 {Rotation:[218.10F, 16.35F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 6 run function cutscene:cutscene/spectate
execute if score @s cutscene matches 6 positioned 158 4 -713 run function walk:summon
execute if score @s cutscene matches 6 positioned 158 4 -713 run function walk:start_animation

execute if score @s cutscene matches 102 run function end:play
execute if score @s cutscene matches 102 run function creditmusic:play

#1
execute if score @s cutscene matches 130 run kill @e[tag=camera]
execute if score @s cutscene matches 131 positioned 158 4 -713 run kill @e[distance=..2]
execute if score @s cutscene matches 131 run tp @s 138.26 6.13 -758.39 -38.10 31.50
execute if score @s cutscene matches 131 run summon villager 138.26 6.13 -758.39 {Rotation:[-38F, 30.60F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 131 run tp @e[tag=camera] 138.26 6.13 -758.39 -38.10 31.50
execute if score @s cutscene matches 131 run tellraw @s "마침내, 마지막 카트 대회가 열리는 그날이 찾아왔다."
execute if score @s cutscene matches 131 positioned 367.53 7.28 -423.88 run forceload add ~ ~
execute if score @s cutscene matches 131..300 as @e[tag=camera] at @s run tp @s ^ ^ ^0.01
#2
execute if score @s cutscene matches 300 run kill @e[tag=camera]
execute if score @s cutscene matches 300 run tp @s 367.53 7.28 -423.88 -166.80 34.20
execute if score @s cutscene matches 302 run summon villager 367.53 7.28 -423.88 {Rotation:[-165.60F, 30.60F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 302 run tp @e[tag=camera] 367.53 7.28 -423.88 -166.80 34.20
execute if score @s cutscene matches 302 unless score clear-data licensestage matches 12.. run tellraw @s "아무리 연습을 해도 높은 라이센스를 획득할 실력은 되지 못했지만, 그건 그리 중요하지 않았고"
execute if score @s cutscene matches 302 if score clear-data licensestage matches 12..16 run tellraw @s "그는 연습 트랙을 타며 L1 라이센스까지 획득할 정도의 실력으로 성장했고"
execute if score @s cutscene matches 302 if score clear-data licensestage matches 17.. run tellraw @s "그는 연습 트랙을 타며 PRO 라이센스까지 획득할 정도의 실력으로 성장했고"
execute if score @s cutscene matches 302..500 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^0.01
#3  
execute if score @s cutscene matches 500 run kill @e[tag=camera]
execute if score @s cutscene matches 500 run tp @s 122.53 18.29 -879.20 317.85 33.90
execute if score @s cutscene matches 500 positioned 367.53 7.28 -423.88 run forceload remove ~ ~
execute if score @s cutscene matches 503 run summon villager 122.53 18.29 -879.20 {Rotation:[317.85F, 33.90F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 503 run tp @e[tag=camera] 122.53 18.29 -879.20 317.85 33.90
execute if score @s cutscene matches 503 run tellraw @s "수없이 많은 트랙을 달리며 그 길을 자신의 것으로 만들었다."
execute if score @s cutscene matches 503..700 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^-0.011
#4
execute if score @s cutscene matches 701 run gamemode creative @s
execute if score @s cutscene matches 701 run tp @e[tag=camera] 137.30 4.96 -755.92 -83.55 22.20
execute if score @s cutscene matches 701 run tellraw @s "이제 그에게 두려울 것은 없다."
execute if score @s cutscene matches 701..800 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^-0.01 ^ ^
#3 2 1 출발
execute if score @s cutscene matches 800 run tp @e[tag=camera] 143.51 5.69 -755.06 89.25 50.85

execute if score @s cutscene matches 800 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s cutscene matches 800 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.6674

execute if score @s cutscene matches 820 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s cutscene matches 820 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.6674

execute if score @s cutscene matches 840 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s cutscene matches 840 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.6674

execute if score @s cutscene matches 860 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.3348
execute if score @s cutscene matches 860 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 1.3348

execute if score @s cutscene matches 860 run kill @e[tag=camera]
execute if score @s cutscene matches 860 run tp @s 143.63 -2.00 -754.30 169.50 90.00

#5
execute if score @s cutscene matches 900 run kill @e[tag=camera]
execute if score @s cutscene matches 901 run summon villager 271.46 11.12 -801.76 {Rotation:[360.84F, 11.10F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 901 run tp @e[tag=camera] 271.46 11.12 -801.76 360.84 11.10
execute if score @s cutscene matches 901..1000 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^-0.01


#오두막집
execute if score @s cutscene matches 1000 positioned 94.0 4 -576.0 run forceload add ~ ~
execute if score @s cutscene matches 1001 positioned 94.0 4 -576.0 run kill @e[distance=..2,type=!player]
#execute if score @s cutscene matches 1001 positioned 94.0 4 -576.0 run function endingwalk:summon
execute if score @s cutscene matches 1001 positioned 94.0 4 -576.0 run function stevemo:cutscenestart
execute if score @s cutscene matches 1001 positioned 94.0 4 -576.0 as @e[distance=..2,type=!player] at @s run tp @s ~ ~ ~ ~180 ~

execute if score @s cutscene matches 1001 run kill @e[tag=camera]
execute if score @s cutscene matches 1001 run tp @s 94.00 4.90 -587.39 -180.00 0.45
execute if score @s cutscene matches 1002 run summon villager 94.00 4.90 -587.39 {Rotation:[-180F, 0F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 1002 run tp @e[tag=camera] 94.00 4.90 -587.39 -180.00 0.45
execute if score @s cutscene matches 1002..1060 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^-0.015
execute if score @s cutscene matches 1061..1120 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^-0.03
execute if score @s cutscene matches 1121..1200 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^-0.06
execute if score @s cutscene matches 1201..1320 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^-0.1

execute if score @s cutscene matches 1062 run tellraw @s "어쩌면, 찬란했던 카트의 시대는 영원히 돌아오지 않을지도 모른다."

execute if score @s cutscene matches 1220 run tellraw @s "하지만, 이것 하나만은 확신할 수 있다."

execute if score @s cutscene matches 1320 as @e[tag=camera] at @s run tp @s ~ ~ ~ -180 30
#execute if score @s cutscene matches 1320 run function endingwalk:start_animation 
#카트에 탑승함
execute if score @s cutscene matches 1420 positioned 94.0 4 -576.0 run kill @e[distance=..2,type=!player]


execute if score @s cutscene matches 1420 run kill @e[tag=camera]
execute if score @s cutscene matches 1420..1540 run tp @s 93.55 -2.00 -571.70 361.50 90.00
execute if score @s cutscene matches 1420 run playsound minecraft:entity.bat.takeoff weather @a
execute if score @s cutscene matches 1420 run function endandstart:play


#6
execute if score @s cutscene matches 1480 run tellraw @s "나는 카트라이더다."

execute if score @s cutscene matches 1540 run setblock -102 5 216 minecraft:oak_button[face=wall,facing=east,powered=false]
execute if score @s cutscene matches 1540 run setblock -102 5 212 minecraft:oak_button[face=wall,facing=east,powered=false]
execute if score @s cutscene matches 1540 run function project:remove
execute if score @s cutscene matches 1540 run gamemode adventure @s

execute if score @s cutscene matches 1540 run kill @e[tag=camera]
execute if score @s cutscene matches 1540 run tp @s -139 4 198 0 0
execute if score @s cutscene matches 1540 unless score play endcredit matches 1.. run function endcredit:start
execute if score @s cutscene matches 1540 run scoreboard players reset @s cutscene
