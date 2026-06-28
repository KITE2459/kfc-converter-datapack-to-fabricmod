execute store result storage temp temp int 1 run scoreboard players get #temp temp
function trackselect:system/get_theme_list_one with storage temp
scoreboard players add #temp temp 1
execute if score #temp temp matches ..100 run function trackselect:system/get_theme_list