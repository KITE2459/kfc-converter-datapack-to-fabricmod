tag @e remove nbs_abyss
scoreboard objectives remove nbs_abyss
scoreboard objectives remove nbs_abyss_t
datapack disable "file/bgm-abyss.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-abyss.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]