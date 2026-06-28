tag @e remove nbs_nemo4
scoreboard objectives remove nbs_nemo4
scoreboard objectives remove nbs_nemo4_t
datapack disable "file/nemo4.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"nemo4.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]