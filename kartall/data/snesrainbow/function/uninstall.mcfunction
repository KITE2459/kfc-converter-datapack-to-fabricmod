tag @e remove nbs_snesrainbo
scoreboard objectives remove nbs_snesrainbo
scoreboard objectives remove nbs_snesrainbo_t
datapack disable "file/snesrainbow.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"snesrainbow.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]