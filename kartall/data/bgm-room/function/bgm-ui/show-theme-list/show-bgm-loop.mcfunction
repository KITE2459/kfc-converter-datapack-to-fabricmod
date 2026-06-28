summon minecraft:text_display ~ ~ ~ {Tags:["bgm-bgm-text","bgm-bgm-text-temp"],background:-16777216}
summon minecraft:text_display ~ ~-0.25 ~ {Tags:["bgm-bgm-composer","bgm-bgm-composer-temp"],background:-16777216}
summon minecraft:text_display ~ ~-0.4 ~ {Tags:["bgm-bgm-composer2","bgm-bgm-composer2-temp"],background:-16777216}
summon interaction ^1 ^ ^ {Tags:["bgm-bgm-interaction"],response:1b,width:2f,height:0.75f}
summon marker ~ ~ ~ {Tags:["bgm-bgm-marker","bgm-bgm-marker-temp"]}

#테마 하나 pop
data modify entity @n[tag=bgm-bgm-text-temp,type=text_display] text set from storage bgm-room temp[0].text
data modify entity @n[tag=bgm-bgm-text-temp,type=text_display] transformation.scale set value [0.85f, 0.85f, 0.85f]

data modify entity @n[tag=bgm-bgm-composer-temp,type=text_display] text set from storage bgm-room temp[0].composer
data modify entity @n[tag=bgm-bgm-composer-temp,type=text_display] transformation.scale set value [0.75f, 0.75f, 0.75f]

data modify entity @n[tag=bgm-bgm-composer2-temp,type=text_display] text set from storage bgm-room temp[0].composer2
data modify entity @n[tag=bgm-bgm-composer2-temp,type=text_display] transformation.scale set value [0.75f, 0.75f, 0.75f]

data modify entity @n[tag=bgm-bgm-marker-temp,type=marker] data.bgm set from storage bgm-room temp[0]
data remove storage bgm-room temp[0]

tag @e[tag=bgm-bgm-text-temp,type=text_display] remove bgm-bgm-text-temp
tag @e[tag=bgm-bgm-composer-temp,type=text_display] remove bgm-bgm-composer-temp
tag @e[tag=bgm-bgm-composer2-temp,type=text_display] remove bgm-bgm-composer2-temp
tag @e[tag=bgm-bgm-marker-temp,type=marker] remove bgm-bgm-marker-temp

execute if data storage minecraft:bgm-room temp[0] positioned ^ ^ ^2 if block ~ ~ ~ #kartmobil:ignoreblock run function bgm-room:bgm-ui/show-theme-list/show-bgm-loop
execute if data storage minecraft:bgm-room temp[0] positioned ^ ^ ^2 unless block ~ ~ ~ #kartmobil:ignoreblock as @e[tag=bgm-y-pos,type=marker] at @s run tp @s ~ ~-1 ~
execute if data storage minecraft:bgm-room temp[0] positioned ^ ^ ^2 unless block ~ ~ ~ #kartmobil:ignoreblock positioned as @e[tag=bgm-y-pos,type=marker] run function bgm-room:bgm-ui/show-theme-list/show-bgm-loop