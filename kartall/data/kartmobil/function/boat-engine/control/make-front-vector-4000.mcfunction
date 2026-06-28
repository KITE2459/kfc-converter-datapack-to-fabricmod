execute positioned 0.0 0.0 0.0 run summon marker ~ ~ ~ {Tags:["boat-move-vector-marker"]}

rotate @n[tag=boat-move-vector-marker,type=marker] ~ 0

execute at @n[tag=boat-move-vector-marker,type=marker] run tp @n[tag=boat-move-vector-marker,type=marker] ^ ^ ^1

execute positioned 0.0 0.0 0.0 run summon marker ~ ~ ~ {Tags:["boat-move-vector-marker-2"]}
execute at @n[tag=boat-move-vector-marker-2,type=marker] facing entity @n[tag=boat-move-vector-marker,type=marker] feet unless entity @n[tag=boat-move-vector-marker,distance=..0.5,type=marker] run tp @n[tag=boat-move-vector-marker-2,type=marker] ^ ^ ^1

execute store result score #front-vector boat-move-x run data get entity @n[tag=boat-move-vector-marker-2,type=marker] Pos[0] 4000
execute store result score #front-vector boat-move-z run data get entity @n[tag=boat-move-vector-marker-2,type=marker] Pos[2] 4000

kill @e[tag=boat-move-vector-marker,type=marker]
kill @e[tag=boat-move-vector-marker-2,type=marker]
