execute if entity @s[tag=can-jump] run return 1

tag @s add can-jump

tellraw @p {translate:"UP & DOWN 점프","color":"yellow"}
tellraw @p {translate:"카트가 점프할 수 있게 됩니다.","color":"aqua"}

#스페이스 쓰는 엔진
execute if score @s kart-engine matches 5 run tellraw @p {translate:"V1 엔진을 사용하시네요! 유감이지만 당신의 익시드는 봉인되었습니다.","color":"red"}
execute if score @s kart-engine matches 10 run tellraw @p {translate:"CHARGE 엔진을 사용하시네요! 유감이지만 당신의 차저는 봉인되었습니다.","color":"red"}
execute if score @s kart-engine matches 1002 run tellraw @p {translate:"KEY 엔진을 사용하시네요! 유감이지만 드리프트를 할 때마다 점프합니다.","color":"red"}
execute if score @s kart-engine matches 1003 run tellraw @p {translate:"MK 엔진을 사용하시네요! 유감이지만 드리프트를 할 때마다 점프합니다.","color":"red"}
execute if score @s kart-engine matches 1008 run tellraw @p {translate:"DS 엔진을 사용하시네요! 유감이지만 드리프트를 할 때마다 점프합니다.","color":"red"}

execute on passengers if entity @s[tag=kartmodelsaddle] on passengers if data entity @s[tag=kart-special-ability] data.engine-fix on vehicle on vehicle on passengers if entity @s[tag=kartsaddle] on passengers run tellraw @s[type=player] {translate:"엔진 고정을 사용하는 카트로 UP & DOWN 트랙을 플레이할 수 없습니다.","color":"red"}
execute on passengers if entity @s[tag=kartmodelsaddle] on passengers if data entity @s[tag=kart-special-ability] data.engine-fix on vehicle on vehicle on passengers if entity @s[tag=kartsaddle] on passengers run ride @s[type=player] dismount