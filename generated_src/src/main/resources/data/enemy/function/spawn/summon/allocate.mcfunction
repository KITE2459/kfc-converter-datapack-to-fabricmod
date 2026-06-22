# 스테이더스 복사
data modify entity @s data set from storage enemy data

# 체력 할당
execute store result score @s enemy.hp run data get storage enemy data.health 1.0
scoreboard players operation @s enemy.max_hp = @s enemy.hp

# 방어력 할당
execute store result score @s enemy.defence run data get storage enemy data.defence 1.0

# 능력 할당
execute store result score @s enemy.attribute.healing run data get storage enemy data.attribute.healing 1.0
execute store result score @s enemy.attribute.dealing run data get storage enemy data.attribute.dealing 1.0

# 디스폰 방지
data modify entity @s PersistenceRequired set value 1b
effect give @s instant_health infinite 0 true
effect give @s resistance infinite 4 true

# 효과 타이머 초기화
scoreboard players set @s enemy.attribute.timer 0

# 진행도 초기화
scoreboard players set @s enemy.progress 0

# 충돌 방지
team join noColison @s

# 디버프 초기화
scoreboard players set @s enemy.state.freeze 0
scoreboard players set @s enemy.state.stun 0
scoreboard players set @s enemy.state.poison 0
scoreboard players set @s enemy.state.flame 0
scoreboard players set @s enemy.state.bleed 0
scoreboard players set @s enemy.state.corruption 0

# 스킬 트리거 스코어 설정
execute if data storage enemy data.skills.low_hp store result score @s enemy.skill-trigger.hp run data get storage enemy data.skills.low_hp 1.0
execute if data storage enemy data.skills.timer store result score @s enemy.skill-trigger.timer run data get storage enemy data.skills.timer 1.0
execute if data storage enemy data.skills.timer run scoreboard players operation @s enemy.skill-trigger.timer-cooldown = @s enemy.skill-trigger.timer

# 무한 모드 보정 적용
execute if score game game.difficulty matches 5 run function game:infinity/apply_enemy_bonus

# 스폰포인트랑 방향 동기화
data modify entity @s Rotation set from entity @n[tag=map.spawn_point] Rotation
execute on passengers at @s run data modify entity @s Rotation set from entity @n[tag=map.spawn_point] Rotation
# execute at @n[tag=map.spawn_point] run tp @s ~ ~ ~
scoreboard players set @s enemy.onGround 50

# 만약 progress 데이터가 있으면 진행도 동기화
execute if score @n[tag=map.spawn_point] enemy.progress matches 1.. run scoreboard players operation @s enemy.progress = @n[tag=map.spawn_point] enemy.progress

# 데이터 할당 완료 처리
scoreboard players add global enemy.number 1
scoreboard players operation @s enemy.number = global enemy.number