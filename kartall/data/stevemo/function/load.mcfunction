scoreboard objectives add kartstevecut dummy
scoreboard players reset * kartstevecut
scoreboard players set #-1 kartstevecut -1
scoreboard players set #loop1 kartstevecut -1
execute positioned 94 6 -588 run tag @e[limit=4,distance=..2,type=item_frame] add moveitemframe