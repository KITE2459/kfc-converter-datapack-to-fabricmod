$data modify storage devattack:dev-attack-shadows temp-shadow-play set from storage devattack:dev-attack-shadows map$(tempmap)-dev$(tempdev)-shadow

#기록 초기화를 했다면 쉐도우 안 표시하기
execute if score @e[tag=trackselect-devbattle-record,limit=1] timermain matches 2147483647 run data remove storage devattack:dev-attack-shadows temp-shadow-play