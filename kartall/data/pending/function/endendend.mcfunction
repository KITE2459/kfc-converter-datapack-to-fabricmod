scoreboard players add #pendcredit pendcredit 1
execute as @a at @s run function singleplayroom:stop
function modscript:end

execute if score #stevewalkon pendcredit matches 1 run function pending:stevewalk/stevewalk

execute if score #pendcredit pendcredit matches 3 positioned -113.50 4.00 217.50 run tp @a[limit=1,sort=nearest] 154.88 5.36 -708.23 218.10 16.35
execute if score #pendcredit pendcredit matches 3 run function pending:pend1/kill
execute if score #pendcredit pendcredit matches 4 positioned 158.50 4.00 -709.50 run function pending:pend1/summonsteve
execute if score #pendcredit pendcredit matches 4 as @e[limit=20,tag=pendsteve0,type=item_display] at @s run rotate @s ~180 ~
execute if score #pendcredit pendcredit matches 4 positioned 154.88 5.36 -708.23 positioned ~ ~1.62 ~ run summon text_display ~ ~ ~ {teleport_duration:0,Rotation:[218.10f,16.35f],Tags:[pendcredit0,pendcredit1],Passengers:[{id:"text_display",text:[{translate:"F5를 눌러 1인칭 화면으로 시청해주세요","color":"#ff0000"}],text_opacity:255,Tags:[pendcredit0,pendcredit2],Rotation:[218.10f,16.35f],background:-16777216,line_width:210,transformation:[-1f,0f,0f,0f,0f,1f,0f,0f,0f,0f,-1f,-0.535f,0f,0f,0f,1f]}]}
execute if score #pendcredit pendcredit matches 6..999 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..4,type=text_display] run function pending:spe
execute if score #pendcredit pendcredit matches 7 positioned 141.0 4 -755.0 run function pending:pend1/summonwpwkrwk
execute if score #pendcredit pendcredit matches 6 run scoreboard players set #stevewalkon pendcredit 1
execute if score #pendcredit pendcredit matches 6..78 as @e[limit=1,tag=pendsteve20,type=item_display] at @s run tp @s ^ ^ ^.04
execute if score #pendcredit pendcredit matches 79 run scoreboard players set #stevewalk pendcredit 25
execute if score #pendcredit pendcredit matches 100 run scoreboard players set #stevewalkon pendcredit 0
execute if score #pendcredit pendcredit matches 102 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run function end:play

execute if score #pendcredit pendcredit matches 102 positioned 138 6 -758 run kill @e[tag=pendcredit0,type=text_display,distance=..2]
execute if score #pendcredit pendcredit matches 132 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 3
execute if score #pendcredit pendcredit matches 131 positioned 138.26 6.13 -758.39 run tp @e[limit=1,tag=pendcredit1,type=text_display] ~ ~1.62 ~
execute if score #pendcredit pendcredit matches 131 as @e[limit=2,tag=pendcredit0,type=text_display] at @s run rotate @s -38.10 31.50
execute if score #pendcredit pendcredit matches 131 run kill @e[limit=20,tag=pendsteve0,type=item_display]
execute if score #pendcredit pendcredit matches 131 run scoreboard players reset #stevewalk
execute if score #pendcredit pendcredit matches 132 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run tellraw @s "마침내, 마지막 카트 대회가 열리는 그날이 찾아왔다."
execute if score #pendcredit pendcredit matches 131..299 as @e[limit=1,tag=pendcredit1,type=text_display] at @s run tp @s ^ ^ ^.01
execute if score #pendcredit pendcredit matches 131..299 run scoreboard players add #pendpeople pendcredit 1
execute if score #pendcredit pendcredit matches 131..299 run function pending:pend1/tkfkaemf/tkfkaemf
execute if score #pendcredit pendcredit matches 299 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 0
execute if score #pendcredit pendcredit matches 300 run scoreboard players reset #pendpeople pendcredit
 
execute if score #pendcredit pendcredit matches 301 positioned 52.83 7.38 -781.75 run tp @e[limit=1,tag=pendcredit1,type=text_display] ~ ~1.62 ~
execute if score #pendcredit pendcredit matches 302 as @e[limit=2,tag=pendcredit0,type=text_display] at @s run rotate @s 33.83 35.69
execute if score #pendcredit pendcredit matches 302 positioned 59.50 4.00 -782.50 run function pending:pend2/summonsolidsteve
execute if score #pendcredit pendcredit matches 303..308 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run tp @s ^-1 ^ ^
execute if score #pendcredit pendcredit matches 325..480 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s ~.9 0
execute if score #pendcredit pendcredit matches 325..450 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run tp @s ^-.01 ^ ^
execute if score #pendcredit pendcredit matches 300..450 as @e[limit=1,tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run particle minecraft:enchanted_hit ^3 ^1 ^8 .25 .25 .25 1.25 3 force @a
execute if score #pendcredit pendcredit matches 305 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 3
execute if score #pendcredit pendcredit matches 307 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] unless score clear-data licensestage matches 12.. run tellraw @s "아무리 연습을 해도 높은 라이센스를 획득할 실력은 되지 못했지만, 그건 그리 중요하지 않았고"
execute if score #pendcredit pendcredit matches 307 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] if score clear-data licensestage matches 12..16 run tellraw @s "그는 연습 트랙을 타며 L1 라이센스까지 획득할 정도의 실력으로 성장했고"
execute if score #pendcredit pendcredit matches 307 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] if score clear-data licensestage matches 17.. run tellraw @s "그는 연습 트랙을 타며 PRO 라이센스까지 획득할 정도의 실력으로 성장했고"
execute if score #pendcredit pendcredit matches 302..499 as @e[limit=1,tag=pendcredit1,type=text_display] at @s rotated ~ 0 run tp @s ^-.007 ^ ^.007
execute if score #pendcredit pendcredit matches 302..499 run function pending:pend2/solidsteve
execute if score #pendcredit pendcredit matches 302..499 run scoreboard players add #solidsteve pendcredit 1
execute if score #pendcredit pendcredit matches 499 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 0
execute if score #pendcredit pendcredit matches 500 run scoreboard players reset #solidsteve pendcredit

execute if score #pendcredit pendcredit matches 504 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 3
execute if score #pendcredit pendcredit matches 500 run kill @e[tag=psolidsteve0,type=#kartmobil:kartmodels]
execute if score #pendcredit pendcredit matches 500 positioned 122.53 18.29 -879.20 run tp @e[limit=1,tag=pendcredit1,type=text_display] ~ ~1.62 ~
execute if score #pendcredit pendcredit matches 502 as @e[limit=2,tag=pendcredit0,type=text_display] at @s run rotate @s 317.85 33.90
execute if score #pendcredit pendcredit matches 503 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run tellraw @s "수없이 많은 트랙을 달리며 그 길을 자신의 것으로 만들었다."
execute if score #pendcredit pendcredit matches 503..700 as @e[limit=1,tag=pendcredit1,type=text_display] at @s rotated ~ 0 run tp @s ^-.005 ^ ^-.011
execute if score #pendcredit pendcredit matches 503 positioned 156.66 18.00 -861.58 run function pending:pend3/summonsolidsteve
execute if score #pendcredit pendcredit matches 503 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 12.0 0
execute if score #pendcredit pendcredit matches 504 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] run data modify entity @s teleport_duration set value 4
execute if score #pendcredit pendcredit matches 507 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 155.50 18.20 -856.16
execute if score #pendcredit pendcredit matches 507 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 10.0 0
execute if score #pendcredit pendcredit matches 510 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 154.53 18.20 -850.64
execute if score #pendcredit pendcredit matches 510 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 19.0 0
execute if score #pendcredit pendcredit matches 513 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 152.70 18.20 -845.36
execute if score #pendcredit pendcredit matches 513 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 32.0 0
execute if score #pendcredit pendcredit matches 514 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] run data modify entity @s teleport_duration set value 6
execute if score #pendcredit pendcredit matches 516 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 149.77 18.20 -840.67
execute if score #pendcredit pendcredit matches 516 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 151.5 0
execute if score #pendcredit pendcredit matches 521 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 145.58 18.20 -837.67
execute if score #pendcredit pendcredit matches 521 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 157.9 0
execute if score #pendcredit pendcredit matches 526 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 140.77 18.20 -837.12
execute if score #pendcredit pendcredit matches 526 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s -162.5 0
execute if score #pendcredit pendcredit matches 531 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 136.44 18.20 -838.98
execute if score #pendcredit pendcredit matches 531 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s -154.3 0
execute if score #pendcredit pendcredit matches 536 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 133.63 18.20 -842.57
execute if score #pendcredit pendcredit matches 536 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s -148.9 0
execute if score #pendcredit pendcredit matches 538 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] run data modify entity @s teleport_duration set value 4
execute if score #pendcredit pendcredit matches 539 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 132.22 18.20 -847.04
execute if score #pendcredit pendcredit matches 539 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 162.4 0
execute if score #pendcredit pendcredit matches 542 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 131.63 18.20 -852.00
execute if score #pendcredit pendcredit matches 542 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 173.3 0
execute if score #pendcredit pendcredit matches 545 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 131.26 18.20 -857.39
execute if score #pendcredit pendcredit matches 545 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 176.0 0
execute if score #pendcredit pendcredit matches 548 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 130.60 18.20 -863.15
execute if score #pendcredit pendcredit matches 548 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 173.5 0
execute if score #pendcredit pendcredit matches 551 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 129.38 18.20 -869.18
execute if score #pendcredit pendcredit matches 551 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 119.6 0
execute if score #pendcredit pendcredit matches 554 as @e[tag=psolidsteve102,type=item_display,limit=1] at @s run tp @s 127.05 18.20 -876.30
execute if score #pendcredit pendcredit matches 554 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] at @s run rotate @s 80.9 0
execute if score #pendcredit pendcredit matches 560 as @e[tag=psolidsteve0,type=#kartmobil:kartmodels] run data modify entity @s teleport_duration set value 40
execute if score #pendcredit pendcredit matches 555..700 as @e[tag=psolidsteve102,type=#kartmobil:kartmodels] at @s run tp @s ^-.02 ^-.01 ^.011
execute if score #pendcredit pendcredit matches 554..700 as @e[tag=psolidsteve102,type=#kartmobil:kartmodels] on passengers at @s run rotate @s ~-.15 0
execute if score #pendcredit pendcredit matches 503..700 run function pending:pend3/solidsteve
execute if score #pendcredit pendcredit matches 503..700 run scoreboard players add #solidsteve pendcredit 1
execute if score #pendcredit pendcredit matches 700 run scoreboard players reset #solidsteve pendcredit
execute if score #pendcredit pendcredit matches 703 run kill @e[tag=psolidsteve0,type=#kartmobil:kartmodels]

execute if score #pendcredit pendcredit matches 703 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 0
execute if score #pendcredit pendcredit matches 705 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 3
execute if score #pendcredit pendcredit matches 704 positioned 137.30 4.96 -755.92 run tp @e[limit=1,tag=pendcredit1,type=text_display] ~ ~1.62 ~
execute if score #pendcredit pendcredit matches 704 run function pending:pend4/leg
execute if score #pendcredit pendcredit matches 704 as @e[limit=2,tag=pendcredit0,type=text_display] run rotate @s -83.55 22.20
execute if score #pendcredit pendcredit matches 705 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run tellraw @s "이제 그에게 두려울 것은 없다."
execute if score #pendcredit pendcredit matches 705..870 run function pending:pend4/tkfkaemf
execute if score #pendcredit pendcredit matches 705..870 run scoreboard players add #pendpeople pendcredit 1
execute if score #pendcredit pendcredit matches 765 run scoreboard players set #pendpeople pendcredit 28
execute if score #pendcredit pendcredit matches 816 run scoreboard players set #pendpeople pendcredit 62
execute if score #pendcredit pendcredit matches 834 run scoreboard players set #pendpeople pendcredit 62
execute if score #pendcredit pendcredit matches 704..800 as @e[limit=1,tag=pendcredit1,type=text_display] at @s rotated ~ 0 run tp @s ^-.01 ^ ^
execute if score #pendcredit pendcredit matches 799 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 0
execute if score #pendcredit pendcredit matches 800 positioned 143.51 5.69 -755.06 run tp @e[limit=1,tag=pendcredit1,type=text_display] ~ ~1.62 ~
execute if score #pendcredit pendcredit matches 800 as @e[limit=2,tag=pendcredit0,type=text_display] run rotate @s 89.25 50.85
execute if score #pendcredit pendcredit matches 801 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 .675
execute if score #pendcredit pendcredit matches 801 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 .675
execute if score #pendcredit pendcredit matches 820 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 .675
execute if score #pendcredit pendcredit matches 820 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 .675
execute if score #pendcredit pendcredit matches 840 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 .675
execute if score #pendcredit pendcredit matches 840 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 .675
execute if score #pendcredit pendcredit matches 860 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.3348
execute if score #pendcredit pendcredit matches 860 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..2,type=text_display] run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.3348
execute if score #pendcredit pendcredit matches 861 positioned 143.63 -2.00 -754.30 run tp @e[limit=1,tag=pendcredit1,type=text_display] ~ ~1.62 ~
execute if score #pendcredit pendcredit matches 861 as @e[limit=2,tag=pendcredit0,type=text_display] run rotate @s 169.50 90.00
execute if score #pendcredit pendcredit matches 870 positioned 140.50 4.00 -754.50 run kill @e[tag=pendpeo0,type=#kartmobil:kartmodels,distance=..2]

execute if score #pendcredit pendcredit matches 900 positioned 271.46 11.12 -801.76 run tp @e[limit=1,tag=pendcredit1,type=text_display] ~ ~1.62 ~
execute if score #pendcredit pendcredit matches 900 as @e[limit=2,tag=pendcredit0,type=text_display] run rotate @s 360.84 11.10
execute if score #pendcredit pendcredit matches 901 positioned 271.50 11.00 -799.50 run function pending:pend5/victorystevesummon
execute if score #pendcredit pendcredit matches 905 as @e[limit=2,tag=pendcredit0,type=text_display] run data modify entity @s teleport_duration set value 3
execute if score #pendcredit pendcredit matches 910..1000 as @e[limit=1,tag=pendcredit1,type=text_display] at @s rotated ~ 0 run tp @s ^ ^ ^-.01
#execute if score #pendcredit pendcredit matches 901..1000 run function pending:pend5/victorysteve
#execute if score #pendcredit pendcredit matches 901..1000 run scoreboard players add #victorysteve pendcredit 1
#너네는 나중에
execute if score #pendcredit pendcredit matches 995 as @a at @s if entity @e[limit=1,tag=pendcredit1,distance=..4,type=text_display] run scoreboard players set @s cutscene 990
execute if score #pendcredit pendcredit matches 995 run scoreboard players set ending cutscene 1
execute if score #pendcredit pendcredit matches 995 run function pending:kill

