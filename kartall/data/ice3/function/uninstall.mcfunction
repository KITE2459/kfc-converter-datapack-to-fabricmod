tag @e remove nbs_ice3
scoreboard objectives remove nbs_ice3
scoreboard objectives remove nbs_ice3_t
datapack disable "file/ice3.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"ice3.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]