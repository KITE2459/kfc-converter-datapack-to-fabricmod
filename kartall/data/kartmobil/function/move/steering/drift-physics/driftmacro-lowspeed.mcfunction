
#전진카트
execute if score @n[tag=kartself,distance=..5,type=#kartmobil:kartmobil] kartaccel matches 0.. at @s rotated as @p[tag=kartpassenger] rotated ~ 35 positioned as @s run rotate @s ~ ~
#후진카트
execute if score @n[tag=kartself,distance=..5,type=#kartmobil:kartmobil] kartaccel matches ..-1 at @s rotated as @p[tag=kartpassenger] rotated ~180 35 positioned as @s run rotate @s ~ ~
