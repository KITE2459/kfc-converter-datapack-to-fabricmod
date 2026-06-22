scoreboard players display name round_value sidebar-text [{text:"라운드 ",color:white},{score:{name:"game",objective:"wave"},color:yellow,bold:true}]

execute if score stage gameState matches 2 run scoreboard players operation #remain_ticks time = #infinity_end time
execute if score stage gameState matches 2 run scoreboard players add #remain_ticks time 300
execute if score stage gameState matches 2 run scoreboard players operation #remain_ticks time -= timer gameState
execute if score stage gameState matches 3 run scoreboard players operation #remain_ticks time = #temp time
execute if score stage gameState matches 3 run scoreboard players operation #remain_ticks time -= timer gameState
execute if score #remain_ticks time matches ..-1 run scoreboard players set #remain_ticks time 0
