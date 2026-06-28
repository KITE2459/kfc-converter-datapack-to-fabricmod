title @s title {translate:"귀환","color":"red"}
title @s subtitle {translate:"허브로 돌아왔습니다.","color":"yellow"}
#BGM 끄기
function bgm-room:manage-bgm/stopbgm
#쉐도우 죽이기
kill @e[tag=shadow-models]

scoreboard players reset @s timecount

time set day
weather clear
tp @s -139 4 236 180 0


tag @s remove checkpointpass

data modify entity @e[tag=timeattack-state-text,limit=1] text set value [{translate:"","color":"aqua"}]

#타이머 스탑
function timerpack:stop