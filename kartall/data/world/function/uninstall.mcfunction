tag @e remove nbs_world
scoreboard objectives remove nbs_world
scoreboard objectives remove nbs_world_t
datapack disable "file/world.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"world.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]