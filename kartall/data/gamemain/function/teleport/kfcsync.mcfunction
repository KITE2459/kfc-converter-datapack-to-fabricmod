# execute if score @s kartcollisiontime matches 1..15 run tag @a[tag=needsync] remove needsync


# execute as @e[tag=kartmodel,type=#kartmobil:kartmodels] store success entity @s OnGround byte 1 store success score @s rotafix unless score @s rotafix matches 1
# execute as @a store success entity @s OnGround byte 1 store success score @s rotafix unless score @s rotafix matches 1

kfcplayersync