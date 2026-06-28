execute if score #fast-garage-toggle-height kartmain matches 0 run summon minecraft:text_display ~ ~0.075 ~ {Tags:["garage-classic-text","garage-classic-text-temp"],background:-16777216}
execute if score #fast-garage-toggle-height kartmain matches 0 run summon minecraft:item_display ~ ~0.35 ~ {Tags:["garage-classic-icon","garage-classic-icon-temp"],item:{id:"minecraft:tnt",count:1}}
execute if score #fast-garage-toggle-height kartmain matches 0 run summon interaction ^0.25 ^ ^ {Tags:["fast-garage-interaction","garage-classic-interaction"],response:1b,width:0.5f,height:0.5f}
execute if score #fast-garage-toggle-height kartmain matches 0 run summon marker ~ ~ ~ {Tags:["fast-garage-marker","garage-classic-marker","fast-garage-marker-temp"]}

execute if score #fast-garage-toggle-height kartmain matches 1 positioned ~ ~-0.1 ~ run summon minecraft:text_display ~ ~0.075 ~ {Tags:["garage-classic-text","garage-classic-text-temp"],background:-16777216}
execute if score #fast-garage-toggle-height kartmain matches 1 positioned ~ ~-0.1 ~ run summon minecraft:item_display ~ ~0.35 ~ {Tags:["garage-classic-icon","garage-classic-icon-temp"],item:{id:"minecraft:tnt",count:1}}
execute if score #fast-garage-toggle-height kartmain matches 1 positioned ~ ~-0.1 ~ run summon interaction ^0.25 ^ ^ {Tags:["fast-garage-interaction","garage-classic-interaction"],response:1b,width:0.5f,height:0.5f}
execute if score #fast-garage-toggle-height kartmain matches 1 positioned ~ ~-0.1 ~ run summon marker ~ ~ ~ {Tags:["fast-garage-marker","garage-classic-marker","fast-garage-marker-temp"]}

execute store result score #fast-garage-toggle-height kartmain if score #fast-garage-toggle-height kartmain matches 0

#테마 하나 pop
data modify entity @n[tag=garage-classic-icon-temp,type=item_display] item.id set from storage garage-room temp[0].item
data modify entity @n[tag=garage-classic-icon-temp,type=item_display] transformation.scale set value [0.245f, 0.245f, 0.245f]

data modify entity @n[tag=garage-classic-text-temp,type=text_display] text set from storage garage-room temp[0].name
data modify entity @n[tag=garage-classic-text-temp,type=text_display] transformation.scale set value [0.35f, 0.35f, 0.35f]
data modify entity @n[tag=fast-garage-marker-temp,type=marker] data.kart set from storage garage-room temp[0]

    scoreboard players set #item-exist kartmain 0
    function kartmain:player-head-item/detect-exist-item with storage garage-room temp[0]
    execute if score #item-exist kartmain matches 0 run function garage:fast-garage/classic/show-list/head with storage garage-room temp[0]

data remove storage garage-room temp[0]

tag @e[tag=garage-classic-icon-temp,type=item_display] remove garage-classic-icon-temp
tag @e[tag=garage-classic-text-temp,type=text_display] remove garage-classic-text-temp
tag @e[tag=fast-garage-marker-temp,type=marker] remove fast-garage-marker-temp

execute if data storage minecraft:garage-room temp[0] positioned ^ ^ ^0.5 if block ~ ~ ~ #kartmobil:ignoreblock run function garage:fast-garage/classic/show-list/show-garage-loop
execute if data storage minecraft:garage-room temp[0] positioned ^ ^ ^0.5 unless block ~ ~ ~ #kartmobil:ignoreblock as @e[tag=garage-y-pos,type=marker] at @s run tp @s ~ ~-0.5 ~
execute if data storage minecraft:garage-room temp[0] positioned ^ ^ ^0.5 unless block ~ ~ ~ #kartmobil:ignoreblock as @e[tag=garage-y-pos,type=marker] at @s run scoreboard players set #fast-garage-toggle-height kartmain 0
execute if data storage minecraft:garage-room temp[0] positioned ^ ^ ^0.5 unless block ~ ~ ~ #kartmobil:ignoreblock positioned as @e[tag=garage-y-pos,type=marker] run function garage:fast-garage/classic/show-list/show-garage-loop