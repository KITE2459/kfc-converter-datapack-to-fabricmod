$data modify storage timeattack:timeattack-record temp-record set from storage timeattack:timeattack-record map$(tempmap)-record

#기록 초기화를 했다면 기록이 없는 셈 치기


tag @s add kartplayertemp
execute as @e[tag=timeattack] if score @s trackselect-map-id = @a[tag=kartplayertemp,limit=1] trackselect-map-id run tag @s add timeattacktext
execute if score @e[tag=timeattacktext,limit=1] timermain matches 2147483647 run data remove storage timeattack:timeattack-shadows temp-shadow-play
tag @e[tag=timeattacktext] remove timeattacktext
tag @s remove kartplayertemp


