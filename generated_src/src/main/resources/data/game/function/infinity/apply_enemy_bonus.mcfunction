scoreboard players operation #bonus_round wave = game wave
scoreboard players set #hp_mul wave 1000
scoreboard players set #speed_mul wave 1000
function game:infinity/apply_enemy_bonus_loop

scoreboard players operation @s enemy.hp *= #hp_mul wave
scoreboard players set #base wave 1000
scoreboard players operation @s enemy.hp /= #base wave
scoreboard players operation @s enemy.max_hp *= #hp_mul wave
scoreboard players operation @s enemy.max_hp /= #base wave

scoreboard players operation #def_add wave = game wave
scoreboard players set #five wave 5
scoreboard players operation #def_add wave /= #five wave
scoreboard players set #three wave 1
scoreboard players operation #def_add wave *= #three wave
scoreboard players operation @s enemy.defence += #def_add wave

execute store result score #enemy_speed enemy.speed run data get entity @s data.speed 100000
scoreboard players operation #enemy_speed enemy.speed *= #speed_mul wave
scoreboard players operation #enemy_speed enemy.speed /= #base wave
execute store result entity @s data.speed float 0.00001 run scoreboard players get #enemy_speed enemy.speed
