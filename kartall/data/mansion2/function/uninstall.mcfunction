tag @e remove nbs_mansion2
scoreboard objectives remove nbs_mansion2
scoreboard objectives remove nbs_mansion2_t
datapack disable "file/mansion2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"mansion2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]