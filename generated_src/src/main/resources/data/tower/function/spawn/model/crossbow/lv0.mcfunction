summon block_display ~ ~1 ~ {Passengers:[{id:"minecraft:item_display",item:{id:"minecraft:crossbow"},item_display:"none",transformation:{left_rotation:[0.27059805f,0.65328148f,0.65328148f,-0.27059805f],right_rotation:[0f,0f,0f,1f],translation:[0f,0f,0f],scale:[2f,2f,2f]},Tags:[tower,crossbow,tower.head,"tower.animation0",tower.animation,upgrade-parts.1],teleport_duration:3},{id:"minecraft:interaction",width:2f,height:-1f,response:1b,Tags:[tower,tower.hitbox]},{id:"minecraft:marker",Tags:[tower,crossbow,tower.head,tower.muzzle,tower.animation,tower.sound]}],Tags:[tower,crossbow,tower.core]}

function tower:spawn/model/supporter/lv0
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1000
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated