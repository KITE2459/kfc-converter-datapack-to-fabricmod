tag @e remove nbs_factory5
scoreboard objectives remove nbs_factory5
scoreboard objectives remove nbs_factory5_t
datapack disable "file/bgm-factory5.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-factory5.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]