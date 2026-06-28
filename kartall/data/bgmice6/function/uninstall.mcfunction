tag @e remove nbs_bgmice6
scoreboard objectives remove nbs_bgmice6
scoreboard objectives remove nbs_bgmice6_t
datapack disable "file/bgm-ice6.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-ice6.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]