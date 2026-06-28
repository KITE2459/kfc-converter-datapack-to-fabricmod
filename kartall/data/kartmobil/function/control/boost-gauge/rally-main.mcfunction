#타코미터
data remove storage kartactbar message

data modify storage kartactbar message append value [{translate:"   ","color":"yellow","bold":false},{"keybind":"key.left","color":"aqua","bold":false},{translate:" or ","color":"yellow","bold":false},{"keybind":"key.right","color":"aqua","bold":false},{translate:" ","color":"yellow","bold":false}]

#RPM 역산
# execute if score @s kartboostcount matches 1 store result storage minecraft:kartmain gear-rpm int 0.1082 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 2 store result storage minecraft:kartmain gear-rpm int 0.0738 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 3 store result storage minecraft:kartmain gear-rpm int 0.0580 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 4 store result storage minecraft:kartmain gear-rpm int 0.0514 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 5 store result storage minecraft:kartmain gear-rpm int 0.0461 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 6 store result storage minecraft:kartmain gear-rpm int 0.0427 run scoreboard players get @s kartmove

#로감이 손본 수치
# execute if score @s kartboostcount matches 1 store result storage minecraft:kartmain gear-rpm int 0.095 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 2 store result storage minecraft:kartmain gear-rpm int 0.065 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 3 store result storage minecraft:kartmain gear-rpm int 0.049 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 4 store result storage minecraft:kartmain gear-rpm int 0.046 run scoreboard players get @s kartmove
# execute if score @s kartboostcount matches 5 store result storage minecraft:kartmain gear-rpm int 0.043 run scoreboard players get @s kartmove

#롤백
execute if score @s kartboostcount matches 1 store result storage minecraft:kartmain gear-rpm int 0.095 run scoreboard players get @s kartmove
execute if score @s kartboostcount matches 2 store result storage minecraft:kartmain gear-rpm int 0.065 run scoreboard players get @s kartmove
execute if score @s kartboostcount matches 3 store result storage minecraft:kartmain gear-rpm int 0.052 run scoreboard players get @s kartmove
execute if score @s kartboostcount matches 4 store result storage minecraft:kartmain gear-rpm int 0.047 run scoreboard players get @s kartmove
execute if score @s kartboostcount matches 5 store result storage minecraft:kartmain gear-rpm int 0.043 run scoreboard players get @s kartmove

execute store result score @s kartboostgauge run data get storage minecraft:kartmain gear-rpm

function kartmobil:control/boost-gauge/left-right/rally-leftgauge

data modify storage kartactbar message append value [{translate:"// ","color":"aqua","bold":false}]

#속도계 수치를 2배로 표시
execute on passengers if entity @s[tag=kart-old-velocity] run scoreboard players operation #kart-speed-double kartmove = @s kartmove-remain
scoreboard players operation #kart-speed-double kartmove /= #kart139 kartmain

data modify storage kartactbar message append value [{"score":{"name":"#kart-speed-double","objective":"kartmove"},"color":"aqua","bold":false},{translate:"km/h ","color":"aqua","bold":false}]

data modify storage kartactbar message append value [{translate:"\\\\","color":"aqua","bold":false}]

function kartmobil:control/boost-gauge/left-right/rally-rightgauge

data modify storage kartactbar message append value [{translate:" ","color":"yellow","bold":false},{translate:"GEAR ","color":"yellow","bold":false},{translate:"[","color":"gray","bold":false}]
execute if score @s kartboostcount matches 1..4 run data modify storage kartactbar message append value [{"score":{"name":"@s","objective":"kartboostcount"},"color":"aqua","bold":false}]
execute if score @s kartboostcount matches 5 run data modify storage kartactbar message append value [{"score":{"name":"@s","objective":"kartboostcount"},"color":"red","bold":false}]
data modify storage kartactbar message append value [{translate:"]","color":"gray","bold":false}]

#,{translate:"단","color":"yellow","bold":false}

title @a[distance=..5,tag=kart-listener] actionbar [{"nbt":"message[]","storage":"kartactbar","interpret":true,"separator":{text:""}}]