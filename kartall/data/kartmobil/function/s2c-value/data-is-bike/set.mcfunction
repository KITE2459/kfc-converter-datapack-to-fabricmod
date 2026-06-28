attribute @s minecraft:armor modifier remove data-is-bike

execute on vehicle if entity @s[tag=!kartbike] on passengers if entity @s[tag=kartsaddle] run attribute @s minecraft:armor modifier add data-is-bike 0 add_value
execute on vehicle if entity @s[tag=kartbike] on passengers if entity @s[tag=kartsaddle] run attribute @s minecraft:armor modifier add data-is-bike 1 add_value
