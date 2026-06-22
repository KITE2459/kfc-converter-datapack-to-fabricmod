execute store result score @s tower.attack run data get entity @s data.attack
scoreboard players operation @a money += @s tower.attack
execute at @s run playsound block.chain.break record @a ~ ~ ~ 1.0 1.0