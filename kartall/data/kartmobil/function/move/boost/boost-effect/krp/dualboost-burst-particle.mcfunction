playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1.5 1 1
playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1.5 2 1
playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 0.5 0 0.5

playsound minecraft:entity.firework_rocket.blast neutral @a[tag=kart-listener] ~ ~ ~ 1.5 2 1
playsound minecraft:entity.firework_rocket.blast neutral @a[tag=kart-listener] ~ ~ ~ 1.5 1 1

playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~ ~ 2 1.1

execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~1 ~24 2 1.1
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~1 ~-24 2 1.1
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~24 ~1 ~ 2 1.1
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-24 ~1 ~ 2 1.1

execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~17 ~1 ~17 2 1.1
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-17 ~1 ~-17 2 1.1
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~17 ~1 ~-17 2 1.1
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-17 ~1 ~17 2 1.1

execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 10 force @a[tag=kart-listener]
execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 8 force @a[tag=kart-listener]

execute on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/burst-set-3