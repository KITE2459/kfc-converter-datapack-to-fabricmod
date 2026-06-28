tag @e remove nbs_factory3
scoreboard objectives remove nbs_factory3
scoreboard objectives remove nbs_factory3_t
datapack disable "file/factory3.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"factory3.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]