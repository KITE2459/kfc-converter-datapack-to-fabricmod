scoreboard players set @e[tag=tower.data,distance=..5] tower.state.stun 60
summon marker ~ ~ ~ {Tags:[enemy.dark-field,enemy.dark-field.large,enemy.dark-field.new]}
scoreboard players set @n[type=marker,tag=enemy.dark-field.new,distance=..1,sort=nearest,limit=1] enemy.dark-field.timer 100
tag @n[type=marker,tag=enemy.dark-field.new,distance=..1,sort=nearest,limit=1] remove enemy.dark-field.new
particle minecraft:explosion ~ ~ ~ 1 1 1 0 10 force @a
playsound entity.generic.explode record @a ~ ~ ~ 1.0 1.0
