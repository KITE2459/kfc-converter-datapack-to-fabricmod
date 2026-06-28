execute as @a unless score @s kart-performance-limit-level matches 0.. run scoreboard players set @s kart-performance-limit-level 0
scoreboard players enable @a kart-performance-limit
execute as @a[scores={kart-performance-limit=1}] run function kartmain:limit-kart-spec/tellraw
execute as @a[scores={kart-performance-limit=2}] run function kartmain:limit-kart-spec/limit-100
execute as @a[scores={kart-performance-limit=3}] run function kartmain:limit-kart-spec/limit-98.5
execute as @a[scores={kart-performance-limit=4}] run function kartmain:limit-kart-spec/limit-97
execute as @a[scores={kart-performance-limit=5}] run function kartmain:limit-kart-spec/limit-95.5
execute as @a[scores={kart-performance-limit=6}] run function kartmain:limit-kart-spec/limit-94
execute as @a[scores={kart-performance-limit=7}] run function kartmain:limit-kart-spec/limit-92.5
execute as @a[scores={kart-performance-limit=8}] run function kartmain:limit-kart-spec/limit-91
execute as @a[scores={kart-performance-limit=9}] run function kartmain:limit-kart-spec/limit-89.5
execute as @a[scores={kart-performance-limit=10}] run function kartmain:limit-kart-spec/limit-88
execute as @a[scores={kart-performance-limit=11}] run function kartmain:limit-kart-spec/limit-86.5
execute as @a[scores={kart-performance-limit=12}] run function kartmain:limit-kart-spec/limit-85

execute as @a[scores={kart-performance-limit=2..}] run function kartmobil:s2c-value/unique/push-into-value

execute as @a[scores={kart-performance-limit=1..}] run scoreboard players set @s kart-performance-limit 0