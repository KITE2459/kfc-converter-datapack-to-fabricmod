$data modify storage timeattack:timeattack-shadows temp-shadow-play set from storage timeattack:timeattack-shadows map$(tempmap)-shadow

#기록 초기화를 했다면 쉐도우 안 표시하기
execute if score @e[tag=trackselect-timeattack-record,limit=1] timermain matches 2147483647 run data remove storage timeattack:timeattack-shadows temp-shadow-play
