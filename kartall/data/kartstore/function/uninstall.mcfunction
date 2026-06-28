tag @e remove nbs_kartstore
scoreboard objectives remove nbs_kartstore
scoreboard objectives remove nbs_kartstore_t
datapack disable "file/bgm-kartstore.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-kartstore.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]