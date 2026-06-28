    tag @s remove kart-a-press
    tag @s remove kart-a-release
    execute if entity @s[tag=kart-a-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:a] run tag @s add kart-a-release
    execute if entity @s[tag=kart-a-pressed] if entity @p[tag=kartpassenger,predicate=!kartmobil:a] run tag @s remove kart-a-pressed
    execute if entity @s[tag=!kart-a-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run tag @s add kart-a-press
    execute if entity @s[tag=!kart-a-pressed] if entity @p[tag=kartpassenger,predicate=kartmobil:a] run tag @s add kart-a-pressed

execute if score @s kart-nodelay-timer matches 1.. run scoreboard players remove @s kart-nodelay-timer 1
execute if score @s kart-nodelay-timer matches 1 run scoreboard players reset @s kart-nodelay-detect

execute unless score @s[tag=kart-a-press] kart-nodelay-timer matches 1.. run scoreboard players set @s kart-nodelay-timer 18
execute unless score @s[tag=kart-boost-press,tag=!kart-a-press] kart-nodelay-timer matches 1.. run scoreboard players set @s kart-nodelay-timer 18

execute if score @s[tag=kart-a-press] kart-nodelay-timer matches 1.. run scoreboard players add @s kart-nodelay-detect 1
execute if score @s[tag=kart-boost-press,tag=!kart-a-press] kart-nodelay-timer matches 1.. run scoreboard players add @s kart-nodelay-detect 1

execute if score @s kart-nodelay-detect matches 4.. run function kartmobil:control/nodelay-detect/detected