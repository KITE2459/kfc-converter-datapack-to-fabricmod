playsound minecraft:entity.puffer_fish.blow_up hostile @a
playsound minecraft:ui.button.click hostile @a ~ ~ ~ 1 2

function garage:fast-garage/legend/remove-ui

scoreboard players set #fast-garage-toggle-height kartmain 0

#테마 선택
execute at @e[tag=garage-legend-main] run summon minecraft:text_display ~ ~1 ~ {Tags:["garage-legend-return-text"],text:{"color":"red",translate:"뒤로가기"}}
execute at @e[tag=garage-legend-main] run summon interaction ^1 ^1 ^ {Tags:["garage-return-interaction","garage-legend-interaction"],response:1b,width:2f,height:0.75f}

data modify storage garage-room temp set from entity @n[tag=garage-legend-main] data.kart-item-data

execute at @e[tag=garage-legend-main] run summon marker ~ ~ ~ {Tags:["garage-y-pos"]}
execute as @e[tag=garage-legend-main] at @s run function garage:fast-garage/legend/show-list/show-garage-loop

kill @e[tag=garage-y-pos]
function garage:fast-garage/legend/remove-theme-ui