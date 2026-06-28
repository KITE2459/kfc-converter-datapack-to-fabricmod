
scoreboard players operation #temp bgm-section-target = @s nbs_rainbow1_t
scoreboard players add #temp bgm-section-target 2

scoreboard players set #measure bgm-section-target 64
scoreboard players operation #temp bgm-section-target %= #measure bgm-section-target

execute if score #temp bgm-section-target matches 0..2 run function bgm-room:loop-bgm-system/rainbow-road/stop-all
execute if score #temp bgm-section-target matches 0..2 run function rainbow2:play
