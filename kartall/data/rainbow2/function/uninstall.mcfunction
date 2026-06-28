tag @e remove nbs_rainbow2
scoreboard objectives remove nbs_rainbow2
scoreboard objectives remove nbs_rainbow2_t
datapack disable "file/bgm-rainbow2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-rainbow2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]