
execute if score #temp boat-move-x matches 5000.. at @s run tp @s ~0.05 ~ ~
execute if score #temp boat-move-x matches 5000.. run scoreboard players remove #temp boat-move-x 5000

execute if score #temp boat-move-x matches ..-5000 at @s run tp @s ~-0.05 ~ ~
execute if score #temp boat-move-x matches ..-5000 run scoreboard players add #temp boat-move-x 5000

execute unless score #temp boat-move-x matches -5000..5000 run function kartmobil:boat-engine/move/move-axis/x