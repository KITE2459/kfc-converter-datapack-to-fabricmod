#execute as @a run attribute @s minecraft:entity_interaction_range modifier remove minecraft:0-0-0-0-1700
#execute as @a[x=3,y=3,z=93,dx=17,dy=10,dz=12] run attribute @s minecraft:entity_interaction_range modifier add 0-0-0-0-1700 10 add_value

#function garage:fast-garage/
execute positioned -158 5 -298 as @e[tag=fast-garage-interaction,distance=..64,type=interaction] at @s on attacker run function garage:fast-garage/getkart
execute positioned -158 5 -298 as @e[tag=fast-garage-interaction,distance=..64,type=interaction] at @s on target run function garage:fast-garage/getkart

execute positioned -158 5 -298 as @e[tag=garage-return-interaction,distance=..64,type=interaction] at @s on attacker run function garage:fast-garage/exit
execute positioned -158 5 -298 as @e[tag=garage-return-interaction,distance=..64,type=interaction] at @s on target run function garage:fast-garage/exit

execute positioned -158 5 -298 unless entity @a[x=-216,y=4,z=-304,dx=116,dy=8,dz=10] if entity @e[tag=fast-garage-marker,distance=..64,limit=1,type=marker] run function garage:fast-garage/remove-all-ui

scoreboard players add #garage-in-text-time garage-time 1
execute if score #garage-in-text-time garage-time matches 10 unless entity @a[x=-216,y=4,z=-304,dx=116,dy=8,dz=33] positioned -15 0 154 run data modify entity @e[tag=multi-garage-in-text,limit=1,distance=..2,type=text_display] text set value {"translate":"","color":"red"}
execute if score #garage-in-text-time garage-time matches 10 if entity @a[x=-216,y=4,z=-304,dx=116,dy=8,dz=33] positioned -15 0 154 run data modify entity @e[tag=multi-garage-in-text,limit=1,distance=..2,type=text_display] text set value {"translate":"차고 사용 중입니다. 그래도 시작할까요?","color":"red"}
execute if score #garage-in-text-time garage-time matches 10 run scoreboard players set #garage-in-text-time garage-time 0

#execute as @e[tag=bgm-bgm-return-interaction,type=interaction] at @s on attacker as @e[tag=bgm-main] at @s run function garage:fast-garage/show-theme-ui
#execute as @e[tag=bgm-bgm-return-interaction,type=interaction] at @s on target as @e[tag=bgm-main] at @s run function garage:fast-garage/show-theme-ui

