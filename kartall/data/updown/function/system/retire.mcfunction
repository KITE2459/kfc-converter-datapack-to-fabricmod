title @s title {translate:"완주 실패","color":"gray"}
title @s subtitle {translate:"완주하지 못했습니다.","color":"yellow"}
#BGM 끄기
function bgm-room:manage-bgm/stopbgm
function retiresound:play

scoreboard players reset @s updown-count

time set day
weather clear
tp @s -73 4 205 0 0


tag @s remove checkpointpass

#data modify entity @e[tag=timeattack-state-text,limit=1] text set value [{translate:"관전 불가","color":"red"}]

#타이머 스탑
function timerpack:stop

function checkpoint:system/init