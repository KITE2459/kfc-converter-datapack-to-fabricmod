execute unless function trackselect:track-ui/show-theme-list/show-condition run return run function trackselect:track-ui/show-theme-list/skip-current

#execute unless data storage minecraft:track-list temp[0] run return 0

summon minecraft:text_display ~ ~ ~ {Tags:["track-theme-text","track-theme-text-temp"]}
summon minecraft:item_display ~ ~0.5 ~ {Tags:["track-theme-icon","track-theme-icon-temp"],item:{id:"minecraft:player_head",count:1}}

summon marker ~ ~ ~ {Tags:["track-theme-marker","track-theme-marker-temp"]}
summon interaction ^0.5 ^ ^ {Tags:["track-theme-interaction"],response:1b,width:1f,height:0.75f}

#테마 하나 pop
data modify entity @n[tag=track-theme-text-temp,type=text_display] text set from storage track-list temp[0].text
execute if data storage track-list temp[0].smalltext run data modify entity @n[tag=track-theme-text-temp,type=text_display] transformation.scale set value [0.75f, 0.75f, 0.75f]
execute if data storage track-list temp[0].smalltext run data modify entity @n[tag=track-theme-text-temp,type=text_display] transformation.translation[1] set value 0.0325f
execute if data storage track-list temp[0].carriagereturn run data modify entity @n[tag=track-theme-text-temp,type=text_display] transformation.translation[1] set value -0.125f

#data modify entity @n[tag=track-theme-icon-temp] item.id set from storage track-list temp[0].icon
#data modify entity @n[tag=track-theme-icon-temp] transformation.scale set value [0.25f, 0.25f, 0.25f]

    # 머리블록

    #마리오카트 머리를 사용해 초기화
    item replace entity @n[tag=track-theme-icon-temp,type=item_display] contents from entity @n[tag=mariokart-icon] contents

    execute at @n[tag=track-theme-icon-temp,type=item_display] run rotate @n[tag=track-theme-icon-temp,type=item_display] ~180 ~
    data modify entity @n[tag=track-theme-icon-temp,type=item_display] transformation.translation[1] set value 0.125f
    data modify entity @n[tag=track-theme-icon-temp,type=item_display] transformation.scale set value [0.5f, 0.5f, 0.5f]
    data modify entity @n[tag=track-theme-icon-temp,type=item_display] item.components."minecraft:profile".properties[0].value set from storage track-list temp[0].icon

data modify entity @n[tag=track-theme-marker-temp,type=marker] data.theme set from storage track-list temp[0]
data remove storage track-list temp[0]

tag @e[tag=track-theme-text-temp,type=text_display] remove track-theme-text-temp
tag @e[tag=track-theme-marker-temp,type=marker] remove track-theme-marker-temp
tag @e[tag=track-theme-icon-temp,type=item_display] remove track-theme-icon-temp

execute if data storage minecraft:track-list temp[0] positioned ^ ^ ^1 if block ~ ~ ~ #kartmobil:ignoreblock run function trackselect:track-ui/show-theme-list/show-theme-loop
execute if data storage minecraft:track-list temp[0] positioned ^ ^ ^1 unless block ~ ~ ~ #kartmobil:ignoreblock as @e[tag=track-y-pos,type=marker] at @s run tp @s ~ ~-1 ~
execute if data storage minecraft:track-list temp[0] positioned ^ ^ ^1 unless block ~ ~ ~ #kartmobil:ignoreblock positioned as @e[tag=track-y-pos,type=marker] run function trackselect:track-ui/show-theme-list/show-theme-loop
