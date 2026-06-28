title @s title {translate:"완주 실패","color":"gray"}
title @s subtitle {translate:"완주하지 못했습니다.","color":"yellow"}
# BGM 끄기
function bgm-room:manage-bgm/stopbgm

function retiresound:play

bossbar set minecraft:master players

scoreboard players reset @s mastercount


time set day
weather clear

# 로비로 텔레포트 (위치는 기존 license와 동일하게 설정)
tp @s -138.5 4 296.5 90 0

tag @s remove checkpointpass
tag @s remove check-pass-last

# 타이머 스탑
function timerpack:stop

function checkpoint:system/init
