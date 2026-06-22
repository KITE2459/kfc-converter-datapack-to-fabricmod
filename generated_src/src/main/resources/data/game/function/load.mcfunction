# 스코어보드
scoreboard objectives add money dummy
scoreboard objectives add coins dummy
scoreboard objectives add wave dummy
scoreboard objectives add killCount dummy
scoreboard objectives add game.base_health dummy
scoreboard objectives add time dummy

scoreboard objectives add gameState dummy
scoreboard objectives add game.return dummy

scoreboard objectives add player.id dummy
scoreboard objectives add player.last_upgrade_id dummy

scoreboard objectives add game.difficulty dummy
scoreboard objectives add game.enemy_hp_multiply dummy
scoreboard objectives add game.enemy_speed_multiply dummy
scoreboard objectives add game.enemy_def_multiply dummy

# 통계용
scoreboard objectives add statistics dummy
scoreboard objectives modify statistics displayname {text:"통계"}

# 정보용
scoreboard objectives add info dummy
scoreboard objectives modify info displayname {text:"소론",color:"dark_purple",shadow_color:-99999999}

execute unless score coin info matches 0.. run scoreboard players set coin info 0
scoreboard players display name coin info {text:"코인",color:yellow,bold:true}
execute unless score killcount info matches 0.. run scoreboard players set killcount info 0
scoreboard players display name killcount info {text:"킬카운트",color:red,bold:true}


execute as @a unless score @s player.id matches 0.. run scoreboard players add global player.id 1
execute as @a unless score @s player.id matches 0.. run scoreboard players operation @s player.id = global player.id