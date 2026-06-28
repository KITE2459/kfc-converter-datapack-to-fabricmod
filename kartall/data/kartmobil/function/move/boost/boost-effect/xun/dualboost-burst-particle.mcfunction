function kartmobil:move/boost/boost-effect/xun/boost-sound

execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 10 force @a[tag=kart-listener]
execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 8 force @a[tag=kart-listener]

execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/burst-set-3