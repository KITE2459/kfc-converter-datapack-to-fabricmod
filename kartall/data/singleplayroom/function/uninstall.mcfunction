tag @e remove nbs_singleplay
scoreboard objectives remove nbs_singleplay
scoreboard objectives remove nbs_singleplay_t
datapack disable "file/singleplayroom.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"singleplayroom.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]