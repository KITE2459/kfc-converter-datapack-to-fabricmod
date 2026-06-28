title @s title {translate:"완주!","color":"yellow"}
title @s subtitle {translate:"","color":"yellow"}

tellraw @a {translate:"카트라이더의 최강자를 증명하는, PRO 라이센스를 획득했습니다!","color":"yellow"}
tellraw @a [{translate:"마지막 도전, ","color":"aqua"},{translate:"[Master]","color":"dark_red"},{translate:"가 해금되었습니다.","color":"aqua"}]
tellraw @a [{translate:"[차고]","color":"green"},{translate:"의 어딘가 비밀 공간이 열렸습니다.","color":"aqua"}]
tellraw @a [{translate:"[숨겨진 엔진 룸]","color":"green"},{translate:"이 열렸습니다.","color":"aqua"}]

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

#클리어
setblock -63 5 263 gold_block
setblock -123 5 242 gold_block

#더미룸
setblock 4965 5 5130 oak_button[face=wall,facing=east]
#파츠룸
setblock 4979 5 5128 stone_button[face=wall,facing=west]


fill -102 4 208 -102 5 208 air

execute unless score clear-data licensestage matches 17.. run scoreboard players set clear-data licensestage 17

# 업적 주기
advancement grant @s only mcrider:licence/pro

# 마스터 해금
scoreboard players set clear-data masterstage 0
kill @e[tag=master.interaction]
kill @e[tag=master.text]
scoreboard players set #game_state master 0
fill -125 8 239 -125 4 235 air destroy