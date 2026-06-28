data modify storage kartmain boosttag set from storage kartmain boosttags[0]

function kartmain:summon/boost-effect/boost-move/save-end-transform with storage kartmain
data remove storage kartmain boosttags[0]

execute if data storage kartmain boosttags[0] run function kartmain:summon/boost-effect/boost-move/find-numeric-tag-loop