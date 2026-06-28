scoreboard players remove #drift-escape-force kartdrift 4

execute if score #drift-escape-force kartdrift matches 2.. positioned ^ ^ ^0.2 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.2
execute if score #drift-escape-force kartdrift matches 1.. positioned ^ ^ ^0.1 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.1

function kartmobil:move/steering/drift-physics/drift-tree/final