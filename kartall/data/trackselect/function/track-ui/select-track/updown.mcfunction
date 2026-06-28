
execute unless entity @p[scores={trackselect-game-id=1..}] run return 1

#업다운 텍스트에 트랙 정보 설정
data modify entity @n[tag=trackselect-updown-marker] data set from entity @n[tag=track-track-marker] data
data modify entity @n[tag=trackselect-updown-text] text set from entity @n[tag=trackselect-updown-marker] data.track.text

tp @p[scores={trackselect-game-id=1..}] -139 4 236