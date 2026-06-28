scoreboard players remove @s multi-spectate-time 1

tag @s add spectator-self
execute as @a[tag=kart-multi-player] if score @s multi-spectator-id = @a[tag=spectator-self,limit=1] multi-spectator-id run tag @s add spectator-target-temp

tp @s @s
tp @s @p[tag=spectator-target-temp]

tag @s remove spectator-self
tag @a[tag=spectator-target-temp] remove spectator-target-temp