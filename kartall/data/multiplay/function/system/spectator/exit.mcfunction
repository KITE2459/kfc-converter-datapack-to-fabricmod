title @s title {translate:"관전 종료","color":"aqua"}
title @s subtitle {translate:"허브로 돌아왔습니다.","color":"yellow"}

title @s actionbar ["관전 종료됨"]

tp @s -33 3 163 

tag @s remove multi-spectator
gamemode adventure @s

function bgm-room:manage-bgm/stopbgm
function multiplayroom:play

scoreboard players reset @s multi-shift

scoreboard players set @s[tag=kite-played] multi-spectate-quit 1