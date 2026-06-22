$execute store result score #total_rounds temp run data get storage map $(map).$(difficulty).round
scoreboard players display name round_value sidebar-text [{text:"라운드 ",color:white},{score:{name:"game",objective:"wave"},color:yellow,bold:true},{text:"/",color:gray},{score:{name:"#total_rounds",objective:"temp"},color:yellow,bold:true}]

$execute if score stage gameState matches 2 store result score #round_end time run data get storage map $(map).$(difficulty).round[$(round)].end
execute if score stage gameState matches 2 run scoreboard players operation #remain_ticks time = #round_end time
execute if score stage gameState matches 2 run scoreboard players add #remain_ticks time 300
execute if score stage gameState matches 2 run scoreboard players operation #remain_ticks time -= timer gameState
execute if score stage gameState matches 3 run scoreboard players operation #remain_ticks time = #temp time
execute if score stage gameState matches 3 run scoreboard players operation #remain_ticks time -= timer gameState
execute if score #remain_ticks time matches ..-1 run scoreboard players set #remain_ticks time 0
