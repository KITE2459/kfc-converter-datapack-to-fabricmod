$execute store result storage boatpack move-x float $(slip) run scoreboard players get @s boat-move-x
$execute store result storage boatpack move-z float $(slip) run scoreboard players get @s boat-move-z

execute store result score @s boat-move-x run data get storage boatpack move-x
execute store result score @s boat-move-z run data get storage boatpack move-z
