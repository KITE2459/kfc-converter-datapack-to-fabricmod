#임시 스토리지 초기화 후 만들기
data modify storage devattack:dev-attack-record temp-record set value {record:2147483647, record-text:{translate:"00:00:00","color":"aqua"}}
execute store result storage devattack:dev-attack-record temp-record.record int 1 run scoreboard players get #time timermain
data modify storage devattack:dev-attack-record temp-record.record-text set from storage timermain time

#맵1-개발자1-record 식으로 저장
$data modify storage devattack:dev-attack-record map$(tempmap)-dev$(tempdev)-record set from storage devattack:dev-attack-record temp-record

