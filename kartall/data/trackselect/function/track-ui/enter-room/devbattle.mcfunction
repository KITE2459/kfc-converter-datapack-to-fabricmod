

execute if function gamemain:cannot-start-condition run return 1

execute as @e[tag=dev-text] if score @s dev-count = #dev dev-count run tag @s add dev-text-temp
data modify entity @e[tag=trackselect-mode,limit=1] text set value [{translate:"제작자와의 대결\n","color":"yellow"},{"interpret":true,"entity":"@e[tag=devbattle-devname,limit=1]","nbt":"text","color":"aqua"}]
tag @e[tag=dev-text-temp] remove dev-text-temp

#방에 입장
scoreboard players set @p trackselect-game-id 2
scoreboard players reset @p trackselect-map-id

tp @p 44 5 185 180 0

forceload add -132 185
clone -134 5 188 -134 5 188 49 6 185

execute as @e[tag=track-main] at @s run function trackselect:track-ui/show-theme-ui