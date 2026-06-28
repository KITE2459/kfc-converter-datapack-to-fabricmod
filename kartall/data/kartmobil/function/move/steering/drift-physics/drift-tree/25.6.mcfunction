scoreboard players remove #drift-escape-force kartdrift 256

execute if score #drift-escape-force kartdrift matches 128.. positioned ^ ^ ^12.8 run return run function kartmobil:move/steering/drift-physics/drift-tree/12.8
execute if score #drift-escape-force kartdrift matches 64.. positioned ^ ^ ^6.4 run return run function kartmobil:move/steering/drift-physics/drift-tree/6.4
execute if score #drift-escape-force kartdrift matches 32.. positioned ^ ^ ^3.2 run return run function kartmobil:move/steering/drift-physics/drift-tree/3.2
execute if score #drift-escape-force kartdrift matches 16.. positioned ^ ^ ^1.6 run return run function kartmobil:move/steering/drift-physics/drift-tree/1.6
execute if score #drift-escape-force kartdrift matches 8.. positioned ^ ^ ^0.8 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.8
execute if score #drift-escape-force kartdrift matches 4.. positioned ^ ^ ^0.4 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.4
execute if score #drift-escape-force kartdrift matches 2.. positioned ^ ^ ^0.2 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.2
execute if score #drift-escape-force kartdrift matches 1.. positioned ^ ^ ^0.1 run return run function kartmobil:move/steering/drift-physics/drift-tree/0.1

function kartmobil:move/steering/drift-physics/drift-tree/final
