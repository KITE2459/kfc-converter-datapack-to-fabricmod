tag @e remove nbs_ice5
scoreboard objectives remove nbs_ice5
scoreboard objectives remove nbs_ice5_t
datapack disable "file/bgm-ice5.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-ice5.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]