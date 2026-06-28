tag @e remove nbs_abyss2
scoreboard objectives remove nbs_abyss2
scoreboard objectives remove nbs_abyss2_t
datapack disable "file/abyss2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"abyss2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]