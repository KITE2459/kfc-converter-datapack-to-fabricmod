$tp @s ^ ^ ^$(range)
execute at @s run tp @s ^ ^ ^-1.0
execute at @s run particle sweep_attack ~ ~ ~
execute at @s run playsound minecraft:entity.player.attack.sweep neutral @a ~ ~ ~ 0.3 1
execute at @s run function tower:attack/fire/sword/hit-marker/move/detect-hitbox with storage tower temp
$execute at @s run tp @s ^ ^ ^-$(range)
execute at @s run tp @s ^ ^ ^1.0