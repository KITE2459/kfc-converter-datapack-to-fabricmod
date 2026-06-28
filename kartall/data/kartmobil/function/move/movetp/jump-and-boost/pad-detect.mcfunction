execute if entity @s[tag=kart-direction-fixed-by-pad] run return 1

#점프패드, 부스터패드
execute positioned ~ ~-0.5 ~ if block ~ ~ ~ slime_block run function kartmobil:move/movetp/jump-and-boost/jumppad
execute positioned ~ ~-1.5 ~ if block ~ ~ ~ slime_block run function kartmobil:move/movetp/jump-and-boost/jumppad

execute unless score @s kartboostpadtime matches 1.. positioned ~ ~-0.5 ~ if block ~ ~ ~ magenta_glazed_terracotta run function kartmobil:move/movetp/jump-and-boost/boosterpad
execute unless score @s kartboostpadtime matches 1.. positioned ~ ~-1.5 ~ if block ~ ~ ~ magenta_glazed_terracotta run function kartmobil:move/movetp/jump-and-boost/boosterpad

execute if entity @s[tag=!kart-command-pad-executed] run function kartmobil:move/movetp/jump-and-boost/command-pad-detect