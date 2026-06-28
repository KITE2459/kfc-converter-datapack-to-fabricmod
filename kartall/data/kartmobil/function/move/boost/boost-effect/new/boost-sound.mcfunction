execute as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~ ~ ~ 2 0.85
execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~ ~1 ~24 2 0.85
execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~ ~1 ~-24 2 0.85
execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~24 ~1 ~ 2 0.85
execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~-24 ~1 ~ 2 0.85

execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~17 ~1 ~17 2 0.85
execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~17 ~1 ~-17 2 0.85
execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~-17 ~1 ~-17 2 0.85
execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~-17 ~1 ~17 2 0.85

execute as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~ ~ ~ 2 2
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~ ~1 ~24 2 2
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~ ~1 ~-24 2 2
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~24 ~1 ~ 2 2
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~-24 ~1 ~ 2 2

execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~17 ~1 ~17 2 2
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~17 ~1 ~-17 2 2
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~-17 ~1 ~-17 2 2
execute if score #kartspeed kartmove matches 65.. as @e[limit=2] run playsound minecraft:block.beacon.activate neutral @a[tag=kart-listener] ~-17 ~1 ~17 2 2

execute as @e[limit=2] run playsound minecraft:block.piston.extend neutral @a[tag=kart-listener] ~ ~ ~ 2 1
