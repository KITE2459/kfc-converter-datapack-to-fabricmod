execute if score @s garage-time matches 35..50 positioned ~ ~-0.5 ~ run scoreboard players set #term kart-run-anime 5

##sidite 분기
execute if score @s garage-time matches 35 positioned ~ ~-0.5 ~ run function kartmobil:move/run-anime/frame-first-vanilla
execute if score @s garage-time matches 40 positioned ~ ~-0.5 ~ run function kartmobil:move/run-anime/frame-mid-vanilla
execute if score @s garage-time matches 45 positioned ~ ~-0.5 ~ run function kartmobil:move/run-anime/frame-last-vanilla
execute if score @s garage-time matches 50 positioned ~ ~-0.5 ~ run function kartmobil:move/run-anime/frame-mid-vanilla

execute if score @s garage-time matches 50 positioned ~ ~-0.5 ~ run scoreboard players set @s garage-time 30