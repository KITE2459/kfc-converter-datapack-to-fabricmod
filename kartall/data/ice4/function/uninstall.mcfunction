tag @e remove nbs_ice4
scoreboard objectives remove nbs_ice4
scoreboard objectives remove nbs_ice4_t
datapack disable "file/ice4.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"ice4.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]