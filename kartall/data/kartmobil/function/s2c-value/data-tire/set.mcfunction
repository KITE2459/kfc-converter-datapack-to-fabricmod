attribute @s minecraft:armor modifier remove data-tire

execute on vehicle if entity @s[tag=!kart-hairpin-unlimit] on passengers if entity @s[tag=kartsaddle] run attribute @s minecraft:armor modifier add data-tire 0 add_value
execute on vehicle if entity @s[tag=kart-hairpin-unlimit] on passengers if entity @s[tag=kartsaddle] run attribute @s minecraft:armor modifier add data-tire 1 add_value
