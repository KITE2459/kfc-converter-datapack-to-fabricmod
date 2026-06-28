#카트 얻기
scoreboard players enable @a getkart
scoreboard players reset @a[gamemode=spectator] getkart
execute as @a[scores={getkart=1..}] at @s run function kartmodel:getkartitem/getofficialkart/getkarthub

execute as @a[scores={getkart=-1699}] at @s run function kartmodel:getkartitem/ignore-nodelay