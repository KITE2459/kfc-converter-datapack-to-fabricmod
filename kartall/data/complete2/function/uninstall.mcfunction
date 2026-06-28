tag @e remove nbs_complete2
scoreboard objectives remove nbs_complete2
scoreboard objectives remove nbs_complete2_t
datapack disable "file/complete2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"complete2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]