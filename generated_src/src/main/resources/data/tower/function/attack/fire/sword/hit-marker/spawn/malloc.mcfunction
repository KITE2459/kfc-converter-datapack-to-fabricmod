execute store result score @s bullet.attack run data get storage tower temp.attack
execute store result score @s bullet.life run data get storage tower temp.Bullet.life 4.0

execute store result score @s bullet.attribute.freeze run data get storage tower temp.Bullet.attribute.freeze
execute store result score @s bullet.attribute.poison run data get storage tower temp.Bullet.attribute.poison
execute store result score @s bullet.attribute.flame run data get storage tower temp.Bullet.attribute.flame
execute store result score @s bullet.attribute.bleed run data get storage tower temp.Bullet.attribute.bleed
execute store result score @s bullet.attribute.stun run data get storage tower temp.Bullet.attribute.stun


data remove storage tower temp.hitscan_area
data remove storage tower temp.hitscan_area_2
scoreboard players set @s hitscan-marker.area 0
execute if data storage tower temp.Bullet.hitscan.area store result score @s hitscan-marker.area run data get storage tower temp.Bullet.hitscan.area
execute if data storage tower temp.Bullet.hitscan.area store result storage tower temp.hitscan_area float 1.0 run data get storage tower temp.Bullet.hitscan.area
data modify entity @s Rotation set from entity @n[tag=tower.muzzle] Rotation