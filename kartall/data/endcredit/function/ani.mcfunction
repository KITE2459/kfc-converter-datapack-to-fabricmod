execute if score play endcredit matches 10..2245 as @a[tag=endcredit] run spectate @e[tag=creditpoint,limit=1] @s
execute if score play endcredit matches 2252.. as @a[tag=endcredit] run spectate @e[tag=creditpoint,limit=1] @s

scoreboard players add play endcredit 1
function endcredit:mapmove
execute as @e[tag=creditmap,tag=movemain] run scoreboard players add @s endcredit 1
execute as @e[tag=creditmap,tag=movemain] at @s run tp @s ~ ~ ~0.7
execute as @e[tag=creditmap,tag=movemain] if score play endcredit matches ..2250 if score @s endcredit matches 60.. on passengers run kill @s
execute as @e[tag=creditmap,tag=movemain] if score play endcredit matches ..2250 if score @s endcredit matches 60.. run kill @s
#tp @s -1305.0 6.5 6.5 90 0

execute if score play endcredit matches 5 positioned -1298.60 7.81 281.81 run forceload add ~ ~
execute if score play endcredit matches 5 run forceload add -1313 79 -1313 -96

execute if score play endcredit matches 5 run kill @e[tag=kite-model-credit,type=#kartmobil:kartmodels]
execute if score play endcredit matches 10 positioned -1314.5 5.00 29.5 run setblock -1314 3 29 minecraft:redstone_block
execute if score play endcredit matches 10 positioned -1314.5 5.00 29.5 run setblock -1304 2 266 minecraft:redstone_block

execute if score play endcredit matches 30 positioned -1314.5 5.00 29.5 run setblock -1314 3 29 minecraft:air
execute if score play endcredit matches 5 positioned -1313.5 5.0 14.5 run function endcredit:summon/endcredit-steve-summon

execute if score play endcredit matches 60 positioned -1315.75 5.00 -13.0 run function endcredit:summon/logmodel
execute if score play endcredit matches 60 positioned -1315.75 5.00 -13.0 run tp @e[tag=logmain,type=#kartmobil:kartmodels] ~ ~ ~ 0 0
execute if score play endcredit matches 60..125 as @e[limit=1,tag=logmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.25 0 0

execute if score play endcredit matches 110 positioned -1316.25 5.00 -13.0 run function endcredit:summon/kitemodel
execute if score play endcredit matches 110..125 as @e[limit=1,tag=kitemain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^1.023 0 0

execute if score play endcredit matches 125 as @e[limit=1,tag=logmain,type=#kartmobil:kartmodels] at @s on passengers run rotate @s ~15 ~
execute if score play endcredit matches 125 as @e[limit=1,tag=kitemain,type=#kartmobil:kartmodels] at @s on passengers run rotate @s ~-15 0

execute if score play endcredit matches 125 as @e[limit=1,tag=logmain,type=#kartmobil:kartmodels] at @s run tp @s ^0.9 ^ ^ ~ 0
execute if score play endcredit matches 125 as @e[limit=1,tag=kitemain,type=#kartmobil:kartmodels] at @s run tp @s ^-0.9 ^ ^ ~ 0
execute if score play endcredit matches 126..180 as @e[limit=1,tag=logmain,type=#kartmobil:kartmodels] at @s run particle explosion ^-.9 ^ ^ 0.3 0.3 0.3 0 10 force @a
execute if score play endcredit matches 126..180 as @e[limit=1,tag=logmain,type=#kartmobil:kartmodels] at @s run particle minecraft:cloud ^-.9 ^ ^ 0 0 0 0.3 10 force @a
execute if score play endcredit matches 126..190 as @e[limit=1,tag=logmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.6 ~ 0
execute if score play endcredit matches 126..190 as @e[limit=1,tag=kitemain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.6 ~ 0

execute if score play endcredit matches 190 run kill @e[tag=kitemodel,type=#kartmobil:kartmodels]
execute if score play endcredit matches 190 run kill @e[tag=loggamjamodel,type=#kartmobil:kartmodels]

execute if score play endcredit matches 160 positioned -1316.0 5.00 -13.0 run function endcredit:summon/towermodel
execute if score play endcredit matches 160..270 as @e[limit=1,tag=tmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.4 ~ 0
execute if score play endcredit matches 270 run kill @e[tag=ttower,type=#kartmobil:kartmodels]

execute if score play endcredit matches 300 positioned -1316.0 5.00 -13.0 run function endcredit:summon/sanskart
execute if score play endcredit matches 300..420 as @e[limit=1,tag=sansbike1,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.3 ~ 0
execute if score play endcredit matches 300..420 as @e[tag=!sansbike1,tag=sansbike2,type=#kartmobil:kartmodels] at @s run rotate @s ~15 0
execute if score play endcredit matches 420 run kill @e[tag=sansbike2,type=#kartmobil:kartmodels]

execute if score play endcredit matches 440 positioned -1313.5 5.00 -13.0 run function endcredit:summon/pangchmodel
execute if score play endcredit matches 440..550 as @e[limit=1,tag=pangchmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.4 ~ 0
execute if score play endcredit matches 503 as @e[limit=1,tag=pangchmain,type=#kartmobil:kartmodels] at @s run particle explosion ^ ^ ^ 0.3 0.3 0.3 0 10 force @a
execute if score play endcredit matches 503 as @e[limit=1,tag=pangchmain,type=#kartmobil:kartmodels] at @s run particle minecraft:cloud ^ ^ ^ 0 0 0 0.7 10 force @a
execute if score play endcredit matches 503 run kill @e[tag=pangchmodel,type=#kartmobil:kartmodels]


execute if score play endcredit matches 580 positioned -1316.0 5.00 -13.0 run function endcredit:summon/gdmodel
execute if score play endcredit matches 580..700 as @e[limit=1,tag=gdmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.3 ~ 0
execute if score play endcredit matches 700 run kill @e[tag=gdmodel,type=#kartmobil:kartmodels]

execute if score play endcredit matches 620 positioned -1316.0 5.00 -13.0 run function endcredit:summon/nkmodel
execute if score play endcredit matches 620..630 as @e[limit=1,tag=nkmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.9 ~ 0
execute if score play endcredit matches 631..640 as @e[limit=1,tag=nkmain,type=#kartmobil:kartmodels] at @s run tp @s ~ ~0.45 ~0.7
execute if score play endcredit matches 641..650 as @e[limit=1,tag=nkmain,type=#kartmobil:kartmodels] at @s run tp @s ~ ~-0.45 ~0.7
execute if score play endcredit matches 651..680 as @e[limit=1,tag=nkmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.9 ~ 0

execute if score play endcredit matches 631..640 as @e[tag=nkmodel,type=#kartmobil:kartmodels] at @s run rotate @s ~ ~-6
execute if score play endcredit matches 641..650 as @e[tag=nkmodel,type=#kartmobil:kartmodels] at @s run rotate @s ~ ~6
execute if score play endcredit matches 680 run kill @e[tag=nkmodel,type=#kartmobil:kartmodels]
execute if score play endcredit matches 631 as @e[tag=nkmodel,limit=1,type=#kartmobil:kartmodels] at @s run particle minecraft:cloud ^-.9 ^ ^ 0 0 0 0.1 10 force @a


execute if score play endcredit matches 700 positioned -1316.0 5.00 -13.0 run function endcredit:summon/pengmodel
execute if score play endcredit matches 700..770 as @e[limit=1,tag=pengmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.25 ~ 0
execute if score play endcredit matches 800 run kill @e[tag=pengmodel,type=#kartmobil:kartmodels]

execute if score play endcredit matches 745 positioned -1314.0 5.00 -13.0 run function endcredit:summon/anothermodel
execute if score play endcredit matches 745..765 as @e[limit=1,tag=anomain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^1.07 ~ 0
execute if score play endcredit matches 745..765 as @e[limit=1,tag=anomain,type=#kartmobil:kartmodels] at @s run particle minecraft:cloud ^-.9 ^ ^ 0 0 0 0.1 10 force @a
execute if score play endcredit matches 766..770 as @e[tag=anotherone,tag=!anomain,type=#kartmobil:kartmodels] at @s run rotate @s ~10 0
execute if score play endcredit matches 771..780 as @e[tag=anotherone,tag=!anomain,type=#kartmobil:kartmodels] at @s run rotate @s ~31 0
execute if score play endcredit matches 766..780 as @e[limit=1,tag=anomain,type=#kartmobil:kartmodels] at @s run tp @s ~-0.2 ~ ~-0.45 ~ 0
execute if score play endcredit matches 771..780 as @e[limit=1,tag=anomain,type=#kartmobil:kartmodels] at @s run tp @s ~0.2 ~ ~-0.8 ~ 0

execute if score play endcredit matches 770..820 as @e[limit=1,tag=anomain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.7 ~ 0

execute if score play endcredit matches 870 run kill @e[tag=anotherone,type=#kartmobil:kartmodels]

execute if score play endcredit matches 770..790 as @e[limit=1,tag=pengmain,type=#kartmobil:kartmodels] on passengers at @s run rotate @s ~30 0
execute if score play endcredit matches 770..790 as @e[limit=1,tag=pengmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^ ~3.0 0
execute if score play endcredit matches 790..820 as @e[limit=1,tag=pengmain,type=#kartmobil:kartmodels] at @s run tp @s ^-0.8 ^-0.8 ^ ~ 0

execute if score play endcredit matches 900 positioned -1316.0 5.00 -13.0 run function endcredit:summon/custom-ranek
execute if score play endcredit matches 901 as @e[tag=ranek,type=#kartmobil:kartmodels] at @s run rotate @s ~180 0
execute if score play endcredit matches 901..1020 as @e[limit=1,tag=ranekmain,type=#kartmobil:kartmodels] at @s run tp @s ~ ~ ~0.3 0 0
execute if score play endcredit matches 1020 run kill @e[tag=ranek,type=#kartmobil:kartmodels]

execute if score play endcredit matches 1100 positioned -1316.0 5.00 -13.0 run function endcredit:summon/daomodel
execute if score play endcredit matches 1100..1220 as @e[limit=1,tag=daomain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.4 0 0
execute if score play endcredit matches 1220 run kill @e[tag=daomodel,type=#kartmobil:kartmodels]

execute if score play endcredit matches 1300 positioned -1316.0 5.00 -13.0 run function endcredit:summon/ecmodel
execute if score play endcredit matches 1301..1340 as @e[limit=1,tag=ecmain,type=#kartmobil:kartmodels] at @s run tp @s ~ ~ ~1 ~ 0
execute if score play endcredit matches 1301 as @e[tag=ecmodel,type=#kartmobil:kartmodels] at @s run rotate @s ~ ~-3
execute if score play endcredit matches 1340 run kill @e[tag=ecmodel,type=#kartmobil:kartmodels]
execute if score play endcredit matches 1301..1340 as @e[limit=1,tag=ecmain,type=#kartmobil:kartmodels] at @s run particle flame ^ ^ ^ 0.3 0.3 0.3 0.2 20 force @a
execute if score play endcredit matches 1301..1340 as @e[limit=1,tag=ecmain,type=#kartmobil:kartmodels] at @s run particle minecraft:cloud ^-.9 ^ ^ 0 0 0 0.3 10 force @a




execute if score play endcredit matches 1380 positioned -1316.0 5.00 -13.0 run function endcredit:summon/misomodel
execute if score play endcredit matches 1380..1480 as @e[limit=1,tag=misomain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.4 ~ 0
execute if score play endcredit matches 1480 run kill @e[tag=misomodel,type=#kartmobil:kartmodels]

execute if score play endcredit matches 1560 positioned -1316.0 5.00 -13.0 run function endcredit:summon/wymodel
execute if score play endcredit matches 1560..1680 as @e[limit=1,tag=wymain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.4 ~ 0
execute if score play endcredit matches 1680 run kill @e[tag=wymodel,type=#kartmobil:kartmodels]

execute if score play endcredit matches 1730 positioned -1316.0 5.00 -13.0 run function endcredit:summon/sidmodel
execute if score play endcredit matches 1730..1820 as @e[limit=1,tag=sidmain,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.6 ~ 0
execute if score play endcredit matches 1731..1820 as @e[tag=sidmodel,limit=1,type=#kartmobil:kartmodels] at @s run particle minecraft:sweep_attack ~ ~ ~ 1 1 1 0.3 10 force @a
execute if score play endcredit matches 1820 run kill @e[tag=sidmodel,type=#kartmobil:kartmodels]



execute if score play endcredit matches 1900 positioned -1316.0 5.00 -13.0 run function endcredit:summon/custom-granny
execute if score play endcredit matches 1900..2120 as @e[limit=1,tag=mandick1,type=#kartmobil:kartmodels] at @s run tp @s ^ ^ ^0.3 ~ 0
execute if score play endcredit matches 2120 run kill @e[tag=mandick,type=#kartmobil:kartmodels]



execute as @e[tag=endtext] at @s if score play endcredit matches ..2280 run tp @s ~ ~0.15 ~
execute as @e[tag=endtext] run scoreboard players add @s endcredit 1
execute as @e[tag=endtext] if score @s endcredit matches 120.. run kill @s

execute if score play endcredit matches 20 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:[{"bold":true,"color":"yellow","italic":false,translate:"Kart"},{"bold":true,"color":"green","italic":false,translate:"R"},{"bold":true,"color":"yellow","italic":false,translate:"ider\n"},{"bold":true,"color":"green","italic":false,translate:"Minecraft"}],shadow:1b}

execute if score play endcredit matches 60 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"개발"},shadow:1b}
execute if score play endcredit matches 90 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"green","italic":false,translate:"LogGamja"},shadow:1b}
execute if score play endcredit matches 90 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"맵 제작 및 메인 개발"},shadow:1b}

execute if score play endcredit matches 115 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"green","italic":false,translate:"KITE2459"},shadow:1b}
execute if score play endcredit matches 115 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"서브 개발 및 서버 지원"},shadow:1b}

execute if score play endcredit matches 140 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"Towercrain"},shadow:1b}
execute if score play endcredit matches 140 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"물리엔진 개발 보조"},shadow:1b}

execute if score play endcredit matches 165 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"N_Devil"},shadow:1b}
execute if score play endcredit matches 165 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"일반인(?)"},shadow:1b}

execute if score play endcredit matches 190 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"white","italic":false,translate:"QUAM12"},shadow:1b}
execute if score play endcredit matches 190 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"개발 및 최적화 조언"},shadow:1b}

execute if score play endcredit matches 215 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"Pangch"},shadow:1b}
execute if score play endcredit matches 215 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"모델링 최적화 도움"},shadow:1b}

execute if score play endcredit matches 240 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"Asdf08"},shadow:1b}
execute if score play endcredit matches 240 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"엔딩 크레딧"},shadow:1b}


execute if score play endcredit matches 330 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"모델링 제공"},shadow:1b}
execute if score play endcredit matches 400 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"green","italic":false,translate:"KITE2459"},shadow:1b}
execute if score play endcredit matches 470 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"green","italic":false,translate:"LogGamja"},shadow:1b}
execute if score play endcredit matches 540 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"_Nekter_"},shadow:1b}
execute if score play endcredit matches 610 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"L_Peng"},shadow:1b}
execute if score play endcredit matches 680 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"ECYCEeeee"},shadow:1b}
execute if score play endcredit matches 750 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"Asdf08 anotherone_yt"},shadow:1b}

execute if score play endcredit matches 820 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2f,2f,2f]},text:{"bold":true,"color":"gold","italic":false,translate:"Sias_kr  tco3307402\nParkmung  resi-le\nGlass_Man01"},shadow:1b}
execute if score play endcredit matches 820 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"gold","italic":false,translate:"골든 라이더 제공"},shadow:1b}
execute if score play endcredit matches 890 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2f,2f,2f]},text:{"bold":true,"color":"white","italic":false,translate:"comet_09  SEOLEETAE\nOnliy  innosday\nZoropic kimunb\ngangryu  bal\nunderbar  yeonmot\nEGG_Gyeran ONOFF"},shadow:1b}
execute if score play endcredit matches 890 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"라이더 제공"},shadow:1b}



execute if score play endcredit matches 980 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"트랙 제작"},shadow:1b}
execute if score play endcredit matches 1040 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"green","italic":false,translate:"LogGamja   KITE2459"},shadow:1b}
execute if score play endcredit matches 1100 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"GhangDhang   _Nekter_"},shadow:1b}
execute if score play endcredit matches 1160 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"L_Peng ECYCEeeee"},shadow:1b}
execute if score play endcredit matches 1170 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"anotherone_yt Asdf08"},shadow:1b}

execute if score play endcredit matches 1220 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"gold","italic":false,translate:"fourgod  tco3307402\nBKGpolar  Glass_Man01\nwhitecow_kim  EYRT77\nwithlight_ HITE_yt\nresi-le ongler_"},shadow:1b}
execute if score play endcredit matches 1220 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"gold","italic":false,translate:"골든 라이더 제공"},shadow:1b}
execute if score play endcredit matches 1280 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2f,2f,2f]},text:{"bold":true,"color":"white","italic":false,translate:"Kati0n  bobjihoo\nDominogames0229  N_Devil\nbanjil2009  Newconner\nKoral Physical\nSEOLEETAE  trl3\nOnliy"},shadow:1b}
execute if score play endcredit matches 1280 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"라이더 제공"},shadow:1b}


#execute if score play endcredit matches 1370 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"건축"}}
#execute if score play endcredit matches 1430 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"white","italic":false,translate:"mingyeol2\nL_Peng\nBKGpolar"}}
#execute if score play endcredit matches 1430 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"맵 인테리어"}}

#execute if score play endcredit matches 1500 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"white","italic":false,translate:"L_Peng"}}
#execute if score play endcredit matches 1500 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"인트로, 엔딩 건축"}}



execute if score play endcredit matches 1380 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"BGM"},shadow:1b}
execute if score play endcredit matches 1440 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"green","italic":false,translate:"LogGamja"},shadow:1b}
execute if score play endcredit matches 1440 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"green","italic":false,translate:"메인 제작자"},shadow:1b}
execute if score play endcredit matches 1500 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"gold","italic":false,translate:"Sodyum\nKITE2459\ntco3307402\nN_Devil"},shadow:1b}
execute if score play endcredit matches 1560 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"aqua","italic":false,translate:"SPECIAL THANKS"},shadow:1b}
execute if score play endcredit matches 1560 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"aqua","italic":false,translate:"WyvernP"},shadow:1b}



execute if score play endcredit matches 1660 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"카트 밸런싱"},shadow:1b}
execute if score play endcredit matches 1670 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-1f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"gold","italic":false,translate:"anotherone_yt\nLogGamja\nECYCEeeee"},shadow:1b}



execute if score play endcredit matches 1760 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"관련 모드 제작"},shadow:1b}
execute if score play endcredit matches 1770 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-1f,0f],scale:[2f,2f,2f]},text:[{"bold":true,"color":"aqua","italic":false,translate:"Sidite"},{"bold":true,"color":"green","italic":false,translate:"\nKite2459  LogGamja"},{"bold":true,"color":"white","italic":false,translate:"\nDominogames0229"}],shadow:1b}


execute if score play endcredit matches 1830 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"일부 캐릭터 저작권"},shadow:1b}

execute if score play endcredit matches 1880 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"white","italic":false,translate:"Sonic The Hedgehog"},shadow:1b}
execute if score play endcredit matches 1880 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"by Team Sonic"},shadow:1b}

execute if score play endcredit matches 1930 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"white","italic":false,translate:"SANS"},shadow:1b}
execute if score play endcredit matches 1930 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"by Toby Fox"},shadow:1b}

execute if score play endcredit matches 1980 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"white","italic":false,translate:"Kirby"},shadow:1b}
execute if score play endcredit matches 1980 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"by HAL Laboratory"},shadow:1b}

execute if score play endcredit matches 2030 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2.5f,2.5f,2.5f]},text:{"bold":true,"color":"white","italic":false,translate:"Dragoon, Vulture"},shadow:1b}
execute if score play endcredit matches 2030 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,-0.25f,0f],scale:[1f,1f,1f]},text:{"bold":true,"color":"white","italic":false,translate:"by Blizzard Entertainment"},shadow:1b}

execute if score play endcredit matches 2120 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:[{"bold":true,"color":"gold","italic":false,translate:"TEAM MC"},{"bold":true,"color":"blue","italic":false,translate:"R"},{"bold":true,"color":"gold","italic":false,translate:"ider"}],shadow:1b}

#execute if score play endcredit matches 2250 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[3f,3f,3f]},text:{"bold":true,"color":"green","italic":false,translate:"Thanks For Playing"}}
#execute if score play endcredit matches 2255 run summon text_display -1314.51 4.00 0.5 {teleport_duration:3,Rotation:[-90F,0F],Tags:["endtext"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2f,2f,2f]},text:[{"bold":true,"color":"yellow","italic":false,translate:"Kart"},{"bold":true,"color":"green","italic":false,translate:"R"},{"bold":true,"color":"yellow","italic":false,translate:"ider"},{"bold":true,"color":"yellow","italic":false,translate:" Forever!"}]}



execute as @e[tag=kartsteve,type=#kartmobil:kartmodels] at @s if score play endcredit matches 2150..2309 run tp @s ~ ~ ~-0.75 ~ 0

execute if score play endcredit matches 2250 as @e[tag=creditpoint,type=villager] at @s run kill @s
execute if score play endcredit matches 2250 run summon minecraft:villager -1297.63 6.75 280.78 {NoAI:1b,Invurnerable:1b,Silent:1b,Tags:[creditpoint],Rotation:[90f,0f],active_effects:[{id:"minecraft:invisibility",amplifier:1,duration:999999,show_particles:0b,show_icon:0b,ambient:0b}]}

execute if score play endcredit matches 2250 run tp @a[tag=endcredit] -1297.63 6.75 280.78 145.73 24.74
execute if score play endcredit matches 2250 run tp @e[tag=creditpoint,type=villager] -1297.63 6.75 280.78 145.73 24.74
execute if score play endcredit matches 2251..2379 as @e[tag=creditpoint,type=villager] at @s run tp @s ^ ^ ^0.01

execute if score play endcredit matches 2370 as @e[tag=kartsteve,type=#kartmobil:kartmodels] at @s run kill @e[distance=..1]
execute if score play endcredit matches 2379 as @e[tag=creditmap] run kill @s

execute if score play endcredit matches 2380 run time set day


execute if score play endcredit matches 2380 run gamemode adventure @a[tag=endcredit]
execute if score play endcredit matches 2380 run tp @a[tag=endcredit] -118.5 4.00 215.5 0 0
execute if score play endcredit matches 2380 run tag @a[tag=endcredit] remove endcredit
execute if score play endcredit matches 2380 run kill @e[tag=kitemodel]
execute if score play endcredit matches 2380 run kill @e[tag=loggamjamodel]
execute if score play endcredit matches 2380 run kill @e[tag=sansbike2]
execute if score play endcredit matches 2380 run kill @e[tag=ttower]
execute if score play endcredit matches 2380 run kill @e[tag=pangchmodel]
execute if score play endcredit matches 2380 run kill @e[tag=mandick]
execute if score play endcredit matches 2380 run kill @e[tag=pengmodel]
execute if score play endcredit matches 2380 run kill @e[tag=eggmobile1]
execute if score play endcredit matches 2380 run kill @e[tag=sonic1]
execute if score play endcredit matches 2380 run kill @e[tag=anotherone]
execute if score play endcredit matches 2380 run kill @e[tag=endtext]
execute if score play endcredit matches 2380 run kill @e[tag=creditpoint]
execute if score play endcredit matches 2380 run kill @e[tag=gdmain]
execute if score play endcredit matches 2380 run kill @e[tag=nkmain]
execute if score play endcredit matches 2380 run kill @e[tag=sidmain]
execute if score play endcredit matches 2380 run kill @e[tag=ecmain]
execute if score play endcredit matches 2380 run kill @e[tag=misomain]
execute if score play endcredit matches 2380 run kill @e[tag=ranexmain]
execute if score play endcredit matches 2380 run kill @e[tag=wymain]
execute if score play endcredit matches 2380 run kill @e[tag=anomain]
execute if score play endcredit matches 2380 run kill @e[tag=pangchmain]
execute if score play endcredit matches 2380 run kill @e[tag=kartsteve] 
execute if score play endcredit matches 2380 run kill @e[tag=ending-thumnail]

execute if score play endcredit matches 2378 positioned -1314.5 5.00 29.5 run setblock -1304 2 266 minecraft:air
execute if score play endcredit matches 2380 run forceload remove -1313 79 -1313 -96
execute if score play endcredit matches 2380 positioned -1298.60 7.81 281.81 run forceload remove ~ ~

execute if score play endcredit matches 2380 run function endcredit:clear

execute if score play endcredit matches 2380 run scoreboard players set play endcredit 0