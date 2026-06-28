data merge entity @s {start_interpolation:0,interpolation_duration:25}

#execute store result entity @s transformation.scale[1] byte 0.00000078125 run data get entity @s transformation.scale[1] 10000
data modify entity @s transformation.scale[1] set value 0

tag @s add kart-boost-flame-slow-fade