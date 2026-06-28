scoreboard players set @s multi-spectate-time 0

tag @s add spectator-self

tag @a[scores={dev-count=1..}] add spectator-target-temp

spectate @n[tag=spectator-target-temp]

#만약 플레이어가 없다면
execute unless entity @a[tag=spectator-target-temp] run spectate @n[tag=multi-spectator-none-target]

tag @s remove spectator-self
tag @a[tag=spectator-target-temp] remove spectator-target-temp
