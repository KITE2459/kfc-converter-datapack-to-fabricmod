execute if entity @s[tag=kartmobil] on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kart-boost-flame,tag=!kart-boost-flame-show,type=#kartmobil:kartmodels] run function kartmobil:move/boost/boost-effect/show-boost-model/show-by-case/show-flame
execute if entity @s[type=player] on vehicle on vehicle on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kart-boost-flame,tag=!kart-boost-flame-show,type=#kartmobil:kartmodels] run function kartmobil:move/boost/boost-effect/show-boost-model/show-by-case/show-flame

execute if entity @s[tag=shadow-main] on passengers if entity @s[tag=kart-boost-flame,tag=!kart-boost-flame-show,type=#kartmobil:kartmodels] run function kartmobil:move/boost/boost-effect/show-boost-model/show-by-case/show-flame

# execute if entity @s[tag=!kartmobil,type=!player] as @e[tag=kart-boost-flame,tag=!kart-boost-flame-show,distance=..0.3,type=#kartmobil:kartmodels] run function kartmobil:move/boost/boost-effect/show-boost-model/show-by-case/show-flame

execute if entity @s[tag=garage-show] as @e[tag=kart-boost-flame,tag=!kart-boost-flame-show,distance=..0.3,type=#kartmobil:kartmodels] run function kartmobil:move/boost/boost-effect/show-boost-model/show-by-case/show-flame