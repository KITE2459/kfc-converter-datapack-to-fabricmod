scoreboard players operation @s kart-tire = @n[type=interaction] kart-tire

#멀티서버 & 모드용
function kartmobil:s2c-value/for-official-multiplay/push-into-value

particle minecraft:trial_spawner_detection ~ ~ ~ .5 .5 .5 0 25
execute at @s run particle minecraft:trial_spawner_detection ~ ~ ~ .5 .5 .5 0 25

playsound minecraft:block.trial_spawner.about_to_spawn_item weather @a ~ ~ ~ 1 2

execute if score @s kart-tire matches 0 run tellraw @s [{translate:"[레이싱 타이어]","color":"gray","bold":true},{translate:"를 선택했습니다.","color":"green","bold":false}]
execute if score @s kart-tire matches 1 run tellraw @s [{translate:"[스파이크 타이어]","color":"gray","italic":true,"bold":true},{translate:"를 선택했습니다.","color":"green","bold":false}]

