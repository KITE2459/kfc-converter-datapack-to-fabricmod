title @s title {translate:"완주 실패","color":"gray"}
title @s subtitle {translate:"완주하지 못했습니다.","color":"yellow"}
#BGM 끄기
function bgm-room:manage-bgm/stopbgm
function retiresound:play

#쉐도우 죽이기
kill @e[tag=shadow-models]

scoreboard players reset @s timecount

time set day
weather clear
tp @s -139 4 236 180 0


tag @s remove checkpointpass

data modify entity @e[tag=timeattack-state-text,limit=1] text set value [{translate:"관전 불가","color":"red"}]

#타이머 스탑
function timerpack:stop

# 업적 관리용
scoreboard players add @s achievement_total_retire 1

function checkpoint:system/init