execute if score @s trackselect-lap matches ..1 run scoreboard players set @s multi-best-time 2147483647
execute if score @s trackselect-lap matches ..1 run scoreboard players reset @s multi-last-pass-time

function multiplay:system/game/complete-race/calculate-milisec
#tellraw @s [{"text":"\n"},{"score":{"name":"@s","objective":"trackselect-lap"},"color":"yellow"},{"text":"/","color":"yellow"},{"score":{"name":"#max-lap","objective":"trackselect-lap"},"color":"yellow"}]

#현재시간 백업
scoreboard players operation #time multi-best-time = #time timermain

#랩타임
scoreboard players operation #time timermain -= @s multi-last-pass-time

function timerpack:convert-milisec
#tellraw @s [{"text":"LAP ","color":"yellow"},{"interpret":true,"nbt":"time","storage":"timermain"}]
tellraw @s [{"score":{"name":"@s","objective":"trackselect-lap"},"color":"yellow"},{"text":"/","color":"yellow"},{"score":{"name":"#max-lap","objective":"trackselect-lap"},"color":"yellow"},{"text":" | ","color":"yellow"},{"interpret":true,"nbt":"time","storage":"timermain"}]

#베타
scoreboard players operation @s multi-best-time < #time timermain
scoreboard players operation #time timermain = @s multi-best-time

#마지막 랩에는 랩타임, 베타, 완주시간 전부 표시
function timerpack:convert-milisec
execute if score @s trackselect-lap = #max-lap trackselect-lap run tellraw @s [{"text":"BEST | ","color":"yellow"},{"interpret":true,"nbt":"time","storage":"timermain"}]

scoreboard players operation @s multi-last-pass-time = #time multi-best-time
