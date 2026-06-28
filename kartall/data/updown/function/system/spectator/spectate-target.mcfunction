execute if score @s multi-spectate-time matches 1.. run scoreboard players set @s multi-spectate-time 10
execute unless score @s multi-spectate-time matches 10 run scoreboard players set @s multi-spectate-time 5

tag @s add spectator-self
tag @a[scores={updown-count=1..}] add spectator-target-temp

tp @s @s
tp @s @n[tag=spectator-target-temp]

#만약 플레이어가 없다면
execute unless entity @a[tag=spectator-target-temp] run tp @s @n[tag=multi-spectator-none-target]

tag @s remove spectator-self
tag @a[tag=spectator-target-temp] remove spectator-target-temp