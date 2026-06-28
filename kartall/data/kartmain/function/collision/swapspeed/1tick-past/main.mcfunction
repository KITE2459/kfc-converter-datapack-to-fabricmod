summon marker ~ ~ ~ {Tags:["kart-a-past","kart-collision-past"]}
execute at @e[tag=carB,limit=1,type=#kartmobil:kartmobil] run summon marker ~ ~ ~ {Tags:["kart-b-past","kart-collision-past"]}

execute store result storage kartmain kartvector0 float 0.00005 on passengers if entity @s[tag=kart-old-velocity] run scoreboard players get @s kartmove
execute on passengers rotated as @s[tag=kart-old-velocity] rotated ~ 0 as @e[tag=kart-a-past,limit=1,type=marker] positioned as @s run function kartmain:collision/swapspeed/1tick-past/raycast with storage kartmain
execute rotated as @e[tag=carAdirection,limit=1,type=item_display] run rotate @e[tag=kart-a-past,limit=1,type=marker] ~ 0

execute store result storage kartmain kartvector0 float 0.00005 as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] on passengers if entity @s[tag=kart-old-velocity] run scoreboard players get @s kartmove
execute as @e[tag=carB,limit=1,type=#kartmobil:kartmobil] on passengers rotated as @s[tag=kart-old-velocity] rotated ~ 0 as @e[tag=kart-b-past,limit=1,type=marker] positioned as @s run function kartmain:collision/swapspeed/1tick-past/raycast with storage kartmain
execute rotated as @e[tag=carBdirection,limit=1,type=item_display] run rotate @e[tag=kart-b-past,limit=1,type=marker] ~ 0



#scoreboard players set #should-current-pos kartcollisiontime 0
#execute positioned as @e[tag=kart-a-past,limit=1,type=marker] facing entity @e[tag=kart-b-past,limit=1,type=marker] feet run rotate @e[tag=kart-normal-line,limit=1,type=marker] ~ ~
#execute positioned as @e[tag=carA,limit=1,type=#kartmobil:kartmobil] facing entity @e[tag=carB,limit=1,type=#kartmobil:kartmobil] feet run rotate @e[tag=kart-tangent-line,limit=1,type=marker] ~ ~
#
#execute as @e[tag=kart-normal-line,limit=1,type=marker] at @s positioned ^ ^ ^-1 rotated as @e[tag=kart-tangent-line,limit=1,type=marker] positioned ^ ^ ^1 if entity @s[distance=..1.4142135] run scoreboard players set #should-current-pos kartcollisiontime 1
#
#execute at @e[tag=kart-normal-line,limit=1,type=marker] run particle minecraft:angry_villager ^ ^ ^1
#execute at @e[tag=kart-normal-line,limit=1,type=marker] run particle minecraft:angry_villager ^ ^ ^2
#execute at @e[tag=kart-normal-line,limit=1,type=marker] run particle minecraft:angry_villager ^ ^ ^3
#execute at @e[tag=kart-normal-line,limit=1,type=marker] run particle minecraft:angry_villager ^ ^ ^4
#
#execute at @e[tag=kart-tangent-line,limit=1,type=marker] run particle minecraft:happy_villager ^ ^ ^1
#execute at @e[tag=kart-tangent-line,limit=1,type=marker] run particle minecraft:happy_villager ^ ^ ^2
#execute at @e[tag=kart-tangent-line,limit=1,type=marker] run particle minecraft:happy_villager ^ ^ ^3
#execute at @e[tag=kart-tangent-line,limit=1,type=marker] run particle minecraft:happy_villager ^ ^ ^4
#
#execute at @e[tag=kart-a-past,limit=1,type=marker] run particle minecraft:campfire_cosy_smoke ^ ^ ^
#execute at @e[tag=kart-b-past,limit=1,type=marker] run particle minecraft:glow ^ ^ ^
#
#execute at @e[tag=carA,limit=1] run particle minecraft:note ^ ^ ^
#execute at @e[tag=carB,limit=1] run particle minecraft:crit ^ ^ ^
#
#scoreboard players set #should-current-pos kartcollisiontime 1