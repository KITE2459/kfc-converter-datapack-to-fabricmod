function kartmobil:control/boost-gauge/rally-main

#전진 키 이벤트(불빛 깜빡임을 위해)
tag @s remove kart-w-press
tag @s remove kart-w-release
execute if entity @s[tag=kart-w-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:w] run tag @s add kart-w-release
execute if entity @s[tag=kart-w-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:w] run tag @s remove kart-w-pressed
execute if entity @s[tag=!kart-w-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:w] run tag @s add kart-w-press
execute if entity @s[tag=!kart-w-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:w] run tag @s add kart-w-pressed

#부스터 키 이벤트
tag @s remove kart-gear-press
execute if entity @s[tag=kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:a,predicate=!kartmobil:d] run tag @s remove kart-gear-pressed
execute if entity @s[tag=!kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run tag @s add kart-gear-press
execute if entity @s[tag=!kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run tag @s add kart-gear-press
execute if entity @s[tag=!kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run tag @s add kart-gear-pressed
execute if entity @s[tag=!kart-gear-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:d] run tag @s add kart-gear-pressed

#페달조작
execute if entity @p[tag=kartpassenger,predicate=kartmobil:w] run function kartmobil:control/rally-mode/accel
execute if entity @p[tag=kartpassenger,predicate=kartmobil:s] run function kartmobil:control/brake

#기어조작
execute unless score @s[tag=kart-gear-press] kartboostcount matches 5.. if entity @p[tag=kartpassenger,predicate=kartmobil:d] run scoreboard players add @s kartboostcount 1
execute unless score @s[tag=kart-gear-press] kartboostcount matches ..1 if entity @p[tag=kartpassenger,predicate=kartmobil:a] run scoreboard players remove @s kartboostcount 1

execute if entity @s[tag=kart-gear-press] at @s as @e[limit=2] run playsound minecraft:block.nether_bricks.break weather @a[tag=kart-listener] ~ ~ ~ 1.5 0