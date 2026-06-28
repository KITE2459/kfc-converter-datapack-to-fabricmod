tag @e remove nbs_village2a
scoreboard objectives remove nbs_village2a
scoreboard objectives remove nbs_village2a_t
datapack disable "file/bgm-village2a.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-village2a.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]