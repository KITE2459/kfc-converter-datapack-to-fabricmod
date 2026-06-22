# 기존 히트박스에 크기 + 0.25 (0.125*2) 만큼 확장된 히트박스 영역을 감지하는 함수입니다.
execute if entity @s[tag=enemy.hitbox_type_breeze] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.02,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.77,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_cave_spider] positioned ~-0.475 ~-0.5 ~-0.475 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.05 ~0 ~-0.05 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_creeper] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.95,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.7,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_drowned] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.2,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.95,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_enderman] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=2.15,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.9,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_endermite] positioned ~-0.325 ~-0.7 ~-0.325 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.35 ~0 ~-0.35 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_phantom] positioned ~-0.575 ~-0.5 ~-0.575 if entity @n[type=marker,tag=hitscan-marker,dx=1.15,dy=0.75,dz=1.15] run return 1
execute if entity @s[tag=enemy.hitbox_type_rabbit] positioned ~-0.325 ~-0.5 ~-0.325 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.35 ~0 ~-0.35 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_ravager] positioned ~-1.1 ~0 ~-1.1 if entity @n[type=marker,tag=hitscan-marker,dx=2.2,dy=2.45,dz=2.2] run return 1
execute if entity @s[tag=enemy.hitbox_type_skeleton] positioned ~-0.425 ~0 ~-0.425 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.24,dz=0] positioned ~-0.15 ~0 ~-0.15 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.99,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_skeleton_horse] positioned ~-0.82324 ~0 ~-0.82324 if entity @n[type=marker,tag=hitscan-marker,dx=1.64648,dy=1.85,dz=1.64648] run return 1
execute if entity @s[tag=enemy.hitbox_type_spider] positioned ~-0.825 ~-0.1 ~-0.825 if entity @n[type=marker,tag=hitscan-marker,dx=1.65,dy=1.15,dz=1.65] run return 1
execute if entity @s[tag=enemy.hitbox_type_vex] positioned ~-0.325 ~-0.2 ~-0.325 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.35 ~0 ~-0.35 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_warden] positioned ~-0.575 ~0 ~-0.575 if entity @n[type=marker,tag=hitscan-marker,dx=1.15,dy=3.15,dz=1.15] run return 1
execute if entity @s[tag=enemy.hitbox_type_wither_skeleton] positioned ~-0.475 ~0 ~-0.475 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.65,dz=0] positioned ~-0.05 ~0 ~-0.05 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.4,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_baby] positioned ~-0.275 ~-0.025 ~-0.275 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0.25,dz=0] positioned ~-0.45 ~0 ~-0.45 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=0,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_1] positioned ~-0.455 ~0 ~-0.455 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.395,dz=0] positioned ~-0.09 ~0 ~-0.09 if entity @n[type=marker,tag=hitscan-marker,dx=0,dy=1.145,dz=0] run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_5] positioned ~-0.525 ~0 ~-0.525 if entity @n[type=marker,tag=hitscan-marker,dx=0.025,dy=2.175,dz=0.025] run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_scale_1_8] positioned ~-0.615 ~0 ~-0.615 if entity @n[type=marker,tag=hitscan-marker,dx=0.115,dy=2.55,dz=0.115] run return 1
execute if entity @s[tag=enemy.hitbox_type_zombie_scale_2] positioned ~-0.675 ~0 ~-0.675 if entity @n[type=marker,tag=hitscan-marker,dx=0.2,dy=2.9,dz=0.2] run return 1
execute if entity @s[tag=enemy.hitbox_type_zoglin] positioned ~-0.825 ~ ~-0.825 if entity @n[type=marker,tag=hitscan-marker,dx=0.65,dy=0.65,dz=0.65] run return 1
return 0
