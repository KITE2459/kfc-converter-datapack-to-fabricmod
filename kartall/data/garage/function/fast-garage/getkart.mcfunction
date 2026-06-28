data remove entity @n[type=interaction] attack
data remove entity @n[type=interaction] interaction

tag @n[tag=fast-garage-marker,type=marker] add temp-asdf
execute at @s run function kartmain:makekart with entity @e[tag=temp-asdf,limit=1,type=marker] data.kart
tag @e[tag=temp-asdf,type=marker] remove temp-asdf