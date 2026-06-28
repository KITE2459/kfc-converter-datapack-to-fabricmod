#타코미터
data remove storage kartactbar message

# ERS 게이지 표시
data modify storage kartactbar message append value [{translate:"ERS ","color":"green","bold":false}]
execute store result storage kartmain temp int 0.1 run scoreboard players get @s kart-ers-gauge
execute store result score #temp kart-ers-gauge run data get storage kartmain temp
execute unless score @s kart-ers matches 1 if score #temp kart-ers-gauge matches ..9 run data modify storage kartactbar message append value [{color:green,bold:false,text:"[00"},{"score":{"name":"#temp","objective":"kart-ers-gauge"},"color":"green","bold":false},{translate:"] ","color":"green","bold":false}]
execute unless score @s kart-ers matches 1 if score #temp kart-ers-gauge matches 10..99 run data modify storage kartactbar message append value [{color:green,bold:false,text:"[0"},{"score":{"name":"#temp","objective":"kart-ers-gauge"},"color":"green","bold":false},{translate:"] ","color":"green","bold":false}]
execute unless score @s kart-ers matches 1 if score #temp kart-ers-gauge matches 100 run data modify storage kartactbar message append value [{color:green,bold:false,text:"["},{"score":{"name":"#temp","objective":"kart-ers-gauge"},"color":"green","bold":false},{translate:"] ","color":"green","bold":false}]
execute if score @s kart-ers matches 1 if score #temp kart-ers-gauge matches ..9 run data modify storage kartactbar message append value [{color:yellow,bold:false,text:"[00"},{"score":{"name":"#temp","objective":"kart-ers-gauge"},"color":"yellow","bold":false},{translate:"] ","color":"yellow","bold":false}]
execute if score @s kart-ers matches 1 if score #temp kart-ers-gauge matches 10..99 run data modify storage kartactbar message append value [{color:yellow,bold:false,text:"[0"},{"score":{"name":"#temp","objective":"kart-ers-gauge"},"color":"yellow","bold":false},{translate:"] ","color":"yellow","bold":false}]
execute if score @s kart-ers matches 1 if score #temp kart-ers-gauge matches 100 run data modify storage kartactbar message append value [{color:yellow,bold:false,text:"["},{"score":{"name":"#temp","objective":"kart-ers-gauge"},"color":"yellow","bold":false},{translate:"] ","color":"yellow","bold":false}]

#RPM 역산
# 2760
scoreboard players set #temp kartboostgauge 3600
scoreboard players operation #temp kartboostgauge *= @s kartboostcount
execute store result score @s kartboostgauge run scoreboard players get @s kartmove
scoreboard players operation @s kartboostgauge -= #temp kartboostgauge
execute store result storage minecraft:kartmain gear-rpm int 0.1170 run scoreboard players get @s kartboostgauge
execute store result score @s kartboostgauge run data get storage minecraft:kartmain gear-rpm
scoreboard players set #temp kartboostgauge 13
scoreboard players set #temp kartboostcount 8
scoreboard players operation #temp kartboostcount -= @s kartboostcount
scoreboard players operation #temp kartboostgauge *= #temp kartboostcount
scoreboard players operation @s kartboostgauge -= #temp kartboostgauge

function kartmobil:tachometer/boost-gauge/left-right/f1-leftgauge

data modify storage kartactbar message append value [{translate:"// ","color":"aqua","bold":false}]

#속도계 수치를 2배로 표시
execute on passengers if entity @s[tag=kart-old-velocity] run scoreboard players operation #kart-speed-double kartmove = @s kartmove-remain
scoreboard players operation #kart-speed-double kartmove /= #kart139 kartmain

data modify storage kartactbar message append value [{"score":{"name":"#kart-speed-double","objective":"kartmove"},"color":"aqua","bold":false},{translate:"km/h ","color":"aqua","bold":false}]

data modify storage kartactbar message append value [{translate:"\\\\","color":"aqua","bold":false}]

function kartmobil:tachometer/boost-gauge/left-right/f1-rightgauge

data modify storage kartactbar message append value [{translate:" ","color":"yellow","bold":false},{translate:"GEAR ","color":"yellow","bold":false}]
execute if score @s kartboostcount matches 1..7 run data modify storage kartactbar message append value [{color:yellow,bold:false,text:"["},{"score":{"name":"@s","objective":"kartboostcount"},"color":"aqua","bold":false},{translate:"] ","color":"yellow","bold":false}]
execute if score @s kartboostcount matches 8 run data modify storage kartactbar message append value [{color:yellow,bold:false,text:"["},{"score":{"name":"@s","objective":"kartboostcount"},"color":"red","bold":false},{translate:"] ","color":"yellow","bold":false}]

title @a[distance=..5,tag=kart-listener] actionbar [{"nbt":"message[]","storage":"kartactbar","interpret":true,"separator":{text:""}}]
