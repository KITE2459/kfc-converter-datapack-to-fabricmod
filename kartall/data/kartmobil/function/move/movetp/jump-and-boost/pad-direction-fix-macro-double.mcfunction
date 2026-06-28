$execute unless score #pad-third-text-exist kartmovey matches 1 at @p[tag=kartpassenger] run rotate @p[tag=kartpassenger] $(boostpaddirection) ~
$execute on passengers rotated as @s[tag=kartdirection,type=item_display] run rotate @s $(boostpaddirection) ~
$execute if score @s kart-engine matches 1002..1003 run rotate @s $(boostpaddirection) ~

$execute unless score #pad-third-text-exist kartmovey matches 1 if score @s kartaccel matches ..-1 at @p[tag=kartpassenger] rotated 180 0 run rotate @p[tag=kartpassenger] ~$(boostpaddirection) ~