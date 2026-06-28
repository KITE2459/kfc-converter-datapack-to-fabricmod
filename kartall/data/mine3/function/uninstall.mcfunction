tag @e remove nbs_mine3
scoreboard objectives remove nbs_mine3
scoreboard objectives remove nbs_mine3_t
datapack disable "mine3"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"mine3","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]