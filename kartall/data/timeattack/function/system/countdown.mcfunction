#카트 정지
tag @n[tag=kartmobil,distance=..10,type=#kartmobil:kartmobil] add kart-stop
scoreboard players set @n[tag=kartmobil,distance=..10,type=#kartmobil:kartmobil] kartboostgauge 0

#카트 탑승
execute if score @s timecount matches 8 run effect clear @s minecraft:unluck
execute if score @s timecount matches 8 run function trackselect:read-track-data/movetomap with entity @n[tag=trackselect-timeattack-marker] data.track
execute if score @s timecount matches 8 run function kartmain:summon/swaphand
execute if score @s timecount matches 8 run function bgm-room:manage-bgm/stopbgm

execute if score @s timecount matches 10 run function bgm-room:manage-bgm/playbgm with entity @n[tag=trackselect-timeattack-marker] data.track

execute if score @s timecount matches 15 on vehicle if entity @s[tag=kartsaddle] run function multiplay:s2c-value/data-max-lap/set
execute if score @s timecount matches 15 on vehicle if entity @s[tag=kartsaddle] run function multiplay:s2c-value/data-current-lap/set-1
execute if score @s timecount matches 15 on vehicle if entity @s[tag=kartsaddle] run function multiplay:s2c-value/data-trackselect-map/set

execute if score @s timecount matches 10 run title @s title {translate:"","color":"yellow"}
execute if score @s timecount matches 10 run title @s subtitle {translate:"준비","color":"yellow"}
execute if score @s timecount matches 40 run title @s title {translate:"3","color":"yellow"}
execute if score @s timecount matches 40 run title @s subtitle {translate:"","color":"yellow"}
execute if score @s timecount matches 60 run title @s title {translate:"2","color":"yellow"}
execute if score @s timecount matches 80 run title @s title {translate:"1","color":"yellow"}
execute if score @s timecount matches 100 run title @s title {translate:"시작","color":"yellow"}

execute if score @s timecount matches 40 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s timecount matches 60 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s timecount matches 80 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s timecount matches 100 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.3348

execute if score @s timecount matches 40 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.675
execute if score @s timecount matches 60 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.675
execute if score @s timecount matches 80 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.675
execute if score @s timecount matches 100 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 1.345


execute if score @s timecount matches 100 run function timerpack:start
execute if score @s timecount matches 100 as @n[tag=kartmobil,tag=kart-stop,distance=..10,type=#kartmobil:kartmobil] run function kartmobil:move/boost/startboost/use

#@e[tag=kartmobil,type=#kartmobil:kartmobil]