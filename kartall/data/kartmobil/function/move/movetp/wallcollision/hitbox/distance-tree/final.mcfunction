scoreboard players set #wall-condition kartcollisiontime 0
execute facing entity @s feet store result score #wall-condition kartcollisiontime run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/wall-condition

execute if score #from-projection kartcollisiontime matches 1 if score #wall-condition kartcollisiontime matches 1 run scoreboard players set #is-projection-success kartcollisiontime 1
execute if score #from-projection kartcollisiontime matches 1 run return 0

execute if score #wall-condition kartcollisiontime matches 0 run return 0

execute if score #wall-hitbox-mode kartcollisiontime matches 1 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode1

execute if score #wall-hitbox-mode kartcollisiontime matches 2 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode2





