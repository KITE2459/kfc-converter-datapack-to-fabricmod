scoreboard objectives add carrot minecraft.used:minecraft.carrot_on_a_stick

scoreboard objectives add guardrailtool dummy
execute unless score guardrailtool-speed guardrailtool matches 0.. run scoreboard players set guardrailtool-speed guardrailtool 2