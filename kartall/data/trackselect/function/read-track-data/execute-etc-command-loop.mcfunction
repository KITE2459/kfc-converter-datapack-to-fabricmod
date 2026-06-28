data remove storage track-list temp
data modify storage minecraft:track-list temp set from storage minecraft:track-list etc[0]
data remove storage minecraft:track-list etc[0]

function trackselect:read-track-data/execute-etc-command-macro with storage minecraft:track-list

execute if data storage minecraft:track-list etc[0] run function trackselect:read-track-data/execute-etc-command-loop