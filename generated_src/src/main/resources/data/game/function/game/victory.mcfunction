scoreboard players set timer gameState 0
scoreboard players set stage gameState 0
title @a title {"text":"Victory","color":"gold","bold":true}
execute store result score #temp info run data get storage game setup.each_money 0.4
scoreboard players operation #temp info *= game wave
scoreboard players operation coin info += #temp info
title @a subtitle [{text:"승리 보상: ",color:green},{score:{name:"#temp",objective:info},color:yellow}]
playsound minecraft:item.goat_horn.sound.1 master @a ~ ~ ~ 1 1 1
scoreboard players set game gameState 0

scoreboard objectives setdisplay sidebar info