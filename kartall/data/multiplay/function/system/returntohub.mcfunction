title @s title {translate:"종료","color":"red"}
title @s subtitle {translate:"대기실로 돌아왔습니다.","color":"yellow"}
kill @e[tag=engine-display,type=text_display]
kill @e[tag=bumpallow-display,type=text_display]
scoreboard players reset @s timecount

function bgm-room:manage-bgm/stopbgm
function multiplayroom:play

tp @s -18 -1 169

tag @s remove kart-multi-player
tag @s remove checkpointpass