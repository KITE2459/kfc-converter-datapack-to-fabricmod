scoreboard players set timer gameState 0
scoreboard players set stage gameState 0
title @a title {"text":"Defeat","color":"red","bold":true}
execute store result score #temp info run data get storage game setup.each_money 0.1
scoreboard players operation #temp info *= game wave
scoreboard players operation coin info += #temp info
title @a subtitle [{text:"패배 보상: ",color:green},{score:{name:"#temp",objective:info},color:yellow}]
playsound minecraft:item.goat_horn.sound.5 master @a ~ ~ ~ 1 1 1
scoreboard players set game gameState 0
kill @e[tag=enemy]
kill @e[tag=tower]
kill @e[tag=bullet]

scoreboard objectives setdisplay sidebar info