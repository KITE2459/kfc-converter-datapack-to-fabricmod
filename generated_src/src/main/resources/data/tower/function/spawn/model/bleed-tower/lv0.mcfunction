summon block_display ~ ~1.5 ~ {Passengers:[{teleport_duration:3,Tags:[tower,tower.head],id:"minecraft:item_display",item:{id:"minecraft:wooden_sword"},item_display:"none",transformation:[-1.4142135624f,-1.4142135624f,0f,0f,1.4142135624f,-1.4142135624f,0f,0.5f,0f,0f,2f,0f,0f,0f,0f,1f]},{id:"minecraft:interaction",width:2f,height:-1.5f,response:1b,Tags:[tower,tower.hitbox]},{id:"minecraft:marker",Tags:[tower,bleed-tower,tower.head,tower.muzzle,tower.animation,tower.sound]}],Tags:[tower,bleed-tower,tower.core]}


function tower:spawn/model/supporter/lv0
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result score @s tower.model.y_sync run data get entity @s transformation.translation.[1] 1000
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run scoreboard players remove @s tower.model.y_sync 1500
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] store result entity @s transformation.translation.[1] float 0.001 run scoreboard players get @s tower.model.y_sync
kill @n[tag=tower.supporter,tag=not-allocated,type=block_display]
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] positioned ~ ~1.5 ~ run ride @s mount @n[tag=tower.core]
execute as @e[tag=tower.supporter,tag=not-allocated,type=item_display] run tag @s remove not-allocated
