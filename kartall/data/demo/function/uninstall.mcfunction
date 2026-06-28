tag @e remove nbs_demo
scoreboard objectives remove nbs_demo
scoreboard objectives remove nbs_demo_t
datapack disable "file/demo.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"demo.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]