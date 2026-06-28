tp @p -80 4 236 -90 0

execute if entity @a[scores={dev-count=1..}] at @s run playsound minecraft:block.anvil.place neutral @a ~ ~ ~ 1 1
execute if entity @a[scores={dev-count=1..}] run tellraw @s {translate:"이미 경기가 진행 중입니다!","color":"red"}
execute if entity @a[scores={dev-count=1..}] run return 1


$scoreboard players set #dev dev-count $(devnumber)
$scoreboard players set @n[tag=dev-text] dev-count $(devnumber)

execute as @n[tag=trackselect-devbattle-record] run function devbattle:system/devrecord/changerecordtext with entity @n[tag=trackselect-devbattle-marker] data.track

playsound minecraft:entity.experience_orb.pickup weather @a

#제작자 모델
execute positioned -73 5 236 run kill @e[distance=..0.1,type=#kartmobil:kartmodels,tag=!devbattle-devname]
execute positioned -73 5 236 run function devbattle:system/dev-model/summon-dev-model
execute positioned -73 5 236 as @e[distance=..0.1,type=#kartmobil:kartmodels,tag=!devbattle-devname] at @s run tp @s ~ ~ ~ ~90 ~

tag @n[tag=dev-text] add dev-text-temp
data modify entity @e[tag=devbattle-devname,limit=1] text set value [{translate:"vs ","color":"yellow"},{"interpret":true,"entity":"@n[tag=dev-text-temp]","nbt":"text.translate","color":"aqua"}]
tag @e[tag=dev-text-temp] remove dev-text-temp