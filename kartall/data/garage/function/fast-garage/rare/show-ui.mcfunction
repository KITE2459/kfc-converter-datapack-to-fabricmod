playsound minecraft:entity.puffer_fish.blow_up hostile @a
playsound minecraft:ui.button.click hostile @a ~ ~ ~ 1 2

function garage:fast-garage/rare/remove-ui

scoreboard players set #fast-garage-toggle-height kartmain 0

#테마 선택
execute at @e[tag=garage-rare-main] run summon minecraft:text_display ~ ~1 ~ {Tags:["garage-rare-return-text"],text:{"color":"red",translate:"뒤로가기"}}
execute at @e[tag=garage-rare-main] run summon interaction ^1 ^1 ^ {Tags:["garage-return-interaction","garage-rare-interaction"],response:1b,width:2f,height:0.75f}

data modify storage garage-room temp set from entity @n[tag=garage-rare-main] data.kart-item-data

execute at @e[tag=garage-rare-main] run summon marker ~ ~ ~ {Tags:["garage-y-pos"]}
execute as @e[tag=garage-rare-main] at @s run function garage:fast-garage/rare/show-list/show-garage-loop

kill @e[tag=garage-y-pos]
function garage:fast-garage/rare/remove-theme-ui