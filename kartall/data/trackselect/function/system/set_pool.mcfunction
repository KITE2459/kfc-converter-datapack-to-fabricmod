execute store result storage temp theme_index int 1 run scoreboard players get #theme_index temp
function trackselect:system/process_theme with storage temp
scoreboard players add #theme_index temp 1
execute store result score #themes_length temp run data get storage temp themes
execute if score #theme_index temp < #themes_length temp run function trackselect:system/set_pool