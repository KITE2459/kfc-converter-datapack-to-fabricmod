summon marker ~ ~ ~ {Tags:[sword-hit-marker]}
execute as @n[tag=sword-hit-marker] at @s unless score @s bullet.attack matches 0.. run function tower:attack/fire/sword/hit-marker/spawn/malloc
execute as @n[tag=sword-hit-marker] at @s run function tower:attack/fire/sword/hit-marker/move/main with storage tower temp