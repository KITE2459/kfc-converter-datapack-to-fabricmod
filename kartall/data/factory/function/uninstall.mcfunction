tag @e remove nbs_factory
scoreboard objectives remove nbs_factory
scoreboard objectives remove nbs_factory_t
datapack disable "file/bgm-factory.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"bgm-factory.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]