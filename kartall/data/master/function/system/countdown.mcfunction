# 카트 정지
tag @n[tag=kartmobil,type=#kartmobil:kartmobil] add kart-stop
scoreboard players set @n[tag=kartmobil,type=#kartmobil:kartmobil] kartboostgauge 0

tag @s remove nbs_singleplay

# 카트 탑승
execute if score @s mastercount matches 8 run effect clear @s minecraft:unluck
execute if score @s mastercount matches 8 run function kartmain:summon/swaphand

# 태그 부여
execute if score @s mastercount matches 8 run tag @s add master_racing


# 관전
execute if score @s mastercount matches 9 as @a[x=-151,y=4,z=285,dx=15,dy=5,dz=24] run tp @s @p[tag=master_racing]
execute if score @s mastercount matches 10 as @a[distance=..5,tag=!master_racing] at @s run function master:system/spectator/startspectate
execute if score @s mastercount matches 10 as @a[distance=..5,tag=!master_racing] at @s run spectate @p[tag=master_racing]

# BGM 재생 (master_license BGM이 있다면 그것을 쓰지만, 없으면 map data에 있는 걸 씀)
# map data에 이미 "master_license"를 넣었으므로, bgm-room이 그걸 처리할 것임.
execute if score @s mastercount matches 10 run function bgm-room:manage-bgm/playbgm with entity @n[tag=gamemain-mapdata-marker] data.track

execute if score @s mastercount matches 15 on vehicle if entity @s[tag=kartsaddle] run function multiplay:s2c-value/data-max-lap/set
execute if score @s mastercount matches 15 on vehicle if entity @s[tag=kartsaddle] run function multiplay:s2c-value/data-trackselect-map/set

execute if score @s mastercount matches 10 run title @s title {translate:"","color":"yellow"}
execute if score @s mastercount matches 10 run title @s subtitle {translate:"준비","color":"yellow"}
execute if score @s mastercount matches 40 run title @s title {translate:"3","color":"yellow"}
execute if score @s mastercount matches 40 run title @s subtitle {translate:"","color":"yellow"}
execute if score @s mastercount matches 60 run title @s title {translate:"2","color":"yellow"}
execute if score @s mastercount matches 80 run title @s title {translate:"1","color":"yellow"}
execute if score @s mastercount matches 100 run title @s title {translate:"시작","color":"yellow"}

execute if score @s mastercount matches 40 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s mastercount matches 60 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s mastercount matches 80 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 0.6674
execute if score @s mastercount matches 100 as @a at @s run playsound minecraft:block.note_block.pling weather @s ~ ~ ~ 1 1.3348

execute if score @s mastercount matches 40 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.675
execute if score @s mastercount matches 60 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.675
execute if score @s mastercount matches 80 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 0.675
execute if score @s mastercount matches 100 as @a at @s run playsound minecraft:block.note_block.chime weather @s ~ ~ ~ 1 1.345


execute if score @s mastercount matches 100 run function timerpack:start
execute if score @s mastercount matches 100 as @n[tag=kartmobil,tag=kart-stop,distance=..10,type=#kartmobil:kartmobil] run function kartmobil:move/boost/startboost/use
