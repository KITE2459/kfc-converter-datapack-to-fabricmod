scoreboard players operation #kartdragaccel-2 kartmain = @s kartdrift
scoreboard players operation #kartdragaccel-2 kartmain /= #kart6 kartmain
scoreboard players operation @s kartmove += #kartdragaccel-2 kartmain

scoreboard players add @p[tag=kartpassenger] kartdrift 10