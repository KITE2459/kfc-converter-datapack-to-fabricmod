tag @s add spectator-self
execute as @a[tag=kart-multi-player] if score @s multi-spectator-id = @n[tag=spectator-self] multi-spectator-id run tag @s add spectator-target-temp

#만약 플레이어가 없다면
execute if score @s multi-spectate-time matches ..0 unless entity @a[tag=spectator-target-temp] run scoreboard players operation @s multi-spectator-id = @r[tag=kart-multi-player] multi-spectator-id
execute if score @s multi-spectate-time matches ..0 unless entity @a[tag=spectator-target-temp] run function multiplay:system/spectator/spectate-target

tag @s remove spectator-self
tag @a[tag=spectator-target-temp] remove spectator-target-temp


