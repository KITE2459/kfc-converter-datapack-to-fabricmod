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


#tag @s remove checkpointpass

#타이머 스탑
function timerpack:stop

#다음 미션 열어주기
setblock -139 5 262 oak_button
setblock -136 5 263 gold_block

execute unless score clear-data licensestage matches 1.. run scoreboard players set clear-data licensestage 1
execute store result score clear-data-from-file licensestage run data get storage license-cleardata:license-data cleared-stage
execute unless score clear-data-from-file licensestage matches 1.. run data modify storage license-cleardata:license-data cleared-stage set value 1
