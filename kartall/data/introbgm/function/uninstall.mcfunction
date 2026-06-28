tag @e remove nbs_introbgm
scoreboard objectives remove nbs_introbgm
scoreboard objectives remove nbs_introbgm_t
datapack disable "file/introbgm.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"introbgm.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]