#바람이펙트

execute as @e[limit=2] run playsound minecraft:block.note_block.snare neutral @a[tag=kart-listener] ~ ~ ~ 1 0

execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:flash ^ ^1 ^2 0 0 0 1 1 force @a[tag=kart-listener]
execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:enchanted_hit ^ ^1 ^1 .25 .25 .25 2 50 force @a[tag=kart-listener]
execute on passengers rotated as @s[tag=kartdirection,type=item_display] rotated ~ 0 run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 8 force @a[tag=kart-listener]

playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~ ~ 1.8 2
execute if score #kartspeed kartmove matches 65.. run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~1 ~24 1.8 2
execute if score #kartspeed kartmove matches 65.. run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~1 ~-24 1.8 2
execute if score #kartspeed kartmove matches 65.. run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~24 ~1 ~ 1.8 2
execute if score #kartspeed kartmove matches 65.. run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-24 ~1 ~ 1.8 2

execute if score #kartspeed kartmove matches 65.. run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~17 ~1 ~17 1.8 2
execute if score #kartspeed kartmove matches 65.. run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-17 ~1 ~-17 1.8 2
execute if score #kartspeed kartmove matches 65.. run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~17 ~1 ~-17 1.8 2
execute if score #kartspeed kartmove matches 65.. run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-17 ~1 ~17 1.8 2

playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.5 1 0.5
execute as @e[limit=2] run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 0.75 .75

execute as @e[limit=2] run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 1
execute as @e[limit=2] run playsound minecraft:entity.firework_rocket.blast neutral @a[tag=kart-listener] ~ ~ ~ 1 1