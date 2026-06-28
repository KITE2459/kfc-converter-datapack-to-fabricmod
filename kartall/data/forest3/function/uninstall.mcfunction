tag @e remove nbs_forest3
scoreboard objectives remove nbs_forest3
scoreboard objectives remove nbs_forest3_t
datapack disable "file/forest3.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"forest3.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]