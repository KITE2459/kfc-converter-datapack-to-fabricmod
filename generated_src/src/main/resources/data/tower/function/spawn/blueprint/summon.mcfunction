# 블루프린트 id (플레이어 구분용)
execute store result score @s blueprint_id run scoreboard players add global blueprint_seq 1

# 블루프린트 소환
$summon block_display ~ ~ ~ {teleport_duration:1,Tags:["blueprint"],transformation:{left_rotation:[0f,0f,0f,1f],right_rotation:[0f,0f,0f,1f],translation:[$(translation),0f,$(translation)],scale:[$(size),0.2f,$(size)]},block_state:{Name:"minecraft:lime_stained_glass"}}

# 블루프린트 id 할당
execute at @s as @e[type=minecraft:block_display,tag=blueprint,sort=nearest,limit=1] unless score @s blueprint_id matches 0.. run scoreboard players operation @s blueprint_id = global blueprint_seq

# 블루프린트 범위 마커 소환 및 id 할당
summon marker ~ ~ ~ {Tags:["blueprint_range"],Rotation:[0f,0f]}
summon marker ~ ~ ~ {Tags:["blueprint_range"],Rotation:[90f,0f]}
summon marker ~ ~ ~ {Tags:["blueprint_range"],Rotation:[180f,0f]}
summon marker ~ ~ ~ {Tags:["blueprint_range"],Rotation:[270f,0f]}
execute as @e[tag=blueprint_range] unless score @s blueprint_id matches 0.. run scoreboard players operation @s blueprint_id = global blueprint_seq

# 플레이어에게 블루프린트 조작 태그 부여
tag @s add blueprint_player