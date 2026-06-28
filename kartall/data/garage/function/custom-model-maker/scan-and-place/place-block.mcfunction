
summon marker 5258 4 4500 {Tags:["garage-marker-temp"]}

$execute as @n[tag=garage-marker-temp,type=marker] at @s run tp @s ~$(pos0) ~$(pos1) ~$(pos2)
$execute as @n[tag=garage-marker-temp,type=marker] at @s run tp @s ~$(pos0) ~$(pos1) ~$(pos2)
$execute as @n[tag=garage-marker-temp,type=marker] at @s run tp @s ~$(pos0) ~$(pos1) ~$(pos2)
$execute as @n[tag=garage-marker-temp,type=marker] at @s run tp @s ~$(pos0) ~$(pos1) ~$(pos2)

$execute as @n[tag=garage-marker-temp,type=marker] at @s run setblock ~$(pos0) ~$(pos1) ~$(pos2) $(id)

kill @e[tag=garage-marker-temp,type=marker]