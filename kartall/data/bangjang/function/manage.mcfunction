execute if score #alert multi-main matches 1 run function bangjang:alert
effect give @a[tag=bangjang] minecraft:glowing 1 1 false

execute if entity @a[tag=!multi-hub-player,tag=bangjang,limit=1] run tag @a[tag=!multi-hub-player,tag=bangjang] remove bangjang

execute if entity @a[x=-26,y=7,z=154,dx=22,dy=-8,dz=8,tag=!bangjang] as @a[x=-26,y=7,z=154,dx=22,dy=-8,dz=8,tag=!bangjang] run function bangjang:kicknonbangjang

execute as @a[tag=multi-hub-player,tag=!bangjang,nbt={HurtTime:10s}] at @s run function bangjang:toss-bangjang

execute unless entity @a[tag=bangjang,limit=1] run function bangjang:nonbangjang