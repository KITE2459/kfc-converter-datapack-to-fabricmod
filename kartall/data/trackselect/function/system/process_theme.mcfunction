execute store result storage temp theme_index int 1 run scoreboard players get #theme_index temp
$data modify storage temp current_theme_key set from storage temp themes[$(theme_index)]
function trackselect:system/process_theme_sub with storage temp
scoreboard players set #track_index temp 0
execute store result storage temp track_index int 1 run scoreboard players get #track_index temp
function trackselect:system/process_track with storage temp
scoreboard players add #theme_index temp 1
execute store result storage temp theme_index int 1 run scoreboard players get #theme_index temp
execute store result score #themes_length temp run data get storage temp themes
execute if score #theme_index temp < #themes_length temp run function trackselect:system/process_theme with storage temp