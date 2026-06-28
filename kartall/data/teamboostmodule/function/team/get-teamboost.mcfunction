
execute if score @s kart-engine matches 1003 run return 1
execute if score @s kart-engine matches 1008 run return 1
execute if score @s kart-engine matches 1005..1007 run return 1

execute on passengers if entity @s[tag=kartsaddle] on passengers run tag @s add kart-passenger-temp

scoreboard players operation #boostcount kart-teamboost = @s kartboostcount
# tellraw KITE2459 [{text:"[팀부 모듈]내가 가진 부스터: "},{"score":{"name":"@s","objective":"kartboostcount"}}]
execute store result score #teamboostcount kart-teamboost run clear @p[tag=kart-passenger-temp] minecraft:soul_campfire 0
# tellraw KITE2459 [{text:"[팀부 모듈]내가 가진 팀부스터: "},{"score":{"name":"#teamboostcount","objective":"kart-teamboost"}}]
scoreboard players operation #boostcount kart-teamboost -= #teamboostcount kart-teamboost
# tellraw KITE2459 [{text:"[팀부 모듈]지급될 팀부스터: "},{"score":{"name":"#boostcount","objective":"kart-teamboost"}}]

execute store result storage minecraft:kartmain teamboostcount int 1 run scoreboard players get #boostcount kart-teamboost
execute as @p[tag=kart-passenger-temp] run function teamboostmodule:team/get-teamboost-macro with storage kartmain
# say 3.팀부스터를 지급했다

tag @a[tag=kart-passenger-temp] remove kart-passenger-temp

execute on passengers if entity @s[tag=kartsaddle] run function multiplay:s2c-value/data-teamboost-count/set
