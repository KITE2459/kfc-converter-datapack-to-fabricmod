execute unless entity @p[scores={trackselect-game-id=1..}] run return 1

#타임어택 텍스트에 트랙 정보 설정
data modify entity @n[tag=trackselect-devbattle-marker] data set from entity @n[tag=track-track-marker] data
data modify entity @n[tag=trackselect-devbattle-text] text set from entity @n[tag=trackselect-devbattle-marker] data.track.text
data modify entity @n[tag=trackselect-devbattle-difficulty] text set from entity @n[tag=trackselect-devbattle-marker] data.track.difficulty

execute as @n[tag=trackselect-devbattle-record] run function devbattle:system/devrecord/changerecordtext with entity @n[tag=trackselect-devbattle-marker] data.track
execute as @n[tag=trackselect-devbattle-record] run function devbattle:system/devrecord/changerecordtext with entity @n[tag=trackselect-devbattle-marker] data.track

#랜덤트랙일 경우 기록을 ???로
#execute as @n[tag=trackselect-text] if entity @s[tag=trackselect-text-random] run data modify entity @n[tag=trackselect-devbattle-record] text set from entity @n[tag=trackselect-record] text

#금블록
function devbattle:system/devrecord/load-clear-data-text with entity @n[tag=track-track-marker] data.track
execute if score cleardata timermain matches 1 run setblock -86 4 236 minecraft:gold_block
execute if score cleardata timermain matches 0 run setblock -86 4 236 minecraft:polished_tuff

tp @p[scores={trackselect-game-id=1..}] -80 4 236 90 0