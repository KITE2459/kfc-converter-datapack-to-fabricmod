tag @e remove nbs_village4
scoreboard objectives remove nbs_village4
scoreboard objectives remove nbs_village4_t
datapack disable "file/village4.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"village4.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]