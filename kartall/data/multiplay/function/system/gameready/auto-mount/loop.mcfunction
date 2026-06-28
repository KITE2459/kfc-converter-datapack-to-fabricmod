execute as @e[tag=auto-mount-marker,type=marker] run function multiplay:system/gameready/auto-mount/detect-collision-and-move

execute if entity @e[tag=kart-multi-collision,type=marker] run function multiplay:system/gameready/auto-mount/loop
