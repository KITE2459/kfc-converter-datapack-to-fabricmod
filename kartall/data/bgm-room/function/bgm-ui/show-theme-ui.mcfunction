playsound minecraft:entity.puffer_fish.blow_up hostile @a
playsound minecraft:ui.button.click hostile @a ~ ~ ~ 1 2

function bgm-room:bgm-ui/remove-ui

#theme-count theme-ui
data modify storage bgm-room temp set from storage bgm-room themes

summon marker ~ ~ ~ {Tags:["bgm-y-pos"]}
function bgm-room:bgm-ui/show-theme-list/show-theme-loop
kill @e[tag=bgm-y-pos]
