tag @e remove nbs_nemo3
scoreboard objectives remove nbs_nemo3
scoreboard objectives remove nbs_nemo3_t
datapack disable "file/nemo3.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"nemo3.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]