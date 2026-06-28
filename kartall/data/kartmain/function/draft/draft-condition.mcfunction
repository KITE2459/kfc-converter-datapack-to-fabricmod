tag @e[tag=kart-draft-target,type=#kartmobil:kartmobil] remove kart-draft-target

execute rotated ~ 0 positioned ^ ^ ^15 run tag @e[tag=kartmobil,tag=!kartself,distance=..15,limit=1,type=#kartmobil:kartmobil] add kart-draft-target

execute rotated ~ 0 positioned ^1 ^ ^ if entity @e[tag=kart-draft-target,tag=!kartself,distance=..1,type=#kartmobil:kartmobil] run return 0
execute rotated ~ 0 positioned ^-1 ^ ^ if entity @e[tag=kart-draft-target,tag=!kartself,distance=..1,type=#kartmobil:kartmobil] run return 0
execute if entity @e[tag=kart-draft-target,tag=!kartself,type=#kartmobil:kartmobil] run return 1