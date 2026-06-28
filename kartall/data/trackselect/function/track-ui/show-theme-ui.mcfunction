function trackselect:track-ui/remove-ui

playsound minecraft:entity.puffer_fish.blow_up hostile @a
playsound minecraft:ui.button.click hostile @a ~ ~ ~ 1 2

execute at @e[tag=track-main] run summon minecraft:text_display ~ ~1 ~ {Tags:["track-theme-text"],text:{"color":"red",translate:"돌아가기"}}
execute at @e[tag=track-main] run summon interaction ^1 ^1 ^ {Tags:["track-theme-return-interaction"],response:1b,width:2f,height:0.75f}

#theme-count theme-ui
data modify storage track-list temp set from storage track-list themes

summon marker ~ ~ ~ {Tags:["track-y-pos"]}
function trackselect:track-ui/show-theme-list/show-theme-loop
kill @e[tag=track-y-pos]