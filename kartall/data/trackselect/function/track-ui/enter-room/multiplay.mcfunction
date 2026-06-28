execute if function gamemain:cannot-start-condition run return 1

#텍스트 숨기기
execute as @e[tag=trackselect-record,tag=!trackselect-record-random] at @s run setblock ^ ^-1.25 ^-0.25 light_gray_concrete
execute as @e[tag=trackselect-record,tag=!trackselect-record-random] at @s run data modify entity @s text set value {translate:""}

data modify entity @e[tag=trackselect-mode,limit=1] text set value [{translate:"멀티 플레이","color":"yellow"}]

#방에 입장
scoreboard players set @p trackselect-game-id 3
scoreboard players reset @p trackselect-map-id

execute if score updown multi-main matches 1 run tp @p 44 -60 185 180 0
execute unless score updown multi-main matches 1 run tp @p 44 5 185 180 0

forceload add -132 185
clone -134 5 188 -134 5 188 49 6 185

execute as @e[tag=track-main] at @s run function trackselect:track-ui/show-theme-ui