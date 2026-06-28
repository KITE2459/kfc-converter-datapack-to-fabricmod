#https://www.desmos.com/calculator/y29qyldqyn

execute if score #angle kartcollisiontime matches 0..44 run function kartmain:collision/swapspeed/hitbox-filter/0-44
execute if score #angle kartcollisiontime matches 45..89 run function kartmain:collision/swapspeed/hitbox-filter/45-89
execute if score #angle kartcollisiontime matches 90..134 run function kartmain:collision/swapspeed/hitbox-filter/90-134
execute if score #angle kartcollisiontime matches 135..179 run function kartmain:collision/swapspeed/hitbox-filter/135-179

#보통 비정상
execute if score #angle kartcollisiontime matches 180.. run function kartmain:collision/swapspeed/hitbox-filter/180-

scoreboard players operation #angle kartcollisiontime = #nemo-result kartcollisiontime
