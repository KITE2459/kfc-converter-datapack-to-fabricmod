# 타워 속성 복제
$summon marker ~ ~ ~ {Tags:[tower,tower.data,tower.animation,$(model)]}
ride @n[tag=tower.data] mount @s
execute on passengers at @s[tag=tower.data] run data modify entity @s data set from storage tower temp2.components.minecraft:custom_data.Tower_Status

# 타워 업그레이드 속성 복제
summon marker ~ ~ ~ {Tags:[tower,tower.upgrade]}
ride @n[tag=tower.upgrade] mount @s
execute on passengers at @s[tag=tower.upgrade] run data modify entity @s data set from storage tower temp2.components.minecraft:custom_data.Tower_Upgrade

# muzzle 속성 복제
execute on passengers at @s[tag=tower.muzzle] run data modify entity @s data set from storage tower temp2.components.minecraft:custom_data.Tower_Status

# 타워 번호 할당
scoreboard players add total tower.number 1
execute on passengers run scoreboard players operation @s tower.number = total tower.number
scoreboard players operation @s tower.number = total tower.number
execute store result entity @s data.number int 1 run scoreboard players get total tower.number
execute on passengers store result entity @s data.number int 1 run scoreboard players get total tower.number
scoreboard players set @n[tag=tower.data] tower.level 0

# 타워 위치 할당
execute if score #temp blueprint_id matches 1 run scoreboard players set @n[tag=tower.data] tower.y -60
execute if score #temp blueprint_id matches 2 run scoreboard players set @n[tag=tower.data] tower.y -52

# 사거리 표시기 소환
execute at @s run summon marker ~ ~ ~ {Tags:[tower,tower.upgrade_range]}

# 타워 속성 스코어보드에 값 저장
execute on passengers at @s[tag=tower] store result score @s tower.animation run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.attack_speed 1.0
execute on passengers at @s[tag=tower.muzzle] store result score @s tower.attack run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.attack 1.0
execute on passengers at @s[tag=tower.muzzle] store result score @s tower.attack_speed run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.attack_speed 1.0
execute on passengers at @s[tag=tower.muzzle] store result score @s tower.target_mode run data get storage tower temp2.components.minecraft:custom_data.Tower_Status.target_mode 1.0

execute on passengers at @s[tag=tower.muzzle,tag=farm] run scoreboard players operation @s tower.attack_countdown = @s tower.attack_speed
execute on passengers at @s[tag=tower.muzzle,tag=farm] run scoreboard players remove @s tower.attack_countdown 1