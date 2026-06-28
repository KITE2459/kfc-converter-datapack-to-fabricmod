tag @e remove nbs_desert2
scoreboard objectives remove nbs_desert2
scoreboard objectives remove nbs_desert2_t
datapack disable "file/desert2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"desert2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]