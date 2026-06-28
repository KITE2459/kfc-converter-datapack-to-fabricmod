playsound minecraft:block.conduit.activate neutral @a[tag=kart-listener] ~ ~ ~ 2.5 2 1
playsound minecraft:block.conduit.activate neutral @a[tag=kart-listener] ~ ~ ~ 2.5 1 1
playsound minecraft:block.conduit.activate neutral @a[tag=kart-listener] ~ ~ ~ 2.5 2 1
playsound minecraft:block.conduit.activate neutral @a[tag=kart-listener] ~ ~ ~ 2.5 1 1
playsound minecraft:block.conduit.activate neutral @a[tag=kart-listener] ~ ~ ~ 2.5 0 1
playsound minecraft:block.conduit.activate neutral @a[tag=kart-listener] ~ ~ ~ 2.5 0 1

execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 10 force @a[tag=kart-listener]

execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/ready-set-2