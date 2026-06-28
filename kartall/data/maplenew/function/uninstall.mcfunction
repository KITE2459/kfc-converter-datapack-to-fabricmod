tag @e remove nbs_maplenew
scoreboard objectives remove nbs_maplenew
scoreboard objectives remove nbs_maplenew_t
datapack disable "file/maplenew.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"maplenew.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]