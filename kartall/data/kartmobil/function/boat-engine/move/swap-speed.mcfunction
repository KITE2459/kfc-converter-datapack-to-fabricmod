scoreboard players operation #temp boat-move-x = @n[tag=carA,type=#kartmobil:kartmobil] boat-move-x
scoreboard players operation @n[tag=carA,type=#kartmobil:kartmobil] boat-move-x = @n[tag=carB,type=#kartmobil:kartmobil] boat-move-x
scoreboard players operation @n[tag=carB,type=#kartmobil:kartmobil] boat-move-x = #temp boat-move-x

scoreboard players operation #temp boat-move-z = @n[tag=carA,type=#kartmobil:kartmobil] boat-move-z
scoreboard players operation @n[tag=carA,type=#kartmobil:kartmobil] boat-move-z = @n[tag=carB,type=#kartmobil:kartmobil] boat-move-z
scoreboard players operation @n[tag=carB,type=#kartmobil:kartmobil] boat-move-z = #temp boat-move-z


scoreboard players set @s kartcollisiontime 8
scoreboard players set @n[tag=carB,type=#kartmobil:kartmobil] kartcollisiontime 8