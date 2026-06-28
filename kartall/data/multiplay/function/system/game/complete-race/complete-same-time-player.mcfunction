#tellraw @a {"score":{"name":"LogGamja","objective":"kart-milisec-calc"}}
#tellraw @a {"score":{"name":"Pangch","objective":"kart-milisec-calc"}}

#tellraw @p [{"selector":"@p"},{"score":{"name":"@p[tag=kart-multi-same-time]","objective":"kart-milisec-calc"}}]

scoreboard players set #max-same-time multi-rank 2147483647
scoreboard players operation #max-same-time multi-rank < @a[tag=kart-multi-same-time] kart-milisec-calc
scoreboard players operation @a[tag=kart-multi-same-time] kart-milisec-calc -= #max-same-time multi-rank

#tellraw @p [{"selector":"@p[tag=kart-multi-same-time,scores={kart-milisec-calc=0}]"},{"score":{"name":"#max-same-time","objective":"multi-rank"}}]

scoreboard players add @a[tag=kart-multi-same-time,scores={kart-milisec-calc=0}] achievement_multiplay_same_time 1
execute as @a[tag=kart-multi-same-time,scores={kart-milisec-calc=0}] at @s run function multiplay:system/game/complete-race/complete

scoreboard players operation @a[tag=kart-multi-same-time] kart-milisec-calc += #max-same-time multi-rank

execute if entity @a[tag=kart-multi-same-time] run function multiplay:system/game/complete-race/complete-same-time-player