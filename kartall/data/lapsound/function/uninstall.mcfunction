tag @e remove nbs_lapsound
scoreboard objectives remove nbs_lapsound
scoreboard objectives remove nbs_lapsound_t
datapack disable "file/lapsound.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"lapsound.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]