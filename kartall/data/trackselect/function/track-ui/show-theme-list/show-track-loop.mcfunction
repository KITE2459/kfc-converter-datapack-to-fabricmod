summon minecraft:text_display ~ ~0.1875 ~ {Tags:["track-track-text","track-track-text-temp"]}
summon marker ~ ~ ~ {Tags:["track-track-marker","track-track-marker-temp"]}

summon interaction ^1 ^ ^ {Tags:["track-track-interaction"],response:1b,width:2f,height:0.75f}

    #타임어택/개발자대결 기록 + 클리어텍스트
    execute if entity @p[scores={trackselect-game-id=1..2}] run summon minecraft:text_display ~ ~ ~ {Tags:["track-track-record","track-track-record-temp"]}
    execute if entity @p[scores={trackselect-game-id=2}] run summon minecraft:text_display ~ ~-0.1875 ~ {Tags:["track-track-clear-text","track-track-clear-text-temp"]}

#난이도
summon minecraft:text_display ~ ~0.6875 ~ {Tags:["track-track-difficulty-text","track-track-difficulty-text-temp"]}

#트랙 하나 pop
data modify entity @n[tag=track-track-text-temp,type=text_display] text set from storage track-list temp[0].text
data modify entity @n[tag=track-track-text-temp,type=text_display] transformation.scale set value [0.95f, 0.95f, 0.95f]

data modify entity @n[tag=track-track-marker-temp,type=marker] data.track set from storage track-list temp[0]
execute store result score @n[tag=track-track-marker-temp,type=marker] trackselect-map-id run data get entity @n[tag=track-track-marker-temp,type=marker] data.track.number

    #기록 텍스트
    execute if entity @p[scores={trackselect-game-id=1}] as @n[tag=track-track-record-temp,type=text_display] run function timeattack:system/record/changerecordtext with entity @n[tag=track-track-marker-temp,type=marker] data.track
    execute if entity @p[scores={trackselect-game-id=2}] as @n[tag=track-track-record-temp,type=text_display] run function devbattle:system/devrecord/changerecordtext with entity @n[tag=track-track-marker-temp,type=marker] data.track
    data modify entity @n[tag=track-track-record-temp,type=text_display] transformation.scale set value [0.75f, 0.75f, 0.75f]

    #클리어 텍스트
    execute if entity @p[scores={trackselect-game-id=2}] as @n[tag=track-track-clear-text-temp,type=text_display] run function devbattle:system/devrecord/load-clear-data-text with entity @n[tag=track-track-marker-temp,type=marker] data.track
    data modify entity @n[tag=track-track-clear-text-temp,type=text_display] transformation.scale set value [0.75f, 0.75f, 0.75f]

#난이도 텍스트
data modify entity @n[tag=track-track-difficulty-text-temp,type=text_display] text set from storage track-list temp[0].difficulty
data modify entity @n[tag=track-track-difficulty-text-temp,type=text_display] transformation.scale set value [0.35f, 0.35f, 0.35f]

data remove storage track-list temp[0]

tag @e[tag=track-track-text-temp,type=text_display] remove track-track-text-temp
tag @e[tag=track-track-marker-temp,type=marker] remove track-track-marker-temp
tag @e[tag=track-track-record-temp,type=text_display] remove track-track-record-temp
tag @e[tag=track-track-clear-text-temp,type=text_display] remove track-track-clear-text-temp
tag @e[tag=track-track-difficulty-text-temp,type=text_display] remove track-track-difficulty-text-temp

execute if data storage minecraft:track-list temp[0] positioned ^ ^ ^2 if block ~ ~ ~ #kartmobil:ignoreblock run function trackselect:track-ui/show-theme-list/show-track-loop
execute if data storage minecraft:track-list temp[0] positioned ^ ^ ^2 unless block ~ ~ ~ #kartmobil:ignoreblock as @e[tag=track-y-pos,type=marker] at @s run tp @s ~ ~-1 ~
execute if data storage minecraft:track-list temp[0] positioned ^ ^ ^2 unless block ~ ~ ~ #kartmobil:ignoreblock positioned as @e[tag=track-y-pos,type=marker] run function trackselect:track-ui/show-theme-list/show-track-loop