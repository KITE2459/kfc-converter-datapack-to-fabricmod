scoreboard players set #term kart-run-anime 3

##sidite 분기
execute if score @s kart-run-anime matches 3 run function kartmobil:move/run-anime/frame-first-vanilla
execute if score @s kart-run-anime matches 6 run function kartmobil:move/run-anime/frame-mid-vanilla
execute if score @s kart-run-anime matches 9 run function kartmobil:move/run-anime/frame-last-vanilla
execute if score @s kart-run-anime matches 12.. run function kartmobil:move/run-anime/frame-mid-vanilla

execute if score @s kart-run-anime matches 12.. run scoreboard players set @s kart-run-anime 0