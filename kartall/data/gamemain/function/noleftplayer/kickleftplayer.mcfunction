title @s title {translate:"추방","color":"red"}
title @s subtitle {translate:"비정상적인 플레이가 감지되었습니다.","color":"gray"}
#BGM 끄기
function bgm-room:manage-bgm/stopbgm

function retiresound:play



#내리고 귀환
kill @e[tag=shadow-models]
tp @s -138.00 4.00 199.00 0.00 -1.05

tag @s remove kart-left-player
scoreboard players reset @s kartboostpadtime

function checkpoint:system/init
function trackselect:drone/destroy