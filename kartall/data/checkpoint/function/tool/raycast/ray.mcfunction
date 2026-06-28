particle dust{color:[0, 1, 1],scale:3} ~ ~0.25 ~ 0 -1000 0 1000 0 force @a

execute unless entity @s[distance=..4] positioned ^ ^ ^4 run function checkpoint:tool/raycast/ray