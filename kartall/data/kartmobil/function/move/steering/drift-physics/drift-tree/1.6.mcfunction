scoreboard players remove #drift-escape-force kartdrift 16

execute if score #drift-escape-force kartdrift matches 8.. positioned ^ ^ ^0.8 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.8
execute if score #drift-escape-force kartdrift matches 4.. positioned ^ ^ ^0.4 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.4
execute if score #drift-escape-force kartdrift matches 2.. positioned ^ ^ ^0.2 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.2
execute if score #drift-escape-force kartdrift matches 1.. positioned ^ ^ ^0.1 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.1

function kartmobil:move/steering/drift-physics/drift-tree/final
