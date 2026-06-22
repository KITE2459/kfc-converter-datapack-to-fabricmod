# 카운트다운
execute if score timer gameState matches 0 run title @a title {text:"5",color:yellow,bold:true}
execute if score timer gameState matches 0 run title @a subtitle {text:"",color:white}
execute if score timer gameState matches 0 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
execute if score timer gameState matches 0 as @a at @s run clear @a yellow_dye
execute if score timer gameState matches 20 run title @a title {text:"4",color:yellow,bold:true}
execute if score timer gameState matches 20 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
execute if score timer gameState matches 40 run title @a title {text:"3",color:yellow,bold:true}
execute if score timer gameState matches 40 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
execute if score timer gameState matches 60 run title @a title {text:"2",color:yellow,bold:true}
execute if score timer gameState matches 60 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
execute if score timer gameState matches 80 run title @a title {text:"1",color:yellow,bold:true}
execute if score timer gameState matches 80 as @a at @s run playsound ui.button.click weather @s ~ ~ ~
execute if score timer gameState matches 100 store result score #round money run data get storage game setup.each_money
execute if score timer gameState matches 100 run scoreboard players operation #round money *= game wave
execute if score timer gameState matches 100 unless score game wave matches ..1 run scoreboard players operation @a money += #round money
execute if score timer gameState matches 100 run title @a title {text:"시작!",color:green,bold:true}
execute if score timer gameState matches 100 run title @a subtitle [{text:"라운드 보너스: ",color:white,bold:true},{score:{name:"#round",objective:"money"},color:yellow,bold:false}]
execute if score timer gameState matches 100 as @a at @s run playsound minecraft:item.goat_horn.sound.0 weather @s ~ ~ ~
execute if score timer gameState matches 100 as @e[tag=mine,tag=tower.data,scores={tower.level=0..}] run function game:game/mine_income

# 라운드 진행으로 전환
execute if score timer gameState matches 100 run scoreboard players set stage gameState 2
execute if score timer gameState matches 100 run scoreboard players set timer gameState 0