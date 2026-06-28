scoreboard players add #40tick check-prev 1
execute if score #40tick check-prev matches 41.. run scoreboard players set #40tick check-prev 0

scoreboard players add #20tick check-prev 1
execute if score #20tick check-prev matches 21.. run scoreboard players set #20tick check-prev 0

scoreboard players add #15tick check-prev 1
execute if score #15tick check-prev matches 16.. run scoreboard players set #15tick check-prev 0

execute as @a[tag=check-editor] at @s run function checkpoint:tool/main

execute as @a[tag=kart-multi-player] at @s unless score #time multi-main matches 0..100 run function checkpoint:system/multi-sort/main


execute as @a[tag=kart-multi-player] at @s if score #40tick check-prev matches 40 unless score #time multi-main matches 0..100 run function checkpoint:bbalag/main
