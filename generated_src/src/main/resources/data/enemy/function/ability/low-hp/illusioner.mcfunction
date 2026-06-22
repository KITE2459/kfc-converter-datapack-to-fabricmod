summon marker ~ ~ ~ {Tags:[mob-gen,not-allocated],data:{spawn_cooldown:10,spawn_count:3,model:illusioner-illusion}}
data modify entity @n[tag=mob-gen,tag=not-allocated] Rotation set from entity @s Rotation
execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.tick run data get entity @s data.spawn_cooldown
execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.count run data get entity @s data.spawn_cooldown
execute as @n[tag=mob-gen,tag=not-allocated] store result score @s mob_gen.number run data get entity @s data.spawn_count
execute store result score @n[tag=mob-gen,tag=not-allocated] enemy.progress run scoreboard players get @s enemy.progress
execute as @n[tag=mob-gen,tag=not-allocated] run tag @s remove not-allocated

scoreboard players add @s enemy.hp 500
data modify entity @s data.speed set value 0.2
tp @n[tag=map.spawn_point]
scoreboard players set @s enemy.progress 0


scoreboard players set @s enemy.skill-trigger.hp -1