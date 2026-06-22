$summon area_effect_cloud ~ -60 ~ {Particle:{type:"dust",color:[0.369,0.486,0.086],scale:2},Radius:$(range)f,Duration:$(time),WaitTime:0,Tags:[floor-$(range),bullet.data,not-allocated,bullet.floor]}
$scoreboard players set @n[tag=not-allocated] bullet.floor.range $(range)
$scoreboard players set @n[tag=not-allocated] bullet.floor.time $(time)
execute store result score @n[tag=not-allocated] bullet.attack run data get storage tower temp.Bullet.attribute.floor.attack
execute store result score @n[tag=not-allocated] bullet.attribute.freeze run data get storage tower temp.Bullet.attribute.floor.attribute.freeze
execute store result score @n[tag=not-allocated] bullet.attribute.poison run data get storage tower temp.Bullet.attribute.floor.attribute.poison
execute store result score @n[tag=not-allocated] bullet.attribute.flame run data get storage tower temp.Bullet.attribute.floor.attribute.flame
execute store result score @n[tag=not-allocated] bullet.attribute.bleed run data get storage tower temp.Bullet.attribute.floor.attribute.bleed
execute store result score @n[tag=not-allocated] bullet.attribute.stun run data get storage tower temp.Bullet.attribute.floor.attribute.stun
execute store result score @n[tag=not-allocated] bullet.attribute.weakness run data get storage tower temp.Bullet.attribute.floor.attribute.weakness
tag @n[tag=not-allocated] remove not-allocated