tag @e remove nbs_tomb3
scoreboard objectives remove nbs_tomb3
scoreboard objectives remove nbs_tomb3_t
datapack disable "file/bgm-tomb3.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-tomb3.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]