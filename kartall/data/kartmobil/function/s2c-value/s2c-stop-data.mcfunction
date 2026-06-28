tag @s[tag=kart-stop-perform] remove kart-stop-perform
tag @s[tag=kart-stop-release] remove kart-stop-release

execute if entity @s[tag=kart-stop-performed] if score @s kartmove matches 2780.. run tag @s add kart-stop-release
execute if entity @s[tag=kart-stop-performed] if score @s kartmove matches 2780.. run tag @s remove kart-stop-performed
execute if entity @s[tag=!kart-stop-performed] unless score @s kartmove matches 2780.. run tag @s add kart-stop-perform
execute if entity @s[tag=!kart-stop-performed] unless score @s kartmove matches 2780.. run tag @s add kart-stop-performed

execute unless score @s[tag=kart-stop-release] kart-engine matches 1004 on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-allow-model-rotation/enabled-1
execute unless score @s[tag=kart-stop-perform] kart-engine matches 1004 on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-allow-model-rotation/disabled-0

#출발선에서 강제 정지
execute if entity @s[tag=kart-stop] on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-allow-model-rotation/disabled-0