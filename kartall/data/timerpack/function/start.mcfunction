
scoreboard players set iswork timermain 1

execute if score reset timermain matches 1 run scoreboard players set time timermain 0
scoreboard players set reset timermain 0

scoreboard players reset * timerdisplay
scoreboard objectives setdisplay sidebar timerdisplay
