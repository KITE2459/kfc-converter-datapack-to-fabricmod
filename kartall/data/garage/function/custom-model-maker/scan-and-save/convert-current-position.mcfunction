execute if block ~ ~ ~ #kartmobil:ignoreblock run return 1

execute align xyz positioned ~0.5 ~0.5 ~0.5 run summon marker ~ ~ ~ {Tags:["custom-model-position"]}

execute store result score #custom-model-x garage-time run data get entity @n[tag=custom-model-position,type=marker] Pos[0]
execute store result score #custom-model-y garage-time run data get entity @n[tag=custom-model-position,type=marker] Pos[1]
execute store result score #custom-model-z garage-time run data get entity @n[tag=custom-model-position,type=marker] Pos[2]


scoreboard players set #custom-model-x-center garage-time 5258
scoreboard players set #custom-model-y-center garage-time 4
scoreboard players set #custom-model-z-center garage-time 4500

scoreboard players operation #custom-model-x garage-time -= #custom-model-x-center garage-time
scoreboard players operation #custom-model-y garage-time -= #custom-model-y-center garage-time
scoreboard players operation #custom-model-z garage-time -= #custom-model-z-center garage-time

kill @e[tag=custom-model-position]

summon item_display 5248 5 4500 {Tags:["custom-model-garage-temp"]}
data modify entity @n[tag=custom-model-garage-temp] transformation.scale set value [0.2f, 0.2f, 0.2f]

scoreboard players operation #custom-model-y garage-time *= #kart2 kartmain
scoreboard players add #custom-model-y garage-time 1
execute store result entity @n[tag=custom-model-garage-temp] transformation.translation[0] float 0.2 run scoreboard players get #custom-model-x garage-time
execute store result entity @n[tag=custom-model-garage-temp] transformation.translation[1] float 0.1 run scoreboard players get #custom-model-y garage-time
execute store result entity @n[tag=custom-model-garage-temp] transformation.translation[2] float 0.2 run scoreboard players get #custom-model-z garage-time

loot replace entity @n[tag=custom-model-garage-temp] contents mine ~ ~ ~ shears[enchantments={silk_touch:255}]

    data modify storage kart-custom-model temp set value {id:"asdf", translation:[0.0f, 0.0f, 0.0f]}
    data modify storage kart-custom-model temp.id set from entity @n[tag=custom-model-garage-temp] item.id
    data modify storage kart-custom-model temp.translation set from entity @n[tag=custom-model-garage-temp] transformation.translation

    data modify storage kart-custom-model temp2 append from storage minecraft:kart-custom-model temp

tag @e[tag=custom-model-garage-temp] remove custom-model-garage-temp
