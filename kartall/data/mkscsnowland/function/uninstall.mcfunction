tag @e remove nbs_mkscsnowla
scoreboard objectives remove nbs_mkscsnowla
scoreboard objectives remove nbs_mkscsnowla_t
datapack disable "file/mkscsnowland.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"mkscsnowland.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]