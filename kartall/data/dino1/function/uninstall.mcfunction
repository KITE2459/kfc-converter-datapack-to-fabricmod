tag @e remove nbs_dino1
scoreboard objectives remove nbs_dino1
scoreboard objectives remove nbs_dino1_t
datapack disable "file/bgm-dino1.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-dino1.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]