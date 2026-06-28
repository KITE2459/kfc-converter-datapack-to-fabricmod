execute if score #map trackselect-map-id matches 1 store result score #random_track_index temp run data get storage random_tracks tracks.easy
execute if score #map trackselect-map-id matches 2 store result score #random_track_index temp run data get storage random_tracks tracks.normal
execute if score #map trackselect-map-id matches 3 store result score #random_track_index temp run data get storage random_tracks tracks.hard
execute if score #map trackselect-map-id matches 4 store result score #random_track_index temp run data get storage random_tracks tracks.very-hard
execute if score #map trackselect-map-id matches 5 store result score #random_track_index temp run data get storage random_tracks tracks.all
scoreboard players remove #random_track_index temp 1
execute store result storage temp selected_track int 1 run scoreboard players get #random_track_index temp
function multiplay:system/gameready/random-value with storage temp
function multiplay:system/gameready/random-apply with storage temp