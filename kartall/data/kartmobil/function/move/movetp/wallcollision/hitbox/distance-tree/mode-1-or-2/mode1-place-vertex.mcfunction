execute rotated ~ 0 positioned ^ ^ ^0.5 as @e[tag=kart-vertex-additional,tag=kart-vertex-front,limit=1,distance=..1,type=area_effect_cloud] run function kartmobil:move/movetp/tp-bug-fix/tp
execute rotated ~180 0 positioned ^ ^ ^0.5 as @e[tag=kart-vertex-additional,tag=kart-vertex-rear,limit=1,distance=..1,type=area_effect_cloud] run function kartmobil:move/movetp/tp-bug-fix/tp

execute rotated ~-90 0 positioned ^ ^ ^0.5 as @e[tag=kart-vertex-additional,tag=kart-vertex-left,limit=1,distance=..1,type=area_effect_cloud] run function kartmobil:move/movetp/tp-bug-fix/tp
execute rotated ~90 0 positioned ^ ^ ^0.5 as @e[tag=kart-vertex-additional,tag=kart-vertex-right,limit=1,distance=..1,type=area_effect_cloud] run function kartmobil:move/movetp/tp-bug-fix/tp
 
execute if score @s kart-hitbox-radius matches 11.. run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode1-place-vertex-11
execute if score @s kart-hitbox-radius matches ..10 run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode1-place-vertex-10
