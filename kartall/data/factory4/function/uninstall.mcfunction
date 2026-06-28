tag @e remove nbs_factory4
scoreboard objectives remove nbs_factory4
scoreboard objectives remove nbs_factory4_t
datapack disable "file/factory4.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"factory4.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]