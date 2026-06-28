data modify storage temp themes_ban set value "random"
$execute store result score #temp_return temp run data modify storage temp themes_ban set from storage track-list themes[$(temp)].key
execute if score #temp_return temp matches 0 run return 0
data modify storage temp themes_ban set value "updown"
$execute store result score #temp_return temp run data modify storage temp themes_ban set from storage track-list themes[$(temp)].key
execute if score #temp_return temp matches 0 run return 0
data modify storage temp themes_ban set value "updowncrossover"
$execute store result score #temp_return temp run data modify storage temp themes_ban set from storage track-list themes[$(temp)].key
execute if score #temp_return temp matches 0 run return 0

$data modify storage temp themes append from storage track-list themes[$(temp)].key