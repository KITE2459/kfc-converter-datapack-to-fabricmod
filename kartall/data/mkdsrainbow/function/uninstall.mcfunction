tag @e remove nbs_mkdsrainbo
scoreboard objectives remove nbs_mkdsrainbo
scoreboard objectives remove nbs_mkdsrainbo_t
datapack disable "file/mkdsrainbow.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"mkdsrainbow.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]