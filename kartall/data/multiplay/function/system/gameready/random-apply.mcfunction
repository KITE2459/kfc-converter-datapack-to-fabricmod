$execute if score #map trackselect-map-id matches 1 store result storage temp number int 1 run data get storage random_tracks tracks.easy[$(selected_track)]
$execute if score #map trackselect-map-id matches 2 store result storage temp number int 1 run data get storage random_tracks tracks.normal[$(selected_track)]
$execute if score #map trackselect-map-id matches 3 store result storage temp number int 1 run data get storage random_tracks tracks.hard[$(selected_track)]
$execute if score #map trackselect-map-id matches 4 store result storage temp number int 1 run data get storage random_tracks tracks.very-hard[$(selected_track)]
$execute if score #map trackselect-map-id matches 5 store result storage temp number int 1 run data get storage random_tracks tracks.all[$(selected_track)]

function trackselect:read-track-data/get-track-by-number/main with storage temp
execute if score #map trackselect-map-id matches 1 run data modify storage track-list number-search-result.random set value 1
execute if score #map trackselect-map-id matches 2 run data modify storage track-list number-search-result.random set value 2
execute if score #map trackselect-map-id matches 3 run data modify storage track-list number-search-result.random set value 3
execute if score #map trackselect-map-id matches 4 run data modify storage track-list number-search-result.random set value 4
execute if score #map trackselect-map-id matches 5 run data modify storage track-list number-search-result.random set value 5
data modify entity @n[tag=trackselect-multi-marker] data.track set from storage track-list number-search-result
execute store result score #map trackselect-map-id run function trackselect:read-track-data/get-track-number with entity @n[tag=trackselect-multi-marker] data.track