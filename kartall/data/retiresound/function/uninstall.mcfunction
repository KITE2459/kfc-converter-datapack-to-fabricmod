tag @e remove nbs_retiresoun
scoreboard objectives remove nbs_retiresoun
scoreboard objectives remove nbs_retiresoun_t
datapack disable "file/retiresound.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"retiresound.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]