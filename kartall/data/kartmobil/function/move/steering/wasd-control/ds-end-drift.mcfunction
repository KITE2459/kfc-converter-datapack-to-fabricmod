function kartmobil:move/steering/drift/drift-end

#미니터보 이펙트
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.blaze.shoot neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1 0.75

execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1.5 1 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 1.5 2 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.launch neutral @a[tag=kart-listener] ~ ~ ~ 0.5 0 0.5
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:block.conduit.deactivate neutral @a[tag=kart-listener] ~ ~ ~ 1.5 0 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:block.conduit.activate neutral @a[tag=kart-listener] ~ ~ ~ 1.5 0 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:block.conduit.deactivate neutral @a[tag=kart-listener] ~ ~ ~ 1.5 0 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:block.conduit.activate neutral @a[tag=kart-listener] ~ ~ ~ 1.5 0 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.zombie.attack_wooden_door neutral @a[tag=kart-listener] ~ ~ ~ 1 0 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.blast neutral @a[tag=kart-listener] ~ ~ ~ 1.5 2 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.blast neutral @a[tag=kart-listener] ~ ~ ~ 1.5 1 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.blast neutral @a[tag=kart-listener] ~ ~ ~ 1.5 2 1
execute if score @s kartboostgauge matches 2000.. at @a[tag=kartpassenger] run playsound minecraft:entity.firework_rocket.blast neutral @a[tag=kart-listener] ~ ~ ~ 1.5 1 1

execute if score @s kartboostgauge matches 2000.. at @s rotated as @a[tag=kartpassenger] run particle minecraft:crit ^ ^1 ^2 .25 .25 .25 1.25 50 force @a
execute if score @s kartboostgauge matches 2000.. at @s rotated as @a[tag=kartpassenger] run particle minecraft:cloud ^ ^1 ^2 .25 .25 .25 0.75 10 force @a
execute if score @s kartboostgauge matches 2000.. at @s rotated as @a[tag=kartpassenger] run particle minecraft:soul_fire_flame ^ ^1 ^2 .25 .25 .25 1 75 force @a

execute if score @s kartboostgauge matches 2000.. on passengers if entity @s[tag=kartsaddle] run function kartmobil:s2c-value/state-boost/on-set-1
execute if score @s kartboostgauge matches 2000.. run scoreboard players set @s kartboosttime 20

scoreboard players set @s kartboostgauge 0

tag @s remove kart-mario-a
tag @s remove kart-mario-d