tag @e remove nbs_pirate2
scoreboard objectives remove nbs_pirate2
scoreboard objectives remove nbs_pirate2_t
datapack disable "file/pirate2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"pirate2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]