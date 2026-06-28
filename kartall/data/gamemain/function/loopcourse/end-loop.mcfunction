tag @s remove kart-loop-kart
tag @s add kartmobil

execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-allow-model-rotation/enabled-1

execute on passengers if score @s[tag=kartdirection] loop-main matches 1 on vehicle run function kartmobil:flip-kart/main

data remove entity @s data.loop-data

execute on passengers if entity @s[tag=kartmodelsaddle] on passengers run kill @s[tag=kart-loop-player]
execute if entity @s[tag=!karthideplayer] as @p run effect clear @s minecraft:invisibility