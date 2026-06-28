tag @a[x=5245,y=4,z=4487,dx=27,dy=27,dz=26,gamemode=adventure] add garage-player

#건축 시스템
execute positioned 5258 4 4500 if entity @a[distance=..50] unless entity @a[tag=garage-player] run fill 5271 25 4487 5245 4 4513 air replace #garage:can-place-on
execute positioned 5258 4 4500 if entity @a[distance=..50] unless entity @a[tag=garage-player] run fill 5245 4 4513 5271 25 4487 light replace air
function garage:custom-model-maker/building-system/main

execute positioned 5258 4 4500 if entity @a[distance=..50] unless entity @a[tag=garage-player] run fill 5260 4 4514 5256 8 4514 air
execute positioned 5258 4 4500 if entity @a[distance=..50] unless entity @a[tag=garage-player] positioned 5248 5 4500 run kill @e[type=minecraft:item_display,distance=..0.01]

    #철창 문열어주기
    execute if entity @a[tag=garage-player] run fill 5260 4 4514 5256 8 4514 minecraft:iron_bars
    execute at @a[tag=garage-player] if block ~ ~-2 ~ minecraft:mushroom_stem run fill 5260 4 4514 5256 8 4514 air
    execute at @a[tag=garage-player] if block ~ ~-3 ~ minecraft:mushroom_stem run fill 5260 4 4514 5256 8 4514 air

execute as @a[tag=!garage-player,gamemode=adventure] if items entity @s container.* #garage:breakable2 run clear @s #garage:breakable2

tag @a[tag=garage-player] remove garage-player