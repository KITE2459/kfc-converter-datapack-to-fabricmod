tag @e remove nbs_forest2
scoreboard objectives remove nbs_forest2
scoreboard objectives remove nbs_forest2_t
datapack disable "file/forest2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"forest2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]