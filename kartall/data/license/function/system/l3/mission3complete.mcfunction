title @s title {translate:"완주!","color":"yellow"}
title @s subtitle {translate:"","color":"yellow"}

tellraw @a {translate:"L3 라이센스를 획득했습니다!","color":"yellow"}
tellraw @a [{translate:"[L2 라이센스]","color":"red"},{translate:"에 도전할 수 있습니다.","color":"aqua"}]
tellraw @a [{translate:"[레전드]","color":"yellow"},{translate:"등급 카트바디가 해금되었습니다!","color":"aqua"}]
tellraw @a [{translate:"[엔진 룸]","color":"gold"},{translate:"이 해금되었습니다!","color":"aqua"}]

#엔진룸해금
fill -91 7 215 -91 4 213 minecraft:air

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

#다음 미션(L2) 열어주기
setblock -122 5 263 gold_block

setblock -117 5 242 gold_block
setblock -119 5 241 oak_button

setblock -98 5 219 minecraft:oak_button[face=wall,facing=north,powered=false]

execute unless score clear-data licensestage matches 6.. run scoreboard players set clear-data licensestage 6

# 업적 주기
advancement grant @s only mcrider:licence/l3