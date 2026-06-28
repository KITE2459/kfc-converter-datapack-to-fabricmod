tag @e remove nbs_world2
scoreboard objectives remove nbs_world2
scoreboard objectives remove nbs_world2_t
datapack disable "world2"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"world2","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]