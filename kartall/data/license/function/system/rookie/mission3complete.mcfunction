title @s title {translate:"완주!","color":"yellow"}
title @s subtitle {translate:"","color":"yellow"}

tellraw @a {translate:"루키 라이센스를 획득했습니다!","color":"yellow"}
tellraw @a [{translate:"[L3 라이센스]","color":"blue"},{translate:"에 도전할 수 있습니다.","color":"aqua"}]
tellraw @a [{translate:"[레어]","color":"light_purple"},{translate:"등급 카트바디가 해금되었습니다!","color":"aqua"}]

bossbar set minecraft:license players

scoreboard players reset @s licensecount
#BGM 끄기
function bgm-room:manage-bgm/stopbgm
time set day
weather clear
tp @s -119 4 236 0 0

execute at @s run playsound minecraft:ui.toast.challenge_complete weather @a ~ ~ ~ 1

tag @s remove checkpointpass

#타이머 스탑
function timerpack:stop

#다음 미션(L3) 열어주기
setblock -142 5 263 gold_block

setblock -115 5 242 gold_block
setblock -117 5 241 oak_button

setblock -96 5 219 minecraft:oak_button[face=wall,facing=north,powered=false]

execute unless score clear-data licensestage matches 3.. run scoreboard players set clear-data licensestage 3

# 업적 주기
advancement grant @s only mcrider:licence/rookie