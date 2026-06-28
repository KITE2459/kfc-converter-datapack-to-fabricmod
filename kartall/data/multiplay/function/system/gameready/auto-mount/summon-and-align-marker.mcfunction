
execute as @a[tag=kart-multi-player] run summon marker 0 0 0 {Tags:["auto-mount-marker"]}
execute as @e[tag=auto-mount-marker,type=marker] run function trackselect:read-track-data/movetomap with entity @n[tag=trackselect-multi-marker] data.track
function multiplay:system/gameready/auto-mount/loop