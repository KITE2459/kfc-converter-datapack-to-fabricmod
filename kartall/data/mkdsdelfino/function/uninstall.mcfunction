tag @e remove nbs_mkdsdelfin
scoreboard objectives remove nbs_mkdsdelfin
scoreboard objectives remove nbs_mkdsdelfin_t
datapack disable "file/mkdsdelfino.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"mkdsdelfino.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]