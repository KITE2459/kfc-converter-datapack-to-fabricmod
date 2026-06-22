execute if score @s hitscan-marker.area matches 1.. as @e[tag=enemy.target,distance=..5] at @s positioned ~-0.5 ~ ~-0.5 if entity @n[tag=hitscan-marker,dx=0,dy=1,dz=0] run tag @s add hitscan-area-effect

execute if score @s bullet.ability.chain matches 1.. run function tower:attack/fire/hitscan/hit-marker/move/detect-hitbox with storage tower temp
execute if score #temp game.return matches 41 run function tower:attack/fire/hitscan/hit-marker/move/hitscan-attribute-chain with storage tower temp