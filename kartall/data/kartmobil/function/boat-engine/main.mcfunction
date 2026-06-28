execute on passengers if entity @s[tag=kartsaddle] on passengers if entity @s[type=player] run tag @s add boat-player

function kartmobil:boat-engine/control/main
execute if entity @s[tag=!kart-stop] run function kartmobil:boat-engine/move/main

tag @p[tag=boat-player] remove boat-player