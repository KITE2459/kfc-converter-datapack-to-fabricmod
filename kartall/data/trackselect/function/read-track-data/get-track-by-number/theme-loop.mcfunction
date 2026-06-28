function trackselect:track-ui/show-theme-list/copy-selected-theme-data with storage track-list temp2[0]

$execute store result score #found trackselect-temp if data storage track-list temp[{number:$(number)}]
$execute if score #found trackselect-temp matches 1 run data modify storage track-list number-search-result set from storage track-list temp[{number:$(number)}]
execute if score #found trackselect-temp matches 1 run return 1

#재귀
data remove storage track-list temp2[0]
$execute if data storage minecraft:track-list temp2[0] run function trackselect:read-track-data/get-track-by-number/theme-loop {number:$(number)}

