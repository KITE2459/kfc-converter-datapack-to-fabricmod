execute if score #bonus_round wave matches ..0 run return 0
scoreboard players set #hp_step wave 1025
scoreboard players operation #hp_mul wave *= #hp_step wave
scoreboard players set #base wave 1000
scoreboard players operation #hp_mul wave /= #base wave
scoreboard players set #speed_step wave 1005
scoreboard players operation #speed_mul wave *= #speed_step wave
scoreboard players operation #speed_mul wave /= #base wave
scoreboard players remove #bonus_round wave 1
function game:infinity/apply_enemy_bonus_loop
