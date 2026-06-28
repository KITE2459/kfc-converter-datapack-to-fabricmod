tag @e remove nbs_rkey
scoreboard objectives remove nbs_rkey
scoreboard objectives remove nbs_rkey_t
datapack disable "file/rkey.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"rkey.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]