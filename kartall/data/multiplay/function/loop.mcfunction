#만약 게임 끝났는데 플레이어가 있으면
execute unless score #game multi-main matches 1 as @a[tag=kart-multi-player] at @s run function gamemain:noleftplayer/kickschedule
kill @e[tag=multi-text-display,predicate=!kartmobil:ifride,type=minecraft:text_display]

#멀티방
tag @a[tag=multi-hub-ready] remove multi-hub-ready
tag @a[tag=multi-hub-player] remove multi-hub-player
execute unless score #game multi-main matches 1 if entity @a[x=-26,y=7,z=154,dx=22,dy=-8,dz=19] run function multiplay:hub/main
execute unless score #game multi-main matches 1 if entity @a[x=39,y=12,z=181,dx=10,dy=-8,dz=12] run function multiplay:hub/main
execute unless score #game multi-main matches 1 if entity @a[x=-215,y=4,z=-271,dx=115,dy=7,dz=-32] run function multiplay:hub/main

execute unless score #game multi-main matches 1 as @a[tag=!multi-hub-ready,team=!multi-hidenick] run team leave @s
execute unless score #game multi-main matches 1 as @a[team=multi-hidenick,tag=!kart-multi-player] run team leave @s

execute as @a[tag=!multi-hub-player,tag=!kart-multi-player] if items entity @s armor.head leather_helmet run item replace entity @s armor.head with air

#만약 멀티플레이 하는 사람이 있다면
execute if score #game multi-main matches 1 run function multiplay:system/main

scoreboard players enable @a multi-returntohub
execute as @a[scores={multi-returntohub=1..}] run function multiplay:system/returntohub
scoreboard players reset @a[scores={multi-returntohub=1..}] multi-returntohub

#멀티플레이 관전자
bossbar set multi-spectator-ui players @a[tag=multi-spectator]
execute as @a[tag=multi-spectator] at @s run function multiplay:system/spectator/spectatormain

#완주브금 딜레이 멀티대기실 재생
scoreboard players add @a[tag=bgm-delay] bgm-delay 1
execute if entity @a[tag=bgm-delay,limit=1] run execute as @a[x=-31,y=-1,z=154,dx=28, dy=6, dz=19,tag=bgm-delay,scores={bgm-delay=111..}] run function multiplayroom:play
execute if entity @a[tag=bgm-delay,limit=1] run execute as @a[tag=bgm-delay,scores={bgm-delay=111..}] run tag @s remove bgm-delay
execute if entity @a[scores={bgm-delay=111..},limit=1] run execute as @a[scores={bgm-delay=111..}] run scoreboard players reset @s bgm-delay