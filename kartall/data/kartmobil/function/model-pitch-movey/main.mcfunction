
rotate @s ~ 0

execute on vehicle run scoreboard players operation #movetp-y-pitch kartmovey = @s kartmovey

execute if score #movetp-y-pitch kartmovey matches 3200.. rotated as @s run rotate @s ~ ~12
execute if score #movetp-y-pitch kartmovey matches 3200.. rotated as @s run scoreboard players remove #movetp-y-pitch kartmovey 3200
execute if score #movetp-y-pitch kartmovey matches 1600.. rotated as @s run rotate @s ~ ~6
execute if score #movetp-y-pitch kartmovey matches 1600.. rotated as @s run scoreboard players remove #movetp-y-pitch kartmovey 1600
execute if score #movetp-y-pitch kartmovey matches 800.. rotated as @s run rotate @s ~ ~3
execute if score #movetp-y-pitch kartmovey matches 800.. rotated as @s run scoreboard players remove #movetp-y-pitch kartmovey 800
execute if score #movetp-y-pitch kartmovey matches 400.. rotated as @s run rotate @s ~ ~1.5
execute if score #movetp-y-pitch kartmovey matches 400.. rotated as @s run scoreboard players remove #movetp-y-pitch kartmovey 400
execute if score #movetp-y-pitch kartmovey matches 200.. rotated as @s run rotate @s ~ ~0.75
execute if score #movetp-y-pitch kartmovey matches 200.. rotated as @s run scoreboard players remove #movetp-y-pitch kartmovey 200
execute if score #movetp-y-pitch kartmovey matches 100.. rotated as @s run rotate @s ~ ~0.375
execute if score #movetp-y-pitch kartmovey matches 100.. rotated as @s run scoreboard players remove #movetp-y-pitch kartmovey 100

execute if score #movetp-y-pitch kartmovey matches ..-3200 rotated as @s run rotate @s ~ ~-12
execute if score #movetp-y-pitch kartmovey matches ..-3200 rotated as @s run scoreboard players add #movetp-y-pitch kartmovey 3200
execute if score #movetp-y-pitch kartmovey matches ..-1600 rotated as @s run rotate @s ~ ~-6
execute if score #movetp-y-pitch kartmovey matches ..-1600 rotated as @s run scoreboard players add #movetp-y-pitch kartmovey 1600
execute if score #movetp-y-pitch kartmovey matches ..-800 rotated as @s run rotate @s ~ ~-3
execute if score #movetp-y-pitch kartmovey matches ..-800 rotated as @s run scoreboard players add #movetp-y-pitch kartmovey 800
execute if score #movetp-y-pitch kartmovey matches ..-400 rotated as @s run rotate @s ~ ~-1.5
execute if score #movetp-y-pitch kartmovey matches ..-400 rotated as @s run scoreboard players add #movetp-y-pitch kartmovey 400
execute if score #movetp-y-pitch kartmovey matches ..-200 rotated as @s run rotate @s ~ ~-0.75
execute if score #movetp-y-pitch kartmovey matches ..-200 rotated as @s run scoreboard players add #movetp-y-pitch kartmovey 200
execute if score #movetp-y-pitch kartmovey matches ..-100 rotated as @s run rotate @s ~ ~-0.375
execute if score #movetp-y-pitch kartmovey matches ..-100 rotated as @s run scoreboard players add #movetp-y-pitch kartmovey 100

