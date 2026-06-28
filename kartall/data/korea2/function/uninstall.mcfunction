tag @e remove nbs_korea2
scoreboard objectives remove nbs_korea2
scoreboard objectives remove nbs_korea2_t
datapack disable "file/korea2.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"korea2.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]