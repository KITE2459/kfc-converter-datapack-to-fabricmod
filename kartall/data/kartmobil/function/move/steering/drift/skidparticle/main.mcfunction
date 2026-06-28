scoreboard players set #water-skid kartdrift 0
execute if entity @s[tag=allow-buoyancy] positioned ~ ~ ~ if function kartmobil:is-water run scoreboard players set #water-skid kartdrift 1
execute if entity @s[tag=allow-buoyancy] positioned ~ ~-0.5 ~ if function kartmobil:is-water run scoreboard players set #water-skid kartdrift 1
execute if entity @s[tag=allow-buoyancy] positioned ~ ~-1 ~ if function kartmobil:is-water run scoreboard players set #water-skid kartdrift 1

#소리
playsound minecraft:entity.rabbit.death neutral @a[tag=kart-listener] ~ ~ ~ 0.5 0.75 0.5
playsound minecraft:entity.axolotl.hurt neutral @a[tag=kart-listener] ~ ~ ~ 0.15 1.75 0.15

#파티클
# scoreboard players operation #kartdriftparticle kartmain = @s kartmove

tag @a[tag=kart-listener] add kartskidshow

execute store result storage minecraft:kartmain kartdriftparticle2 float 0.0005 run scoreboard players get @s kartmove
execute store result score #kart-skid-distance kartdrift run data get storage minecraft:kartmain kartdriftparticle2

scoreboard players set #rally-frontskid kartdrift 0
execute unless score @s kart-engine matches 7 if score #kartspeed kartmove matches 25.. run function kartmobil:move/steering/drift/skidparticle/skid-tree/main

tag @a[tag=kartskidshow] remove kartskidshow