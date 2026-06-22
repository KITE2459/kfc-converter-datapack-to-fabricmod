# 게임 초기화
execute unless score timer gameState matches 1.. run scoreboard players set game wave 0
execute unless score timer gameState matches 1.. store result score @a money run data get storage game setup.money 1.0
execute unless score timer gameState matches 1.. run tag @e[tag=map.point] remove game
execute unless score timer gameState matches 1.. run scoreboard players set game game.base_health 200
$execute unless score timer gameState matches 1.. run tag @e[tag=map.point,tag=map.$(map)] add game
execute unless score timer gameState matches 1.. run kill @e[tag=enemy]
execute unless score timer gameState matches 1.. run kill @e[tag=tower]
execute unless score timer gameState matches 1.. run kill @e[tag=bullet]

# 게임 준비 메시지
execute unless score timer gameState matches 1.. run title @a title {text:"게임 준비",color:green,bold:true}
execute unless score timer gameState matches 1.. run title @a subtitle {text:"곧 라운드가 시작됩니다.",color:white}
execute unless score timer gameState matches 1.. as @a at @s run playsound ui.button.click weather @s ~ ~ ~

# 통계 초기화
scoreboard objectives remove statistics
scoreboard objectives add statistics dummy
scoreboard objectives remove sidebar-text
scoreboard objectives add sidebar-text dummy
scoreboard objectives setdisplay sidebar sidebar-text
scoreboard objectives modify sidebar-text numberformat blank
scoreboard objectives modify sidebar-text displayname {text:"소론",bold:true,color:dark_purple,shadow_color:-99999999}

scoreboard players set map_header sidebar-text 80
scoreboard players set map_name sidebar-text 79
scoreboard players set difficulty_value sidebar-text 78
scoreboard players set blank_1 sidebar-text 77
scoreboard players set round_header sidebar-text 76
scoreboard players set round_value sidebar-text 75
scoreboard players set enemy_value sidebar-text 74
scoreboard players set time_value sidebar-text 73
scoreboard players set enemy_count statistics 0

scoreboard players display name map_header sidebar-text {text:"맵 정보",color:gold,bold:true}
$scoreboard players display name map_name sidebar-text {text:"$(map_name)",color:green,bold:true}
scoreboard players display name difficulty_value sidebar-text {text:"-",color:white}
scoreboard players display name blank_1 sidebar-text {text:" "}
scoreboard players display name round_header sidebar-text {text:"라운드 정보",color:gold,bold:true}
scoreboard players display name round_value sidebar-text {text:"라운드 0/0",color:white}
scoreboard players display name enemy_value sidebar-text [{text:"남은 몹 수: ",color:white},{score:{name:"enemy_count",objective:"statistics"},color:red}]
scoreboard players display name time_value sidebar-text {text:"0:00",color:aqua,bold:true}

# 라운드 준비로 전환
execute if score timer gameState matches 50 run scoreboard players set stage gameState 1
execute if score timer gameState matches 50 run scoreboard players set game wave 1
execute if score timer gameState matches 50 run scoreboard players set timer gameState 0