#execute if score #floor-distance floor-distance matches 2.. if score @s kartmovey matches 500.. run function kartmobil:move/movetp/landing-sound
execute if score @s kartmovey matches 500.. run function kartmobil:move/movetp/landing-sound

execute align y positioned ~ ~0.05 ~ run function kartmobil:move/movetp/tp-bug-fix/tp
scoreboard players set @s[scores={kartmovey=1..}] kartmovey 0

tag @s remove kart-jumped