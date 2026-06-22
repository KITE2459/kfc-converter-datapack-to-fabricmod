kill @e[distance=..1,tag=tower.supporter,type=item_display]
function tower:spawn/model/supporter/lv2
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
