#테마 선택
tag @n[tag=track-theme-marker] add track-theme-selected

playsound minecraft:entity.puffer_fish.blow_up hostile @a
playsound minecraft:ui.button.click hostile @a ~ ~ ~ 1 2

execute at @e[tag=track-main] run summon minecraft:text_display ~ ~1 ~ {Tags:["track-track-text"],text:{"color":"red",translate:"뒤로가기"}}
execute at @e[tag=track-main] run summon interaction ^1 ^1 ^ {Tags:["track-track-return-interaction"],response:1b,width:2f,height:0.75f}

execute at @e[tag=track-main] run summon marker ~ ~ ~ {Tags:["track-y-pos"]}
function trackselect:track-ui/show-theme-list/copy-selected-theme-data with entity @n[tag=track-theme-selected] data.theme
execute as @e[tag=track-main] at @s run function trackselect:track-ui/show-theme-list/show-track-loop

kill @e[tag=track-y-pos]
function trackselect:track-ui/remove-theme-ui