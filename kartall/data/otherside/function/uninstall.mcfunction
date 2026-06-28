tag @e remove nbs_otherside
scoreboard objectives remove nbs_otherside
scoreboard objectives remove nbs_otherside_t
datapack disable "file/otherside.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"otherside.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]