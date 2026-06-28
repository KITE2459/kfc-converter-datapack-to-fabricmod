tag @e remove nbs_lavachicke
scoreboard objectives remove nbs_lavachicke
scoreboard objectives remove nbs_lavachicke_t
datapack disable "file/LavaChicken.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"LavaChicken.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]