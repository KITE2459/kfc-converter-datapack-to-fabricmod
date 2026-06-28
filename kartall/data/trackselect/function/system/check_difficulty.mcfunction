# easy: 1 or 2
execute if function trackselect:system/is_1star unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.easy append from storage temp current_track.number
execute if function trackselect:system/is_2star unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.easy append from storage temp current_track.number

# normal: 2 or 3
execute if function trackselect:system/is_2star unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.normal append from storage temp current_track.number
execute if function trackselect:system/is_3star unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.normal append from storage temp current_track.number

# hard: 3 or 4
execute if function trackselect:system/is_3star unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.hard append from storage temp current_track.number
execute if function trackselect:system/is_4star unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.hard append from storage temp current_track.number

# very-hard: 4 or 5
execute if function trackselect:system/is_4star unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.very-hard append from storage temp current_track.number
execute if function trackselect:system/is_5star unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.very-hard append from storage temp current_track.number

# all: 1 to 5
execute unless data storage temp current_track.is-copyright unless score copyright-content trackselect-map-id matches 0 run data modify storage random_tracks tracks.all append from storage temp current_track.number