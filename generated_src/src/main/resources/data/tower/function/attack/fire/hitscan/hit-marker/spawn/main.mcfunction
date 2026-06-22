summon marker ~ ~ ~ {Tags:[hitscan-marker]}
execute as @n[tag=hitscan-marker] at @s unless score @s bullet.attack matches 0.. run function tower:attack/fire/hitscan/hit-marker/spawn/malloc
execute as @n[tag=hitscan-marker] at @s run function tower:attack/fire/hitscan/hit-marker/move/main with storage tower temp