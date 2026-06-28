scoreboard players add voidtick guardrailtool 1

execute if score voidtick guardrailtool >= guardrailtool-speed guardrailtool unless score guardrailtool-time guardrailtool matches 1 unless score guardrailtool-time guardrailtool matches 3 as @a if items entity @s weapon minecraft:carrot_on_a_stick[minecraft:custom_data~{guardrailtool:1}] at @s rotated 0 90 align xyz positioned ~0.5 ~-0.5 ~0.5 run function guardrailtool:verticalray

execute if score voidtick guardrailtool >= guardrailtool-speed guardrailtool run scoreboard players add guardrailtool-time guardrailtool 1
execute if score voidtick guardrailtool >= guardrailtool-speed guardrailtool run scoreboard players reset voidtick guardrailtool
execute if score guardrailtool-time guardrailtool matches 5.. run scoreboard players set guardrailtool-time guardrailtool 1
