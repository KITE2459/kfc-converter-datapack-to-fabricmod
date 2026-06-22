# 소닉 충전
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run data modify entity @s data.last_rotation set from entity @s Rotation
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run rotate @s facing entity @e[scores={tower.y=-60},tag=tower.data,sort=furthest,limit=1] eyes
execute if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound entity.warden.sonic_charge record @a ~ ~ ~ 1.0 1.0 0.5

# 소닉 발사
execute if score @s enemy.skill-trigger.timer-cooldown matches -90..-40 run playsound entity.warden.sonic_boom record @a ~ ~ ~ 1.0 1.0 0.5
execute if score @s enemy.skill-trigger.timer-cooldown matches -90..-40 run summon marker ~ ~2 ~ {Tags:[sculk-sonicwave.blast]}
execute if score @s enemy.skill-trigger.timer-cooldown matches -91..-40 run data modify entity @n[tag=sculk-sonicwave.blast] Rotation set from entity @s Rotation
execute if score @s enemy.skill-trigger.timer-cooldown matches -90..-40 run scoreboard players set @e[tag=sculk-sonicwave.blast,distance=..2] enemy.hp 50

# 회전
execute if score @s enemy.skill-trigger.timer-cooldown matches -50..-40 run rotate @s ~3 0
execute if score @s enemy.skill-trigger.timer-cooldown matches -70..-50 run rotate @s ~-3 0
execute if score @s enemy.skill-trigger.timer-cooldown matches -90..-70 run rotate @s ~3 0

# 후딜 및 쿨타임 재설정
execute if score @s enemy.skill-trigger.timer-cooldown matches -92 run data modify entity @s Rotation set from entity @s data.last_rotation
tag @s[scores={enemy.skill-trigger.timer-cooldown=..-100}] remove enemy.skill-loop.2
tag @s[scores={enemy.skill-trigger.timer-cooldown=..-100}] add enemy.skill-loop.1
execute if score @s enemy.skill-trigger.timer-cooldown matches -100 run scoreboard players set @s enemy.skill-trigger.timer-cooldown 300
