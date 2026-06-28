scoreboard players add @s cutscene 1

effect give @s minecraft:invisibility infinite 1 true

summon area_effect_cloud ^ ^ ^-2 {CustomNameVisible:1b,Duration:-16,CustomName:{"color":"red",translate:"F5를 눌러 1인칭 화면으로 시청해주세요"},Particle:{type:"block",block_state:"minecraft:air"}}

function cutscene:cutscene/spectate
execute if score @s cutscene matches 2 run forceload add 336 -384
execute if score @s cutscene matches 2 run function introbgm:play
execute if score @s cutscene matches 2 run summon villager 202 9 -404 {Rotation:[120.30F, 21.90F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 2 positioned 197 5 -409 run function walk:summon
execute if score @s cutscene matches 2 positioned 197 5 -409 run function walk:start_animation
execute if score @s cutscene matches 2 positioned 197 5 -409 as @e[distance=..2] at @s run tp @s ~ ~ ~ ~-90 ~
execute if score @s cutscene matches 2 run tp @e[tag=camera] 200.60 7.62 -402.62 120.30 21.90
execute if score @s cutscene matches 2 run function cutscene:cutscene/spectate
execute if score @s cutscene matches 2..130 as @e[tag=camera] at @s run tp @s ^ ^ ^-0.1
#1
execute if score @s cutscene matches 131 run gamemode creative @s
execute if score @s cutscene matches 131 positioned 197 5 -409 run kill @e[distance=..2]
#execute if score @s cutscene matches 131 run tp @e[tag=camera] 202.93 5.115 -243.56 22.05 19.50
execute if score @s cutscene matches 131 run tp @e[tag=camera] 204.60 5.12 -242.30 40.59 19.99
execute if score @s cutscene matches 131 run tellraw @s "오래 전, 카트 레이싱으로 유명한 한 마을이 있었습니다."
execute if score @s cutscene matches 131..300 as @e[tag=camera] at @s run tp @s ^ ^ ^0.01
execute if score @s cutscene matches 131 run function cutscene:cutscene/spectate
#2
execute if score @s cutscene matches 300 run gamemode creative @s
execute if score @s cutscene matches 300 run kill @e[tag=camera]
execute if score @s cutscene matches 300 run tp @s 347.33 7.375 -371.53 -165.60 30.60
execute if score @s cutscene matches 301 run summon villager 347.33 7.375 -371.53 {Rotation:[-165.60F, 30.60F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 301 run function cutscene:cutscene/spectate
execute if score @s cutscene matches 301 run tp @e[tag=camera] 347.33 7.375 -371.53 -165.60 30.60
execute if score @s cutscene matches 301 run tellraw @s "카트 연습장에는 매일같이 실력을 갈고 닦는 라이더가 가득했습니다."
execute if score @s cutscene matches 301 run setblock 348 -1 -381 minecraft:redstone_block
execute if score @s cutscene matches 301..500 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^0.01
#3  
execute if score @s cutscene matches 500 run gamemode creative @s
execute if score @s cutscene matches 499 positioned 348 4.00 -385 run kill @e[distance=..4]
execute if score @s cutscene matches 500 run kill @e[tag=camera]
execute if score @s cutscene matches 500 run tp @s 326.50 11.375 -246.50 0.00 5
execute if score @s cutscene matches 501 run summon villager 326.50 11.375 -246.50 {Rotation:[0F, 5F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 501 run function cutscene:cutscene/spectate
execute if score @s cutscene matches 501 run tp @e[tag=camera] 326.50 11.375 -246.50 0.00 5
execute if score @s cutscene matches 501 run tellraw @s "한때 카트 세계를 주름잡던 '카트라이더'도 이곳 출신이었다고 합니다."
execute if score @s cutscene matches 500..700 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^-0.011


#4 - 하지만...
execute if score @s cutscene matches 600 as @e[tag=opening_text] run data modify entity @s text_opacity set value 4b

execute if score @s cutscene matches 702 as @e[tag=opening_text] run data modify entity @s text_opacity set value 4b
execute if score @s cutscene matches 703 as @e[tag=opening_text] run data modify entity @s text_opacity set value 127b
execute if score @s cutscene matches 703 as @e[tag=opening_text] run data modify entity @s start_interpolation set value 0

execute if score @s cutscene matches 733 as @e[tag=opening_text] run data modify entity @s text_opacity set value -127b
execute if score @s cutscene matches 734 as @e[tag=opening_text] run data modify entity @s text_opacity set value -1b
execute if score @s cutscene matches 734 as @e[tag=opening_text] run data modify entity @s start_interpolation set value 0

execute if score @s cutscene matches 701 run gamemode creative @s
execute if score @s cutscene matches 701 run tp @e[tag=camera] 326 3.875 -242 0 0
execute if score @s cutscene matches 701 run function cutscene:cutscene/spectate
#5
execute if score @s cutscene matches 900 run gamemode creative @s
execute if score @s cutscene matches 900 run kill @e[tag=camera]
execute if score @s cutscene matches 901 run summon villager 398.50 4.375 -313.50 {Rotation:[180.00F, 12.75F],Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 901 run function cutscene:cutscene/spectate
execute if score @s cutscene matches 901 run tp @e[tag=camera] 398.50 4.375 -313.50 180.00 12.75
execute if score @s cutscene matches 901 run tellraw @s "시간이 흐르자, 새로운 이동 수단이 수도 없이 만들어졌습니다."
execute if score @s cutscene matches 901..1100 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^-0.1 ^ ^-0.01
#6

execute if score @s cutscene matches 1101 run gamemode creative @s
execute if score @s cutscene matches 1101 run tp @e[tag=camera] 414.23 5.495 -332.17 364.50 20.70
execute if score @s cutscene matches 1101 run tellraw @s "바퀴 달린 탈것은 재미없어졌고, 카트는 매니아만 찾는 스포츠가 되었습니다."
execute if score @s cutscene matches 1101 run function cutscene:cutscene/spectate
execute if score @s cutscene matches 1101..1300 as @e[tag=camera] at @s rotated ~ 0 run tp @s ^ ^ ^-0.01
#7
execute if score @s cutscene matches 1200 as @e[tag=opening_text] run data modify entity @s text_opacity set value 4b

execute if score @s cutscene matches 1302 as @e[tag=opening_text] run data modify entity @s text_opacity set value 4b
execute if score @s cutscene matches 1303 as @e[tag=opening_text] run data modify entity @s text_opacity set value 127b
execute if score @s cutscene matches 1303 as @e[tag=opening_text] run data modify entity @s start_interpolation set value 0

execute if score @s cutscene matches 1333 as @e[tag=opening_text] run data modify entity @s text_opacity set value -127b
execute if score @s cutscene matches 1334 as @e[tag=opening_text] run data modify entity @s text_opacity set value -1b
execute if score @s cutscene matches 1334 as @e[tag=opening_text] run data modify entity @s start_interpolation set value 0

execute if score @s cutscene matches 1301 run gamemode creative @s
execute if score @s cutscene matches 1301 run tp @s 326 3.875 -235.8 0 0
execute if score @s cutscene matches 1301 run tp @e[tag=camera] 326 3.875 -235.8 0 0
execute if score @s cutscene matches 1301 run function cutscene:cutscene/spectate
#8
execute if score @s cutscene matches 1500 run kill @e[tag=camera]
execute if score @s cutscene matches 1500 positioned 191 5 -409 run function project:summon
execute if score @s cutscene matches 1500 positioned 191 5 -409 as @e[distance=..2] at @s run tp @s ~ ~ ~ ~-90 ~
execute if score @s cutscene matches 1501 run summon villager 205.09 7.375 -400.68 {Tags:["camera"],NoGravity:1b,Silent:1b,Invulnerable:1b,NoAI:1b,active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b}]}
execute if score @s cutscene matches 1501 run tp @e[tag=camera] 200.60 8.62 -402.62 120.30 21.90
execute if score @s cutscene matches 1501 run tellraw @s "그리고 여기, 전설적인 '카트라이더'를 꿈꾸던 한 사람이 있습니다."
execute if score @s cutscene matches 1502 run gamemode creative @s
execute if score @s cutscene matches 1503 run function cutscene:cutscene/spectate
execute if score @s cutscene matches 1501..1700 as @e[tag=camera] at @s run tp @s ^ ^ ^0.048
#9
execute if score @s cutscene matches 1701 run function project:start_animation
execute if score @s cutscene matches 1701 run tp @e[tag=camera] 189.70 5.76 -410.23 314.55 26.40
execute if score @s cutscene matches 1701 run tellraw @s "올해 열리는 마지막 카트 대회, 그에게는 이것이 마지막 기회입니다."

execute if score @s cutscene matches 1821 run tp @e[tag=camera] 200.60 7.62 -402.62 120.30 21.90

execute if score @s cutscene matches 1881 run tp @e[tag=camera] 200.60 7.62 -402.62 108.30 -20.25

execute if score @s cutscene matches 2020 run forceload remove 336 -384
execute if score @s cutscene matches 2020 run function project:remove
execute if score @s cutscene matches 2020 positioned 191 5 -409 run kill @e[distance=..2]
execute if score @s cutscene matches 2020 run kill @e[tag=camera]
execute if score @s cutscene matches 2020 run tp @s -139 4 198 0 0
execute if score @s cutscene matches 2020 run gamemode adventure @s
execute if score @s cutscene matches 2020 run effect clear @s
execute if score @s cutscene matches 2020 run scoreboard players reset @s cutscene
