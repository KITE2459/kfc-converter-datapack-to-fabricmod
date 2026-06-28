$execute at @s on passengers rotated as @s[tag=kart-loop-direction,scores={loop-main=0}] on vehicle positioned ^$(rightspeed) ^ ^ run function kartmobil:move/movetp/tp-bug-fix/tp
$execute at @s on passengers rotated as @s[tag=kart-loop-direction,scores={loop-main=1}] on vehicle rotated ~180 ~ positioned ^$(rightspeed) ^ ^ run function kartmobil:move/movetp/tp-bug-fix/tp

$execute at @s on passengers rotated as @s[tag=kart-loop-direction] run rotate @s ~ ~$(anglespeed)

$execute at @s on passengers rotated as @s[tag=kart-loop-direction] on vehicle positioned ^ ^ ^$(speed) run function kartmobil:move/movetp/tp-bug-fix/tp
