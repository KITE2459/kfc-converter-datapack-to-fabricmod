playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~ ~ 2 1.6

execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~1 ~24 2 1.6
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~ ~1 ~-24 2 1.6
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~24 ~1 ~ 2 1.6
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-24 ~1 ~ 2 1.6

execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~17 ~1 ~17 2 1.6
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-17 ~1 ~-17 2 1.6
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~17 ~1 ~-17 2 1.6
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.trial_spawner.about_to_spawn_item neutral @a[tag=kart-listener] ~-17 ~1 ~17 2 1.6

playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 1