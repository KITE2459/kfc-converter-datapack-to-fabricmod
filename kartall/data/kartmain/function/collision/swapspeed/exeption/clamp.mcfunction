#execute if score #vector-vt kartcollisiontime matches 91.. rotated as @e[tag=kart-normal-line,limit=1,type=marker] run rotate @e[tag=kart-vector-result-relative,limit=1,type=marker] ~-90 0
#execute if score #vector-vt kartcollisiontime matches 91.. rotated as @e[tag=kart-normal-line,limit=1,type=marker] run scoreboard players set #vector-vt kartcollisiontime 90

#execute if score #vector-vt kartcollisiontime matches ..-91 rotated as @e[tag=kart-normal-line,limit=1,type=marker] run rotate @e[tag=kart-vector-result-relative,limit=1,type=marker] ~90 0
#execute if score #vector-vt kartcollisiontime matches ..-91 rotated as @e[tag=kart-normal-line,limit=1,type=marker] run scoreboard players set #vector-vt kartcollisiontime -90

execute if score #vector-vt kartcollisiontime matches 91.. run scoreboard players set #vector-vt kartcollisiontime 90
execute if score #vector-vt kartcollisiontime matches ..-91 run scoreboard players set #vector-vt kartcollisiontime -90