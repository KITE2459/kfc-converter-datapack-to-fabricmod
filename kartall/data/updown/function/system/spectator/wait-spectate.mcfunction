scoreboard players remove @s multi-spectate-time 1

tag @s add spectator-self
tag @a[scores={updown-count=1..}] add spectator-target-temp

tp @s @s
tp @s @n[tag=spectator-target-temp]

tag @s remove spectator-self
tag @a[tag=spectator-target-temp] remove spectator-target-temp