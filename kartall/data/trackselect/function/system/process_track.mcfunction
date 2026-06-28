execute store result storage temp track_index int 1 run scoreboard players get #track_index temp
$data modify storage temp current_track set from storage temp tracks[$(track_index)]
data modify storage temp difficulty_text_origin set from storage temp current_track.difficulty.translate
function trackselect:system/check_difficulty
scoreboard players add #track_index temp 1
execute store result storage temp track_index int 1 run scoreboard players get #track_index temp
execute if score #track_index temp < #tracks_length temp run function trackselect:system/process_track with storage temp