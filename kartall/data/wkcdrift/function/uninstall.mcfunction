tag @e remove nbs_wkcdrift
scoreboard objectives remove nbs_wkcdrift
scoreboard objectives remove nbs_wkcdrift_t
datapack disable "file/wkcdrift.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"wkcdrift.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]