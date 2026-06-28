tag @e remove nbs_pretion1
scoreboard objectives remove nbs_pretion1
scoreboard objectives remove nbs_pretion1_t
datapack disable "file/pretion1.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"pretion1.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]