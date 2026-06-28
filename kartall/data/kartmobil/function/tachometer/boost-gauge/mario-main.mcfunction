#타코미터
data remove storage kartactbar message

execute if entity @s[tag=!kart-drifting] run data modify storage kartactbar message append value [{translate:"MINI  ","color":"gray","bold":false},{translate:"|","color":"yellow","bold":false,font:"minecraft:include/default"}]
execute if entity @s[tag=kart-drifting] run data modify storage kartactbar message append value [{translate:"MINI  ","color":"aqua","bold":false},{translate:"|","color":"yellow","bold":false,font:"minecraft:include/default"}]

#마리오는 속도계 없음
function kartmobil:tachometer/boost-gauge/left-right/mario-leftgauge
function kartmobil:tachometer/boost-gauge/left-right/mario-rightgauge

execute if entity @s[tag=!kart-drifting] run data modify storage kartactbar message append value [{translate:"|","color":"yellow","bold":false,font:"minecraft:include/default"},{translate:" TURBO","color":"gray","bold":false,font:"minecraft:default"}]
execute if entity @s[tag=kart-drifting] run data modify storage kartactbar message append value [{translate:"|","color":"yellow","bold":false,font:"minecraft:include/default"},{translate:" TURBO","color":"aqua","bold":false,font:"minecraft:default"}]

title @a[distance=..5,tag=kart-listener] actionbar [{"nbt":"message[]","storage":"kartactbar","interpret":true,"separator":{text:""}}]