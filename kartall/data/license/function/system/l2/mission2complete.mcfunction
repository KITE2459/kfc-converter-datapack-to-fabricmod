title @s title {translate:"완주!","color":"yellow"}
title @s subtitle {translate:"","color":"yellow"}

bossbar set minecraft:license players

scoreboard players reset @s licensecount
#BGM 끄기
function bgm-room:manage-bgm/stopbgm

function licenseclear:play


time set day
weather clear
tp @s -119 4 236 0 0


tag @s remove checkpointpass

#타이머 스탑
function timerpack:stop

#다음 미션 열어주기
setblock -102 5 262 oak_button
setblock -99 5 263 gold_block

execute unless score clear-data licensestage matches 8.. run scoreboard players set clear-data licensestage 8