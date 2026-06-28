$execute on passengers if entity @s[tag=kartdirection] run rotate @s $(rotation) ~
$execute on passengers if entity @s[tag=kartsaddle] on passengers rotated as @s[type=player] run rotate @s $(rotation) ~
$scoreboard players set @s kartmove $(speed)

scoreboard players remove @p[tag=kartpassenger] kartdrift 350