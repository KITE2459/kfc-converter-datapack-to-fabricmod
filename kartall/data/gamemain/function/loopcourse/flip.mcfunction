#가ㅣㄱ속도 뒤집기
execute store result entity @s data.loop-data.anglespeed float -1 run data get entity @s data.loop-data.anglespeed
execute store result storage kartmain loop-data.anglespeed float -1 run data get storage kartmain loop-data.anglespeed

#모델링 뒤집기
tag @s add kart-loop-flipped-late
function kartmobil:rkey-ghost/hide
execute on passengers if entity @s[tag=kartmodelsaddle] on passengers run data modify entity @s teleport_duration set value 0
execute on passengers if entity @s[tag=kartmodelsaddle] on passengers run data modify entity @s interpolation_duration set value 0
function kartmobil:flip-kart/main

execute rotated as @n[tag=kart-loop-direction] run rotate @n[tag=kart-loop-direction] ~180 ~

#플립 여부, 1=플립됨
execute store result score @n[tag=kart-loop-direction] loop-main if score @n[tag=kart-loop-direction] loop-main matches 0


