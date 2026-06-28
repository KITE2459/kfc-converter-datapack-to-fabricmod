scoreboard players add @s kart-run-anime-state 1
execute if score @s kart-run-anime-state matches 4.. run scoreboard players set @s kart-run-anime-state 0

##sidite 분기
execute if score @s kart-run-anime-state matches 0 run function kartmobil:move/run-anime/frame-first-vanilla
execute if score @s kart-run-anime-state matches 1 run function kartmobil:move/run-anime/frame-mid-vanilla
execute if score @s kart-run-anime-state matches 2 run function kartmobil:move/run-anime/frame-last-vanilla
execute if score @s kart-run-anime-state matches 3 run function kartmobil:move/run-anime/frame-mid-vanilla