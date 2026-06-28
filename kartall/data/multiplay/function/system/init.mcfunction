title @a[tag=kart-multi-player] times 0 20 20
#시간 리셋
#scoreboard players reset #time multi-main
scoreboard players reset #retire-time multi-main
#순위 리셋
scoreboard players reset #max-rank multi-rank
#랩 리셋
scoreboard players set @a trackselect-lap 0

#이미 레디에서 한 번 했지만, 혹시 모르니 보험으로 한번더 
execute as @a[tag=kart-multi-player] run function checkpoint:system/init

# 임시 기록모드
scoreboard players set #player-count multi-main 0
execute as @a[tag=multi-hub-ready] run scoreboard players add #player-count multi-main 1

scoreboard players set #record_mode multi-main 0

execute unless score team-battle multi-main matches 1.. unless score no-collision multi-main matches 1.. unless score inf-boost multi-main matches 1.. unless score no-draft multi-main matches 1.. unless score drag-accel multi-main matches 1.. unless score mad-crash multi-main matches 1.. unless score wall-crash multi-main matches 1.. unless score #player-count multi-main matches 2.. run function multiplay:system/enable_record_mode