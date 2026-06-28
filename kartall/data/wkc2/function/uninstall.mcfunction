tag @e remove nbs_wkc2
scoreboard objectives remove nbs_wkc2
scoreboard objectives remove nbs_wkc2_t
datapack disable "file/bgm-wkc2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-wkc2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]