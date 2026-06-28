#전진(1.0은 디더링 없음)
execute if entity @s[tag=kart-speed-fixed-by-pad] store result score @s kartmove run data get entity @s data.kartmove-fixed-by-pad

scoreboard players set @s[tag=kart-stop] kartmove 0

execute if score @s kart-engine matches 7 run scoreboard players set @s kartmove-remain 0
execute if score @s kartmove-remain matches 10000.. run scoreboard players set @s kartmove-remain 0
scoreboard players operation @s[tag=!kart-stop] kartmove-remain += @s kartmove

    #kart-old-velocity - 0.001초 판정을 위해 필요
    execute on passengers if entity @s[tag=kart-old-velocity] run scoreboard players operation @s kartmove = @e[tag=kartself,limit=1,type=#kartmobil:kartmobil] kartmove-remain
    execute on passengers if entity @s[tag=kart-old-velocity] run scoreboard players operation @s kartmove-remain = @e[tag=kartself,limit=1,type=#kartmobil:kartmobil] kartmove
    execute on passengers if entity @s[tag=kartdirectiontemp] rotated as @s rotated ~ 0 on vehicle on passengers if entity @s[tag=kart-old-velocity] run rotate @s ~ ~

tag @s[tag=kart-command-pad-executed] remove kart-command-pad-executed
execute if score @s[tag=!kart-stop] kartmove-remain matches 1000.. at @s on passengers rotated as @s[tag=kartdirection,type=item_display] on vehicle rotated ~ 0 run function kartmobil:move/movetp/moveforward
