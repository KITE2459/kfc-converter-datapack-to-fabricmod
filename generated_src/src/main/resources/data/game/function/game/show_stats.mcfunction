execute if score game game.difficulty matches 1 run scoreboard players display name difficulty_value sidebar-text {text:"쉬움",color:green,bold:true}
execute if score game game.difficulty matches 2 run scoreboard players display name difficulty_value sidebar-text {text:"보통",color:yellow,bold:true}
execute if score game game.difficulty matches 3 run scoreboard players display name difficulty_value sidebar-text {text:"어려움",color:red,bold:true}
execute if score game game.difficulty matches 4 run scoreboard players display name difficulty_value sidebar-text {text:"다크",color:dark_purple,bold:true}
execute if score game game.difficulty matches 5 run scoreboard players display name difficulty_value sidebar-text {text:"무한",color:aqua,bold:true}

scoreboard players set enemy_count statistics 0
execute as @e[tag=enemy.core] run scoreboard players add enemy_count statistics 1
scoreboard players display name enemy_value sidebar-text [{text:"남은 몹 수: ",color:white},{score:{name:"enemy_count",objective:"statistics"},color:red}]

scoreboard players set #remain_ticks time 0
execute if score game game.difficulty matches 5 run function game:game/show_stats_infinity
execute unless score game game.difficulty matches 5 run function game:game/show_stats_normal with storage game setup

scoreboard players operation #remain_seconds time = #remain_ticks time
scoreboard players set #div time 20
scoreboard players operation #remain_seconds time /= #div time
scoreboard players operation #minutes time = #remain_seconds time
scoreboard players set #div time 60
scoreboard players operation #minutes time /= #div time
scoreboard players operation #seconds time = #remain_seconds time
scoreboard players set #div time 60
scoreboard players operation #seconds time %= #div time
scoreboard players operation #sec_tens time = #seconds time
scoreboard players set #div time 10
scoreboard players operation #sec_tens time /= #div time
scoreboard players operation #sec_ones time = #seconds time
scoreboard players set #div time 10
scoreboard players operation #sec_ones time %= #div time

scoreboard players display name time_value sidebar-text [{score:{name:"#minutes",objective:"time"},color:aqua,bold:true},{text:":",color:aqua,bold:true},{score:{name:"#sec_tens",objective:"time"},color:aqua,bold:true},{score:{name:"#sec_ones",objective:"time"},color:aqua,bold:true}]
