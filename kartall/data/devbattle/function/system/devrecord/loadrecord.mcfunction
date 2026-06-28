$data modify storage devattack:dev-attack-record temp-record set from storage devattack:dev-attack-record map$(tempmap)-dev$(tempdev)-record

#기록 초기화를 했다면 기록이 없는 셈 치기
execute if score @e[tag=trackselect-devbattle-record,limit=1] timermain matches 2147483647 run data remove storage devattack:dev-attack-shadows temp-shadow-play
