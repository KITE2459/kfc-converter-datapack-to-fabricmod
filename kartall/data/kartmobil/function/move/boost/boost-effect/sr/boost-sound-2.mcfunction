#execute as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~ ~ ~ 2 0.85
#execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~ ~1 ~24 2 0.85
#execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~ ~1 ~-24 2 0.85
#execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~24 ~1 ~ 2 0.85
#execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~-24 ~1 ~ 2 0.85
#
#execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~17 ~1 ~17 2 0.85
#execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~17 ~1 ~-17 2 0.85
#execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~-17 ~1 ~-17 2 0.85
#execute if score #kartspeed kartmove matches 65.. as @e[limit=3] run playsound minecraft:block.grindstone.use neutral @a[tag=kart-listener] ~-17 ~1 ~17 2 0.85


playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.7 1
playsound minecraft:entity.breeze.jump neutral @a[tag=kart-listener] ~ ~ ~ 1.5 0.5 1
playsound minecraft:entity.breeze.land neutral @a[tag=kart-listener] ~ ~ ~ 1.5 0.5 1

#playsound minecraft:entity.breeze.hurt neutral @a[tag=kart-listener] ~ ~ ~ 1.5 0.5 1