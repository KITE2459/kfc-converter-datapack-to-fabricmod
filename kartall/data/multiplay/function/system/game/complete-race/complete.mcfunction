#동타완주 처리용
scoreboard players reset @s kart-milisec-calc
tag @s remove kart-multi-same-time
tag @s remove kart-multi-complete

#리타 카운트 시작
scoreboard players set #time multi-main -1

#자신의 순위를 정하기(이 함수를 실행하는 순서대로 1등부터 순위가 부여됨)
execute store result score @s multi-rank run scoreboard players add #max-rank multi-rank 1

#완주인원 증가
scoreboard players add #complete-player-count multi-rank 1

function multiplay:system/game/complete-race/calculate-milisec

#BGM 끄기
function bgm-room:manage-bgm/stopbgm

#1등
execute if score #max-rank multi-rank matches 1 run title @s title {translate:"우승","color":"yellow"}
execute if score #max-rank multi-rank matches 1 run tag @s add bgm-delay
execute if score #max-rank multi-rank matches 1 run function completesound:play
execute unless score #max-rank multi-rank matches 1 run title @s title {translate:"완주","color":"yellow"}
execute unless score #max-rank multi-rank matches 1 run tag @s add bgm-delay
execute unless score #max-rank multi-rank matches 1 run function complete2:play

title @s times 10 80 10
title @s subtitle [{"interpret":true,"nbt":"time","storage":"timermain"},{"nbt":"multi-record-subtick[-1]","color":"yellow","storage":"timermain","interpret":false}]
data modify storage minecraft:timermain multi-record append from storage minecraft:timermain time



#차 부수고 얻기
ride @s dismount

tp @s -18 -1 169

#순위에 따른 tp
execute if score @s multi-rank matches 1 run tp @s -6 2 163
execute if score @s multi-rank matches 2 run tp @s -6 1 160
execute if score @s multi-rank matches 3 run tp @s -6 0 166

tag @s remove kart-multi-player
tag @s remove check-pass-last

clear @s minecraft:soul_campfire

# 업적 관리용
scoreboard players add @s achievement_multiplay_played 1
scoreboard players add @s achievement_total_played 1
execute if score @s multi-rank matches 1 run scoreboard players add @s achievement_multiplay_first 1
scoreboard players set @s achievement_multiplay_retire_streak 0

return 1