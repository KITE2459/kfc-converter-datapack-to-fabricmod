execute unless score #is-official-multi multi-main matches 1 run return 0

execute store result storage minecraft:kartmain kartengine int 1 run scoreboard players get @s kart-engine
execute store result storage minecraft:kartmain kartperformancelimitlevel int 1 run scoreboard players get @s kart-performance-limit-level
execute store result storage minecraft:kartmain karttire int 1 run scoreboard players get @s kart-tire

function kartmobil:s2c-value/for-official-multiplay/push-into-value-macro with storage minecraft:kartmain