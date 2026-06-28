execute unless entity @e[tag=auto-mount-marker,tag=!auto-mount-marker-with-player] run function multiplay:system/gameready/auto-mount/kickplayer

tp @s @e[tag=auto-mount-marker,tag=!auto-mount-marker-with-player,type=marker,sort=random,limit=1]
execute at @s run tag @n[tag=auto-mount-marker,type=marker] add auto-mount-marker-with-player