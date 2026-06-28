tag @e remove nbs_mkddrainbo
scoreboard objectives remove nbs_mkddrainbo
scoreboard objectives remove nbs_mkddrainbo_t
datapack disable "file/bgm-mkddrainbow.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-mkddrainbow.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]