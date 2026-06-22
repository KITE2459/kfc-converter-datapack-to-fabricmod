execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=2.77,dz=1.6] run return 1
execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.85 ~-0.5 ~-0.85 if entity @n[type=marker,tag=sword-hit-marker,dx=1.7,dy=1.5,dz=1.7] run return 1
execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=2.7,dz=1.6] run return 1
execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-1.3 ~0 ~-1.3 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=2.95,dz=1.6] run return 1
execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=3.9,dz=1.6] run return 1
execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.7 ~-0.7 ~-0.7 if entity @n[type=marker,tag=sword-hit-marker,dx=1.4,dy=1.3,dz=1.4] run return 1
execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.95 ~-0.5 ~-0.95 if entity @n[type=marker,tag=sword-hit-marker,dx=1.9,dy=1.5,dz=1.9] run return 1
execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.7 ~-0.5 ~-0.7 if entity @n[type=marker,tag=sword-hit-marker,dx=1.4,dy=1.5,dz=1.4] run return 1
execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-1.475 ~0 ~-1.475 if entity @n[type=marker,tag=sword-hit-marker,dx=2.95,dy=3.2,dz=2.95] run return 1
execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.8 ~0 ~-0.8 if entity @n[type=marker,tag=sword-hit-marker,dx=1.6,dy=2.99,dz=1.6] run return 1
execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-1.19824 ~0 ~-1.19824 if entity @n[type=marker,tag=sword-hit-marker,dx=2.39648,dy=2.6,dz=2.39648] run return 1
execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-1.2 ~-0.1 ~-1.2 if entity @n[type=marker,tag=sword-hit-marker,dx=2.4,dy=1.9,dz=2.4] run return 1
execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.7 ~-0.2 ~-0.7 if entity @n[type=marker,tag=sword-hit-marker,dx=1.4,dy=1.8,dz=1.4] run return 1
execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.95 ~0 ~-0.95 if entity @n[type=marker,tag=sword-hit-marker,dx=1.9,dy=3.9,dz=1.9] run return 1
execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.85 ~0 ~-0.85 if entity @n[type=marker,tag=sword-hit-marker,dx=1.7,dy=3.4,dz=1.7] run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.65 ~-0.025 ~-0.65 if entity @n[type=marker,tag=sword-hit-marker,dx=1.3,dy=1.975,dz=1.3] run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.83 ~0 ~-0.83 if entity @n[type=marker,tag=sword-hit-marker,dx=1.66,dy=3.145,dz=1.66] run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.925 ~0 ~-0.925 if entity @n[type=marker,tag=sword-hit-marker,dx=1.85,dy=4.025,dz=1.85] if function tower:bullet/move/tag run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-1.025 ~0 ~-1.025 if entity @n[type=marker,tag=sword-hit-marker,dx=2.3,dy=4.6,dz=2.3] if function tower:bullet/move/tag run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~-1.1 ~0 ~-1.1 if entity @n[type=marker,tag=sword-hit-marker,dx=2.4,dy=5.15,dz=2.4] if function tower:bullet/move/tag run return 1
execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-1.2 ~ ~-1.2 if entity @n[type=marker,tag=sword-hit-marker,dx=1.4,dy=1.4,dz=1.4] run return 1
return 0
