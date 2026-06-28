scoreboard players set @s multi-spectate-time 0

tag @s add spectator-self
#execute as @a[tag=kart-multi-player] if score @s multi-spectator-id = @n[tag=spectator-self] multi-spectator-id run tag @s add spectator-target-temp

#등수 기반으로 수정
execute as @a[tag=kart-multi-player] if score @s multi-spectator-id = @a[tag=spectator-self,limit=1] multi-spectator-id run tag @s add spectator-target-temp
spectate @p[tag=spectator-target-temp]

#만약 플레이어가 없다면
execute unless entity @a[tag=spectator-target-temp] run spectate @n[tag=multi-spectator-none-target]

tag @s remove spectator-self
tag @a[tag=spectator-target-temp] remove spectator-target-temp
