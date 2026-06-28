execute as @a run attribute @s minecraft:entity_interaction_range modifier remove minecraft:0-0-0-0-1700
execute as @a[x=3,y=3,z=93,dx=17,dy=10,dz=12] run attribute @s minecraft:entity_interaction_range modifier add 0-0-0-0-1700 10 add_value


#인터랙션
execute as @e[tag=bgm-theme-interaction,type=interaction] at @s on attacker run function bgm-room:bgm-ui/show-bgm-ui
execute as @e[tag=bgm-theme-interaction,type=interaction] at @s on target run function bgm-room:bgm-ui/show-bgm-ui

execute as @e[tag=bgm-bgm-interaction,type=interaction] at @s on attacker run function bgm-room:bgm-ui/play-bgm
execute as @e[tag=bgm-bgm-interaction,type=interaction] at @s on target run function bgm-room:bgm-ui/play-bgm
#
execute as @e[tag=bgm-bgm-return-interaction,type=interaction] at @s on attacker as @e[tag=bgm-main] at @s run function bgm-room:bgm-ui/show-theme-ui
execute as @e[tag=bgm-bgm-return-interaction,type=interaction] at @s on target as @e[tag=bgm-main] at @s run function bgm-room:bgm-ui/show-theme-ui

#루프 BGM 시스템
execute if score iswork timermain matches 0 if entity @a[scores={bgm-section-target=1..}] run scoreboard players reset * bgm-section-target
execute if score #map trackselect-map-id matches 142 as @a[scores={bgm-section-target=1..}] at @s run function bgm-room:loop-bgm-system/rainbow-road/main

#싱글 자동브금
execute positioned -124 2 211 as @a[dx=10,dy=2,dz=8] run function bgm-room:single-bgm
execute positioned -124 2 233 as @a[dx=10,dy=2,dz=8] run function bgm-room:single-bgm
execute positioned -144 2 231 as @a[dx=10,dy=2,dz=8] run function bgm-room:single-bgm

execute positioned -72 2 241 as @a[dx=10,dy=2,dz=8] run function bgm-room:single-bgm
execute positioned -78 2 202 as @a[dx=10,dy=2,dz=8] run function bgm-room:single-bgm

execute positioned -85 2 231 as @a[dx=10,dy=2,dz=8] run function bgm-room:single-bgm





