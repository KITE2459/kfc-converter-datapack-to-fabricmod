execute store result score #temp kartmain run data get entity @s[tag=!drift-effect,tag=!kart-boost-flame] view_range
execute if score #temp kartmain matches 1 run return 0

data modify entity @s view_range set value 1f
tag @s remove rkey-hidden
