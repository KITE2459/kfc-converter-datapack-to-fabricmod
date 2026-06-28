execute unless entity @p[scores={trackselect-game-id=1..}] run return 1

#타임어택 텍스트에 트랙 정보 설정
data modify entity @n[tag=trackselect-timeattack-marker] data set from entity @n[tag=track-track-marker] data
data modify entity @n[tag=trackselect-timeattack-text] text set from entity @n[tag=trackselect-timeattack-marker] data.track.text
data modify entity @n[tag=trackselect-timeattack-difficulty] text set from entity @n[tag=trackselect-timeattack-marker] data.track.difficulty

#타임어택 기록 텍스트에 기록 정보 설정
execute as @n[tag=trackselect-timeattack-record] run function timeattack:system/record/changerecordtext with entity @n[tag=trackselect-timeattack-marker] data.track

tp @p[scores={trackselect-game-id=1..}] -139 4 236