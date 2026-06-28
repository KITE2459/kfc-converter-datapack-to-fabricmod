tag @e remove nbs_end
scoreboard objectives remove nbs_end
scoreboard objectives remove nbs_end_t
datapack disable "file/end.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"end.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]