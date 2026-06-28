tag @e remove nbs_ghzsonicsp
scoreboard objectives remove nbs_ghzsonicsp
scoreboard objectives remove nbs_ghzsonicsp_t
datapack disable "file/ghzsonicspring.zip"
tellraw @s ["",{"text":"[NBS] ","color":"gold","bold":true},{"text":"Data pack ","color":"yellow"},{"text":"ghzsonicspring.zip","color":"gold","underlined":true},{"text":" uninstalled successfully. You may now remove it from your data pack folder.","color":"yellow"}]