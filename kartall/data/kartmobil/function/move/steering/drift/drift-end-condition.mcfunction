#
execute if score @s kart-engine matches 6 if score #kartangle kartmain matches -250..250 run return run function kartmobil:move/steering/drift/drift-end
execute if score @s kart-engine matches 8 if score #kartangle kartmain matches -250..250 run return run function kartmobil:move/steering/drift/drift-end
execute if score @s kart-engine matches 1007 if score #kartangle kartmain matches -150..150 run return run function kartmobil:move/steering/drift/drift-end

execute if score @s kart-engine matches 1000 if score #kartangle kartmain matches -100..100 run return run function kartmobil:move/steering/drift/drift-end

execute if score @s kart-engine matches 1002 unless score @s kartdrifttime matches 8.. if score #kartangle kartmain matches -8..8 run return run function kartmobil:move/steering/drift/drift-end
execute if score @s kart-engine matches 1002 if score @s kartdrifttime matches 8.. if score #kartangle kartmain matches -250..250 run return run function kartmobil:move/steering/drift/drift-end

execute if score @s kart-engine matches 1003 run return 1
execute if score @s kart-engine matches 1008 run return 1

execute if score @s kart-engine matches 1006 run return run function kartmobil:move/steering/drift/f1-drift-end

#기본
execute if score #kartangle kartmain matches -210..210 run function kartmobil:move/steering/drift/drift-end