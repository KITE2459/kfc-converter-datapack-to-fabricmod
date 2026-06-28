tag @e remove nbs_maple2
scoreboard objectives remove nbs_maple2
scoreboard objectives remove nbs_maple2_t
datapack disable "file/maple2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"maple2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]