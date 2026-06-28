tag @e remove nbs_planet3
scoreboard objectives remove nbs_planet3
scoreboard objectives remove nbs_planet3_t
datapack disable "file/bgm-planet3.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-planet3.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]