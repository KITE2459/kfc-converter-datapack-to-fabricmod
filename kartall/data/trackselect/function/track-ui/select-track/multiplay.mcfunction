execute unless entity @p[scores={trackselect-game-id=1..}] run return 1

data modify entity @n[tag=trackselect-multi-marker] data set from entity @n[tag=track-track-marker] data
data modify entity @n[tag=trackselect-multi-text] text set from entity @n[tag=trackselect-multi-marker] data.track.text
data modify entity @n[tag=trackselect-multi-creator] text set from entity @n[tag=trackselect-multi-marker] data.track.creator
data modify entity @n[tag=trackselect-multi-difficulty] text set from entity @n[tag=trackselect-multi-marker] data.track.difficulty

tp @p[scores={trackselect-game-id=1..}] -18 -1 156 180 0