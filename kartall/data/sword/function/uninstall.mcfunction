tag @e remove nbs_sword
scoreboard objectives remove nbs_sword
scoreboard objectives remove nbs_sword_t
datapack disable "file/sword.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"sword.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]