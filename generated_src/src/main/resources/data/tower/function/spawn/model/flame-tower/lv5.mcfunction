kill @e[distance=..1,tag=tower,type=item_display]



execute as @n[tag=not-allocated] run kill @s
execute as @e[tag=tower.upgrade-1,type=item_display,distance=..0.5] at @s run ride @s mount @n[tag=tower.core]