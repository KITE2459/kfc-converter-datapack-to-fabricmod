scoreboard players reset @s dev-count

#쉐도우 죽이기
kill @e[tag=shadow-models]

#기록 저장하기
tag @s add kartplayertemp

setblock -86 4 236 minecraft:gold_block


##dev-record-mode dev-count가 1이면 기록을 교체, 아니면 그냥 승리로 끝남
execute store result storage devattack:dev-attack-record tempmap int 1 run scoreboard players get @s trackselect-map-id
execute store result storage devattack:dev-attack-record tempdev int 1 run scoreboard players get #dev dev-count

# 정확한 기록 확보(서브틱 연산)
function multiplay:system/game/complete-race/calculate-milisec

title @a subtitle {"interpret":true,"nbt":"time","storage":"timermain"}
tellraw @s [{"text":"TIME | ","color":"yellow"},{"interpret":true,"nbt":"time","storage":"timermain"}]

# 기존 기록과의 시간 차 계산(기존 기록 - 내 기록)
scoreboard players operation #achievement_margin achievement_calc = @e[tag=trackselect-devbattle-record,limit=1] timermain
scoreboard players operation #achievement_margin achievement_calc -= #time timermain

#신기록을 반영
execute if score @e[tag=trackselect-devbattle-record,limit=1] timermain > #time timermain run function devbattle:system/devrecord/savecleardata with storage devattack:dev-attack-record

execute if score #dev-record-mode dev-count matches 1 unless score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run setblock -86 4 236 gold_block
execute if score #dev-record-mode dev-count matches 1 unless score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run data modify entity @e[tag=trackselect-devbattle-record,limit=1] text set from storage timermain time
execute if score #dev-record-mode dev-count matches 1 unless score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run function devbattle:system/devrecord/saverecord with storage devattack:dev-attack-record
execute if score #dev-record-mode dev-count matches 1 unless score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run scoreboard players operation @e[tag=trackselect-devbattle-record,limit=1] timermain = #time timermain
execute if score #dev-record-mode dev-count matches 1 unless score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run function devbattle:system/shadow/saveshadow with storage devattack:dev-attack-shadows

#BGM 끄기
function bgm-room:manage-bgm/stopbgm
function completesound:play

#메시지 띄우고 기록을 반영

# 기록이 저장된 기록보다 느릴 경우 실패 처리
execute if score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run title @s title {translate:"완주 실패","color":"red"}
execute if score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run title @s subtitle {translate:"완주하지 못했습니다.","color":"yellow"}
execute if score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run function completesound:stop
execute if score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run function retiresound:play

# 성공 시 메시지 및 기록 표시 (실패가 아닐 때만)
execute unless score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run title @s title {translate:"우승","color":"yellow"}
execute unless score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run title @s subtitle {"interpret":true,"nbt":"time","storage":"timermain"}
execute if score #achievement_margin achievement_calc matches 500.. run scoreboard players add @s achievement_devbattle_win_5s 1
execute if score #achievement_margin achievement_calc matches 6000.. run scoreboard players add @s achievement_devbattle_win_60s 1
execute if score #achievement_margin achievement_calc matches 0..10 run scoreboard players add @s achievement_devbattle_close_01 1



time set day
weather clear
tp @s -80 4 236 180 0

tag @s remove check-pass-last

data modify entity @e[tag=dev-state-text,limit=1] text set value [{translate:"관전 불가","color":"red"}]

#마무리
tag @s remove kartplayertemp
function timerpack:stop
function checkpoint:system/init

# 업적 관리용
scoreboard players add @s achievement_devbattle_played 1
scoreboard players add @s achievement_total_played 1
execute unless score #time timermain > @e[tag=trackselect-devbattle-record,limit=1] timermain run scoreboard players add @s achievement_devbattle_win 1
