tag @e remove nbs_pirate4
scoreboard objectives remove nbs_pirate4
scoreboard objectives remove nbs_pirate4_t
datapack disable "file/bgm-pirate4.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-pirate4.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]