tag @e remove nbs_sans
scoreboard objectives remove nbs_sans
scoreboard objectives remove nbs_sans_t
datapack disable "file/sans.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"sans.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]