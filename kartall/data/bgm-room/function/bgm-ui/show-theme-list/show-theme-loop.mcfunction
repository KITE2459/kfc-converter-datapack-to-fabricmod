summon minecraft:text_display ~ ~ ~ {Tags:["bgm-theme-text","bgm-theme-text-temp"],background:-16777216}
summon minecraft:item_display ~ ~0.5 ~ {Tags:["bgm-theme-icon","bgm-theme-icon-temp"]}

summon marker ~ ~ ~ {Tags:["bgm-theme-marker","bgm-theme-marker-temp"]}
summon interaction ^0.5 ^ ^ {Tags:["bgm-theme-interaction"],response:1b,width:1f,height:0.75f}
#테마 하나 pop
data modify entity @n[tag=bgm-theme-text-temp,type=text_display] text set from storage bgm-room temp[0].text
execute if data storage bgm-room temp[0].smalltext run data modify entity @n[tag=bgm-theme-text-temp,type=text_display] transformation.scale set value [0.75f, 0.75f, 0.75f]
execute if data storage bgm-room temp[0].smalltext run data modify entity @n[tag=bgm-theme-text-temp,type=text_display] transformation.translation[1] set value 0.0325f
execute if data storage bgm-room temp[0].carriagereturn run data modify entity @n[tag=bgm-theme-text-temp,type=text_display] transformation.translation[1] set value -0.125f

    #마리오카트 머리를 사용해 초기화
    item replace entity @n[tag=bgm-theme-icon-temp,type=item_display] contents from entity @n[tag=mariokart-icon] contents

    execute at @n[tag=bgm-theme-icon-temp,type=item_display] run rotate @n[tag=bgm-theme-icon-temp,type=item_display] ~180 ~
    data modify entity @n[tag=bgm-theme-icon-temp,type=item_display] transformation.translation[1] set value 0.125f
    data modify entity @n[tag=bgm-theme-icon-temp,type=item_display] transformation.scale set value [0.5f, 0.5f, 0.5f]
    data modify entity @n[tag=bgm-theme-icon-temp,type=item_display] item.components."minecraft:profile".properties[0].value set from storage bgm-room temp[0].icon

data modify entity @n[tag=bgm-theme-marker-temp] data.theme set from storage bgm-room temp[0]
data remove storage bgm-room temp[0]


tag @e[tag=bgm-theme-text-temp,type=text_display] remove bgm-theme-text-temp
tag @e[tag=bgm-theme-marker-temp,type=marker] remove bgm-theme-marker-temp
tag @e[tag=bgm-theme-icon-temp,type=item_display] remove bgm-theme-icon-temp

execute if data storage minecraft:bgm-room temp[0] positioned ^ ^ ^1 if block ~ ~ ~ #kartmobil:ignoreblock run function bgm-room:bgm-ui/show-theme-list/show-theme-loop
execute if data storage minecraft:bgm-room temp[0] positioned ^ ^ ^1 unless block ~ ~ ~ #kartmobil:ignoreblock as @e[tag=bgm-y-pos,type=marker] at @s run tp @s ~ ~-1 ~
execute if data storage minecraft:bgm-room temp[0] positioned ^ ^ ^1 unless block ~ ~ ~ #kartmobil:ignoreblock positioned as @e[tag=bgm-y-pos,type=marker] run function bgm-room:bgm-ui/show-theme-list/show-theme-loop
