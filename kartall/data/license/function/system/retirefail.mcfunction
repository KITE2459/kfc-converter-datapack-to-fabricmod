title @s title {translate:"완주 실패","color":"gray"}
title @s subtitle {translate:"완주하지 못했습니다.","color":"yellow"}
#BGM 끄기
function bgm-room:manage-bgm/stopbgm

function retiresound:play

bossbar set minecraft:license players

scoreboard players reset @s licensecount

time set day
weather clear
tp @s -119 4 236 0 0


tag @s remove checkpointpass

#타이머 스탑
function timerpack:stop
