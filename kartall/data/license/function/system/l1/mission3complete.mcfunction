title @s title {translate:"완주!","color":"yellow"}
title @s subtitle {translate:"","color":"yellow"}

tellraw @a {translate:"L1 라이센스를 획득했습니다!","color":"yellow"}
tellraw @a {translate:"싱글 플레이 로비에서 엔딩을 볼 수 있습니다.","color":"aqua"}
tellraw @a [{translate:"엔딩을 시청하면 ","color":"aqua"},{translate:"[카트바디 성능 커스텀]","color":"green"},{translate:"과 ","color":"aqua"},{translate:"[스페셜]","color":"green"},{translate:"등급이 해금됩니다.","color":"aqua"}]
tellraw @a [{translate:"[PRO 라이센스]","color":"yellow"},{translate:"에 도전할 수 있습니다.","color":"aqua"}]

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

#다음 미션(Pro) 열어주기 + 엔딩 버튼 설치
setblock -114 5 217 minecraft:oak_button[face=wall,facing=west,powered=false]

setblock -82 5 263 gold_block

setblock -121 5 242 gold_block
setblock -123 5 241 oak_button
setblock -114 5 213 minecraft:oak_button[face=wall,facing=west,powered=false]

execute unless score clear-data licensestage matches 12.. run scoreboard players set clear-data licensestage 12

# 업적 주기
advancement grant @s only mcrider:licence/l1