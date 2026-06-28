
scoreboard players reset @s updown-count
#BGM 끄기
function bgm-room:manage-bgm/stopbgm
function completesound:play
title @s title {translate:"완주","color":"yellow"}


time set day
weather clear
tp @s -73 4 205 0 0
#마무리
tag @s remove kartplayertemp
function timerpack:stop

