execute if entity @s[tag=mad-crash-spin-reverse] as @p at @s run rotate @s ~-70 ~
execute if entity @s[tag=!mad-crash-spin-reverse] as @p[tag=!mad-crash-spin-reverse] at @s run rotate @s ~70 ~

execute unless score @s kartcollisiontime matches 1.. run tag @s remove mad-crash-spin
execute unless score @s kartcollisiontime matches 1.. run tag @s remove mad-crash-spin-reverse