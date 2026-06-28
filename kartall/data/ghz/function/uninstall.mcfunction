tag @e remove nbs_ghz
scoreboard objectives remove nbs_ghz
scoreboard objectives remove nbs_ghz_t
datapack disable "file/ghz.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"ghz.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]