# 소닉 충전
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches 0 unless entity @n[distance=..15,scores={tower.y=-60},tag=tower.data] run scoreboard players set @s enemy.skill-trigger.timer-cooldown 10
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches 0 run data modify entity @s data.last_rotation set from entity @s Rotation
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches 0 run rotate @s facing entity @e[scores={tower.y=-60},distance=..15,tag=tower.data,sort=furthest,limit=1] eyes
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches 0 run playsound entity.warden.sonic_charge record @a ~ ~ ~ 1.0 1.0 0.5

# 소닉 발사
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 run playsound entity.warden.sonic_boom record @a ~ ~ ~ 1.0 1.0 0.5
# ^ ^ ^15까지 범위 내 타워 스턴
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^1 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^2 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^3 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^4 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^5 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^6 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^7 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^8 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^9 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^10 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^11 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^12 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^13 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^14 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -40 at @s positioned ^ ^ ^15 run scoreboard players set @e[tag=tower.data,distance=..2] tower.state.stun 120
# 소닉 이펙트
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^1 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^2 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^3 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^4 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^5 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^6 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^7 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^8 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^9 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^10 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^11 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^12 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^13 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^14 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -45..-40 at @s positioned ^ ^ ^15 run particle minecraft:sonic_boom ~ ~ ~ 0 0 0 0 1 force @a

# 후딜 및 쿨타임 재설정
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -50 run data modify entity @s Rotation set from entity @s data.last_rotation
execute at @s positioned ~ ~2 ~ if score @s enemy.skill-trigger.timer-cooldown matches -50 run scoreboard players operation @s enemy.skill-trigger.timer-cooldown = @s enemy.skill-trigger.timer