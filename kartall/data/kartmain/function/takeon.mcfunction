tag @s add playertemp

execute if entity @n[tag=kartsaddle,distance=..0.0001,predicate=!kartmobil:ifpassenger,type=#kartmobil:kartsaddle] run playsound minecraft:entity.bat.takeoff neutral @a ~ ~ ~ 1 1
execute unless entity @n[tag=kartsaddle,distance=..0.0001,predicate=!kartmobil:ifpassenger,type=#kartmobil:kartsaddle] run playsound minecraft:block.anvil.place neutral @s ~ ~ ~ 1 1

execute as @n[tag=kartsaddle,distance=..0.0001,predicate=!kartmobil:ifpassenger] run ride @p[tag=playertemp] dismount
execute as @n[tag=kartsaddle,distance=..0.0001,predicate=!kartmobil:ifpassenger] run ride @p[tag=playertemp] mount @s

tag @s remove playertemp