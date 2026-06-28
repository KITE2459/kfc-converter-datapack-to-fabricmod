scoreboard players operation #kartspeed kartmove = @s kartmove
scoreboard players operation #kartspeed kartmove /= #kart278 kartmain

execute if score @s kart-engine matches 0 run function kartmobil:sound-and-fov/x/sound
execute if score @s kart-engine matches 1 run function kartmobil:sound-and-fov/ex/sound
execute if score @s kart-engine matches 2 run function kartmobil:sound-and-fov/jiu/sound
execute if score @s kart-engine matches 3 run function kartmobil:sound-and-fov/new/sound
execute if score @s kart-engine matches 4 run function kartmobil:sound-and-fov/z7/sound
execute if score @s kart-engine matches 5 run function kartmobil:sound-and-fov/v1/sound
execute if score @s kart-engine matches 6 run function kartmobil:sound-and-fov/a2/sound
execute if score @s kart-engine matches 7 run function kartmobil:sound-and-fov/1.0/sound
execute if score @s kart-engine matches 8 run function kartmobil:sound-and-fov/pro/sound
execute if score @s kart-engine matches 9 run function kartmobil:sound-and-fov/krp/sound
execute if score @s kart-engine matches 10 run function kartmobil:sound-and-fov/xun/sound
execute if score @s kart-engine matches 11 run function kartmobil:sound-and-fov/sr/sound

execute if score @s kart-engine matches 1000 run function kartmobil:sound-and-fov/jiu/sound
execute if score @s kart-engine matches 1001 run function kartmobil:sound-and-fov/x/sound
execute if score @s kart-engine matches 1002 run function kartmobil:sound-and-fov/jiu/sound
execute if score @s kart-engine matches 1003 run function kartmobil:sound-and-fov/v1/sound

execute if score @s kart-engine matches 1005 run function kartmobil:sound-and-fov/dummy/gear/sound
execute if score @s kart-engine matches 1006 run function kartmobil:sound-and-fov/dummy/f1/sound
execute if score @s kart-engine matches 1007 run function kartmobil:sound-and-fov/dummy/rally/sound
execute if score @s kart-engine matches 1008 run function kartmobil:sound-and-fov/v1/sound


