tag @e remove nbs_mine2
scoreboard objectives remove nbs_mine2
scoreboard objectives remove nbs_mine2_t
datapack disable "file/mine2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"mine2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]