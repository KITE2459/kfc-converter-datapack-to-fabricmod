$data modify storage devattack:dev-attack-record tempmap set value $(number)
execute store result storage devattack:dev-attack-record tempdev int 1 run scoreboard players get #dev dev-count

function devbattle:system/devrecord/changerecordtextmacro with storage devattack:dev-attack-record
