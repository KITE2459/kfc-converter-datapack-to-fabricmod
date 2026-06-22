execute if items entity @a weapon.offhand yellow_dye run scoreboard players set stage gameState 1
execute if items entity @a weapon.offhand yellow_dye run scoreboard players add game wave 1
execute if items entity @a weapon.offhand yellow_dye run scoreboard players set timer gameState -1
execute if score timer gameState >= #temp time run scoreboard players set stage gameState 1
execute if score timer gameState >= #temp time run scoreboard players add game wave 1
execute if score timer gameState >= #temp time run scoreboard players set timer gameState -1
execute unless entity @n[tag=enemy] run scoreboard players set stage gameState 1
execute unless entity @n[tag=enemy] run scoreboard players add game wave 1
execute unless entity @n[tag=enemy] run scoreboard players set timer gameState -1
