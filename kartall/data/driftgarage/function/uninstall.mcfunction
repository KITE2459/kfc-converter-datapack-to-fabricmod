tag @e remove nbs_driftgarag
scoreboard objectives remove nbs_driftgarag
scoreboard objectives remove nbs_driftgarag_t
datapack disable "file/driftgarage.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"driftgarage.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]