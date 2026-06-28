



#execute if entity @s[tag=kart-boost-flame,tag=!kart-boost-flame-show] run data modify entity @s transformation set from entity @s data.boost-transform-start
data modify entity @s transformation.scale[1] set value 0f
data modify entity @s interpolation_duration set from entity @s data.interpolation_duration_hide
data modify entity @s start_interpolation set value 0

tag @s remove kart-boost-flame-show

tag @s add kart-boost-flame-hide-finish


