execute if entity @s[tag=kartmobil] on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kart-run-anime,type=#kartmobil:kartmodels] run function kartmobil:move/run-anime/show-by-case/frame-first
execute if entity @s[type=player] on vehicle on vehicle on passengers if entity @s[tag=kartmodelsaddle,type=item_display] on passengers if entity @s[tag=kart-run-anime,type=#kartmobil:kartmodels] run function kartmobil:move/run-anime/show-by-case/frame-first

execute if entity @s[tag=shadow-main] on passengers if entity @s[tag=kart-run-anime,type=#kartmobil:kartmodels] run function kartmobil:move/run-anime/show-by-case/frame-first

execute if entity @s[tag=garage-show] as @e[tag=kart-run-anime,distance=..0.3,type=#kartmobil:kartmodels] run function kartmobil:move/run-anime/show-by-case/frame-first