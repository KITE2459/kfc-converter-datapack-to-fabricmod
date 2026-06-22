# 보이기
execute at @a run tag @e[distance=..7,tag=armory-tower,type=item_display] add armory-show
execute as @e[tag=armory-show,type=item_display] at @s run function tower:armory/summon/main

# 숨기기
execute as @e[tag=armory-show,type=item_display] at @s unless entity @a[distance=..7] run function tower:armory/summon/remove