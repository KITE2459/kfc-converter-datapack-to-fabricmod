$execute store result score #forceload trackselect-temp positioned $(posx) $(posy) $(posz) run forceload add ~ ~

$execute positioned $(posx) $(posy) $(posz) align y positioned ~ ~0.05 ~ run function kartmobil:move/movetp/tp-bug-fix/tp

$execute if score #forceload trackselect-temp matches 1 positioned $(posx) $(posy) $(posz) run forceload remove ~ ~