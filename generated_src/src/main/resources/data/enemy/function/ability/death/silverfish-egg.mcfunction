summon marker ~ ~ ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:5,spawn_count:5,model:silverfish-baby}}
data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
execute store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
tag @n[tag=mob-gen,tag=not-allocated] remove not-allocated