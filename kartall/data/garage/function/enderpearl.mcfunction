tag @s add ender-self

execute at @s on origin facing entity @s feet as @e[tag=ender-self,limit=1,distance=..1,type=ender_pearl] facing ^ ^ ^-1 unless block ^ ^ ^1 #kartmobil:ignoreblock run function garage:stop-enderpearl
execute at @s on origin facing entity @s feet as @e[tag=ender-self,limit=1,distance=..1,type=ender_pearl] facing ^ ^ ^-1 unless block ^ ^ ^1.5 #kartmobil:ignoreblock run function garage:stop-enderpearl
execute at @s on origin facing entity @s feet as @e[tag=ender-self,limit=1,distance=..1,type=ender_pearl] facing ^ ^ ^-1 unless block ^ ^ ^2 #kartmobil:ignoreblock run function garage:stop-enderpearl
execute at @s on origin facing entity @s feet as @e[tag=ender-self,limit=1,distance=..1,type=ender_pearl] facing ^ ^ ^-1 unless block ^ ^ ^2.5 #kartmobil:ignoreblock run function garage:stop-enderpearl
execute at @s on origin facing entity @s feet as @e[tag=ender-self,limit=1,distance=..1,type=ender_pearl] facing ^ ^ ^-1 unless block ^ ^ ^3 #kartmobil:ignoreblock run function garage:stop-enderpearl
execute at @s on origin facing entity @s feet as @e[tag=ender-self,limit=1,distance=..1,type=ender_pearl] facing ^ ^ ^-1 unless block ^ ^ ^0.5 #kartmobil:ignoreblock run function garage:stop-enderpearl

tag @s remove ender-self
