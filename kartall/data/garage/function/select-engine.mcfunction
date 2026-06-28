scoreboard players operation @s kart-engine = @n[type=interaction] kart-engine

#멀티서버 & 모드용
function kartmobil:s2c-value/for-official-multiplay/push-into-value

particle minecraft:trial_spawner_detection ~ ~ ~ .5 .5 .5 0 25
execute at @s run particle minecraft:trial_spawner_detection ~ ~ ~ .5 .5 .5 0 25

playsound minecraft:block.trial_spawner.about_to_spawn_item weather @a ~ ~ ~ 1 2

tellraw @s [{"entity":"@n[tag=kart-engine-text,type=text_display]","nbt":"text","font":"default","bold":true,"interpret":true},{translate:"엔진을 선택했습니다.","color":"green","bold":false}]


