playsound minecraft:entity.puffer_fish.blow_up hostile @a
playsound minecraft:ui.button.click hostile @a ~ ~ ~ 1 2

#테마 선택
tag @n[tag=bgm-theme-marker] add bgm-theme-selected

execute at @e[tag=bgm-main] run summon minecraft:text_display ~ ~1 ~ {Tags:["bgm-bgm-text"],text:{"color":"red",translate:"뒤로가기"}}
execute at @e[tag=bgm-main] run summon interaction ^1 ^1 ^ {Tags:["bgm-bgm-return-interaction"],response:1b,width:2f,height:0.75f}

execute at @e[tag=bgm-main] run summon marker ~ ~ ~ {Tags:["bgm-y-pos"]}
function bgm-room:bgm-ui/show-theme-list/copy-selected-theme-data with entity @n[tag=bgm-theme-selected] data.theme
execute as @e[tag=bgm-main] at @s run function bgm-room:bgm-ui/show-theme-list/show-bgm-loop

kill @e[tag=bgm-y-pos]
function bgm-room:bgm-ui/remove-theme-ui