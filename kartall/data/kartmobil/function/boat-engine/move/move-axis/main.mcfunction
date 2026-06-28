
scoreboard players set #kartspeed kartmove 25

scoreboard players operation #temp boat-move-x = @s boat-move-x
scoreboard players operation #temp boat-move-z = @s boat-move-z

scoreboard players operation @s kartmove-remain = #temp boat-move-x
scoreboard players operation @s kartmove-remain /= #kart5 kartmain
execute if score @s kartmove-remain matches ..-1 run scoreboard players operation @s kartmove-remain *= #kart-1 kartmain

execute if score #temp boat-move-x matches 5000.. rotated -90 0 run function kartmobil:move/movetp/moveforward
execute if score #temp boat-move-x matches ..-5000 rotated 90 0 run function kartmobil:move/movetp/moveforward

scoreboard players operation @s kartmove-remain = #temp boat-move-z
scoreboard players operation @s kartmove-remain /= #kart5 kartmain
execute if score @s kartmove-remain matches ..-1 run scoreboard players operation @s kartmove-remain *= #kart-1 kartmain

execute if score #temp boat-move-z matches 5000.. rotated 0 0 run function kartmobil:move/movetp/moveforward
execute if score #temp boat-move-z matches ..-5000 rotated 180 0 run function kartmobil:move/movetp/moveforward