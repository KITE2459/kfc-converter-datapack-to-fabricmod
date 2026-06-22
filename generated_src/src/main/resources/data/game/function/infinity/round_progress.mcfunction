scoreboard players set #infinity_end time 0
function game:infinity/process_wave with storage game setup
execute if score timer gameState >= #infinity_end time run scoreboard players set stage gameState 3
execute if score timer gameState >= #infinity_end time run give @a yellow_dye[item_name=[{text:"라운드 스킵",bold:true}]] 1
execute if score timer gameState >= #infinity_end time run scoreboard players operation #temp time = #infinity_end time
execute if score timer gameState >= #infinity_end time run scoreboard players add #temp time 300
