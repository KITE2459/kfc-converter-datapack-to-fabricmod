title @s title {translate:"관전 종료","color":"aqua"}
title @s subtitle {translate:"돌아왔습니다.","color":"yellow"}

title @s actionbar ["관전 종료됨"]

tp @s -80 7 236

tag @s remove dev-attack-spectator
gamemode adventure @s

function bgm-room:manage-bgm/stopbgm

scoreboard players reset @s multi-shift