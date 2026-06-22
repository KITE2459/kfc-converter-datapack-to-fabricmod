summon marker ~ ~ ~ {Tags:[enemy.dark-field,enemy.dark-field.new]}
scoreboard players set @n[type=marker,tag=enemy.dark-field.new,distance=..1,sort=nearest,limit=1] enemy.dark-field.timer 100
tag @n[type=marker,tag=enemy.dark-field.new,distance=..1,sort=nearest,limit=1] remove enemy.dark-field.new
