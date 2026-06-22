$execute positioned ~ -60 ~ unless entity @n[tag=map.dest_point,distance=..$(speed)] run return 0
scoreboard players operation game game.base_health -= @s enemy.hp
execute on passengers run kill @s
scoreboard players set #temp game.return 1
kill @s