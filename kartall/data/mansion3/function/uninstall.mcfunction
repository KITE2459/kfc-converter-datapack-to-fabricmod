tag @e remove nbs_mansion3
scoreboard objectives remove nbs_mansion3
scoreboard objectives remove nbs_mansion3_t
datapack disable "file/mansion3.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"mansion3.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]