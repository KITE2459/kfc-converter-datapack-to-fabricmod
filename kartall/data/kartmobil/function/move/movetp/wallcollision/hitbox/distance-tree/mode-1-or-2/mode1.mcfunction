execute positioned as @s as @e[tag=kart-vertex-all,limit=9,type=area_effect_cloud] run function kartmobil:move/movetp/tp-bug-fix/tp
execute positioned as @s on passengers if entity @s[tag=kartmodelsaddle] rotated as @s on vehicle run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode1-place-vertex

tag @n[tag=kart-vertex-all,distance=..2.6,type=area_effect_cloud] add kart-vertex-nearest

function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/mode-1-or-2/mode1-score

execute positioned as @s run function kartmobil:move/movetp/wallcollision/hitbox/distance-tree/push-tree/push

function kartmain:collision/rectangle-hitbox/finish-calc-additional