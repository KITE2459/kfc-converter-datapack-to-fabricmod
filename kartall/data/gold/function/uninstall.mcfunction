tag @e remove nbs_gold
scoreboard objectives remove nbs_gold
scoreboard objectives remove nbs_gold_t
datapack disable "file/gold.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"gold.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]