#초기화
data modify storage timeattack:timeattack-record temp-record-for-text set value {record:2147483647, record-text:{translate:"기록되지 않음","color":"red"}}
#불러오기
$data modify storage timeattack:timeattack-record temp-record-for-text set from storage timeattack:timeattack-record map$(tempmap)-record

execute store result score @s timermain run data get storage timeattack:timeattack-record temp-record-for-text.record
data modify entity @s text set from storage timeattack:timeattack-record temp-record-for-text.record-text
