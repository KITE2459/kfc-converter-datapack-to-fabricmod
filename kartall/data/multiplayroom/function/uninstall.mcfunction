tag @e remove nbs_multiplayr
scoreboard objectives remove nbs_multiplayr
scoreboard objectives remove nbs_multiplayr_t
datapack disable "file/bgm-multiplayroom.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-multiplayroom.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]