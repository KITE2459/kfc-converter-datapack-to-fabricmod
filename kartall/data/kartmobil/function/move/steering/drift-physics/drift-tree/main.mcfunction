scoreboard players operation #drift-escape-force kartdrift = #kartdrift kartmain

execute if score #kartdrift kartmain matches 1..1023 run function kartmobil:move/steering/drift-physics/drift-tree/start-tree

execute unless score #kartdrift kartmain matches 1..1023 store result storage kartmain driftmacro float 0.1 run scoreboard players get #kartdrift kartmain
execute unless score #kartdrift kartmain matches 1..1023 run function kartmobil:move/steering/drift-physics/drift-tree/fallback-macro with storage kartmain