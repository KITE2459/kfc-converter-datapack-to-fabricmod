execute store result score @s bullet.attack run data get storage tower temp.attack
execute store result score @s bullet.life run data get storage tower temp.Bullet.life
execute store result score @n[tag=bullet.core] bullet.speed run data get storage tower temp.Bullet.speed 4

execute store result score @n[tag=bullet.data] bullet.attribute.freeze run data get storage tower temp.Bullet.attribute.freeze
execute store result score @n[tag=bullet.data] bullet.attribute.poison run data get storage tower temp.Bullet.attribute.poison
execute store result score @n[tag=bullet.data] bullet.attribute.flame run data get storage tower temp.Bullet.attribute.flame
execute store result score @n[tag=bullet.data] bullet.attribute.bleed run data get storage tower temp.Bullet.attribute.bleed
execute store result score @n[tag=bullet.data] bullet.attribute.stun run data get storage tower temp.Bullet.attribute.stun
execute store result score @n[tag=bullet.data] bullet.attribute.weakness run data get storage tower temp.Bullet.attribute.weakness

execute if data storage tower temp.Bullet.attribute.floor run tag @n[tag=bullet.data] add bullet.flooring
execute store result score @n[tag=bullet.data] bullet.floor.range run data get storage tower temp.Bullet.attribute.floor.range
execute store result score @n[tag=bullet.data] bullet.floor.time run data get storage tower temp.Bullet.attribute.floor.time
data modify entity @n[tag=bullet.data] data.Bullet.flooring set from storage tower temp.Bullet.attribute.floor