execute unless score #map multi-main matches 1001.. run summon text_display ~ ~ ~ {brightness:{sky:15,block:15},billboard:"vertical",Tags:["check-rank-text","multi-text-display"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.725f,0f],scale:[0.75f,0.75f,0.75f]},translate:"1등"}
execute if score mad-crash multi-main matches 0 if entity @s[tag=bump-allow,tag=kite-played] run summon text_display ~ ~ ~ {brightness:{sky:15,block:15},billboard:"vertical",Tags:["multi-bump","multi-text-display"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[-0.2906f,1.725f,0f],scale:[0.75f,0.75f,0.75f]},text:{"font":"include/default",translate:"🗡",color:red}}
execute if score mad-crash multi-main matches 0 if entity @s[tag=!bump-allow,tag=kite-played] run summon text_display ~ ~ ~ {brightness:{sky:15,block:15},billboard:"vertical",Tags:["multi-bump","multi-text-display"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[-0.28125f,1.725f,0f],scale:[0.75f,0.75f,0.75f]},text:{"font":"include/default",translate:"⛨",color:yellow}}
execute if score mad-crash multi-main matches 1 if entity @s[tag=kite-played] run summon text_display ~ ~ ~ {brightness:{sky:15,block:15},billboard:"vertical",Tags:["multi-bump","multi-text-display"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[-0.28125f,1.725f,0f],scale:[0.75f,0.75f,0.75f]},text:{"font":"include/default",translate:"⚠",color:red}}

summon text_display ~ ~ ~ {brightness:{sky:15,block:15},billboard:"vertical",Tags:["multi-engine","multi-text-display"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0.375f,1.725f,0f],scale:[0.75f,0.75f,0.75f]},text:{translate:""}}
function multiplay:system/engine-and-bump-text/multi-engine-text

summon text_display ~ ~ ~ {brightness:{sky:15,block:15},billboard:"vertical",Tags:["multi-playername","multi-text-display"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[0f,1.475f,0f],scale:[0.9f,0.9f,0.9f]},translate:"Player"}

execute as @e[tag=multi-text-display,sort=nearest,limit=4,distance=..0.00001] at @s run ride @s mount @n[tag=kartsaddle]

#닉네임 / 와이번 모드
execute on vehicle on passengers run data modify entity @s[tag=multi-playername] text set value {"selector":"@p[tag=kart-multi-player]"}
execute if entity @s[tag=wyvernp] on vehicle on passengers run data modify entity @s[tag=multi-playername] text set value "WyvernP"
